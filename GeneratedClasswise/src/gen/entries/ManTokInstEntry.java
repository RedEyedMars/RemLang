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

public class ManTokInstEntry implements Entry,IInnerable,IImportable,IContextualizable {
	public ManTokInstEntry getSelf(){
		return this;
	}

	public static final Integer SOLO = 0;
	public static final Integer MULTI = 1;

	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ContextEntry context = (ContextEntry)null;
	private Integer tabs = 0;
	private StringEntry e_tabs = (StringEntry)new StringEntry("0");
	private Integer mode = MULTI;
	private Entry subject = null;
	private ListEntry body = null;
	private String tokenName = null;
	private String elementName = null;

	public ManTokInstEntry(Entry iSubject,String iElementName,String iTokenName,ListEntry iBody,ContextEntry iContext){
		isInner = true;
		subject = iSubject;
		tokenName = iTokenName;
		elementName = iElementName;
		context = iContext;
		ContextEntry bodyContext = new ContextEntry(iContext);
		String semicolon = ";";
		body = new ListEntry();
		for(Entry e:iBody){
			IImportable i = (IImportable)e;
			IContextualizable c = (IContextualizable)e;
			c.setContext(bodyContext);
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
	}
	public Integer getMode(){
		return mode;
	}	public Entry getSubject(){
		return subject;
	}	public ListEntry getBody(){
		return body;
	}
	public String getTokenName(){
		return tokenName;
	}
	public String getElementName(){
		return elementName;
	}
	public void setMode(Integer newMode){
		mode = newMode;
	}
	public void get(StringBuilder builder){
		if((context == null)){
			tabs = 0;
		}
		else {
			tabs = context.getTab();
		}
		if((mode == SOLO)){
			body.get(builder);
		}
		else if((mode == MULTI)){
			new TabEntry(tabs,new ListEntry(new ElementEntry(InternalGenerator.manipulateOneMultiTokenElement,new ListEntry(new StringEntry(elementName),new StringEntry(tokenName),new StringEntry(elementName),subject,new StringEntry(elementName),body)))).get(builder);
		}
		if((mode == MULTI)){
			new TabEntry(tabs,new ListEntry(new StringEntry("}"))).get(builder);
		}
	}
}