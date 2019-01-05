package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OutputAnonymousInstance  extends CallableOutput {
	private OutputType type = null;
	private OutputArguments arguments = new OutputArguments();
	private CallableOutput calls = null;
	private int arraySymbols = 0;
	private List<OutputVariable> variables = new ArrayList<OutputVariable>();
	private List<OutputMethod> methods = new ArrayList<OutputMethod>();
	public OutputAnonymousInstance(){
	}
	public OutputAnonymousInstance(OutputType type, OutputArguments arguments){
		set(type,arguments);
	}
	public OutputAnonymousInstance set(OutputType type, OutputArguments arguments){
		this.type = type;
		this.arguments = new OutputNewObjectArguments().set(arguments);
		return this;
	}
	public CallableOutput addCall(CallableOutput calls){
		this.calls = calls;
		return this;
	}
	public CallableOutput add(Output subject){
		if(calls==null){
			calls = new OutputCall();
		}
		calls.add(subject);
		return this;
	}
	public CallableOutput add(Output subject, OutputArguments arguments){
		if(calls==null){
			calls = new OutputCall();
		}
		calls.add(subject,arguments);
		return calls;
	}
	public OutputAnonymousInstance variable(OutputVariable variable){
		this.variables.add(variable);
		return this;
	}
	public OutputAnonymousInstance method(OutputMethod method){
		this.methods.add(method);
		return this;
	}
	public Stream<? extends Importable> flatStream(){
		return calls!=null?Stream.of(type.flatStream(), arguments.flatStream(), calls.flatStream(),variables.stream().flatMap(V->V.flatStream()),methods.stream().flatMap(M->M.flatStream())).flatMap(S->S):
			               Stream.of(type.flatStream(), arguments.flatStream(),variables.stream().flatMap(V->V.flatStream()),methods.stream().flatMap(M->M.flatStream())).flatMap(S->S);
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept("new ");
		type.add(builder);
		IntStream.range(0,arraySymbols).forEach(I->builder.accept("[]"));
		arguments.add(builder);
		builder.accept("{");
		variables.forEach(V->V.output(builder));
		methods.forEach(M->M.output(builder));
		builder.accept("}");
		if(calls!=null){
			builder.accept(".");
			calls.output(builder);
		}
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputAnonymousInstance");
		stasis = stasis.add("set",type,arguments);
		if(calls!=null){
			stasis = stasis.add("addCall",calls);
		}
		stasis = stasis.addAll("variable", variables);
		stasis = stasis.addAll("method", methods);
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return type.verify(context)&&arguments.verify(context)&&variables.stream().allMatch(M->M.verify(context))&&methods.stream().allMatch(M->M.verify(context))&&(calls==null||calls.verify(context));
	}
	@Override
	protected List<Output> subjects() {
		if(calls!=null){
			return new OutputCall().add(new OutputExact("new "+type.evaluate()),arguments).addCall(calls).subjects();
		}
		else {
			return new OutputCall().add(new OutputExact("new "+type.evaluate()),arguments).subjects();
		}
	}
	@Override
	protected List<OutputArguments> arguments() {
		if(calls!=null){
			return new OutputCall().add(new OutputExact("new "+type.evaluate()),arguments).addCall(calls).arguments();
		}
		else {
			return new OutputCall().add(new OutputExact("new "+type.evaluate()),arguments).arguments();
		}
	}
}
