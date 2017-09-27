package com.rem.parser.generation.classwise;
import com.rem.parser.generation.*;
import java.util.*;
public abstract class ExternalClassEntry extends ExternalImportEntry {

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
		classMap.put("ArrayList", new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry("ArrayList"),
						" class ",
						null,
						new ArrayList<Entry>(),
						new StringEntry(" class ArrayList "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(Arrays.asList(
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("sort"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		classMap.get("ArrayList").__INIT__();
	}

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
	private ExternalClassEntry enclosingClass;
	private Entry parentClass;
	private ExternalClassEntry bonifideParentClass;
	private List<Entry> interfaces;
	private boolean isInterface;

	private ExternalMethodEntry constructorMethod;
	public ExternalClassEntry(){
		super();
	}
	public void __INIT__(){}
	public void __SETUP__(Entry initialPackageName, Entry preImports, Entry initialName, String classType, Entry initialParentClass, List<Entry> initialInterfaces, Entry initialHeader, List<ExternalVariableEntry> initialVariables, List<ExternalMethodEntry> initialMethods, List<ExternalClassEntry> initialSubClasses){

		isInterface = classType.contains("interface");
		StringBuilder builder = new StringBuilder();
		initialName.get(builder);
		name = builder.toString();
		myContext = ExternalContext.getClassContext(getName());
		header = initialHeader;
		parentClass = initialParentClass;
		interfaces = initialInterfaces;
		packageName = initialPackageName;
		if(packageName!=null){
			ExternalImportEntry.packages.put(name, packageName);
		}
		allClasses.add(this);
		classMap.put(name, this);
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
		addMethod(new ExternalMethodEntry(0,false, variable.getType(),new Entry(){
			@Override
			public void get(StringBuilder builder) {
				builder.append("get");
				builder.append(Generator.camelize(variable.getName()));
			}
		},new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body(
				new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry(variable.getName())))
				)));
		if(variable.isWeak()){
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
						new ExternalVariableEntry(false,variable.getType(),
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
		ExternalContext.freeClass(subClass.getName());
		classes.put(subClass.getName(), subClass);
		addSubImport(subClass);
		subClass.isSubClass  = true;
		subClass.enclosingClass = this;
		//subClass.myContext.setParent(myContext);
		ExternalClassEntry.classMap.put(subClass.getFullName(),subClass);
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
		if(enclosingClass!=null){
			enclosingClass.getFullName(builder);
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
			for(String classKey: classes.keySet()){
				classes.get(classKey).accumulateParentImports(this);
			}
			if(getParentClass()!=null){
				for(String key:bonifideParentClass.variables.keySet()){
					if(!bonifideParentClass.variables.get(key).isWeak()&&!bonifideParentClass.variables.get(key).isStatic()){
						addImport(new ImportEntry(bonifideParentClass.variables.get(key).getType()));
					}
				}
			}
			outputImport(builder);
		}
		new TabEntry(tabs, new StringEntry("public ")).get(builder);;
		header.get(builder);
		if(parentClass!=null){
			builder.append(" extends ");
			parentClass.get(builder);
		}
		String prefix = " implements ";
		for(Entry interfaceClass:interfaces){
			builder.append(prefix);
			interfaceClass.get(builder);
			prefix = ",";
		}
		builder.append("{");
		for(String variableKey:variables.keySet()){
			variables.get(variableKey).setTabs(tabs+1);
			variables.get(variableKey).getAsMember().get(builder);
		}
		if(!isInterface){
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
			if(!variables.get(variableKey).isStatic()&&!variables.get(variableKey).isWeak()){
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
}