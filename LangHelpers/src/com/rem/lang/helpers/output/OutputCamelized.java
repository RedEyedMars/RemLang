package com.rem.lang.helpers.output;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputCamelized extends Output {

	private Output output;
	public OutputCamelized(){
	}
	public OutputCamelized(Output output){
		this.output = output;
	}
	public OutputCamelized set(Output output){
		this.output = output;
		return this;
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept(OutputClassStructure.camelize(this.output.evaluate()));
	}
	@Override
	public Output stasis() {
		return new OutputStasis().name("OutputExact").add("set","\""+OutputClassStructure.camelize(this.output.evaluate())+"\"");
	}
	@Override
	public boolean verify(OutputContext context) {
		return true;
	}
	@Override
	public Stream<? extends Importable> flatStream(){
		return Stream.empty();
	}

}
