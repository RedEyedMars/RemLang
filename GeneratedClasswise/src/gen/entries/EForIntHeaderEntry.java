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

public class EForIntHeaderEntry implements Entry,IInnerable,IImportable {
	public EForIntHeaderEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry left = null;
	private Entry variableName = null;
	private Entry right = null;
	private QuoteEntry operator = null;

	public EForIntHeaderEntry(Entry iLeft,String iOperator,Entry iRight){
		left = (Entry)iLeft;
		right = (Entry)iRight;
		EVariableEntry leftAsVariable = (EVariableEntry)iLeft;
		leftAsVariable.setAssignment(new EExactEntry(new StringEntry("0")));
		variableName = leftAsVariable.getName();
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
	}	public Entry getVariableName(){
		return variableName;
	}	public Entry getRight(){
		return right;
	}	public QuoteEntry getOperator(){
		return operator;
	}
	public void get(StringBuilder builder){
		if((right != null)){
			new ElementEntry(ExternalGenerator.bodyForIntHeaderElement,new ListEntry(left,operator,variableName,right,variableName)).get(builder);
		}
	}
}