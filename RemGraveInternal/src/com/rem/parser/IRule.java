package com.rem.parser;

public interface IRule extends IParser{
	public void setup();
	public <Type> Parameter<Type>[] getParameters();
}
