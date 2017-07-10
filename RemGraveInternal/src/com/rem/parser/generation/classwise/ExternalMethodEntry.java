package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;
import java.util.List;
public class ExternalMethodEntry extends ExternalImportEntry {
	private Integer tabs = 0;
	private Entry type;
	private Entry name;
	private List<ExternalVariableEntry> parameters;
	private ExternalStatement.Body body;
	private ExternalContext classContext;
	private ExternalContext currentContext;
	
	public ExternalMethodEntry(Integer tabs, Entry type, Entry name, List<ExternalVariableEntry> parameters, ExternalStatement.Body body){
		this.tabs = tabs;
		this.type = type;
		this.name = name;
		this.parameters = parameters;

		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		currentContext = new ExternalContext(null,parameters);
		currentContext.add(this);
		this.body = body;
		body.setParentContext(currentContext);
		addImport(new ImportEntry(type));
		for(ExternalVariableEntry variable:parameters){
			addSubImport(variable);
		}
		addSubImport(body);

	}
	public void setTabs(int newTabs){
		tabs = newTabs;
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
		currentContext.setParent(parentContext);
	}
	public void get(StringBuilder builder){
		new TabEntry(tabs, new StringEntry("public ")).get(builder);
		type.get(builder);
		builder.append(" ");
		name.get(builder);
		builder.append("(");
		if(!parameters.isEmpty()){
			parameters.get(0).get(builder);
		}
		for(int i=1;i<parameters.size();++i){
			builder.append(",");
			parameters.get(i).get(builder);
		}
		builder.append(") {");
		for(int i=0;i<body.size();++i){
			body.get(i).setTabs(tabs+1);
			body.get(i).get(builder);
		}
		new TabEntry(tabs, new StringEntry("}")).get(builder);
	}
}