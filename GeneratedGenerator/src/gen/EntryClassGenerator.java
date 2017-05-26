package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import gen.checks.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class EntryClassGenerator extends Generator {

	private File directory = null;
	private Integer constructorIndex = 0;


	public static final Element outlineElement = new Element("outline",new String[]{"package gen.entries;\n\n"+
			"import java.io.*;\n"+
			"import java.util.*;\n"+
			"import com.rem.parser.*;\n"+
			"import com.rem.parser.generation.*;\n"+
			"import com.rem.parser.token.*;\n"+
			"import gen.*;\n"+
			"import gen.checks.*;\n"+
			"import gen.properties.*;\n"+
			"import lists.*;\n\n"+
			"public class ",/*Class Name*/"Entry implements Entry",/*Properties*/" {\n\tpublic ",/*Class name*/"Entry getSelf(){\n\t\treturn this;\n\t}\n",/*Contents*/"\n}"});
	public static final Element bodyElement = new Element("body",new String[]{"{",/*Body*/"\n\t}"});
	public static final Element implementsElement = new Element("implements",new String[]{",",/*Properties*/""});
	public static final Element publicCallElement = new Element("publicCall",new String[]{"public ",/*Contents*/""});
	public static final Element privateCallElement = new Element("privateCall",new String[]{"private ",/*Contents*/""});
	public static final Element appendToBuilderElement = new Element("appendToBuilder",new String[]{"",/*Entry*/".get(builder);"});
	public static final Element methodDeclarationElement = new Element("methodDeclaration",new String[]{"public ",/*Type*/" ",/*Method Header*/"",/*MethodBody*/""});
	public static final Element getMethodDeclarationElement = new Element("getMethodDeclaration",new String[]{"public void get(StringBuilder builder){",/*MethodBody*/"\n\t}"});
	public static final Element constructorDeclarationElement = new Element("constructorDeclaration",new String[]{"public ",/*Type*/"Entry(",/*Parameters*/")",/*MethodBody*/""});
	public static final Element blankOutputMethodElement = new Element("blankOutputMethod",new String[]{"\n\tpublic void get(StringBuilder builder){\n\t}"});
	public EntryClassGenerator(){
		addElement("outline",outlineElement);
		addElement("body",bodyElement);
		addElement("implements",implementsElement);
		addElement("publicCall",publicCallElement);
		addElement("privateCall",privateCallElement);
		addElement("appendToBuilder",appendToBuilderElement);
		addElement("methodDeclaration",methodDeclarationElement);
		addElement("getMethodDeclaration",getMethodDeclarationElement);
		addElement("constructorDeclaration",constructorDeclarationElement);
		addElement("blankOutputMethod",blankOutputMethodElement);
	}
	public void setup(ParseContext data){
		this.addPage();
		directory = new File(Generators.generator.getDirectory(),"entries");
		directory.mkdirs();
	}
	public void generate(ParseContext data){
		ParseList dataList = (ParseList)data.getList("entry_class_definitions");
		Generators.entryClass.generateAll(dataList.getNewTokens(),"entry_dec");
	}
	public void generateRoot(IToken root){
		String className = root.get("entryClassName").getString();
		Map<String,Entry> variables = (Map<String,Entry>)new LinkedHashMap<String,Entry>();
		Map<String,Entry> methods = (Map<String,Entry>)new LinkedHashMap<String,Entry>();
		Map<String,String> methodTypes = (Map<String,String>)new LinkedHashMap<String,String>();
		ListEntry propertyNames = new ListEntry();
		ListEntry constants = new ListEntry();
		constants.setDelimiter("");
		Set<String> methodNameSet = (Set<String>)new HashSet<String>();
		Set<String> variableNameSet = (Set<String>)new HashSet<String>();
		ListEntry constructors = new ListEntry();
		constructors.setDelimiter("");
		String realClassName = Generators.entryClass.camelize(className);
		Generators.generator.addContext(className,null,Generators.generator.LOCAL_CONTEXT,Generators.generator.NO_DEFAULT_TOKEN);
		if((root.get("implements") != null)){
			List<IToken> properties = (List<IToken>)root.get("implements").getAll("property_names");
			if((properties != null)){
				for(IToken properT:properties){
					IToken propToken = (IToken)properT;
					String propertyName = propToken.getString();
					String propertyClassName = Generators.entryClass.buildString("I",Generators.entryClass.camelize(propertyName));
					propertyNames.add(new StringEntry(propertyClassName));
					Map<String,Map<String,Entry>> propertyVariables = (Map<String,Map<String,Entry>>)Generators.property.getVariablesOfProperties();
					if((propertyVariables.containsKey(propertyName))){
						Set<String> varKeySet = (Set<String>)propertyVariables.get(propertyName).keySet();
						for(String key:varKeySet){
							variables.put(key,propertyVariables.get(propertyName).get(key));
							variableNameSet.add(key);
							Map<String,Map<String,Map<String,VariableEntry>>> contexts = (Map<String,Map<String,Map<String,VariableEntry>>>)Generators.generator.getContexts();
							String localContext = Generators.generator.LOCAL_CONTEXT;
							Map<String,Map<String,VariableEntry>> actualProperies = (Map<String,Map<String,VariableEntry>>)Generators.property.getActualVariablesOfProperties();
							contexts.get(className).get(localContext).put(key,actualProperies.get(propertyName).get(key));
						}
					}
					else {
						ListEntry listener = new ListEntry();
						Generators.property.addVariableListener(propertyName,className,listener,variableNameSet);
						variables.put(Generators.entryClass.buildString("$",propertyName),listener);
						checks.add(new PropertyDefinedCheck(propertyVariables,propertyName,"EntryClass generateRoot(001)"));
					}
					Map<String,Map<String,Entry>> propertyMethods = (Map<String,Map<String,Entry>>)Generators.property.getMethodsOfProperties();
					if((propertyMethods.containsKey(propertyName))){
						Map<String,Entry> methodMap = (Map<String,Entry>)propertyMethods.get(propertyName);
						Map<String,Map<String,ITypeListener>> propertyMethodTypes = (Map<String,Map<String,ITypeListener>>)Generators.property.getMethodTypesOfProperties();
						Map<String,ITypeListener> typeMap = (Map<String,ITypeListener>)propertyMethodTypes.get(propertyName);
						Set<String> keySet = (Set<String>)methodMap.keySet();
						for(String key:keySet){
							methods.put(key,methodMap.get(key));
							methodNameSet.add(key);
							if((typeMap.containsKey(key))){
								if((typeMap.get(key).hasType())){
									methodTypes.put(key,typeMap.get(key).getType());
								}
								else {
									methodTypes.put(key,typeMap.get(key).getDefaultType());
								}
							}
						}
					}
					else {
						ListEntry listener = new ListEntry();
						Generators.property.addMethodListener(propertyName,listener,methodNameSet);
						methods.put(Generators.entryClass.buildString("$",propertyName),listener);
						checks.add(new PropertyDefinedCheck(propertyVariables,propertyName,"EntryClass generateRoot(002)"));
					}
				}
			}
		}
		Boolean hasOutput = false;
		List<IToken> elementEntryClassElement = root.getAll("entry_class_element");
		if(elementEntryClassElement != null){
			for(IToken element:elementEntryClassElement){
				for(IToken.Key atomKey:element.keySet()){
					if("constant_declaration".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String variableName = atom.get("variableName").getString();
						constants.add(new TabEntry(1,new ListEntry(generateVariableDeclaration(atom,className,true))));
						variableNameSet.add(variableName);
						Map<String,Map<String,Map<String,VariableEntry>>> contexts = (Map<String,Map<String,Map<String,VariableEntry>>>)Generators.generator.getContexts();
						String localContext = Generators.generator.LOCAL_CONTEXT;
						contexts.get(className).get(localContext).get(variableName).setFinal(true);
						contexts.get(className).get(localContext).get(variableName).setStatic(true);
					}
					else if("variable_declaration".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String variableName = atom.get("variableName").getString();
						variables.put(variableName,new TabEntry(1,new ListEntry(generateVariableDeclaration(atom,className,false))));
						MethodEntry variableGetMethod = (MethodEntry)generateVariableDeclarationMethod(atom,className);
						methods.put(variableGetMethod.getMethodName(),new TabEntry(0,new ListEntry(variableGetMethod)));
						variableNameSet.add(variableName);
					}
					else if("entry_declaration".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String variableName = atom.get("entryName").getString();
						Map<String,Map<String,Map<String,VariableEntry>>> contexts = (Map<String,Map<String,Map<String,VariableEntry>>>)Generators.generator.getContexts();
						String localContext = Generators.generator.LOCAL_CONTEXT;
						Entry koodo = Generators.generator.generateEntryDeclaration(atom,1,className,localContext);
						VariableEntry variable = (VariableEntry)contexts.get(className).get(localContext).get(variableName);
						TypeEntry type = new TypeEntry(variable);
						variables.put(variableName,new TabEntry(1,new ListEntry(new ElementEntry(EntryClassGenerator.privateCallElement,new ListEntry(variable)))));
						String getMethodName = Generators.entryClass.buildString("get",Generators.entryClass.camelize(variableName));
						methods.put(variableName,new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(type,new StringEntry(getMethodName),new ListEntry(),new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.returnCallElement,new ListEntry(new StringEntry(variableName))))))));
						variableNameSet.add(variableName);
					}
					else if("constructor".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String constructorName = Generators.entryClass.buildString("constructor",constructorIndex.toString());
						constructorIndex = constructorIndex + 1;
						Generators.generator.addContext(className,Generators.generator.LOCAL_CONTEXT,constructorName,Generators.generator.NO_DEFAULT_TOKEN);
						ListEntry parameters = new ListEntry();
						List<IToken> parameterParameter = atom.getAll("parameter");
						if(parameterParameter != null){
							for(IToken parameter:parameterParameter){
								VariableEntry variable = (VariableEntry)Generators.generator.getParameter(parameter,className,Generators.generator.LOCAL_CONTEXT,constructorName);
								parameters.add(variable);
							}
						}
						constructors.add(new TabEntry(1,new ListEntry(new ElementEntry(EntryClassGenerator.constructorDeclarationElement,new ListEntry(new StringEntry(realClassName),parameters,generateEntryMethodBody(atom,null,className,constructorName))))));
					}
					else if("output_method".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						hasOutput = true;
						MethodEntry header = new MethodEntry("noSubjectCall",new ListEntry(new StringEntry("get"),new StringEntry("StringBuilder builder")));
						Generators.generator.addContext(className,Generators.generator.LOCAL_CONTEXT,"$OUTPUT",Generators.generator.NO_DEFAULT_TOKEN);
						ListEntry body = new ListEntry();
						body.setDelimiter("");
						if((atom.get("body") != null)){
							body.add(Generators.entryClass.generateEntryMethodBody(atom.get("body"),header,className,null));
						}
						Boolean first = true;
						List<IToken> statementEntryValues = atom.getAll("entry_values");
						if(statementEntryValues != null){
							for(IToken statement:statementEntryValues){
								IToken breck = statement.get("break");
								if((breck != null)){
									first = true;
								}
								else {
									IToken value = statement.get("value");
									IToken ifStatement = statement.get("ifStatement");
									if((ifStatement != null)){
										ListEntry ifBody = new ListEntry();
										ifBody.setDelimiter("");
										for(IToken.Key quarkKey:value.keySet()){
											if("entry_definition".equals(quarkKey.getName())){
												IToken quark = value.get(quarkKey);
												Entry ifLine = Generators.generator.generateEntryDefinition(quark,className,"$OUTPUT");
												ifBody.add(new TabEntry(3,new ListEntry(new ElementEntry(EntryClassGenerator.appendToBuilderElement,new ListEntry(ifLine)))));
											}
											else if("entry_names".equals(quarkKey.getName())){
												IToken quark = value.get(quarkKey);
												ifBody.add(new TabEntry(3,new ListEntry(new ElementEntry(EntryClassGenerator.appendToBuilderElement,new ListEntry(new StringEntry(quark.getString()))))));
											}
										}
										if((ifStatement.get("otherwise") == null)){
											Entry booleanStatement = Generators.generator.generateBooleanStatement(ifStatement.get("boolean_statement"),className,"$OUTPUT");
											if((first == true)){
												body.add(new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.ifStatementCallElement,new ListEntry(booleanStatement,ifBody)))));
											}
											else {
												body.add(new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.elseIfStatementCallElement,new ListEntry(booleanStatement,ifBody)))));
											}
										}
										else {
											body.add(new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.elseStatementCallElement,new ListEntry(ifBody)))));
										}
										body.add(new TabEntry(2,new ListEntry(new StringEntry("}"))));
										first = false;
									}
									else {
										first = true;
										for(IToken.Key quarkKey:value.keySet()){
											if("entry_definition".equals(quarkKey.getName())){
												IToken quark = value.get(quarkKey);
												Entry ifLine = Generators.generator.generateEntryDefinition(quark,className,"$OUTPUT");
												body.add(new TabEntry(2,new ListEntry(new ElementEntry(EntryClassGenerator.appendToBuilderElement,new ListEntry(ifLine)))));
											}
											else if("entry_names".equals(quarkKey.getName())){
												IToken quark = value.get(quarkKey);
												body.add(new TabEntry(2,new ListEntry(new ElementEntry(EntryClassGenerator.appendToBuilderElement,new ListEntry(new StringEntry(quark.getString()))))));
											}
										}
									}
								}
							}
						}
						methods.put("$OUTPUT",new TabEntry(1,new ListEntry(new ElementEntry(EntryClassGenerator.getMethodDeclarationElement,new ListEntry(body)))));
					}
					else if("entry_method".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						MethodEntry header = (MethodEntry)generateEntryMethodHeader(atom,className);
						String methodName = header.getMethodName();
						if((methodNameSet.contains(methodName))){
							header.setDefaultType(methodTypes.get(methodName));
						}
						TypeEntry type = new TypeEntry(header);
						methods.put(methodName,new TabEntry(1,new ListEntry(new ElementEntry(EntryClassGenerator.methodDeclarationElement,new ListEntry(type,header,generateEntryMethodBody(atom,header,className,methodName))))));
					}
				}
			}
		}
		Entry implementsProperties = new ListEntry();
		if((!propertyNames.isEmpty())){
			implementsProperties = new ElementEntry(EntryClassGenerator.implementsElement,new ListEntry(propertyNames));
		}
		ListEntry variableList = new ListEntry();
		variableList.setDelimiter("");
		Set<String> variablesKeySet = (Set<String>)variables.keySet();
		for(String varName:variablesKeySet){
			variableList.add(variables.get(varName));
		}
		ListEntry methodList = new ListEntry();
		methodList.setDelimiter("");
		Set<String> methodsKeySet = (Set<String>)methods.keySet();
		for(String methodName:methodsKeySet){
			methodList.add(methods.get(methodName));
		}
		ListEntry complete = new ListEntry(constants,variableList,constructors,methodList);
		complete.setDelimiter("\n");
		if((hasOutput == false)){
			methodList.add(new ElementEntry(EntryClassGenerator.blankOutputMethodElement,new ListEntry()));
		}
		Generators.entryClass.addFile(directory,Generators.entryClass.buildString(realClassName,"Entry.java"),new ListEntry(new StringEntry(realClassName),implementsProperties,new StringEntry(realClassName),complete));
	}
	public Entry generateVariableDeclaration(IToken variableDeclaration,String contextName,Boolean isPublic){
		String localContext = Generators.generator.LOCAL_CONTEXT;
		if((isPublic == true)){
			return new ElementEntry(EntryClassGenerator.publicCallElement,new ListEntry(Generators.generator.generateVariableDeclaration(variableDeclaration,contextName,localContext)));
		}
		else {
			return new ElementEntry(EntryClassGenerator.privateCallElement,new ListEntry(Generators.generator.generateVariableDeclaration(variableDeclaration,contextName,localContext)));
		}
	}
	public Entry generateVariableDeclarationMethod(IToken variableDeclarationMethod,String contextName){
		String variableName = variableDeclarationMethod.get("variableName").getString();
		VariableEntry variable = null;
		String localContext = Generators.generator.LOCAL_CONTEXT;
		Map<String,Map<String,Map<String,VariableEntry>>> contexts = (Map<String,Map<String,Map<String,VariableEntry>>>)Generators.generator.getContexts();
		if((!contexts.get(contextName).get(localContext).containsKey(variableName))){
			String unknownType = ITypeListener.TYPE_UNKNOWN;
			variable = new VariableEntry(variableName,unknownType);
			String castToType = Generators.generator.getCastType(variableDeclarationMethod,contextName,localContext);
			if((castToType != null)){
				variable.changeType(castToType);
			}
			contexts.get(contextName).get(localContext).put(variableName,variable);
		}
		else {
			variable = contexts.get(contextName).get(localContext).get(variableName);
		}
		TypeEntry type = new TypeEntry(variable);
		String getMethodName = Generators.entryClass.buildString("get",Generators.entryClass.camelize(variableName));
		MethodEntry ret = (MethodEntry)new MethodEntry("methodDeclaration",new ListEntry(type,new StringEntry(getMethodName),new ListEntry(),new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.returnCallElement,new ListEntry(new StringEntry(variableName)))))));
		ret.setMethodNames(getMethodName);
		return ret;
	}
	public Entry generateEntryMethodHeader(IToken entryMethodHeader,String contextName){
		String localContext = Generators.generator.LOCAL_CONTEXT;
		Map<String,Map<String,Map<String,VariableEntry>>> contexts = (Map<String,Map<String,Map<String,VariableEntry>>>)Generators.generator.getContexts();
		String returnType = Generators.generator.getCastType(entryMethodHeader,contextName,Generators.generator.LOCAL_CONTEXT);
		ListEntry parameters = new ListEntry();
		String methodName = null;
		for(IToken.Key elementKey:entryMethodHeader.keySet()){
			if("methodName".equals(elementKey.getName())){
				IToken element = entryMethodHeader.get(elementKey);
				methodName = element.getString();
				Generators.generator.addContext(contextName,Generators.generator.LOCAL_CONTEXT,methodName,Generators.generator.NO_DEFAULT_TOKEN);
			}
			else if("parameter".equals(elementKey.getName())){
				IToken element = entryMethodHeader.get(elementKey);
				parameters.add(Generators.generator.getParameter(element,contextName,Generators.generator.LOCAL_CONTEXT,methodName));
			}
			else if("methodType".equals(elementKey.getName())){
				IToken element = entryMethodHeader.get(elementKey);
				returnType = element.getString();
			}
		}
		MethodEntry ret = new MethodEntry("noSubjectCall",new ListEntry(new StringEntry(methodName),parameters));
		if((returnType != null)){
			ret.changeType(returnType);
		}
		else {
			ret.setDefaultType("void");
		}
		ret.setMethodNames(methodName);
		return ret;
	}
	public Entry generateEntryMethodBody(IToken entryMethodBody,MethodEntry method,String contextName,String methodName){
		ListEntry methodBody = new ListEntry();
		methodBody.setDelimiter("");
		TypeEntry retType = new TypeEntry();
		Boolean isOutput = (methodName == null);
		if((isOutput == true)){
			methodName = "$OUTPUT";
		}
		for(IToken.Key elementKey:entryMethodBody.keySet()){
			if("entry_declaration".equals(elementKey.getName())){
				IToken element = entryMethodBody.get(elementKey);
				methodBody.add(Generators.generator.generateEntryDeclaration(element,2,contextName,methodName));
			}
			else if("body_element".equals(elementKey.getName())){
				IToken element = entryMethodBody.get(elementKey);
				methodBody.add(Generators.generator.generateBodyElement(element,2,contextName,methodName,retType));
			}
		}
		ITypeListener subject = (ITypeListener)retType.getSubject();
		if((method != null && subject != null && subject.hasType())){
			method.changeType(subject.getType());
		}
		else {
			if((method != null)){
				method.changeType(retType.getDefaultType());
			}
		}
		if((isOutput == true)){
			return methodBody;
		}
		else {
			return new ElementEntry(EntryClassGenerator.bodyElement,new ListEntry(methodBody));
		}
	}

	public File getDirectory(){
		return directory;
	}

	public Integer getConstructorIndex(){
		return constructorIndex;
	}

	public String getName(){
		return "EntryClass";
	}

	public IParser getLazyNameParser(){
		return null;
	}
}