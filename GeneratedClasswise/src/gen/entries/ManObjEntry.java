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

public class ManObjEntry implements Entry,IInnerable,IImportable,IContextualizable {
	public ManObjEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private Entry className = null;
	private StringEntry methodName = null;
	private Entry parameters = (Entry)null;

	public ManObjEntry(Entry iClassName,String iMethodName,ListEntry iParameters){
		isInner = true;
		className = iClassName;
		methodName = new StringEntry(iMethodName);
		iParameters.setDelimiter(",");
		parameters = iParameters;
		if((iMethodName.equals("appendToBody"))){
			parameters = new ElementEntry(InternalGenerator.newBodyElement,new ListEntry(parameters));
		}
	}

	public Boolean getIsInner(){
		return isInner;
	}
	public void setIsInner(Boolean newInner) {
		isInner = newInner;
	}
	public ImportListEntry getImportPackage(){
		return importPackage;
	}
	public void setPackage(String newPackage,String newName) {
		importPackage.addPackage(newPackage,newName);
	}
	public void addImports(ImportListEntry newImport) {
		importPackage.addImports(newImport);
	}
	public ContextEntry getContext(){
		return context;
	}
	public Integer getTabs(){
		return tabs;
	}
	public StringEntry getETabs(){
		return e_tabs;
	}
	public void setContext(ContextEntry newContext) {
		context = newContext;
	}	public Entry getClassName(){
		return className;
	}	public StringEntry getMethodName(){
		return methodName;
	}
	public Entry getParameters(){
		return parameters;
	}
	public void get(StringBuilder builder){
		if((className != null)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(InternalGenerator.manipulateObjectElement,new ListEntry(className,methodName,parameters)))).get(builder);
		}
	}
}