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

public class MethodEntry implements Entry,IExecutable {
	public MethodEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private Integer systemMethodIndex = -1;
	private String methodName = null;
	private ArrayList<String> parameterNames = new ArrayList<String>();
	private Integer numberOfParameters = 0;

	public MethodEntry(SystemEntry system,String methodName){
		systemMethodIndex = system.getMethodIndex(methodName);
	}
	public MethodEntry(){
		methodName = "$Hidden";
	}
	public MethodEntry(String initialMethodName){
		methodName = initialMethodName;
	}
	public MethodEntry(String initialMethodName,List<String> initialParameterNames){
		methodName = initialMethodName;
		parameterNames.addAll(initialParameterNames);
		numberOfParameters = parameterNames.size();
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
	public Integer getSystemMethodIndex(){
		return systemMethodIndex;
	}
	public String getMethodName(){
		return methodName;
	}
	public ArrayList<String> getParameterNames(){
		return parameterNames;
	}
	public Integer getNumberOfParameters(){
		return numberOfParameters;
	}
	public VariableEntry executeMethod(IContext toContext,IContext fromContext,List<IExecutable> parameters){
		IContext newContext = (IContext)toContext.descend();
		for(Integer index = 0;index<numberOfParameters;++index){
			newContext.setVariable(parameterNames.get(index),parameters.get(index).execute(fromContext));
		}
		if((systemMethodIndex != -1)){
			VariableEntry ret = (VariableEntry)new SystemEntry().handle(systemMethodIndex,newContext);
			return ret;
		}
		return (VariableEntry)this.execute(newContext);
	}
	public void addParameter(String newVariableName){
		parameterNames.add(newVariableName);
		numberOfParameters = parameterNames.size();
	}
	public void get(StringBuilder builder){
	}
}