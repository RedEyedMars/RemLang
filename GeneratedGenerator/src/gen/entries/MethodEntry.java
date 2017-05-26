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

public class MethodEntry implements Entry,ITypeListener {
	public MethodEntry getSelf(){
		return this;
	}

	public static final StringEntry NEW_METHOD = new StringEntry("new");

	private String type = ITypeListener.TYPE_UNKNOWN;
	private String defaultType = "String";
	private List<ITypeListener> listeners = (List<ITypeListener>)null;
	private Boolean isCast = false;
	private Boolean isEntry = false;
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
	public MethodEntry(Entry initialSubject,String initialMethodName,ParametersEntry initialParameters){
		methodName = initialMethodName;
		subject = initialSubject;
		parameters = initialParameters.getListEntry();
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
	public Boolean getIsCast(){
		return isCast;
	}
	public Boolean getIsEntry(){
		return isEntry;
	}
	public void setCast(Boolean newCast) {
		isCast = newCast;
	}
	public void setDefaultType(String newDefaultType) {
		if((listeners != null)){
			for(ITypeListener listener:listeners){
				listener.setDefaultType(newDefaultType);
			}
		}
		defaultType = newDefaultType;
	}
	public void addListener(ITypeListener listener) {
		if((listeners == null)){
			listeners = new ArrayList<ITypeListener>();
		}
		listeners.add(listener);
	}
	public void changeType(String newType) {
		Boolean myHasType = this.hasType();
		Boolean leftClause = (isCast == false && myHasType == false);
		Boolean rightClause = (isCast == false && isEntry == true && newType.contains("Entry"));
		if((leftClause == true || rightClause == true)){
			if((listeners != null)){
				for(ITypeListener listener:listeners){
					listener.changeType(newType);
				}
			}
			type = newType;
			if((newType.contains("Entry"))){
				isEntry = true;
			}
		}
	}
	public void setType(String newType) {
		if((listeners != null)){
			for(ITypeListener listener:listeners){
				listener.setType(newType);
			}
		}
		type = newType;
		isEntry = newType.contains("Entry");
	}
	public Boolean hasType() {
		return (!type.equals("$UNKNOWN"));
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
	public void setElementName(String newName){
		elementName = newName;
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