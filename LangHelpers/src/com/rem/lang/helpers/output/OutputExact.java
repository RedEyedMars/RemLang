package com.rem.lang.helpers.output;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputExact extends Output {

	public OutputExact(){
	}
	public OutputExact(char value){
		this.value = Character.toString(value);
	}
	public OutputExact(int value){
		this.value = Integer.toString(value);
	}
	public OutputExact(long value){
		this.value = Long.toString(value);
	}
	public OutputExact(double value){
		this.value = Double.toString(value);
	}
	public OutputExact(short value){
		this.value = Short.toString(value);
	}
	public OutputExact(boolean value){
		this.value = Boolean.toString(value);
	}
	public OutputExact(String value){
		this.value = value;
	}
	public OutputExact(Object value){
		this.value = value.toString();
	}
	public OutputExact set(String value){
		this.value = value;
		return this;
	}
	public OutputExact set(Object output){
		this.value = output.toString();
		return this;
	}
	public OutputExact set(int i){
		this.value = Integer.toString(i);
		return this;
	}
	public OutputExact set(double d){
		this.value = Double.toString(d);
		return this;
	}
	public OutputExact set(float f){
		this.value = Float.toString(f);
		return this;
	}
	public OutputExact set(short s){
		this.value = Short.toString(s);
		return this;
	}
	public OutputExact set(long l){
		this.value = Long.toString(l);
		return this;
	}
	public OutputExact set(byte b){
		this.value = Byte.toString(b);
		return this;
	}
	public OutputExact set(boolean b){
		this.value = Boolean.toString(b);
		return this;
	}
	public OutputExact set(char c){
		this.value = Character.toString(c);
		return this;
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

	public Stream<? extends Importable> flatStream(){
		return Stream.empty();
	}

}
