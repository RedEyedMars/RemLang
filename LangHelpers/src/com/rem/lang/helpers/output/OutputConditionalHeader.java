package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class OutputConditionalHeader  extends Output {

	private Output asCase = null;
	private OutputVariable declaration = null;
	private String separator = "";
	private List<Output> calls = null;
	private List<OutputExact> separators = null;
	private boolean forgetFirst = false;

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
	public OutputConditionalHeader forgetFirst(){
		this.forgetFirst = true;
		return this;
	}
	public OutputConditionalHeader call(Output call){
		if(this.calls == null){
			this.calls = new ArrayList<Output>();
		}
		this.calls.add(call);
		if(separators == null){
			separators = new ArrayList<OutputExact>();
			if(this.declaration!=null){
				separators.add(new OutputExact(separator));
			}
			else {
				separators.add(new OutputExact(""));
			}
		}
		else {
			separators.add(new OutputExact(separator));
		}
		return this;
	}

	public boolean hasContents(){
		return calls!=null&&!calls.isEmpty();
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
			if(calls!=null){
				boolean isFirst = forgetFirst;
				for(Output var:calls){
					if(!isFirst)builder.accept(separator);
					var.output(builder);
					isFirst = false;
				}
			}
			builder.accept(")");
		}
		else {
			builder.accept(" ");
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
