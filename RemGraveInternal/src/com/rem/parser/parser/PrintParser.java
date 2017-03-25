package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class PrintParser extends ConcreteParser implements IParser{

	private String phrase;

	public PrintParser(String phrase){
		this.phrase = phrase;
	}
	
	@Override
	public void real_parse(ParseContext data) {
		System.out.println(phrase);
	}

}
