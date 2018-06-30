package com.rem.output.helpers;

import java.util.Set;

public class OutputConditional extends LineableOutput {
	private String conditional;
	private OutputConditionalHeader header = null;
	private OutputBody bodyAsBody;
	private Output bodyAsVariable;
	private OutputContext parentContext = null;

	public OutputConditional(){
	}
	public OutputConditional(String conditional){
		init(conditional);
	}
	public OutputConditional init(String conditional){
		this.conditional = conditional;
		if(header!=null&&this.bodyAsBody!=null&&header.getDeclaredVariable()!=null){
			this.bodyAsBody.addVariable(header.getDeclaredVariable());
		}
		if(parentContext!=null){
			this.bodyAsBody.setParent(parentContext);
		}
		return this;
	}
	public OutputConditional body(OutputBody body){
		this.bodyAsBody = body;
		if(header!=null&&this.bodyAsBody!=null&&header.getDeclaredVariable()!=null){
			this.bodyAsBody.addVariable(header.getDeclaredVariable());
		}
		if(parentContext!=null){
			this.bodyAsBody.setParent(parentContext);
		}
		return this;
	}
	public OutputConditional body(Output body){
		this.bodyAsVariable = body;
		return this;
	}
	public OutputConditional header(OutputConditionalHeader header){
		this.header = header;
		if(header!=null&&this.bodyAsBody!=null&&header.getDeclaredVariable()!=null){
			this.bodyAsBody.addVariable(header.getDeclaredVariable());
		}
		return this;
	}
	public OutputConditional add(OutputVariable variable){
		bodyAsBody.add(variable);
		return this;
	}
	public OutputConditional add(OutputStatement statement){
		bodyAsBody.add(statement);
		return this;
	}
	public OutputConditional add(LineableOutput output){
		bodyAsBody.add(output);
		return this;
	}
	public OutputConditional add(Output statement){
		bodyAsBody.add(statement);
		return this;
	}

	public void getImports(Set<String> imports) {
		if(header != null){
			header.getImports(imports);
		}
		if(bodyAsBody!=null){
			bodyAsBody.getImports(imports);
		}
	}

	@Override
	public OutputLine line() {
		OutputLine line = new OutputLine();
		if(header!=null){
			if(bodyAsBody!=null){
				line.exact(conditional).variable(header).exact("{").tabNextLine()
					.connect(bodyAsBody.line()).untabNextLine()
					.exact("}");
			}
			else {
				line.exact(conditional).variable(header).exact("{").exact("}");
			}
		}
		else {
			if(bodyAsBody!=null){
				line.exact(conditional).exact("{").tabNextLine()
					.connect(bodyAsBody.line()).untabNextLine()
					.exact("}");
			}
			else {
				line.exact(conditional).exact("{").exact("}");
			}
		}
		return line;
	}

	@Override
	public Output stasis(){

		OutputStasis stasis = new OutputStasis().name("OutputConditional").add("init",conditional);
		if(bodyAsBody!=null){
			stasis = stasis.add("body",bodyAsBody);
		}
		else if(bodyAsVariable!=null){
			stasis = stasis.add("body",bodyAsVariable.evaluate());
		}
		else {
			stasis = stasis.add("body",new OutputBody());
		}
		if(header!=null){
			stasis.add("header",header);
		}
		return this;
	}
	@Override
	public boolean verify(OutputContext context) {
		return (header==null||header.verify(context))&&(bodyAsBody==null||bodyAsBody.verify(context));
	}
	@Override
	public OutputContext setParent(OutputContext context) {
		this.parentContext = context;
		if(bodyAsBody!=null){
			bodyAsBody.setParent(context);
		}
		return bodyAsBody;
	}


	public OutputBody body(){
		return bodyAsBody;
	}
}
