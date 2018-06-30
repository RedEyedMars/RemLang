package com.rem.output.helpers;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class OutputStaticCall  extends CallableOutput {
	private OutputType type = null;
	private CallableOutput calls = new OutputCall();
	public OutputStaticCall set(OutputType type){
		this.type = type;
		return this;
	}
	public OutputStaticCall set(OutputType type,OutputCall calls){
		this.type = type;
		this.calls = calls;
		return this;
	}
	public OutputStaticCall addCall(CallableOutput call){
		this.calls = call;
		return this;
	}
	public OutputStaticCall add(Output subject){
		calls.add(subject);
		return this;
	}
	public OutputStaticCall add(Output subject, OutputArguments arguments){
		calls.add(subject,arguments);
		return this;
	}
	public void getImports(Set<String> imports) {
		type.getImports(imports);
		calls.getImports(imports);
	}
	@Override
	public void output(Consumer<String> builder) {
		type.add(builder);
		builder.accept(".");
		calls.add(builder);
	}
	@Override
	public Output stasis() {
		return new OutputStasis().name("OutputStaticCall").add("set",type,calls);
	}
	@Override
	public boolean verify(OutputContext context) {
		return type.verify(context)&&calls.verify(type.getAsClass());
	}
	@Override
	protected List<Output> subjects() {
		if(calls!=null){
			return new OutputCall().add(type).addCall(calls).subjects();
		}
		else {
			return new OutputCall().add(type).subjects();
		}
	}
	@Override
	protected List<OutputArguments> arguments() {
		if(calls!=null){
			return new OutputCall().add(type).addCall(calls).arguments();
		}
		else {
			return new OutputCall().add(type).arguments();
		}
	}
}
