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

public class GetVariableEntry implements Entry,IExecutable {
	public GetVariableEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private String variableName = null;

	public GetVariableEntry(String initialVariableName){
		variableName = initialVariableName;
	}

	public ArrayList<IExecutable> getBody(){
		return body;
	}
	public void addToBody(IExecutable element) {
		body.add(element);
	}
	public VariableEntry execute(IContext context){
		return context.getVariable(variableName);
	}
	public String getVariableName(){
		return variableName;
	}
	public void get(StringBuilder builder){
	}
}