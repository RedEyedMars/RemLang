package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;
public class ExternalVariableEntry extends ExternalStatement {
	private int tabs = 0;
	private Entry type;
	private Entry name;
	private Entry assignment = null;
	private ExternalContext classContext;
	private ExternalContext currentContext;
	private boolean isStatic;
	
	public ExternalVariableEntry(Boolean isStatic, Entry type, Entry name, ExternalImportEntry assignment){
		this.isStatic = isStatic;
		this.type = type;
		this.name = name;
		this.assignment = assignment;
		addImport(new ImportEntry(type));
		addSubImport(assignment);
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		currentContext = new ExternalContext(null,this);
		currentContext.add(this);
	}
	public ExternalVariableEntry(Boolean isStatic, Entry type, Entry name){
		this.isStatic = isStatic;
		this.type = type;
		this.name = name;
		addImport(new ImportEntry(type));
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		currentContext = new ExternalContext(null,this);
	}
	public String getName(){
		StringBuilder builder = new StringBuilder();
		name.get(builder);
		return builder.toString();
	}
	public Entry getType(){
		return type;
	}
	public ExternalContext getClassContext() {
		return classContext;
	}
	public void setTabs(int newTabs){
		tabs = newTabs;
	}
	
	public Entry getAsMember() {
		final ExternalVariableEntry self = this;
		return new Entry(){
			@Override
			public void get(StringBuilder builder) {
				if(isStatic){
					new TabEntry(tabs, new StringEntry("protected static ")).get(builder);
				}
				else {
					new TabEntry(tabs, new StringEntry("protected ")).get(builder);
				}
				self.get(builder);
				builder.append(";");
			}
			
		};
	}
	public Entry getAsParameter(){
		return new Entry(){
			@Override
			public void get(StringBuilder builder) {
				builder.append("final ");
				type.get(builder);
				builder.append(" ");
				name.get(builder);
			}
			
		};
	}
	public Entry getAsConstructorElement(){
		final ExternalVariableEntry self = this;
		return new Entry(){
			@Override
			public void get(StringBuilder builder) {
				new TabEntry(tabs, new StringEntry("if(")).get(builder);
				name.get(builder);
				builder.append(" != null){");
				new TabEntry(tabs+1, new StringEntry("this.")).get(builder);
				name.get(builder);
				builder.append(" = ");
				name.get(builder);
				builder.append(";");
				new TabEntry(tabs, new StringEntry("}")).get(builder);
			}
			
		};
	}
	public void setParentContext(ExternalContext parentContext){
		currentContext.setParent(parentContext);
	}
	public void get(StringBuilder builder){
		if(isStatic){
			builder.append("static");
		}
		if(type != null){
			type.get(builder);
			builder.append(" ");
		}
		name.get(builder);
		if(assignment != null){
			builder.append(" = ");
			assignment.get(builder);
		}
	}
	
}