package com.rem.output.helpers;

import java.util.stream.Stream;

public class OutputBlankStatement extends LineableOutput {
	@Override
	public OutputLine line() {
		return new OutputLine().blank();
	}
	@Override
	public Stream<Importable> flatStream() {
		return Stream.empty();
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
