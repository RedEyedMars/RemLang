package com.rem.parser.parser;

public class Parameter <ValueType extends Object> extends Argument<ValueType>{
	
	private ValueType value;
	public Parameter(ValueType value){
		this.value = value;
	}
	public void set(ValueType value){
		this.value = value;
	}

	public ValueType evaluate() {
		return value;
	}

}
