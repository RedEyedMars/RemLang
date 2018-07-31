package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OutputConcatenation extends Output {

	protected List<Output> values = new ArrayList<Output>();
	public OutputConcatenation(){
	}
	public OutputConcatenation add(String value){
		this.values.add(new OutputExact(value));
		return this;
	}
	public OutputConcatenation add(Output output){
		this.values.add(output);
		return this;
	}
	@Override
	public void output(Consumer<String> builder) {
		IntStream.range(0,values.size()).forEach(I->{if(I>0)builder.accept("+");values.get(I).output(builder);});
	}
	@Override
	public Output stasis() {
		if(values.size()==1){
			return values.get(0).stasis();
		}
		else {
			OutputStasis stasis = new OutputStasis().name("OutputConcatenation.SansPlus");
			values.forEach(V->stasis.add("add",V));
			return stasis;
		}
	}
	@Override
	public boolean verify(OutputContext context) {
		return true;
	}

	public Stream<? extends Importable> flatStream(){
		return Stream.empty();
	}
	public List<Output> getOutputs() {
		return values;
	}
	
	public static class SansPlus extends OutputConcatenation {
		@Override
		public void output(Consumer<String> builder) {
			values.forEach(V->V.output(builder));
		}
	}

}
