package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;
public class ExternalVariableEntry extends ExternalStatement {
	private int tabs = 0;
	private Entry type;
	private Entry name;
	private Entry assignment = null;
	private ExternalContext classContext;
	private boolean isStatic;
	private boolean isWeak = false;
	
	public ExternalVariableEntry(Boolean isStatic, Entry type, Entry name, ExternalImportEntry assignment){
		this(isStatic,false,type,name,assignment);
	}
	public ExternalVariableEntry(Boolean isStatic, Boolean isWeak, Entry type, Entry name, ExternalImportEntry assignment){
		this.isStatic = isStatic;
		this.type = type;
		this.name = name;
		this.assignment = assignment;
		this.isWeak = isWeak;
		addImport(new ImportEntry(type));
		if(assignment!=null){
			addSubImport(assignment);
		}
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		getContext().add(this);
	}
	public ExternalVariableEntry(Boolean isStatic, Entry type, Entry name){
		this.isStatic = isStatic;
		this.type = type;
		this.name = name;
		addImport(new ImportEntry(type));
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
	}
	public ExternalVariableEntry(Boolean isStatic, Boolean isWeak, Entry type, Entry name){
		this.isStatic = isStatic;
		this.type = type;
		this.name = name;
		this.isWeak = isWeak;
		addImport(new ImportEntry(type));
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
	}
	public String getName(){
		StringBuilder builder = new StringBuilder();
		name.get(builder);
		return builder.toString();
	}
	public boolean isWeak(){
		return isWeak;
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
					new TabEntry(tabs, new StringEntry("public ")).get(builder);
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
		if(isWeak){
			return null;
		}
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
		if(isWeak){
			return null;
		}
		//final ExternalVariableEntry self = this;
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
	public Entry getAsSuperParameter(){
		if(isWeak){
			return null;
		}
		return new Entry(){
			@Override
			public void get(StringBuilder builder) {
				builder.append("final ");
				type.get(builder);
				builder.append(" initalSuper");
				StringBuilder subBuilder = new StringBuilder();
				name.get(subBuilder);
				builder.append(Generator.camelize(subBuilder.toString()));
			}
		};
	}
	public Entry getAsSuperArgument(){
		if(isWeak){
			return null;
		}
		return new Entry(){
			@Override
			public void get(StringBuilder builder) {
				builder.append("initalSuper");
				StringBuilder subBuilder = new StringBuilder();
				name.get(subBuilder);
				builder.append(Generator.camelize(subBuilder.toString()));
			}
		};
	}

	public boolean isStatic() {
		return isStatic;
	}
	public void get(StringBuilder builder){
		if(isStatic){
			builder.append("static ");
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