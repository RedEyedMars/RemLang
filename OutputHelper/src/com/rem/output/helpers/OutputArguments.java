package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OutputArguments extends Output {

	protected List<Output> arguments = new ArrayList<Output>();
	protected List<Output> arrayArguments = null;

	public OutputArguments(){
	}
	public OutputArguments(OutputArguments other){
		set(other);
	}
	public OutputArguments set(OutputArguments other){
		this.arguments = other.arguments;
		this.arrayArguments = other.arrayArguments;
		return this;
	}
	public OutputArguments add(Output arg){
		arguments.add(arg);
		return this;
	}
	public OutputArguments array(Output subject) {
		if(arrayArguments==null){
			arrayArguments = new ArrayList<Output>();
		}
		arrayArguments.add(subject);
		return this;
	}
	public void getImports(Set<String> imports) {
		arguments.parallelStream().forEach(A->A.getImports(imports));
		if(arrayArguments!=null)arguments.parallelStream().forEach(A->A.getImports(imports));
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept("(");
		IntStream.range(0,arguments.size()).forEach(P->{if(P>0)builder.accept(",");arguments.get(P).add(builder);});
		builder.accept(")");
		if(arrayArguments!=null)arrayArguments.forEach(AA->AA.add(builder));
	}

	public Output stasis(){
		if(arrayArguments==null){
			return new OutputStasis().name("OutputArguments").addAll("add", arguments);
		}
		else {
			return new OutputStasis().name("OutputArguments").addAll("add", arguments).addAll("array", arrayArguments);
		}

	}
	@Override
	public boolean verify(OutputContext context) {
		return arguments.parallelStream().allMatch(A->A.verify(context));
	}
}
