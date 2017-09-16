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

public class ITypeVarEntry implements Entry,IInnerable,IImportable,ICanAddSubClass,IVariablizable {
	public ITypeVarEntry getSelf(){
		return this;
	}

	public static final Integer DEFAULT = 0;
	public static final Integer CONCAT = 1;
	public static final Integer ACCESS_CLASS = 2;
	public static final Integer ACCESS_METHOD = 3;
	public static final Integer ACCESS_DEFAULT = 4;

	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Boolean isVariable = true;
	private ListEntry sansTemp = new ListEntry();
	private ListEntry first = null;
	private Entry second = null;
	private Integer mode = DEFAULT;
	private ListEntry templateParameters = new ListEntry();

	public ITypeVarEntry(){
		isInner = true;
		first = new ListEntry();
		first.setDelimiter(".");
		sansTemp.setDelimiter(".");
	}
	public ITypeVarEntry(Entry iValue){
		isInner = true;
		first = new ListEntry(iValue);
		sansTemp = new ListEntry(iValue);
		first.setDelimiter(".");
		sansTemp.setDelimiter(".");
	}
	public ITypeVarEntry(Entry iValue,ListEntry iTemplateParameters,IImportable importType){
		isInner = true;
		first = new ListEntry(iValue);
		sansTemp = new ListEntry(iValue);
		if((importType != null)){
			this.addImports(importType.getImportPackage());
		}
		templateParameters = iTemplateParameters;
		first.setDelimiter(".");
		sansTemp.setDelimiter(".");
	}
	public ITypeVarEntry(Entry iFirst,String operator,Entry iSecond){
		isInner = true;
		IImportable firstAsImportable = (IImportable)iFirst;
		IImportable secondAsImportable = (IImportable)iSecond;
		this.addImports(firstAsImportable.getImportPackage());
		this.addImports(secondAsImportable.getImportPackage());
		first = new ListEntry(iFirst);
		sansTemp = new ListEntry(iFirst);
		second = iSecond;
		if((operator.equals("+"))){
			mode = CONCAT;
		}
		else {
			if((operator.equals(".*"))){
				mode = ACCESS_METHOD;
			}
			else {
				if((operator.equals("."))){
					mode = ACCESS_CLASS;
				}
				if((operator.equals(".."))){
					mode = ACCESS_DEFAULT;
				}
			}
		}
		first.setDelimiter(".");
		sansTemp.setDelimiter(".");
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
	public void addSubClass(Entry newSubType){
		ITypeVarEntry newTypeVar = (ITypeVarEntry)newSubType;
		sansTemp.add(newTypeVar.getSansTemp());
		StringBuilder builder = new StringBuilder();
		sansTemp.get(builder);
		IImportable importType = (IImportable)Generators.classwise.getType(builder.toString());
		this.addImports(importType.getImportPackage());
		first.add(newSubType);
	}
	public Boolean getIsVariable(){
		return isVariable;
	}
	public void setIsVariable(Boolean newIsVariable) {
		isVariable = newIsVariable;
	}	public ListEntry getSansTemp(){
		return sansTemp;
	}	public ListEntry getFirst(){
		return first;
	}	public Entry getSecond(){
		return second;
	}
	public Integer getMode(){
		return mode;
	}	public ListEntry getTemplateParameters(){
		return templateParameters;
	}
	public void get(StringBuilder builder){
		if((isVariable == true)){
			isVariable = false;
			StringBuilder checkBuilder = new StringBuilder();
			this.get(checkBuilder);
			Set<String> definedClassNames = (Set<String>)Generators.clazz.getDefinedClassNames();
			if((definedClassNames.contains(checkBuilder.toString()))){
				isVariable = true;
			}
		}
		if((second == null && templateParameters.isEmpty() && isVariable == false && mode == DEFAULT)){
			new ElementEntry(InternalGenerator.bodyNameElement,new ListEntry(first)).get(builder);
		}
		else if((second == null && templateParameters.isEmpty() && isVariable == false && mode == ACCESS_DEFAULT)){
			new ElementEntry(InternalGenerator.bodyNameElement,new ListEntry(new ElementEntry(ClasswiseGenerator.accessDefaultElement,new ListEntry(first,second)))).get(builder);
		}
		else if((second == null && templateParameters.isEmpty() && isVariable == true && mode == DEFAULT)){
			new ElementEntry(InternalGenerator.bodyNameElement,new ListEntry(new ElementEntry(ClasswiseGenerator.classAsVariableElement,new ListEntry(first)))).get(builder);
		}
		else if((second != null && mode == CONCAT && templateParameters.isEmpty())){
			new ElementEntry(InternalGenerator.bodyNameElement,new ListEntry(new ElementEntry(ClasswiseGenerator.concatElement,new ListEntry(first,second)))).get(builder);
		}
		else if((second != null && mode == ACCESS_CLASS && templateParameters.isEmpty())){
			new ElementEntry(InternalGenerator.bodyNameElement,new ListEntry(new ElementEntry(ClasswiseGenerator.accessClassElement,new ListEntry(first,second)))).get(builder);
		}
		else if((second != null && mode == ACCESS_METHOD && templateParameters.isEmpty())){
			new ElementEntry(InternalGenerator.bodyNameElement,new ListEntry(new ElementEntry(ClasswiseGenerator.accessMethodElement,new ListEntry(first,second)))).get(builder);
		}
		else if((second == null && !templateParameters.isEmpty() && mode == DEFAULT)){
			new ElementEntry(InternalGenerator.bodyNameWithParametersElement,new ListEntry(first,templateParameters)).get(builder);
		}
		else if((second != null && !templateParameters.isEmpty() && mode == CONCAT)){
			new ElementEntry(InternalGenerator.bodyNameWithParametersElement,new ListEntry(new ElementEntry(ClasswiseGenerator.concatElement,new ListEntry(first,second)),templateParameters)).get(builder);
		}
		else if((second != null && !templateParameters.isEmpty() && mode == ACCESS_CLASS)){
			new ElementEntry(InternalGenerator.bodyNameWithParametersElement,new ListEntry(new ElementEntry(ClasswiseGenerator.accessClassElement,new ListEntry(first,second)),templateParameters)).get(builder);
		}
		else if((second != null && !templateParameters.isEmpty() && mode == ACCESS_CLASS)){
			new ElementEntry(InternalGenerator.bodyNameWithParametersElement,new ListEntry(new ElementEntry(ClasswiseGenerator.accessMethodElement,new ListEntry(first,second)),templateParameters)).get(builder);
		}
	}
}