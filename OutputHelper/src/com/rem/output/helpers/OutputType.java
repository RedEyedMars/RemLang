package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OutputType extends Output {

	private Output name;
	private Template templates = null;
	private int arraySymbols = 0;
	private boolean inlineList = false;
	public OutputType(){}
	public OutputType(Output name){
		set(name);
	}
	public OutputType(String name){
		set(name);
	}
	public OutputType set(Output name) {
		this.name = name;
		return this;
	}
	public OutputType set(String name) {
		this.name = new OutputExact(name);
		return this;
	}
	public OutputType add(OutputType next){
		name = new OutputType.MultiName().set(name,next);
		return this;
	}
	public OutputType set(Output parent, Output name) {
		name = new OutputType.MultiName().set(parent,name);
		return this;
	}
	public OutputType or(OutputType rightSide) {
		name = new OutputType.MultiChoice().set(name,rightSide);
		return this;
	}
	public OutputType array(){
		++arraySymbols;
		return this;
	}
	public OutputType template(OutputType... templateType){
		if(templates==null){
			templates = new Template().set(Arrays.asList(templateType));
		}
		else {
			templates.add(Arrays.asList(templateType));
		}
		return this;
	}
	public OutputType template(Template templates){
		this.templates = templates;
		return this;
	}
	public OutputType isInlineList(){
		this.inlineList = true;
		return this;
	}
	public void getImports(Set<String> imports) {
		if(name!=null)imports.add(name.evaluate());
	}
	public OutputClass getAsClass() {
		return OutputHelper.classMap.get(name.evaluate());
	}
	@Override
	public void output(Consumer<String> builder) {
		if(name!=null)name.add(builder);
		if(templates!=null)templates.output(builder);
		IntStream.range(0, arraySymbols).forEach(I->builder.accept("[]"));
		if(inlineList)builder.accept("...");
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputType").add("set", name);
		if(templates!=null)stasis.add("template",templates);
		IntStream.range(0, arraySymbols).forEach(I->stasis.add("array"));
		if(inlineList)stasis.add("isInlineList");
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return OutputHelper.classMap.containsKey(name.evaluate())&&(templates==null||templates.verify(context));
	}

	public static class MultiName extends Output {
		private Output parent;
		private Output name;
		public MultiName set(Output parent, Output name){
			this.parent = parent;
			this.name = name;
			return this;
		}
		public void output(Consumer<String> builder){
			if(parent!=null){
				parent.add(builder);
				builder.accept(".");
			}
			name.add(builder);}

		@Override
		public Output stasis() {
			if(parent!=null){
				return new OutputStasis().name("OutputType.MultiName").add("set", parent, name);
			}
			else {
				return name.stasis();
			}
		}
		@Override
		public boolean verify(OutputContext context) {
			return OutputHelper.classMap.containsKey(evaluate());
		}
		@Override
		public void getImports(Set<String> imports) {
			throw new RuntimeException("Tried to call import on an internal Output");
		}
	}
	public static class MultiChoice extends Output {
		private Output left;
		private OutputType right;
		public MultiChoice set(Output left, OutputType right){
			this.left = left;
			this.right = right;
			return this;
		}
		public void output(Consumer<String> builder){
			left.add(builder);
			builder.accept("|");
			right.add(builder);
		}

		@Override
		public Output stasis() {
			return new OutputStasis().name("OutputType.MultiChoice").add("set", left, right);
		}
		@Override
		public boolean verify(OutputContext context) {
			return OutputHelper.classMap.containsKey(left.evaluate())&&right.verify(context);
		}
		@Override
		public void getImports(Set<String> imports) {
			throw new RuntimeException("Tried to call import on an internal Output");
		}
	}
	public static class Template extends Output {
		private List<OutputType> names = new ArrayList<OutputType>();
		public Template add(OutputType name){
			this.names.add(name);
			return this;
		}
		public Template set(List<OutputType> names){
			this.names.addAll(names);
			return this;
		}
		public Template add(List<OutputType> names){
			this.names.addAll(names);
			return this;
		}
		public void output(Consumer<String> builder){
			builder.accept("<");
			IntStream.range(0,names.size()).forEach(I->{if(I>0){builder.accept(", ");}names.get(I).output(builder);});
			builder.accept(">");
		}

		@Override
		public Output stasis() {
			return new OutputStasis().name("OutputType.Template").addAll("add", names);
		}
		@Override
		public boolean verify(OutputContext context) {
			return names.parallelStream().allMatch(T->T.verify(context));
		}
		@Override
		public void getImports(Set<String> imports) {
			throw new RuntimeException("Tried to call import on an internal Output");
		}
	}
	@Override
	public String toString(){
		return evaluate();
	}
}
