package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class OutputConditionalHeader  extends Output {

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
	@Override
	public void output(Consumer<String> builder){
		builder.accept("(");
		if(declaration!=null)declaration.output(builder);
		if(calls!=null)for(Output var:calls){
			builder.accept(separator);
			var.output(builder);
		}
		builder.accept(")");
	}

	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputConditionalHeader").add("declare", declaration);
		if(separator!=null)stasis = stasis.add("separator",separator);
		if(calls!=null)stasis = stasis.addAll("call",calls);
		return stasis;
	}

	@Override
	public boolean verify(OutputContext context) {
		return (calls==null||calls.parallelStream().allMatch(V->V.verify(context)))&&(declaration==null||declaration.verify(context));
	}

	@Override
	public void getImports(Set<String> imports) {
		if(calls!=null)calls.parallelStream().forEach(V->V.getImports(imports));
		if(declaration!=null)declaration.getImports(imports);
	}
	public OutputVariable getDeclaredVariable() {
		return declaration;
	}


}
