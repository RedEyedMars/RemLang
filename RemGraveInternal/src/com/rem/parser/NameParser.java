package com.rem.parser;

public class NameParser extends RegexParser{

	public NameParser(String listName, String... parsers) {
		super(listName, listName);
		if(parsers==null||parsers.length==0){
			setup("");
		}
		StringBuilder builder = new StringBuilder();
		String pipe = "";
		for(int i=0;i<parsers.length;++i){
			builder.append(pipe);
			builder.append(parsers[i]);
			builder.append("\\b");
			pipe = "|";
		}
		setup(builder.toString());
	}

}
