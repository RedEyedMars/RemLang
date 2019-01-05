package com.rem.lang.helpers.output;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputNewNumber  extends Output {
	private OutputType type = null;
	private OutputArguments arguments = new OutputArguments();
	public OutputNewNumber(){
	}
	public OutputNewNumber(OutputType type, Output number){
		set(type,arguments);
	}
	public Output set(OutputType type, Output number){
		if(type.evaluate().equals("double")||
				type.evaluate().equals("float")||
				type.evaluate().equals("short")||
				type.evaluate().equals("long")||
				type.evaluate().equals("byte")||
				type.evaluate().equals("boolean")){
			this.type = new OutputType(new OutputCamelized(type));
		}
		else if(type.evaluate().equals("int")){
			this.type = new OutputType("Integer");
		}
		else {
			return type;
		}
		
		this.arguments = new OutputNewObjectArguments().set(new OutputArguments().add(number));
		return this;
	}
	public Stream<? extends Importable> flatStream(){
		return Stream.empty();
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept("new ");
		type.add(builder);
		arguments.add(builder);
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputNewNumber");
		stasis = stasis.add("set",type,arguments);
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return arguments.verify(context);
	}
}
