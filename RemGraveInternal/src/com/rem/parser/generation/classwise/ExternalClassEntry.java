package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;

import java.io.File;
import java.util.*;
public class ExternalClassEntry extends ExternalImportEntry {

	public static final List<ExternalClassEntry> allClasses = new ArrayList<ExternalClassEntry>();
	public static final Map<String,ExternalClassEntry> classMap = new HashMap<String,ExternalClassEntry>(){
		private static final long serialVersionUID = 19849465L;
		@Override
		public boolean containsKey(Object query){
			if(super.containsKey(query)){
				return true;
			}
			if(query instanceof String){
				String className = (String)query;
				int indexOfAngle = className.indexOf('<');
				if(indexOfAngle>-1){
					query = className.substring(0, indexOfAngle);
				}
			}
			return super.containsKey(query);
		}
		@Override
		public ExternalClassEntry get(Object query){
			if(super.containsKey(query)){
				return super.get(query);
			}
			if(query instanceof String){
				String className = (String)query;
				int indexOfAngle = className.indexOf('<');
				if(indexOfAngle>-1){
					query = className.substring(0, indexOfAngle);
				}
			}
			return super.get(query);
		}
	};
	public static final Map<String,List<ExternalClassEntry>> allOffspring = new HashMap<String,List<ExternalClassEntry>>();

	public static void suppliment(final String className,final String packageName){
		if(!classMap.containsKey(className)){
			classMap.put(className, new ExternalClassEntry(){
				@Override
				public void __INIT__(){
					super.__SETUP__(new StringEntry(packageName),new StringEntry(""),new StringEntry(className)," class ",null,new ArrayList<Entry>(),new StringEntry(" class "+className+" "),new ArrayList<ExternalVariableEntry>(),new ArrayList<ExternalMethodEntry>(),new ArrayList<ExternalClassEntry>());
				}
			});
			classMap.get(className).__INIT__();
		}
	}
	static {
		ExternalClassHelper.setup();
	}

	protected Map<String,ExternalVariableEntry> variables = new LinkedHashMap<String,ExternalVariableEntry>();
	protected Map<String,ExternalMethodEntry> methods = new LinkedHashMap<String,ExternalMethodEntry>();
	private Map<String,ExternalMethodEntry> simpleMethods = new LinkedHashMap<String,ExternalMethodEntry>();
	private Map<String,ExternalClassEntry> classes = new LinkedHashMap<String,ExternalClassEntry>();
	private Map<String,ExternalClassEntry> classyclasses = new LinkedHashMap<String,ExternalClassEntry>();
	private List<Entry> templateTypes = new ArrayList<Entry>();

	private String name;
	private int tabs;
	private Entry header;
	private Entry packageName;
	private boolean isSubClass = false;

	private ExternalContext myContext;
	private ExternalClassEntry enclosingClass;
	private String enclosingClassName;
	private Entry parentClass;
	private ExternalClassEntry bonifideParentClass;
	private List<Entry> interfaces;
	private boolean isInterface;
	private boolean isEnum;
	private boolean displayConstructors = true;
	private boolean isStatic = false;

	private ExternalMethodEntry constructorMethod;
	public ExternalClassEntry(){
		super();
	}
	public static void main(String[] args0){}
	public void __INIT__(){}
	public void __SETUP__(String initialEnclosingClassName, Entry initialPackageName, Entry preImports, Entry initialName, String classType, Entry initialParentClass, List<Entry> initialInterfaces, Entry initialHeader){
		__SETUP__(initialEnclosingClassName,         initialPackageName,       preImports,       initialName,        classType,       initialParentClass,             initialInterfaces,       initialHeader,new ArrayList<ExternalVariableEntry>(), new ArrayList<ExternalMethodEntry>(), new ArrayList<ExternalClassEntry>());
	}
	public void __SETUP__(Entry initialPackageName, Entry preImports, Entry initialName, String classType, Entry initialParentClass, List<Entry> initialInterfaces, Entry initialHeader){
		__SETUP__(null,         initialPackageName,       preImports,       initialName,        classType,       initialParentClass,             initialInterfaces,       initialHeader,new ArrayList<ExternalVariableEntry>(), new ArrayList<ExternalMethodEntry>(), new ArrayList<ExternalClassEntry>());
	}
	public void __SETUP__(Entry initialPackageName, Entry preImports, Entry initialName, String classType, Entry initialParentClass, List<Entry> initialInterfaces, Entry initialHeader, List<ExternalVariableEntry> initialVariables, List<ExternalMethodEntry> initialMethods, List<ExternalClassEntry> initialSubClasses){
		__SETUP__(null,         initialPackageName,       preImports,       initialName,        classType,       initialParentClass,             initialInterfaces,       initialHeader,  initialVariables, initialMethods, initialSubClasses);
	}
	public void __SETUP__(String initialEnclosingClassName, Entry initialPackageName, Entry preImports, Entry initialName, String classType, Entry initialParentClass, List<Entry> initialInterfaces, Entry initialHeader, List<ExternalVariableEntry> initialVariables, List<ExternalMethodEntry> initialMethods, List<ExternalClassEntry> initialSubClasses){

		isInterface = classType.contains("interface");
		isEnum = classType.contains("enum");
		StringBuilder builder = new StringBuilder();
		initialName.get(builder);
		name = builder.toString();
		myContext = ExternalContext.getClassContext(getName());
		header = initialHeader;
		parentClass = initialParentClass;
		interfaces = initialInterfaces;
		packageName = initialPackageName;
		enclosingClassName = initialEnclosingClassName;
		if(packageName!=null){
			ExternalImportEntry.packages.put(getFullName(), packageName);
		}
		allClasses.add(this);
		classMap.put(getFullName(), this);
		if(allOffspring.containsKey(name)){
			for(ExternalClassEntry offspring:allOffspring.get(name)){
				offspring.myContext.setParent(myContext);
			}
		}
		if(parentClass!=null&&getParentClass() == null){
			StringBuilder parentNameBuilder = new StringBuilder();
			parentClass.get(parentNameBuilder);
			if(!allOffspring.containsKey(parentNameBuilder.toString())){
				allOffspring.put(parentNameBuilder.toString(),new ArrayList<ExternalClassEntry>());
			}
			allOffspring.get(parentNameBuilder.toString()).add(this);
			addParentImport(parentClass);
		}
		else if(bonifideParentClass!=null){
			myContext.setParent(bonifideParentClass.myContext);
			addParentImport(parentClass);
		}
		if(interfaces!=null){
			for(Entry interfaceClass:interfaces){
				addParentImport(interfaceClass);
			}
		}

		ExternalStatement.Body constructorBody = new ExternalStatement.Body();
		constructorMethod = new ExternalMethodEntry(
				0, false, new Entry(){public void get(StringBuilder builder){new StringEntry(name).get(builder);}},
				new Entry(){public void get(StringBuilder builder){new StringEntry("*").get(builder);}},
				new ArrayList<ExternalVariableEntry>(),
				constructorBody);
		methods.put("*", constructorMethod);
		myContext.add(constructorMethod);
		myContext.add(new ExternalVariableEntry(false,initialName,"",new StringEntry("this"),null));
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
	public ExternalClassEntry getParentClass(){
		if(bonifideParentClass==null){
			if(parentClass!=null){
				StringBuilder parentNameBuilder = new StringBuilder();
				parentClass.get(parentNameBuilder);
				if(classMap.containsKey(parentNameBuilder.toString())){
					bonifideParentClass = classMap.get(parentNameBuilder.toString());

				}
			}
		}
		return bonifideParentClass;
	}
	public ExternalClassEntry getEnclosingClass(){
		return enclosingClass;
	}
	public void addVariable(final ExternalVariableEntry variable){
		variables.put(variable.getName(), variable);
		myContext.add(variable);
		addSubImport(variable);
		addImport(new ImportEntry(variable.getType()));
		if(isEnum==false&&variable.isStatic()==false){
			addMethod(new ExternalMethodEntry(0,false, variable.getType(), variable.getTypeSuffix(),new Entry(){
				@Override
				public void get(StringBuilder builder) {
					builder.append("get");
					builder.append(Generator.camelize(variable.getName()));
				}
			},new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body(
					new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry(variable.getName())))
					)));
		}
		if(isEnum==false&&!variable.isFinal()){
			StringBuilder typeBuilder = new StringBuilder();
			variable.getType().get(typeBuilder);
			final String camelName = Generator.camelize(variable.getName());
			String soloMethodName = "set"+camelName;
			String methodName = soloMethodName+"["+typeBuilder.toString()+",]";
			if(getMethod(methodName)!=null){
				getMethod(methodName).prependToBody(
						new ExternalStatement.Body(
								new ExternalStatement(new TabEntry(new StringEntry(variable.getName()+" = ")),
										new StringEntry(";"),"",new ExternalStatement(new StringEntry("new"+Generator.camelize(variable.getName())))))
						);
			}
			else {
				addMethod(new ExternalMethodEntry(0,false, new ExternalStatement.TypeName(new StringEntry("void")),new Entry(){
					@Override
					public void get(StringBuilder builder) {
						builder.append("set");
						builder.append(Generator.camelize(variable.getName()));
					}
				},new ArrayList<ExternalVariableEntry>(Arrays.asList(
						new ExternalVariableEntry(false,variable.getType(),variable.getTypeSuffix(),
								new Entry(){
							@Override
							public void get(StringBuilder builder) {
								builder.append("new");
								builder.append(camelName);
							}
						}
								))),new ExternalStatement.Body(
										new ExternalStatement(new TabEntry(new StringEntry(variable.getName()+" = ")),
												new StringEntry(";"),"",new ExternalStatement(new StringEntry("new"+Generator.camelize(variable.getName()))))
										)));
			}
		}
	}
	public void addMethod(ExternalMethodEntry method){
		if(method.getName().equals("*[]")||method.getName().equals("*")){
			constructorMethod.getBody().addAll(method.getBody());
		}
		else {
			methods.put(method.getName(), method);
			simpleMethods.put(method.getSimpleName(), method);
			myContext.add(method);
			addSubImport(method);
			method.setIsInterface(isInterface);
		}
	}
	public void removeMethod(ExternalMethodEntry method){
		methods.remove(method.getName());
		simpleMethods.remove(method.getSimpleName());
	}
	public Collection<String> getMethodNames(){
		return methods.keySet();
	}
	public void addSubClass(ExternalClassEntry subClass){

		subClass.enclosingClass = this;
		subClass.__INIT__();
		classes.put(subClass.getName(), subClass);
		classyclasses.put(subClass.getName()+"Class", subClass);
		addSubImport(subClass);
		subClass.isSubClass  = true;
		//subClass.myContext.setParent(myContext);
		subClass.redoContext();
	}
	private void redoContext(){
		ExternalClassEntry.classMap.put(getFullName(),this);
		ExternalContext.getClassContext(getFullName()).setParent(myContext);
		for(String className:classes.keySet()){
			classes.get(className).redoContext();
		}
	}
	public void addParentClass(ExternalClassEntry newParentClass){
		bonifideParentClass = null;
		parentClass = new StringEntry(newParentClass.getFullName());
		if(parentClass!=null&&getParentClass() == null){
			StringBuilder parentNameBuilder = new StringBuilder();
			parentClass.get(parentNameBuilder);
			if(!allOffspring.containsKey(parentNameBuilder.toString())){
				allOffspring.put(parentNameBuilder.toString(),new ArrayList<ExternalClassEntry>());
			}
			allOffspring.get(parentNameBuilder.toString()).add(this);
			addParentImport(parentClass);
		}
		else if(bonifideParentClass!=null){
			myContext.setParent(bonifideParentClass.myContext);

			addParentImport(parentClass);
		}
	}
	public void addInterfaceClass(ExternalClassEntry newInterfaceClass){
		if(interfaces==null){
			interfaces = new ArrayList<Entry>();
		}
		interfaces.add(newInterfaceClass);
		addParentImport(newInterfaceClass);
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
		if (classes.containsKey(subClassName)){
			return classes.get(subClassName);
		}
		else {
			return classyclasses.get(subClassName);
		}
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
		if(enclosingClass!=null){
			enclosingClass.getFullName(builder);
			builder.append(".");
		}
		else if(enclosingClassName!=null) {
			if(classMap.containsKey(enclosingClassName)){
				classMap.get(enclosingClassName).getFullName(builder);
				builder.append(".");
			}
			else {
				builder.append(enclosingClassName);
				builder.append(".");
			}
		}
		builder.append(name);
	}
	public void setTabs(int newTabs){
		tabs = newTabs;
	}
	public void setDisplayConstructors(boolean option){
		displayConstructors = option;
	}
	public void addTemplateType(Entry newTemplateType){
		this.templateTypes.add(newTemplateType);
	}
	public List<Entry> getTemplateTypes(){
		return templateTypes;
	}
	public void get(StringBuilder builder){
		if(isSubClass == false){
			builder.append("package ");
			packageName.get(builder);
			builder.append(";");
			builder.append("\nimport java.util.*;");
			builder.append("\nimport java.io.*;");
			for(String classKey: classes.keySet()){
				classes.get(classKey).accumulateParentImports(this);
			}
			if(getParentClass()!=null){
				for(String key:bonifideParentClass.variables.keySet()){
					if(bonifideParentClass.variables.get(key).isFinal()&&!bonifideParentClass.variables.get(key).isStatic()){
						addImport(new ImportEntry(bonifideParentClass.variables.get(key).getType()));
					}
				}
			}
			outputImport(builder);
		}
		new TabEntry(tabs, new StringEntry("public ")).get(builder);;
		if(header != null){
			header.get(builder);
		}
		else {
			if(isStatic){
				builder.append("static ");
			}
			if(isEnum){
				builder.append("enum ");
			}
			else if(isInterface){
				builder.append("interface ");
			}
			else {
				builder.append("class ");
			}
			if(name==null) {
				this.nameAsStatement.get(builder);
			}
			else {
				builder.append(name);
			}
			if(templateTypes!=null&&!templateTypes.isEmpty()) {
				builder.append(" <");
				for(Entry templateType: templateTypes){
					templateType.get(builder);
				}
				builder.append(" >");
			}
		}
		if(parentClass!=null){
			builder.append(" extends ");
			parentClass.get(builder);
		}
		if(interfaces != null){
			String prefix = " implements ";
			for(Entry interfaceClass:interfaces){
				builder.append(prefix);
				interfaceClass.get(builder);
				prefix = ",";
			}
		}
		builder.append("{");
		String enumComma = "";
		for(String variableKey:variables.keySet()){
			variables.get(variableKey).setTabs(tabs+1);
			if(isEnum){
				variables.get(variableKey).getAsEnumMember(enumComma).get(builder);
				enumComma = ",";
			}
			else {
				variables.get(variableKey).getAsMember().get(builder);
			}
		}
		if(!isInterface&&!isEnum&&displayConstructors){
			int constructorState = 2;
			if(getParentClass()!=null){
				constructorState = outputConstructor(builder,true);
			}
			if(constructorState>1){
				constructorState = outputConstructor(builder,false);	
			}
			if(constructorState>0){
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
		if(!isEnum){
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
		}
		new TabEntry(tabs, new StringEntry("}")).get(builder);
	}
	private int outputConstructor(StringBuilder builder, boolean withParentVariables){
		new TabEntry(tabs+1,new StringEntry("public ")).get(builder);
		builder.append(name);
		builder.append("(");
		String comma = getHeader("",true,withParentVariables,builder,new HashSet<ExternalClassEntry>());
		int result = 0;
		builder.append(") {");
		if(withParentVariables&&getParentClass()!=null){
			comma = "";
			new TabEntry(tabs+2,new StringEntry("super(")).get(builder);
			comma = getParentClass().getSuperCall(comma,builder,new HashSet<ExternalClassEntry>());
			result += "".equals(comma)?0:1;
			builder.append(");");
		}
		boolean hasSelfVar = false;
		for(String variableKey:variables.keySet()){
			if(!variables.get(variableKey).isStatic()&&!variables.get(variableKey).hasSetMethod()){
				variables.get(variableKey).setTabs(tabs+2);
				variables.get(variableKey).getAsConstructorElement().get(builder);
				hasSelfVar = true;
			}
		}
		for(ExternalStatement statement:getMethod("*").getBody()){
			statement.setTabs(tabs+2);
			statement.get(builder);
		}
		new TabEntry(tabs+1,new StringEntry("}")).get(builder);
		if(hasSelfVar){
			result+=1;
		}
		return result;

	}
	private void accumulateParentImports(ExternalClassEntry subToAll){
		if(getParentClass()!=null){
			for(String variableName:getParentClass().getVariables().keySet()){
				ExternalVariableEntry variable = getParentClass().getVariables().get(variableName);
				subToAll.addImport(new ImportEntry(variable.getType()));
			}
			getParentClass().accumulateParentImports(subToAll);
		}
	}
	public String getHeader(String comma, boolean first, boolean useParent, StringBuilder builder, Set<ExternalClassEntry> alreadyClasses){
		if(alreadyClasses.add(this)){
			if(useParent&&getParentClass()!=null){
				comma = getParentClass().getHeader(comma,false,true,builder,alreadyClasses);
			}
			for(String variableKey:variables.keySet()){
				if(!variables.get(variableKey).isStatic()){
					Entry result;
					if(first){
						result = variables.get(variableKey).getAsParameter();
					}
					else {
						result = variables.get(variableKey).getAsSuperParameter();
					}
					if(result!=null){
						builder.append(comma);
						result.get(builder);
						comma = ", ";
					}
				}
			}
		}
		return comma;
	}

	public String getSuperCall(String comma, StringBuilder builder, Set<ExternalClassEntry> alreadyClasses){
		if(alreadyClasses.add(this)){
			if(getParentClass()!=null){
				comma = getParentClass().getSuperCall(comma,builder,alreadyClasses);
			}
			for(String variableKey:variables.keySet()){
				if(!variables.get(variableKey).isStatic()){
					Entry result = variables.get(variableKey).getAsSuperArgument();
					if(result!=null){
						builder.append(comma);
						result.get(builder);
						comma = ", ";
					}
				}
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
	ExternalStatement nameAsStatement = null;
	public void setIsInterface(Boolean newIsInterface){
		this.isInterface = newIsInterface;
	}
	public void setIsEnum(Boolean newIsEnum){
		this.isEnum = newIsEnum;
	}
	public void setParentClass(ExternalStatement.TypeName newParentClass){
		this.parentClass = newParentClass;
		this.addSubImport(newParentClass);
	}
	public void addImplementingInterface(ExternalStatement.TypeName newInteface){
		if(interfaces == null){
			this.interfaces = new ArrayList<Entry >();
		}
		interfaces.add(newInteface);
	}
	private ExternalStatement packageNameAsStatement = null;
	public void setPackageName(ExternalStatement newPackageName){
		packageNameAsStatement = newPackageName;
	}
	public void setPackageName(String newPackageName){
		packageName = new StringEntry(newPackageName);
		if(packageName!=null){
			ExternalImportEntry.packages.put(name, packageName);
		}
	}
	public void setName(String newName){
		name = newName;
		myContext = ExternalContext.getClassContext(getName());
	}
	public void setNameAsStatement(ExternalStatement newName){
		StringBuilder builder = new StringBuilder();
		newName.get(builder);
		name = builder.toString();
		nameAsStatement = newName;
		myContext = ExternalContext.getClassContext(getName());

	}
	public void setupContext(){		
		allClasses.add(this);
		classMap.put(getFullName(), this);
		if(allOffspring.containsKey(name)){
			for(ExternalClassEntry offspring:allOffspring.get(name)){
				offspring.myContext.setParent(myContext);
			}
		}
		if(parentClass!=null&&getParentClass() == null){
			StringBuilder parentNameBuilder = new StringBuilder();
			parentClass.get(parentNameBuilder);
			if(!allOffspring.containsKey(parentNameBuilder.toString())){
				allOffspring.put(parentNameBuilder.toString(),new ArrayList<ExternalClassEntry>());
			}
			allOffspring.get(parentNameBuilder.toString()).add(this);
			addParentImport(parentClass);
		}
		else if(bonifideParentClass!=null){
			myContext.setParent(bonifideParentClass.myContext);
			addParentImport(parentClass);
		}
		if(interfaces!=null){
			for(Entry interfaceClass:interfaces){
				addParentImport(interfaceClass);
			}
		}

		ExternalStatement.Body constructorBody = new ExternalStatement.Body();
		constructorMethod = new ExternalMethodEntry(
				0, false, new Entry(){public void get(StringBuilder builder){new StringEntry(name).get(builder);}},
				new Entry(){public void get(StringBuilder builder){new StringEntry("*").get(builder);}},
				new ArrayList<ExternalVariableEntry>(),
				constructorBody);
		methods.put("*", constructorMethod);
		myContext.add(constructorMethod);
		myContext.add(new ExternalVariableEntry(false,new StringEntry(name),"",new StringEntry("this"),null));
		addSubImport(constructorBody);
	}
	public void addInitMethodFromClass(ExternalClassEntry initClass){
		ExternalClassHelper.addInitMethodFromClass(this,initClass);
	}
	public ExternalStatement getAsClass(){
		ExternalStatement.Parameters parameters = getInitParameters();
		ExternalStatement externalInitCalls = new ExternalStatement(";");
		ExternalStatement externalMethods = new ExternalStatement("\n\t\t\t");
		int variableIndex = 0;

		if(isInterface()){
			externalInitCalls.add(new ExternalStatement(new TabEntry(new StringEntry("setIsInterface")),new StringEntry("(true);")));
		}
		if(isEnum()){
			externalInitCalls.add(new ExternalStatement(new TabEntry(new StringEntry("setIsEnum")),new StringEntry("(true);")));
		}
		if(isStatic()){
			externalInitCalls.add(new ExternalStatement(new TabEntry(new StringEntry("setIsStatic")),new StringEntry("(true);")));
		}
		for(String variableName: variables.keySet()){
			String addVariableMethodName = "__add_variable_"+variableIndex+"__";
			externalInitCalls.add(new ExternalStatement(new StringEntry(addVariableMethodName),new StringEntry("();")));
			externalMethods.add(new ExternalStatement(new StringEntry("public void "+addVariableMethodName+"(){\n\t\t\t\taddVariable("),new StringEntry(");}"),variables.get(variableName).getAsStatement()));
			++variableIndex;
		}
		int methodIndex = 0;
		for(String methodName: methods.keySet()){
			if(!methodName.contains("*")){
				String addMethodMethodName = "__add_method_"+methodIndex+"__";
				externalInitCalls.add(new ExternalStatement(new StringEntry(addMethodMethodName),new StringEntry("();")));
				externalMethods.add(new ExternalStatement(new StringEntry("public void "+addMethodMethodName+"(){\n\t\t\t\taddMethod("),new StringEntry(");}"),methods.get(methodName).getAsStatement()));
				++methodIndex;
			}
		}
		int subClassIndex = 0;
		for(String subClassName: classes.keySet()){
			String addSubClassMethodName = "__add_sub_class_"+subClassIndex+"__";
			externalInitCalls.add(new ExternalStatement(new StringEntry(addSubClassMethodName),new StringEntry("();")));
			externalMethods.add(new ExternalStatement(new StringEntry("public void "+addSubClassMethodName+"(){\n\t\t\t\taddSubClass("),new StringEntry(");}"),
					new ExternalStatement(new StringEntry(subClassName+"._"))));
			++subClassIndex;
		}
		return new ExternalStatement("\n\t\t\t",
				new ExternalStatement(new StringEntry( "public void __INIT__(){"),new StringEntry("}"),
						new ExternalStatement(new StringEntry("super.__SETUP__("),new StringEntry(");"),parameters),
						externalInitCalls),externalMethods);
	}
	public ExternalStatement getAsStatement(){

		return new ExternalStatement(new ExternalStatement.NewObject(new ExternalStatement.TypeName("ExternalClassEntry")),new ExternalStatement(new StringEntry("{"),new StringEntry("}"),"", getAsClass()));
	}

	public void outputToFile(GeneralFlowController controller,File outputDirectory){
		StringBuilder packageNameBuilder = new StringBuilder();
		packageName.get(packageNameBuilder);
		outputDirectory.mkdirs();
		File outputFile = new File(outputDirectory,packageNameBuilder.toString().replace("..", "$$$").replace(".", File.separator).replace("$$$", "."));
		outputFile.mkdirs();
		controller.addFile(outputFile, getName().replace("\"", "")+".java", this);
	}
	public ExternalStatement.Parameters getInitParameters() {
		ExternalStatement.Parameters parameters = new ExternalStatement.Parameters();
		if(enclosingClass!=null){
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),ExternalClassHelper.getAsStatementFromEntry(enclosingClass.getFullName())));
		}
		else if(enclosingClassName!=null){
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),ExternalClassHelper.getAsStatementFromEntry(enclosingClassName)));
		}
		else {
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),new ExternalStatement(new StringEntry("null"))));
		}
		if(packageNameAsStatement != null) {
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),ExternalClassHelper.getAsStatementFromEntry(packageNameAsStatement)));
		}
		else {
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),ExternalClassHelper.getAsStatementFromEntry(packageName)));	
		}
		parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),"",new ExternalStatement.NewObject(new ExternalStatement.TypeName(new StringEntry("Entry"))),new ExternalStatement(new StringEntry("{public void get(StringBuilder builder){}}"))));
		parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),new VariableNameEntry(nameAsStatement).getAsStatement()));
		parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),ExternalClassHelper.getAsStatementFromEntry(!isInterface?!isEnum?"class ":"enum ":"inteface ")));
		if(parentClass != null){
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),ExternalClassHelper.getAsStatementFromEntry(parentClass)));
		}
		else {
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),new ExternalStatement(new StringEntry("null"))));
		}

		ExternalStatement.Parameters interfaceParameters = new ExternalStatement.Parameters();
		if(interfaces != null){
			for(Entry i: interfaces){
				interfaceParameters.add(ExternalClassHelper.getAsStatementFromEntry(i));
			}
		}
		parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),new ExternalStatement(new StringEntry("Arrays.asList("),new StringEntry(")"),new ExternalStatement.NewObject(new ExternalStatement.TypeName("Entry"),new ExternalStatement.Parameters(interfaceParameters), new ExternalStatement.ArrayParameters()))));
		if(header != null){
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),ExternalClassHelper.getAsStatementFromEntry(header)));
		}
		else {
			parameters.add(new ExternalStatement(new TabEntry(new StringEntry("\t")),new ExternalStatement(new StringEntry("null"))));
		}
		return parameters;
	}
	public Map<String, ExternalClassEntry> getSubClasses() {
		return classes;
	}
	public void removeConstructors(){
		List<String> constructorMethods = new ArrayList<String>();
		for(String methodName: methods.keySet()){
			if(methodName.contains("*")){
				constructorMethods.add(methodName);
			}
		}
		for(String methodName: constructorMethods){
			methods.remove(methodName);
		}
		displayConstructors = false;
	}
	public void removeInterfaces(){
		if(this.interfaces!=null){
			this.interfaces.clear();
		}
	}
	public boolean isInterface() {
		return isInterface;
	}
	public boolean isEnum(){
		return isEnum;
	}
	public boolean isStatic(){
		return isStatic;
	}
	public void setIsStatic(Boolean newIsStatic){
		this.isStatic = newIsStatic;
	}
}