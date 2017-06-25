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

public class GeneratorGenerator extends Generator {

	public static final String DEFAULT_TOKEN = "$token";
	public static final VariableEntry NO_DEFAULT_TOKEN = new VariableEntry();
	public static final String LOCAL_CONTEXT = "$LOCAL";
	private Boolean truth = true;
	private File directory = (File)null;
	private String file = null;
	private Set<String> classNames = (Set<String>)new HashSet<String>();
	private List<String> generatorNames = (List<String>)new ArrayList<String>();
	private Map<String,Map<String,Map<String,VariableEntry>>> contexts = (Map<String,Map<String,Map<String,VariableEntry>>>)new HashMap<String,Map<String,Map<String,VariableEntry>>>();
	private Map<String,Map<String,List<ITypeListener>>> methodParameters = (Map<String,Map<String,List<ITypeListener>>>)new HashMap<String,Map<String,List<ITypeListener>>>();
	private Map<String,ListEntry> elementBodies = (Map<String,ListEntry>)new HashMap<String,ListEntry>();
	private Map<String,ListEntry> constructorBodies = (Map<String,ListEntry>)new HashMap<String,ListEntry>();
	private Integer ifIndex = 0;
	private String generatorName = "";
	private StringEntry new_method = new StringEntry("");


	public static final Element outlineElement = new Element("outline",new String[]{"package gen;\n\n"+
			"import java.io.*;\n"+
			"import java.util.*;\n"+
			"import com.rem.parser.*;\n"+
			"import com.rem.parser.generation.*;\n"+
			"import com.rem.parser.token.*;\n"+
			"import com.rem.parser.parser.*;\n"+
			"import gen.checks.*;\n"+
			"import gen.entries.*;\n"+
			"import gen.properties.*;\n"+
			"import lists.*;\n\n"+
			"public class ",/*Class Name*/" extends ",/*Extension*/" {\n",/*Contents*/"\n}"});
	public static final Element typeAndNameElement = new Element("typeAndName",new String[]{"",/*Type*/" ",/*Name*/""});
	public static final Element variableDeclarationElement = new Element("variableDeclaration",new String[]{"",/*Type Name*/" ",/*Variable Name*/" = ",/*Assignment*/";"});
	public static final Element variablePrototypeElement = new Element("variablePrototype",new String[]{"",/*Type Name*/" ",/*Variable Name*/""});
	public static final Element flowMainElement = new Element("flowMain",new String[]{"\tpublic static void main(String[] args){\n"+
			"\t\tif(args.length==1){\n\t\t\tnew ",/*Meta Name*/"Flow().parse(args[0]);\n\t\t}\n\t\telse {\n\t\t\tSystem.err.println(\"No filename provided!\");\n\t\t}\n\t}\n"});
	public static final Element generatorCallElement = new Element("generatorCall",new String[]{"Generators.",/*Generator Name*/""});
	public static final Element generatorMethodCallElement = new Element("generatorMethodCall",new String[]{"Generators.",/*Generator Name*/".",/*Method*/""});
	public static final Element generatorDeclarationElement = new Element("generatorDeclaration",new String[]{"",/*Class Name*/"Generator ",/*Class name*/" = new ",/*Assignment*/"Generator();"});
	public static final Element generatorListDeclarationElement = new Element("generatorListDeclaration",new String[]{"Generator[] _ = new Generator[]{",/*Generator Names*/"};"});
	public static final Element methodCallElement = new Element("methodCall",new String[]{"",/*Subject Name*/".",/*Method Name*/"(",/*Parameters*/")"});
	public static final Element generatorElementElement = new Element("generatorElement",new String[]{"",/*Generator Name*/"Generator.",/*Element Name*/"Element"});
	public static final Element generatorElementWithVariableElement = new Element("generatorElementWithVariable",new String[]{"Generators.",/*Generator Name*/",",/*Variable Name*/""});
	public static final Element castCallElement = new Element("castCall",new String[]{"(",/*Type Name*/")",/*Subject*/""});
	public static final Element exactCallElement = new Element("exactCall",new String[]{"",/*Method Name*/""});
	public static final Element exactDuoCallElement = new Element("exactDuoCall",new String[]{"",/*First*/"",/*Second*/""});
	public static final Element exactWithAngleCallElement = new Element("exactWithAngleCall",new String[]{"",/*Method Name*/"<",/*Parameters*/">"});
	public static final Element newObjectCallElement = new Element("newObjectCall",new String[]{"new ",/*Type Name*/"(",/*Parameters*/")"});
	public static final Element newMethodWithBracesCallElement = new Element("newMethodWithBracesCall",new String[]{"new ",/*Type Name*/"<",/*Parameters*/">()"});
	public static final Element operatorCallElement = new Element("operatorCall",new String[]{"",/*Left*/" ",/*Operator*/" ",/*Right*/""});
	public static final Element noSubjectCallElement = new Element("noSubjectCall",new String[]{"",/*Method Name*/"(",/*Parameter*/")"});
	public static final Element errorCallElement = new Element("errorCall",new String[]{"throw new UnableToGenerateException(",/*Token Name*/",",/*Error Output*/");"});
	public static final Element returnCallElement = new Element("returnCall",new String[]{"return ",/*Output*/";"});
	public static final Element returnCallWithCastElement = new Element("returnCallWithCast",new String[]{"return (",/*Type*/")",/*Output*/";"});
	public static final Element newBooleanCallElement = new Element("newBooleanCall",new String[]{"(",/*Parameters*/")"});
	public static final Element setCallElement = new Element("setCall",new String[]{"",/*Subject*/" = ",/*Argument*/";"});
	public static final Element equalsCallElement = new Element("equalsCall",new String[]{"",/*Subject*/".equals(",/*Argument*/")"});
	public static final Element notEqualsCallElement = new Element("notEqualsCall",new String[]{"!",/*Subject*/".equals(",/*Argument*/")"});
	public static final Element newStringListCallElement = new Element("newStringListCall",new String[]{"new String[]{",/*Parameters*/"}"});
	public static final Element staticGetVariableCallElement = new Element("staticGetVariableCall",new String[]{"",/*Class Name*/".",/*Variable Name*/"",/*Blank*/""});
	public static final Element staticMethodCallElement = new Element("staticMethodCall",new String[]{"",/*Class Name*/".",/*Method Name*/"(",/*Parameters*/")"});
	public static final Element ifStatementCallElement = new Element("ifStatementCall",new String[]{"if(",/*Boolean*/"){",/*Body*/""});
	public static final Element elseIfStatementCallElement = new Element("elseIfStatementCall",new String[]{"else if(",/*Boolean*/"){",/*Body*/""});
	public static final Element elseStatementCallElement = new Element("elseStatementCall",new String[]{"else {",/*Body*/""});
	public static final Element forStatementCallElement = new Element("forStatementCall",new String[]{"for(",/*Key Name*/":",/*Token Name*/"){",/*Body*/""});
	public static final Element forNumberStatementCallElement = new Element("forNumberStatementCall",new String[]{"for(",/*Initialization*/"",/*Left*/"<",/*Right*/";++",/*Iterate*/"){",/*Body*/""});
	public static final Element tokenForStatementCallElement = new Element("tokenForStatementCall",new String[]{"for(IToken.Key ",/*Key Name*/":",/*Token Name*/".keySet()){",/*Body*/""});
	public static final Element methodDeclarationElement = new Element("methodDeclaration",new String[]{"\tpublic ",/*Method Type*/" ",/*Method Name*/"(",/*Method Parameters*/"){",/*Body*/"\n\t}"});
	public static final Element getMethodDeclarationElement = new Element("getMethodDeclaration",new String[]{"public ",/*Method Type*/" get",/*Variable Name*/"(){\n\t\treturn ",/*Member Name*/";\n\t}"});
	public static final Element constructorElement = new Element("constructor",new String[]{"\tpublic ",/*Class Name*/"Generator(){",/*Constructor Body*/"\n\t}"});
	public static final Element publicStaticFinalVariableDeclarationElement = new Element("publicStaticFinalVariableDeclaration",new String[]{"public static final ",/*Type*/" ",/*Name*/" = ",/*Assignement*/";"});
	public static final Element semicolonedElement = new Element("semicoloned",new String[]{"",/*Body*/";"});
	public GeneratorGenerator(){
		addElement("outline",outlineElement);
		addElement("typeAndName",typeAndNameElement);
		addElement("variableDeclaration",variableDeclarationElement);
		addElement("variablePrototype",variablePrototypeElement);
		addElement("flowMain",flowMainElement);
		addElement("generatorCall",generatorCallElement);
		addElement("generatorMethodCall",generatorMethodCallElement);
		addElement("generatorDeclaration",generatorDeclarationElement);
		addElement("generatorListDeclaration",generatorListDeclarationElement);
		addElement("methodCall",methodCallElement);
		addElement("generatorElement",generatorElementElement);
		addElement("generatorElementWithVariable",generatorElementWithVariableElement);
		addElement("castCall",castCallElement);
		addElement("exactCall",exactCallElement);
		addElement("exactDuoCall",exactDuoCallElement);
		addElement("exactWithAngleCall",exactWithAngleCallElement);
		addElement("newObjectCall",newObjectCallElement);
		addElement("newMethodWithBracesCall",newMethodWithBracesCallElement);
		addElement("operatorCall",operatorCallElement);
		addElement("noSubjectCall",noSubjectCallElement);
		addElement("errorCall",errorCallElement);
		addElement("returnCall",returnCallElement);
		addElement("returnCallWithCast",returnCallWithCastElement);
		addElement("newBooleanCall",newBooleanCallElement);
		addElement("setCall",setCallElement);
		addElement("equalsCall",equalsCallElement);
		addElement("notEqualsCall",notEqualsCallElement);
		addElement("newStringListCall",newStringListCallElement);
		addElement("staticGetVariableCall",staticGetVariableCallElement);
		addElement("staticMethodCall",staticMethodCallElement);
		addElement("ifStatementCall",ifStatementCallElement);
		addElement("elseIfStatementCall",elseIfStatementCallElement);
		addElement("elseStatementCall",elseStatementCallElement);
		addElement("forStatementCall",forStatementCallElement);
		addElement("forNumberStatementCall",forNumberStatementCallElement);
		addElement("tokenForStatementCall",tokenForStatementCallElement);
		addElement("methodDeclaration",methodDeclarationElement);
		addElement("getMethodDeclaration",getMethodDeclarationElement);
		addElement("constructor",constructorElement);
		addElement("publicStaticFinalVariableDeclaration",publicStaticFinalVariableDeclarationElement);
		addElement("semicoloned",semicolonedElement);
	}
	public void generateRoot(IToken root){
		new_method = MethodEntry.NEW_METHOD;
		String className = "$";
		ListEntry variableDeclarations = new ListEntry();
		variableDeclarations.setDelimiter("\n\t");
		variableDeclarations.startWithDelimiter(true);
		ListEntry methodDeclarations = new ListEntry();
		methodDeclarations.setDelimiter("\n");
		ListEntry variableMethods = new ListEntry();
		variableMethods.setDelimiter("\n");
		ListEntry classElements = new ListEntry(variableDeclarations,new StringEntry(""),methodDeclarations,variableMethods);
		classElements.setDelimiter("\n");
		HashMap<String,Map<String,VariableEntry>> newContext = new HashMap<String,Map<String,VariableEntry>>();
		for(IToken.Key classElementKey:root.keySet()){
			if("className".equals(classElementKey.getName())){
				IToken classElement = root.get(classElementKey);
				String genName = classElement.getString();
				generatorName = genName;
				className = Generators.generator.camelize(genName);
				classNames.add(className);
				generatorNames.add(genName);
				contexts.put(className,newContext);
				ListEntry elementBody = new ListEntry();
				elementBody.setDelimiter("");
				ListEntry constructorBody = new ListEntry();
				constructorBody.setDelimiter("");
				elementBodies.put(className,elementBody);
				constructorBodies.put(className,constructorBody);
				methodParameters.put(className,new HashMap<String,List<ITypeListener>>());
				HashMap<String,VariableEntry> varMap = new HashMap<String,VariableEntry>();
				varMap.put(DEFAULT_TOKEN,new VariableEntry("root","IToken",null));
				newContext.put(LOCAL_CONTEXT,varMap);
				methodDeclarations.add(elementBodies.get(className));
				methodDeclarations.add(new ElementEntry(GeneratorGenerator.constructorElement,new ListEntry(new StringEntry(className),constructorBody)));
			}
			else if("auxillary_declaration".equals(classElementKey.getName())){
				IToken classElement = root.get(classElementKey);
				methodDeclarations.add(generateAuxillaryDeclaration(classElement,className,LOCAL_CONTEXT,true));
			}
			else if("variable_declaration".equals(classElementKey.getName())){
				IToken classElement = root.get(classElementKey);
				VariableEntry newVariable = (VariableEntry)generateVariableDeclaration(classElement,className,LOCAL_CONTEXT);
				newVariable.setAccess(VariableEntry.PRIVATE_ACCESS);
				variableDeclarations.add(newVariable);
				String variableName = Generators.generator.camelize(newVariable.getName());
				String memberName = newVariable.getName();
				TypeEntry type = new TypeEntry(newVariable);
				variableMethods.add(new TabEntry(1,new ListEntry(new ElementEntry(GeneratorGenerator.getMethodDeclarationElement,new ListEntry(type,new StringEntry(variableName),new StringEntry(memberName))))));
			}
			else if("constant_declaration".equals(classElementKey.getName())){
				IToken classElement = root.get(classElementKey);
				VariableEntry newVariable = (VariableEntry)generateVariableDeclaration(classElement,className,LOCAL_CONTEXT);
				newVariable.setAccess(VariableEntry.PUBLIC_ACCESS);
				newVariable.setStatic(true);
				newVariable.setFinal(true);
				variableDeclarations.add(newVariable);
			}
			else if("element_declaration".equals(classElementKey.getName())){
				IToken classElement = root.get(classElementKey);
				generateElementDeclaration(classElement,className,LOCAL_CONTEXT);
			}
			else if("generation_declaration".equals(classElementKey.getName())){
				IToken classElement = root.get(classElementKey);
				methodDeclarations.add(generateGenerationDeclaration(classElement,className,LOCAL_CONTEXT));
			}
		}
		classElements.add(new TabEntry(1,new ListEntry(new ElementEntry(GeneratorGenerator.getMethodDeclarationElement,new ListEntry(new StringEntry("String"),new StringEntry("Name"),new QuoteEntry(className))))));
		if((!newContext.containsKey("generateRoot"))){
			classElements.add(new TabEntry(1,new ListEntry(new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("void"),new StringEntry("generateRoot"),new StringEntry("IToken root"),new ListEntry())))));
		}
		if((!newContext.containsKey("generate"))){
			classElements.add(new TabEntry(1,new ListEntry(new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("void"),new StringEntry("generate"),new StringEntry("ParseContext data"),new ListEntry())))));
		}
		if((!newContext.containsKey("setup"))){
			classElements.add(new TabEntry(1,new ListEntry(new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("void"),new StringEntry("setup"),new StringEntry("ParseContext context"),new ListEntry())))));
		}
		Map<String,VariableEntry> local_context = (Map<String,VariableEntry>)newContext.get(LOCAL_CONTEXT);
		if((!local_context.containsKey("lazyNameParser"))){
			classElements.add(new TabEntry(1,new ListEntry(new ElementEntry(GeneratorGenerator.getMethodDeclarationElement,new ListEntry(new StringEntry("IParser"),new StringEntry("LazyNameParser"),new StringEntry("null"))))));
		}
		String javaClassName = Generators.generator.buildString(className,"Generator");
		String fileName = Generators.generator.buildString(javaClassName,".java");
		Generators.generator.addFile(directory,fileName,new ListEntry(new StringEntry(javaClassName),new StringEntry("Generator")));
		Generators.generator.addEntry(directory,fileName,"celements",new ListEntry(classElements));
	}
	public Entry generateAuxillaryDeclaration(IToken auxillaryDeclaration,String contextName,String contextSubName,Boolean addSuper){
		Entry ret = null;
		String methodName = "";
		ListEntry parameters = new ListEntry();
		ListEntry body = new ListEntry();
		body.setDelimiter("");
		TypeEntry type = new TypeEntry();
		type.setDefaultType("void");
		for(IToken.Key auxillaryElementKey:auxillaryDeclaration.keySet()){
			if("methodName".equals(auxillaryElementKey.getName())){
				IToken auxillaryElement = auxillaryDeclaration.get(auxillaryElementKey);
				String auxillaryName = auxillaryElement.getString();
				methodName = auxillaryName;
				Generators.generator.addContext(contextName,contextSubName,methodName,NO_DEFAULT_TOKEN);
				ret = new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(type,new StringEntry(methodName),parameters,body));
				if((addSuper == true && methodName.equals("setup"))){
					body.add(new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.semicolonedElement,new ListEntry(new MethodEntry(new StringEntry("this"),"addPage",new ListEntry()))))));
				}
			}
			else if("parameter".equals(auxillaryElementKey.getName())){
				IToken auxillaryElement = auxillaryDeclaration.get(auxillaryElementKey);
				VariableEntry var = (VariableEntry)Generators.generator.getParameter(auxillaryElement,contextName,contextSubName,methodName);
				parameters.add(var);
			}
			else if("entry_declaration".equals(auxillaryElementKey.getName())){
				IToken auxillaryElement = auxillaryDeclaration.get(auxillaryElementKey);
				body.add(generateEntryDeclaration(auxillaryElement,2,contextName,methodName));
			}
			else if("body_element".equals(auxillaryElementKey.getName())){
				IToken auxillaryElement = auxillaryDeclaration.get(auxillaryElementKey);
				body.add(generateBodyElement(auxillaryElement,2,contextName,methodName,type));
			}
		}
		return ret;
	}
	public Entry generateElementDeclaration(IToken elementDeclaration,String contextName,String contextSubName){
		if((elementDeclaration.get("ELEMENT_IMPORT") != null)){
			elementDeclaration = elementDeclaration.get("ELEMENT_IMPORT");
		}
		for(IToken.Key elementKey:elementDeclaration.keySet()){
			if("element_definition".equals(elementKey.getName())){
				IToken element = elementDeclaration.get(elementKey);
				String elementName = "NO_NAME";
				ListEntry elementParameters = new ListEntry();
				StringEntry entryDef = null;
				for(IToken.Key atomKey:element.keySet()){
					if("elementName".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						elementName = atom.getString();
					}
					else if("element_entry".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						ListEntry entryD = new ListEntry();
						entryD.setDelimiter("");
						if((entryDef != null)){
							entryD.add(new StringEntry("/*"));
							entryD.add(entryDef);
							entryD.add(new StringEntry("*/"));
							entryDef = null;
						}
						ListEntry entry = new ListEntry();
						entry.setDelimiter("+\n\t\t\t");
						for(IToken.Key quarkKey:atom.keySet()){
							if("entry".equals(quarkKey.getName())){
								IToken quark = atom.get(quarkKey);
								List<String> split = (List<String>)Arrays.asList(quark.getString().split("\n"));
								String newline = "";
								for(String line:split){
									line = Generators.generator.buildString(newline,line);
									entry.add(new QuoteEntry(line));
									newline = "\\n";
								}
							}
						}
						elementParameters.add(new ElementEntry(GeneratorGenerator.exactDuoCallElement,new ListEntry(entryD,entry)));
					}
					else if("entry".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						StringBuilder entryName = new StringBuilder();
						String space = "";
						for(IToken.Key quarkKey:atom.keySet()){
							if("NAME".equals(quarkKey.getName())){
								IToken quark = atom.get(quarkKey);
								entryName.append(space);
								entryName.append(quark.getString());
								space = " ";
							}
						}
						String entryFinishedName = entryName.toString();
						entryDef = new StringEntry(entryFinishedName);
					}
				}
				ListEntry constructorBody = (ListEntry)constructorBodies.get(contextName);
				ListEntry elementBody = (ListEntry)elementBodies.get(contextName);
				constructorBody.add(new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.semicolonedElement,new ListEntry(new ElementEntry(GeneratorGenerator.noSubjectCallElement,new ListEntry(new StringEntry("addElement"),new ListEntry(new QuoteEntry(elementName),new ElementEntry(GeneratorGenerator.exactDuoCallElement,new ListEntry(new StringEntry(elementName),new StringEntry("Element")))))))))));
				elementBody.add(new TabEntry(1,new ListEntry(new ElementEntry(GeneratorGenerator.publicStaticFinalVariableDeclarationElement,new ListEntry(new StringEntry("Element"),new ElementEntry(GeneratorGenerator.exactDuoCallElement,new ListEntry(new StringEntry(elementName),new StringEntry("Element"))),new ListEntry(new ElementEntry(GeneratorGenerator.newObjectCallElement,new ListEntry(new StringEntry("Element"),new ListEntry(new QuoteEntry(elementName),new ElementEntry(GeneratorGenerator.newStringListCallElement,new ListEntry(elementParameters)))))))))));
			}
		}
		return null;
	}
	public Entry generateGenerationDeclaration(IToken generationDeclaration,String contextName,String contextSubName){
		Entry ret = null;
		String methodName = "generate";
		ListEntry parameters = new ListEntry();
		ListEntry body = new ListEntry();
		body.setDelimiter("");
		Boolean undefined = false;
		for(IToken.Key elementKey:generationDeclaration.keySet()){
			if("tokenName".equals(elementKey.getName())){
				IToken element = generationDeclaration.get(elementKey);
				String generateName = element.getString();
				methodName = Generators.generator.buildString("generate",Generators.generator.camelize(generateName));
				VariableEntry defaultToken = new VariableEntry(generateName,"IToken");
				Generators.generator.addContext(contextName,contextSubName,methodName,defaultToken);
				parameters.add(defaultToken);
				Map<String,List<ITypeListener>> methodParameterList = (Map<String,List<ITypeListener>>)methodParameters.get(contextName);
				if((!methodParameterList.containsKey(methodName))){
					ArrayList<ITypeListener> newList = new ArrayList<ITypeListener>();
					methodParameterList.put(methodName,newList);
					newList.add(defaultToken);
					undefined = true;
				}
				if((generateName.equals("root"))){
					ret = new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("void"),new StringEntry(methodName),parameters,body));
				}
				else {
					ret = new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("Entry"),new StringEntry(methodName),parameters,body));
				}
			}
			else if("parameter".equals(elementKey.getName())){
				IToken element = generationDeclaration.get(elementKey);
				String parameter = element.get("takeName").getString();
				VariableEntry variable = (VariableEntry)null;
				String type_unknown = ITypeListener.TYPE_UNKNOWN;
				if((undefined == true)){
					variable = new VariableEntry(parameter,type_unknown);
					Map<String,List<ITypeListener>> methodParametersMap = (Map<String,List<ITypeListener>>)methodParameters.get(contextName);
					List<ITypeListener> methodParametersList = (List<ITypeListener>)methodParametersMap.get(methodName);
					methodParametersList.add(variable);
				}
				else {
					if((methodName.equals("generate"))){
						variable = new VariableEntry(parameter,type_unknown);
					}
					else {
						Map<String,List<ITypeListener>> methodParametersMap = (Map<String,List<ITypeListener>>)methodParameters.get(contextName);
						List<ITypeListener> methodParametersList = (List<ITypeListener>)methodParametersMap.get(methodName);
						ITypeListener listener = (ITypeListener)methodParametersList.get(parameters.size());
						String type = listener.getType();
						variable = new VariableEntry(parameter,type);
						variable.addListener(listener);
					}
				}
				String castToType = Generators.generator.getCastType(element,contextName,contextSubName);
				if((castToType != null)){
					variable.setType(castToType);
				}
				parameters.add(variable);
				Map<String,Map<String,VariableEntry>> myContext = (Map<String,Map<String,VariableEntry>>)contexts.get(contextName);
				if((!myContext.containsKey(methodName))){
					Generators.generator.addContext(contextName,contextSubName,methodName,NO_DEFAULT_TOKEN);
				}
				Map<String,VariableEntry> mySpecificContext = (Map<String,VariableEntry>)myContext.get(methodName);
				mySpecificContext.put(parameter,variable);
			}
			else if("entry_declaration".equals(elementKey.getName())){
				IToken element = generationDeclaration.get(elementKey);
				body.add(generateEntryDeclaration(element,2,contextName,methodName));
			}
			else if("body_element".equals(elementKey.getName())){
				IToken element = generationDeclaration.get(elementKey);
				body.add(generateBodyElement(element,2,contextName,methodName,null));
			}
		}
		if((ret == null)){
			Map<String,Map<String,VariableEntry>> thisContext = (Map<String,Map<String,VariableEntry>>)contexts.get(contextName);
			if((!thisContext.containsKey(methodName))){
				Generators.generator.addContext(contextName,contextSubName,methodName,NO_DEFAULT_TOKEN);
			}
			ret = new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("void"),new StringEntry(methodName),parameters,body));
			ITypeListener firstParameter = (ITypeListener)parameters.get(0);
			firstParameter.changeType("ParseContext");
		}
		return ret;
	}
	public Entry generateEntryDeclaration(IToken entryDeclaration,Integer tabs,String contextName,String contextSubName){
		String entryName = entryDeclaration.get("entryName").getString();
		IToken definition = entryDeclaration.get("entry_definition");
		VariableEntry retEntry = (VariableEntry)null;
		if((definition != null)){
			MethodEntry assignment = (MethodEntry)Generators.generator.generateEntryDefinition(definition,contextName,contextSubName);
			String assignmentType = assignment.getType();
			retEntry = new VariableEntry(entryName,assignmentType,assignment);
		}
		else {
			retEntry = new VariableEntry(entryName,"Entry",new StringEntry("null"));
		}
		contexts.get(contextName).get(contextSubName).put(entryName,retEntry);
		ListEntry section = new ListEntry(new TabEntry(tabs,new ListEntry(retEntry)));
		section.setDelimiter("");
		IToken delimToken = entryDeclaration.get("delimiter");
		if((delimToken != null)){
			section.add(new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.semicolonedElement,new ListEntry(new MethodEntry(new StringEntry(entryName),"setDelimiter",new ListEntry(new QuoteEntry(delimToken.getString()))))))));
			retEntry.changeType("ListEntry");
		}
		return section;
	}
	public Entry generateBodyElement(IToken bodyElement,Integer tabs,String contextName,String contextSubName,TypeEntry retType){
		for(IToken.Key atomKey:bodyElement.keySet()){
			if("error_statement".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				Entry errorStatement = generateCallErr(atom,contextName,contextSubName);
				return new TabEntry(tabs,new ListEntry(errorStatement));
			}
			else if("return_statement".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				Entry returnStatement = generateReturnStatement(atom,contextName,contextSubName,retType);
				return new TabEntry(tabs,new ListEntry(returnStatement));
			}
			else if("if_statement".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				return generateIfStatement(atom,tabs,contextName,contextSubName,retType);
			}
			else if("check_call".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				return Generators.check.generateCheckCheck(atom,tabs,contextName,contextSubName);
			}
			else if("each_call".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				return generateEachCall(atom,tabs,contextName,contextSubName,retType);
			}
			else if("token_declaration".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				VariableEntry variable = (VariableEntry)generateVariableDeclaration(atom,contextName,contextSubName);
				variable.changeType("IToken");
				return new TabEntry(tabs,new ListEntry(variable));
			}
			else if("variable_declaration".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				Entry variableStatement = generateVariableDeclaration(atom,contextName,contextSubName);
				return new TabEntry(tabs,new ListEntry(variableStatement));
			}
			else if("set_call".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				Entry setStatement = generateSetCall(atom,contextName,contextSubName);
				return new TabEntry(tabs,new ListEntry(setStatement));
			}
			else if("flip_switch".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				return generateFlipSwitch(atom,tabs,contextName,contextSubName,retType);
			}
			else if("token_expansion".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				return generateTokenExpansion(atom,tabs,contextName,contextSubName,retType);
			}
			else if("inline_addition_call".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				Entry inlineAdditionStatement = generateInlineAddition(atom,contextName,contextSubName);
				return new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.semicolonedElement,new ListEntry(inlineAdditionStatement))));
			}
			else if("method_call".equals(atomKey.getName())){
				IToken atom = bodyElement.get(atomKey);
				Entry methodStatement = generateMethodCall(atom,contextName,contextSubName);
				return new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.semicolonedElement,new ListEntry(methodStatement))));
			}
		}
		throw new UnableToGenerateException("Method Parameter (004)"+bodyElement.getString(),bodyElement);
	}
	public Entry generateCallErr(IToken callErr,String contextName,String contextSubName){
		ListEntry output = new ListEntry();
		output.setDelimiter("+");
		String varName = contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN).getName();
		StringEntry tokenName = new StringEntry(varName);
		for(IToken.Key elementKey:callErr.keySet()){
			if("entry".equals(elementKey.getName())){
				IToken element = callErr.get(elementKey);
				output.add(new QuoteEntry(element.getString()));
			}
			else if("variable_or_token_name".equals(elementKey.getName())){
				IToken element = callErr.get(elementKey);
				output.add(generateVariableOrTokenName(element,contextName,contextSubName));
			}
			else if("token_names".equals(elementKey.getName())){
				IToken element = callErr.get(elementKey);
				tokenName = new StringEntry(element.getString());
			}
		}
		return new ElementEntry(GeneratorGenerator.errorCallElement,new ListEntry(output,tokenName));
	}
	public Entry generateReturnStatement(IToken returnStatement,String contextName,String contextSubName,TypeEntry retType){
		ListEntry parameters = new ListEntry();
		MethodEntry ret = new MethodEntry("returnCall",parameters);
		if((retType != null)){
			retType.setSubject(ret);
		}
		String returnParameterType = Generators.generator.getCastType(returnStatement,contextName,contextSubName);
		if((returnParameterType != null)){
			ret.changeType(returnParameterType);
			ret.setElementName("returnCallWithCast");
			parameters.add(new StringEntry(returnParameterType));
		}
		for(IToken.Key elementKey:returnStatement.keySet()){
			if("generate_call".equals(elementKey.getName())){
				IToken element = returnStatement.get(elementKey);
				MethodEntry call = (MethodEntry)generateMethodCall(element,contextName,contextSubName);
				parameters.add(call);
				if((!ret.hasType())){
					ret.changeType(call.getType());
				}
			}
			else if("method_call".equals(elementKey.getName())){
				IToken element = returnStatement.get(elementKey);
				MethodEntry call = (MethodEntry)generateMethodCall(element,contextName,contextSubName);
				parameters.add(call);
				if((!ret.hasType())){
					ret.changeType(call.getType());
				}
			}
			else if("boolean_statement".equals(elementKey.getName())){
				IToken element = returnStatement.get(elementKey);
				parameters.add(generateBooleanStatement(element,contextName,contextSubName));
				if((!ret.hasType())){
					ret.changeType("Boolean");
				}
			}
			else if("entry_definition".equals(elementKey.getName())){
				IToken element = returnStatement.get(elementKey);
				MethodEntry entry = (MethodEntry)generateEntryDefinition(element,contextName,contextSubName);
				parameters.add(entry);
				if((!ret.hasType())){
					ret.changeType(entry.getType());
				}
			}
			else if("method_parameter".equals(elementKey.getName())){
				IToken element = returnStatement.get(elementKey);
				MethodEntry entry = (MethodEntry)generateMethodParameter(element,false,contextName,contextSubName);
				parameters.add(entry);
				if((entry.getIsNull())){
					ret.setIsNull(true);
				}
				else {
					if((!ret.hasType())){
						if((entry.hasType())){
							ret.changeType(entry.getType());
						}
						else {
							entry.addListener(ret);
						}
					}
				}
			}
		}
		return ret;
	}
	public Entry generateEachCall(IToken eachCall,Integer tabs,String contextName,String contextSubName,TypeEntry retType){
		String type_unknown = ITypeListener.TYPE_UNKNOWN;
		ListEntry forComplete = new ListEntry();
		forComplete.setDelimiter("");
		String eachName = eachCall.get("eachName").getString();
		String initialType = Generators.generator.getCastType(eachCall,contextName,contextSubName);
		if((initialType == null)){
			initialType = type_unknown;
		}
		VariableEntry variable = new VariableEntry(eachName,initialType);
		String iterableName = null;
		VariableEntry iterable = (VariableEntry)null;
		MethodEntry left = (MethodEntry)null;
		MethodEntry right = (MethodEntry)null;
		if((eachCall.get("iterable") != null)){
			iterableName = eachCall.get("iterable").getString();
			if((contexts.get(contextName).get(contextSubName).containsKey(iterableName))){
				iterable = contexts.get(contextName).get(contextSubName).get(iterableName);
				String iterableType = iterable.getType();
				Integer indexOfAngle = iterableType.indexOf("<");
				if((indexOfAngle > -1)){
					Integer indexOfSecond = iterableType.indexOf(",");
					if((indexOfSecond == -1)){
						indexOfSecond = iterableType.lastIndexOf(">");
					}
					variable.changeType(iterableType.substring(indexOfAngle + 1,indexOfSecond));
				}
			}
			else {
				Map<String,VariableEntry> myContext = (Map<String,VariableEntry>)contexts.get(contextName).get(contextSubName);
				checks.add(new ContextCheck(myContext,iterableName,"Each (011):"+iterableName+" does not exist in context:"+contextName+":"+contextSubName+""));
			}
		}
		else {
			left = (MethodEntry)Generators.generator.generateArithmatic(eachCall.get("range").get("left"),true,contextName,contextSubName);
			right = (MethodEntry)Generators.generator.generateArithmatic(eachCall.get("range").get("right"),true,contextName,contextSubName);
		}
		ifIndex = ifIndex + 1;
		String forName = Generators.generator.buildString(contextSubName,".for",ifIndex.toString());
		Generators.generator.addContext(contextName,contextSubName,forName,contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN));
		contexts.get(contextName).get(forName).put(variable.getName(),variable);
		ListEntry forBody = new ListEntry();
		forBody.setDelimiter("");
		for(IToken.Key elementKey:eachCall.keySet()){
			if("body_element".equals(elementKey.getName())){
				IToken element = eachCall.get(elementKey);
				forBody.add(generateBodyElement(element,tabs + 1,contextName,forName,retType));
			}
			else if("entry_declaration".equals(elementKey.getName())){
				IToken element = eachCall.get(elementKey);
				forBody.add(generateEntryDeclaration(element,tabs + 1,contextName,forName));
			}
		}
		Entry forStatement = null;
		if((iterableName != null)){
			forStatement = new ElementEntry(GeneratorGenerator.forStatementCallElement,new ListEntry(variable,new StringEntry(iterableName),forBody));
		}
		else {
			String actualVarName = variable.getName();
			StringEntry varName = new StringEntry(actualVarName);
			variable.setAssignment(left);
			variable.changeType("Integer");
			forStatement = new ElementEntry(GeneratorGenerator.forNumberStatementCallElement,new ListEntry(variable,varName,right,varName,forBody));
		}
		forComplete.add(new TabEntry(tabs,new ListEntry(forStatement)));
		forComplete.add(new TabEntry(tabs,new ListEntry(new StringEntry("}"))));
		return forComplete;
	}
	public Entry generateIfStatement(IToken ifStatement,Integer tabs,String contextName,String contextSubName,TypeEntry retType){
		ListEntry realIfPart = new ListEntry();
		realIfPart.setDelimiter("");
		ListEntry ifPart = realIfPart;
		ListEntry ifBody = new ListEntry();
		ifBody.setDelimiter("");
		ListEntry elseBody = (ListEntry)null;
		ifIndex = ifIndex + 1;
		String ifContextName = Generators.generator.buildString(contextSubName,".if",ifIndex.toString());
		Generators.generator.addContext(contextName,contextSubName,ifContextName,contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN));
		for(IToken.Key elementKey:ifStatement.keySet()){
			if("boolean_statement".equals(elementKey.getName())){
				IToken element = ifStatement.get(elementKey);
				ifPart.add(generateBooleanStatement(element,contextName,contextSubName));
			}
			else if("body_element".equals(elementKey.getName())){
				IToken element = ifStatement.get(elementKey);
				ifBody.add(generateBodyElement(element,tabs + 1,contextName,ifContextName,retType));
			}
			else if("entry_declaration".equals(elementKey.getName())){
				IToken element = ifStatement.get(elementKey);
				ifBody.add(generateEntryDeclaration(element,tabs + 1,contextName,ifContextName));
			}
			else if("else_statement".equals(elementKey.getName())){
				IToken element = ifStatement.get(elementKey);
				ListEntry realElseBody = new ListEntry();
				realElseBody.setDelimiter("");
				elseBody = realElseBody;
				for(IToken.Key atomKey:element.keySet()){
					if("entry_declaration".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						elseBody.add(generateEntryDeclaration(atom,tabs + 1,contextName,ifContextName));
					}
					else if("body_element".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						elseBody.add(generateBodyElement(atom,tabs + 1,contextName,ifContextName,retType));
					}
				}
			}
		}
		ListEntry ret = new ListEntry();
		ret.setDelimiter("");
		ifPart.add(ifBody);
		ret.add(new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.ifStatementCallElement,ifPart))));
		ret.add(new TabEntry(tabs,new ListEntry(new StringEntry("}"))));
		if((elseBody != null)){
			ret.add(new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.elseStatementCallElement,new ListEntry(elseBody)))));
			ret.add(new TabEntry(tabs,new ListEntry(new StringEntry("}"))));
		}
		return ret;
	}
	public Entry generateFlipSwitch(IToken flipSwitch,Integer tabs,String contextName,String contextSubName,TypeEntry retType){
		String varName = flipSwitch.get("variable_names").getString();
		Map<String,VariableEntry> myContext = (Map<String,VariableEntry>)contexts.get(contextName).get(contextSubName);
		checks.add(new ContextCheck(myContext,varName,"(002) Context not found in "+contextName+":"+contextSubName+""));
		Entry left = null;
		Boolean first = true;
		ListEntry ret = new ListEntry();
		ret.setDelimiter("");
		for(IToken.Key elementKey:flipSwitch.keySet()){
			if("left".equals(elementKey.getName())){
				IToken element = flipSwitch.get(elementKey);
				left = new ElementEntry(GeneratorGenerator.equalsCallElement,new ListEntry(new StringEntry(varName),new QuoteEntry(element.getString())));
			}
			else if("right".equals(elementKey.getName())){
				IToken element = flipSwitch.get(elementKey);
				Entry right = new ElementEntry(GeneratorGenerator.setCallElement,new ListEntry(new StringEntry(varName),new QuoteEntry(element.getString())));
				Entry ifPart = null;
				if((first == true)){
					ifPart = new ElementEntry(GeneratorGenerator.ifStatementCallElement,new ListEntry(left,new TabEntry(tabs + 1,new ListEntry(right))));
				}
				else {
					ifPart = new ElementEntry(GeneratorGenerator.elseIfStatementCallElement,new ListEntry(left,new TabEntry(tabs + 1,new ListEntry(right))));
				}
				ListEntry ifStatement = new ListEntry(new TabEntry(tabs,new ListEntry(ifPart)));
				ifStatement.setDelimiter("");
				ifStatement.add(new TabEntry(tabs,new ListEntry(new StringEntry("}"))));
				ret.add(ifStatement);
				first = false;
			}
			else if("else_statement".equals(elementKey.getName())){
				IToken element = flipSwitch.get(elementKey);
				ListEntry body = new ListEntry();
				body.setDelimiter("");
				List<IToken> atomBodyElement = element.getAll("body_element");
				if(atomBodyElement != null){
					for(IToken atom:atomBodyElement){
						body.add(generateBodyElement(atom,tabs + 1,contextName,contextSubName,retType));
					}
				}
				ListEntry elseStatement = new ListEntry(new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.elseStatementCallElement,new ListEntry(body)))));
				elseStatement.setDelimiter("");
				elseStatement.add(new TabEntry(tabs,new ListEntry(new StringEntry("}"))));
				ret.add(elseStatement);
			}
		}
		return ret;
	}
	public Entry generateSetCall(IToken setCall,String contextName,String contextSubName){
		String varName = null;
		VariableEntry variable = (VariableEntry)null;
		ITypeListener assignment = (ITypeListener)null;
		String typeName = null;
		Boolean cast = false;
		for(IToken.Key elementKey:setCall.keySet()){
			if("subject".equals(elementKey.getName())){
				IToken element = setCall.get(elementKey);
				varName = element.getString();
				if((!contexts.get(contextName).get(contextSubName).containsKey(varName))){
					String type_unknown = ITypeListener.TYPE_UNKNOWN;
					variable = new VariableEntry(varName,type_unknown,null);
					variable.setDefined(false);
					contexts.get(contextName).get(contextSubName).put(varName,variable);
					checks.add(new DefinedCheck(variable,truth,"(003) variable "+varName+" was not defined in context "+contextName+":"+contextSubName+""));
				}
				else {
					variable = contexts.get(contextName).get(contextSubName).get(varName);
				}
			}
			else if("boolean_statement".equals(elementKey.getName())){
				IToken element = setCall.get(elementKey);
				assignment = (MethodEntry)generateBooleanStatement(element,contextName,contextSubName);
			}
			else if("method_call".equals(elementKey.getName())){
				IToken element = setCall.get(elementKey);
				assignment = (MethodEntry)generateMethodCall(element,contextName,contextSubName);
			}
			else if("method_parameter".equals(elementKey.getName())){
				IToken element = setCall.get(elementKey);
				assignment = (MethodEntry)generateMethodParameter(element,false,contextName,contextSubName);
			}
			else if("castToType".equals(elementKey.getName())){
				IToken element = setCall.get(elementKey);
				typeName = Generators.generator.getCastType(setCall,contextName,contextSubName);
				cast = true;
			}
		}
		if((assignment.hasType())){
			variable.changeType(assignment.getType());
		}
		else {
			if((variable.hasType())){
				assignment.changeType(variable.getType());
			}
		}
		Entry assignmentAsEntry = (Entry)assignment;
		if((cast == true)){
			variable.changeType(typeName);
			assignmentAsEntry = new MethodEntry("castCall",new ListEntry(new StringEntry(typeName),assignmentAsEntry));
		}
		return new ElementEntry(GeneratorGenerator.setCallElement,new ListEntry(new StringEntry(varName),assignmentAsEntry));
	}
	public Entry generateInlineAddition(IToken inlineAddition,String contextName,String contextSubName){
		IToken lastSubjectToken = inlineAddition.get("subject");
		lastSubjectToken = lastSubjectToken.getLast();
		Entry subject = (Entry)Generators.generator.generateMethodParameter(lastSubjectToken,false,contextName,contextSubName);
		Entry parameter = null;
		IToken parameters = inlineAddition.get("parameter");
		for(IToken.Key elementKey:parameters.keySet()){
			if("boolean_statement".equals(elementKey.getName())){
				IToken element = parameters.get(elementKey);
				parameter = generateBooleanStatement(element,contextName,contextSubName);
			}
			else if("method_parameter".equals(elementKey.getName())){
				IToken element = parameters.get(elementKey);
				parameter = generateMethodParameter(element,false,contextName,contextSubName);
			}
			else if("method_call".equals(elementKey.getName())){
				IToken element = parameters.get(elementKey);
				parameter = generateMethodCall(element,contextName,contextSubName);
			}
		}
		return new MethodEntry(subject,"add",new ListEntry(parameter));
	}
	public Entry generateTokenExpansion(IToken tokenExpansion,Integer tabs,String contextName,String contextSubName,TypeEntry retType){
		String tokenName = null;
		for(IToken.Key elementKey:tokenExpansion.keySet()){
			if("token_names".equals(elementKey.getName())){
				IToken element = tokenExpansion.get(elementKey);
				tokenName = element.getString();
				Map<String,VariableEntry> myContext = (Map<String,VariableEntry>)contexts.get(contextName).get(contextSubName);
				checks.add(new ContextCheck(myContext,tokenName,"(006) context:"+contextName+":"+contextSubName+" does not contain token "+tokenName+""));
			}
			else if("getName".equals(elementKey.getName())){
				IToken element = tokenExpansion.get(elementKey);
				MethodEntry ret = new MethodEntry(new StringEntry(tokenName),"getName",new ListEntry());
				ret.changeType("String");
				return ret;
			}
			else if("all_type_tokens".equals(elementKey.getName())){
				IToken element = tokenExpansion.get(elementKey);
				IToken allTypeTokens = element;
				String specificNameString = allTypeTokens.get("specificTokenName").getString();
				String camelizedSpecificName = Generators.generator.camelize(specificNameString);
				StringEntry specificName = new StringEntry(camelizedSpecificName);
				StringEntry childName = new StringEntry(allTypeTokens.get("tokenName").getString());
				String contextTokenSubName = Generators.generator.buildString(contextSubName,".",childName.toString());
				VariableEntry childToken = new VariableEntry(childName.toString(),"IToken");
				Generators.generator.addContext(contextName,contextSubName,contextTokenSubName,childToken);
				if((!specificNameString.equals("*"))){
					String allNameString = Generators.generator.buildString(childName.toString(),specificName.toString());
					StringEntry allName = new StringEntry(allNameString);
					VariableEntry alls = new VariableEntry(allNameString,"List<IToken>",new MethodEntry(new StringEntry(tokenName),"getAll",new ListEntry(new QuoteEntry(specificNameString))));
					ListEntry ret = new ListEntry(new TabEntry(tabs,new ListEntry(alls)));
					ret.setDelimiter("");
					ListEntry ifBody = new ListEntry();
					ifBody.setDelimiter("");
					ListEntry forBody = new ListEntry();
					forBody.setDelimiter("");
					IToken bodyToken = element.get("body");
					for(IToken.Key bonesKey:bodyToken.keySet()){
						if("entry_declaration".equals(bonesKey.getName())){
							IToken bones = bodyToken.get(bonesKey);
							forBody.add(generateEntryDeclaration(bones,tabs + 2,contextName,contextTokenSubName));
						}
						else if("body_element".equals(bonesKey.getName())){
							IToken bones = bodyToken.get(bonesKey);
							forBody.add(generateBodyElement(bones,tabs + 2,contextName,contextTokenSubName,retType));
						}
					}
					ifBody.add(new TabEntry(tabs + 1,new ListEntry(new ElementEntry(GeneratorGenerator.forStatementCallElement,new ListEntry(new ElementEntry(GeneratorGenerator.variablePrototypeElement,new ListEntry(new StringEntry("IToken"),childName)),allName,forBody)))));
					ifBody.add(new TabEntry(tabs + 1,new ListEntry(new StringEntry("}"))));
					ret.add(new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.ifStatementCallElement,new ListEntry(new ElementEntry(GeneratorGenerator.operatorCallElement,new ListEntry(allName,new StringEntry("!="),new StringEntry("null"))),ifBody)))));
					ret.add(new TabEntry(tabs,new ListEntry(new StringEntry("}"))));
					return ret;
				}
				else {
					ListEntry ret = new ListEntry();
					ret.setDelimiter("");
					ListEntry forBody = new ListEntry();
					forBody.setDelimiter("");
					String childKeyNameString = Generators.generator.buildString(childName.toString(),"Key");
					StringEntry childKeyName = new StringEntry(childKeyNameString);
					forBody.add(new TabEntry(tabs + 1,new ListEntry(new VariableEntry(childName.toString(),"IToken",new MethodEntry(new StringEntry(tokenName),"get",new ListEntry(childKeyName))))));
					IToken bodyToken = element.get("body");
					for(IToken.Key bonesKey:bodyToken.keySet()){
						if("entry_declaration".equals(bonesKey.getName())){
							IToken bones = bodyToken.get(bonesKey);
							forBody.add(generateEntryDeclaration(bones,tabs + 1,contextName,contextTokenSubName));
						}
						else if("body_element".equals(bonesKey.getName())){
							IToken bones = bodyToken.get(bonesKey);
							forBody.add(generateBodyElement(bones,tabs + 1,contextName,contextTokenSubName,retType));
						}
					}
					ret.add(new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.tokenForStatementCallElement,new ListEntry(childKeyName,new StringEntry(tokenName),forBody)))));
					ret.add(new TabEntry(tabs,new ListEntry(new StringEntry("}"))));
					return ret;
				}
			}
			else if("clause_type_tokens".equals(elementKey.getName())){
				IToken element = tokenExpansion.get(elementKey);
				ListEntry forBody = new ListEntry();
				forBody.setDelimiter("");
				StringEntry childName = null;
				StringEntry childKeyName = null;
				MethodEntry childKeyNameGetName = null;
				VariableEntry childToken = (VariableEntry)null;
				Boolean first = true;
				ListEntry elseBody = (ListEntry)null;
				String contextTokenSubName = null;
				for(IToken.Key clauseKey:element.keySet()){
					if("tokenName".equals(clauseKey.getName())){
						IToken clause = element.get(clauseKey);
						childName = new StringEntry(clause.getString());
						String childKeyNameString = Generators.generator.buildString(childName.toString(),"Key");
						childKeyName = new StringEntry(childKeyNameString);
						childKeyNameGetName = new MethodEntry(childKeyName,"getName",new ListEntry());
						contextTokenSubName = Generators.generator.buildString(contextSubName,".",childName.toString());
						childToken = new VariableEntry(childName.toString(),"IToken",new MethodEntry(new StringEntry(tokenName),"get",new ListEntry(childKeyName)));
						Generators.generator.addContext(contextName,contextSubName,contextTokenSubName,childToken);
					}
					else if("token_clause".equals(clauseKey.getName())){
						IToken clause = element.get(clauseKey);
						String clauseNameString = clause.get("specificTokenName").getString();
						QuoteEntry clauseName = null;
						Boolean isElse = false;
						if((!clauseNameString.equals("else"))){
							clauseName = new QuoteEntry(clauseNameString);
						}
						else {
							ListEntry realElseBody = new ListEntry();
							realElseBody.setDelimiter("");
							elseBody = realElseBody;
							isElse = true;
						}
						ListEntry clauseBody = new ListEntry();
						clauseBody.setDelimiter("");
						IToken bodyToken = clause.get("body");
						if((isElse == true && first == true)){
							clauseBody.add(new TabEntry(tabs + 1,new ListEntry(childToken)));
							for(IToken.Key bonesKey:bodyToken.keySet()){
								if("entry_declaration".equals(bonesKey.getName())){
									IToken bones = bodyToken.get(bonesKey);
									clauseBody.add(generateEntryDeclaration(bones,tabs + 1,contextName,contextTokenSubName));
								}
								else if("body_element".equals(bonesKey.getName())){
									IToken bones = bodyToken.get(bonesKey);
									clauseBody.add(generateBodyElement(bones,tabs + 1,contextName,contextTokenSubName,retType));
								}
							}
							elseBody = clauseBody;
						}
						else {
							clauseBody.add(new TabEntry(tabs + 2,new ListEntry(childToken)));
							String contextSpecificTokenSubName = Generators.generator.buildString(contextTokenSubName,".",clauseNameString);
							Generators.generator.addContext(contextName,contextTokenSubName,contextSpecificTokenSubName,childToken);
							for(IToken.Key bonesKey:bodyToken.keySet()){
								if("entry_declaration".equals(bonesKey.getName())){
									IToken bones = bodyToken.get(bonesKey);
									clauseBody.add(generateEntryDeclaration(bones,tabs + 2,contextName,contextSpecificTokenSubName));
								}
								else if("body_element".equals(bonesKey.getName())){
									IToken bones = bodyToken.get(bonesKey);
									clauseBody.add(generateBodyElement(bones,tabs + 2,contextName,contextSpecificTokenSubName,retType));
								}
							}
							if((isElse == true)){
								elseBody = clauseBody;
							}
							else {
								Entry ifPart = null;
								if((first == true)){
									ifPart = new TabEntry(tabs + 1,new ListEntry(new ElementEntry(GeneratorGenerator.ifStatementCallElement,new ListEntry(new MethodEntry(clauseName,"equals",new ListEntry(childKeyNameGetName)),clauseBody))));
								}
								else {
									ifPart = new TabEntry(tabs + 1,new ListEntry(new ElementEntry(GeneratorGenerator.elseIfStatementCallElement,new ListEntry(new MethodEntry(clauseName,"equals",new ListEntry(childKeyNameGetName)),clauseBody))));
								}
								ListEntry clauseComplete = new ListEntry(ifPart);
								clauseComplete.setDelimiter("");
								clauseComplete.add(new TabEntry(tabs + 1,new ListEntry(new StringEntry("}"))));
								forBody.add(clauseComplete);
							}
						}
						first = false;
					}
				}
				if((elseBody != null)){
					if((forBody.isEmpty())){
						forBody.add(elseBody);
					}
					else {
						forBody.add(new TabEntry(tabs + 1,new ListEntry(new ElementEntry(GeneratorGenerator.elseStatementCallElement,new ListEntry(elseBody)))));
						forBody.add(new TabEntry(tabs + 1,new ListEntry(new StringEntry("}"))));
					}
				}
				Entry forPart = new ElementEntry(GeneratorGenerator.tokenForStatementCallElement,new ListEntry(childKeyName,new StringEntry(tokenName),forBody));
				ListEntry forComplete = new ListEntry(new TabEntry(tabs,new ListEntry(forPart)));
				forComplete.setDelimiter("");
				forComplete.add(new TabEntry(tabs,new ListEntry(new StringEntry("}"))));
				return forComplete;
			}
		}
		throw new UnableToGenerateException("Unable to generate Token Expansion (006)",tokenExpansion);
	}
	public Entry generateVariableDeclaration(IToken variableDeclaration,String contextName,String contextSubName){
		String varName = null;
		String typeName = ITypeListener.TYPE_UNKNOWN;
		Entry assignment = new StringEntry("null");
		Boolean cast = false;
		for(IToken.Key elementKey:variableDeclaration.keySet()){
			if("variableName".equals(elementKey.getName())){
				IToken element = variableDeclaration.get(elementKey);
				varName = element.getString();
			}
			else if("tokenName".equals(elementKey.getName())){
				IToken element = variableDeclaration.get(elementKey);
				varName = element.getString();
			}
			else if("castToType".equals(elementKey.getName())){
				IToken element = variableDeclaration.get(elementKey);
				typeName = Generators.generator.getCastType(variableDeclaration,contextName,contextSubName);
				cast = true;
			}
			else if("method_call".equals(elementKey.getName())){
				IToken element = variableDeclaration.get(elementKey);
				MethodEntry method = (MethodEntry)generateMethodCall(element,contextName,contextSubName);
				assignment = method;
				if((cast == false)){
					if((method.hasType())){
						typeName = method.getType();
					}
					else {
						method.changeType(typeName);
					}
				}
			}
			else if("method_parameter".equals(elementKey.getName())){
				IToken element = variableDeclaration.get(elementKey);
				MethodEntry parameter = (MethodEntry)generateMethodParameter(element,false,contextName,contextSubName);
				assignment = parameter;
				if((cast == false)){
					if((parameter.hasType())){
						typeName = parameter.getType();
					}
					else {
						parameter.changeType(typeName);
					}
				}
			}
			else if("boolean_statement".equals(elementKey.getName())){
				IToken element = variableDeclaration.get(elementKey);
				MethodEntry bool = (MethodEntry)generateBooleanStatement(element,contextName,contextSubName);
				assignment = bool;
				typeName = "Boolean";
			}
		}
		if((contexts.get(contextName).get(contextSubName).containsKey(varName))){
			VariableEntry variable = (VariableEntry)contexts.get(contextName).get(contextSubName).get(varName);
			variable.changeType(typeName);
			if((cast == true)){
				variable.setAssignment(new ElementEntry(GeneratorGenerator.castCallElement,new ListEntry(new StringEntry(typeName),assignment)));
			}
			else {
				variable.setAssignment(assignment);
			}
			variable.setCast(true);
			return variable;
		}
		else {
			if((cast == true)){
				assignment = new ElementEntry(GeneratorGenerator.castCallElement,new ListEntry(new StringEntry(typeName),assignment));
			}
			VariableEntry variable = new VariableEntry(varName,typeName,assignment);
			contexts.get(contextName).get(contextSubName).put(varName,variable);
			variable.setCast(cast);
			return variable;
		}
	}
	public Entry generateMethodCall(IToken methodCall,String contextName,String contextSubName){
		Boolean isStatic = (methodCall.get("isStatic") != null);
		IToken subject = methodCall.get("subject");
		IToken subjectAsMethodParameter = subject.get("method_parameter");
		Entry subjectName = (Entry)null;
		Boolean isGenerate = false;
		Entry method_new = MethodEntry.NEW_METHOD;
		if((subjectAsMethodParameter != null)){
			subjectName = Generators.generator.generateMethodParameter(subjectAsMethodParameter,isStatic,contextName,contextSubName);
		}
		else {
			String subName = subject.getString();
			subjectName = new StringEntry(subName);
			if((subName.contains("generate "))){
				isGenerate = true;
			}
			else {
				if((subName.equals("new "))){
					subjectName = MethodEntry.NEW_METHOD;
				}
			}
		}
		String methodName = null;
		if((subjectName.equals(method_new))){
			methodName = "$constructorCall";
		}
		else {
			methodName = methodCall.get("methodName").getString();
			if((isGenerate == false && methodName.equals("single"))){
				methodName = "getSingle";
			}
		}
		String theTrueMethod = null;
		Boolean undefined = false;
		String theTrueContext = contextName;
		if((methodCall.get("generator_names") != null)){
			theTrueContext = Generator.camelize(methodCall.get("generator_names").getString());
		}
		if((!methodParameters.containsKey(theTrueContext))){
			methodParameters.put(theTrueContext,new HashMap<String,List<ITypeListener>>());
		}
		if((isGenerate == true)){
			theTrueMethod = Generators.generator.buildString("generate",Generators.generator.camelize(methodName));
			if((!methodParameters.get(theTrueContext).containsKey(theTrueMethod))){
				methodParameters.get(theTrueContext).put(theTrueMethod,new ArrayList<ITypeListener>());
				undefined = true;
			}
			else {
				undefined = false;
			}
		}
		else {
			theTrueMethod = methodName;
			methodParameters.get(contextName).put(theTrueMethod,new ArrayList<ITypeListener>());
			undefined = true;
		}
		ParametersEntry parameters = new ParametersEntry(undefined,theTrueContext,theTrueMethod);
		MethodEntry ret = (MethodEntry)null;
		if((isGenerate == true)){
			VariableEntry defaultToken = (VariableEntry)contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN);
			String defaultTokenName = defaultToken.getName();
			MethodEntry tokenParam = new MethodEntry(defaultTokenName);
			tokenParam.changeType("IToken");
			parameters.add(tokenParam);
			ret = new MethodEntry("noSubjectCall",new ListEntry(new StringEntry(theTrueMethod),parameters));
			ret.changeType("Entry");
		}
		else {
			if((isStatic == true)){
				ret = new MethodEntry("staticGetVariableCall",new ListEntry(subjectName,new StringEntry(methodName),parameters));
			}
			else {
				ret = new MethodEntry(subjectName,methodName,parameters);
				if((subjectName.equals(method_new))){
					ret.changeType(methodName);
				}
				else {
					if((methodName.equals("getSingle"))){
						ret.changeType("Entry");
					}
				}
			}
		}
		Boolean isMethod = false;
		for(IToken.Key elementKey:methodCall.keySet()){
			if("inline_parameters".equals(elementKey.getName())){
				IToken element = methodCall.get(elementKey);
				generateInlineParameter(element,parameters,contextName,contextSubName);
				isMethod = true;
			}
			else if("angle_class".equals(elementKey.getName())){
				IToken element = methodCall.get(elementKey);
				MethodEntry entry = (MethodEntry)generateAngleClasses(element,contextName,contextSubName);
				String entryType = entry.getType();
				ret = new MethodEntry(subjectName,entryType,parameters);
				ret.changeType(entryType);
				isMethod = true;
			}
			else if("boolean_statement".equals(elementKey.getName())){
				IToken element = methodCall.get(elementKey);
				parameters.add(generateBooleanStatement(element,contextName,contextSubName));
				isMethod = true;
			}
			else if("method_parameter".equals(elementKey.getName())){
				IToken element = methodCall.get(elementKey);
				parameters.add(generateMethodParameter(element,false,contextName,contextSubName));
				isMethod = true;
			}
			else if("method_call".equals(elementKey.getName())){
				IToken element = methodCall.get(elementKey);
				parameters.add(generateMethodCall(element,contextName,contextSubName));
				isMethod = true;
			}
			else if("parameter".equals(elementKey.getName())){
				IToken element = methodCall.get(elementKey);
				isMethod = true;
				MethodEntry newParameter = (MethodEntry)null;
				for(IToken.Key atomKey:element.keySet()){
					if("boolean_statement".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						newParameter = (MethodEntry)generateBooleanStatement(atom,contextName,contextSubName);
					}
					else if("method_parameter".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						newParameter = (MethodEntry)generateMethodParameter(atom,false,contextName,contextSubName);
					}
					else if("method_call".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						newParameter = (MethodEntry)generateMethodCall(atom,contextName,contextSubName);
					}
				}
				String parameterType = Generators.generator.getCastType(element,contextName,contextSubName);
				if((parameterType != null)){
					MethodEntry oldParameter = null;
					oldParameter = (MethodEntry)newParameter;
					newParameter = new MethodEntry("castCall",new ListEntry(new StringEntry(parameterType),newParameter));
					newParameter.addListener(oldParameter);
				}
				parameters.add(newParameter);
			}
		}
		if((isStatic == true && isMethod == true)){
			ret.setElementName("staticMethodCall");
		}
		else {
			if((methodCall.get("generator_names") != null)){
				MethodEntry realRet = new MethodEntry("generatorMethodCall",new ListEntry(new StringEntry(methodCall.get("generator_names").getString()),ret));
				ret.addListener(realRet);
				realRet.changeType(ret.getType());
				realRet.setDefaultType(ret.getDefaultType());
				ret = realRet;
			}
		}
		return ret;
	}
	public Entry generateInlineParameter(IToken inlineParameter,ParametersEntry parameters,String contextName,String contextSubName){
		List<IToken> parameterMethodParameter = inlineParameter.getAll("method_parameter");
		if(parameterMethodParameter != null){
			for(IToken parameter:parameterMethodParameter){
				parameters.add(generateMethodParameter(parameter,false,contextName,contextSubName));
			}
		}
		return null;
	}
	public Entry generateAngleClasses(IToken angleClasses,String contextName,String contextSubName){
		StringBuilder typeName = (StringBuilder)new StringBuilder();
		String comma = "";
		for(IToken.Key elementKey:angleClasses.keySet()){
			if("class_names".equals(elementKey.getName())){
				IToken element = angleClasses.get(elementKey);
				typeName.append(comma);
				comma = ",";
				typeName.append(element.getString());
			}
			else if("property_names".equals(elementKey.getName())){
				IToken element = angleClasses.get(elementKey);
				typeName.append(comma);
				comma = ",";
				typeName.append(Generators.generator.buildString("I",Generators.generator.camelize(element.getString())));
			}
			else if("entry_class_names".equals(elementKey.getName())){
				IToken element = angleClasses.get(elementKey);
				typeName.append(comma);
				comma = ",";
				typeName.append(Generators.generator.buildString(Generators.generator.camelize(element.getString()),"Entry"));
			}
			else if("braces".equals(elementKey.getName())){
				IToken element = angleClasses.get(elementKey);
				MethodEntry entry = (MethodEntry)generateAngleClasses(element,contextName,contextSubName);
				typeName.append("<");
				typeName.append(entry.getType());
				typeName.append(">");
			}
		}
		MethodEntry ret = new MethodEntry(typeName.toString());
		ret.changeType(typeName.toString());
		return ret;
	}
	public Entry generateMethodParameter(IToken methodParameter,Boolean isStatic,String contextName,String contextSubName){
		for(IToken.Key elementKey:methodParameter.keySet()){
			if("NULL".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				MethodEntry param = new MethodEntry(element.getString());
				param.setIsNull(true);
				return param;
			}
			else if("TRUE".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				MethodEntry param = new MethodEntry("true");
				param.changeType("Boolean");
				return param;
			}
			else if("FALSE".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				MethodEntry param = new MethodEntry("false");
				param.changeType("Boolean");
				return param;
			}
			else if("NUMBER".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				MethodEntry param = new MethodEntry(element.getString());
				param.changeType("Integer");
				return param;
			}
			else if("generate_call".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				return generateMethodCall(element,contextName,contextSubName);
			}
			else if("getKeyName".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				String keyName = Generators.generator.buildString(contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN).getName(),"Key");
				MethodEntry param = new MethodEntry(new StringEntry(keyName),"getName",new ListEntry());
				param.changeType("String");
				return param;
			}
			else if("entry_definition".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				return generateEntryDefinition(element,contextName,contextSubName);
			}
			else if("entry_names".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				String variableName = element.getString();
				MethodEntry param = new MethodEntry(variableName);
				VariableEntry variable = (VariableEntry)contexts.get(contextName).get(contextSubName).get(variableName);
				if((variable == null)){
					String type_unknown = ITypeListener.TYPE_UNKNOWN;
					variable = new VariableEntry(variableName,type_unknown,null);
					variable.setDefined(false);
					contexts.get(contextName).get(contextSubName).put(variableName,variable);
					checks.add(new DefinedCheck(variable,truth,"(040) variable "+variableName+" is not defined in context "+contextName+":"+contextSubName+""));
				}
				param = new MethodEntry(variableName);
				if((variable.hasType())){
					param.changeType(variable.getType());
				}
				else {
					param.addListener(variable);
				}
				if((methodParameter.get("getString") != null)){
					MethodEntry retEntry = new MethodEntry(param,"getString",new ListEntry());
					retEntry.changeType("String");
					return retEntry;
				}
				else {
					return param;
				}
			}
			else if("class_names".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				MethodEntry param = (MethodEntry)null;
				String type = Generators.generator.camelize(element.getString());
				if((isStatic == true)){
					param = new MethodEntry(type);
				}
				else {
					StringEntry method_new = new StringEntry("");
					method_new = MethodEntry.NEW_METHOD;
					param = new MethodEntry(method_new,type,new ListEntry());
				}
				param.changeType(type);
				return param;
			}
			else if("generator_names".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				String name = element.getString();
				MethodEntry param = new MethodEntry("generatorCall",new ListEntry(new StringEntry(name)));
				param.changeType(Generators.generator.camelize(Generators.generator.buildString(name,"Generator")));
				return param;
			}
			else if("property_names".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				String name = Generators.generator.buildString("I",Generators.generator.camelize(element.getString()));
				MethodEntry param = new MethodEntry(name);
				param.changeType(name);
				return param;
			}
			else if("entry_class_names".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				String name = Generators.generator.buildString(Generators.generator.camelize(element.getString()),"Entry");
				MethodEntry param = new MethodEntry(name);
				param.changeType(name);
				return param;
			}
			else if("string".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				String string = Generators.generator.buildString("\"",element.getString(),"\"");
				MethodEntry param = new MethodEntry(string);
				param.changeType("String");
				return param;
			}
			else if("variable_or_token_name".equals(elementKey.getName())){
				IToken element = methodParameter.get(elementKey);
				return generateVariableOrTokenName(element,contextName,contextSubName);
			}
		}
		throw new UnableToGenerateException("(002) Method Parameter unable to generate!",methodParameter);
	}
	public Entry generateVariableOrTokenName(IToken variableOrTokenName,String contextName,String contextSubName){
		Boolean isGetString = (variableOrTokenName.get("getString") != null);
		Boolean isCamelized = (variableOrTokenName.get("camelize") != null);
		MethodEntry ret = (MethodEntry)null;
		Boolean isToken = false;
		for(IToken.Key elementKey:variableOrTokenName.keySet()){
			if("generator_names".equals(elementKey.getName())){
				IToken element = variableOrTokenName.get(elementKey);
				String name = element.getString();
				ret = new MethodEntry("generatorCall",new ListEntry(new StringEntry(name)));
				ret.changeType(Generators.generator.camelize(Generators.generator.buildString(name,"Generator")));
			}
			else if("entry_names".equals(elementKey.getName())){
				IToken element = variableOrTokenName.get(elementKey);
				String entryName = element.getString();
				VariableEntry variable = (VariableEntry)Generators.generator.getVariable(entryName,contextName,contextSubName,"Entry","List Entry Definition(707)");
				String varType = variable.getType();
				if((!varType.contains("Entry"))){
					variable.changeType("Entry");
				}
				ret = new MethodEntry(entryName);
				ret.changeType(variable.getType());
			}
			else if("token_names".equals(elementKey.getName())){
				IToken element = variableOrTokenName.get(elementKey);
				ret = new MethodEntry(element.getString());
				ret.changeType("IToken");
				isToken = true;
			}
			else if("variable_names".equals(elementKey.getName())){
				IToken element = variableOrTokenName.get(elementKey);
				String varName = element.getString();
				VariableEntry variable = (VariableEntry)Generators.generator.getVariable(varName,contextName,contextSubName,ITypeListener.TYPE_UNKNOWN,"Variable or Token(123)");
				ret = new MethodEntry(varName);
				String variableType = variable.getType();
				ret.changeType(variableType);
				if((variableType.equals("IToken"))){
					isToken = true;
				}
			}
			else if("option".equals(elementKey.getName())){
				IToken element = variableOrTokenName.get(elementKey);
				for(IToken.Key atomKey:element.keySet()){
					if("NAME".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						ret = new MethodEntry(ret,"get",new ListEntry(new QuoteEntry(atom.getString())));
					}
					else if("variable_names".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						if((isToken == true)){
							ret = new MethodEntry(ret,"get",new ListEntry(new QuoteEntry(atom.getString())));
						}
						else {
							ret = new MethodEntry(ret,"get",new ListEntry(new StringEntry(atom.getString())));
						}
					}
				}
				if((isToken == true)){
					ret.changeType("IToken");
				}
			}
			else if("arithmatic".equals(elementKey.getName())){
				IToken element = variableOrTokenName.get(elementKey);
				ret = (MethodEntry)generateArithmatic(element,false,contextName,contextSubName);
			}
		}
		if((isGetString == true || isCamelized == true)){
			String retType = ret.getType();
			if((retType.equals("IToken"))){
				ret = new MethodEntry(ret,"getString",new ListEntry());
			}
			else {
				ret = new MethodEntry(ret,"toString",new ListEntry());
			}
			ret.changeType("String");
		}
		if((isCamelized == true)){
			ret = new MethodEntry(new StringEntry("Generator"),"camelize",new ListEntry(ret));
		}
		return ret;
	}
	public Entry generateArithmatic(IToken arithmatic,Boolean mustBeNumber,String contextName,String contextSubName){
		MethodEntry ret = (MethodEntry)null;
		for(IToken.Key elementKey:arithmatic.keySet()){
			if("arithmatic".equals(elementKey.getName())){
				IToken element = arithmatic.get(elementKey);
				MethodEntry next = (MethodEntry)generateArithmatic(element,mustBeNumber,contextName,contextSubName);
				if((ret == null)){
					ret = next;
				}
				else {
					ListEntry entries = (ListEntry)ret.getParameters();
					entries.add(next);
				}
				if((next.hasType())){
					ret.changeType(next.getType());
				}
			}
			else if("operand".equals(elementKey.getName())){
				IToken element = arithmatic.get(elementKey);
				if((ret != null)){
					ret.changeType("Integer");
				}
				ret = new MethodEntry("operatorCall",new ListEntry(ret,new StringEntry(element.getString())));
				mustBeNumber = true;
			}
			else if("variable_names".equals(elementKey.getName())){
				IToken element = arithmatic.get(elementKey);
				String variableName = element.getString();
				VariableEntry variable = (VariableEntry)contexts.get(contextName).get(contextSubName).get(variableName);
				if((variable == null)){
					String type_unknown = ITypeListener.TYPE_UNKNOWN;
					variable = new VariableEntry(variableName,type_unknown,null);
					variable.setDefined(false);
					contexts.get(contextName).get(contextSubName).put(variableName,variable);
					checks.add(new DefinedCheck(variable,truth,"(004) Variable "+variableName+" is not defined in context:"+contextName+":"+contextSubName+""));
				}
				ret = new MethodEntry(variableName);
				if((variable.hasType())){
					ret.changeType(variable.getType());
				}
				else {
					ret.addListener(variable);
				}
				String variableType = variable.getType();
				if((variableType.equals("Integer"))){
					mustBeNumber = true;
				}
			}
			else if("NUMBER".equals(elementKey.getName())){
				IToken element = arithmatic.get(elementKey);
				String number = element.getString();
				ret = new MethodEntry(number);
				mustBeNumber = true;
			}
		}
		if((mustBeNumber == true)){
			ret.changeType("Integer");
		}
		return ret;
	}
	public Entry generateBooleanStatement(IToken booleanStatement,String contextName,String contextSubName){
		MethodEntry bool = (MethodEntry)null;
		for(IToken.Key elementKey:booleanStatement.keySet()){
			if("boolean_clause".equals(elementKey.getName())){
				IToken element = booleanStatement.get(elementKey);
				bool = (MethodEntry)generateBooleanClause(element,contextName,contextSubName);
			}
			else if("continuationStatement".equals(elementKey.getName())){
				IToken element = booleanStatement.get(elementKey);
				if((element.get("continuationOperator").getString().equals("and "))){
					bool = new MethodEntry("operatorCall",new ListEntry(bool,new StringEntry("&&")));
				}
				else {
					bool = new MethodEntry("operatorCall",new ListEntry(bool,new StringEntry("||")));
				}
				ListEntry boolParameters = (ListEntry)bool.getParameters();
				IToken clause = element.get("boolean_clause");
				boolParameters.add(Generators.generator.generateBooleanClause(clause,contextName,contextSubName));
			}
		}
		bool = new MethodEntry("newBooleanCall",new ListEntry(bool));
		bool.changeType("Boolean");
		return bool;
	}
	public Entry generateBooleanClause(IToken booleanClause,String contextName,String contextSubName){
		IToken notStatement = booleanClause.get("notStatement");
		if((notStatement != null)){
			MethodEntry operand = (MethodEntry)Generators.generator.generateMethodParameter(notStatement.get("method_parameter"),false,contextName,contextSubName);
			operand.changeType("Boolean");
			return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),operand));
		}
		else {
			IToken containsToken = booleanClause.get("containsStatement");
			if((containsToken != null)){
				MethodEntry operation = new MethodEntry(new StringEntry(containsToken.get("token_names").getString()),"containsKey",new ListEntry(new QuoteEntry(containsToken.get("argument").getString())));
				operation.changeType("Boolean");
				return operation;
			}
			else {
				IToken statement = booleanClause.get("operatedStatement");
				Boolean hasRight = true;
				if((statement == null)){
					statement = booleanClause.get("methodWithNoArgument");
					hasRight = false;
				}
				Integer operatorState = -1;
				String previousDefaultType = null;
				MethodEntry left = (MethodEntry)Generators.generator.generateMethodParameter(statement.get("left").get("method_parameter"),false,contextName,contextSubName);
				IToken operator = statement.get("operator");
				String doesMethodName = null;
				if((operator.get("IS") != null)){
					if((operator.get("NOT") != null)){
						operatorState = 1;
					}
					else {
						operatorState = 0;
					}
				}
				else {
					if((operator.get("DOES") != null)){
						if((operator.get("NOT") != null)){
							operatorState = 4;
						}
						else {
							operatorState = 3;
						}
						doesMethodName = operator.get("methodName").getString();
					}
					else {
						operatorState = 2;
					}
				}
				if((hasRight == true)){
					IToken rightToken = statement.get("right");
					MethodEntry right = (MethodEntry)null;
					Boolean isPrimitive = false;
					for(IToken.Key elementKey:rightToken.keySet()){
						if("method_parameter".equals(elementKey.getName())){
							IToken element = rightToken.get(elementKey);
							right = (MethodEntry)generateMethodParameter(element,false,contextName,contextSubName);
							String leftType = left.getType();
							String rightType = right.getType();
							Boolean leftNull = (left.getIsNull());
							Boolean rightNull = (right.getIsNull());
							isPrimitive = (leftType.equals("Integer") || leftType.equals("Boolean") || rightType.equals("Integer") || rightType.equals("Boolean") || leftNull == true || rightNull == true);
							if((operatorState == 2)){
								left.changeType("Integer");
								right.changeType("Integer");
								return new MethodEntry("operatorCall",new ListEntry(left,new StringEntry(operator.getString()),right));
							}
							Boolean leftHasType = left.hasType();
							if((operatorState <= 2)){
								if((leftHasType == false && right.hasType())){
									left.changeType(right.getType());
								}
								else {
									if((leftHasType == true && !right.hasType())){
										right.changeType(left.getType());
									}
								}
							}
							if((isPrimitive == true)){
								if((operatorState == 0)){
									return new MethodEntry("operatorCall",new ListEntry(left,new StringEntry("=="),right));
								}
								else {
									if((operatorState == 1)){
										return new MethodEntry("operatorCall",new ListEntry(left,new StringEntry("!="),right));
									}
								}
							}
							else {
								if((operatorState == 0)){
									return new MethodEntry("equalsCall",new ListEntry(left,right));
								}
								if((operatorState == 1)){
									return new MethodEntry("notEqualsCall",new ListEntry(left,right));
								}
								if((operatorState == 3)){
									return new MethodEntry(left,doesMethodName,new ListEntry(right));
								}
								if((operatorState == 4)){
									return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),new MethodEntry(left,doesMethodName,new ListEntry(right))));
								}
							}
						}
						else if("SINGULAR".equals(elementKey.getName())){
							IToken element = rightToken.get(elementKey);
							if((operatorState == 0)){
								return new MethodEntry(left,"isSingular",new ListEntry());
							}
							if((operatorState == 1)){
								return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),new MethodEntry(left,"isSingular",new ListEntry())));
							}
						}
						else if("EMPTY".equals(elementKey.getName())){
							IToken element = rightToken.get(elementKey);
							if((operatorState == 0)){
								return new MethodEntry(left,"isEmpty",new ListEntry());
							}
							if((operatorState == 1)){
								return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),new MethodEntry(left,"isEmpty",new ListEntry())));
							}
						}
					}
				}
				else {
					if((operatorState == 3)){
						return new MethodEntry(left,doesMethodName,new ListEntry());
					}
					if((operatorState == 4)){
						return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),new MethodEntry(left,doesMethodName,new ListEntry())));
					}
				}
			}
		}
		throw new UnableToGenerateException("(009)",booleanClause);
	}
	public Entry generateEntryDefinition(IToken entryDefinition,String contextName,String contextSubName){
		if((entryDefinition.get("generate_call") != null)){
			return Generators.generator.generateMethodCall(entryDefinition.get("generate_call"),contextName,contextSubName);
		}
		IToken getSingle = entryDefinition.get("getSingle");
		if((getSingle != null)){
			String entryName = getSingle.getString();
			if((!contexts.get(contextName).get(contextSubName).containsKey(entryName))){
				VariableEntry newEntry = new VariableEntry(entryName,"Entry");
				newEntry.setDefined(false);
				contexts.get(contextName).get(contextSubName).put(entryName,newEntry);
			}
			String entryType = contexts.get(contextName).get(contextSubName).get(entryName).getType();
			if((!entryType.contains("Entry"))){
				contexts.get(contextName).get(contextSubName).get(entryName).changeType("Entry");
			}
			MethodEntry def = new MethodEntry(new StringEntry(entryName),"getSingle",new ListEntry());
			def.changeType("Entry");
			return def;
		}
		IToken quoteEntry = entryDefinition.get("quoted");
		if((quoteEntry != null)){
			MethodEntry ret = (MethodEntry)null;
			for(IToken.Key elementKey:quoteEntry.keySet()){
				if("entry".equals(elementKey.getName())){
					IToken element = quoteEntry.get(elementKey);
					ret = new MethodEntry(new_method,"QuoteEntry",new ListEntry(new QuoteEntry(element.getString())));
				}
				else if("variable_or_token_name".equals(elementKey.getName())){
					IToken element = quoteEntry.get(elementKey);
					ret = new MethodEntry(new_method,"QuoteEntry",new ListEntry(generateVariableOrTokenName(element,contextName,contextSubName)));
				}
			}
			ret.changeType("QuoteEntry");
			return ret;
		}
		IToken stringEntry = entryDefinition.get("string");
		if((stringEntry != null)){
			MethodEntry ret = (MethodEntry)null;
			for(IToken.Key choiceKey:stringEntry.keySet()){
				if("entry".equals(choiceKey.getName())){
					IToken choice = stringEntry.get(choiceKey);
					ret = new MethodEntry(new_method,"StringEntry",new ListEntry(new QuoteEntry(choice.getString())));
				}
				else if("variable_or_token_name".equals(choiceKey.getName())){
					IToken choice = stringEntry.get(choiceKey);
					ret = new MethodEntry(new_method,"StringEntry",new ListEntry(generateVariableOrTokenName(choice,contextName,contextSubName)));
				}
			}
			ret.changeType("StringEntry");
			return ret;
		}
		IToken elementNameToken = entryDefinition.get("element_names");
		Boolean isVariableName = false;
		if((elementNameToken == null)){
			elementNameToken = entryDefinition.get("variable_names");
			isVariableName = true;
		}
		IToken listEntryToken = entryDefinition.get("list_entry_definition");
		Entry listEntry = null;
		MethodEntry ret = (MethodEntry)null;
		if((listEntryToken != null)){
			listEntry = Generators.generator.generateListEntryDefinition(listEntryToken,contextName,contextSubName);
		}
		if((elementNameToken == null)){
			if((listEntryToken == null)){
				IToken entryClassName = entryDefinition.get("entry_class_names");
				if((entryClassName != null)){
					ListEntry parameters = new ListEntry();
					List<IToken> methodParameterMethodParameter = entryDefinition.getAll("method_parameter");
					if(methodParameterMethodParameter != null){
						for(IToken methodParameter:methodParameterMethodParameter){
							parameters.add(generateMethodParameter(methodParameter,false,contextName,contextSubName));
						}
					}
					String className = Generators.generator.buildString(Generators.generator.camelize(entryClassName.getString()),"Entry");
					ret = new MethodEntry(new_method,className,parameters);
					ret.changeType(className);
					return ret;
				}
				else {
					IToken tabBrace = entryDefinition.get("tab_braces");
					IToken arithmatic = tabBrace.get("arithmatic");
					IToken entryDefinitionToken = tabBrace.get("entry_definition");
					Entry arithmaticEntry = Generators.generator.generateArithmatic(arithmatic,false,contextName,contextSubName);
					if((entryDefinitionToken != null)){
						Entry entryDefinitionEntry = Generators.generator.generateEntryDefinition(entryDefinitionToken,contextName,contextSubName);
						ret = new MethodEntry(new_method,"TabEntry",new ListEntry(arithmaticEntry,new MethodEntry(new_method,"ListEntry",new ListEntry(entryDefinitionEntry))));
					}
					else {
						ret = new MethodEntry(new_method,"TabEntry",new ListEntry(arithmaticEntry,new MethodEntry(new_method,"ListEntry",new ListEntry(new StringEntry(tabBrace.get("entry_names").getString())))));
					}
				}
			}
			else {
				return listEntry;
			}
		}
		else {
			IToken specificGenerator = entryDefinition.get("gen");
			StringEntry specificGeneratorName = null;
			if((isVariableName == true)){
				if((specificGenerator != null)){
					specificGeneratorName = new StringEntry(specificGenerator.getString());
				}
				else {
					specificGeneratorName = new StringEntry(generatorName);
				}
				ret = new MethodEntry(new_method,"ElementEntry",new ListEntry(new ElementEntry(GeneratorGenerator.generatorElementWithVariableElement,new ListEntry(specificGeneratorName,new StringEntry(elementNameToken.getString()))),new ListEntry(listEntry)));
			}
			else {
				if((specificGenerator != null)){
					String specificGeneratorClassName = Generators.generator.camelize(specificGenerator.getString());
					specificGeneratorName = new StringEntry(specificGeneratorClassName);
				}
				else {
					specificGeneratorName = new StringEntry(contextName);
				}
				ret = new MethodEntry(new_method,"ElementEntry",new ListEntry(new ElementEntry(GeneratorGenerator.generatorElementElement,new ListEntry(specificGeneratorName,new StringEntry(elementNameToken.getString()))),new ListEntry(listEntry)));
			}
		}
		ret.changeType("Entry");
		return ret;
	}
	public Entry generateListEntryDefinition(IToken listEntryDefinition,String contextName,String contextSubName){
		for(IToken.Key elementKey:listEntryDefinition.keySet()){
			if("list".equals(elementKey.getName())){
				IToken element = listEntryDefinition.get(elementKey);
				ListEntry ret = new ListEntry();
				for(IToken.Key atomKey:element.keySet()){
					if("entry_definition".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						ret.add(generateEntryDefinition(atom,contextName,contextSubName));
					}
					else if("generate_call".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						ret.add(generateMethodCall(atom,contextName,contextSubName));
					}
					else if("variable_or_token_name".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						ret.add(generateVariableOrTokenName(atom,contextName,contextSubName));
					}
					else if("variable_names".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String varName = atom.getString();
						VariableEntry variable = (VariableEntry)Generators.generator.getVariable(varName,contextName,contextSubName,"Entry","List Entry Definition(006)");
						String varType = variable.getType();
						if((!varType.contains("Entry"))){
							variable.changeType("Entry");
						}
						ret.add(new MethodEntry(varName));
					}
					else if("entry_names".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String entryName = atom.getString();
						VariableEntry variable = (VariableEntry)Generators.generator.getVariable(entryName,contextName,contextSubName,"Entry","List Entry Definition(007)");
						String varType = variable.getType();
						if((!varType.contains("Entry"))){
							variable.changeType("Entry");
						}
						ret.add(new MethodEntry(entryName));
					}
				}
				MethodEntry retEntry = new MethodEntry(new_method,"ListEntry",ret);
				retEntry.changeType("ListEntry");
				return retEntry;
			}
			else if("set".equals(elementKey.getName())){
				IToken element = listEntryDefinition.get(elementKey);
				ListEntry ret = new ListEntry();
				for(IToken.Key atomKey:element.keySet()){
					if("entry_definition".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						ret.add(generateEntryDefinition(atom,contextName,contextSubName));
					}
					else if("generate_call".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						ret.add(generateMethodCall(atom,contextName,contextSubName));
					}
					else if("variable_or_token_name".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						ret.add(generateVariableOrTokenName(atom,contextName,contextSubName));
					}
					else if("variable_names".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String varName = atom.getString();
						VariableEntry variable = (VariableEntry)Generators.generator.getVariable(varName,contextName,contextSubName,"Entry","List Entry Definition(006)");
						String varType = variable.getType();
						if((!varType.contains("Entry"))){
							variable.changeType("Entry");
						}
						ret.add(new MethodEntry(varName));
					}
					else if("entry_names".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String entryName = atom.getString();
						VariableEntry variable = (VariableEntry)Generators.generator.getVariable(entryName,contextName,contextSubName,"Entry","List Entry Definition(007)");
						String varType = variable.getType();
						if((!varType.contains("Entry"))){
							variable.changeType("Entry");
						}
						ret.add(new MethodEntry(entryName));
					}
				}
				MethodEntry retEntry = new MethodEntry(new_method,"SetEntry",ret);
				retEntry.changeType("ListEntry");
				return retEntry;
			}
			else if("exact_variable".equals(elementKey.getName())){
				IToken element = listEntryDefinition.get(elementKey);
				String variableName = element.get("variable_names").getString();
				VariableEntry variable = (VariableEntry)Generators.generator.getVariable(variableName,contextName,contextSubName,"ListEntry","ListEntryDefinition (020)");
				if((!variable.hasType())){
					variable.changeType("Entry");
				}
				MethodEntry ret = new MethodEntry(variableName);
				ret.changeType("ListEntry");
				return ret;
			}
		}
		throw new UnableToGenerateException("(003)",listEntryDefinition);
	}
	public void addContext(String contextName,String contextSubName,String newContextName,VariableEntry newDefaultEntry){
		if((!contexts.containsKey(contextName))){
			contexts.put(contextName,new HashMap<String,Map<String,VariableEntry>>());
			methodParameters.put(contextName,new HashMap<String,List<ITypeListener>>());
		}
		Map<String,VariableEntry> context = (Map<String,VariableEntry>)new HashMap<String,VariableEntry>();
		contexts.get(contextName).put(newContextName,context);
		if((contextSubName != null)){
			Set<String> iterableContext = (Set<String>)contexts.get(contextName).get(contextSubName).keySet();
			for(String varName:iterableContext){
				context.put(varName,contexts.get(contextName).get(contextSubName).get(varName));
			}
		}
		context.put(DEFAULT_TOKEN,newDefaultEntry);
		context.put(newDefaultEntry.getName(),newDefaultEntry);
		if((contextSubName != null)){
			VariableEntry previousDefault = (VariableEntry)contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN);
			context.put(previousDefault.getName(),previousDefault);
		}
	}
	public String getCastType(IToken castToken,String contextName,String contextSubName){
		StringBuilder ret = (StringBuilder)null;
		IToken casty = castToken;
		List<IToken> castToTypeCastToType = casty.getAll("castToType");
		if(castToTypeCastToType != null){
			for(IToken castToType:castToTypeCastToType){
				if((ret == null)){
					ret = new StringBuilder();
				}
				else {
					ret.append(")(");
				}
				MethodEntry type = (MethodEntry)Generators.generator.generateAngleClasses(castToType,contextName,contextSubName);
				ret.append(type.getType());
			}
		}
		if((ret == null)){
			return (String)null;
		}
		else {
			return ret.toString();
		}
	}
	public VariableEntry getParameter(IToken parameter,String contextName,String contextSubName,String methodName){
		String parameterName = parameter.get("takeName").getString();
		String type_unknown = ITypeListener.TYPE_UNKNOWN;
		VariableEntry variable = new VariableEntry(parameterName,type_unknown);
		String castType = Generators.generator.getCastType(parameter,contextName,contextSubName);
		if((castType != null)){
			variable.setType(castType);
		}
		contexts.get(contextName).get(methodName).put(parameterName,variable);
		return variable;
	}
	public VariableEntry getVariable(String variableName,String contextName,String contextSubName,String defaultType,String errorIfUndefined){
		VariableEntry ret = (VariableEntry)null;
		if((!contexts.get(contextName).get(contextSubName).containsKey(variableName))){
			if((contexts.get(contextName).get(LOCAL_CONTEXT).containsKey(variableName))){
				ret = contexts.get(contextName).get(LOCAL_CONTEXT).get(variableName);
				return ret;
			}
			VariableEntry variable = new VariableEntry(variableName,defaultType);
			variable.setDefined(false);
			contexts.get(contextName).get(contextSubName).put(variableName,variable);
			checks.add(new DefinedCheck(variable,truth,""+errorIfUndefined+""));
		}
		ret = contexts.get(contextName).get(contextSubName).get(variableName);
		return ret;
	}
	public void setup(ParseContext data){
		this.addPage();
		file = data.getInput();
		String fileName = data.getFileName();
		Integer indexOfDot = fileName.lastIndexOf(".");
		if((indexOfDot > -1)){
			fileName = fileName.substring(0,indexOfDot);
		}
		directory = new File(Generators.generator.buildString("../Generated",Generators.generator.camelize(fileName),"/src/gen/"));
		directory.mkdirs();
		Generators.generator.println(directory.getAbsolutePath());
	}
	public void generate(ParseContext data){
		ParseList classDefinitionsList = (ParseList)data.getList("class_definitions");
		Generators.generator.generateAll(classDefinitionsList.getNewTokens(),"class_dec");
		Generators.generator.addFile(directory,"Generators.java",new ListEntry(new StringEntry("Generators"),new StringEntry("Object")));
		ListEntry genNames = new ListEntry();
		genNames.setDelimiter("\n\tpublic static final ");
		genNames.startWithDelimiter(true);
		ListEntry genList = new ListEntry();
		ParseList metaDeclarationsList = (ParseList)data.getList("meta_declarations");
		IToken metaTokens = metaDeclarationsList.getNewTokens();
		for(IToken.Key metaTokenKey:metaTokens.keySet()){
			IToken metaToken = metaTokens.get(metaTokenKey);
			String pureName = metaToken.get("metaName").getString();
			String metaName = Generators.generator.buildString(Generators.generator.camelize(pureName),"Flow");
			Generators.generator.addFile(directory,Generators.generator.buildString(metaName,".java"),new ListEntry(new StringEntry(metaName),new StringEntry("FlowController")));
			Generators.generator.addEntry(directory,Generators.generator.buildString(metaName,".java"),"meta",new ListEntry(generateMeta(metaToken)));
		}
		for(String className:generatorNames){
			String realClassName = Generators.generator.camelize(className);
			StringEntry generatorName = new StringEntry(realClassName);
			genNames.add(new ElementEntry(GeneratorGenerator.generatorDeclarationElement,new ListEntry(generatorName,new StringEntry(className),generatorName)));
			genList.add(className);
		}
		ListEntry gen = new ListEntry(genNames,new ElementEntry(GeneratorGenerator.generatorListDeclarationElement,new ListEntry(genList)));
		gen.setDelimiter("\n\tpublic static final ");
		Generators.generator.addEntry(directory,"Generators.java","generators",gen);
	}
	public Entry generateMeta(IToken meta){
		String metaName = meta.get("metaName").getString();
		String realMetaName = Generators.generator.camelize(metaName);
		Entry mainCall = new ElementEntry(GeneratorGenerator.flowMainElement,new ListEntry(new StringEntry(realMetaName)));
		ListEntry variableDeclarations = new ListEntry();
		variableDeclarations.setDelimiter("\n\tprivate ");
		variableDeclarations.startWithDelimiter(true);
		ListEntry methodDeclarations = new ListEntry();
		methodDeclarations.setDelimiter("");
		ListEntry generateDeclaration = new ListEntry();
		generateDeclaration.setDelimiter("");
		ListEntry ret = new ListEntry(mainCall,variableDeclarations,generateDeclaration,methodDeclarations);
		ret.setDelimiter("\n");
		Generators.generator.addContext(metaName,null,LOCAL_CONTEXT,NO_DEFAULT_TOKEN);
		for(IToken.Key elementKey:meta.keySet()){
			if("variable_declaration".equals(elementKey.getName())){
				IToken element = meta.get(elementKey);
				VariableEntry variable = (VariableEntry)generateVariableDeclaration(element,metaName,LOCAL_CONTEXT);
				variableDeclarations.add(variable);
				TypeEntry type = new TypeEntry(variable);
				String getMethodName = Generators.generator.buildString("get",Generators.generator.camelize(variable.getName()));
				String variableName = variable.getName();
				methodDeclarations.add(new TabEntry(0,new ListEntry(new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(type,new StringEntry(getMethodName),new ListEntry(),new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.returnCallElement,new ListEntry(new StringEntry(variableName))))))))));
			}
			else if("meta_method_declaration".equals(elementKey.getName())){
				IToken element = meta.get(elementKey);
				String methodName = element.get("methodName").getString();
				String castToType = Generators.generator.getCastType(element,metaName,null);
				Generators.generator.addContext(metaName,LOCAL_CONTEXT,methodName,NO_DEFAULT_TOKEN);
				ListEntry parameters = new ListEntry();
				ListEntry body = new ListEntry();
				body.setDelimiter("");
				for(IToken.Key atomKey:element.keySet()){
					if("parameter".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						VariableEntry param = (VariableEntry)Generators.generator.getParameter(atom,metaName,LOCAL_CONTEXT,methodName);
						if((methodName.equals("generate"))){
							param.changeType("ParseContext");
						}
						parameters.add(param);
					}
					else if("entry_declaration".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						body.add(generateEntryDeclaration(atom,2,metaName,methodName));
					}
					else if("body_element".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						body.add(generateBodyElement(atom,2,metaName,methodName,null));
					}
				}
				if((castToType == null)){
					castToType = "void";
				}
				methodDeclarations.add(new TabEntry(0,new ListEntry(new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry(castToType),new StringEntry(methodName),new ListEntry(parameters),body)))));
			}
		}
		if((!contexts.get(metaName).containsKey("assignListElementNames"))){
			methodDeclarations.add(new TabEntry(0,new ListEntry(new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("void"),new StringEntry("assignListElementNames"),new StringEntry("ParseContext data, IToken rootToken"),new ListEntry())))));
		}
		if((!contexts.get(metaName).containsKey("setup"))){
			methodDeclarations.add(new TabEntry(0,new ListEntry(new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("void"),new StringEntry("setup"),new StringEntry("ParseContext context"),new ListEntry())))));
		}
		methodDeclarations.add(new TabEntry(0,new ListEntry(new ElementEntry(GeneratorGenerator.methodDeclarationElement,new ListEntry(new StringEntry("Generator[]"),new StringEntry("getGenerators"),new ListEntry(),new TabEntry(2,new ListEntry(new ElementEntry(GeneratorGenerator.returnCallElement,new ListEntry(new StringEntry("Generators._"))))))))));
		return ret;
	}

	public Boolean getTruth(){
		return truth;
	}

	public File getDirectory(){
		return directory;
	}

	public String getFile(){
		return file;
	}

	public Set<String> getClassNames(){
		return classNames;
	}

	public List<String> getGeneratorNames(){
		return generatorNames;
	}

	public Map<String,Map<String,Map<String,VariableEntry>>> getContexts(){
		return contexts;
	}

	public Map<String,Map<String,List<ITypeListener>>> getMethodParameters(){
		return methodParameters;
	}

	public Map<String,ListEntry> getElementBodies(){
		return elementBodies;
	}

	public Map<String,ListEntry> getConstructorBodies(){
		return constructorBodies;
	}

	public Integer getIfIndex(){
		return ifIndex;
	}

	public String getGeneratorName(){
		return generatorName;
	}

	public StringEntry getNewMethod(){
		return new_method;
	}

	public String getName(){
		return "Generator";
	}

	public IParser getLazyNameParser(){
		return null;
	}
}