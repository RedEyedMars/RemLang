package com.rem.parser.generation.classwise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rem.parser.generation.Entry;
import com.rem.parser.generation.QuoteEntry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.TabEntry;
import com.rem.parser.generation.VariableNameEntry;

public class ExternalClassHelper {
	public static void setup(){
		ExternalClassEntry.classMap.put("List<T1>", new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry("List<T1>"),
						" class ",
						null,
						new ArrayList<Entry>(),
						new StringEntry(" class List<T1> "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(Arrays.asList(
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("add"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("remove"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("removeAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Boolean"), new StringEntry("isEmpty"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("sort"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Integer"), new StringEntry("size"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("T1"), new StringEntry("get"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get("List<T1>").__INIT__();
		supplimentListClass("List","List","Object");
		ExternalClassEntry.classMap.put("ArrayList", new ExternalClassEntry(){
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
										0, false, new StringEntry("void"), new StringEntry("add"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("remove"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("removeAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Boolean"), new StringEntry("isEmpty"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("sort"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Integer"), new StringEntry("size"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("T1"), new StringEntry("get"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get("ArrayList").__INIT__();
		ExternalClassEntry.classMap.put("HashMap", new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry("HashMap"),
						" class ",
						null,
						new ArrayList<Entry>(),
						new StringEntry(" class HashMap "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(Arrays.asList(
								new ExternalMethodEntry(
										0, false, new StringEntry("Object"), new StringEntry("put"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("remove"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Boolean"), new StringEntry("isEmpty"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Integer"), new StringEntry("size"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get("HashMap").__INIT__();
		ExternalClassEntry.classMap.put("Map", new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry("Map"),
						" class ",
						null,
						new ArrayList<Entry>(),
						new StringEntry(" class Map "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(Arrays.asList(
								new ExternalMethodEntry(
										0, false, new StringEntry("Object"), new StringEntry("put"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("remove"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Boolean"), new StringEntry("isEmpty"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Integer"), new StringEntry("size"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get("Map").__INIT__();
		String[] collectionMethods = new String[]{
				"Object", "add",
				"void", "remove",
				"Boolean", "isEmpty",
				"void", "addAll",
				"Integer", "size"
		};
		supplimentClass("java.util","Set",new String[][]{collectionMethods});
		supplimentClass("java.util","HashSet",new String[][]{collectionMethods});
		supplimentClass("java.util","TreeSet",new String[][]{collectionMethods});
		supplimentClass("java.util","Stack",new String[][]{collectionMethods});
		supplimentClass("java.util","Deque",new String[][]{collectionMethods});
		
		supplimentClass("java.io","File",new String[][]{}, "String", "getPath", "void", "mkdirs");
		supplimentClass("java.io","FileReader",new String[][]{});
		supplimentClass("java.io","FileWriter",new String[][]{});
		supplimentClass("java.io","BufferedReader",new String[][]{});
		supplimentClass("java.io","BufferedWriter",new String[][]{});

	}
	private static void supplimentClass(final String packageName, final String className,String[][] collectionsOfMethodParameters, String... methodParameters){
		final List<ExternalMethodEntry> classMethods = new ArrayList<ExternalMethodEntry>();
		if(collectionsOfMethodParameters!=null){
			for(String[] mParams: collectionsOfMethodParameters){
				for(int i=0;i<mParams.length;i+=2){
					classMethods.add(new ExternalMethodEntry(
							0, false, new StringEntry(mParams[i]), new StringEntry(mParams[i+1]),
							new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()));
				}
			}
		}
		if(methodParameters!=null){
			for(int i=0;i<methodParameters.length;i+=2){
				classMethods.add(new ExternalMethodEntry(
						0, false, new StringEntry(methodParameters[i]), new StringEntry(methodParameters[i+1]),
						new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()));
			}
		}
		ExternalClassEntry.classMap.put(className, new ExternalClassEntry(){
			public void __INIT__(){super.__SETUP__(
					new StringEntry(packageName),new StringEntry(""),
					new StringEntry(className)," class ",null,new ArrayList<Entry>(),
					new StringEntry(" class "+className+" "),	new ArrayList<ExternalVariableEntry>(),	classMethods,new ArrayList<ExternalClassEntry>());}});
		ExternalClassEntry.classMap.get(className).__INIT__();
	}

	public static ExternalContext supplimentListClass(final String fullName,final String listType,final String innerType) {

		ExternalClassEntry.classMap.put(fullName, new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry(fullName),
						" class ",
						new StringEntry(listType+"<T1>"),
						new ArrayList<Entry>(),
						new StringEntry(" class "+fullName+" "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get(fullName).__INIT__();
		ExternalClassEntry clazz = ExternalClassEntry.classMap.get(fullName);
		ExternalClassEntry parent = ExternalClassEntry.classMap.get(listType+"<T1>");
		for(String methodName: parent.getMethodNames()){
			if(parent.getMethod(methodName).getTypeName().contains("T1")){
				clazz.addMethod(new ExternalMethodEntry(parent.getMethod(methodName).getTypeName().replace("T1", innerType),parent.getMethod(methodName)));
			}
		}
		return ExternalClassEntry.classMap.get(fullName).getContext();
	}

	public static ExternalStatement getAsStatementFromEntry(String newEntry) {
		return new ExternalStatement(new StringEntry("\""+newEntry+"\""));
	}
	public static ExternalStatement getAsStatementFromEntry(final Entry newEntry) {
		if(newEntry instanceof VariableNameEntry){
			return ((VariableNameEntry)newEntry).getAsStatement();
		}
		else if(newEntry instanceof StringEntry){
			return new ExternalStatement(
					new ExternalStatement.NewObject(new ExternalStatement.TypeName("StringEntry"),
							new ExternalStatement.Parameters(new ExternalStatement(new QuoteEntry(
									((StringEntry)newEntry).getString().replace("\"", "\\\""))))));
		}
		else if(newEntry instanceof QuoteEntry){
			return new ExternalStatement(new StringEntry("new QuoteEntry("+((QuoteEntry)newEntry).getString())+")");
		}
		else if(newEntry instanceof TabEntry){
			return new ExternalStatement(
					new ExternalStatement.NewObject(new ExternalStatement.TypeName("TabEntry"),
							new ExternalStatement.Parameters(
									ExternalClassHelper.getAsStatementFromEntry(((TabEntry)newEntry).getEntry()))));
		}
		else if(newEntry instanceof ExternalStatement){
			return ((ExternalStatement)newEntry).getAsStatement();
		}
		else if(newEntry instanceof ExternalClassEntry){
			return ((ExternalClassEntry)newEntry).getAsStatement();
		}
		else if(newEntry instanceof ExternalMethodEntry){
			return ((ExternalMethodEntry)newEntry).getAsStatement();
		}
		else if(newEntry instanceof ExternalVariableEntry){
			return ((ExternalVariableEntry)newEntry).getAsStatement();
		}
		else {
			return new ExternalStatement(
					new ExternalStatement.NewObject(
							new ExternalStatement.TypeName("Entry")),
					new ExternalStatement(new StringEntry("{public void get(StringBuilder builder){builder.append(\""),new StringEntry("\");}}"),
							new ExternalStatement(new Entry(){public void get(StringBuilder builder){newEntry.get(builder);}})));
		}
	}

	public static void addInitMethodFromClass(ExternalClassEntry externalClassEntry, ExternalClassEntry initClass) {
		ExternalStatement.Body initBody = new ExternalStatement.Body();
		initBody.add(new ExternalStatement(new TabEntry(new StringEntry("super.__SETUP__(")),"",
				initClass.getInitParameters(),
				new ExternalStatement(new StringEntry(");"))));
		if(initClass.isInterface()){
			initBody.add(new ExternalStatement(new TabEntry(new StringEntry("setIsInterface")),new StringEntry("(true);")));
		}
		if(initClass.isEnum()){
			initBody.add(new ExternalStatement(new TabEntry(new StringEntry("setIsEnum")),new StringEntry("(true);")));
		}
		if(initClass.isStatic()){
			initBody.add(new ExternalStatement(new TabEntry(new StringEntry("setIsStatic")),new StringEntry("(true);")));
		}
		else {
			initBody.add(new ExternalStatement(new TabEntry(new StringEntry("setIsStatic")),new StringEntry("(false);")));
		}
		int variableIndex = 0;
		for(String variableName: initClass.getVariables().keySet()){
			String addVariableMethodName = "__add_variable_"+variableIndex+"__";
			initBody.add(new ExternalStatement(new TabEntry(new StringEntry(addVariableMethodName)),new StringEntry("();")));
			externalClassEntry.addMethod(
					new ExternalMethodEntry(0,false,new StringEntry("void"),null,new StringEntry(addVariableMethodName),new ArrayList<ExternalVariableEntry>(),
							new ExternalStatement.Body(
									new ExternalStatement(new TabEntry(new StringEntry("addVariable(")),initClass.getVariable(variableName).getAsStatement()),new ExternalStatement(new StringEntry(");")))));
			++variableIndex;
		}
		int methodIndex = 0;
		for(String methodName: initClass.getMethodNames()){
			
				String addMethodMethodName = "__add_method_"+methodIndex+"__";
				initBody.add(new ExternalStatement(new TabEntry(new StringEntry(addMethodMethodName)),new StringEntry("();")));
				externalClassEntry.addMethod(
						new ExternalMethodEntry(0,false,new StringEntry("void"),null,new StringEntry(addMethodMethodName),new ArrayList<ExternalVariableEntry>(),
								new ExternalStatement.Body(
										new ExternalStatement(
												new TabEntry(new StringEntry("addMethod(")),initClass.getMethod(methodName).getAsStatement()),new ExternalStatement(new StringEntry(");")))));
				++methodIndex;
		}
		int subClassIndex = 0;
		for(String subClassName: initClass.getSubClasses().keySet()){
			String addSubClassMethodName = "__add_sub_class_"+subClassIndex+"__";
			initBody.add(new ExternalStatement(new TabEntry(new StringEntry(addSubClassMethodName)),new StringEntry("();")));
			externalClassEntry.addMethod(
					new ExternalMethodEntry(0,false,new StringEntry("void"),null,new StringEntry(addSubClassMethodName),new ArrayList<ExternalVariableEntry>(),
							new ExternalStatement.Body(
									new ExternalStatement(new TabEntry(new StringEntry("addSubClass(")),new ExternalStatement(new StringEntry(subClassName+"._"))),new ExternalStatement(new StringEntry(");")))));
			++subClassIndex;
		}
		for(Entry templateType: initClass.getTemplateTypes()){
			initBody.add(new ExternalStatement(new TabEntry(new StringEntry("addTemplateType(")),new StringEntry(");"), ExternalClassHelper.getAsStatementFromEntry(templateType)));
		}

		externalClassEntry.addMethod(
				new ExternalMethodEntry(0,false,new StringEntry("void"),null,new StringEntry("__INIT__"),new ArrayList<ExternalVariableEntry>(),
						initBody));
	}
}
