package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;
import java.util.*;
public abstract class ExternalClassEntry extends ExternalImportEntry {

	public static final Map<String,ExternalClassEntry> allClasses = new HashMap<String,ExternalClassEntry>();

	private Map<String,ExternalVariableEntry> variables = new LinkedHashMap<String,ExternalVariableEntry>();
	private Map<String,ExternalMethodEntry> methods = new LinkedHashMap<String,ExternalMethodEntry>();
	private Map<String,ExternalMethodEntry> simpleMethods = new LinkedHashMap<String,ExternalMethodEntry>();
	private Map<String,ExternalClassEntry> classes = new LinkedHashMap<String,ExternalClassEntry>();

	private String name;
	private int tabs;
	private Entry header;
	private Entry packageName;
	private boolean isSubClass = false;

	private ExternalContext myContext;
	private ExternalClassEntry superClass;
	private Entry parentClass;
	private boolean isInterface;

	private ExternalMethodEntry constructorMethod;
	public ExternalClassEntry(){
		super();
	}
	public void __INIT__(){}
	public void __SETUP__(Entry initialPackageName, Entry preImports, Entry initialName, String classType, Entry initialParentClass, Entry initialHeader, List<ExternalVariableEntry> initialVariables, List<ExternalMethodEntry> initialMethods, List<ExternalClassEntry> initialSubClasses){
		super.__SETUP__(preImports);
		isInterface = classType.contains("interface");
		StringBuilder builder = new StringBuilder();
		initialName.get(builder);
		name = builder.toString();
		myContext = ExternalContext.getClassContext(getName());
		packageName = initialPackageName;
		ExternalImportEntry.packages.put(name, packageName);
		allClasses.put(name, this);
		header = initialHeader;
		parentClass = initialParentClass;

		addParentImport(parentClass);

		ExternalStatement.Body constructorBody = new ExternalStatement.Body();
		constructorMethod = new ExternalMethodEntry(
				0, new Entry(){public void get(StringBuilder builder){new StringEntry(name).get(builder);}},
				new Entry(){public void get(StringBuilder builder){new StringEntry("*").get(builder);}},
				new ArrayList<ExternalVariableEntry>(),
				constructorBody);
		methods.put("*", constructorMethod);
		myContext.add(constructorMethod);
		myContext.add(new ExternalVariableEntry(false,initialName,new StringEntry("this"),null));
		addSubImport(constructorBody);
		//addSubImport(constructorMethod);

		for(ExternalVariableEntry variable:initialVariables){
			addVariable(variable);
		}
		for(ExternalMethodEntry method:initialMethods){
			addMethod(method);
		}
		for(ExternalClassEntry subClass:initialSubClasses){
			addSubClass(subClass);
		}
	}
	public void addVariable(final ExternalVariableEntry variable){
		variables.put(variable.getName(), variable);
		//System.out.println(this.getFullName());
		myContext.add(variable);
		addSubImport(variable);
		addMethod(new ExternalMethodEntry(0,variable.getType(),new Entry(){
			@Override
			public void get(StringBuilder builder) {
				builder.append("get");
				builder.append(Generator.camelize(variable.getName()));
			}
		},new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body(
				new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry(variable.getName())))
				)));
	}
	public void addMethod(ExternalMethodEntry method){
		methods.put(method.getName(), method);
		simpleMethods.put(method.getSimpleName(), method);
		myContext.add(method);
		if(!method.getName().equals("*")){
			addSubImport(method);
		}
		method.setIsInterface(isInterface);
	}
	public void addSubClass(ExternalClassEntry subClass){
		subClass.__INIT__();
		ExternalImportEntry.addTopClass(this.getName(),subClass.getName());
		ExternalContext.freeClass(subClass.getName());
		classes.put(subClass.getName(), subClass);
		addSubImport(subClass);
		subClass.isSubClass  = true;
		subClass.superClass = this;
		subClass.myContext.setParent(myContext);
		ExternalClassEntry.allClasses.put(subClass.getFullName(),subClass);
		ExternalContext.getClassContext(subClass.getFullName()).setParent(subClass.myContext);
	}
	public ExternalVariableEntry getVariable(String variableName){
		return variables.get(variableName);
	}
	public ExternalMethodEntry getMethod(String methodName){
		if(methods.containsKey(methodName)){
			return methods.get(methodName);
		}
		else {
			return simpleMethods.get(methodName);
		}
	}
	public ExternalClassEntry getSubClass(String subClassName){
		return classes.get(subClassName);
	}
	public ExternalVariableEntry getVariable(ExternalVariableEntry variable){
		return variables.get(variable.getName());
	}
	public ExternalMethodEntry getMethod(ExternalMethodEntry method){
		return methods.get(method.getName());
	}
	public ExternalClassEntry getSubClass(ExternalClassEntry subClass){
		return classes.get(subClass.getName());
	}
	public String getPackage(){
		StringBuilder builder = new StringBuilder();
		packageName.get(builder);
		return builder.toString();
	}
	public String getName(){
		return name;
	}
	public String getFullName(){
		StringBuilder fullBuilder = new StringBuilder();
		getFullName(fullBuilder);
		return fullBuilder.toString();
	}
	private void getFullName(StringBuilder builder){
		if(superClass!=null){
			superClass.getFullName(builder);
			builder.append(".");
		}
		builder.append(name);
	}
	public void setTabs(int newTabs){
		tabs = newTabs;
	}
	public void get(StringBuilder builder){
		if(isSubClass == false){
			builder.append("package ");
			packageName.get(builder);
			builder.append(";");
			builder.append("\nimport java.util.*;");
			builder.append("\nimport java.io.*;");
			outputImport(builder);
			for(String classKey: classes.keySet()){
				classes.get(classKey).outputParentImports(builder);
			}
		}
		new TabEntry(tabs, header).get(builder);
		for(String variableKey:variables.keySet()){
			variables.get(variableKey).setTabs(tabs+1);
			variables.get(variableKey).getAsMember().get(builder);
		}
		if(!isInterface){
			new TabEntry(tabs+1,new StringEntry("public ")).get(builder);
			builder.append(name);
			builder.append("(");
			String comma = getHeader("",true,builder);
			boolean hasEmptyConstructor = "".equals(comma);
			builder.append(") {");
			if(parentClass!=null){
				comma = "";
				StringBuilder parentClassBuilder = new StringBuilder();
				parentClass.get(parentClassBuilder);
				if(allClasses.containsKey(parentClassBuilder.toString())){
					ExternalClassEntry realParentClass = allClasses.get(parentClassBuilder.toString());
					new TabEntry(tabs+2,new StringEntry("super(")).get(builder);
					comma = realParentClass.getSuperCall(comma,builder);
					builder.append(");");
				}
			}
			for(String variableKey:variables.keySet()){
				if(!variables.get(variableKey).isStatic()){
					variables.get(variableKey).setTabs(tabs+2);
					variables.get(variableKey).getAsConstructorElement().get(builder);
				}
			}
			for(ExternalStatement statement:getMethod("*").getBody()){
				statement.setTabs(tabs+2);
				statement.get(builder);
			}
			new TabEntry(tabs+1,new StringEntry("}")).get(builder);
			if(!hasEmptyConstructor){
				new TabEntry(tabs+1,new StringEntry("public ")).get(builder);
				builder.append(name);
				builder.append("(");
				builder.append(") {");
				for(ExternalStatement statement:getMethod("*").getBody()){
					statement.setTabs(tabs+2);
					statement.get(builder);
				}
				new TabEntry(tabs+1,new StringEntry("}")).get(builder);
			}
		}
		for(String methodKey: methods.keySet()){
			if(!methodKey.equals("*")){
				methods.get(methodKey).setTabs(tabs+1);
				methods.get(methodKey).get(builder);
			}
		}
		for(String classKey: classes.keySet()){
			classes.get(classKey).setTabs(tabs+1);
			classes.get(classKey).get(builder);
		}
		new TabEntry(tabs, new StringEntry("}")).get(builder);
	}
	public void outputParentImports(StringBuilder builder){
		if(parentClass!=null){
			StringBuilder parentClassBuilder = new StringBuilder();
			parentClass.get(parentClassBuilder);
			if(allClasses.containsKey(parentClassBuilder.toString())){
				allClasses.get(parentClassBuilder.toString()).outputImport(builder);
			}
		}
	}
	public String getHeader(String comma, boolean first, StringBuilder builder){
		if(parentClass!=null){
			StringBuilder parentClassBuilder = new StringBuilder();
			parentClass.get(parentClassBuilder);
			if(allClasses.containsKey(parentClassBuilder.toString())){
				comma = allClasses.get(parentClassBuilder.toString()).getHeader(comma,false,builder);
			}
		}
		for(String variableKey:variables.keySet()){
			if(!variables.get(variableKey).isStatic()){
				builder.append(comma);
				if(first){
					variables.get(variableKey).getAsParameter().get(builder);
				}
				else {
					variables.get(variableKey).getAsSuperParameter().get(builder);
				}
				comma = ", ";
			}
		}
		return comma;
	}

	public String getSuperCall(String comma, StringBuilder builder){
		if(parentClass!=null){
			StringBuilder parentClassBuilder = new StringBuilder();
			parentClass.get(parentClassBuilder);
			if(allClasses.containsKey(parentClassBuilder.toString())){
				comma = allClasses.get(parentClassBuilder.toString()).getSuperCall(comma,builder);
			}
		}
		for(String variableKey:variables.keySet()){
			if(!variables.get(variableKey).isStatic()){
				builder.append(comma);
				variables.get(variableKey).getAsSuperArgument().get(builder);
				comma = ", ";
			}
		}
		return comma;
	}

	public ExternalContext getContext(){
		return ExternalContext.getClassContext(getFullName());
	}
	public Map<String,ExternalVariableEntry> getVariables() {
		return this.variables;
	}
}