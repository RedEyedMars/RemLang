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
import java.util.stream.Stream;


public class OutputClass extends OutputContext {


	private Output packageName;

	private Output name;
	private Output fullName;

	private boolean isPublic = true;
	private boolean isFinal = false;
	private boolean isAbstract = false;
	private boolean isStatic = true;
	private Outputable header = new Outputable(){
		@Override
		public void output(Consumer<String> builder) {
			builder.accept(isPublic?"public ":"private ");
			builder.accept(isAbstract?"abstract ":"");
			builder.accept(isStatic&&enclosingClass!=null?"static ":"");
			builder.accept(isFinal?"final ":"");
			builder.accept(isInterface?"interface ":isEnum?"enum ":"class ");
		}
	};


	private OutputClass enclosingClass;
	private List<Output> templateTypes = new ArrayList<Output>();
	private OutputType extendType = null;
	private List<OutputType> implementsTypes = new ArrayList<OutputType>();

	private Map<String,OutputClass> enclosedClassMap = new HashMap<String,OutputClass>();


	private List<OutputClass> enclosedClasses = new ArrayList<OutputClass>();

	private boolean isInterface;

	private boolean isEnum;
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
		flatStream().parallel().forEach(
				S->S.getImports(imports::add));
		return imports.stream().map(P->OutputHelper.packages.containsKey(P)?(OutputCall)new OutputCall().add(OutputHelper.packages.get(P)).add(new OutputExact(P)):null/*new OutputExact("Unable to find:"+P)*/).filter(P->P!=null).collect(Collectors.toList());
	}

	@Override
	public Stream<? extends Importable> flatStream(){

		return extendType!=null?
				     Stream.of(extendType.flatStream(),
			             implementsTypes.stream().flatMap(Flattenable::flatStream),
			             variables.stream().flatMap(Flattenable::flatStream), 
			             methods.stream().flatMap(Flattenable::flatStream),
			              enclosedClasses.stream().flatMap(Flattenable::flatStream)).flatMap(S->S)
				:
					Stream.of(implementsTypes.stream().flatMap(Flattenable::flatStream),
				             variables.stream().flatMap(Flattenable::flatStream), 
				             methods.stream().flatMap(Flattenable::flatStream),
				              enclosedClasses.stream().flatMap(Flattenable::flatStream)).flatMap(S->S);
	}
	@Override
	public OutputLine line() {
		OutputLine line = new OutputLine();
		if(!isEnum){
			line.variable(header).variable(name).variablesIfPresent(" <",templateTypes,">")
				.variableIfPresent(" extends ",extendType,"")
				.variablesIfPresent(" implements ", implementsTypes, "").exact(" {").tabNextLine()
				.all("", variables, ";").nextLine()
				.indexedByLambda(variables.size(),(L,I)->{
					if(variables.get(I).getIsStatic()||variables.get(I).getIsPublic())return L;
					String methodName = "get"+OutputHelper.camelize(variables.get(I).getName().evaluate());
					if(!methodMap.containsKey(methodName+"()")){
						return L.connect(new OutputMethod()
								.set(variables.get(I).getType(), new OutputExact(methodName)).parameters(new OutputParameters()).body(
								new OutputBody().add(new OutputStatement().prefix("return ").set(variables.get(I).getName())))
								.line());
					}
					else return L;})
				.each(methods).nextLine()
				.each(enclosedClasses).untabNextLine()
			.exact("}");
		}
		else
			line.variable(header).variable(name).exact(" {").tabNextLine()
				.indexedByLambda(variables.size(),(L,I)->{L.variable(variables.get(I));if(I!=variables.size()-1)L.exact(",");return L.nextLine();}).untabNextLine()
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
	public OutputClass isNonStatic(){
		this.isStatic = false;
		return this;
	}
	public OutputClass isAbstract(){
		this.isAbstract = true;
		return this;
	}

	public OutputClass classType(String newClassType){
		if(newClassType.equals("interface ")){
			return isInterface();
		}
		else if(newClassType.equals("enum ")) {
			return isEnum();
		}
		else {
			return isClass();
		}
	}
	public OutputClass isClass() {
		this.isInterface = false;
		this.isEnum = false;
		return this;
	}
	public OutputClass isInterface() {
		this.isInterface = true;
		this.isEnum = false;
		return this;
	}
	public OutputClass isEnum() {
		this.isInterface = false;
		this.isEnum = true;
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
	@Override
	public void addVariable(OutputVariable variable) {
		this.variable(variable);
	}
	public OutputClass variable(OutputVariable variable) {
		this.variableMap.put(variable.getName().evaluate(),variable);
		this.variables.add(variable.isClassDeclaration());
		if(isEnum){
			variable.isEnum();
		}
		return this;
	}
	@Override
	public void addMethod(OutputMethod variable) {
		this.method(variable);
	}
	public OutputClass method(OutputMethod method) {
		this.methodMap.put(method.getSignature(),method);
		this.simpleMethodMap.put(method.getName().evaluate(),method);
		this.methods.add(method);
		if(isInterface){
			method.isInterface();
		}
		return this;
	}
	public OutputClass encloseClass(OutputClass enclosedClass) {
		this.enclosedClassMap.put(enclosedClass.getFullName().evaluate(),enclosedClass);
		this.enclosedClasses.add(enclosedClass);
		enclosedClass.setEnclosingClass(this);

		return this;
	}
	protected void setEnclosingClass(OutputClass enclosingClass){
		this.enclosingClass = enclosingClass;
		name(name);
	}
	public OutputClass getEnclosedClassFromHeirachy(String className){
		if(name.evaluate().equals(className)){
			return this;
		}
		else {
			return enclosedClasses.parallelStream().map(C->C.getEnclosedClassFromHeirachy(className)).filter(C->C!=null).findAny().orElse(null);
		}
	}

	public OutputClass name(String className) {
		this.name = new OutputExact(className);
		if(enclosingClass!=null){
			this.fullName = new OutputType(enclosingClass.fullName).add(new OutputExact(className));
		}
		else {
			this.fullName = new OutputType(new OutputExact(className));
		}
		return this;
	}
	public OutputClass name(Output name){
		if(fullName!=null&&OutputHelper.classMap.containsKey(getFullName().evaluate())){
			if(OutputHelper.waitingClassMap.containsKey(getFullName().evaluate())&&!OutputHelper.waitingClassMap.get(getFullName().evaluate()).isEmpty()){
				OutputHelper.classMap.put(getFullName().evaluate(),OutputHelper.waitingClassMap.get(fullName.evaluate()).remove(0));
			}
			else {
				OutputHelper.classMap.remove(getFullName().evaluate());
			}
		}
		this.name = name;
		if(enclosingClass!=null){
			this.fullName = new OutputType(enclosingClass.getFullName()).add(name);
		}
		else {
			this.fullName = new OutputType(name);
		}
		if(!OutputHelper.classMap.containsKey(getFullName().evaluate())){
			OutputHelper.classMap.put(getFullName().evaluate(),this);
		}
		else {
			if(!OutputHelper.waitingClassMap.containsKey(getFullName().evaluate())){
				OutputHelper.waitingClassMap.put(getFullName().evaluate(),Collections.synchronizedList(new ArrayList<OutputClass>()));
			}
			OutputHelper.waitingClassMap.get(getFullName().evaluate()).add(this);
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
		if(isStatic&&enclosingClass!=null)stasis = stasis.add("isStatic");
		else if(!isStatic)stasis = stasis.add("isNonStatic");
		if(isFinal)stasis = stasis.add("isFinal");
		if(isAbstract)stasis = stasis.add("isAbstract");
		if(isInterface)stasis = stasis.add("isInterface");
		if(isEnum)stasis = stasis.add("isEnum");
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
	
	public Set<String> getMethodNames(){
		return methodMap.keySet();
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
		OutputMethod method = simpleMethodMap.get(methodName);
		if(method!=null)return method;
		method = methodMap.get(methodName);
		return method;
	}
	public OutputClass getEnclosedClass(String enclosedClassName){
		return enclosedClassMap.get(enclosedClassName);
	}
	public OutputClass getEnclosedClass(Output enclosedClass){
		return enclosedClassMap.get(enclosedClass.evaluate());
	}
	public boolean hasVariable(String variableName) {
		return variableMap.containsKey(variableName);
	}
	public boolean hasMethod(String methodName) {
		return methodMap.containsKey(methodName);
	}
	public OutputClass getAsVariable(){
		return new OutputClassVariable(this);
	}
	public OutputClass mark(){
		return this;
	}

	public static class OutputClassVariable extends OutputClass {
		private OutputClass parent;
		public OutputClassVariable(OutputClass parent){
			this.parent = parent;
		}
		public Output getPackageName() {
			return parent.packageName;
		}
		public void solidifyImports(Output rootPackageName, Map<String, Output> packages) {
			parent.solidifyImports(rootPackageName, packages);
		}

		public List<Output> getImports(){
			return parent.getImports();
		}
		public Stream<? extends Importable> flatStream(){
			return parent.flatStream();
		}
		@Override
		public OutputLine line() {
			return parent.line();
		}


		public OutputClass _package(Output packageName){
			return parent._package(packageName);
		}
		public OutputClass _package(String packageName){
			return parent._package(packageName);
		}
		public OutputClass isStatic(){
			return parent.isStatic();
		}
		public OutputClass isNonStatic(){
			return parent.isNonStatic();
		}
		public OutputClass isAbstract(){
			return parent.isAbstract();
		}
		public OutputClass isClass() {
			return parent.isClass();
		}
		public OutputClass isInterface() {
			return parent.isInterface();
		}
		public OutputClass isEnum() {
			return parent.isEnum();
		}
		public OutputClass template(String string) {
			return parent.template(string);
		}
		public OutputClass template(Output string) {
			return parent.template(string);
		}
		public void addVariable(OutputVariable variable) {
			parent.variable(variable);
		}
		public OutputClass variable(OutputVariable variable) {
			return parent.variable(variable);
		}
		public OutputClass method(OutputMethod method) {
			return parent.method(method);
		}
		public OutputClass encloseClass(OutputClass enclosedClass) {
			return parent.encloseClass(enclosedClass);
		}

		public OutputClass name(String className) {
			return parent.name(className);
		}
		public OutputClass name(Output className){
			return parent.name(className);
		}
		public OutputClass extendType(OutputType extendType){
			return parent.extendType(extendType);
		}
		public OutputClass implement(OutputType interfaceType){
			return parent.implement(interfaceType);
		}
		@Override
		public Output stasis() {
			return new OutputStaticCall().set(new OutputType(parent.getFullName())).add(new OutputExact("OUTPUT"));
		}
		@Override
		public boolean verify(OutputContext context) {
			return parent.verify(context);
		}
		public Output getName(){
			return parent.getName();
		}
		public Output getFullName(){
			return parent.getFullName();
		}
		public OutputVariable getVariable(String variableName){
			return parent.getVariable(variableName);
		}
		public OutputMethod getMethod(String methodName){
			return parent.getMethod(methodName);
		}
		public OutputClass getEnclosedClass(String enclosedClassName){
			return parent.getEnclosedClass(enclosedClassName);
		}
		public boolean hasVariable(String variableName) {
			return parent.hasVariable(variableName);
		}
		public boolean hasMethod(String methodName) {
			return parent.hasVariable(methodName);
		}

		public OutputContext setParent(OutputContext newParentContext){
			return parent.setParent(newParentContext);
		}
		public Boolean hasVariableInContext(String query){
			return parent.hasVariableInContext(query);
		}
		public void addVariables(List<OutputVariable> variables) {
			parent.addVariables(variables);
		}
		public OutputVariable getVariableFromContext(String query){
			return parent.getVariableFromContext(query);
		}
		public OutputClass getVariableClassFromContext(String query){
			return parent.getVariableClassFromContext(query);
		}

		public Boolean hasMethodInContext(String query){
			return parent.hasMethodInContext(query);
		}
		public void addMethods(List<OutputMethod> methods) {
			parent.addMethods(methods);
		}
		public OutputMethod getMethodFromContext(String query){
			return parent.getMethodFromContext(query);
		}
		public OutputClass getMethodClassFromContext(String query){
			return parent.getMethodClassFromContext(query);
		}

		protected void setEnclosingClass(OutputClass enclosingClass){
			parent.setEnclosingClass(enclosingClass);
		}
	}
}
