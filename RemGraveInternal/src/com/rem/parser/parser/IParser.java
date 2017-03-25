package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public interface IParser {
	public static final String DEFAULT = "$DEFAULT";
	
	public String getName();

	public void parse(ParseContext data);

	public void debug_parse(ParseContext data);
}
