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

public class IVariableEntry implements Entry,IInnerable,IImportable,INameable,IFinalizable,IStatickable,IInlinelistable,IArraytypeable {
	public IVariableEntry getSelf(){
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

	public IVariableEntry(Entry iType,Entry iName,Entry iAssignment){
		isInner = true;
		name = iName;
		type = iType;
		isFinal = true;
		this.setAssignment(iAssignment);
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
		importPackage.clear();
		IImportable typeAsImportable = (IImportable)type;
		assignment = newAssignment;
		this.addImports(typeAsImportable.getImportPackage());
		if((assignment != null)){
			IImportable assignmentAsImportable = (IImportable)assignment;
			this.addImports(assignmentAsImportable.getImportPackage());
		}
	}
	public void get(StringBuilder builder){
		if((assignment != null && isFinal == false && isStatic == false)){
			new ElementEntry(InternalGenerator.declareVariableWithAssignmentNonFinalElement,new ListEntry(type,arrayType,name,assignment)).get(builder);
		}
		else if((assignment != null && isFinal == true && isStatic == false)){
			new ElementEntry(InternalGenerator.declareVariableWithAssignmentFinalElement,new ListEntry(type,arrayType,name,assignment)).get(builder);
		}
		else if((assignment == null && isFinal == false && isStatic == false)){
			new ElementEntry(InternalGenerator.declareVariableWithoutAssignmentNonFinalElement,new ListEntry(type,arrayType,name)).get(builder);
		}
		else if((assignment == null && isFinal == true && isStatic == false)){
			new ElementEntry(InternalGenerator.declareVariableWithoutAssignmentFinalElement,new ListEntry(type,arrayType,name)).get(builder);
		}
		else if((assignment != null && isFinal == false && isStatic == true)){
			new ElementEntry(InternalGenerator.declareStaticVariableWithAssignmentNonFinalElement,new ListEntry(type,arrayType,name,assignment)).get(builder);
		}
		else if((assignment != null && isFinal == true && isStatic == true)){
			new ElementEntry(InternalGenerator.declareStaticVariableWithAssignmentFinalElement,new ListEntry(type,arrayType,name,assignment)).get(builder);
		}
		else if((assignment == null && isFinal == false && isStatic == true)){
			new ElementEntry(InternalGenerator.declareStaticVariableWithoutAssignmentNonFinalElement,new ListEntry(type,arrayType,name)).get(builder);
		}
		else if((assignment == null && isFinal == true && isStatic == true)){
			new ElementEntry(InternalGenerator.declareStaticVariableWithoutAssignmentFinalElement,new ListEntry(type,arrayType,name)).get(builder);
		}
	}
}