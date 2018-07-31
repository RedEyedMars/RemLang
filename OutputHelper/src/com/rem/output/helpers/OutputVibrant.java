package com.rem.output.helpers;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputVibrant extends Output {

	private Output output;

	public OutputVibrant(Output output){
		this.output = output;
	}
	public OutputVibrant set(Output output){
		this.output = output;
		return this;
	}
	
	@Override
	public void output(Consumer<String> builder) {
		this.output.output(builder);
	}

	@Override
	public Output stasis() {
		return this;
	}

	@Override
	public boolean verify(OutputContext context) {
		return this.verify(context);
	}

	public Stream<? extends Importable> flatStream(){
		return output.flatStream();
	}

}
