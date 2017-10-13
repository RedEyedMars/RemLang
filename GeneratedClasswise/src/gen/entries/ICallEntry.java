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

public class ICallEntry implements Entry,IInnerable,IImportable {
	public ICallEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry subject = null;
	private Entry methodName = null;
	private ListEntry parameters = null;
	private ListEntry array_parameters = null;

	public ICallEntry(Entry iMethodName,ListEntry iParameters,ListEntry iArrayParameters){
		isInner = true;
		methodName = iMethodName;
		parameters = iParameters;
		array_parameters = iArrayParameters;
		if((iParameters != null)){
			for(Entry e:iParameters){
				IImportable i = (IImportable)e;
				this.addImports(i.getImportPackage());
			}
			parameters.setDelimiter(",");
		}
		if((iArrayParameters != null)){
			array_parameters.setDelimiter("][");
		}
	}
	public ICallEntry(Entry iSubject,Entry iMethodName,ListEntry iParameters,ListEntry iArrayParameters){
		subject = iSubject;
		IImportable subjectAsImportable = (IImportable)iSubject;
		methodName = iMethodName;
		parameters = iParameters;
		array_parameters = iArrayParameters;
		this.addImports(subjectAsImportable.getImportPackage());
		if((iParameters != null)){
			for(Entry e:iParameters){
				IImportable i = (IImportable)e;
				this.addImports(i.getImportPackage());
			}
			parameters.setDelimiter(",");
		}
		if((iArrayParameters != null)){
			array_parameters.setDelimiter("][");
		}
	}
	public ICallEntry(Entry iMethodName,ListEntry iParameters){
		isInner = true;
		methodName = iMethodName;
		parameters = iParameters;
		for(Entry e:iParameters){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
		}
		parameters.setDelimiter(",");
	}
	public ICallEntry(Entry iSubject,Entry iMethodName,ListEntry iParameters){
		subject = iSubject;
		IImportable subjectAsImportable = (IImportable)iSubject;
		methodName = iMethodName;
		parameters = iParameters;
		this.addImports(subjectAsImportable.getImportPackage());
		for(Entry e:iParameters){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
		}
		parameters.setDelimiter(",");
	}
	public ICallEntry(Entry iMethodName){
		methodName = iMethodName;
		IImportable subjectAsImporable = (IImportable)iMethodName;
		this.addImports(subjectAsImporable.getImportPackage());
	}
	public ICallEntry(Entry iSubject,Entry iMethodName){
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
	}	public ListEntry getArrayParameters(){
		return array_parameters;
	}
	public void get(StringBuilder builder){
		if((subject == null && parameters != null && array_parameters != null)){
			new ElementEntry(InternalGenerator.bodyCallWithoutSubjectWithArrayElement,new ListEntry(methodName,parameters,array_parameters)).get(builder);
		}
		else if((subject != null && parameters != null && array_parameters != null)){
			new ElementEntry(InternalGenerator.bodyCallWithSubjectWithArrayElement,new ListEntry(subject,methodName,parameters,array_parameters)).get(builder);
		}
		else if((subject == null && parameters == null && array_parameters != null)){
			new ElementEntry(InternalGenerator.bodyAccessWithoutSubjectWithArrayElement,new ListEntry(methodName,array_parameters)).get(builder);
		}
		else if((subject != null && parameters == null && array_parameters != null)){
			new ElementEntry(InternalGenerator.bodyAccessWithSubjectWithArrayElement,new ListEntry(subject,methodName,array_parameters)).get(builder);
		}
		else if((subject == null && parameters != null && array_parameters == null)){
			new ElementEntry(InternalGenerator.bodyCallWithoutSubjectElement,new ListEntry(methodName,parameters)).get(builder);
		}
		else if((subject != null && parameters != null && array_parameters == null)){
			new ElementEntry(InternalGenerator.bodyCallWithSubjectElement,new ListEntry(subject,methodName,parameters)).get(builder);
		}
		else if((subject == null && parameters == null && array_parameters == null)){
			new ElementEntry(InternalGenerator.bodyAccessWithoutSubjectElement,new ListEntry(methodName)).get(builder);
		}
		else if((subject != null && parameters == null && array_parameters == null)){
			new ElementEntry(InternalGenerator.bodyAccessWithSubjectElement,new ListEntry(subject,methodName)).get(builder);
		}
	}
}