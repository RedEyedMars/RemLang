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

public class VariableEntry implements Entry {
	public VariableEntry getSelf(){
		return this;
	}


	private String variableName = null;
	private String value = null;
	private ClassEntry type = (ClassEntry)null;

	public VariableEntry(String initialName){
		variableName = initialName;
	}

	public String getVariableName(){
		return variableName;
	}
	public String getValue(){
		if((value != null)){
			return value;
		}
		else {
			VariableEntry classValue = (VariableEntry)type.getVariable("value");
			if((classValue == null)){
				return null;
			}
			else {
				return classValue.getValue();
			}
		}
	}
	public ClassEntry getType(){
		return type;
	}
	public void setType(ClassEntry newType){
		type = newType;
	}
	public void setValue(String newValue){
		value = newValue;
	}
	public void get(StringBuilder builder){
	}
}