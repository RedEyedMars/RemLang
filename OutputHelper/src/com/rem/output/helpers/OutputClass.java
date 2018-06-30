package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class OutputClass extends OutputContext {

	
	private Output packageName;

	private Output name;
	private Output fullName;
	
	private boolean isPublic = true;
	private boolean isFinal = false;
	private boolean isAbstract = false;
	private boolean isStatic = false;
	private String classType = "class ";
	private Outputable header = new Outputable(){
		@Override
		public void output(Consumer<String> builder) {
			builder.accept(isPublic?"public ":"private ");
			builder.accept(isAbstract?"abstract ":"");
			builder.accept(isStatic?"static ":"");
			builder.accept(isFinal?"final ":"");
			builder.accept(classType);
		}
	};
	

	private OutputClass enclosingClass;
	private List<Output> templateTypes = new ArrayList<Output>();
	private OutputType extendType = null;
	private List<OutputType> implementsTypes = new ArrayList<OutputType>();
	
	private Map<String,OutputClass> enclosedClassMap = new HashMap<String,OutputClass>();
	

	private List<OutputClass> enclosedClasses = new ArrayList<OutputClass>();
	public Output getPackageName() {
		return packageName;
	}
	public void solidifyImports(Output rootPackageName, Map<String, Output> packages) {
		if(fullName!=null){
		packages.put(fullName.evaluate(), rootPackageName);
		enclosedClasses.parallelStream().forEach(C->C.solidifyImports(rootPackageName, packages));
		}
	}
	
	public List<Output> getImports(){
		final Set<String> imports = Collections.synchronizedSet(new HashSet<String>());
		getImports(imports);
		return imports.stream().map(P->OutputHelper.packages.containsKey(P)?(OutputCall)new OutputCall().add(OutputHelper.packages.get(P)).add(new OutputExact(P)):null).filter(P->P!=null).collect(Collectors.toList());
	}
	public void getImports(Set<String> imports){

		if(extendType!=null)extendType.getImports(imports);
		implementsTypes.parallelStream().forEach(I->I.getImports(imports));
		variables.parallelStream().forEach(V->V.getImports(imports));
		methods.parallelStream().forEach(M->M.getImports(imports));
		enclosedClasses.parallelStream().forEach(C->C.getImports(imports));
	}
	@Override
	public OutputLine line() {
		OutputLine line = new OutputLine();
		line.variable(header).variable(name).variablesIfPresent(" <",templateTypes,">")
				                                              .variableIfPresent(" extends ",extendType,"")
				                                              .variablesIfPresent(" implements ", implementsTypes, "").exact(" {").tabNextLine()
				                                              	.all("", variables, ";").nextLine()
				                                              	.each(methods).nextLine()
				                                              	.each(enclosedClasses).untabNextLine()
				                                              .exact("}");
		return line;
	}

	
	public OutputClass _package(Output packageName){
		this.packageName = packageName;
		return this;
	}
	public OutputClass _package(String packageName){
		this.packageName = new OutputExact(packageName);
		return this;
	}
	public OutputClass isStatic(){
		this.isStatic = true;
		return this;
	}
	public OutputClass isAbstract(){
		this.isAbstract = true;
		return this;
	}

	public OutputClass isClass() {
		this.classType = "class ";
		return this;
	}
	public OutputClass isInterface() {
		this.classType = "interface ";
		return this;
	}
	public OutputClass isEnum() {
		this.classType = "enum ";
		return this;
	}
	public OutputClass classType(String classType) {
		this.classType = classType;
		return this;
	}
	public OutputClass template(String string) {
		if(this.templateTypes==null){
			this.templateTypes = new ArrayList<Output>();
		}
		this.templateTypes.add(new OutputExact(string));
		return this;
	}
	public OutputClass template(Output string) {
		if(this.templateTypes==null){
			this.templateTypes = new ArrayList<Output>();
		}
		this.templateTypes.add(string);
		return this;
	}
	public void addVariable(OutputVariable variable) {
		this.variable(variable);
	}
	public OutputClass variable(OutputVariable variable) {
		this.variableMap.put(variable.getName().evaluate(),variable);
		this.variables.add(variable.isClassDeclaration());
		return this;
	}
	public OutputClass method(OutputMethod method) {
		this.methodMap.put(method.getName().evaluate(),method);
		this.methods.add(method);
		return this;
	}
	public OutputClass encloseClass(OutputClass enclosedClass) {
		this.enclosedClassMap.put(enclosedClass.getName().evaluate(),enclosedClass);
		this.enclosedClasses.add(enclosedClass);
		enclosedClass.setEnclosingClass(this);
		
		return this;
	}
	private void setEnclosingClass(OutputClass enclosingClass){
		this.enclosingClass = enclosingClass;
		name(name);
	}

	public OutputClass name(String className) {
		this.name = new OutputExact(className);
		if(enclosingClass!=null){
			this.fullName = new OutputType().set(enclosingClass.fullName,new OutputExact(className));
		}
		else {
			this.fullName = new OutputType().set(new OutputExact(className));
		}
		return this;
	}
	public OutputClass name(Output name){
		if(fullName!=null&&OutputHelper.classMap.containsKey(fullName.evaluate())){
			if(OutputHelper.waitingClassMap.containsKey(fullName.evaluate())&&!OutputHelper.waitingClassMap.get(fullName.evaluate()).isEmpty()){
				OutputHelper.classMap.put(fullName.evaluate(),OutputHelper.waitingClassMap.get(fullName.evaluate()).remove(0));
			}
			else {
				OutputHelper.classMap.remove(fullName.evaluate());
			}
		}
		this.name = name;
		if(enclosingClass!=null){
			this.fullName = new OutputType().set(enclosingClass.fullName,name);
		}
		else {
			this.fullName = new OutputType().set(name);
		}
		if(!OutputHelper.classMap.containsKey(fullName.evaluate())){
			OutputHelper.classMap.put(fullName.evaluate(),this);
		}
		else {
			if(!OutputHelper.waitingClassMap.containsKey(fullName.evaluate())){
				OutputHelper.waitingClassMap.put(fullName.evaluate(),Collections.synchronizedList(new ArrayList<OutputClass>()));
			}
			OutputHelper.waitingClassMap.get(fullName.evaluate()).add(this);
		}
		return this;
	}
	public OutputClass extendType(OutputType extendType){
		this.extendType = extendType;
		return this;
	}
	public OutputClass implement(OutputType interfaceType){
		this.implementsTypes.add(interfaceType);
		return this;
	}
	

	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputClass");
		stasis = stasis.add("_package",packageName);
		if(!isPublic)stasis = stasis.add("isPrivate");
		if(isStatic)stasis = stasis.add("isFinal");
		if(isFinal)stasis = stasis.add("isFinal");
		if(isAbstract)stasis = stasis.add("isAbstract");
		stasis = stasis.add("classType","\""+classType+"\"");
		stasis = stasis.add("name",name);
		stasis = stasis.addAll("template",templateTypes);
		stasis = stasis.add("extendType",extendType);
		stasis = stasis.addAll("implement",implementsTypes);
		stasis = stasis.addAll("variable",variables);
		stasis = stasis.addAll("method",methods);
		stasis = stasis.addAll("encloseClass",enclosedClasses);
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return variables.parallelStream().allMatch(V->V.verify(OutputClass.this))&&
			   methods.parallelStream().allMatch(M->M.verify(OutputClass.this))&&
			   enclosedClasses.parallelStream().allMatch(E->E.verify(OutputClass.this));
	}
	
	
	public Output getName(){
		return name;
	}
	public Output getFullName(){
		return fullName;
	}
	public OutputVariable getVariable(String variableName){
		return variableMap.get(variableName);
	}
	public OutputMethod getMethod(String methodName){
		return methodMap.get(methodName);
	}
	public OutputClass getEnclosedClass(String enclosedClassName){
		return enclosedClassMap.get(enclosedClassName);
	}
	public boolean hasVariable(String variableName) {
		return variableMap.containsKey(variableName);
	}
	public boolean hasMethod(String methodName) {
		return methodMap.containsKey(methodName);
	}
}
