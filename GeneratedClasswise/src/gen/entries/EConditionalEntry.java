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

public class EConditionalEntry implements Entry,IInnerable,IImportable,IContextualizable {
	public EConditionalEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private StringEntry name = null;
	private Entry header = null;
	private ListEntry conditionalBody = null;
	private EInnerCallEntry asInnerCall = null;

	public EConditionalEntry(String iName,Entry iHeader,ListEntry iBody,ContextEntry iContext){
		name = new StringEntry(iName);
		header = iHeader;
		conditionalBody = new ListEntry();
		context = iContext;
		if((header != null)){
			IImportable headerAsImportable = (IImportable)iHeader;
			this.addImports(headerAsImportable.getImportPackage());
		}
		ContextEntry bodyContext = new ContextEntry(iContext);
		String semicolon = ";";
		for(Entry e:iBody){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
			conditionalBody.add(e);
		}
		conditionalBody.setDelimiter(",\n/*BODY*/\t\t\t\t");
	}
	public EConditionalEntry(String iName,Entry iHeader,EInnerCallEntry iBody,ContextEntry iContext){
		name = new StringEntry(iName);
		header = iHeader;
		conditionalBody = new ListEntry();
		asInnerCall = iBody;
		context = iContext;
		if((header != null)){
			IImportable headerAsImportable = (IImportable)iHeader;
			this.addImports(headerAsImportable.getImportPackage());
		}
		ContextEntry bodyContext = new ContextEntry(iContext);
		String semicolon = ";";
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
	}	public StringEntry getName(){
		return name;
	}	public Entry getHeader(){
		return header;
	}	public ListEntry getConditionalBody(){
		return conditionalBody;
	}	public EInnerCallEntry getAsInnerCall(){
		return asInnerCall;
	}
	public void get(StringBuilder builder){
		if((context == null)){
			tabs = 0;
		}
		else {
			tabs = context.getTab();
		}
		e_tabs.set(tabs.toString());
		if((header != null && asInnerCall != null)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(ExternalGenerator.bodyConditionalElement,new ListEntry(name,header,asInnerCall)))).get(builder);
		}
		else if((header == null && asInnerCall != null)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(ExternalGenerator.bodyConditionalWithoutHeaderElement,new ListEntry(name,asInnerCall)))).get(builder);
		}
		else if((header != null && asInnerCall == null)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(ExternalGenerator.bodyConditionalElement,new ListEntry(name,header,new ElementEntry(ExternalGenerator.bodyBodyElement,new ListEntry(conditionalBody)))))).get(builder);
		}
		else if((header == null && asInnerCall == null)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(ExternalGenerator.bodyConditionalWithoutHeaderElement,new ListEntry(name,new ElementEntry(ExternalGenerator.bodyBodyElement,new ListEntry(conditionalBody)))))).get(builder);
		}
	}
}