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

public class GetDataEntry implements Entry,IExecutable {
	public GetDataEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private String value = null;

	public GetDataEntry(String initialValue){
		value = initialValue;
	}

	public ArrayList<IExecutable> getBody(){
		return body;
	}
	public void addToBody(IExecutable element) {
		body.add(element);
	}
	public VariableEntry execute(IContext context){
		VariableEntry ret = (VariableEntry)new VariableEntry("$DATA");
		ret.setValue(value);
		return ret;
	}
	public String getValue(){
		return value;
	}
	public void get(StringBuilder builder){
	}
}