package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class NamedParserContainer implements IParser{
	private String name;
	private IParser parser;
	public NamedParserContainer(String name, IParser parser){
		this.parser = parser;
		this.name = name;
	}
	public NamedParserContainer(IParser parser, String name){
		this.parser = parser;
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public IParser getParser(){
		return parser;
	}
	@Override
	public void parse(ParseContext data) {
		parser.parse(data);
	}
	@Override
	public void debug_parse(ParseContext data) {
		parser.debug_parse(data);
	}
	@Override
	public boolean isTerminalParser() {
		return parser.isTerminalParser();
	}
}
