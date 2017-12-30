package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;
public class ExternalVariableEntry extends ExternalStatement {
	private int tabs = 0;
	private Entry type;
	private Entry name;
	private Entry assignment = null;
	private ExternalContext classContext;
	private boolean isStatic;
	private boolean isFinal = false;
	private String typeSuffix = "";
	private boolean hasSetMethod = true;
	
	public ExternalVariableEntry(){
	}
	
	public ExternalVariableEntry(boolean hasSetMethod, boolean isStatic, boolean isFinal, Entry type, String typeSuffix, Entry name, ExternalImportEntry assignment){
		this(isStatic,isFinal,type,typeSuffix,name,assignment);
		this.hasSetMethod  = hasSetMethod;
	}
	public ExternalVariableEntry(Boolean isStatic, Entry type, String typeSuffix, Entry name, ExternalImportEntry assignment){
		this(isStatic,false,type,typeSuffix,name,assignment);
	}
	public ExternalVariableEntry(Boolean isStatic, Boolean isFinal, Entry type, String typeSuffix, Entry name, ExternalImportEntry assignment){
		this.isStatic = isStatic;
		this.type = type;
		this.typeSuffix = typeSuffix;
		this.name = name;
		this.assignment = assignment;
		this.isFinal = isFinal;
		addImport(new ImportEntry(type));
		if(assignment!=null){
			addSubImport(assignment);
		}
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
		getContext().add(this);
	}
	public ExternalVariableEntry(Entry type, Entry name){
		this.isStatic = false;
		this.type = type;
		this.typeSuffix = "";
		this.name = name;
		addImport(new ImportEntry(type));
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
	}
	public ExternalVariableEntry(Boolean isStatic, Entry type, String typeSuffix, Entry name){
		this.isStatic = isStatic;
		this.type = type;
		this.typeSuffix = typeSuffix;
		this.name = name;
		addImport(new ImportEntry(type));
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
	}
	public ExternalVariableEntry(boolean hasSetMethod, boolean isStatic, boolean isFinal, Entry type, String typeSuffix, Entry name){
		this(isStatic,isFinal,type,typeSuffix,name);
		this.hasSetMethod  = hasSetMethod;
	}
	public ExternalVariableEntry(Boolean isStatic, Boolean isFinal, Entry type, String typeSuffix, Entry name){
		this.isStatic = isStatic;
		this.type = type;
		this.typeSuffix = typeSuffix;
		this.name = name;
		this.isFinal = isFinal;
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
	public Entry getType(){
		return type;
	}
	public String getTypeSuffix() {
		return typeSuffix;
	}
	public ExternalContext getClassContext() {
		return classContext;
	}
	public void setTabs(int newTabs){
		tabs = newTabs;
	}

	public Entry getAsEnumMember(final String comma) {
		return new Entry(){
			@Override
			public void get(StringBuilder builder) {
				builder.append(comma);

				new TabEntry(tabs, name).get(builder);
			}
			
		};
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
		if(hasSetMethod){
			return null;
		}
		return new Entry(){
			@Override
			public void get(StringBuilder builder) {
				builder.append("final ");
				type.get(builder);
				builder.append(typeSuffix);
				builder.append(" ");
				name.get(builder);
			}
			
		};
	}
	public Entry getAsConstructorElement(){
		if(hasSetMethod){
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
		if(hasSetMethod){
			return null;
		}
		return new Entry(){
			@Override
			public void get(StringBuilder builder) {
				builder.append("final ");
				type.get(builder);
				builder.append(typeSuffix);
				builder.append(" initalSuper");
				StringBuilder subBuilder = new StringBuilder();
				name.get(subBuilder);
				builder.append(Generator.camelize(subBuilder.toString()));
			}
		};
	}
	public Entry getAsSuperArgument(){
		if(hasSetMethod){
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
	public void setAssignment(ExternalStatement newAssignment){
		assignment = newAssignment;
		if(newAssignment!=null){
			addSubImport(newAssignment);
		}
	}
	public void get(StringBuilder builder){
		if(isStatic){
			builder.append("static ");
		}
		if(isFinal){
			builder.append("final ");
		}
		if(type != null){
			type.get(builder);
			builder.append(typeSuffix);
			builder.append(" ");
		}
		name.get(builder);
		if(assignment != null){
			builder.append(" = ");
			assignment.get(builder);
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
	//public ExternalVariableEntry(Boolean isStatic, Boolean isFinal, Entry type, String typeSuffix, Entry name, ExternalImportEntry assignment){
	@Override
	public ExternalStatement getAsStatement(){
		return new ExternalStatement.NewObject(new ExternalStatement.TypeName("ExternalVariableEntry"),new ExternalStatement.Parameters(
				new ExternalStatement(new StringEntry(hasSetMethod+"")),
				new ExternalStatement(new StringEntry(isStatic+"")),
				new ExternalStatement(new StringEntry(isFinal+"")),
				ExternalClassHelper.getAsStatementFromEntry(type),
				ExternalClassHelper.getAsStatementFromEntry(typeSuffix),
				ExternalClassHelper.getAsStatementFromEntry(name),
				assignment==null?
						new ExternalStatement(new StringEntry("null")):
						ExternalClassHelper.getAsStatementFromEntry(assignment)
				));
	}
	public ExternalStatement getNameAsStatement(){
	   return new ExternalStatement(name);
	}
	public void setType(Entry newType){
		type = newType;
		StringBuilder typeBuilder = new StringBuilder();
		type.get(typeBuilder);
		classContext = ExternalContext.getClassContext(typeBuilder.toString());
	}
	public void setName(Entry newName){
		name = newName;
	}
	public void setAssignment(Entry newAssignment){
		assignment = newAssignment;
	}
	public void setIsStatic(Boolean newStatic){
		isStatic = newStatic;
	}
	public void setIsFinal(Boolean newIsFinal){
		isFinal = newIsFinal;
	}
	public void addArraySymbol(){
		typeSuffix = typeSuffix + "[]";
	}

	public boolean isFinal() {
		return isFinal;
	}
	public boolean hasSetMethod(){
		return hasSetMethod;
	}
	public void setHasSetMethod(boolean newHasSetMethod){
		hasSetMethod = newHasSetMethod;
	}
}