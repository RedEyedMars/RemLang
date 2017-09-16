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

public class ImportHolderEntry implements Entry,IImportable {
	public ImportHolderEntry getSelf(){
		return this;
	}


	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();

	public ImportHolderEntry(){
		importPackage.setIsAnon(false);
	}
	public ImportHolderEntry(Entry iPackageName,Entry iName){
		importPackage.addPackage(new ISingleImportEntry(new ListEntry(iPackageName),new ListEntry(iName)));
		importPackage.addPackage(new ESingleImportEntry(new ListEntry(new ElementEntry(ExternalGenerator.bodyExactElement,new ListEntry(iPackageName))),new ListEntry(iName)));
		importPackage.setIsAnon(true);
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
	public void get(StringBuilder builder){
	}
}