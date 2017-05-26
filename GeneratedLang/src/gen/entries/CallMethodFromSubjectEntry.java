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

public class CallMethodFromSubjectEntry implements Entry,IExecutable {
	public CallMethodFromSubjectEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private String subjectName = null;
	private String methodName = null;
	private List<IExecutable> parameters = (List<IExecutable>)null;
	private Boolean useSelf = false;

	public CallMethodFromSubjectEntry(String initialSubjectName,String initialMethodName,List<IExecutable> initialParameters){
		subjectName = initialSubjectName;
		methodName = initialMethodName;
		parameters = initialParameters;
		if((subjectName != null && subjectName.equals("this"))){
			useSelf = true;
		}
	}
	public CallMethodFromSubjectEntry(String initialMethodName){
		methodName = initialMethodName;
		useSelf = true;
	}

	public ArrayList<IExecutable> getBody(){
		return body;
	}
	public void addToBody(IExecutable element) {
		body.add(element);
	}
	public VariableEntry execute(IContext context){
		ClassEntry useClass = (ClassEntry)null;
		if((useSelf == true)){
			useClass = context.getType();
		}
		else {
			VariableEntry variable = (VariableEntry)context.getVariable(subjectName);
			useClass = variable.getType();
		}
		MethodEntry method = (MethodEntry)useClass.getMethod(methodName);
		return method.executeMethod(context,context,parameters);
	}
	public String getSubjectName(){
		return subjectName;
	}
	public String getMethodName(){
		return methodName;
	}
	public List<IExecutable> getParameters(){
		return parameters;
	}
	public Boolean getUseSelf(){
		return useSelf;
	}
	public void get(StringBuilder builder){
	}
}