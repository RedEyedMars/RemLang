package com.rem.output.helpers;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputStaticCall  extends CallableOutput {
	private OutputType type = null;
	private CallableOutput calls = new OutputCall();
	public OutputStaticCall set(OutputType type){
		this.type = type;
		return this;
	}
	public OutputStaticCall set(OutputType type,OutputExact call){
		this.type = type;
		this.calls.add(call);
		return this;
	}
	public OutputStaticCall set(OutputType type,CallableOutput calls){
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

	public Stream<? extends Importable> flatStream(){
		return Stream.concat(type.flatStream(), calls.flatStream());
	}
	@Override
	public void output(Consumer<String> builder) {
		type.add(builder);
		StringBuilder callBuilder = new StringBuilder();
		calls.add(callBuilder::append);
		if(!"".equals(callBuilder.toString())){
			builder.accept(".");
			builder.accept(callBuilder.toString());
		}
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
