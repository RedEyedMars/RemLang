package com.rem.output.helpers;

import java.util.Set;
import java.util.function.Consumer;

public class OutputMethod extends LineableOutput {

	private String access = "public ";
	private boolean isStatic = false;
	private boolean isAbstract = false;
	private boolean isFinal = false;
	private Outputable header = new Outputable(){
		@Override
		public void output(Consumer<String> builder) {
			builder.accept(access);
			if(isStatic)builder.accept("static ");
			if(isAbstract)builder.accept("abstract ");
			if(isFinal)builder.accept("final ");
		}
		
	};
	private OutputType type;
	private Output name;
	private OutputParameters parameters = new OutputParameters();
	private Output parametersAsVariable = null;
	private OutputBody body = null;

	public void getImports(Set<String> imports) {
		type.getImports(imports);
		parameters.getImports(imports);
		if(body!=null)body.getImports(imports);
	}

	@Override
	public OutputLine line() {

		OutputLine line = new OutputLine();
		if(body!=null){
			line.variable(header).variable(type).exact(" ").variable(name).variable(parameters).exact("{").tabNextLine()
				              .connect(body.line()).untabNextLine()
				              .exact("}");
		}
		else {
			line.variable(header).variable(type).variable(name).variable(parameters).exact(";");
		}
		return line;
	}

	public OutputMethod set(OutputType type, Output name){
		this.type = type;
		this.name = name;
		return this;
	}
	public OutputMethod parameters(OutputParameters parameters){
		this.parameters = parameters;
		if(body!=null)body.addVariables(parameters.getVariables());
		return this;
	}
	public OutputMethod parametersAsVariable(Output parameters){
		this.parametersAsVariable = parameters;
		return this;
	}
	public OutputMethod isPublic(){
		this.access = "public ";
		return this;
	}
	public OutputMethod isProtected(){
		this.access = "protected ";
		return this;
	}
	public OutputMethod isPrivate(){
		this.access = "private ";
		return this;
	}
	public OutputMethod access(String access){
		this.access = access;
		return this;
	}
	public OutputMethod isFinal(){
		this.isFinal = true;
		return this;
	}
	public OutputMethod isAbstract(){
		this.isAbstract = true;
		return this;
	}
	public OutputMethod isStatic(){
		this.isStatic = true;
		return this;
	}
	public OutputMethod type(String string) {
		type = new OutputType().set(new OutputExact(string));
		return this;
	}
	
	public OutputMethod type(OutputType type) {
		this.type = type;
		return this;
	}
	public OutputMethod name(String string) {
		name = new OutputExact(string);
		return this;
	}
	
	public OutputMethod name(OutputType name) {
		this.name = name;
		return this;
	}
	public OutputMethod body(OutputBody newBody){
		this.body = newBody;
		this.body.addVariables(parameters.getVariables());
		return this;
	}
	public OutputMethod add(LineableOutput newLine){
		this.body.add(newLine);
		return this;
	}
	public OutputMethod add(OutputBody newLines){
		this.body.addAll(newLines);
		return this;
	}
	public Output getName() {
		return name;
	}
	public OutputType getType() {
		return type;
	}


	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputMethod").add("access","\""+access+"\"");
		if(isStatic)stasis = stasis.add("isStatic");
		if(isAbstract)stasis = stasis.add("isAbstract");
		if(isFinal)stasis = stasis.add("isFinal");
		stasis = stasis.add("set",type,name);
		if(parametersAsVariable==null)stasis = stasis.add("parameters",parameters);
		else stasis = stasis.asIs("parameters",parametersAsVariable);
		if(body != null){
			stasis = stasis.add("body",body);
		}
		return stasis;
	}

	@Override
	public boolean verify(OutputContext context) {
		return type.verify(context)&&parameters.verify(context)&&(body==null||body.verify(context));
	}

	@Override
	public OutputContext setParent(OutputContext context) {
		if(body!=null)this.body.setParent(context);
		return this.body;
	}

}
