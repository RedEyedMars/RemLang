package com.rem.output.helpers;

import java.util.Set;
import java.util.function.Consumer;

public class OutputQuote extends Output {

	private Output output = null;
	private boolean isString = true;
	public OutputQuote(){
	}
	public OutputQuote(String value){
		set(value);
		isString = false;
	}
	public OutputQuote set(String value){
		this.output = new OutputExact(value);
		return this;
	}
	public OutputQuote set(Output output){
		this.output  = output;
		return this;
	}
	public OutputQuote set(Object output){
		return set(output.toString());
	}
	public OutputQuote set(int i){
		return set(new OutputExact(new Integer(i).toString()));
	}
	public OutputQuote set(double d){
		return set(new OutputExact(new Double(d).toString()));
	}
	public OutputQuote set(float f){
		return set(new OutputExact(new Float(f).toString()));
	}
	public OutputQuote set(short s){
		return set(new OutputExact(new Short(s).toString()));
	}
	public OutputQuote set(long l){
		return set(new OutputExact(new Long(l).toString()));
	}
	public OutputQuote set(byte b){
		return set(new OutputExact(new Byte(b).toString()));
	}
	public OutputQuote set(boolean b){
		return set(new OutputExact(new Boolean(b).toString()));
	}
	public OutputQuote set(char c){
		return set(new OutputExact(new Character(c).toString()));
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept("\"");
		output.output(builder);
		builder.accept("\"");
	}
	@Override
	public Output stasis() {
		if(!isString){
			return new OutputStasis().name("OutputQuote").add("set",output.evaluate());
		}
		else {
			return new OutputStasis().name("OutputQuote").add("set",output.evaluate());
		}
	}
	@Override
	public boolean verify(OutputContext context) {
		return true;
	}
	@Override
	public void getImports(Set<String> imports) {
	}

}
