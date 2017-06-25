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

public class VariableEntry implements Entry,ITypeListener {
	public VariableEntry getSelf(){
		return this;
	}

	public static final Integer DEFAULT_ACCESS = 0;
	public static final Integer PUBLIC_ACCESS = 1;
	public static final Integer PRIVATE_ACCESS = 2;

	private String type = ITypeListener.TYPE_UNKNOWN;
	private String defaultType = "String";
	private List<ITypeListener> listeners = (List<ITypeListener>)null;
	private Boolean isCast = false;
	private Boolean isEntry = false;
	private String name = null;
	private Entry assignment = null;
	private Boolean defined = true;
	private Boolean isFinal = false;
	private Boolean isStatic = false;
	private Integer access = DEFAULT_ACCESS;
	private Boolean showType = true;

	public VariableEntry(){
		name = "null";
	}
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
	public VariableEntry(String initialName,String initialType,Entry newAssignment){
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
	public String getName(){
		return name;
	}	public Entry getAssignment(){
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
		if((assignment == null)){
			assignment = (Entry)new ListEntry();
		}
	}
	public void setDefined(Boolean isDefined){
		defined = isDefined;
	}
	public void setStatic(Boolean newStatic){
		isStatic = newStatic;
	}
	public void setFinal(Boolean newFinal){
		isFinal = newFinal;
	}
	public Boolean isDefined(Boolean checkCase){
		return (defined == checkCase);
	}
	public void setAssignment(Entry newAssignment){
		assignment = newAssignment;
	}
	public void get(StringBuilder builder){
		if((type.equals("$UNKNOWN"))){
			this.changeType(defaultType);
		}
		if((assignment == null && showType == false)){
			new StringEntry(name).get(builder);
		}
		if((access == PRIVATE_ACCESS && showType == true)){
			new StringEntry("private ").get(builder);
		}
		else if((access == PUBLIC_ACCESS && showType == true)){
			new StringEntry("public ").get(builder);
		}
		if((isStatic == true && showType == true)){
			new StringEntry("static ").get(builder);
		}
		if((isFinal == true && showType == true)){
			new StringEntry("final ").get(builder);
		}
		if((assignment == null && showType == true)){
			new ElementEntry(GeneratorGenerator.typeAndNameElement,new ListEntry(new StringEntry(type),new StringEntry(name))).get(builder);
		}
		else if((showType == true)){
			new ElementEntry(GeneratorGenerator.variableDeclarationElement,new ListEntry(new StringEntry(type),new StringEntry(name),assignment)).get(builder);
		}
	}
}