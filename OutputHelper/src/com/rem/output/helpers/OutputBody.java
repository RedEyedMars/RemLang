package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OutputBody extends OutputContext {

	private List<LineableOutput> outputs = new ArrayList<>();
	public OutputBody(){
	}
	public OutputBody(OutputBody other){
		addAll(other);
	}
	public OutputBody addAll(OutputBody body){
		if(body==null){
			return this;
		}
		this.outputs.addAll(body.outputs);
		return this;
	}
	@Override
	public OutputBody setParent(OutputContext newParentContext){
		super.setParent(newParentContext);
		return this;
	}
	public OutputBody add(OutputVariable variable){
		if(variable==null){
			return this;
		}
		outputs.add(new OutputStatement().set(variable));
		addVariable(variable);
		return this;
	}
	public OutputBody add(OutputStatement statement){
		if(statement==null){
			return this;
		}
		outputs.add(statement);
		if(statement.hasVariable()){
			addVariable(statement.getVariable());
		}
		return this;
	}
	public OutputBody add(LineableOutput output){
		if(output==null){
			return this;
		}
		outputs.add(output);
		output.setParent(this);
		return this;
	}
	public OutputBody add(Output statement){
		if(statement==null){
			return this;
		}
		outputs.add(new OutputStatement().set(statement));
		return this;
	}
	public void getImports(Set<String> imports) {
		this.outputs.parallelStream().forEach(O->O.getImports(imports));
	}
	@Override
	public OutputLine line() {
		OutputLine line = new OutputLine();
		line.each(outputs);
		return line;
	}
	@Override
	public Output stasis() {
		return new OutputStasis().name("OutputBody").addAll("add", outputs);
	}
	@Override
	public boolean verify(OutputContext context) {
		return this.outputs.parallelStream().allMatch(O->O.verify(OutputBody.this));
	}

}
