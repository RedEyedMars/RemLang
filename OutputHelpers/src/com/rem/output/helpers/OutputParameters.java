package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OutputParameters extends Output{

	private List<OutputVariable> parameters = new ArrayList<OutputVariable>();
	public OutputParameters(){
		
	}
	public OutputParameters(OutputParameters other){
		this.parameters.addAll(other.parameters);
	}
	public OutputParameters(List<OutputVariable> other){
		this.parameters.addAll(other);
	}
	public OutputParameters add(OutputVariable newOutputVariable){
		this.parameters.add(newOutputVariable);
		return this;
	}
	public Stream<? extends Importable> flatStream(){
		return parameters.stream().flatMap(Flattenable::flatStream);
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept("(");
		IntStream.range(0,parameters.size()).forEach(P->{if(P>0)builder.accept(",");parameters.get(P).add(builder);});
		builder.accept(")");
	}
	@Override
	public Output stasis() {
		return new OutputStasis().name("OutputParameters").addAll("add",parameters);
	}
	@Override
	public boolean verify(OutputContext context) {
		return parameters.parallelStream().allMatch(P->P.verify(context));
	}
	public List<OutputVariable> getVariables() {
		return parameters;
	}

}
