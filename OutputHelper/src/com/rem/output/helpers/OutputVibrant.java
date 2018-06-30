package com.rem.output.helpers;

import java.util.Set;
import java.util.function.Consumer;

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

	@Override
	public void getImports(Set<String> imports) {
		this.output.getImports(imports);
	}

}
