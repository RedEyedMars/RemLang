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

public class IOperatorEntry implements Entry,IInnerable,IImportable {
	public IOperatorEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry left = null;
	private Entry right = null;
	private StringEntry operator = null;

	public IOperatorEntry(Entry iLeft){
		isInner = true;
		left = iLeft;
		IImportable leftAsImportable = (IImportable)iLeft;
		this.addImports(leftAsImportable.getImportPackage());
	}
	public IOperatorEntry(Entry iLeft,String iOperator,Entry iRight){
		isInner = true;
		left = iLeft;
		right = iRight;
		operator = new StringEntry(iOperator);
		IImportable leftAsImportable = (IImportable)iLeft;
		IImportable rightAsImportable = (IImportable)iRight;
		this.addImports(leftAsImportable.getImportPackage());
		this.addImports(rightAsImportable.getImportPackage());
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
	}	public Entry getLeft(){
		return left;
	}	public Entry getRight(){
		return right;
	}	public StringEntry getOperator(){
		return operator;
	}
	public void get(StringBuilder builder){
		if((right == null)){
			new ElementEntry(InternalGenerator.bodyEntryElement,new ListEntry(left)).get(builder);
		}
		else if((right != null)){
			new ElementEntry(InternalGenerator.bodyOperatorElement,new ListEntry(left,operator,right)).get(builder);
		}
	}
}