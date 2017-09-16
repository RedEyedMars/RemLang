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

public class ECaseEntry implements Entry,IInnerable,IImportable,IContextualizable {
	public ECaseEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private Entry header = null;
	private ListEntry caseBody = null;
	private EInnerCallEntry asInnerCall = null;

	public ECaseEntry(Entry iHeader,ListEntry iBody,ContextEntry iContext){
		header = iHeader;
		caseBody = new ListEntry();
		context = iContext;
		if((header != null)){
			IImportable headerAsImportable = (IImportable)iHeader;
			this.addImports(headerAsImportable.getImportPackage());
		}
		ContextEntry bodyContext = new ContextEntry(iContext);
		for(Entry e:iBody){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
			caseBody.add(e);
		}
		caseBody.setDelimiter(",\n/*BODY*/\t\t\t\t");
	}
	public ECaseEntry(Entry iHeader,EInnerCallEntry iBody,ContextEntry iContext){
		header = iHeader;
		caseBody = new ListEntry();
		asInnerCall = iBody;
		context = iContext;
		if((header != null)){
			IImportable headerAsImportable = (IImportable)iHeader;
			this.addImports(headerAsImportable.getImportPackage());
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
	}	public Entry getHeader(){
		return header;
	}	public ListEntry getCaseBody(){
		return caseBody;
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
		if((asInnerCall != null)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(ExternalGenerator.bodyCaseElement,new ListEntry(header,asInnerCall)))).get(builder);
		}
		else if((asInnerCall == null)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(ExternalGenerator.bodyCaseElement,new ListEntry(header,new ElementEntry(ExternalGenerator.bodyBodyElement,new ListEntry(caseBody)))))).get(builder);
		}
	}
}