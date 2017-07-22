package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.ExternalImportEntry.ImportEntry;

import java.util.List;
public class ExternalMethodEntry extends ExternalImportEntry {
	private Integer tabs = 0;
	private Entry type;
	private Entry name;
	private List<ExternalVariableEntry> parameters;
	private ExternalStatement.Body body;
	private ExternalContext classContext;
	private ExternalContext headerContext;
	private boolean isInterface = false;
	private boolean isStatic = false;
	public ExternalMethodEntry(Integer tabs, Boolean isStatic, Entry type, Entry name, List<ExternalVariableEntry> parameters, ExternalStatement.Body body){
		this.tabs = tabs;
		this.type = type;
		this.name = name;
		this.parameters = parameters;
		this.body = body;
		this.isStatic = isStatic;

		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		headerContext = new ExternalContext(null,parameters);
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
		for(ExternalVariableEntry variable:parameters){
			variable.getType().get(builder);
			builder.append(",");
		}
		builder.append("]");
		return builder.toString();
	}
	public ExternalContext getClassContext() {
		return classContext;
	}
	public ExternalContext getCurrentContext(){
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
			builder.append(");");
		}
		else {
			builder.append(") {");
			for(int i=0;i<body.size();++i){
				body.get(i).setTabs(tabs+1);
				body.get(i).get(builder);
			}
			new TabEntry(tabs, new StringEntry("}")).get(builder);
		}
	}
}