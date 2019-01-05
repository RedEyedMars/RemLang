package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OutputStasis extends Output implements Importable {


	private String name;

	private List<OutputStasis.Method> output = new ArrayList<OutputStasis.Method>();
	@Override
	public void output(Consumer<String> builder) {
		builder.accept("new com.rem.lang.helpers.output.");
		builder.accept(name);
		builder.accept("()");
		output.forEach(O->{builder.accept(".");O.output(builder);});
	}

	@Override
	public Output stasis() {
		return this;
		/*
		return output.stream().reduce(new OutputStasis().name("OutputStatis"),(S,O)->S.add(
				new OutputStasis.Method().name("add").arg(O.stasis())),(S,N)->N);*/
	}
	public OutputStasis asIs(String name, Output output){
		this.output.add(new OutputStasis.Method().name(name).arg(output));
		return this;
	}
	public OutputStasis add(OutputStasis.Method output){
		this.output.add(output);
		return this;
	}
	public OutputStasis add(String name){
		this.output.add(new OutputStasis.Method().name(name));
		return this;
	}
	public OutputStasis add(String name, String value){
		this.output.add(new OutputStasis.Method().name(name).arg(new OutputExact(value)));
		return this;
	}
	public OutputStasis add(String name, String value1, String value2){
		this.output.add(new OutputStasis.Method().name(name).arg(new OutputExact(value1)).arg(new OutputExact(value2)));
		return this;
	}
	public OutputStasis add(String name, Stasisable statisable){
		this.output.add(new OutputStasis.Method().name(name).arg(statisable==null?new OutputExact("null"):statisable.stasis()));
		return this;
	}
	public OutputStasis add(String name, Stasisable statisable1, Stasisable statisable2){
		this.output.add(new OutputStasis.Method().name(name).arg(statisable1==null?new OutputExact("null"):statisable1.stasis()).arg(statisable2==null?new OutputExact("null"):statisable2.stasis()));
		return this;
	}

	public OutputStasis addAll(String string, List<? extends Stasisable> arguments) {
		arguments.forEach(O->add(new OutputStasis.Method().name(string).arg(O.stasis())));
		return this;
	}
	public OutputStasis addAll(String string, List<? extends Stasisable> subjects, List<? extends Stasisable> arguments) {
		IntStream.range(0,subjects.size()).forEach(I->add(string,subjects.get(I),arguments.get(I)));
		return this;
	}
	public OutputStasis indexAll(int size, BiFunction<OutputStasis,Integer,OutputStasis> function) {
		IntStream.range(0,size).boxed().reduce(this,function,(P,N)->P);
		return this;
	}
	public OutputStasis name(String string) {
		this.name = string;
		return this;
	}

	public Stream<? extends Importable> flatStream(){
		return Arrays.asList(this).stream();
	}
	@Override
	public void getImports(Consumer<String> imports) {
		imports.accept(name);
	}


	public static class Method extends Output {

		private String methodName;
		private Output arg1 = null;
		private Output arg2 = null;
		public Method name(String method) {
			this.methodName = method;
			return this;
		}

		public Stream<? extends Importable> flatStream(){
			throw new RuntimeException("Tried to call flatStream on an internal Output");
		}
		public Method arg(Output statis) {
			if(arg1==null){
				this.arg1 = statis;
			}
			else {
				this.arg2 = statis;
			}
			return this;
		}
		@Override
		public void output(Consumer<String> builder) {
			builder.accept(methodName);
			builder.accept("(");
			if(arg1!=null){
				arg1.output(builder);
				if(arg2!=null){
					builder.accept(",");
					arg2.output(builder);
				}
			}
			builder.accept(")");
		}
		@Override
		public Output stasis() {
			OutputStasis.Method method = new OutputStasis.Method().name("name").arg(new OutputExact(methodName));
			if(this.arg1 != null){
				method = method.arg(this.arg1.stasis());
			}
			if(this.arg2 != null){
				method = method.arg(this.arg2.stasis());
			}
			return new OutputStasis().name("OutputStatis.Method").add(method);
		}
		@Override
		public boolean verify(OutputContext context) {
			return true;
		}
	}


	@Override
	public boolean verify(OutputContext context) {
		return true;
	}


}
