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

public class SetVariableEntry implements Entry,IExecutable {
	public SetVariableEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private String variableName = null;
	private IExecutable executor = (IExecutable)null;

	public SetVariableEntry(String initialVariableName,IExecutable initialExecutor){
		variableName = initialVariableName;
		executor = initialExecutor;
	}

	public ArrayList<IExecutable> getBody(){
		return body;
	}
	public void addToBody(IExecutable element) {
		body.add(element);
	}
	public VariableEntry execute(IContext context){
		VariableEntry ret = (VariableEntry)executor.execute(context);
		VariableEntry newVariable = context.getVariable(variableName);
		if((newVariable == null)){
			newVariable = new VariableEntry(variableName);
			context.setVariable(variableName,newVariable);
		}
		if((ret != null)){
			newVariable.setType(ret.getType());
			newVariable.setValue(ret.getValue());
		}
		return newVariable;
	}
	public String getVariableName(){
		return variableName;
	}
	public IExecutable getExecutor(){
		return executor;
	}
	public void get(StringBuilder builder){
	}
}