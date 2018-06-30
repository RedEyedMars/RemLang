package com.rem.output.helpers;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OutputNewObjectArguments extends OutputArguments {
	@Override
	public void output(Consumer<String> builder) {
		if(arrayArguments!=null){
			arrayArguments.stream().forEach(AA->AA.add(builder));
			builder.accept("{");
			IntStream.range(0,arguments.size()).forEach(P->{if(P>0)builder.accept(",");arguments.get(P).add(builder);});
			builder.accept("}");
		}
		else {
			builder.accept("(");
			IntStream.range(0,arguments.size()).forEach(P->{if(P>0)builder.accept(",");arguments.get(P).add(builder);});
			builder.accept(")");
		}
	}
}
