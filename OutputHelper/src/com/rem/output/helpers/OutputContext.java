package com.rem.output.helpers;
import java.util.*;


public abstract class OutputContext extends LineableOutput {
	protected List<OutputContext> parentContexts = new ArrayList<OutputContext>();

	protected Map<String, OutputVariable> variableMap = Collections.synchronizedMap(new HashMap<String, OutputVariable>());
	protected List<OutputVariable> variables = Collections.synchronizedList(new ArrayList<OutputVariable>());
	

	protected Map<String, OutputMethod> methodMap = Collections.synchronizedMap(new HashMap<String, OutputMethod>());
	protected List<OutputMethod> methods = Collections.synchronizedList(new ArrayList<OutputMethod>());
	
	public OutputContext setParent(OutputContext newParentContext){
		parentContexts.clear();
		if(newParentContext!=null){
			parentContexts.add(newParentContext);
			parentContexts.addAll(newParentContext.parentContexts);
		}
		return this;
	}
	public Boolean hasVariableInContext(String query){
		if(variableMap.containsKey(query)){
			return true;
		}
		else if(!parentContexts.isEmpty()) {
			return parentContexts.parallelStream().filter(P->P.variableMap.containsKey(query)).findFirst().orElse(null)!=null;
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
	public OutputVariable getVariableFromContext(String query){
		OutputVariable variable = variableMap.get(query);
		if(variable==null){
			return parentContexts.parallelStream().map(P->P.variableMap.get(query)).filter(L->L!=null).findFirst().orElse(null);
		}
		else {
			return variable;
		}
	}
	public OutputClass getVariableClassFromContext(String query){
		OutputVariable variable = variableMap.get(query);
		if(variable==null){
			variable = parentContexts.parallelStream().map(P->P.variableMap.get(query)).filter(L->L!=null).findFirst().orElse(null);
		}
		return variable.getType().getAsClass();
	}

	public Boolean hasMethodInContext(String query){
		if(methodMap.containsKey(query)){
			return true;
		}
		else if(!parentContexts.isEmpty()) {
			return parentContexts.parallelStream().filter(P->P.methodMap.containsKey(query)).findFirst().orElse(null)!=null;
		}
		else {
			return false;
		}
	}
	public void addMethod(OutputMethod method) {
		this.methodMap.put(method.getName().evaluate(),method);
		this.methods.add(method);
		method.setParent(this);
	}
	public void addMethods(List<OutputMethod> methods) {
		methods.forEach(M->{
		  this.methodMap.put(M.getName().evaluate(),M);
		  this.methods.add(M);});
	}
	public OutputMethod getMethodFromContext(String query){
		OutputMethod variable = methodMap.get(query);
		if(variable==null){
			return parentContexts.parallelStream().map(P->P.methodMap.get(query)).filter(L->L!=null).findFirst().orElse(null);
		}
		else {
			return variable;
		}
	}
	public OutputClass getMethodClassFromContext(String query){
		OutputMethod variable = methodMap.get(query);
		if(variable==null){
			variable = parentContexts.parallelStream().map(P->P.methodMap.get(query)).filter(L->L!=null).findFirst().orElse(null);
		}
		return variable.getType().getAsClass();
	}
}
