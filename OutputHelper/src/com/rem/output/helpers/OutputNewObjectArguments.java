package com.rem.output.helpers;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OutputNewObjectArguments extends OutputArguments {
	@Override
	public void output(Consumer<String> builder) {
		if(arrayArguments!=null){
			arrayArguments.stream().forEach(AA->AA.add(builder));
			builder.accept("{");
			outputInnards(builder);
			builder.accept("}");
		}
		else {
			builder.accept("(");
			outputInnards(builder);
			builder.accept(")");
		}
	}
}
