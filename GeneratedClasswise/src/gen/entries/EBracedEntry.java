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

public class EBracedEntry implements Entry,IInnerable,IImportable {
	public EBracedEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry subject = null;
	private Entry operator = null;
	private Entry after = null;

	public EBracedEntry(Entry iSubject,Entry iOperator,Entry iAfterSubject){
		isInner = false;
		subject = iSubject;
		operator = iOperator;
		after = iAfterSubject;
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
	}	public Entry getSubject(){
		return subject;
	}	public Entry getOperator(){
		return operator;
	}	public Entry getAfter(){
		return after;
	}
	public void get(StringBuilder builder){
		if((subject != null && after == null && operator == null)){
			new ElementEntry(ExternalGenerator.bodyBracedStatementElement,new ListEntry(subject)).get(builder);
		}
		else if((subject != null && after != null && operator == null)){
			new ElementEntry(ExternalGenerator.bodyCastedStatementElement,new ListEntry(subject,after)).get(builder);
		}
		else if((subject != null && after != null && operator != null)){
			new ElementEntry(ExternalGenerator.bodyBracedOperatorStatementElement,new ListEntry(subject,operator,after)).get(builder);
		}
	}
}