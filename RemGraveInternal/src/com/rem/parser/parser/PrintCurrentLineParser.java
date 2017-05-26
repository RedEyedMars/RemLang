package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class PrintCurrentLineParser extends ConcreteParser implements IParser{

	private String phrase;

	public PrintCurrentLineParser(){
		phrase = "";
	}
	public PrintCurrentLineParser(String phrase){
		this.phrase = phrase;
	}
	
	@Override
	public void real_parse(ParseContext data) {
		System.out.println(phrase+":"+data.getLineNumber(data.getFrontPosition())+":"+data.getLine());
	}

}
