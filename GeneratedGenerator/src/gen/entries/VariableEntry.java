package gen.entries;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import gen.*;
import gen.properties.*;
import lists.*;

public class VariableEntry implements Entry,ITypeListener {

	public final static Integer DEFAULT_ACCESS = 0;
	public final static Integer PUBLIC_ACCESS = 1;
	public final static Integer PRIVATE_ACCESS = 2;

	private String type = ITypeListener.TYPE_UNKNOWN;
	private String defaultType = "String";
	private List<ITypeListener> listeners = (List<ITypeListener>)null;
	private String name = null;
	private ListEntry assignment = new ListEntry();
	private Boolean defined = true;
	private Boolean isFinal = false;
	private Boolean isStatic = false;
	private Integer access = DEFAULT_ACCESS;
	private Boolean showType = true;

	public VariableEntry(String initialName){
		name = initialName;
		assignment = null;
		showType = false;
	}
	public VariableEntry(String initialName,String initialType){
		name = initialName;
		this.changeType(initialType);
		assignment = null;
	}
	public VariableEntry(String initialName,String initialType,ListEntry newAssignment){
		name = initialName;
		this.changeType(initialType);
		assignment = newAssignment;
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
	public String getName(){
		return name;
	}	public ListEntry getAssignment(){
		return assignment;
	}
	public Boolean getDefined(){
		return defined;
	}
	public Boolean getIsFinal(){
		return isFinal;
	}
	public Boolean getIsStatic(){
		return isStatic;
	}
	public Integer getAccess(){
		return access;
	}
	public Boolean getShowType(){
		return showType;
	}
	public void setAccess(Integer newAccess){
		access = newAccess;
	}
	public void setDefined(Boolean isDefined){
		defined = isDefined;
	}
	public void get(StringBuilder builder){
		if((type.equals("$UNKNOWN"))){
			this.changeType(defaultType);
		}
		if((assignment == null && showType == false)){
			new ListEntry(new StringEntry(name)).get(builder);
		}
		if((access == PRIVATE_ACCESS && showType == true)){
			new ListEntry(new StringEntry("private ")).get(builder);
		}
		else if((access == PUBLIC_ACCESS && showType == true)){
			new ListEntry(new StringEntry("public ")).get(builder);
		}
		if((isStatic == true && showType == true)){
			new ListEntry(new StringEntry("static ")).get(builder);
		}
		if((isFinal == true && showType == true)){
			new ListEntry(new StringEntry("final ")).get(builder);
		}
		if((assignment == null && showType == true)){
			new ElementEntry(GeneratorGenerator.typeAndNameElement,new ListEntry(new StringEntry(type),new StringEntry(name))).get(builder);
		}
		else if((showType == true)){
			new ElementEntry(GeneratorGenerator.variableDeclarationElement,new ListEntry(new ListEntry(new StringEntry(type)),new ListEntry(new StringEntry(name)),assignment)).get(builder);
		}
	}
}