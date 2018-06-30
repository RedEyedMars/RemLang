package com.rem.output.helpers;

import java.util.Set;

public class OutputBlankStatement extends LineableOutput {
	@Override
	public OutputLine line() {
		return new OutputLine().blank();
	}

	@Override
	public void getImports(Set<String> imports) {
	}
	@Override
	public Output stasis() {
		return new OutputStasis().name("OutputBlankStatement");
	}
	@Override
	public boolean verify(OutputContext context) {
		return true;
	}
	@Override
	public OutputContext setParent(OutputContext context) {
		return null;
	}

}
