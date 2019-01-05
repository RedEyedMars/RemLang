package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OutputBody extends OutputContext {

	private List<LineableOutput> outputs = new ArrayList<>();
	public OutputBody(){
	}
	public OutputBody(OutputBody other){
		addAll(other);
	}
	@Override
	public OutputBody setParent(OutputContext newParentContext){
		super.setParent(newParentContext);
		return this;
	}
	public OutputBody addAll(OutputBody body){
		if(body==null){
			return this;
		}
		this.outputs.addAll(body.outputs);
		return this;
	}
	public OutputBody add(OutputBody body){
		if(body==null){
			return this;
		}
		this.outputs.addAll(body.outputs);
		this.variables.addAll(body.variables);
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
		if(output instanceof OutputBody){
			return add((OutputBody)output);
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
	public OutputBody prepend(OutputBody body){
		if(body==null){
			return this;
		}
		this.outputs.addAll(0,body.outputs);
		return this;
	}
	public OutputBody prepend(OutputVariable variable){
		if(variable==null){
			return this;
		}
		outputs.add(0,new OutputStatement().set(variable));
		addVariable(variable);
		return this;
	}
	public OutputBody prepend(OutputStatement statement){
		if(statement==null){
			return this;
		}
		outputs.add(0,statement);
		if(statement.hasVariable()){
			addVariable(statement.getVariable());
		}
		return this;
	}
	public OutputBody prepend(LineableOutput output){
		if(output==null){
			return this;
		}
		else if(output instanceof OutputBody){
			return prepend((OutputBody)output);
		}
		else {
			outputs.add(0,output);
			output.setParent(this);
			return this;
		}
	}
	public OutputBody prepend(Output statement){
		if(statement==null){
			return this;
		}
		outputs.add(0,new OutputStatement().set(statement));
		return this;
	}
	public OutputBody append(OutputBody body){
		if(body==null){
			return this;
		}
		this.outputs.addAll(body.outputs);
		return this;
	}
	public OutputBody append(OutputVariable variable){
		if(variable==null){
			return this;
		}
		outputs.add(new OutputStatement().set(variable));
		addVariable(variable);
		return this;
	}
	public OutputBody append(OutputStatement statement){
		if(statement==null){
			return this;
		}
		outputs.add(statement);
		if(statement.hasVariable()){
			addVariable(statement.getVariable());
		}
		return this;
	}
	public OutputBody append(LineableOutput output){
		if(output==null){
			return this;
		}
		if(output instanceof OutputBody){
			return add((OutputBody)output);
		}
		outputs.add(output);
		output.setParent(this);
		return this;
	}
	public OutputBody append(Output statement){
		if(statement==null){
			return this;
		}
		outputs.add(new OutputStatement().set(statement));
		return this;
	}
	@Override
	public Stream<? extends Importable> flatStream(){
		return outputs.stream().flatMap(Flattenable::flatStream);
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
