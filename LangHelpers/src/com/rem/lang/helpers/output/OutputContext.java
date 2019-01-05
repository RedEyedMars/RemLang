package com.rem.lang.helpers.output;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;


public abstract class OutputContext extends LineableOutput {
	protected Set<OutputContext> parentContexts = new HashSet<OutputContext>();

	protected Map<String, OutputVariable> variableMap = Collections.synchronizedMap(new HashMap<String, OutputVariable>());
	protected List<OutputVariable> variables = Collections.synchronizedList(new ArrayList<OutputVariable>());
	

	protected Map<String, OutputMethod> methodMap = Collections.synchronizedMap(new HashMap<String, OutputMethod>());
	protected Map<String, OutputMethod> simpleMethodMap = Collections.synchronizedMap(new HashMap<String, OutputMethod>());
	protected List<OutputMethod> methods = Collections.synchronizedList(new ArrayList<OutputMethod>());
	
	public OutputContext setParent(OutputContext newParentContext){
		parentContexts.clear();
		if(newParentContext!=null){
			parentContexts.add(newParentContext);
			//parentContexts.addAll(newParentContext.parentContexts);
		}
		return this;
	}
	public OutputContext addParent(OutputContext newParentContext){
		if(newParentContext!=null){
			parentContexts.add(newParentContext);
			//parentContexts.addAll(newParentContext.parentContexts);
			//methods.forEach(M->M.addParent(newParentContext));
		}
		return this;
	}
	
	public Set<String> getMethodNamesFromContext(){
		return methodMap.keySet();
	}
	public String printMethodNamesFromContext(){
		StringBuilder builder = new StringBuilder();
		builder.append(">");
		printMethodNamesFromContext(0,builder::append);
		return builder.toString();
	}
	public void printMethodNamesFromContext(int tab, Consumer<String> acceptor){
		if(methodMap.isEmpty()){
			if(parentContexts!=null&&!parentContexts.isEmpty()){
				parentContexts.forEach(PC->PC.printMethodNamesFromContext(tab,acceptor));
			}
			return;
		}
		acceptor.accept("\n");
		IntStream.range(0,tab).forEach(I->acceptor.accept("\t"));
		acceptor.accept(methodMap.keySet().toString());
		if(parentContexts!=null&&!parentContexts.isEmpty()){
			parentContexts.forEach(PC->PC.printMethodNamesFromContext(tab+1,acceptor));
		}
	}
	public Set<String> getVariableNamesFromContext(){
		return variableMap.keySet();
	}
	public Boolean hasVariableInContext(String query){
		if(variableMap.containsKey(query)){
			return true;
		}
		else if(!parentContexts.isEmpty()) {
			return parentContexts.parallelStream().anyMatch(P->P.hasVariableInContext(query));
		}
		else {
			return false;
		}
	}
	public void addVariable(OutputVariable variable) {
		this.variableMap.put(variable.getName().evaluate(),variable);
		this.variables.add(variable);
	}
	public void addVariables(List<OutputVariable> variables) {
		variables.forEach(V->{
		  this.variableMap.put(V.getName().evaluate(),V);
		  this.variables.add(V);});
	}
	public void removeVariable(String variableName){
		if(variableMap.containsKey(variableName)){
			this.variables.remove(this.variableMap.remove(variableName));
		}
	}
	public OutputVariable getVariableFromContext(String query){
		OutputVariable variable = variableMap.get(query);
		if(variable==null){
			return parentContexts.parallelStream().map(P->P.getVariableFromContext(query)).filter(L->L!=null).findAny().orElse(null);
		}
		else {
			return variable;
		}
	}
	public OutputClass getVariableClassFromContext(String query){
		OutputVariable variable = variableMap.get(query);
		if(variable==null){
			variable = parentContexts.parallelStream().map(P->P.getVariableFromContext(query)).filter(L->L!=null).findAny().orElse(null);
			if(variable==null){
				return null;
			}
		}
		return variable.getType().getAsClass();
	}

	public Boolean hasMethodInContext(String query){
		if(methodMap.containsKey(query)||simpleMethodMap.containsKey(query)){
			return true;
		}
		else if(!parentContexts.isEmpty()) {
			return parentContexts.parallelStream().filter(P->P.hasMethodInContext(query)).findAny().orElse(null)!=null;
		}
		else {
			return false;
		}
	}
	public void addMethod(OutputMethod method) {
		this.simpleMethodMap.put(method.getName().evaluate(),method);
		this.methodMap.put(method.getSignature(), method);
		this.methods.add(method);
		method.setParent(this);
	}
	public void addMethods(List<OutputMethod> methods) {
		methods.forEach(M->{
		  this.simpleMethodMap.put(M.getName().evaluate(),M);
		  this.methodMap.put(M.getSignature(), M);
		  this.methods.add(M);});
	}
	public OutputMethod getMethodFromContext(String query){
		OutputMethod method = methodMap.get(query);
		if(method==null){
			method = simpleMethodMap.get(query);
			if(method==null){
				return parentContexts.stream().map(P->P.getMethodFromContext(query)).filter(L->L!=null).findFirst().orElse(null);
			}
			else {
				return method;
			}
		}
		else {
			return method;
		}
	}
	public OutputClass getMethodClassFromContext(String query){
		OutputMethod method = getMethodFromContext(query);
		if(method==null){
			return null;
		}
		return method.getType().getAsClass();
	}
}
