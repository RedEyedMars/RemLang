package gen.entries;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import gen.*;
import gen.properties.*;
import lists.*;

public class MethodEntry implements Entry,ITypeListener {

	public final static ListEntry NEW_METHOD = new ListEntry(new StringEntry("new"));

	private String type = ITypeListener.TYPE_UNKNOWN;
	private String defaultType = "String";
	private List<ITypeListener> listeners = (List<ITypeListener>)null;
	private Entry subject = null;
	private String methodName = "$NO_METHOD_NAME";
	private ListEntry parameters = new ListEntry();
	private Boolean isNull = false;
	private String elementName = null;

	public MethodEntry(String initialElementName,ListEntry initialParameters){
		elementName = initialElementName;
		parameters = initialParameters;
	}
	public MethodEntry(String initialMethodName){
		methodName = initialMethodName;
	}
	public MethodEntry(Entry initialSubject,String initialMethodName,ListEntry initialParameters){
		methodName = initialMethodName;
		subject = initialSubject;
		parameters = initialParameters;
	}

	public String getType(){
		return type;
	}
	public String getDefaultType(){
		return defaultType;
	}
	public List<ITypeListener> getListeners(){
		return listeners;
	}
	public void setDefaultType(String newDefaultType){
		if((listeners != null)){
			for(ITypeListener listener:listeners){
				listener.setDefaultType(newDefaultType);
			}
		}
		defaultType = newDefaultType;
	}
	public void addListener(ITypeListener listener){
		if((listeners == null)){
			listeners = new ArrayList<ITypeListener>();
		}
		listeners.add(listener);
	}
	public void changeType(String newType){
		if((listeners != null)){
			for(ITypeListener listener:listeners){
				listener.changeType(newType);
			}
		}
		type = newType;
	}
	public Boolean hasType(){
		return (type.equals("$UNKNOWN"));
	}
	public Entry getSubject(){
		return subject;
	}
	public String getMethodName(){
		return methodName;
	}
	public ListEntry getParameters(){
		return parameters;
	}
	public Boolean getIsNull(){
		return isNull;
	}
	public String getElementName(){
		return elementName;
	}
	public void setIsNull(Boolean b){
		isNull = b;
	}
	public void setMethodNames(String newName){
		methodName = newName;
	}
	public void get(StringBuilder builder){
		if((type.equals("$UNKNOWN"))){
			this.changeType(defaultType);
		}
		if((elementName != null)){
			new ElementEntry(Generators.generator,elementName,parameters).get(builder);
		}
		else if((subject == null)){
			new ElementEntry(GeneratorGenerator.exactCallElement,new ListEntry(new ListEntry(new StringEntry(methodName)))).get(builder);
		}
		else if((NEW_METHOD.equals(subject))){
			new ElementEntry(GeneratorGenerator.newObjectCallElement,new ListEntry(new ListEntry(new StringEntry(methodName)),new ListEntry(parameters))).get(builder);
		}
		else {
			new ElementEntry(GeneratorGenerator.methodCallElement,new ListEntry(subject,new ListEntry(new StringEntry(methodName)),new ListEntry(parameters))).get(builder);
		}
	}
}