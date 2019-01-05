package com.rem.output.helpers;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OutputArrayOnlyArguments extends OutputArguments {
	@Override
	public void output(Consumer<String> builder) {
		builder.accept("[");
		IntStream.range(0,arguments.size()).forEach(P->{if(P>0)builder.accept("][");arguments.get(P).add(builder);});
		builder.accept("]");
	}
	@Override
	public OutputArrayOnlyArguments array(Output subject){
		arguments.add(subject);
		return this;
	}

	public Output stasis(){
		return new OutputStasis().name("OutputArrayOnlyArguments").addAll("add", arguments);

	}
}
