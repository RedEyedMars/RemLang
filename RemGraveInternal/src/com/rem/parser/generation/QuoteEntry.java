package com.rem.parser.generation;


public class QuoteEntry extends StringEntry{

	public QuoteEntry(String value) {
		super(value);
	}

	public void get(StringBuilder builder) {
		builder.append('\"');
		super.get(builder);
		builder.append('\"');
	}

}
