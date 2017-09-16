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

public class IBodyEntry implements Entry,IInnerable,IImportable,IContextualizable {
	public IBodyEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private ListEntry body = null;

	public IBodyEntry(ListEntry iBody,ContextEntry iContext){
		isInner = true;
		body = new ListEntry();
		context = iContext;
		String semicolon = ";";
		for(Entry e:iBody){
			IImportable i = (IImportable)e;
			IContextualizable c = (IContextualizable)e;
			c.setContext(context);
			this.addImports(i.getImportPackage());
			body.add(e);
		}
		body.setDelimiter("");
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
	}	public ListEntry getBody(){
		return body;
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
			new TabEntry(tabs,new ListEntry(body)).get(builder);
		}
	}
}