package com.rem.output.helpers;

import java.util.Set;
import java.util.function.Consumer;

public class OutputVariable extends Output {

	private String access = "private ";
	private boolean isStatic = false;
	private boolean isFinal = false;
	private boolean isClassDeclaration = false;
	private Outputable header = new Outputable(){
		@Override
		public void output(Consumer<String> builder) {
			if(isClassDeclaration){
				builder.accept(access);
				if(isStatic)builder.accept("static ");
			}
			if(isFinal)builder.accept("final ");
		}
		
	};
	private OutputType type;
	private Output name;
	private Output assignment = null;
	public OutputVariable(){}
	public OutputVariable(OutputType type, Output name){
		set(type,name);
	}
	public void getImports(Set<String> imports) {
		type.getImports(imports);
		if(assignment!=null){
			assignment.getImports(imports);
		}
	}
	public OutputVariable isPublic(){
		this.access = "public ";
		return this;
	}
	public OutputVariable isProtected(){
		this.access = "protected ";
		return this;
	}
	public OutputVariable isPrivate(){
		this.access = "private ";
		return this;
	}
	public OutputVariable access(String access){
		this.access = access;
		return this;
	}
	public OutputVariable isFinal(){
		this.isFinal = true;
		return this;
	}
	public OutputVariable isStatic(){
		this.isStatic = true;
		return this;
	}
	public OutputVariable isClassDeclaration(){
		this.isClassDeclaration = true;
		return this;
	}
	public OutputVariable set(OutputType type, Output name){
		this.type = type;
		this.name = name;
		return this;
	}
	public OutputVariable set(OutputType type, String name){
		this.type = type;
		this.name = new OutputExact(name);
		return this;
	}
	public OutputVariable assign(Output assignment){
		this.assignment = assignment;
		return this;
	}
	public void output(Consumer<String> builder) {
		header.output(builder);
		type.add(builder);
		builder.accept(" ");
		name.add(builder);
		if(assignment!=null){
			builder.accept(" = ");
			assignment.add(builder);
		}
		
	}
	public Output getName() {
		return name;
	}
	public OutputType getType() {
		return type;
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputVariable");
		if(!"private ".equals(access))stasis = stasis.add("access","\""+access+"\"");
		if(isStatic)stasis = stasis.add("isStatic");
		if(isFinal)stasis = stasis.add("isFinal");
		stasis = stasis.add("set",type,name);
		if(assignment != null){
			stasis = stasis.add("assign",assignment);
		}
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return OutputHelper.classMap.containsKey(type.evaluate())&&(assignment==null||assignment.verify(context));
	}

}
