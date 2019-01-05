package com.rem.output.helpers;

import java.util.List;

public abstract class CallableOutput extends Output {

	protected abstract List<Output> subjects();
	protected abstract List<OutputArguments> arguments();
	public abstract CallableOutput addCall(CallableOutput calls);
	public abstract CallableOutput add(Output subject);
	public abstract CallableOutput add(Output subject, OutputArguments arguments);
}
