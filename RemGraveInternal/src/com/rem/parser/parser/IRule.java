package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public interface IRule extends IParser{
	public void setup();
	public String getTokenName();
}
