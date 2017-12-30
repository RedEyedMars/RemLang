package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.ExternalStatement.NewObject;

import java.util.ArrayList;
import java.util.List;
public class ExternalMethodEntry extends ExternalImportEntry {
	private Integer tabs = 0;
	private Entry type;
	private Entry name;
	private List<ExternalVariableEntry> parameters;
	private ExternalContext classContext;
	private ExternalContext headerContext;
	private ExternalStatement.Body body;
	private boolean isInterface = false;
	private boolean isStatic = false;
	private String typeSuffix = null;
	private String throwsStatement = null;
	public ExternalMethodEntry(){
		
	}
	public ExternalMethodEntry(String newType, ExternalMethodEntry otherMethod){
		this(otherMethod.tabs, otherMethod.isStatic, new StringEntry(newType),
				otherMethod.typeSuffix, otherMethod.name, otherMethod.parameters , otherMethod.throwsStatement, otherMethod.body);
	}
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, String typeSuffix, Entry name, List<ExternalVariableEntry> parameters , String throwStatement, ExternalStatement.Body body){
		this.tabs = tabs;
		this.type = type;
		this.typeSuffix = typeSuffix;
		this.name = name;
		this.parameters = parameters;
		this.throwsStatement = throwStatement;
		this.body = body;
		this.isStatic = isStatic;

		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		headerContext = new ExternalContext(true,null,parameters);
		if(!getSimpleName().contains("*")){
			addImport(new ImportEntry(type));
		}
		for(ExternalVariableEntry variable:parameters){
			addSubImport(variable);
		}
		if(body!=null){
			body.setParentContext(headerContext);
			addSubImport(body);
		}
		else {
			this.isInterface = true;
		}
	}
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, String typeSuffix, Entry name, ExternalStatement.Parameters params, String throwStatement, ExternalStatement.Body body){
		this.tabs = tabs;
		this.type = type;
		this.typeSuffix  = typeSuffix;
		this.name = name;
		this.throwsStatement = throwStatement;
		this.parameters = new ArrayList<ExternalVariableEntry>();
		for(ExternalStatement param:params){
			parameters.add((ExternalVariableEntry)param);
			((ExternalVariableEntry)param).setAssignment(null);
		}
		this.body = body;
		this.isStatic = isStatic;

		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		headerContext = new ExternalContext(true,null,parameters);
		if(!getSimpleName().contains("*")){
			addImport(new ImportEntry(type));
		}
		for(ExternalVariableEntry variable:parameters){
			addSubImport(variable);
		}
		if(body!=null){
			body.setParentContext(headerContext);
			addSubImport(body);
		}
		else {
			this.isInterface = true;
		}
	}
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, Entry name, List<ExternalVariableEntry> parameters, String throwStatement, ExternalStatement.Body body){
		this(tabs, isStatic, type, "", name, parameters, throwStatement, body);
	}
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, Entry name, ExternalStatement.Parameters params , String throwStatement,  ExternalStatement.Body body){
		this(tabs, isStatic, type, "", name, params,          "", body);
	}
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, Entry name, List<ExternalVariableEntry> parameters, ExternalStatement.Body body){
		this(tabs, isStatic, type, "",         name, parameters,"", body);
	}
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, String typeSuffix, Entry name, List<ExternalVariableEntry> parameters, ExternalStatement.Body body){
		this(tabs, isStatic, type, typeSuffix, name, parameters,"", body);
	}
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, Entry name, ExternalStatement.Parameters params, ExternalStatement.Body body){
		this(tabs, isStatic, type, ""        , name, params, "", body);
	}
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, String typeSuffix, Entry name, ExternalStatement.Parameters params, ExternalStatement.Body body){
		this(tabs, isStatic, type, typeSuffix, name, params, "", body);
	}

	public void setIsInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}
	public void setTabs(int newTabs){
		tabs = newTabs;
	}


	public String getSimpleName() {
		StringBuilder builder = new StringBuilder();
		name.get(builder);
		return builder.toString();
	}
	public String getName(){
		StringBuilder builder = new StringBuilder();
		name.get(builder);
		builder.append("[");
		String comma = "";
		for(ExternalVariableEntry variable:parameters){
			builder.append(comma);
			variable.getType().get(builder);
			comma = ",";
		}
		builder.append("]");
		return builder.toString();
	}
	public ExternalContext getClassContext() {
		return classContext;
	}
	public ExternalContext getContext(){
		return body.getContext();
	}

	public ExternalContext getHeaderContext() {
		return headerContext;
	}
	public void addParameter(ExternalVariableEntry newParameter){
		parameters.add(newParameter);
	}
	public void appendToBody(ExternalStatement.Body newBodyParts){
		body.addAll(newBodyParts);
	}
	public void prependToBody(ExternalStatement.Body newBodyParts){
		body.addAll(0, newBodyParts);
	}
	public void appendToBody(ExternalStatement newBodyParts){
		body.add(newBodyParts);
	}
	public void prependToBody(ExternalStatement newBodyParts){
		body.add(0, newBodyParts);
	}
	public void setParentContext(ExternalContext parentContext){
		headerContext.setParent(parentContext);
	}

	public ExternalStatement.Body getBody() {
		return body;
	}
	public void get(StringBuilder builder){
		new TabEntry(tabs, new StringEntry("public ")).get(builder);
		if(isStatic){
			builder.append("static ");
		}
		type.get(builder);
		if (typeSuffix!=null){
			builder.append(typeSuffix);
		}
		if(!getSimpleName().contains("*")){
			builder.append(" ");
			name.get(builder);
		}
		builder.append("(");
		if(!parameters.isEmpty()){
			parameters.get(0).get(builder);
		}
		for(int i=1;i<parameters.size();++i){
			builder.append(",");
			parameters.get(i).get(builder);
		}
		if(isInterface){
			builder.append(")");
			if(throwsStatement!=null){
				builder.append(throwsStatement);	
			}
			
			builder.append(";");
		}
		else {
			builder.append(")");
			if(throwsStatement!=null){
				builder.append(throwsStatement);	
			}
			builder.append("{");
			for(int i=0;i<body.size();++i){
				body.get(i).setTabs(tabs+1);
				body.get(i).get(builder);
			}
			new TabEntry(tabs, new StringEntry("}")).get(builder);
		}
	}
	public String getTypeName() {
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		if (typeSuffix != null) {
			typeBuilder.append(typeSuffix);
		}
		return typeBuilder.toString();
	}
	public void setType(ExternalStatement.TypeName type){
		StringBuilder typeBuilder = new StringBuilder();
		type.getCleanType().get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		this.type = type;
	}

	public void setName(ExternalStatement newName){
		name = newName;
		if(!getSimpleName().contains("*")){
			addImport(new ImportEntry(type));
		}
	}
	public void setParameters(List<ExternalVariableEntry> parameters){
		this.parameters = parameters;
		headerContext = new ExternalContext(true,null,parameters);
		for(ExternalVariableEntry variable:parameters){
			addSubImport(variable);
		}

	}
	ExternalStatement parametersAsStatement = null;
	public void setParametersAsStatement(ExternalStatement parameters){
		this.parametersAsStatement = parameters;

	}
	public void setThrowsStatement(String newThrowsStatement){
		throwsStatement = newThrowsStatement;
	}
	public void setBody(ExternalStatement.Body body){
		this.body = body;
		if(body!=null){
			body.setParentContext(headerContext);
			addSubImport(body);
		}
		else {
			this.isInterface = true;
		}
	}
	
	public void setIsStatic(Boolean newIsStatic){
		this.isStatic = newIsStatic;
	}
	//public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, String typeSuffix, Entry name, List<ExternalVariableEntry> parameters , String throwStatement, ExternalStatement.Body body){
	
	public ExternalStatement getAsStatement(){
		ExternalStatement.Parameters completeStatement = new ExternalStatement.Parameters(
				new ExternalStatement(new StringEntry("0")),
				new ExternalStatement(new StringEntry(isStatic+"")),
				type!=null?ExternalClassHelper.getAsStatementFromEntry(type):new ExternalStatement(new StringEntry("null")),
				typeSuffix!=null?ExternalClassHelper.getAsStatementFromEntry(typeSuffix):new ExternalStatement(new StringEntry("null")),
				name!=null?ExternalClassHelper.getAsStatementFromEntry(name):new ExternalStatement(new StringEntry("null"))
				);
		if(parametersAsStatement != null){
			completeStatement.add(ExternalClassHelper.getAsStatementFromEntry(parametersAsStatement));
		}
		else {
			ExternalStatement.Parameters variableAsParameters = new ExternalStatement.Parameters();
			for(ExternalVariableEntry variable:parameters){
				variableAsParameters.add(variable.getAsStatement());
			}
			completeStatement.add(
				new ExternalStatement(new StringEntry("Arrays.asList("),new StringEntry(")"),new NewObject(new ExternalStatement.TypeName("ExternalVariableEntry"),variableAsParameters,new ExternalStatement.ArrayParameters())));
		}
		completeStatement.add(throwsStatement == null?new ExternalStatement(new StringEntry("null")):ExternalClassHelper.getAsStatementFromEntry(throwsStatement));
		if(body!=null){
			completeStatement.add(body.getAsStatement());
		}
		else {
			completeStatement.add(new ExternalStatement(new StringEntry("null")));
		}
		return new ExternalStatement.NewObject(new ExternalStatement.TypeName("ExternalMethodEntry"),completeStatement);
				
	}
}