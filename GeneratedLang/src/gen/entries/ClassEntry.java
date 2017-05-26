package gen.entries;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import gen.*;
import gen.checks.*;
import gen.properties.*;
import lists.*;

public class ClassEntry implements Entry,IContext,IExecutable {
	public ClassEntry getSelf(){
		return this;
	}


	private HashMap<String,VariableEntry> variables = new HashMap<String,VariableEntry>();
	private IContext parentContext = (IContext)null;
	private ClassEntry contextClass = (ClassEntry)null;
	private Boolean shouldReturn = false;
	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private String className = null;
	private HashMap<String,MethodEntry> methods = new HashMap<String,MethodEntry>();

	public ClassEntry(String initialName,ClassEntry actualClass){
		className = Generators.classifier.buildString(initialName,"$C");
		contextClass = actualClass;
	}
	public ClassEntry(String initialName){
		className = initialName;
		contextClass = this.getSelf();
	}

	public HashMap<String,VariableEntry> getVariables(){
		return variables;
	}
	public IContext getParentContext(){
		return parentContext;
	}
	public ClassEntry getContextClass(){
		return contextClass;
	}
	public Boolean getShouldReturn(){
		return shouldReturn;
	}
	public ClassEntry getType() {
		ClassEntry ret = (ClassEntry)null;
		if((contextClass != null)){
			return (ClassEntry)contextClass;
		}
		if((parentContext != null)){
			ret = parentContext.getType();
		}
		else {
			ret = null;
		}
		return ret;
	}
	public VariableEntry getVariable(String variableName) {
		VariableEntry ret = (VariableEntry)null;
		if((variables.containsKey(variableName))){
			ret = variables.get(variableName);
		}
		else {
			if((parentContext != null)){
				ret = parentContext.getVariable(variableName);
			}
		}
		return ret;
	}
	public void addVariable(String variableName) {
		if((!variables.containsKey(variableName))){
			variables.put(variableName,new VariableEntry(variableName));
		}
	}
	public void setVariable(String variableName,VariableEntry variable) {
		variables.put(variableName,variable);
	}
	public void setShouldReturn(Boolean newShould) {
		shouldReturn = newShould;
	}
	public ClassEntry descend(){
		ClassEntry ret = (ClassEntry)new ClassEntry(className,contextClass);
		Set<String> vars = (Set<String>)variables.keySet();
		for(String key:vars){
			ret.setVariable(key,variables.get(key));
		}
		Set<String> mets = (Set<String>)methods.keySet();
		for(String key:mets){
			ret.setMethod(key,methods.get(key));
		}
		return ret;
	}
	public ArrayList<IExecutable> getBody(){
		return body;
	}
	public void addToBody(IExecutable element) {
		body.add(element);
	}
	public VariableEntry execute(IContext myContext) {
		VariableEntry ret = (VariableEntry)null;
		for(IExecutable element:body){
			IExecutable atom = (IExecutable)element;
			ret = atom.execute(myContext);
			if((myContext.getShouldReturn())){
				myContext.setShouldReturn(false);
				return ret;
			}
		}
		return ret;
	}
	public String getClassName(){
		return className;
	}
	public HashMap<String,MethodEntry> getMethods(){
		return methods;
	}
	public MethodEntry getMethod(String methodName){
		MethodEntry method = (MethodEntry)methods.get(methodName);
		if((method == null)){
			throw new UnableToGenerateException("Unable to find method:"+methodName+" in class:"+className,null);
		}
		return method;
	}
	public void setMethod(String methodName,MethodEntry method){
		methods.put(methodName,method);
	}
	public ClassEntry createChild(String newClassName){
		ClassEntry ret = (ClassEntry)new ClassEntry(newClassName);
		Set<String> vars = (Set<String>)variables.keySet();
		for(String key:vars){
			ret.setVariable(key,variables.get(key));
		}
		Set<String> mets = (Set<String>)methods.keySet();
		for(String key:mets){
			ret.setMethod(key,methods.get(key));
		}
		return ret;
	}
	public void get(StringBuilder builder){
	}
}