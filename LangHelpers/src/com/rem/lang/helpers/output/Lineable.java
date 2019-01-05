package com.rem.lang.helpers.output;

public interface Lineable extends Stasisable, Verifyable {
	public OutputLine line();
	public OutputContext setParent(OutputContext context);
}
