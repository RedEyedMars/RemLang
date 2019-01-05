package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputConditionalHeader  extends Output {

	private Output asCase = null;
	private OutputVariable declaration = null;
	private String separator = "";
	private List<Output> calls = null;

	public OutputConditionalHeader(){
	}
	public OutputConditionalHeader(OutputVariable other){
		declare(other);
	}
	public OutputConditionalHeader declare(OutputVariable var){
		declaration = var;
		return this;
	}
	public OutputConditionalHeader separator(String separator){
		this.separator = separator;
		return this;
	}
	public OutputConditionalHeader call(Output call){
		if(this.calls == null){
			this.calls = new ArrayList<Output>();
		}
		this.calls.add(call);
		return this;
	}
	public OutputConditionalHeader asCase(Output caseCase) {
		this.asCase = caseCase;
		return this;
	}
	@Override
	public void output(Consumer<String> builder){
		if(asCase==null){
			builder.accept("(");
			if(declaration!=null)declaration.output(builder);
			if(calls!=null)for(Output var:calls){
				builder.accept(separator);
				var.output(builder);
			}
			builder.accept(")");
		}
		else {
			asCase.output(builder);
			builder.accept(" :");
		}
	}

	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputConditionalHeader").add("declare", declaration);
		if(separator!=null&&!separator.equals(""))stasis = stasis.add("separator","\""+separator+"\"");
		if(calls!=null)stasis = stasis.addAll("call",calls);
		if(asCase!=null)stasis = stasis.add("asCase",asCase);
		return stasis;
	}

	@Override
	public boolean verify(OutputContext context) {
		return (asCase==null||asCase.verify(context))&&(calls==null||calls.parallelStream().allMatch(V->V.verify(context)))&&(declaration==null||declaration.verify(context));
	}

	public Stream<? extends Importable> flatStream(){
		if(asCase!=null){
			return asCase.flatStream();
		}
		else if(calls != null) {
			if(declaration != null) {
				return Stream.concat(calls.stream().flatMap(Flattenable::flatStream),
						declaration.flatStream()); 
			}
			else {
				return calls.stream().flatMap(Flattenable::flatStream);
			}
		}
		else {
			if(declaration != null) {
				return declaration.flatStream();
			}
			else {
				return Stream.empty();
			}
		}
	}
	public void getImports(Consumer<String> imports) {
	}
	public OutputVariable getDeclaredVariable() {
		return declaration;
	}


}
