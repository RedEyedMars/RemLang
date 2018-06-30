package com.rem.output.helpers;

import java.util.Set;
import java.util.function.Consumer;

public class OutputExact extends Output {

	public OutputExact(){
	}
	public OutputExact(String value){
		this.value = value;
	}
	public OutputExact set(String value){
		this.value = value;
		return this;
	}
	public OutputExact set(Object output){
		return set(output.toString());
	}
	public OutputExact set(int i){
		return set(new Integer(i).toString());
	}
	public OutputExact set(double d){
		return set(new Double(d).toString());
	}
	public OutputExact set(float f){
		return set(new Float(f).toString());
	}
	public OutputExact set(short s){
		return set(new Short(s).toString());
	}
	public OutputExact set(long l){
		return set(new Long(l).toString());
	}
	public OutputExact set(byte b){
		return set(new Byte(b).toString());
	}
	public OutputExact set(boolean b){
		return set(new Boolean(b).toString());
	}
	public OutputExact set(char c){
		return set(new Character(c).toString());
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept(this.value);
	}
	@Override
	public Output stasis() {
		return new OutputStasis().name("OutputExact").add("set","\""+value+"\"");
	}
	@Override
	public boolean verify(OutputContext context) {
		return true;
	}
	@Override
	public void getImports(Set<String> imports) {
	}

}
