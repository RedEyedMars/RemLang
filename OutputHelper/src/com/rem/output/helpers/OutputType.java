package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OutputType extends Output implements Importable {

	public static final OutputType Any = new OutputType("$$$"){
		{
			name.clear();
			name.add(new OutputExact(""));
		}
		public Output stasis(){
			return new OutputExact("com.rem.output.helpers.OutputType.Any");
		}
		public boolean verify(OutputContext context){
			return true;
		}
		public OutputClass getAsClass(){
			return new OutputClass(){
				@Override
				public Boolean hasVariableInContext(String name){
					return true;
				}
				@Override
				public OutputVariable getVariableFromContext(String name){
					return new OutputVariable().set(OutputType.Any, "$ANY$"); 
				}
				@Override
				public Boolean hasMethodInContext(String name){
					return true;
				}
				@Override
				public OutputMethod getMethodFromContext(String name){
					return new OutputMethod().set(OutputType.Any, new OutputExact("$ANY$")); 
				}
			};
		}
	};
	protected List<Output> name = new ArrayList<Output>();
	protected List<Output> asGet = new ArrayList<Output>();
	private Template templates = null;
	private int arraySymbols = 0;
	private boolean inlineList = false;
	public OutputType(){}
	public OutputType(OutputString name){
		add(name);
	}
	public OutputType(Output name){
		add(name);
	}
	public OutputType(String name){
		add(new OutputExact(name));
	}
	public OutputType set(Output... types){
		Arrays.asList(types).forEach(T->{if(T instanceof OutputString)add((OutputString)T);else add(T);});
		return this;
	}
	public OutputType add(OutputString next){
		this.name.add(next.getOutput().vibrate());
		this.asGet.add(next);
		return this;
	}
	public OutputType add(OutputType next){
		name.addAll(next.name);
		asGet.addAll(next.asGet);
		return this;
	}
	public OutputType add(String next){
		this.name.add(new OutputExact(next));
		this.asGet.add(new OutputQuote().set(next));
		return this;
	}
	public OutputType add(Output next){
		this.name.add(next);
		this.asGet.add(new OutputQuote().set(next));
		return this;
	}
	public OutputType or(OutputType rightSide) {
		return new OutputType.MultiChoice().set(this,rightSide);
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
	public Stream<? extends Importable> flatStream(){
		return Stream.of(this);
	}
	public void getImports(Consumer<String> imports) {
		name.forEach(T->imports.accept(T.evaluate()));
	}
	public OutputClass getAsClass() {
		if(name.isEmpty())return null;
		return IntStream.range(1, name.size()).boxed().map(I->name.get(I).evaluate()).reduce(
				OutputHelper.classMap.get(name.get(0).evaluate()),(C,N)->C==null?null:C.getEnclosedClass(N),(L,R)->R);
	}
	public OutputStaticCall getAsGetClass(){
		OutputStaticCall call = new OutputStaticCall().set(new OutputType("com.rem.output.helpers.OutputHelper"));
		if(!asGet.isEmpty())call.add(new OutputExact("getClass"), new OutputArguments().add(asGet.get(0)));
		return IntStream.range(1, asGet.size()).boxed().map(I->asGet.get(I)).reduce(
				call,(C,N)->C.add(new OutputExact("getEnclosedClass"),new OutputArguments().add(N)),(L,R)->R);
	}
	@Override
	public void output(Consumer<String> builder) {
		IntStream.range(0, name.size()).forEach(I->{if(I>0)builder.accept(".");name.get(I).add(builder);});
		if(templates!=null)templates.output(builder);
		IntStream.range(0, arraySymbols).forEach(I->builder.accept("[]"));
		if(inlineList)builder.accept("...");
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputType").addAll("add", name);
		if(templates!=null)stasis.add("template",templates);
		IntStream.range(0, arraySymbols).forEach(I->stasis.add("array"));
		if(inlineList)stasis.add("isInlineList");
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return getAsClass()!=null&&(templates==null||templates.verify(context));
	}
	public static class MultiChoice extends OutputType {
		private Output left;
		private OutputType right;
		public MultiChoice set(OutputType left, OutputType right){
			this.left = left==null?null:left.evaluate().equals("")?null:left;
			this.right = right;
			return this;
		}
		public void output(Consumer<String> builder){
			if(left!=null){
				left.add(builder);
				builder.accept("|");
			}
			right.add(builder);
		}

		@Override
		public Output stasis() {
			return new OutputStasis().name("OutputType.MultiChoice").add("set", left, right);
		}
		@Override
		public boolean verify(OutputContext context) {
			return (left==null||OutputHelper.classMap.containsKey(left.evaluate()))&&right.verify(context);
		}
		public Stream<? extends Importable> flatStream(){
			return Stream.concat(left!=null?left.flatStream():Stream.empty(), right.flatStream());
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
		public Stream<? extends Importable> flatStream(){
			throw new RuntimeException("Tried to call flatStream on an internal Output");
		}
	}
	@Override
	public String toString(){
		return evaluate();
	}
}
