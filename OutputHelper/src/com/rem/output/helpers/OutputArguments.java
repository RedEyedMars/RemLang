package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
	public Stream<Importable> flatStream(){
		return arrayArguments!=null?
				Stream.concat(arguments.stream().flatMap(Flattenable::flatStream),
				             arrayArguments.stream().flatMap(Flattenable::flatStream))
				:arguments.stream().flatMap(Flattenable::flatStream);
	}
	@Override
	public void output(Consumer<String> builder) {
		if(arrayArguments==null||!arguments.isEmpty()){
			builder.accept("(");
			outputInnards(builder);
			builder.accept(")");
		}
		if(arrayArguments!=null)arrayArguments.forEach(AA->AA.add(builder));
	}
	protected void outputInnards(Consumer<String> builder){
		IntStream.range(0,arguments.size()).forEach(P->{
		  if(arguments.get(P) instanceof OutputArguments){
			  ((OutputArguments)arguments.get(P)).outputInnards(builder);
		  }
		  else {
			  if(P>0)builder.accept(",");
			  arguments.get(P).add(builder);
		  }});
	}

	public Output stasis(){
		OutputStasis stasis = new OutputStasis().name("OutputArguments").indexAll(arguments.size(),(B,I)->{
			if(arguments.get(I) instanceof OutputArguments){
				B.addAll("add",((OutputArguments)arguments.get(I)).arguments);
			}
			else {
				B.add("add",arguments.get(I));
			}
			return B;
		});
		if(arrayArguments!=null){
			stasis = stasis.addAll("array", arrayArguments);
		}
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return arguments.parallelStream().allMatch(A->A.verify(context));
	}
	public boolean isEmpty(){
		return arguments.isEmpty();
	}
	public boolean isSingle(){
		return arguments.size()==1;
	}
	public Output get(int index){
		return arguments.get(index);
	}
}
