package com.rem.lang.helpers.output;

import java.util.List;

public abstract class CallableOutput extends Output implements Endable {

	protected CallableOutput onEnd = null;
	public void setOnEnd(CallableOutput onEnd){
		this.onEnd = onEnd;
	}
	
	protected abstract List<Output> subjects();
	protected abstract List<OutputArguments> arguments();
	public abstract CallableOutput addCall(CallableOutput calls);
	public abstract CallableOutput add(Output subject);
	public abstract CallableOutput add(Output subject, OutputArguments arguments);
	
	public OutputOperator negate(){
		return new OutputOperator().operator("!").right(new OutputBraced(this));
	}
	public Output end(){
		return onEnd!=null?this.add(onEnd):this;
	}
}
