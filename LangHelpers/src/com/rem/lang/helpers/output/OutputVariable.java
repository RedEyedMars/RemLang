package com.rem.lang.helpers.output;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputVariable extends Output {

	private boolean isPrivate = false;
	private boolean isProtected = true;
	private boolean isPublic = false;
	private boolean isStatic = false;
	private boolean isFinal = false;
	private boolean isClassDeclaration = false;
	private boolean isEnum = false;
	private Outputable header = new Outputable(){
		@Override
		public void output(Consumer<String> builder) {
			if(isClassDeclaration){
				if(isPublic)builder.accept("public ");
				else if(isProtected)builder.accept("protected ");
				else if(isPrivate)builder.accept("private ");
				if(isStatic)builder.accept("static ");
			}
			if(isFinal)builder.accept("final ");
		}

	};
	private OutputType type;
	private Output name;
	private Output assignment = null;
	private OutputMethod getMethod;
	private OutputMethod setMethod;
	public OutputVariable(){}
	public OutputVariable(OutputType type, Output name){
		set(type,name);
	}
	public Stream<? extends Importable> flatStream(){
		return assignment==null?type.flatStream():Stream.concat(type.flatStream(), assignment.flatStream());
	}
	public OutputVariable isPublic(){
		this.isPublic = true;
		this.isProtected = false;
		this.isPrivate = false;
		return this;
	}
	public OutputVariable isProtected(){
		if(this.isPublic){
			if(getMethod!=null)getMethod.display(true);
			if(setMethod!=null)setMethod.display(true);
		}
		this.isPublic = false;
		this.isProtected = true;
		this.isPrivate = false;
		return this;
	}
	public OutputVariable isPrivate(){
		this.isPublic = false;
		this.isProtected = false;
		this.isPrivate = true;
		return this;
	}
	public OutputVariable isFinal(){
		if(!this.isFinal&&setMethod!=null){
			setMethod.display(false);
		}
		this.isFinal = true;
		return this;
	}
	public OutputVariable isStatic(){
		if(!this.isStatic){
			if(getMethod!=null)getMethod.display(false);
			if(setMethod!=null)setMethod.display(false);
		}
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
		if(!isEnum){
			header.output(builder);
			type.add(builder);
		}
		builder.accept(" ");
		name.add(builder);
		if(assignment!=null){
			builder.accept(" = ");
			assignment.add(builder);
		}

	}
	public Output asParameter() {
		return new Output(){
			@Override
			public void output(Consumer<String> builder) {
				type.add(builder);
				builder.accept(" ");
				name.add(builder);
			}

			@Override
			public Output stasis() {
				return new OutputCall().add(OutputVariable.this.stasis()).add(new OutputExact("asParameter"), new OutputArguments());
			}

			@Override
			public boolean verify(OutputContext context) {
				return type.verify(context);
			}

			@Override
			public Stream<? extends Importable> flatStream() {
				return OutputVariable.this.flatStream();
			}};
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
		if(isPrivate)stasis = stasis.add("isPrivate");
		if(isPublic)stasis = stasis.add("isPublic");
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
		return OutputClassStructure.classMap.containsKey(type.evaluate())&&(assignment==null||assignment.verify(context));
	}
	public void isEnum() {
		this.isEnum = true;
	}
	public boolean getIsStatic() {
		return isStatic;
	}
	public boolean getIsPublic() {
		return isPublic;
	}
	public boolean getIsFinal(){
		return isFinal;
	}
	public OutputVariable access(String access){
		if("private ".equals(access)){
			return isPrivate();
		}
		else if("public ".equals(access)){
			return isPublic();
		}
		else {
			return isProtected();
		}
	}
	public void setGetMethod(OutputMethod getMethod) {
		this.getMethod = getMethod;
	}
	public void setSetMethod(OutputMethod getMethod) {
		this.setMethod = setMethod;
	}
}
