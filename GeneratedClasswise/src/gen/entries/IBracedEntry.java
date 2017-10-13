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

public class IBracedEntry implements Entry,IInnerable,IImportable {
	public IBracedEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry subject = null;
	private Entry after = null;
	private Entry operator = null;

	public IBracedEntry(Entry iSubject,Entry iOperator,Entry iAfterSubject){
		isInner = true;
		subject = iSubject;
		after = iAfterSubject;
		operator = iOperator;
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
	}	public Entry getAfter(){
		return after;
	}	public Entry getOperator(){
		return operator;
	}
	public void get(StringBuilder builder){
		if((subject != null && after == null && operator == null)){
			new ElementEntry(InternalGenerator.bodyBracedStatementElement,new ListEntry(subject)).get(builder);
		}
		else if((subject != null && after != null && operator == null)){
			new ElementEntry(InternalGenerator.bodyCastedStatementElement,new ListEntry(subject,after)).get(builder);
		}
		else if((subject != null && operator != null && after != null)){
			new ElementEntry(InternalGenerator.bodyBracedOperatorStatementElement,new ListEntry(subject,operator,after)).get(builder);
		}
	}
}