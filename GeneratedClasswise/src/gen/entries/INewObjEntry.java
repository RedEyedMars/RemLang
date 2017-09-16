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

public class INewObjEntry implements Entry,IInnerable,IImportable {
	public INewObjEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry className = null;
	private ListEntry parameters = null;

	public INewObjEntry(Entry iClassName,ListEntry iParameters){
		isInner = true;
		className = iClassName;
		parameters = iParameters;
		IImportable classNameAsImportable = (IImportable)className;
		this.addImports(classNameAsImportable.getImportPackage());
		for(Entry e:iParameters){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
		}
		parameters.setDelimiter(",");
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
	}	public Entry getClassName(){
		return className;
	}	public ListEntry getParameters(){
		return parameters;
	}
	public void get(StringBuilder builder){
		if((className != null)){
			new ElementEntry(InternalGenerator.bodyNewObjElement,new ListEntry(className,parameters)).get(builder);
		}
	}
}