package com.rem.output.helpers;

public interface Lineable extends Stasisable, Verifyable {
	public OutputLine line();
	public OutputContext setParent(OutputContext context);
}
