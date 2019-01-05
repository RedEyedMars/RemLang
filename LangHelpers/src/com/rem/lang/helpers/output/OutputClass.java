package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class OutputClass extends OutputContext {


	private Output packageName;

	private Output name;
	private OutputType fullName;

	private boolean isPublic = true;
	private boolean isFinal = false;
	private boolean isAbstract = false;
	private boolean isStatic = true;
	private boolean isInterface = false;
	private boolean isEnum = false;
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

	private OutputClass original;

	private boolean nameIsSolid = false;

	private OutputVariable variableThis = new OutputVariable().set(null,new OutputExact("this"));
	public OutputClass(){
		super();
		variables.add(variableThis);
		variableMap.put("this",variableThis);
	}
	public Output getPackageName() {
		return packageName;
	}
	public void solidifyImports(Output rootPackageName, Map<String, Output> packages) {
		if(fullName!=null){
			packages.put(fullName.evaluate(), rootPackageName);
			enclosedClasses.parallelStream().forEach(C->C.solidifyImports(rootPackageName, packages));
		}
	}
	public OutputClass getRoot(){
		OutputClass root = this;
		OutputClass parent = root.getEnclosingClass();
		while(parent!=null&&parent!=root) {
			root = parent;
			parent = root.getEnclosingClass();
		}
		return root;
	}
	public void solidifyNames(){
		classStream().forEach(OutputClass::solidifyName);
	}
	public String printMethodNamesFromContext(){
		return fullName.evaluate().toLowerCase()+super.printMethodNamesFromContext();
	}
	public void solidifyName(){
		if(nameIsSolid)return ;
		if(OutputClassStructure.waitingClassMap.containsKey(getFullName().evaluate())){
			OutputClassStructure.waitingClassMap.remove(getFullName().evaluate()).forEach(C->C.addExtension(OutputClass.this));
		}
		if(OutputClassStructure.classMap.containsKey(getFullName().evaluate())){

			throw new RuntimeException(
					"Duplication of:"+
							getRoot().getPackageName().evaluate()+":"+getFullName().evaluate()+"//"+
							OutputClassStructure.classMap.get(getFullName().evaluate()).getRoot().getPackageName().evaluate()+":"+getFullName().evaluate());
		}
		else {
			OutputClassStructure.classMap.put(getFullName().evaluate(), this);
			nameIsSolid = true;
		}
	}
	public Stream<OutputClass> classStream(){
		return Stream.concat(Stream.of(this), enclosedClasses.stream().flatMap(OutputClass::classStream));
	}

	public List<Output> getImports(){
		final Set<String> imports = Collections.synchronizedSet(new HashSet<String>());
		flatStream().parallel().forEach(
				S->S.getImports(imports::add));
		return imports.stream()
				.filter(P->P!=null)
				.filter(OutputClassStructure.packages::containsKey)
				.filter(P->!OutputClassStructure.packages.get(P).evaluate().equals("java.lang"))
				.map(P->
				(OutputCall)new OutputCall().add(OutputClassStructure.packages.get(P)).add(new OutputExact(P)))
				.collect(Collectors.toList());
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
			if(isInterface){
				methods.forEach(M->M.isInterface());
			}
			line.variable(header).variable(name).variablesIfPresent(" <",templateTypes,", " ,">")
			.variableIfPresent(" extends ",extendType,"")
			.variablesIfPresent(" implements ", implementsTypes, ", ", "").exact(" {").tabNextLine()
			.allExcept("", variables, ";",V->((OutputVariable)V).getName().evaluate().equals("this")).nextLine()
			.eachNonNull(methods).nextLine()
			.each(enclosedClasses).untabNextLine()
			.exact("}");
		}
		else
			line.variable(header).variable(name).exact(" {").tabNextLine()
			.indexedByLambda(variables.size(),
					(L,I)->{
						if(!variables.get(I).getName().evaluate().equals("this")){
							L.variable(variables.get(I));
							if(I!=variables.size()-1)L.exact(",");
							return L.nextLine();
						}
						else {
							return L;
						}
					}).untabNextLine()
			.exact("}");
		return line;
	}

	private static void addAsSuperConstructor(List<OutputVariable> initialArgs,List<OutputParameters> superPars,  List<OutputArguments> superArgs,OutputType extendType){
		if(extendType!=null){
			OutputClass asClass = extendType.getAsClass();
			if(asClass!=null){
				addAsSuperConstructor(initialArgs,superPars,superArgs,asClass.extendType);
				OutputParameters superPar = new OutputParameters();
				OutputArguments superArg = new OutputArguments();
				asClass.variables.stream().filter(V->!V.getName().evaluate().equals("this")).filter(V->!V.getIsStatic()).forEach(V->{
					superArg.add(V.getName());
					superPar.add(new OutputVariable().set(V.getType(), V.getName()));
				});
				superPars.add(superPar);
				superArgs.add(superArg);
			}
			
		}
	}
	public void addConstructors(){
		if(isInterface||isEnum){
			return;
		}
		List<OutputVariable> myPars = new ArrayList<OutputVariable>();
		List<OutputArguments> superArgs = new ArrayList<OutputArguments>();
		List<OutputParameters> superPars = new ArrayList<OutputParameters>();
		superArgs.add(new OutputArguments());
		superPars.add(new OutputParameters());
		variables.stream().filter(V->!"this".equals(V.getName().evaluate())).filter(V->!V.getIsStatic()).forEach(V->myPars.add(V));
		addAsSuperConstructor(myPars,superPars,superArgs,extendType);
		IntStream.range(0, superPars.size()).forEach(Ps->{
			OutputParameters paras = new OutputParameters().add(superPars.get(Ps)).addSafe(myPars);
			if(!methodMap.containsKey(paras.getAsSignature())){
				OutputBody body = new OutputBody();
				body.add(new OutputStatement().set(
						new OutputCall().add(new OutputExact().set("super"),superArgs.get(Ps))));
				myPars.forEach(P->
				body.add(
						new OutputStatement().set(
								new OutputOperator().left(new OutputCall().add(new OutputExact("this")).add(P.getName()))
								.operator("=").right(P.getName()))));
				addMethod(new OutputMethod().set(new OutputType().set(name), new OutputExact().set("")).parameters(paras).body(body));
			}
			paras = new OutputParameters().add(superPars.get(Ps));
			if(!methodMap.containsKey(paras.getAsSignature())){
				OutputBody body = new OutputBody();
				body.add(new OutputStatement().set(
						new OutputCall().add(new OutputExact().set("super"),superArgs.get(Ps))));
				addMethod(new OutputMethod().set(new OutputType().set(name), new OutputExact().set("")).parameters(paras).body(body));
			}
		});
		enclosedClasses.forEach(C->C.addConstructors());
	}
	public String getString(){
		return getFullName().evaluate();
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
	public List<OutputVariable> getVariables(){
		return variables;
	}
	public void addDefaultConstructor(List<OutputVariable> variables){
		final OutputBody body = new OutputBody();
		variables.forEach(V->body.add(new OutputStatement().set(
				new OutputOperator().left(new OutputCall().add(new OutputExact("this")).add(V.getName()))
				.operator("=")
				.right(V.getName()))));
		method(new OutputMethod()
				.set(new OutputType(getName()), new OutputExact("")).parameters(
						new OutputParameters().add(variables)).body(body));
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
		else if(!variable.getIsStatic()&&!variable.getIsPublic()){
			String getMethodName = OutputHelper.asGetMethod(variable.getName().evaluate());
			if(!methodMap.containsKey(getMethodName+"()")){
				OutputMethod getMethod = new OutputMethod()
						.set(variable.getType(), new OutputExact(getMethodName)).parameters(new OutputParameters()).body(
								new OutputBody().add(new OutputStatement().prefix("return ").set(variable.getName())));
				method(getMethod);
				variable.setGetMethod(getMethod);
			}
			if(!variable.getIsFinal()){
				String setMethodName = "set"+OutputHelper.camelize(variable.getName().evaluate());
				String variableName = "new"+OutputHelper.camelize(variable.getName().evaluate());
				if(!methodMap.containsKey(setMethodName+"("+variable.getType().evaluate()+")")){
					OutputMethod setMethod = new OutputMethod()
							.set(new OutputType("void"), new OutputExact(setMethodName)).parameters(new OutputParameters().add(new OutputVariable(variable.getType(),new OutputExact(variableName)))).body(
									new OutputBody().add(new OutputStatement().set(
											new OutputOperator().left(variable.getName()).operator("=").right(new OutputExact(variableName)))));
					method(setMethod);
					variable.setSetMethod(setMethod);
				}
			}
		}
		return this;
	}
	@Override
	public void addMethod(OutputMethod method) {
		this.method(method);
	}
	public OutputClass method(OutputMethod method) {
		String signature = method.getSignature();
		if(methodMap.containsKey(signature)){
			methods.remove(methodMap.get(signature));
		}
		this.methodMap.put(signature,method);
		this.simpleMethodMap.put(method.getName().evaluate(),method);
		this.methods.add(method);
		if(isInterface){
			method.isInterface();
		}
		method.setParent(this);
		return this;
	}
	public OutputClass encloseClass(OutputClass enclosedClass) {
		this.enclosedClassMap.put(enclosedClass.getFullName().evaluate(),enclosedClass);
		this.enclosedClasses.add(enclosedClass);

		enclosedClass.setEnclosingClass(this);

		if(nameIsSolid){
			enclosedClass.solidifyName();
		}
		return this;
	}
	public OutputClass getEnclosingClass(){
		return enclosingClass;
	}
	protected void setEnclosingClass(OutputClass enclosingClass){
		this.enclosingClass = enclosingClass;
		classStream().forEach(OutputClass::updateName);
	}
	protected void updateName(){
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
		variableThis.set(fullName, "this");
		return this;
	}
	public OutputClass name(Output name){
		this.name = name;
		if(enclosingClass!=null){
			this.fullName = new OutputType(enclosingClass.getFullName()).add(name);
		}
		else {
			this.fullName = new OutputType(name);
		}
		variableThis.set(fullName, "this");
		return this;
	}
	/*
	public OutputClass name(Output name){
		if(fullName!=null&&OutputClassStructure.classMap.containsKey(getFullName().evaluate())){
			if(OutputClassStructure.waitingClassMap.containsKey(getFullName().evaluate())&&!OutputClassStructure.waitingClassMap.get(getFullName().evaluate()).isEmpty()){
				OutputClassStructure.classMap.put(getFullName().evaluate(),OutputClassStructure.waitingClassMap.get(fullName.evaluate()).remove(0));
			}
			else {
				OutputClassStructure.classMap.remove(getFullName().evaluate());
			}
		}
		this.name = name;
		if(enclosingClass!=null){
			this.fullName = new OutputType(enclosingClass.getFullName()).add(name);
		}
		else {
			this.fullName = new OutputType(name);
		}
		if(!OutputClassStructure.classMap.containsKey(getFullName().evaluate())){
			OutputClassStructure.classMap.put(getFullName().evaluate(),this);
		}
		else {
			if(!OutputClassStructure.waitingClassMap.containsKey(getFullName().evaluate())){
				OutputClassStructure.waitingClassMap.put(getFullName().evaluate(),Collections.synchronizedList(new ArrayList<OutputClass>()));
			}
			OutputClassStructure.waitingClassMap.get(getFullName().evaluate()).add(this);
		}
		return this;
	}*/
	public OutputClass extendType(OutputType extendType){
		this.extendType = extendType;
		if(extendType!=null){
			OutputClassStructure.addSubClass(extendType.getName(),this);
		}
		return this;
	}
	public OutputClass implement(OutputType interfaceType){
		this.implementsTypes.add(interfaceType);
		if(interfaceType!=null){
			OutputClassStructure.addSubClass(interfaceType.getName(),this);
		}
		return this;
	}
	public void addExtension(OutputClass parentClass){
		this.addParent(parentClass);
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
	public Set<String> getVariableNames(){
		return variableMap.keySet();
	}

	public Output getName(){
		return name;
	}
	public OutputType getFullName(){
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
		original = new OutputClass();
		original.copy(this);
		return this;
	}
	public void restore() {
		copy(original);
		enclosedClasses.parallelStream().forEach(OutputClass::restore);
	}
	private void copy(OutputClass clone){
		packageName = clone.packageName;
		name = clone.name;
		name(name);

		isPublic = clone.isPublic;
		isFinal = clone.isFinal;
		isAbstract = clone.isAbstract;
		isStatic = clone.isStatic;
		isInterface = clone.isInterface;
		isEnum = clone.isEnum;

		enclosingClass = clone.enclosingClass;
		templateTypes.clear();
		templateTypes.addAll(clone.templateTypes);
		extendType = clone.extendType;
		implementsTypes.clear();
		implementsTypes.addAll(clone.implementsTypes);

		Stream<Supplier<Boolean>> parallel = Stream.of(
				()->cleanCollection(clone.variableMap,variableMap,variables),
				()->cleanCollection(clone.methodMap,methodMap,methods),
				()->cleanCollection(clone.enclosedClassMap,enclosedClassMap,enclosedClasses));
		parallel.parallel().forEach(A->A.get());

	}
	private <T> Boolean cleanCollection(Map<String,T> master,Map<String, T> slaveMap, List<T> slaveList){
		Set<String> neededVariables = new HashSet<String>(master.keySet());
		Set<String> hasVariable = new HashSet<String>(slaveMap.keySet()); 
		for(String key:hasVariable){
			if(neededVariables.contains(key)){
				neededVariables.remove(key);
			}
			else {
				slaveList.remove(slaveMap.remove(key));
			}
		}
		for(String key:neededVariables){
			slaveList.add(master.get(key));
			slaveMap.put(key, master.get(key));
		}
		return true;
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
		public OutputType getFullName(){
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
