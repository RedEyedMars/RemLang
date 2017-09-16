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

public class ECallEntry implements Entry,IInnerable,IImportable {
	public ECallEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry subject = null;
	private Entry methodName = null;
	private ListEntry parameters = null;

	public ECallEntry(Entry iMethodName,ListEntry iParameters){
		methodName = iMethodName;
		parameters = iParameters;
		for(Entry e:iParameters){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
		}
		parameters.setDelimiter(",");
	}
	public ECallEntry(Entry iSubject,Entry iMethodName,ListEntry iParameters){
		subject = iSubject;
		IImportable subjectAsImporable = (IImportable)iSubject;
		methodName = iMethodName;
		parameters = iParameters;
		this.addImports(subjectAsImporable.getImportPackage());
		for(Entry e:iParameters){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
		}
		parameters.setDelimiter(",");
	}
	public ECallEntry(Entry iMethodName){
		methodName = iMethodName;
	}
	public ECallEntry(Entry iSubject,Entry iMethodName){
		subject = iSubject;
		IImportable subjectAsImporable = (IImportable)iSubject;
		methodName = iMethodName;
		this.addImports(subjectAsImporable.getImportPackage());
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
	}	public Entry getMethodName(){
		return methodName;
	}	public ListEntry getParameters(){
		return parameters;
	}
	public void get(StringBuilder builder){
		if((subject == null && parameters != null)){
			new ElementEntry(ExternalGenerator.bodyCallWithoutSubjectElement,new ListEntry(methodName,parameters)).get(builder);
		}
		else if((subject != null && parameters != null)){
			new ElementEntry(ExternalGenerator.bodyCallWithSubjectElement,new ListEntry(subject,methodName,parameters)).get(builder);
		}
		else if((subject == null && parameters == null)){
			new ElementEntry(ExternalGenerator.bodyAccessWithoutSubjectElement,new ListEntry(methodName)).get(builder);
		}
		else if((subject != null && parameters == null)){
			new ElementEntry(ExternalGenerator.bodyAccessWithSubjectElement,new ListEntry(subject,methodName)).get(builder);
		}
	}
}