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

public class IClassEntry implements Entry,IInnerable,INameable,IImportable,IContextualizable,IFileable,IStaticable {
	public IClassEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private Entry name = (Entry)new StringEntry("");
	private ListEntry completeName = (ListEntry)new ListEntry();
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private Boolean isStatic = false;
	private StringEntry asStatic = new StringEntry("");
	private Entry packageName = null;
	private StringEntry classType = null;
	private Entry parent = (Entry)null;
	private Entry interfaces = (Entry)null;
	private ListEntry internalVariables = new ListEntry();
	private ListEntry externalVariables = new ListEntry();
	private ListEntry internalMethods = new ListEntry();
	private ListEntry externalMethods = new ListEntry();
	private ListEntry internalSubClasses = new ListEntry();
	private ListEntry externalSubClasses = new ListEntry();
	private Boolean isSubClass = false;
	private Boolean hasOutput = false;

	public IClassEntry(Entry iPackageName,String iType,Entry iName,Entry iParent,ListEntry iInterfaces,ListEntry iVariables,ListEntry iMethods,ContextEntry iContext){
		isInner = true;
		iPackageName = (Entry)new StringEntry("clgen");
		packageName = iPackageName;
		name = iName;
		completeName.add(name);
		completeName.setDelimiter(".");
		IImportable establishedImportable = (IImportable)Generators.classwise.getType(this.getFullName());
		importPackage = establishedImportable.getImportPackage();
		importPackage.addPackage(new ISingleImportEntry(new ListEntry(new StringEntry("com.rem.parser.generation")),new ListEntry(new StringEntry("*"))));
		importPackage.addPackage(new ISingleImportEntry(new ListEntry(new StringEntry("com.rem.parser.generation.classwise")),new ListEntry(new StringEntry("*"))));
		importPackage.addPackage(new ISingleImportEntry(new ListEntry(new StringEntry("clent")),new ListEntry(new StringEntry("*"))));
		importPackage.addPackage(new ISingleImportEntry(new ListEntry(new StringEntry("java.util")),new ListEntry(new StringEntry("*"))));
		importPackage.addPackage(new ISingleImportEntry(new ListEntry(new StringEntry("java.io")),new ListEntry(new StringEntry("*"))));
		importPackage.addPackage(new ISingleImportEntry(new ListEntry(new StringEntry("java.nio")),new ListEntry(new StringEntry("*"))));
		importPackage.addPackage(packageName,iName);
		importPackage.setIsInner(true);
		classType = new StringEntry(iType);
		if((iParent != null)){
			parent = new ElementEntry(ClasswiseGenerator.extendsElement,new ListEntry(iParent));
			IImportable iParentType = (IImportable)iParent;
			this.addImports(iParentType.getImportPackage());
		}
		else {
			parent = new ListEntry();
		}
		if((!iInterfaces.isEmpty())){
			interfaces = new ElementEntry(ClasswiseGenerator.implementsElement,new ListEntry(iInterfaces));
			for(Entry itrf:iInterfaces){
				IImportable iIntefaceType = (IImportable)itrf;
				this.addImports(iIntefaceType.getImportPackage());
			}
		}
		else {
			interfaces = new ListEntry();
		}
		for(Entry e:iVariables){
			IInnerable i = (IInnerable)e;
			if((i.getIsInner())){
				IVariableEntry niv = (IVariableEntry)e;
				this.addVariable(niv);
			}
			else {
				EVariableEntry nev = (EVariableEntry)e;
				this.addVariable(nev);
			}
		}
		for(Entry e:iMethods){
			IInnerable i = (IInnerable)e;
			if((i.getIsInner())){
				IMethodEntry niv = (IMethodEntry)e;
				this.addMethod(niv);
			}
			else {
				EMethodEntry nev = (EMethodEntry)e;
				this.addMethod(nev);
			}
		}
		context = iContext;
		internalVariables.setDelimiter("");
		externalVariables.setDelimiter("");
		internalMethods.setDelimiter("");
		externalMethods.setDelimiter("");
		internalSubClasses.setDelimiter("");
		externalSubClasses.setDelimiter("");
	}

	public Boolean getIsInner(){
		return isInner;
	}
	public void setIsInner(Boolean newInner) {
		isInner = newInner;
	}
	public Entry getName(){
		return name;
	}
	public ListEntry getCompleteName(){
		return completeName;
	}
	public String getFullName(){
		StringBuilder builder = new StringBuilder();
		completeName.get(builder);
		return builder.toString();
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
	public Entry asFile() {
		return null;
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
	}	public Entry getPackageName(){
		return packageName;
	}	public StringEntry getClassType(){
		return classType;
	}
	public Entry getParent(){
		return parent;
	}
	public Entry getInterfaces(){
		return interfaces;
	}	public ListEntry getInternalVariables(){
		return internalVariables;
	}	public ListEntry getExternalVariables(){
		return externalVariables;
	}	public ListEntry getInternalMethods(){
		return internalMethods;
	}	public ListEntry getExternalMethods(){
		return externalMethods;
	}	public ListEntry getInternalSubClasses(){
		return internalSubClasses;
	}	public ListEntry getExternalSubClasses(){
		return externalSubClasses;
	}
	public Boolean getIsSubClass(){
		return isSubClass;
	}
	public Boolean getHasOutput(){
		return hasOutput;
	}
	public void addVariable(IVariableEntry i){
		String semicolon = ";";
		ContextEntry variableContext = new ContextEntry(context);
		internalVariables.add(new IElementEntry("protected ",i,semicolon,variableContext));
		this.addImports(i.getImportPackage());
		Entry iAssignment = (Entry)i.getAssignment();
		Entry iType = (Entry)i.getType();
		Entry iName = (Entry)i.getName();
		StringBuilder cBuilder = new StringBuilder();
		iName.get(cBuilder);
		if((iAssignment == null)){
			i.setAssignment(new INewObjEntry(iType,new ListEntry()));
		}
		this.addMethod(new IMethodEntry(iType,new IExactEntry(new ElementEntry(InternalGenerator.getMethodNameElement,new ListEntry(new StringEntry(Generator.camelize(cBuilder.toString()))))),new ListEntry(),new ListEntry(new IElementEntry("return ",iName,semicolon,new ContextEntry())),new ContextEntry(new ContextEntry())));
	}
	public void addVariable(EVariableEntry e){
		Entry eName = (Entry)e.getName();
		Entry eAssignment = (Entry)e.getAssignment();
		if((eAssignment == null)){
			Entry eType = (Entry)e.getType();
			e.setAssignment(new ENewObjEntry(eType,new ListEntry()));
		}
		externalVariables.add(new TabEntry(2,new ListEntry(new ElementEntry(InternalGenerator.declareMemberVariableElement,new ListEntry(eName,new ElementEntry(ExternalGenerator.declareMemberElement,new ListEntry(e_tabs,e)))))));
		this.addImports(e.getImportPackage());
	}
	public void addMethod(IMethodEntry i){
		internalMethods.add(i);
		this.addImports(i.getImportPackage());
	}
	public void addMethod(EMethodEntry e){
		Entry eName = (Entry)e.getName();
		externalMethods.add(new TabEntry(1,new ListEntry(new ElementEntry(InternalGenerator.declareMemberMethodElement,new ListEntry(eName,e)))));
		this.addImports(e.getImportPackage());
	}
	public void addSubClass(IClassEntry i){
		internalSubClasses.add(new TabEntry(1,new ListEntry(i)));
		i.setIsSubClass(completeName,true);
		ContextEntry theirContext = (ContextEntry)i.getContext();
		this.addImports(i.getImportPackage());
		theirContext.setParentContext(context);
	}
	public void addSubClass(EClassEntry e){
		Entry eName = (Entry)e.getName();
		Entry eAsFile = (Entry)e.getAsFile();
		externalSubClasses.add(new TabEntry(1,new ListEntry(new ElementEntry(InternalGenerator.declareMemberClassElement,new ListEntry(eName,eName,eAsFile)))));
		this.addImports(e.getImportPackage());
		e.setIsSubClass(completeName,completeName,true);
		ContextEntry theirContext = (ContextEntry)e.getContext();
		theirContext.setParentContext(context);
		StringBuilder nameBuilder = new StringBuilder();
		eName.get(nameBuilder);
		Generators.clazz.addDefinedClassName(nameBuilder.toString());
	}
	public void addClass(EClassEntry e){
		Entry eName = (Entry)e.getName();
		Entry eAsFile = (Entry)e.getAsFile();
		externalSubClasses.add(new TabEntry(1,new ListEntry(new ElementEntry(InternalGenerator.declareMemberClassElement,new ListEntry(eName,eName,eAsFile)))));
		this.addImports(e.getImportPackage());
		StringBuilder nameBuilder = new StringBuilder();
		eName.get(nameBuilder);
		Generators.clazz.addDefinedClassName(nameBuilder.toString());
	}
	public void setIsSubClass(Entry superCompleteName,Boolean newStatus){
		this.setIsStatic(newStatus);
		isSubClass = newStatus;
		completeName.clear();
		completeName.add(superCompleteName);
		completeName.add(name);
	}
	public void get(StringBuilder builder){
		if((context == null)){
			tabs = 0;
		}
		else {
			tabs = context.getTab();
		}
		if((isSubClass == false)){
			new ElementEntry(InternalGenerator.declareClassElement,new ListEntry(packageName,importPackage.get("INTERNAL"),asStatic,classType,name,parent,interfaces,externalVariables,externalMethods,externalSubClasses,internalVariables,internalMethods,internalSubClasses)).get(builder);
		}
		else if((isSubClass == true)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(InternalGenerator.declareSubClassElement,new ListEntry(asStatic,classType,name,parent,interfaces,externalVariables,externalMethods,externalSubClasses,internalVariables,internalMethods,internalSubClasses)))).get(builder);
		}
		if((isSubClass == true)){
			new TabEntry(tabs,new ListEntry(new StringEntry("}"))).get(builder);
		}
	}
}