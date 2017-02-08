package com.rem.parser;

public interface IParser {
	public static final String DEFAULT = "$DEFAULT";
	
	public String getName();

	public void parse(ParseData data);

	public void debug_parse(ParseData data);
}
