package com.rem.output.helpers;

import java.util.stream.Stream;

public class OutputStatement extends LineableOutput {

	private String prefix = "";
	private Output statement;
	private boolean hasVariable = false;
	public OutputStatement set(OutputVariable variable){
		this.statement = variable;
		if(this.statement!=null){
			this.hasVariable = true;
		}
		return this;
	}
	public OutputStatement set(Output statement){
		this.statement = statement;
		return this;
	}
	public OutputStatement prefix(String prefix){
		this.prefix = prefix;
		return this;
	}
	public OutputStatement prefix(Output prefix){
		this.prefix = prefix.evaluate();
		return this;
	}
	@Override
	public OutputLine line() {
		return new OutputLine().exact(prefix).variable(statement).exact(";");
	}

	public Stream<? extends Importable> flatStream(){
		return statement!=null?statement.flatStream():Stream.empty();
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputStatement");
		if(prefix!=null&&!prefix.equals("")){
			stasis = stasis.add("prefix","\""+prefix+"\"");
		}
		if(statement!=null){
			stasis = stasis.add("set",statement);
		}
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return statement.verify(context);
	}
	@Override
	public OutputContext setParent(OutputContext context) {
		return null;
	}
	public boolean hasVariable() {
		return hasVariable;
	}
	public OutputVariable getVariable() {
		return (OutputVariable)statement;
	}

}
