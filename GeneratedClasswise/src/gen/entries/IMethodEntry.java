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

public class IMethodEntry implements Entry,IInnerable,IImportable,IContextualizable,INameable,IStaticable,IArraytypeable {
	public IMethodEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private Entry name = (Entry)new StringEntry("");
	private ListEntry completeName = (ListEntry)new ListEntry();
	private Boolean isStatic = false;
	private StringEntry asStatic = new StringEntry("");
	private ListEntry arrayType = (ListEntry)new ListEntry();
	private Entry type = null;
	private ListEntry parameters = null;
	private ListEntry methodBody = null;

	public IMethodEntry(Entry iType,Entry iName,ListEntry iParameters,ListEntry iMethodBody,ContextEntry iContext){
		isInner = true;
		StringBuilder realName = (StringBuilder)new StringBuilder();
		iName.get(realName);
		if((realName.toString().equals("*"))){
			name = (Entry)new StringEntry("");
		}
		else {
			name = iName;
		}
		type = iType;
		IImportable typeAsImportable = (IImportable)iType;
		parameters = iParameters;
		methodBody = new ListEntry();
		context = iContext;
		this.addImports(typeAsImportable.getImportPackage());
		for(Entry e:iParameters){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
			IFinalizable f = (IFinalizable)e;
			f.setIsFinal(true);
		}
		ContextEntry methodBodyContext = new ContextEntry(iContext);
		String semicolon = ";";
		for(Entry e:iMethodBody){
			IImportable i = (IImportable)e;
			IContextualizable c = (IContextualizable)e;
			c.setContext(methodBodyContext);
			this.addImports(i.getImportPackage());
			methodBody.add(e);
		}
		parameters.setDelimiter(",");
		methodBody.setDelimiter("");
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
	}
	public Entry getName(){
		return name;
	}
	public ListEntry getCompleteName(){
		return completeName;
	}
	public String getFullName() {
		StringBuilder builder = new StringBuilder();
		completeName.get(builder);
		return builder.toString();
	}
	public Boolean getIsStatic(){
		return isStatic;
	}
	public StringEntry getAsStatic(){
		return asStatic;
	}
	public void setIsStatic(Boolean newIsStatic) {
		isStatic = newIsStatic;
		if((isStatic == true)){
			asStatic.set("static ");
		}
		else {
			asStatic.set("");
		}
	}
	public ListEntry getArrayType(){
		return arrayType;
	}
	public void setArrayType(ListEntry newArrayType) {
		arrayType = newArrayType;
	}	public Entry getType(){
		return type;
	}	public ListEntry getParameters(){
		return parameters;
	}	public ListEntry getMethodBody(){
		return methodBody;
	}
	public void get(StringBuilder builder){
		if((context == null)){
			tabs = 0;
		}
		else {
			tabs = context.getTab();
		}
		e_tabs.set(tabs.toString());
		if((context != null)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(InternalGenerator.declareMethodElement,new ListEntry(asStatic,type,arrayType,name,parameters,methodBody)))).get(builder);
		}
		if((context != null)){
			new TabEntry(tabs,new ListEntry(new StringEntry("}"))).get(builder);
		}
	}
}