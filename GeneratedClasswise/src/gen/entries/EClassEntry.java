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

public class EClassEntry implements Entry,IInnerable,INameable,IImportable,IContextualizable,IFileable,IInterfaceable,IGlobalizable {
	public EClassEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private Entry name = (Entry)new StringEntry("");
	private ListEntry completeName = (ListEntry)new ListEntry();
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private Boolean isInterface = false;
	private Boolean isGlobal = false;
	private Entry packageName = (Entry)null;
	private Entry externalPackageName = (Entry)null;
	private StringEntry asStatic = new StringEntry("");
	private IExactEntry asFile = null;
	private IExactEntry asFileVariable = null;
	private IExactEntry asInternalFile = null;
	private IExactEntry asArgument = null;
	private StringEntry classType = null;
	private Entry externalName = (Entry)null;
	private Entry externalTemplateTypeName = (Entry)null;
	private Entry externalParent = (Entry)null;
	private Entry externalParentEntry = (Entry)null;
	private Entry externalInterfaces = (Entry)null;
	private Entry parent = (Entry)null;
	private Entry interfaces = (Entry)null;
	private ListEntry internalVariables = new ListEntry();
	private ListEntry externalVariables = new ListEntry();
	private ListEntry internalMethods = new ListEntry();
	private ListEntry externalMethods = new ListEntry();
	private ListEntry internalSubClasses = new ListEntry();
	private ListEntry externalSubClasses = new ListEntry();
	private ListEntry externalConstructorVariables = new ListEntry();
	private ListEntry externalConstructorMethods = new ListEntry();
	private ListEntry externalConstructorSubClass = new ListEntry();
	private Entry externalInterfacesEntry = new ListEntry();
	private IExactEntry getHeader = null;
	private Boolean isSubClass = false;
	private Boolean isWeak = false;
	private Boolean hasOutput = false;
	private ListEntry completeUnsulliedName = (ListEntry)new ListEntry();

	public EClassEntry(Entry iPackageName,String iType,String sName,Entry iName,IToken templateTypeName,Entry iParent,ListEntry iInterfaces,ListEntry iVariables,ListEntry iMethods,ContextEntry iContext){
		isInner = false;
		packageName = iPackageName;
		name = new StringEntry(sName);
		completeName = new ListEntry(new ElementEntry(ClasswiseGenerator.retrieveClassElement,new ListEntry(new StringEntry(sName))));
		completeUnsulliedName = new ListEntry(iName);
		completeName.setDelimiter(".");
		completeUnsulliedName.setDelimiter(".");
		IImportable establishedImportable = (IImportable)Generators.classwise.getType(this.getFullName());
		importPackage = establishedImportable.getImportPackage();
		if((packageName != null)){
			importPackage.addPackage(packageName,completeUnsulliedName);
			externalPackageName = new ElementEntry(ExternalGenerator.exactEntryElement,new ListEntry(packageName));
		}
		else {
			externalPackageName = new StringEntry("null");
		}
		importPackage.setIsInner(false);
		classType = new StringEntry(iType);
		if((iType.contains("interface"))){
			this.setIsInterface(true);
		}
		externalName = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(iName));
		if((templateTypeName != null)){
			externalTemplateTypeName = new ElementEntry(ExternalGenerator.asTemplateElement,new ListEntry(new StringEntry(templateTypeName.getString())));
		}
		else {
			externalTemplateTypeName = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry("")));
		}
		if((iParent != null)){
			parent = new ElementEntry(ClasswiseGenerator.extendsElement,new ListEntry(iParent));
			externalParent = new ElementEntry(ExternalGenerator.extendsElement,new ListEntry(iParent));
			externalParentEntry = iParent;
			IImportable iParentType = (IImportable)iParent;
			this.addImports(iParentType.getImportPackage());
		}
		else {
			parent = new ListEntry();
			externalParent = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry("")));
			externalParentEntry = new StringEntry("null");
		}
		if((!iInterfaces.isEmpty())){
			interfaces = new ElementEntry(ClasswiseGenerator.implementsElement,new ListEntry(iInterfaces));
			ListEntry extItrf = new ListEntry();
			for(Entry itrf:iInterfaces){
				IImportable iIntefaceType = (IImportable)itrf;
				this.addImports(iIntefaceType.getImportPackage());
				extItrf.add(itrf);
			}
			extItrf.setDelimiter(".get(builder);builder.append(\",\"); ");
			externalInterfaces = new ElementEntry(ExternalGenerator.implementsElement,new ListEntry(extItrf));
		}
		else {
			interfaces = new ListEntry();
			externalInterfaces = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry("")));
		}
		externalInterfacesEntry = new ElementEntry(ExternalGenerator.asEntryListElement,new ListEntry(iInterfaces));
		for(Entry ev:iVariables){
			IInnerable i = (IInnerable)ev;
			if((i.getIsInner())){
				IVariableEntry niv = (IVariableEntry)ev;
				this.addVariable(niv);
			}
			else {
				EVariableEntry nev = (EVariableEntry)ev;
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
		externalConstructorVariables.setDelimiter(",");
		externalConstructorMethods.setDelimiter(",");
		externalConstructorSubClass.setDelimiter(",");
		getHeader = new IExactEntry(new TabEntry(2,new ListEntry(new ElementEntry(ExternalGenerator.getCompleteHeaderElement,new ListEntry(asStatic,classType,externalName,externalTemplateTypeName)))));
		asFile = new IExactEntry(new ElementEntry(ExternalGenerator.declareClassElement,new ListEntry(name,name,internalVariables,internalMethods,internalSubClasses,externalSubClasses,externalPackageName,importPackage.get("EXTERNAL"),externalName,new QuoteEntry(iType),externalParentEntry,externalInterfacesEntry,getHeader,externalConstructorVariables,externalConstructorMethods,externalConstructorSubClass)));
		Entry classwizeImports = (Entry)Generators.classwise.getInternalImports();
		asInternalFile = new IExactEntry(new ElementEntry(ExternalGenerator.declareOutputClassElement,new ListEntry(classwizeImports,name,internalVariables,internalMethods,internalSubClasses,externalSubClasses,externalPackageName,importPackage.get("EXTERNAL"),externalName,new QuoteEntry(iType),externalParentEntry,externalInterfacesEntry,getHeader,externalConstructorVariables,externalConstructorMethods,externalConstructorSubClass)));
		asFileVariable = new IExactEntry(new ElementEntry(ExternalGenerator.declareNewClassElement,new ListEntry(name)));
		asArgument = new IExactEntry(new ElementEntry(ExternalGenerator.declareClassAsArgumentElement,new ListEntry(externalPackageName,importPackage.get("EXTERNAL"),externalName,new QuoteEntry(iType),externalParentEntry,externalInterfacesEntry,getHeader,externalConstructorVariables,externalConstructorMethods,externalConstructorSubClass)));
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
	public String getFullName() {
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
	public Boolean getIsInterface(){
		return isInterface;
	}
	public void setIsInterface(Boolean newIsInterface) {
		newIsInterface = newIsInterface;
	}
	public Boolean getIsGlobal(){
		return isGlobal;
	}
	public void setIsGlobal(Boolean newIsGlobal) {
		isGlobal = newIsGlobal;
	}
	public Entry getPackageName(){
		return packageName;
	}
	public Entry getExternalPackageName(){
		return externalPackageName;
	}	public StringEntry getAsStatic(){
		return asStatic;
	}	public IExactEntry getAsFile(){
		return asFile;
	}	public IExactEntry getAsFileVariable(){
		return asFileVariable;
	}	public IExactEntry getAsInternalFile(){
		return asInternalFile;
	}	public IExactEntry getAsArgument(){
		return asArgument;
	}	public StringEntry getClassType(){
		return classType;
	}
	public Entry getExternalName(){
		return externalName;
	}
	public Entry getExternalTemplateTypeName(){
		return externalTemplateTypeName;
	}
	public Entry getExternalParent(){
		return externalParent;
	}
	public Entry getExternalParentEntry(){
		return externalParentEntry;
	}
	public Entry getExternalInterfaces(){
		return externalInterfaces;
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
	}	public ListEntry getExternalConstructorVariables(){
		return externalConstructorVariables;
	}	public ListEntry getExternalConstructorMethods(){
		return externalConstructorMethods;
	}	public ListEntry getExternalConstructorSubClass(){
		return externalConstructorSubClass;
	}	public Entry getExternalInterfacesEntry(){
		return externalInterfacesEntry;
	}	public IExactEntry getGetHeader(){
		return getHeader;
	}
	public Boolean getIsSubClass(){
		return isSubClass;
	}
	public Boolean getIsWeak(){
		return isWeak;
	}
	public Boolean getHasOutput(){
		return hasOutput;
	}
	public ListEntry getCompleteUnsulliedName(){
		return completeUnsulliedName;
	}
	public void addVariable(IVariableEntry i){
		internalVariables.add(new TabEntry(2,new ListEntry(new ElementEntry(InternalGenerator.declareMemberCompleteElement,new ListEntry(i)))));
		Entry iAssignment = (Entry)i.getAssignment();
		if((iAssignment == null)){
			Entry iType = (Entry)i.getType();
			i.setAssignment(new INewObjEntry(iType,new ListEntry()));
		}
	}
	public void addVariable(EVariableEntry e){
		Entry eName = (Entry)e.getName();
		Entry eAssignment = (Entry)e.getAssignment();
		if((eAssignment == null)){
			Entry eType = (Entry)e.getType();
			e.setAssignment(new ENewObjEntry(eType,new ListEntry()));
		}
		externalVariables.add(new TabEntry(2,new ListEntry(new ElementEntry(InternalGenerator.declareMemberClassElement,new ListEntry(eName,eName,new ElementEntry(ExternalGenerator.declareMemberElement,new ListEntry(e_tabs,e)))))));
		externalConstructorVariables.add(e);
		this.addImports(e.getImportPackage());
	}
	public void addMethod(IMethodEntry i){
		internalMethods.add(new TabEntry(1,new ListEntry(i)));
	}
	public void addMethod(EMethodEntry e){
		Entry eName = (Entry)e.getName();
		e.setIsInterface(isInterface);
		externalMethods.add(new TabEntry(1,new ListEntry(new ElementEntry(InternalGenerator.declareMemberMethodElement,new ListEntry(eName,e)))));
		externalConstructorMethods.add(e);
		this.addImports(e.getImportPackage());
	}
	public void addSubClass(IClassEntry i){
		internalSubClasses.add(new TabEntry(1,new ListEntry(i)));
		i.setIsSubClass(completeName,true);
		ContextEntry theirContext = (ContextEntry)i.getContext();
		theirContext.setParentContext(context);
		this.addImports(i.getImportPackage());
	}
	public void addSubClass(EClassEntry e){
		Entry ePlainName = (Entry)e.getName();
		String eName = Generators.classwise.buildString(e.getName(),"Class");
		Entry eAsFile = (Entry)e.getAsFile();
		externalSubClasses.add(new TabEntry(1,new ListEntry(new ElementEntry(InternalGenerator.declareMemberClassElement,new ListEntry(ePlainName,ePlainName,eAsFile)))));
		externalConstructorSubClass.add(e);
		this.addImports(e.getImportPackage());
		e.setIsSubClass(completeName,completeUnsulliedName,true);
		ContextEntry theirContext = (ContextEntry)e.getContext();
		theirContext.setParentContext(context);
		StringBuilder nameBuilder = new StringBuilder();
		ePlainName.get(nameBuilder);
		Generators.clazz.addDefinedClassName(nameBuilder.toString());
	}
	public void setIsSubClass(Entry superCompleteName,Entry superUnsulliedName,Boolean newStatus){
		isSubClass = newStatus;
		if((isSubClass == true && isWeak != true)){
			asStatic.set("static");
		}
		else {
			asStatic.set("");
		}
		completeName.clear();
		completeName.add(superCompleteName);
		completeName.add(new ElementEntry(ClasswiseGenerator.classAsVariableElement,new ListEntry(name)));
		completeUnsulliedName.clear();
		completeUnsulliedName.add(superUnsulliedName);
	}
	public void setIsWeak(Boolean newIsWeak){
		isWeak = newIsWeak;
		if((isWeak == true)){
			asStatic.set("");
		}
	}
	public void get(StringBuilder builder){
		if((context == null)){
			tabs = 0;
		}
		else {
			tabs = context.getTab();
		}
		e_tabs.set(tabs.toString());
		if((name != null && isGlobal == true)){
			new ElementEntry(ClasswiseGenerator.retrieveClassElement,new ListEntry(completeName)).get(builder);
		}
		else if((name != null && isGlobal == false)){
			completeName.get(builder);
		}
	}
}