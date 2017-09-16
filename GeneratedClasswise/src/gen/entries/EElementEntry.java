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

public class EElementEntry implements Entry,IInnerable,IImportable,IContextualizable {
	public EElementEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private QuoteEntry preffix = null;
	private QuoteEntry suffix = null;
	private Entry value = null;

	public EElementEntry(String iPreffix,Entry iValue,String iSuffix,ContextEntry iContext){
		preffix = new QuoteEntry(iPreffix);
		suffix = new QuoteEntry(iSuffix);
		value = iValue;
		context = iContext;
		IImportable valueAsImportable = (IImportable)iValue;
		this.addImports(valueAsImportable.getImportPackage());
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
	}	public QuoteEntry getPreffix(){
		return preffix;
	}	public QuoteEntry getSuffix(){
		return suffix;
	}	public Entry getValue(){
		return value;
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
			new TabEntry(tabs,new ListEntry(new ElementEntry(ExternalGenerator.bodyElementElement,new ListEntry(preffix,suffix,value)))).get(builder);
		}
	}
}