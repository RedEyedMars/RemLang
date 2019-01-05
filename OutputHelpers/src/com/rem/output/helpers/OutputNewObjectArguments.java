package com.rem.output.helpers;

import java.util.function.Consumer;

public class OutputNewObjectArguments extends OutputArguments {
	@Override
	public void output(Consumer<String> builder) {
		if(arrayArguments!=null){
			arrayArguments.stream().forEach(AA->{
				builder.accept("[");
				AA.add(builder);
				builder.accept("]");
			});
			if(hasMethodArguments){
				builder.accept("{");
				outputInnards(builder);
				builder.accept("}");
			}
		}
		else {
			builder.accept("(");
			outputInnards(builder);
			builder.accept(")");
		}
	}
}
