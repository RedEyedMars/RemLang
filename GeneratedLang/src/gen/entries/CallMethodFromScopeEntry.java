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

public class CallMethodFromScopeEntry implements Entry,IExecutable {
	public CallMethodFromScopeEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private IExecutable scope = (IExecutable)null;
	private String methodName = null;
	private List<IExecutable> parameters = (List<IExecutable>)null;

	public CallMethodFromScopeEntry(IExecutable initialScope,String initialMethodName,List<IExecutable> initialParameters){
		scope = initialScope;
		methodName = initialMethodName;
		parameters = initialParameters;
	}
	public CallMethodFromScopeEntry(String initialMethodName){
		methodName = initialMethodName;
	}

	public ArrayList<IExecutable> getBody(){
		return body;
	}
	public void addToBody(IExecutable element) {
		body.add(element);
	}
	public VariableEntry execute(IContext context){
		ClassEntry useClass = (ClassEntry)null;
		if((scope != null)){
			VariableEntry variable = (VariableEntry)scope.execute(context);
			useClass = (ClassEntry)variable.getType();
		}
		else {
			useClass = (ClassEntry)Generators.classifier.getDefaultContext();
		}
		MethodEntry method = (MethodEntry)useClass.getMethod(methodName);
		return method.executeMethod(useClass,context,parameters);
	}
	public IExecutable getScope(){
		return scope;
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