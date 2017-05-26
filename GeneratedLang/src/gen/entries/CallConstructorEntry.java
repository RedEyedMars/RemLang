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

public class CallConstructorEntry implements Entry,IExecutable {
	public CallConstructorEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private String className = null;
	private String methodName = null;
	private List<IExecutable> parameters = (List<IExecutable>)null;

	public CallConstructorEntry(String initialClassName,String initialMethodName,List<IExecutable> initialParameters){
		className = initialClassName;
		methodName = initialMethodName;
		parameters = initialParameters;
	}

	public ArrayList<IExecutable> getBody(){
		return body;
	}
	public void addToBody(IExecutable element) {
		body.add(element);
	}
	public VariableEntry execute(IContext context){
		if((className == null)){
			className = Generators.classifier.getClassNameFromConstructor(methodName);
		}
		ClassEntry useClass = (ClassEntry)Generators.classifier.getType(className);
		MethodEntry method = (MethodEntry)useClass.getMethod(methodName);
		ClassEntry newClass = (ClassEntry)useClass.descend();
		VariableEntry variable = (VariableEntry)new VariableEntry("$NEW");
		useClass.execute(newClass);
		variable.setType(newClass);
		newClass.setVariable("this",variable);
		Integer numberOfParameters = (Integer)method.getNumberOfParameters();
		List<String> parameterNames = (List<String>)method.getParameterNames();
		for(Integer index = 0;index<numberOfParameters;++index){
			newClass.setVariable(parameterNames.get(index),parameters.get(index).execute(context));
		}
		method.execute(newClass);
		return variable;
	}
	public String getClassName(){
		return className;
	}
	public String getMethodName(){
		return methodName;
	}
	public List<IExecutable> getParameters(){
		return parameters;
	}
	public void get(StringBuilder builder){
	}
}