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

public class SystemEntry implements Entry {
	public SystemEntry getSelf(){
		return this;
	}




	public Integer getMethodIndex(String methodName){
		if((methodName.equals("println"))){
			return 0;
		}
		if((methodName.equals("concat"))){
			return 1;
		}
		return -1;
	}
	public VariableEntry handle(Integer methodIndex,IContext context){
		if((methodIndex == 0)){
			VariableEntry parameter = (VariableEntry)context.getVariable("default");
			Generators.classifier.println(parameter.getValue());
			return parameter;
		}
		if((methodIndex == 1)){
			VariableEntry left = (VariableEntry)context.getVariable("leftParameter");
			VariableEntry right = (VariableEntry)context.getVariable("rightParameter");
			VariableEntry ret = new VariableEntry("$CONCAT");
			ret.setValue(Generators.classifier.buildString(left.getValue(),right.getValue()));
			return ret;
		}
		return null;
	}
	public void get(StringBuilder builder){
	}
}