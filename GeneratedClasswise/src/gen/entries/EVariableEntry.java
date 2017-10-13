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

public class EVariableEntry implements Entry,IInnerable,IImportable,INameable,IFinalizable,IStatickable,IInlinelistable,IArraytypeable {
	public EVariableEntry getSelf(){
		return this;
	}


	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Entry name = (Entry)new StringEntry("");
	private ListEntry completeName = (ListEntry)new ListEntry();
	private Boolean isFinal = false;
	private Boolean isStatic = false;
	private Boolean isInlineList = false;
	private ListEntry arrayType = (ListEntry)new ListEntry();
	private Entry type = null;
	private Entry assignment = null;

	public EVariableEntry(Entry iType,Entry iName,Entry iAssignment){
		name = iName;
		type = iType;
		IImportable typeAsImportable = (IImportable)iType;
		assignment = iAssignment;
		this.setIsFinal(true);
		this.addImports(typeAsImportable.getImportPackage());
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
	}
	public Entry getName(){
		return name;
	}
	public ListEntry getCompleteName(){
		return completeName;
	}
	public String getFullName() {
		StringBuilder builder = new StringBuilder();
		completeName.get(builder);
		return builder.toString();
	}
	public Boolean getIsFinal(){
		return isFinal;
	}
	public void setIsFinal(Boolean newIsFinal) {
		isFinal = newIsFinal;
	}
	public Boolean getIsStatic(){
		return isStatic;
	}
	public void setIsStatic(Boolean newIsStatic) {
		isStatic = newIsStatic;
	}
	public Boolean getIsInlineList(){
		return isInlineList;
	}
	public void setIsInlineList(Boolean newIsInlineList) {
		isInlineList = newIsInlineList;
	}
	public ListEntry getArrayType(){
		return arrayType;
	}
	public void setArrayType(ListEntry newArrayType) {
		arrayType = newArrayType;
	}	public Entry getType(){
		return type;
	}	public Entry getAssignment(){
		return assignment;
	}
	public void setAssignment(Entry newAssignment){
		assignment = newAssignment;
		if((assignment != null)){
			IImportable assignmentAsImportable = (IImportable)assignment;
			this.addImports(assignmentAsImportable.getImportPackage());
		}
	}
	public void get(StringBuilder builder){
		if((isInlineList == true)){
			new ElementEntry(ExternalGenerator.declareVariableAsInlineListElement,new ListEntry(type,arrayType,name)).get(builder);
		}
		else if((assignment != null && isStatic == false && isFinal == true)){
			new ElementEntry(ExternalGenerator.declareVariableWithAssignmentFinalElement,new ListEntry(type,arrayType,name,assignment)).get(builder);
		}
		else if((assignment != null && isStatic == false && isFinal == false)){
			new ElementEntry(ExternalGenerator.declareVariableWithAssignmentNonFinalElement,new ListEntry(type,arrayType,name,assignment)).get(builder);
		}
		else if((assignment == null && isFinal == true && isStatic == false)){
			new ElementEntry(ExternalGenerator.declareVariableWithoutAssignmentFinalElement,new ListEntry(type,arrayType,name)).get(builder);
		}
		else if((assignment == null && isFinal == false && isStatic == false)){
			new ElementEntry(ExternalGenerator.declareVariableWithoutAssignmentNonFinalElement,new ListEntry(type,arrayType,name)).get(builder);
		}
		else if((assignment != null && isStatic == true)){
			new ElementEntry(ExternalGenerator.declareStaticVariableWithAssignmentElement,new ListEntry(type,arrayType,name,assignment)).get(builder);
		}
		else if((assignment == null && isFinal == false && isStatic == true)){
			new ElementEntry(ExternalGenerator.declareStaticVariableWithoutAssignmentNonFinalElement,new ListEntry(type,arrayType,name)).get(builder);
		}
		else if((assignment == null && isFinal == true && isStatic == true)){
			new ElementEntry(ExternalGenerator.declareStaticVariableWithoutAssignmentFinalElement,new ListEntry(type,arrayType,name)).get(builder);
		}
	}
}