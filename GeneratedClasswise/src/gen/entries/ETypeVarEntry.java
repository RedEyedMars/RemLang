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

public class ETypeVarEntry implements Entry,IInnerable,IImportable,ICanAddSubClass,IVariablizable {
	public ETypeVarEntry getSelf(){
		return this;
	}

	public static final Integer DEFAULT = 0;
	public static final Integer CONCAT = 1;
	public static final Integer ACCESS_CLASS = 2;
	public static final Integer ACCESS_METHOD = 3;

	private Boolean isInner = false;
	private ImportListEntry importPackage = (ImportListEntry)new ImportListEntry();
	private Boolean isVariable = true;
	private ListEntry first = null;
	private Entry second = null;
	private Integer mode = DEFAULT;
	private ListEntry templateParameters = new ListEntry();

	public ETypeVarEntry(){
		isVariable = false;
		first = new ListEntry();
		first.setDelimiter(".");
	}
	public ETypeVarEntry(Entry iValue){
		isVariable = false;
		first = new ListEntry(iValue);
		first.setDelimiter(".");
	}
	public ETypeVarEntry(Entry iValue,ListEntry iTemplateParameters,IImportable importType){
		isVariable = false;
		first = new ListEntry(iValue);
		templateParameters = iTemplateParameters;
		first.setDelimiter(".");
		if((importType != null)){
			this.addImports(importType.getImportPackage());
		}
		templateParameters.setDelimiter(",");
	}
	public ETypeVarEntry(Entry iFirst,String operator,Entry iSecond){
		isVariable = false;
		first = new ListEntry(iFirst);
		second = iSecond;
		if((operator.equals("+"))){
			mode = CONCAT;
		}
		else {
			if((operator.equals(".*"))){
				mode = ACCESS_METHOD;
			}
			else {
				mode = ACCESS_CLASS;
			}
		}
		first.setDelimiter(".");
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
		first.add(newSubType);
		StringBuilder builder = new StringBuilder();
		first.get(builder);
		IImportable importType = (IImportable)Generators.classwise.getType(builder.toString());
		this.addImports(importType.getImportPackage());
	}
	public Boolean getIsVariable(){
		return isVariable;
	}
	public void setIsVariable(Boolean newIsVariable) {
		isVariable = newIsVariable;
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
		if((second == null && templateParameters.isEmpty() && isVariable == false && mode == DEFAULT)){
			new ElementEntry(ExternalGenerator.bodyTypeNameElement,new ListEntry(first)).get(builder);
		}
		else if((second == null && templateParameters.isEmpty() && isVariable == true && mode == DEFAULT)){
			new ElementEntry(ExternalGenerator.bodyTypeNameElement,new ListEntry(new ElementEntry(ClasswiseGenerator.classAsVariableElement,new ListEntry(first)))).get(builder);
		}
		else if((second == null && templateParameters.isEmpty() && mode == DEFAULT)){
			new ElementEntry(ExternalGenerator.bodyTypeNameElement,new ListEntry(first)).get(builder);
		}
		else if((second != null && mode == CONCAT && templateParameters.isEmpty())){
			new ElementEntry(ExternalGenerator.bodyTypeNameElement,new ListEntry(new ElementEntry(ClasswiseGenerator.concatElement,new ListEntry(first,second)))).get(builder);
		}
		else if((second != null && mode == ACCESS_CLASS && templateParameters.isEmpty())){
			new ElementEntry(ExternalGenerator.bodyTypeNameElement,new ListEntry(new ElementEntry(ExternalGenerator.accessClassElement,new ListEntry(first,second)))).get(builder);
		}
		else if((second != null && mode == ACCESS_METHOD && templateParameters.isEmpty())){
			new ElementEntry(ExternalGenerator.bodyTypeNameElement,new ListEntry(new ElementEntry(ExternalGenerator.accessMethodElement,new ListEntry(first,second)))).get(builder);
		}
		else if((second == null && !templateParameters.isEmpty() && mode == DEFAULT)){
			new ElementEntry(ExternalGenerator.bodyTypeNameWithParametersElement,new ListEntry(first,templateParameters)).get(builder);
		}
		else if((second != null && mode == CONCAT && !templateParameters.isEmpty())){
			new ElementEntry(ExternalGenerator.bodyTypeNameWithParametersElement,new ListEntry(new ElementEntry(ClasswiseGenerator.concatElement,new ListEntry(first,second)),templateParameters)).get(builder);
		}
		else if((second != null && mode == ACCESS_CLASS && !templateParameters.isEmpty())){
			new ElementEntry(ExternalGenerator.bodyTypeNameWithParametersElement,new ListEntry(new ElementEntry(ClasswiseGenerator.accessClassElement,new ListEntry(first,second)),templateParameters)).get(builder);
		}
		else if((second != null && mode == ACCESS_METHOD && !templateParameters.isEmpty())){
			new ElementEntry(ExternalGenerator.bodyTypeNameWithParametersElement,new ListEntry(new ElementEntry(ClasswiseGenerator.accessMethodElement,new ListEntry(first,second)),templateParameters)).get(builder);
		}
	}
}