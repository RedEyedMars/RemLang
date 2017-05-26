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

public class ExecuteReturnEntry implements Entry,IExecutable {
	public ExecuteReturnEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private IExecutable executor = (IExecutable)null;

	public ExecuteReturnEntry(IExecutable initialExecutor){
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
		context.setShouldReturn(true);
		return ret;
	}
	public IExecutable getExecutor(){
		return executor;
	}
	public void get(StringBuilder builder){
	}
}