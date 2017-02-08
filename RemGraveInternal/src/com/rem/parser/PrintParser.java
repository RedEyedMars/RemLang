package com.rem.parser;

public class PrintParser extends ConcreteParser implements IParser{

	private String phrase;

	public PrintParser(String phrase){
		this.phrase = phrase;
	}
	
	@Override
	public void real_parse(ParseData data) {
		System.out.println(phrase);
	}

}
