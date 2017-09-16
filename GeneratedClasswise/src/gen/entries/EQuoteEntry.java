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

public class EQuoteEntry implements Entry,IInnerable,IImportable {
	public EQuoteEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry value = null;

	public EQuoteEntry(Entry iValue){
		value = iValue;
		isInner = true;
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
	}	public Entry getValue(){
		return value;
	}
	public void setValue(Entry newValue){
		value = newValue;
	}
	public void get(StringBuilder builder){
		if((value != null)){
			new ElementEntry(ExternalGenerator.bodyQuoteElement,new ListEntry(value)).get(builder);
		}
	}
}