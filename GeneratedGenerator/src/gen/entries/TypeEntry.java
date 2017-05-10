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

public class TypeEntry implements Entry {
	public TypeEntry getSelf(){
		return this;
	}


	private ITypeListener subject = (ITypeListener)null;
	private String defaultType = "void";

	public TypeEntry(ITypeListener initialSubject){
		subject = initialSubject;
	}
	public TypeEntry(){
		subject = null;
	}

	public ITypeListener getSubject(){
		return subject;
	}
	public String getDefaultType(){
		return defaultType;
	}
	public void setSubject(ITypeListener newSubject){
		subject = newSubject;
	}
	public void setDefaultType(String newDefaultType){
		defaultType = newDefaultType;
	}
	public void get(StringBuilder builder){
		if((subject == null)){
			new ListEntry(new StringEntry(defaultType)).get(builder);
		}
		else {
			new GetTypeEntry(subject).get(builder);
		}
	}
}