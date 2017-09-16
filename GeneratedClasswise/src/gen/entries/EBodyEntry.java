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

public class EBodyEntry implements Entry,IInnerable,IImportable {
	public EBodyEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private ListEntry realBody = null;

	public EBodyEntry(ListEntry iBody){
		realBody = new ListEntry();
		for(Entry e:iBody){
			IImportable i = (IImportable)e;
			this.addImports(i.getImportPackage());
			realBody.add(e);
		}
		realBody.setDelimiter(",\n/*BODY*/\t\t\t\t");
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
	}	public ListEntry getRealBody(){
		return realBody;
	}
	public void get(StringBuilder builder){
		if((realBody != null)){
			new ElementEntry(ExternalGenerator.bodyBodyElement,new ListEntry(realBody)).get(builder);
		}
	}
}