package com.rem.lang.helpers.output;

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
	public OutputParameters addSafe(List<OutputVariable> other){
		other.stream().filter(V->!parameters.stream().anyMatch(P->P.getName().evaluate().equals(V.getName().evaluate()))).forEach(V->this.parameters.add(V));
		return this;
	}
	public OutputParameters addSafe(OutputParameters otherParameters){
		return addSafe(otherParameters.parameters);
	}
	public OutputParameters add(List<OutputVariable> other){
		this.parameters.addAll(other);
		return this;
	}
	public OutputParameters add(OutputVariable newOutputVariable){
		this.parameters.add(newOutputVariable);
		return this;
	}
	public OutputParameters add(OutputParameters otherParameters){
		return add(otherParameters.parameters);
	}
	public Stream<? extends Importable> flatStream(){
		return parameters.stream().flatMap(Flattenable::flatStream);
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept("(");
		IntStream.range(0,parameters.size()).forEach(P->{if(P>0)builder.accept(",");parameters.get(P).asParameter().add(builder);});
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
	public List<OutputVariable> asVariableList() {
		return parameters;
	}
	public String getAsSignature() {
		StringBuilder signature = new StringBuilder();
		signature.append("(");
		if(parameters.size()>0){
			signature.append(parameters.get(0).getType().evaluate());
		}
		IntStream.range(1, parameters.size()).forEach(I->{signature.append(",");signature.append(parameters.get(I).getType().evaluate());});
		signature.append(")");
		return signature.toString();
	}
}
