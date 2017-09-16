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

public class EOperatorEntry implements Entry,IInnerable,IImportable {
	public EOperatorEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry left = null;
	private Entry right = null;
	private QuoteEntry operator = null;

	public EOperatorEntry(Entry iLeft){
		left = iLeft;
		IImportable leftAsImportable = (IImportable)iLeft;
		this.addImports(leftAsImportable.getImportPackage());
	}
	public EOperatorEntry(Entry iLeft,String iOperator,Entry iRight){
		left = (Entry)iLeft;
		right = (Entry)iRight;
		operator = new QuoteEntry(iOperator);
		IImportable leftAsImport = (IImportable)iLeft;
		IImportable rightAsImport = (IImportable)iRight;
		this.addImports(leftAsImport.getImportPackage());
		this.addImports(rightAsImport.getImportPackage());
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
	}	public QuoteEntry getOperator(){
		return operator;
	}
	public void get(StringBuilder builder){
		if((right == null)){
			new ElementEntry(ExternalGenerator.bodyNameElement,new ListEntry(left)).get(builder);
		}
		else if((right != null)){
			new ElementEntry(ExternalGenerator.bodyOperatorElement,new ListEntry(operator,left,right)).get(builder);
		}
	}
}