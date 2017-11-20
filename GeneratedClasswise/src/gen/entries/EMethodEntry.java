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

public class EMethodEntry implements Entry,IInnerable,IImportable,IContextualizable,INameable,IInterfaceable,IStatickable,IArraytypeable {
	public EMethodEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private Entry name = (Entry)new StringEntry("");
	private ListEntry completeName = (ListEntry)new ListEntry();
	private Boolean isInterface = false;
	private Boolean isStatic = false;
	private ListEntry arrayType = (ListEntry)new ListEntry();
	private Entry type = null;
	private ListEntry parameters = null;
	private ListEntry methodBody = null;
	private Entry sThrows = null;
	private Boolean pas = false;
	private String methodIndex = null;

	public EMethodEntry(Entry iType,Entry iName,Boolean iParametersAreStatement,ListEntry iParameters,ListEntry iThrows,ListEntry iMethodBody,ContextEntry iContext,Integer iIndex){
		methodIndex = iIndex.toString();
		pas = iParametersAreStatement;
		name = iName;
		type = iType;
		IImportable typeAsImportable = (IImportable)iType;
		parameters = iParameters;
		methodBody = new ListEntry();
		context = iContext;
		this.addImports(typeAsImportable.getImportPackage());
		for(Entry e:iParameters){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
		}
		if((!iThrows.isEmpty())){
			sThrows = (Entry)new ElementEntry(ExternalGenerator.throwsStatementElement,new ListEntry(iThrows));
		}
		else {
			sThrows = (Entry)new ListEntry();
		}
		ContextEntry methodBodyContext = new ContextEntry(iContext);
		String semicolon = ";";
		for(Entry e:iMethodBody){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
			methodBody.add(e);
		}
		parameters.setDelimiter(",\n/*PARAMS*/\t\t\t\t");
		methodBody.setDelimiter(",\n/*BODY*/\t\t\t\t");
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
	public Boolean getIsInterface(){
		return isInterface;
	}
	public void setIsInterface(Boolean newIsInterface) {
		newIsInterface = newIsInterface;
	}
	public Boolean getIsStatic(){
		return isStatic;
	}
	public void setIsStatic(Boolean newIsStatic) {
		isStatic = newIsStatic;
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
	}	public Entry getSThrows(){
		return sThrows;
	}
	public Boolean getPas(){
		return pas;
	}
	public String getMethodIndex(){
		return methodIndex;
	}
	public void get(StringBuilder builder){
		if((context == null)){
			tabs = 0;
		}
		else {
			tabs = context.getTab();
		}
		e_tabs.set(tabs.toString());
		if((isStatic == true && pas == true)){
			new ElementEntry(ExternalGenerator.declareStaticMethodElement,new ListEntry(e_tabs,type,arrayType,name,parameters,sThrows,new ElementEntry(ExternalGenerator.bodyBodyElement,new ListEntry(methodBody)))).get(builder);
		}
		else if((isInterface == true && pas == true)){
			new ElementEntry(ExternalGenerator.declareInterfaceMethodElement,new ListEntry(e_tabs,type,arrayType,name,parameters,sThrows)).get(builder);
		}
		else if((isInterface == false && pas == true)){
			new ElementEntry(ExternalGenerator.declareMethodElement,new ListEntry(e_tabs,type,arrayType,name,parameters,sThrows,new ElementEntry(ExternalGenerator.bodyBodyElement,new ListEntry(methodBody)))).get(builder);
		}
		else if((isStatic == true && pas == false)){
			new ElementEntry(ExternalGenerator.declareStaticMethodElement,new ListEntry(e_tabs,type,arrayType,name,new ElementEntry(ExternalGenerator.bodyParametersElement,new ListEntry(parameters)),sThrows,new ElementEntry(ExternalGenerator.bodyBodyElement,new ListEntry(methodBody)))).get(builder);
		}
		else if((isInterface == true && pas == false)){
			new ElementEntry(ExternalGenerator.declareInterfaceMethodElement,new ListEntry(e_tabs,type,arrayType,name,new ElementEntry(ExternalGenerator.bodyParametersElement,new ListEntry(parameters)),sThrows)).get(builder);
		}
		else if((isInterface == false && pas == false)){
			new ElementEntry(ExternalGenerator.declareMethodElement,new ListEntry(e_tabs,type,arrayType,name,new ElementEntry(ExternalGenerator.bodyParametersElement,new ListEntry(parameters)),sThrows,new ElementEntry(ExternalGenerator.bodyBodyElement,new ListEntry(methodBody)))).get(builder);
		}
	}
}