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

public class ENameVarEntry implements Entry,IInnerable,IImportable {
	public ENameVarEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry first = null;
	private Entry second = null;
	private Boolean isConcat = false;

	public ENameVarEntry(Entry iValue){
		first = iValue;
	}
	public ENameVarEntry(Entry iFirst,Entry iSecond){
		first = iFirst;
		second = iSecond;
	}
	public ENameVarEntry(Entry iFirst,String concat,Entry iSecond){
		first = iFirst;
		second = iSecond;
		isConcat = true;
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
	}	public Entry getFirst(){
		return first;
	}	public Entry getSecond(){
		return second;
	}
	public Boolean getIsConcat(){
		return isConcat;
	}
	public void get(StringBuilder builder){
		if((second == null)){
			new ElementEntry(ExternalGenerator.bodyNameElement,new ListEntry(first)).get(builder);
		}
		else if((second != null && isConcat == true)){
			new ElementEntry(ExternalGenerator.bodyNameElement,new ListEntry(new ElementEntry(ExternalGenerator.bodyConcatElement,new ListEntry(first,second)))).get(builder);
		}
		else if((second != null && isConcat == false)){
			new ElementEntry(ExternalGenerator.bodyNameElement,new ListEntry(new ElementEntry(ClasswiseGenerator.accessElement,new ListEntry(first,second)))).get(builder);
		}
	}
}