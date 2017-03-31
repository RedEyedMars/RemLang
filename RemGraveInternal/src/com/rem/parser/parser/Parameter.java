package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class Parameter <ValueType extends Object> extends Argument<ValueType>{
	
	private int index;
	public Parameter(int index){
		this.index = index;
	}

	@SuppressWarnings("unchecked")
	public ValueType evaluate(ParseContext context) {
		return (ValueType) context.getParameter(index);
	}

}
