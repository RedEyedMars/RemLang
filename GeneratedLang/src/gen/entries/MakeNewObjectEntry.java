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

public class MakeNewObjectEntry implements Entry,IExecutable {
	public MakeNewObjectEntry getSelf(){
		return this;
	}


	private ArrayList<IExecutable> body = new ArrayList<IExecutable>();
	private String className = null;

	public MakeNewObjectEntry(String initialClassName){
		className = initialClassName;
	}

	public ArrayList<IExecutable> getBody(){
		return body;
	}
	public void addToBody(IExecutable element) {
		body.add(element);
	}
	public VariableEntry execute(IContext context){
		ClassEntry useClass = (ClassEntry)Generators.classifier.getType(className);
		VariableEntry variable = (VariableEntry)new VariableEntry("$NEW");
		ClassEntry newClass = (ClassEntry)useClass.descend();
		useClass.execute(newClass);
		variable.setType(newClass);
		return variable;
	}
	public String getClassName(){
		return className;
	}
	public void get(StringBuilder builder){
	}
}