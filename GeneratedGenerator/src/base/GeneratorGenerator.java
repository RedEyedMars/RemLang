package base;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;


import base.GeneratorGenerator.VariableEntry;
import lists.ClassNames;
import lists.Listnames;
import lists.Tokens;

public class GeneratorGenerator extends Generator {

	private static final String LOCAL_CONTEXT = "$local";
	private static final String DEFAULT_TOKEN = "$token";
	private static final String TYPE_UNKNOWN = "$UNKNOWN";
	private static final Entry METHOD_NEW = new StringEntry("new");
	private VariableEntry NO_DEFAULT_TOKEN = null;
	private File directory;
	private Set<String> classNames = new HashSet<String>();
	private List<String> generatorNames = new ArrayList<String>();
	private Map<String,Map<String,Map<String,VariableEntry>>> contexts = new HashMap<String,Map<String,Map<String,VariableEntry>>>();
	private Map<String,Map<String,List<ITypeListener>>> methodParameters = new HashMap<String,Map<String,List<ITypeListener>>>();
	private List<Check> checks  = new ArrayList<Check>();
	private Map<String,ListEntry> elementsBodies = new HashMap<String,ListEntry>();
	private Map<String,ListEntry> constructorBodies = new HashMap<String,ListEntry>();
	private MethodEntry returnType = null;
	private int ifIndex=0;
	private String generatorName = "";
	private String[] outline = new String[]{
			"package gen;\n\n"+
					"import java.io.*;\n"+
					"import java.util.*;\n"+
					"import com.rem.parser.*;\n"+
					"import com.rem.parser.generation.*;\n"+
					"import com.rem.parser.token.*;\n"+
					"import com.rem.parser.parser.*;\n"+
					"import gen.entries.*;\n"+
					"import gen.properties.*;\n"+
					"import lists.*;\n\n"+
					"public class ",/*Class Name*/" extends ",/*Extension*/" {\n",/*Contents*/ "\n}"};
	private String[] variableDeclaration = new String[]{
			"",/*Type Name*/" ",/*Variable Name*/" = ",/*Assignment*/";"};
	private String[] variablePrototype = new String[]{
			"",/*Type Name*/" ",/*Variable Name*/""};
	private String[] generatorCall = new String[]{
			"Generators.",/*Generator Name*/""};
	private String[] generatorDeclaration = new String[]{
			"",/*Class Name*/"Generator ",/*Class name*/" = new ",/*Assignment*/"Generator();"};
	private String[] methodCall = new String[]{
			"",/*Subject Name*/".",/*Method Name*/"(",/*Parameters*/")"};
	private String[] generatorElement = new String[]{
			"",/*Generator Name*/"Generator.",/*Element Name*/"Element"};
	private String[] generatorElementWithVariable = new String[]{
			"Generators.",/*Generator Name*/",",/*Variable Name*/""};
	private String[] castCall = new String[]{
			"(",/*Type Name*/")",/*Subject*/""};
	private String[] exactCall = new String[]{
			"",/*Method Name*/""};
	private String[] exactDuoCall = new String[]{
			"",/*First*/"",/*Second*/""};
	private String[] exactWithAngleCall = new String[]{
			"",/*Method Name*/"<",/*Parameters*/">"};
	private String[] newObjectCall = new String[]{
			"new ",/*Type Name*/"(",/*Parameters*/")"};
	private String[] newMethodWithBracesCall = new String[]{
			"new ",/*Type Name*/"<",/*Parameters*/">()"};
	private String[] operatorCall = new String[]{
			"",/*Left*/" ",/*Operator*/" ",/*Right*/""};
	private String[] noSubjectCall = new String[]{
			"",/*Method Name*/"(",/*Parameter*/")"};
	private String[] errorCall = new String[]{
			"System.err.println(",/*Error Output*/");"};
	private String[] returnCall = new String[]{
			"return ",/*Output*/";"};
	private String[] newBooleanCall = new String[]{
			"(",/*Parameters*/")"};
	private String[] setCall = new String[]{
			"",/*Subject*/" = ",/*Argument*/";"};
	private String[] equalsCall = new String[]{
			"",/*Subject*/".equals(",/*Argument*/")"};
	private String[] notEqualsCall = new String[]{
			"!",/*Subject*/".equals(",/*Argument*/")"};
	private String[] newStringListCall = new String[]{
			"new String[]{",/*Parameters*/"}"};
	private String[] staticGetVariableCall = new String[]{
			"",/*Class Name*/".",/*Variable Name*/"",""};
	private String[] staticMethodCall = new String[]{
			"",/*Class Name*/".",/*Method Name*/"(",")"};
	private String[] ifStatementCall = new String[]{
			"if(",/*Boolean*/"){",/*Body*/""};
	private String[] elseIfStatementCall = new String[]{
			"else if(",/*Boolean*/"){",/*Body*/""};
	private String[] elseStatementCall = new String[]{
			"else {",/*Body*/""};
	private String[] forStatementCall = new String[]{
			"for(",/*Key Name*/":",/*Token Name*/"){",/*Body*/""};
	private String[] tokenForStatementCall = new String[]{
			"for(IToken.Key ",/*Key Name*/":",/*Token Name*/".keySet()){",/*Body*/""};
	private String[] methodDeclaration = new String[]{
			"\tpublic ",/*Method Type*/" ",/*Method Name*/"(",/*Method Parameters*/"){",/*Body*/"\n\t}"};
	private String[] constructor = new String[]{
			"\tpublic ",/*Class Name*/"Generator(){",/*Constructor Body*/"\n\t}"};
	private String[] publicStaticFinalVariableDeclaration = new String[]{
			"public static final ",/*Type*/" ",/*Name*/" = ",/*Assignement*/";"};
	private String[] semicoloned = new String[]{
			"",/*Body*/";"};
	private String file;

	public GeneratorGenerator(){
		addElement("outline",outline);
		addElement("variableDeclaration",variableDeclaration);
		addElement("variablePrototype",variablePrototype);
		addElement("generatorCall",generatorCall);
		addElement("generatorDeclaration",generatorDeclaration);
		addElement("methodCall",methodCall);
		addElement("castCall",castCall);
		addElement("exactCall",exactCall);
		addElement("exactDuoCall",exactDuoCall);
		addElement("newObjectCall",newObjectCall);
		addElement("operatorCall",operatorCall);
		addElement("noSubjectCall",noSubjectCall);
		addElement("staticGetVariableCall",staticGetVariableCall);
		addElement("staticMethodCall",staticMethodCall);
		addElement("errorCall",errorCall);
		addElement("returnCall",returnCall);
		addElement("exactWithAngleCall",exactWithAngleCall);
		addElement("newMethodWithBracesCall",newMethodWithBracesCall);
		addElement("newBooleanCall",newBooleanCall);
		addElement("setCall",setCall);
		addElement("equalsCall",equalsCall);
		addElement("notEqualsCall",notEqualsCall);
		addElement("newStringListCall",newStringListCall);
		addElement("ifStatementCall",ifStatementCall);
		addElement("elseIfStatementCall",elseIfStatementCall);
		addElement("elseStatementCall",elseStatementCall);
		addElement("forStatementCall",forStatementCall);
		addElement("tokenForStatementCall",tokenForStatementCall);
		addElement("methodDeclaration",methodDeclaration);
		addElement("generatorElement",generatorElement);
		addElement("generatorElementWithVariable",generatorElementWithVariable);
		addElement("constructor",constructor);
		addElement("publicStaticFinalVariableDeclaration",publicStaticFinalVariableDeclaration);
		addElement("semicoloned",semicoloned);
	}

	@Override
	public void generateRoot(IToken root){

		String className = "$";
		ListEntry classElements = new ListEntry();
		classElements.setDelimiter("\n");
		ListEntry variableDeclarations = new ListEntry();
		variableDeclarations.setDelimiter("\n\tprivate ");
		variableDeclarations.startWithDelimiter(true);
		classElements.add(variableDeclarations);
		classElements.add(new StringEntry(""));
		ListEntry methodDeclarations = new ListEntry();
		methodDeclarations.setDelimiter("\n");
		classElements.add(methodDeclarations);
		ListEntry variableMethods = new ListEntry();
		variableMethods.setDelimiter("\n");
		classElements.add(variableMethods);

		for(IToken.Key key:root.keySet()){
			if("className".equals(key.getName())){
				String genName = root.get(key).getString();
				generatorName = genName;
				className = camelize(genName);
				classNames.add(className);
				generatorNames.add(genName);
				contexts.put(className, new HashMap<String,Map<String,VariableEntry>>());
				elementsBodies.put(className, new ListEntry());
				elementsBodies.get(className).setDelimiter("");
				constructorBodies.put(className, new ListEntry());
				constructorBodies.get(className).setDelimiter("");
				methodParameters.put(className, new HashMap<String,List<ITypeListener>>());
				Map<String,VariableEntry> varMap = new HashMap<String,VariableEntry>();
				varMap.put(DEFAULT_TOKEN, new VariableEntry("root","IToken",null));
				contexts.get(className).put(LOCAL_CONTEXT, varMap);
				methodDeclarations.add(elementsBodies.get(className));
				methodDeclarations.add(new ElementEntry(Generators.generator,"constructor",new ListEntry(new StringEntry(className),constructorBodies.get(className))));
			}
			else if("auxillary_declaration".equals(key.getName())){
				methodDeclarations.add(generateAuxillaryDeclaration(root.get(key),className,LOCAL_CONTEXT));
			}
			else if("variable_declaration".equals(key.getName())){
				VariableEntry variable = (VariableEntry) generateVariableDeclaration(root.get(key),className,LOCAL_CONTEXT);
				variableDeclarations.add(variable);

				MethodEntry typeEntry = new MethodEntry(null,variable.getType(),null){
					@Override
					public void changeType(String newType){
						super.changeType(newType);
						this.methodName.set(newType);
					}
				};
				variable.addListener(typeEntry);
				TypeEntry type = new TypeEntry(variable);
				variableMethods.add(new TabEntry(0,new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(type,new StringEntry("get"+camelize(variable.getName())),new ListEntry(),
						new TabEntry(2,new ElementEntry(Generators.generator,"returnCall", new ListEntry(new StringEntry(variable.getName()))))))));
			}
			else if("constant_declaration".equals(key.getName())){
				VariableEntry variable = (VariableEntry) generateVariableDeclaration(root.get(key),className,LOCAL_CONTEXT);
				variable.setStatic(true);
				variable.setFinal(true);
				variableDeclarations.add(variable);
			}
			else if("element_declaration".equals(key.getName())){
				generateElementDeclaration(root.get(key),className,LOCAL_CONTEXT);
			}
			else if("generation_declaration".equals(key.getName())){
				methodDeclarations.add(generateGenerationDeclaration(root.get(key),className,LOCAL_CONTEXT));
			}
		}

		for(Check check:checks){
			check.check();
		}
		classElements.add(new TabEntry(0,new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("String"),new StringEntry("getName"),new ListEntry(),
				new TabEntry(2,new ElementEntry(Generators.generator,"returnCall", new ListEntry(new QuoteEntry(className))))))));
		if(!contexts.get(className).containsKey("generateRoot")){
			classElements.add(new TabEntry(0,new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("void"),new StringEntry("generateRoot"),new StringEntry("IToken root"),new ListEntry()))));
		}
		if(!contexts.get(className).containsKey("generate")){
			classElements.add(new TabEntry(0,new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("void"),new StringEntry("generate"),new StringEntry("ParseContext data"),new ListEntry()))));
		}		
		if(!contexts.get(className).containsKey("assignListElementNames")){
			classElements.add(new TabEntry(0,new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("void"),new StringEntry("assignListElementNames"),new StringEntry("ParseContext context, IToken rootToken"),new ListEntry()))));
		}
		if(!contexts.get(className).get(LOCAL_CONTEXT).containsKey("lazyNameParser")){
			classElements.add(new TabEntry(0,new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("IParser"),new StringEntry("getLazyNameParser"),new ListEntry(),
					new TabEntry(2,new ElementEntry(Generators.generator,"returnCall", new ListEntry(new StringEntry("null"))))))));
		}

		addFile(getName(),directory,className+"Generator.java",new ListEntry(new StringEntry(className+"Generator"), new StringEntry("Generator")));
		addEntry(getName(),directory,className+"Generator.java","celements",new ListEntry(classElements));

	}

	//	whitetab{tabs} AUXILLARY NAME as methodName 
	//			( TAKES NAME as takeName in variable_names ( COMMA NAME as takeName in variable_names )* )? 
	//			( entry_declaration{tabs+1} | body_element{tabs+1} )+
	public Entry generateAuxillaryDeclaration(IToken auxillary, String contextName, String contextSubName){
		Entry ret = null;
		String methodName = "";
		ListEntry parameters = new ListEntry();
		ListEntry body = new ListEntry();
		body.setDelimiter("");
		for(IToken.Key key:auxillary.keySet()){
			if("methodName".equals(key.getName())){
				String auxillaryName = auxillary.get(key).getString();
				methodName = auxillaryName;
				addContext(contextName,contextSubName,methodName,NO_DEFAULT_TOKEN);

				ret = new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("void"),new StringEntry(methodName),parameters,body));
			}
			else if("parameter".equals(key.getName())){
				VariableEntry var = getParameter(auxillary.get(key),contextName,contextSubName,methodName);
				parameters.add(var);
			}
			else if("entry_declaration".equals(key.getName())){
				body.add(generateEntryDeclaration(auxillary.get(key),2,contextName,methodName));
			}
			else if("body_element".equals(key.getName())){
				body.add(generateBodyElement(auxillary.get(key),2,contextName,methodName));
			}
		}
		return ret;
	}
	/*
	 * 
element_declaration has tabs
	whitetab{tabs} ELEMENTS element_definition{tabs+1}+

element_definition has tabs
	whitetab{tabs} NAME as elementName in element_names element_entry{tabs+1} ( (SPACES? NAME )* as entry element_entry{tabs+1} )*

element_entry has tabs
	whitetab{tabs}? QUOTE ( PLUS whitetab{tabs} QUOTE)*
	 */
	public Entry generateElementDeclaration(IToken declaration, String contextName, String contextSubName){
		for(IToken.Key key:declaration.keySet()){
			if("element_definition".equals(key.getName())){
				IToken definition = declaration.get(key);
				String elementName = "NO_NAME";
				ListEntry elementParameters = new ListEntry();
				Entry entryDef = null; 
				for(IToken.Key subKey:definition.keySet()){
					if("elementName".equals(subKey.getName())){
						elementName = definition.get(subKey).getString();
					}
					else if("element_entry".equals(subKey.getName())){
						ListEntry entryD = new ListEntry();
						entryD.setDelimiter("");
						if(entryDef!=null){
							entryD.add(new StringEntry("/*"));
							entryD.add(entryDef);
							entryD.add(new StringEntry("*/"));
							entryDef = null;
						}
						ListEntry entry = new ListEntry();
						entry.setDelimiter("+\n\t\t\t");
						IToken element_entry = definition.get(subKey);
						for(IToken.Key entryKey:element_entry.keySet()){
							if("entry".equals(entryKey.getName())){
								entry.add(new QuoteEntry(element_entry.get(entryKey).getString()));
							}
						}
						elementParameters.add(new ElementEntry(Generators.generator,"exactDuoCall",new ListEntry(entryD,entry)));
					}
					else if("entry".equals(subKey.getName())){
						StringBuilder entryName = new StringBuilder();
						String space = "";
						for(IToken.Key entryKey:definition.get(subKey).keySet()){
							if("NAME".equals(entryKey.getName())){
								entryName.append(space);
								entryName.append(definition.get(subKey).get(entryKey).getString());
								space = " ";
							}
						}
						entryDef = new StringEntry(entryName.toString());
					}
				}
				constructorBodies.get(contextName).add(
						new TabEntry(2,
								new ElementEntry(Generators.generator,"semicoloned",new ListEntry(new ElementEntry(Generators.generator,"noSubjectCall",
										new ListEntry(new StringEntry("addElement"),new ListEntry(new QuoteEntry(elementName),
												new ElementEntry(Generators.generator,"exactDuoCall",new ListEntry(new StringEntry(elementName),new StringEntry("Element"))))))))));
				elementsBodies.get(contextName).add(
						new TabEntry(1,
								new ElementEntry(Generators.generator,"publicStaticFinalVariableDeclaration",new ListEntry(
										new StringEntry("Element"),
										new StringEntry(elementName+"Element"),
										new ListEntry(new ElementEntry(Generators.generator,"newObjectCall",
												new ListEntry(new StringEntry("Element"),new ListEntry(
														new QuoteEntry(elementName),
														new ElementEntry(Generators.generator,"newStringListCall",new ListEntry(elementParameters))))))))));
			}
		}
		return null;
	}

	/*
	 * 
generation_declaration has tabs
	whitetab{tabs} GENERATE NAME as tokenName in token_names ( TAKES NAME as takeName in variable_names ( COMMA NAME as takeName in variable_names )* )? ( entry_declaration{tabs+1} | body_element{tabs+1} )+ 

	 */
	public Entry generateGenerationDeclaration(IToken generation, String contextName, String contextSubName){
		Entry ret = null;
		String methodName = "generate";
		ListEntry parameters = new ListEntry();
		ListEntry body = new ListEntry();
		body.setDelimiter("");
		boolean undefined = false;
		for(IToken.Key key:generation.keySet()){
			if("tokenName".equals(key.getName())){
				String generateName = generation.get(key).getString();
				methodName = "generate"+camelize(generateName);
				VariableEntry defaultToken = new VariableEntry(generateName,"IToken");
				addContext(contextName,contextSubName,methodName,defaultToken);
				parameters.add(defaultToken);
				if(!methodParameters.get(contextName).containsKey(methodName)){
					methodParameters.get(contextName).put(methodName, new ArrayList<ITypeListener>());
					methodParameters.get(contextName).get(methodName).add(defaultToken);
					undefined = true;
				}
				if("root".equals(generateName)){
					ret = new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("void"),new StringEntry(methodName),parameters,body));
				}
				else {
					ret = new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("Entry"),new StringEntry(methodName),parameters,body));
				}
			}
			else if("parameter".equals(key.getName())){

				String parameter = generation.get(key).get("takeName").getString();
				VariableEntry var;
				if(undefined){
					var = new VariableEntry(parameter,TYPE_UNKNOWN);
					methodParameters.get(contextName).get(methodName).add(var);
				}
				else if("generate".equals(methodName)){
					var = new VariableEntry(parameter,TYPE_UNKNOWN);
				}
				else {
					ITypeListener listener = methodParameters.get(contextName).get(methodName).get(parameters.size());
					var = new VariableEntry(parameter,listener.getType());
					var.addListener(listener);
				}
				String castToType = getCastType(generation.get(key),contextName,contextSubName);
				if(castToType!=null){
					var.changeType(castToType);
				}
				parameters.add(var);
				if(!contexts.get(contextName).containsKey(methodName)){
					addContext(contextName,contextSubName,methodName,NO_DEFAULT_TOKEN);
				}
				contexts.get(contextName).get(methodName).put(parameter,var);
			}
			else if("entry_declaration".equals(key.getName())){
				body.add(generateEntryDeclaration(generation.get(key),2,contextName,methodName));
			}
			else if("body_element".equals(key.getName())){
				body.add(generateBodyElement(generation.get(key),2,contextName,methodName));
			}
		}
		if(ret==null){
			if(!contexts.get(contextName).containsKey(methodName)){
				addContext(contextName,contextSubName,methodName,NO_DEFAULT_TOKEN);
			}
			ret = new ElementEntry(Generators.generator,"methodDeclaration",new ListEntry(new StringEntry("void"),new StringEntry(methodName),parameters,body));
			((ITypeListener)parameters.get(0)).changeType("ParseContext");
		}
		return ret;
	}

	/*
	 * entry_declaration has tabs
	whitetab{tabs} ENTRY NAME as entyName in entry_names ( QUOTE as delimiter )? EQUALSIGN ( entry_definition{tabs+1} | NULL )
	 */
	public Entry generateEntryDeclaration(IToken declaration,int tabs, String contextName, String contextSubName){
		String entryName = declaration.get("entryName").getString();		
		Entry assignment = null;
		IToken definition = declaration.get("entry_definition");
		VariableEntry entry = null;
		if(definition!=null){
			assignment = generateEntryDefinition(definition,contextName,contextSubName);
			entry = new VariableEntry(entryName,((MethodEntry)assignment).getType(),assignment);
		}
		else {
			assignment = new StringEntry("null");
			entry = new VariableEntry(entryName,"Entry",assignment);
		}
		contexts.get(contextName).get(contextSubName).put(entryName, entry);

		ListEntry section = new ListEntry();
		section.setDelimiter("");
		section.add(new TabEntry(tabs,entry));
		IToken delimToken = declaration.get("delimiter");
		if(delimToken!=null){
			section.add(
					new TabEntry(tabs,
							new ElementEntry(Generators.generator,"semicoloned",
									new ListEntry(new MethodEntry(new StringEntry(entryName),"setDelimiter",new ListEntry(new QuoteEntry(delimToken.getString())))))));
			entry.changeType("ListEntry");
		}
		return section;
	}

	/*
	error_statement{tabs}
	return_statement{tabs}
	if_statement{tabs}
	token_declaration{tabs}
	variable_declaration{tabs}
	set_call{tabs}
	flip_switch{tabs}
	token_expansion{tabs}
	method_call{tabs}
	 */
	public Entry generateBodyElement(IToken bodyElement,int tabs, String contextName, String contextSubName){
		for(IToken.Key key:bodyElement.keySet()){
			if("error_statement".equals(key.getName())){
				return new TabEntry(tabs,generateError(bodyElement.get(key),contextName,contextSubName));
			}
			else if("return_statement".equals(key.getName())){
				return new TabEntry(tabs,generateReturn(bodyElement.get(key),contextName,contextSubName));
			}
			else if("if_statement".equals(key.getName())){
				return generateIfStatement(bodyElement.get(key),tabs,contextName,contextSubName);				
			}
			else if("each_call".equals(key.getName())){
				return generateEachCall(bodyElement.get(key),tabs,contextName,contextSubName);				
			}
			else if("token_declaration".equals(key.getName())){
				VariableEntry token = (VariableEntry) generateVariableDeclaration(bodyElement.get(key),contextName,contextSubName);
				token.changeType("IToken");
				return new TabEntry(tabs,token);
			}
			else if("variable_declaration".equals(key.getName())){
				return new TabEntry(tabs,generateVariableDeclaration(bodyElement.get(key),contextName,contextSubName));
			}
			else if("set_call".equals(key.getName())){
				return new TabEntry(tabs,generateSetCall(bodyElement.get(key),contextName,contextSubName));
			}
			else if("flip_switch".equals(key.getName())){
				return generateFlipSwitch(bodyElement.get(key),tabs,contextName,contextSubName);
			}
			else if("token_expansion".equals(key.getName())){
				return generateTokenExpansion(bodyElement.get(key),tabs,contextName,contextSubName);
			}
			else if("method_call".equals(key.getName())){
				return 
						new TabEntry(tabs,
								new ListEntry(new ElementEntry(Generators.generator,"semicoloned",new ListEntry(generateMethodCall(bodyElement.get(key),contextName,contextSubName)))));
			}
		}

		throw new UnableToGenerateException("Method Parameter (004)",bodyElement);
	}

	// whitetab{tabs} ERROR ( whitetab{tabs+1}? (QUOTE|variable_or_token_name))+
	public Entry generateError(IToken error, String contextName, String contextSubName){
		ListEntry output = new ListEntry();
		output.setDelimiter("+");
		for(IToken.Key key:error.keySet()){
			if("entry".equals(key.getName())){
				output.add(new QuoteEntry(error.get(key).getString()));
			}
			else if("variable_or_token_name".equals(key.getName())){
				output.add(generateVariableOrTokenName(error.get(key),contextName, contextSubName));
			}
		}
		return new ElementEntry(Generators.generator,"errorCall",new ListEntry(output));
	}
	//whitetab{tabs} RETURN ( generate_call{tabs+1} | entry_definition{tabs+1} | method_call{tabs+1} | whitetab{tabs+1} (entry_name|NULL) )
	public Entry generateReturn(IToken returnToken, String contextName, String contextSubName){
		MethodEntry ret = new MethodEntry("returnCall",new ListEntry());
		for(IToken.Key key:returnToken.keySet()){
			if("generate_call".equals(key.getName())||"method_call".equals(key.getName())){
				MethodEntry call = (MethodEntry) generateMethodCall(returnToken.get(key), contextName, contextSubName);
				ret.getEntries().add(call);
				ret.changeType(call.getType());
			}
			else if("boolean_statement".equals(key.getName())){
				ret.getEntries().add(generateBooleanStatement(returnToken.get(key), contextName, contextSubName));
				ret.changeType("Boolean");
			}
			else if("entry_definition".equals(key.getName())){
				MethodEntry entry = (MethodEntry) generateEntryDefinition(returnToken.get(key), contextName, contextSubName);
				ret.getEntries().add(entry);
				ret.changeType(entry.getType());
			}
			else if("NULL".equals(key.getName())){
				ret.getEntries().add(new ListEntry(new StringEntry("null")));
				ret.setIsNull(true);
			}
			else if("entry_names".equals(key.getName())){
				String entryName = returnToken.get(key).getString();
				if(!contexts.get(contextName).get(contextSubName).containsKey(entryName)){
					VariableEntry variable = new VariableEntry(entryName,"Entry");
					variable.setDefined(false);
					contexts.get(contextName).get(contextSubName).put(entryName,variable);
					checks.add(new DefinedCheck(variable,"Return (010)"));
				}
				contexts.get(contextName).get(contextSubName).get(entryName).addListener(ret);
				ret.getEntries().add(new StringEntry(entryName)); 
			}
		}
		if(returnType!=null&&ret.hasType()){
			returnType.changeType(ret.getType());
		}
		return ret;
	}

	//whitetab{tabs} EACH NAME as eachName in variable_names IN variable_name as iterable body_element{tabs+1}+
	public Entry generateEachCall(IToken each,int tabs, String contextName, String contextSubName){
		ListEntry forComplete = new ListEntry();
		forComplete.setDelimiter("");
		VariableEntry variable = new VariableEntry(each.get("eachName").getString(),TYPE_UNKNOWN);
		String iterableName = each.get("iterable").getString();
		VariableEntry iterable = null;
		if(contexts.get(contextName).get(contextSubName).containsKey(iterableName)){
			iterable = contexts.get(contextName).get(contextSubName).get(iterableName);
			int indexOfAngle = iterable.getType().indexOf('<');
			if(indexOfAngle>-1){
				int indexOfSecond = iterable.getType().indexOf(',');
				if(indexOfSecond==-1){
					indexOfSecond = iterable.getType().lastIndexOf('>');
				}
				variable.changeType(iterable.getType().substring(indexOfAngle+1, indexOfSecond));
			}
		}
		else {
			checks.add(new ContextCheck(contextName,contextSubName,iterableName,"Each (011)"));
		}
		String forName = contextSubName+".for"+ifIndex++;
		addContext(contextName,contextSubName,forName,contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN));
		contexts.get(contextName).get(forName).put(variable.getName(), variable);
		ListEntry forBody = new ListEntry();
		forBody.setDelimiter("");
		List<IToken> bodyElements = each.getAll("body_element");
		if(bodyElements!=null){
			for(IToken bodyElement:bodyElements){
				forBody.add(generateBodyElement(bodyElement,tabs+1,contextName,forName));
			}
		}
		Entry forStatement = new ElementEntry(Generators.generator,"forStatementCall",new ListEntry(variable,new StringEntry(iterableName),forBody));
		forComplete.add(new TabEntry(tabs,forStatement));
		forComplete.add(new TabEntry(tabs,new StringEntry("}")));
		return forComplete;
	}

	//whitetab{tabs} IF boolean_statement ( body_element{tabs+1} )+ else_statement{tabs}?
	public Entry generateIfStatement(IToken ifStatement, int tabs, String contextName, String contextSubName){
		ListEntry ifPart = new ListEntry();
		ifPart.setDelimiter("");
		ListEntry ifBody = new ListEntry();
		ifBody.setDelimiter("");
		ListEntry elseBody = null;
		String ifContextName = contextSubName+".if"+ifIndex++;
		addContext(contextName,contextSubName,ifContextName,contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN));
		for(IToken.Key key:ifStatement.keySet()){
			if("boolean_statement".equals(key.getName())){
				ifPart.add(generateBooleanStatement(ifStatement.get(key),contextName,contextSubName));
			}
			else if("body_element".equals(key.getName())){				
				ifBody.add(generateBodyElement(ifStatement.get(key),tabs+1,contextName,ifContextName));
			}
			else if("else_statement".equals(key.getName())){
				IToken else_statement = ifStatement.get(key);
				elseBody = new ListEntry();
				elseBody.setDelimiter("\n");
				for(IToken.Key subKey:else_statement.keySet()){
					if("body_element".equals(subKey.getName())){
						elseBody.add(generateBodyElement(else_statement.get(subKey),tabs+1,contextName,contextSubName));
					}
				}
			}
		}
		ListEntry ret = new ListEntry();
		ret.setDelimiter("");
		ifPart.add(ifBody);
		ret.add(new TabEntry(tabs,new ElementEntry(Generators.generator,"ifStatementCall",ifPart)));
		ret.add(new TabEntry(tabs,new StringEntry("}")));
		if(elseBody!=null){
			ret.add(new TabEntry(tabs,new ElementEntry(Generators.generator,"elseStatementCall",new ListEntry(elseBody))));
			ret.add(new TabEntry(tabs,new StringEntry("}")));
		}
		return ret;
	}

	/*
	 * whitetab{tabs} FLIP variable_or_token_name 
	 * 		( whitetab{tabs+1} (QUOTE|NON_SPACE) as left EQUALSIGN (QUOTE|NON_SPACE) as right )* 
	 * 		( else_statement{tabs+1} )?
	 */
	public Entry generateFlipSwitch(IToken flip, int tabs, String contextName, String contextSubName){

		String varName = flip.get("variable_names").getString();
		checks.add(new ContextCheck(contextName,contextSubName,varName,"(002) Variable name"));
		Entry left = null;
		boolean first = true;
		ListEntry ret = new ListEntry();
		ret.setDelimiter("");
		for(IToken.Key key:flip.keySet()){
			if("left".equals(key.getName())){
				left = new ElementEntry(Generators.generator,"equalsCall", 
						new ListEntry(new StringEntry(varName),new QuoteEntry(flip.get(key).getString())));
			}
			else if("right".equals(key.getName())){
				Entry right = new ElementEntry(Generators.generator,"setCall", 
						new ListEntry(new StringEntry(varName),new QuoteEntry(flip.get(key).getString())));
				Entry ifPart = new ElementEntry(Generators.generator,first?"ifStatementCall":"elseIfStatementCall",new ListEntry(
						left, new TabEntry(tabs+1,right)));
				ListEntry statement = new ListEntry();
				statement.setDelimiter("");
				statement.add(new TabEntry(tabs,ifPart));
				statement.add(new TabEntry(tabs,new StringEntry("}")));
				ret.add(statement);
				first = false;
			}
			else if("else_statement".equals(key.getName())){
				IToken elseStatement = flip.get(key);
				ListEntry body = new ListEntry();
				body.setDelimiter("");
				for(IToken.Key subKey:elseStatement.keySet()){
					if("body_element".equals(subKey.getName())){
						body.add(generateBodyElement(elseStatement.get(subKey),tabs+1,contextName,contextSubName));
					}
				}
				ListEntry statement = new ListEntry();
				statement.setDelimiter("");
				statement.add(new TabEntry(tabs,new ElementEntry(Generators.generator,"elseStatementCall",new ListEntry(body))));
				statement.add(new TabEntry(tabs,new StringEntry("}")));
				ret.add(statement);
			}
		}

		return ret;
	}

	public Entry generateSetCall(IToken call, String contextName, String contextSubName){
		String varName = null;
		VariableEntry variable = null;
		MethodEntry assignment = null;
		String assignmentType = null;
		for(IToken.Key key:call.keySet()){
			if("subject".equals(key.getName())){
				varName = call.get("subject").getString();
				if(!contexts.get(contextName).get(contextSubName).containsKey(varName)){
					variable = new VariableEntry(varName,TYPE_UNKNOWN,null);
					variable.setDefined(false);
					contexts.get(contextName).get(contextSubName).get(key);
					checks.add(new DefinedCheck(variable, "(003) Variable name"));
				}
				else {
					variable = contexts.get(contextName).get(contextSubName).get(varName);
				}				
			}
			else if("boolean_statement".equals(key.getName())){
				assignment = (MethodEntry) generateBooleanStatement(call.get(key),contextName,contextSubName);
			}
			else if("method_call".equals(key.getName())){
				assignment = (MethodEntry) generateMethodCall(call.get(key),contextName,contextSubName);
				
			}
			else if("method_parameter".equals(key.getName())){
				assignment = (MethodEntry) generateMethodParameter(call.get(key),false,contextName,contextSubName);
			}
		}
		if(assignment.hasType()){
			variable.changeType(((MethodEntry)assignment).getType());
		}
		else if(variable.hasType()){
			assignment.changeType(variable.getType());
		}
		return new ElementEntry(Generators.generator,"setCall",new ListEntry(new StringEntry(varName),assignment));
	}
	/*
	 *
	whitetab{tabs} token_name ( NAME_WORD as getName | clause_type_tokens{tabs} | all_type_tokens{tabs} )

clause_type_tokens has tabs
	TO NAME as tokenName in token_names token_clause{tabs+1}+

token_clause has tabs
	whitetab{tabs} NAME as specificTokenName ( entry_declaration{tabs+1} | body_element{tabs+1} )+ as body

all_type_tokens has tabs
	(STAR|NAME) as specificTokenName TO NAME as tokenName in token_names ( entry_declaration{tabs+1} | body_element{tabs+1} )+ as body

	 */
	public Entry generateTokenExpansion(IToken expansion, int tabs, String contextName, String contextSubName){
		StringEntry tokenName = null;
		for(IToken.Key key:expansion.keySet()){
			if("token_names".equals(key.getName())){
				tokenName = new StringEntry(expansion.get(key).getString());
				checks.add(new ContextCheck(contextName,contextSubName,tokenName.getString(),"(006) Token name"));
			}
			else if("getName".equals(key.getName())){
				MethodEntry ret = new MethodEntry(tokenName,"getName",new ListEntry());
				ret.changeType("String");
				return ret;
			}
			else if("all_type_tokens".equals(key.getName())){
				IToken allTypeTokens = expansion.get(key);
				String specificNameString = allTypeTokens.get("specificTokenName").getString();
				StringEntry specificName = new StringEntry(camelize(specificNameString));
				StringEntry childName = new StringEntry(allTypeTokens.get("tokenName").getString());
				String contextTokenSubName = contextSubName +"."+ childName.getString();
				VariableEntry childToken = new VariableEntry(childName.getString(),"IToken");
				addContext(contextName,contextSubName,contextTokenSubName,childToken);
				if(!specificNameString.equals("*")){
					StringEntry allName = new StringEntry(childName.getString()+specificName.getString());
					VariableEntry alls = new VariableEntry(allName.getString(),"List<IToken>",new MethodEntry(tokenName,"getAll",new ListEntry(new QuoteEntry(specificNameString))));
					ListEntry ret = new ListEntry();
					ret.add(new TabEntry(tabs,alls));
					ListEntry ifBody = new ListEntry();
					ifBody.setDelimiter("");
					ListEntry forBody = new ListEntry();
					forBody.setDelimiter("");
					IToken bodyToken = allTypeTokens.get("body");
					for(IToken.Key bodyKey:bodyToken.keySet()){
						if("entry_declaration".equals(bodyKey.getName())){
							forBody.add(generateEntryDeclaration(bodyToken.get(bodyKey),tabs+2,contextName,contextTokenSubName));
						}
						else if("body_element".equals(bodyKey.getName())){
							forBody.add(generateBodyElement(bodyToken.get(bodyKey),tabs+2,contextName,contextTokenSubName));
						}						
					}
					ifBody.add(new TabEntry(tabs+1,new ElementEntry(Generators.generator,"forStatementCall",new ListEntry(
							new ElementEntry(Generators.generator,"variablePrototype",new ListEntry(new StringEntry("IToken"),childName)),allName,forBody))));
					ifBody.add(new TabEntry(tabs+1,new StringEntry("}")));

					ret.add(
							new TabEntry(tabs,new ElementEntry(Generators.generator,"ifStatementCall",new ListEntry(
									new ElementEntry(Generators.generator,"operatorCall",new ListEntry(allName,new StringEntry("!="),new StringEntry("null"))),
									ifBody))));
					ret.add(new TabEntry(tabs,new StringEntry("}")));
					ret.setDelimiter("");
					return ret;
				}
				else {
					ListEntry ret = new ListEntry();
					ListEntry forBody = new ListEntry();
					forBody.setDelimiter("");
					StringEntry childKeyName = new StringEntry(childName.getString()+"Key");
					forBody.add(new TabEntry(tabs+1,new VariableEntry(childName.getString(),"IToken",new MethodEntry(tokenName,"get",new ListEntry(childKeyName)))));
					IToken bodyToken = allTypeTokens.get("body");					
					for(IToken.Key bodyKey:bodyToken.keySet()){
						if("entry_declaration".equals(bodyKey.getName())){
							forBody.add(generateEntryDeclaration(bodyToken.get(bodyKey),tabs+1,contextName,contextTokenSubName));
						}
						else if("body_element".equals(bodyKey.getName())){
							forBody.add(generateBodyElement(bodyToken.get(bodyKey),tabs+1,contextName,contextTokenSubName));
						}						
					}
					ret.add(new TabEntry(tabs,new ElementEntry(Generators.generator,"tokenForStatementCall",new ListEntry(childKeyName,tokenName,forBody))));
					ret.add(new TabEntry(tabs,new StringEntry("}")));
					ret.setDelimiter("");
					return ret;
				}
			}
			else if("clause_type_tokens".equals(key.getName())){
				ListEntry forBody = new ListEntry();
				forBody.setDelimiter("");
				StringEntry childName = null;
				StringEntry childKeyName = null;
				Entry childKeyNameGetName = null;
				VariableEntry childToken = null;
				IToken clause = expansion.get(key);
				boolean first = true;
				ListEntry elseBody = null;
				String contextTokenSubName = null;
				for(IToken.Key subKey:clause.keySet()){
					if("tokenName".equals(subKey.getName())){
						childName = new StringEntry(clause.get(subKey).getString());
						childKeyName = new StringEntry(childName.getString()+"Key");
						childKeyNameGetName = new MethodEntry(childKeyName,"getName",new ListEntry());
						contextTokenSubName = contextSubName+"."+childName.getString();

						childToken = new VariableEntry(childName.getString(),"IToken",
								new MethodEntry(tokenName,"get",new ListEntry(childKeyName)));
						addContext(contextName,contextSubName,contextTokenSubName,childToken);
					}
					else if("token_clause".equals(subKey.getName())){						
						String clauseNameString = clause.get(subKey).get("specificTokenName").getString();
						Entry clauseName = null;
						boolean isElse = false;
						if(!"else".equals(clauseNameString)){
							clauseName = new QuoteEntry(clauseNameString);
						}
						else {
							elseBody = new ListEntry();
							elseBody.setDelimiter("");
							isElse = true;
						}
						ListEntry clauseBody = new ListEntry();
						clauseBody.setDelimiter("");
						IToken bodyToken = clause.get(subKey).get("body");
						if(isElse&&first){
							clauseBody.add(new TabEntry(tabs+1,childToken));
							for(IToken.Key bodyKey:bodyToken.keySet()){
								if("entry_declaration".equals(bodyKey.getName())){
									clauseBody.add(generateEntryDeclaration(bodyToken.get(bodyKey),tabs+1,contextName,contextTokenSubName));
								}
								else if("body_element".equals(bodyKey.getName())){
									clauseBody.add(generateBodyElement(bodyToken.get(bodyKey),tabs+1,contextName,contextTokenSubName));
								}
							}
							elseBody = clauseBody;
						}
						else {
							clauseBody.add(new TabEntry(tabs+2,childToken));
							for(IToken.Key bodyKey:bodyToken.keySet()){
								if("entry_declaration".equals(bodyKey.getName())){
									clauseBody.add(generateEntryDeclaration(bodyToken.get(bodyKey),tabs+2,contextName,contextTokenSubName));
								}
								else if("body_element".equals(bodyKey.getName())){
									clauseBody.add(generateBodyElement(bodyToken.get(bodyKey),tabs+2,contextName,contextTokenSubName));
								}
							}
							if(isElse){
								elseBody = clauseBody;
							}
							else {
								Entry ifPart = new TabEntry(tabs+1,new ElementEntry(Generators.generator,
										first?"ifStatementCall":"elseIfStatementCall",new ListEntry(
												new MethodEntry(clauseName,"equals",new ListEntry(childKeyNameGetName)),
												clauseBody)));

								ListEntry clauseComplete = new ListEntry(ifPart,new TabEntry(tabs+1,new StringEntry("}")));
								clauseComplete.setDelimiter("");
								forBody.add(clauseComplete);
							}
						}
						first = false;
					}
				}
				if(elseBody!=null){
					if(forBody.isEmpty()){						
						forBody.add(elseBody);
					}
					else {
						forBody.add(new TabEntry(tabs+1,new ElementEntry(Generators.generator,
								"elseStatementCall",new ListEntry(elseBody))));
						forBody.add(new TabEntry(tabs+1,new StringEntry("}")));
					}
				}
				Entry forPart = new ElementEntry(Generators.generator,"tokenForStatementCall",new ListEntry(childKeyName,tokenName,forBody));
				ListEntry forComplete = new ListEntry(
						new TabEntry(tabs,forPart),
						new TabEntry(tabs,new StringEntry("}")));
				forComplete.setDelimiter("");
				return forComplete;
			}
		}
		throw new UnableToGenerateException("Token Expansion (006)",expansion);
	}
	//TODO

	public Entry generateVariableDeclaration(IToken varElement, String contextName, String contextSubName){
		String varName = null;
		String typeName = TYPE_UNKNOWN;
		Entry assignment = new StringEntry("null");
		boolean cast = false;
		for(IToken.Key key:varElement.keySet()){
			if("variableName".equals(key.getName())){
				varName = varElement.get(key).getString();
			}
			else if("tokenName".equals(key.getName())){
				varName = varElement.get(key).getString();
			}
			else if("castToType".equals(key.getName())){				
				typeName = getCastType(varElement,contextName,contextSubName);
				cast = true;
			}
			else if("method_call".equals(key.getName())){
				MethodEntry method = (MethodEntry)generateMethodCall(varElement.get(key),contextName, contextSubName);
				assignment = method;
				if(method.hasType()){
					typeName = method.getType();
				}
				else {
					method.changeType(typeName);
				}
			}
			else if("method_parameter".equals(key.getName())){
				MethodEntry parameter = (MethodEntry)generateMethodParameter(varElement.get(key),false,contextName, contextSubName);
				assignment = parameter;
				if(parameter.hasType()){
					typeName = parameter.getType();
				}
				else {
					parameter.changeType(typeName);
				}
			}
			else if("boolean_statement".equals(key.getName())){
				MethodEntry bool = (MethodEntry)generateBooleanStatement(varElement.get(key),contextName, contextSubName);
				assignment = bool;
				typeName = "Boolean";
			}
		}
		if(contexts.get(contextName).get(contextSubName).containsKey(varName)){
			VariableEntry var = contexts.get(contextName).get(contextSubName).get(varName);
			var.changeType(typeName);
			if(cast){
				var.setAssignment(new ElementEntry(Generators.generator,"castCall",new ListEntry(new StringEntry(typeName),assignment)));
			}
			else {
				var.setAssignment(assignment);
			}

			return var;
		}
		else {
			if(cast){
				assignment = new ElementEntry(Generators.generator,"castCall",new ListEntry(new StringEntry(typeName),assignment));
			}
			VariableEntry var = new VariableEntry(varName,typeName,assignment);
			contexts.get(contextName).get(contextSubName).put(varName,var);
			return var;
		}
	}

	/*
	 * 	whitetab{tabs} 
	 * 		( NEW | GENERATE | method_parameter ) as subject 
	 * 		NAME as methodName 
	 * 		( ANGLE_BRACES 
	 * 			| ( whitetab{tabs+1} boolean_statement 
	 * 				| method_call{tabs+1} 
	 * 				| whitetab{tabs+1} method_parameter)* )

	 */
	public Entry generateMethodCall(IToken method, final String contextName, String contextSubName){
		boolean isStatic = method.get("isStatic")!=null;
		IToken subject = method.get("subject");
		IToken subjectAsMethodParameter = subject.get("method_parameter");

		Entry subjectName = null;
		boolean isGenerate = false;
		if(subjectAsMethodParameter!=null){
			subjectName = generateMethodParameter(subjectAsMethodParameter,isStatic,contextName,contextSubName);
		}
		else {
			String subName = method.get("subject").getString();
			subjectName = new StringEntry(subName);
			if("generate".equals(subName)){
				isGenerate = true;
			}
			else if("new ".equals(subName)){
				subjectName = METHOD_NEW;
			}
		}
		String methodName = method.get("methodName").getString();
		if(subjectName.equals(METHOD_NEW)){
			methodName = camelize(methodName);
		}
		else if(!isGenerate&&methodName.equals("single")){
			methodName = "getSingle";
		}
		final String trueMethod;
		final boolean undefined;
		if(isGenerate){
			trueMethod = "generate"+camelize(methodName);
			if(!methodParameters.get(contextName).containsKey(trueMethod)){
				methodParameters.get(contextName).put(trueMethod, new ArrayList<ITypeListener>());
				undefined = true;
			}
			else undefined = false;
		}
		else {
			trueMethod = methodName;
			methodParameters.get(contextName).put(trueMethod, new ArrayList<ITypeListener>());
			undefined = true;
		}
		ListEntry parameters = new ListEntry(){
			@Override
			public boolean add(Entry entry){
				if(undefined){
					methodParameters.get(contextName).get(trueMethod).add((ITypeListener) entry);
				}
				else {
					if(!methodParameters.get(contextName).get(trueMethod).get(size()).hasType()){
						methodParameters.get(contextName).get(trueMethod).get(size()).changeType(((ITypeListener) entry).getType());
					}
				}
				return super.add(entry);
			}
		};
		MethodEntry ret = null;
		if(isGenerate){
			VariableEntry defaultToken = contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN);
			MethodEntry tokenParam = new MethodEntry(null,defaultToken.getName(),null);
			tokenParam.changeType("IToken");
			parameters.add(tokenParam);
			ret = new MethodEntry("noSubjectCall",new ListEntry(new StringEntry(trueMethod),parameters));
			ret.changeType("Entry");
		}
		else if(isStatic){
			ret = new MethodEntry("staticGetVariableCall",new ListEntry(subjectName,new StringEntry(methodName),parameters));
		}
		else {
			ret = new MethodEntry(subjectName,methodName,parameters);
			if(subjectName.equals(METHOD_NEW)){
				ret.changeType(methodName);
			}
			else if("getSingle".equals(methodName)){
				ret.changeType("Entry");
			}
		}

		for(IToken.Key key:method.keySet()){
			if("angle_braces".equals(key.getName())){
				generateAngleBraces(method.get(key),parameters,contextName,contextSubName);
				if(!isGenerate){
					ret.setElementName(Generators.generator,"newMethodWithBracesCall");
					StringBuilder typeBuilder = new StringBuilder();
					typeBuilder.append(ret.getType());
					typeBuilder.append("<");
					parameters.get(typeBuilder);
					typeBuilder.append(">");					
					ret.changeType(typeBuilder.toString());
				}
			}
			else if("boolean_statement".equals(key.getName())){
				parameters.add(generateBooleanStatement(method.get(key),contextName,contextSubName));
			}
			else if("method_parameter".equals(key.getName())){
				Entry param = generateMethodParameter(method.get(key),false,contextName,contextSubName);
				parameters.add(param);
			}
			else if("method_call".equals(key.getName())){
				parameters.add(generateMethodCall(method.get(key),contextName,contextSubName));
			}
		}
		if(isStatic&&!parameters.isEmpty()){
			ret.setElementName(Generators.generator,"staticMethodCall");
		}
		return ret;
	}
	/*
	 * (entry_definition{-1}|arithmatic|class_name ANGLE_BRACES?|entry_name)
	 */
	public Entry generateAngleBraces(IToken angle_braces, ListEntry parameters, String contextName, String contextSubName){
		for(IToken parameter:angle_braces.getAll("parameter")){
			MethodEntry param = null;
			for(IToken.Key key:parameter.keySet()){
				if("arithmatic".equals(key.getName())){
					param = (MethodEntry) generateArithmatic(parameter.get(key), false, contextName, contextSubName);
				}
				else if("entry_definition".equals(key.getName())){
					param = (MethodEntry) generateEntryDefinition(parameter.get(key),contextName,contextSubName);
				}
				else if("class_names".equals(key.getName())){
					String type = parameter.get(key).getString();
					param = new MethodEntry("exactCall",new ListEntry(new StringEntry(type)));
					param.changeType(type);
				}
				else if("property_names".equals(key.getName())){
					String type = "I"+camelize(parameter.get(key).getString());
					param = new MethodEntry("exactCall",new ListEntry(new StringEntry(type)));
					param.changeType(type);
				}
				else if("entry_names".equals(key.getName())){
					param = new MethodEntry(null,parameter.get(key).getString(),null);
					param.changeType("Entry");
				}
				else if("braces".equals(key.getName())){
					ListEntry braces = new ListEntry();
					generateAngleBraces(parameter.get(key),braces,contextName,contextSubName);
					param.getEntries().add(braces);
					param.setElementName(Generators.generator,"exactWithAngleCall");
				}
			}
			parameters.add(param);
		}
		return null;
	}

	/**
	 * | TRUE | FALSE
	entry_definition
	variable_or_token_name
	class_name
	entry_name
	QUOTE as string
	NUMBER
	 * @param parameter
	 * @return
	 */
	public Entry generateMethodParameter(IToken parameter, boolean isStatic, String contextName, String contextSubName){
		for(IToken.Key key:parameter.keySet()){
			if("NULL".equals(key.getName())){
				MethodEntry entry = new MethodEntry(null,parameter.getString(),null);
				entry.setIsNull(true);
				return entry;
			}
			else if("TRUE".equals(key.getName())||"FALSE".equals(key.getName())){
				String value = parameter.getString();
				MethodEntry entry = new MethodEntry(null,value,null);
				entry.changeType("Boolean");
				return entry;
			}
			else if("NUMBER".equals(key.getName())){
				String value = parameter.getString();
				MethodEntry entry = new MethodEntry(null,value,null);
				entry.changeType("Integer");
				return entry;
			}
			else if("getKeyName".equals(key.getName())){
				MethodEntry entry = new MethodEntry(new StringEntry(
						contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN).getName()+"Key"),"getName",new ListEntry());
				entry.changeType("String");
				return entry;
			}
			else if("entry_definition".equals(key.getName())){
				return generateEntryDefinition(parameter.get(key),contextName,contextSubName);
			}
			else if("entry_names".equals(key.getName())){
				MethodEntry entry = new MethodEntry(null,parameter.getString(),null);
				entry.changeType("");
				return entry;
			}
			else if("class_names".equals(key.getName())){
				MethodEntry entry = null;
				String type = camelize(parameter.getString());
				if(isStatic){
					entry = new MethodEntry(null,type,null);
				}
				else {
					entry = new MethodEntry(METHOD_NEW,type,new ListEntry());
				}
				entry.changeType(type);
				return entry;
			}
			else if("generator_names".equals(key.getName())){
				String name = parameter.getString();
				MethodEntry entry = new MethodEntry("generatorCall",new ListEntry(new StringEntry(name)));
				entry.changeType(camelize(name)+"Generator");
				return entry;
			}
			else if("property_names".equals(key.getName())){
				String name = "I"+camelize(parameter.getString());
				MethodEntry entry = new MethodEntry(null,name,null);
				entry.changeType(name);
				return entry;
			}
			else if("entry_class_names".equals(key.getName())){
				String name = camelize(parameter.getString());
				MethodEntry entry = new MethodEntry(null,name,null);
				entry.changeType(name);
				return entry;
			}
			else if("string".equals(key.getName())){
				MethodEntry entry = new MethodEntry(null,"\""+parameter.getString()+"\"",null);
				entry.changeType("String");
				return entry;
			}
			else if("variable_or_token_name".equals(key.getName())){
				return generateVariableOrTokenName(parameter.get(key),contextName, contextSubName);
			}
		}
		throw new UnableToGenerateException("Method Parameter (002)",parameter);
	}

	public Entry generateVariableOrTokenName(IToken votName, String contextName, String contextSubName){
		/*
		 * 
	( PRIME as getString )? ( token_name ( ACCESS NAME as option )+ | arithmatic )
	( PRIME as getString )? token_name
		 */
		boolean isGetString = votName.get("getString") != null;
		MethodEntry ret = null;
		for(IToken.Key key:votName.keySet()){
			if("token_names".equals(key.getName())){

				ret = new MethodEntry(null,votName.get(key).getString(),null);
				ret.changeType("IToken");
			}
			else if("option".equals(key.getName())){
				ret = new MethodEntry(ret,"get",new ListEntry(new QuoteEntry(votName.get(key).getString())));
				ret.changeType("IToken");
			}
			else if("arithmatic".equals(key.getName())){
				ret = (MethodEntry)generateArithmatic(votName.get(key),false, contextName, contextSubName);


			}
		}

		if(isGetString){
			if(ret.getType().equals("IToken")){
				ret = new MethodEntry(ret,"getString",new ListEntry());
			}
			else {
				ret = new MethodEntry(ret,"toString",new ListEntry());
			}
			ret.changeType("String");
		}
		return ret;
	}

	public Entry generateArithmatic(IToken arithmatic, boolean mustBeNumber, String contextName, String contextSubName){
		MethodEntry ret = null;
		for(IToken.Key key:arithmatic.keySet()){
			if("arithmatic".equals(key.getName())){
				MethodEntry next = (MethodEntry)generateArithmatic(arithmatic.get(key),mustBeNumber, contextName, contextSubName);
				if(ret==null){
					ret = next;					
				}
				else {
					ret.getEntries().add(next);
				}
				if(next.hasType()){
					ret.changeType(next.getType());
				}
			}
			else if("operand".equals(key.getName())){
				if(ret!=null){
					ret.changeType("Integer");
				}
				ret = new MethodEntry(
						"operatorCall",
						new ListEntry(ret,new StringEntry(arithmatic.get(key).getString())));
				mustBeNumber = true;
			}
			else if("variable_names".equals(key.getName())){
				String variableName = arithmatic.get(key).getString();
				VariableEntry var = contexts.get(contextName).get(contextSubName).get(variableName);
				if(var==null){
					var = contexts.get(contextName).get(LOCAL_CONTEXT).get(variableName);
				}
				if(var==null){
					var = new VariableEntry(variableName,TYPE_UNKNOWN,null);
					var.setDefined(false);
					contexts.get(contextName).get(contextSubName).put(variableName, var);
					checks.add(new DefinedCheck(var,"(004) Variable name"));
				}
				ret = new MethodEntry(null,var.getName(),null);

				if(var.hasType()){
					ret.changeType(var.getType());
				}
				else {
					ret.addTypeListener(var);
				}
				if(var.getType().equals("Integer")){
					mustBeNumber = true;
				}
			}
			else if("NUMBER".equals(key.getName())){
				String number = arithmatic.get(key).getString();
				ret = new MethodEntry(null,number,null);
				mustBeNumber = true;
			}

		}
		if(mustBeNumber){
			ret.changeType("Integer");
			return ret;
		}
		else {
			return ret;
		}
	}
	/*
	 * boolean_clause ( ( ( AND | OR ) as continuationOperator boolean_clause ) as continuationStatement )*
	 */
	public Entry generateBooleanStatement(IToken boolean_statement, String contextName, String contextSubName){
		MethodEntry bool = null;
		for(IToken.Key key:boolean_statement.keySet()){
			if("boolean_clause".equals(key.getName())){
				bool = (MethodEntry) generateBooleanClause(boolean_statement.get(key),contextName,contextSubName);
			}
			else if("continuationStatement".equals(key.getName())){
				if(boolean_statement.get(key).get("continuationOperator").getString().equals("and ")){
					bool = new MethodEntry("operatorCall",new ListEntry(bool,new StringEntry("&&")));
				}
				else {
					bool = new MethodEntry("operatorCall",new ListEntry(bool,new StringEntry("||")));
				}
				bool.getEntries().add(generateBooleanClause(boolean_statement.get(key).get("boolean_clause"),contextName,contextSubName));
			}
		}
		bool = new MethodEntry("newBooleanCall",new ListEntry(bool));
		bool.changeType("Boolean");
		return bool;
	}
	/*
	 * 
	( NOT method_parameter ) as notStatement
	( method_parameter ( IS NOT? | ORDINAL_OPERATOR ) as operator (method_parameter | EMPTY | SINGULAR) ) as operatedStatement
	 */
	public Entry generateBooleanClause(IToken boolean_clause, String contextName, String contextSubName){
		IToken notStatement = boolean_clause.get("notStatement");
		if(notStatement!=null){
			MethodEntry operand = (MethodEntry) generateMethodParameter(notStatement.get("method_parameter"),false,contextName,contextSubName);
			operand.changeType("Boolean");
			return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),operand));
		}
		else {
			IToken containsToken = boolean_clause.get("containsStatement");
			if(containsToken!=null){
				MethodEntry operation = new MethodEntry(new StringEntry(containsToken.get("token_names").getString()),"containsKey",new ListEntry(new QuoteEntry(containsToken.get("argument").getString())));
				operation.changeType("Boolean");
				return operation;
			}
			else {
				IToken statement = boolean_clause.get("operatedStatement");
				int operatorState = -1;
				String previousDefaultType = null;
				MethodEntry left = (MethodEntry) generateMethodParameter(statement.get("left").get("method_parameter"),false,contextName,contextSubName);
				IToken operator = statement.get("operator");
				//left.setDefaultType("Boolean");
				if(operator.get("IS")!=null){
					if(operator.get("NOT")!=null){						
						operatorState = 1;
					}
					else {
						operatorState = 0;
					}
				}
				else {
					operatorState = 2;
				}
				IToken rightToken = statement.get("right");
				MethodEntry right = null;
				boolean isPrimitive = false;
				for(IToken.Key rightKey:rightToken.keySet()){
					if("method_parameter".equals(rightKey.getName())){
						right = (MethodEntry) generateMethodParameter(rightToken.get(rightKey),false,contextName,contextSubName);
						isPrimitive = left.getType().equals("Integer")||left.getType().equals("Boolean")||
								right.getType().equals("Integer")||right.getType().equals("Boolean")||
								left.isNull()||right.isNull();
						if(operatorState==2){
							left.changeType("Integer");
							right.changeType("Integer");
							return new MethodEntry("operatorCall",new ListEntry(left,new StringEntry(operator.getString()),right));
						}
						if(!left.hasType()&&right.hasType()){
							left.changeType(right.getType());
						}
						else if(left.hasType()&&!right.hasType()){
							right.changeType(left.getType());
						}
						if(isPrimitive){							
							if(operatorState==0){
								return new MethodEntry("operatorCall",new ListEntry(left,new StringEntry("=="),right));
							}
							else if(operatorState==1){
								return new MethodEntry("operatorCall",new ListEntry(left,new StringEntry("!="),right));
							}
						}
						else {
							if(operatorState==0){
								return new MethodEntry("equalsCall",new ListEntry(left,right));
							}
							else if(operatorState==1){
								return new MethodEntry("notEqualsCall",new ListEntry(left,right));
							}
						}
					}
					else if("SINGULAR".equals(rightKey.getName())){
						if(operatorState == 0){
							return new MethodEntry(left,"isSingular",new ListEntry());
						}
						else if(operatorState==1){
							return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),
									new MethodEntry(left,"isSingular",new ListEntry())));
						}
					}
					else if("EMPTY".equals(rightKey.getName())){
						if(operatorState==0){
							return new MethodEntry(left,"isEmpty",new ListEntry());
						}
						else if(operatorState==1){
							return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),
									new MethodEntry(left,"isEmpty",new ListEntry())));
						}
					} 
				}

				/*
				for(IToken.Key key:statement.keySet()){
					if("method_parameter".equals(key.getName())){
						MethodEntry operand = (MethodEntry) generateMethodParameter(statement.get(key),false,contextName,contextSubName);
						ret.getEntries().add(operand);
						if(operand.isNull()&&previousDefaultType!=null){
							((MethodEntry)ret.getEntries().get(0)).setDefaultType(previousDefaultType);
						}
						else if(!operand.hasType()){
							previousDefaultType = operand.defaultType;
							if(operatorState==2){
								operand.changeType("Integer");
							}
							else {
								if(ret.getEntries().size()>0){
									if(((MethodEntry)ret.getEntries().get(0)).hasType()){
										operand.setDefaultType(((MethodEntry)ret.getEntries().get(0)).getType());
									}
									else {
										operand.setDefaultType(((MethodEntry)ret.getEntries().get(0)).defaultType);
									}
								}
								else {
									operand.setDefaultType("Boolean");									
								}
							}
						}
					}
				}*/
			}
		}
		throw new RuntimeException("(009)"+tokenErrorMessage(boolean_clause));
	}

	/*
	 * ( element_name list_entry_definition | list_entry_definition | TAB_BRACES )
	 */

	public Entry generateEntryDefinition(IToken definition,String contextName, String contextSubName){
		IToken getSingle = definition.get("getSingle");
		if(getSingle!=null){
			String entryName = getSingle.getString();
			if(!contexts.get(contextName).get(contextSubName).containsKey(entryName)){
				VariableEntry newEntry = new VariableEntry(entryName,"Entry");
				newEntry.setDefined(false);
				contexts.get(contextName).get(contextSubName).put(entryName, newEntry);
			}
			if(!contexts.get(contextName).get(contextSubName).get(entryName).getType().contains("Entry")){
				contexts.get(contextName).get(contextSubName).get(entryName).changeType("Entry");
			}
			MethodEntry entry = new MethodEntry(new StringEntry(entryName),"getSingle",new ListEntry());
			entry.changeType("Entry");
			return entry;
		}
		IToken quoteEntry = definition.get("quoted");
		if(quoteEntry!=null){
			MethodEntry ret = null;
			for(IToken.Key subKey:quoteEntry.keySet()){
				if("entry".equals(subKey.getName())){
					ret = new MethodEntry(METHOD_NEW,"QuoteEntry",
							new ListEntry(new QuoteEntry(quoteEntry.get(subKey).getString())));
				}
				else if("variable_or_token_name".equals(subKey.getName())){
					ret = new MethodEntry(METHOD_NEW,"QuoteEntry",
									new ListEntry(generateVariableOrTokenName(quoteEntry.get(subKey), contextName, contextSubName)));
				}
			}
			ret.changeType("QuoteEntry");
			return ret;
		}
		
		IToken elementNameToken = definition.get("element_names");
		boolean isVariableName = false;
		if(elementNameToken == null){
			elementNameToken = definition.get("variable_names");
			isVariableName = true;
			
		}
		IToken listEntryToken = definition.get("list_entry_definition");
		Entry listEntry = null;
		MethodEntry ret = null;
		if(listEntryToken!=null){
			listEntry = generateListEntryDefinition(listEntryToken,contextName,contextSubName);
		}
		if(elementNameToken==null){
			if(listEntryToken==null){
				
				IToken tabBrace = definition.get("tab_braces");
				IToken arithmatic = tabBrace.get("arithmatic");
				IToken entryDefinition = tabBrace.get("entry_definition");
				if(entryDefinition!=null){					
					ret = new MethodEntry(METHOD_NEW,"TabEntry",new ListEntry(
							generateArithmatic(arithmatic,false,contextName,contextSubName),
							new MethodEntry(METHOD_NEW,"ListEntry",new ListEntry(generateEntryDefinition(entryDefinition,contextName,contextSubName)))
							));
				}
				else {
					ret = new MethodEntry(METHOD_NEW,"TabEntry",new ListEntry(
							generateArithmatic(arithmatic,false,contextName,contextSubName),
							new MethodEntry(
									METHOD_NEW,
									"ListEntry",
									new ListEntry(
											new StringEntry(
													tabBrace.get("entry_names").getString())))
							));
				}
			}
			else {
				return listEntry;
			}
		}
		else {
			IToken specificGenerator = definition.get("generator_names");
			Entry specificGeneratorName = null;
			
			if(isVariableName){
				if(specificGenerator!=null){
					specificGeneratorName = new StringEntry(specificGenerator.getString());
				}
				else {
					specificGeneratorName = new StringEntry(generatorName);
				}
				ret =  new MethodEntry(METHOD_NEW,"ElementEntry",new ListEntry(
						new ElementEntry(Generators.generator,"generatorElementWithVariable",
								new ListEntry(specificGeneratorName,new StringEntry(elementNameToken.getString()))),
						new ListEntry(listEntry)));
			}
			else {
				if(specificGenerator!=null){
					specificGeneratorName = new StringEntry(camelize(specificGenerator.getString()));
				}
				else {
					specificGeneratorName = new StringEntry(contextName);
				}
				ret =  new MethodEntry(METHOD_NEW,"ElementEntry",new ListEntry(
						new ElementEntry(Generators.generator,"generatorElement",
								new ListEntry(specificGeneratorName,new StringEntry(elementNameToken.getString()))),
						new ListEntry(listEntry)));
			}
		}
		ret.changeType("Entry");
		return ret;
	}

	/*
	 * 
	[{,}] as ENTRY_LIST with (entry_definition{-1}| generate_call{-1} | whitetab{-1}? (entry_name|variable_name))? ( (entry_definition{-1} | generate_call{-1} | whitetab{-1}? (entry_name|variable_name)) )*
	[[,]] as ENTRY_STRING with (QUOTE | variable_or_token_name) ( (QUOTE | variable_or_token_name))*
	 */
	public Entry generateListEntryDefinition(IToken led, String contextName, String contextSubName){

		//System.out.println(tokenErrorMessage(led));
		for(IToken.Key key:led.keySet()){
			if("list".equals(key.getName())){
				IToken list = led.get(key);
				ListEntry entry = new ListEntry();
				for(IToken.Key subKey:list.keySet()){
					if("entry_definition".equals(subKey.getName())){
						entry.add(generateEntryDefinition(list.get(subKey),contextName,contextSubName));
					}
					else if("generate_call".equals(subKey.getName())){
						entry.add(generateMethodCall(list.get(subKey),contextName,contextSubName));
					}
					else if("variable_names".equals(subKey.getName())){
						String varName = list.get(subKey).getString();
						if(!contexts.get(contextName).get(contextSubName).containsKey(varName)){
							VariableEntry var = new VariableEntry(varName,"Entry");
							contexts.get(contextName).get(contextSubName).put(varName, var);
							var.setDefined(false);
							checks.add(new DefinedCheck(var,"List Entry Definition(006)"));
						}
						if(!contexts.get(contextName).get(contextSubName).get(varName).getType().contains("Entry")){
							contexts.get(contextName).get(contextSubName).get(varName).changeType("Entry");
						}
						entry.add(new MethodEntry(null,varName,null));
						checks.add(new ContextCheck(contextName,contextSubName,varName,"(005) Variable name"));						
					}
					else if("entry_names".equals(subKey.getName())){
						String entryName = list.get(subKey).getString();
						//System.out.println(entryName);
						if(!contexts.get(contextName).get(contextSubName).containsKey(entryName)){
							VariableEntry var = new VariableEntry(entryName,"Entry");
							contexts.get(contextName).get(contextSubName).put(entryName, var);
							var.setDefined(false);
							checks.add(new DefinedCheck(var,"List Entry Definition(007)"));
						}
						if(!contexts.get(contextName).get(contextSubName).get(entryName).getType().contains("Entry")){
							contexts.get(contextName).get(contextSubName).get(entryName).changeType("Entry");
						}
						entry.add(new MethodEntry(null,entryName,null));
					}
				}
				MethodEntry ret = new MethodEntry(METHOD_NEW,"ListEntry",entry);
				ret.changeType("ListEntry");
				return ret;
			}
			else if("string".equals(key.getName())){
				IToken string = led.get(key);
				ListEntry entry = new ListEntry();
				for(IToken.Key subKey:string.keySet()){
					if("entry".equals(subKey.getName())){
						entry.add(new MethodEntry(METHOD_NEW,"StringEntry",
								new ListEntry(new QuoteEntry(string.get(subKey).getString()))));
					}
					else if("quoted".equals(subKey.getName())){
						for(IToken.Key quotedKey:string.get(subKey).keySet()){
							if("entry".equals(subKey.getName())){
								entry.add(new MethodEntry(METHOD_NEW,"QuoteEntry",
										new ListEntry(new QuoteEntry(string.get(subKey).get(quotedKey).getString()))));
							}
							else if("variable_or_token_name".equals(subKey.getName())){
								entry.add(new MethodEntry(METHOD_NEW,"QuoteEntry",
												new ListEntry(generateVariableOrTokenName(string.get(subKey).get(quotedKey), contextName, contextSubName))));
							}
						}
					}
					else {
						entry.add(
								new MethodEntry(METHOD_NEW,"StringEntry",
										new ListEntry(generateVariableOrTokenName(string.get(subKey), contextName, contextSubName))));
					}
				}
				MethodEntry ret = new MethodEntry(METHOD_NEW,"ListEntry",entry);
				ret.changeType("ListEntry");
				return ret;
			}
			else if("exact_variable".equals(key.getName())){
				String variableName = led.get(key).get("variable_names").getString();
				VariableEntry var = null;
				if(!contexts.get(contextName).get(contextSubName).containsKey(variableName)){
					var = new VariableEntry(variableName,"ListEntry");
					contexts.get(contextName).get(contextSubName).put(variableName, var);
					var.setDefined(false);
					checks.add(new DefinedCheck(var,"ListEntryDefinition (020)"));
				}
				else {
					var = contexts.get(contextName).get(contextSubName).get(variableName);
					var.changeType("ListEntry");
				}
				MethodEntry ret = new MethodEntry(null,variableName,null);
				ret.changeType("ListEntry");
				return ret;
			}
		}
		throw new UnableToGenerateException("List Entry (003)",led);
	}

	//TODO

	public void addContext(String contextName, String contextSubName, String newContextName, VariableEntry newDefaultEntry){

		if(!contexts.containsKey(contextName)){
			contexts.put(contextName, new HashMap<String,Map<String,VariableEntry>>());
			methodParameters.put(contextName, new HashMap<String,List<ITypeListener>>());
		}
		Map<String,VariableEntry> context = new HashMap<String,VariableEntry>();
		contexts.get(contextName).put(newContextName, context);
		if(contextSubName!=null){
			for(String varName:contexts.get(contextName).get(contextSubName).keySet()){
				context.put(varName, contexts.get(contextName).get(contextSubName).get(varName));
			}
		}
		context.put(DEFAULT_TOKEN,newDefaultEntry);
		context.put(newDefaultEntry.getName(),newDefaultEntry);
		if(contextSubName!=null){
			VariableEntry previousDefault = contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN);
			context.put(previousDefault.getName(), previousDefault);
		}
	}

	@Override
	public IParser getLazyNameParser(){
		return Tokens.NAME;
	}

	public String getName(){
		return "gen";
	}

	@Override
	public void generate(ParseContext data) {
		NO_DEFAULT_TOKEN = new VariableEntry("$NO_DEFAULT","IToken",null);
		file = data.getFile();
		String fileName = data.getFileName();
		int indexOfDot = fileName.lastIndexOf('.');
		if(indexOfDot>-1)fileName = fileName.substring(0, indexOfDot);
		directory = new File("../Generated"+camelize(fileName)+"/src/gen/");
		directory.mkdirs();

		System.out.println(directory.getAbsolutePath());

		Generators.property.generate(data);
		Generators.entryClass.generate(data);
		generateAll(data.getList("class_definitions").getNewTokens(),"class_dec");

		addFile(directory,"Generators.java",new ListEntry(new StringEntry("Generators"),new StringEntry("Object")));
		ListEntry genNames = new ListEntry();
		genNames.setDelimiter("\n\tpublic static final ");
		genNames.startWithDelimiter(true);
		for(String className:generatorNames){
			StringEntry generatorName = new StringEntry(camelize(className));
			genNames.add(new ElementEntry(Generators.generator,"generatorDeclaration",new ListEntry(generatorName,new StringEntry(className),generatorName)));
		}
		addEntry(getName(),directory,"Generators.java","generators",new ListEntry(genNames));
		Generators.property.outputAll();
		Generators.entryClass.outputAll();
		outputAll();

	}

	@Override
	public void assignListElementNames(ParseContext context, IToken root){		
		for(IParser parser:Listnames.parser){
			String pattern = ((RegexParser)parser).getPattern();
			String listName = pattern.substring(0, pattern.length()-1);
			String singleName = listName.substring(0, listName.length()-1);
			if(context.getList(listName)==null){
				context.addList(ParseList.createNew(listName,singleName));
			}
		}
		for(IParser parser:ClassNames.parser){
			String className = ((RegexParser)parser).getPattern();
			context.getList("class_names").getNamesParser().addName(className);
		}
	}

	public String getCastType(IToken castToken, String contextName, String contextSubName){
		IToken castToType = castToken.get("castToType");
		if(castToType!=null){
			StringBuilder typeBuilder = new StringBuilder();
			for(IToken.Key subKey:castToType.keySet()){
				if("NAME".equals(subKey.getName())){
					typeBuilder.append(castToType.get(subKey).getString());
				}
				else if("angle_braces".equals(subKey.getName())){
					typeBuilder.append('<');
					ListEntry params = new ListEntry();
					generateAngleBraces(castToType.get(subKey),params,contextName,contextSubName);
					params.get(typeBuilder);
					typeBuilder.append('>');
				}					
			}
			return typeBuilder.toString();
		}
		return null;
	}

	public VariableEntry getParameter(IToken parameter, String contextName, String contextSubName, String methodName){

		String parameterName = parameter.get("takeName").getString();
		VariableEntry var = new VariableEntry(parameterName,TYPE_UNKNOWN);
		String castType = getCastType(parameter,contextName,contextSubName);
		if(castType!=null){
			var.changeType(castType);
		}
		contexts.get(contextName).get(methodName).put(parameterName,var);
		return var;
	}

	public File getDirectory() {
		return directory;
	}
	//TODO
	private static interface ITypeListener {
		public void changeType(String newType);
		public boolean hasType();
		public String getType();
		public String getDefaultType();
		public void setDefaultType(String string);
	}
	public class VariableEntry extends ElementEntry implements ITypeListener{
		private StringEntry typeEntry;
		private String defaultType = "String";
		private List<ITypeListener> typeListeners;
		private String name;
		private boolean defined = true;
		private boolean isFinal = false;
		private boolean isStatic = false;

		public VariableEntry(String name, String type, Entry assignment) {
			super(Generators.generator,
					assignment!=null?
							"variableDeclaration":"exactCall",
							new ListEntry());
			this.typeEntry = new StringEntry(type);
			this.name = name;
			if(assignment!=null){
				this.parameters.add(typeEntry);
			}
			this.parameters.add(new StringEntry(name));
			if(assignment!=null){
				this.parameters.add(assignment);
			}
		}

		public VariableEntry(String name, String type) {
			super(Generators.generator,"exactDuoCall", new ListEntry());
			this.typeEntry = new StringEntry(type);
			this.name = name;
			this.parameters.add(typeEntry);
			this.parameters.add(new StringEntry(" "+name));
		}

		public void setFinal(boolean b) {
			this.isFinal = b;
		}

		public void setStatic(boolean b) {
			this.isStatic = b;
		}

		public void addListener(ITypeListener listener) {
			if(this.typeListeners==null){
				this.typeListeners = new ArrayList<ITypeListener>();
			}
			this.typeListeners.add(listener);
		}

		public void setDefaultType(String newType){
			this.defaultType = newType;
			if(this.typeListeners!=null){
				for(ITypeListener listener:this.typeListeners){
					listener.setDefaultType(newType);
				}
			}
		}

		public void setAssignment(Entry assignment) {
			this.setElementName(Generators.generator,"variableDeclaration");
			this.parameters.add(assignment);
		}

		public void setDefined(boolean b) {
			defined = b;
		}


		public String getType() {
			return this.typeEntry.getString();
		}
		public String getDefaultType(){
			return this.defaultType;
		}

		public String getName() {
			return name;
		}

		@Override
		public void get(StringBuilder builder){
			if(typeEntry.getString().equals(TYPE_UNKNOWN)){
				typeEntry.set(defaultType);
				if(this.typeListeners!=null){
					for(ITypeListener listener:this.typeListeners){
						listener.changeType(defaultType);
					}
				}
			}

			if(isFinal){
				new StringEntry("final ").get(builder);
			}
			if(isStatic){
				new StringEntry("static ").get(builder);
			}
			super.get(builder);
		}

		public void changeType(String newType){
			if(!this.hasType()){
			this.typeEntry.set(newType);
			if(this.typeListeners!=null){
				for(ITypeListener listener:this.typeListeners){
					listener.changeType(newType);
				}
			}
			}
		}

		@Override
		public boolean equals(Object obj){
			if(obj instanceof String){
				return obj.equals(name);
			}
			else return super.equals(obj);
		}

		public boolean isDefined() {
			return defined;
		}

		public boolean hasType(){
			return !this.typeEntry.getString().equals(TYPE_UNKNOWN);
		}
	}

	public class TypeEntry implements Entry {

		private ITypeListener subject = null;		
		public TypeEntry(ITypeListener subject) {
			this.subject = subject;
		}

		public void get(StringBuilder builder){
			if(subject.hasType()){
				builder.append(subject.getType());
			}
			else {
				builder.append(subject.getDefaultType());
			}
		}

	}

	public class MethodEntry extends ElementEntry implements ITypeListener{
		private StringEntry typeEntry;
		protected StringEntry methodName;
		private List<ITypeListener> typeListeners = new ArrayList<ITypeListener>();
		private String defaultType = "String";
		private boolean isNull = false;

		public MethodEntry(String element, ListEntry parameters) {
			super(Generators.generator,element,parameters);
			this.typeEntry = new StringEntry(TYPE_UNKNOWN);
		}


		public MethodEntry(Entry subject, String methodName, ListEntry parameters) {
			super(Generators.generator,
					subject==null?"exactCall":
						METHOD_NEW.equals(subject)?"newObjectCall":"methodCall", new ListEntry());
			this.typeEntry = new StringEntry(TYPE_UNKNOWN);
			if(subject!=null&&!METHOD_NEW.equals(subject)){
				this.parameters.add(subject);
			}
			//this.parameters.add(typeEntry);
			this.methodName = new StringEntry(methodName);
			this.parameters.add(methodName);
			if(subject!=null){
				this.parameters.add(parameters);
			}
		}

		public boolean isNull(){
			return isNull;
		}
		public void setIsNull(boolean b) {
			this.isNull  = b;
		}
		public void setDefaultType(String type) {
			this.defaultType = type;
			for(ITypeListener listener:this.typeListeners){
				listener.setDefaultType(type);
			}
		}
		public String getDefaultType(){
			return this.defaultType;
		}
		public void addTypeListener(ITypeListener listener) {
			this.typeListeners.add(listener);
		}

		public ListEntry getParameters() {
			return this.parameters;
		}

		@Override
		public void get(StringBuilder builder){
			if(typeEntry.getString().equals(TYPE_UNKNOWN)){
				typeEntry.set(defaultType);
			}
			super.get(builder);
		}

		public void changeType(String newType){
			if(!hasType()||newType.startsWith(typeEntry.getString())){
				this.typeEntry.set(newType);
				for(ITypeListener listener:typeListeners){
					listener.changeType(newType);
				}
			}
			else {
				//throw new RuntimeException((methodName.getString()+" tried to change already set type from "+this.typeEntry.getString()+" to "+newType));
				System.err.println(methodName==null?"null":methodName.getString()+" tried to change already set type from "+this.typeEntry.getString()+" to "+newType);
			}
		}

		public boolean hasType(){
			return !this.typeEntry.getString().equals(TYPE_UNKNOWN);
		}

		public String getType(){
			return this.typeEntry.getString();
		}

		public String getMethodName(){
			return methodName.getString();
		}
		public void setMethodName(String methodName){
			this.methodName = new StringEntry(methodName);
		}
	}

	public interface Check {
		public void check();
	}
	public class ContextCheck implements Check {
		private String contextName;
		private String contextSubName;
		private String variableName;
		private String errorMessage;
		public ContextCheck(String contextName, String contextSubName, String variableName, String errorMessage){
			this.contextName = contextName;
			this.contextSubName = contextSubName;
			this.variableName = variableName;
			this.errorMessage = errorMessage;
		}
		public void check(){
			if(!contexts.get(contextName).get(contextSubName).containsKey(variableName)){
				System.err.println(errorMessage+":"+variableName+" does not exist in context"+contextName+":"+contextSubName);
			}
		}
	}
	public class DefinedCheck implements Check {
		private VariableEntry variable;
		private String errorMessage;
		public DefinedCheck(VariableEntry variable, String errorMessage){
			this.variable = variable;
			this.errorMessage = errorMessage;
		}
		public void check(){
			if(!variable.isDefined()){
				System.err.println(errorMessage+":"+variable.getName()+" was not defined previously.");
			}
		}
	}

	private class UnableToGenerateException extends RuntimeException {
		private static final long serialVersionUID = -7770164685406782500L;

		public UnableToGenerateException(String name, IToken offender){
			super(name+" failed to generate:"+tokenErrorMessage(offender)+"!");
		}
	}

	public String getLocalContext() {
		return LOCAL_CONTEXT;
	}

	public VariableEntry getNoDefaultToken() {
		return NO_DEFAULT_TOKEN;
	}

	public Map<String, Map<String,Map<String,VariableEntry>>> getContexts() {
		return contexts;
	}

	public String getUnknownType() {
		return TYPE_UNKNOWN;
	}

	public void setReturnType() {
		returnType = new MethodEntry(null,TYPE_UNKNOWN,null);		
	}
	public MethodEntry getReturnType(){
		return returnType;
	}

	public void resetReturnType() {
		returnType = null;
	}

	public void addCheck(Check check) {
		this.checks.add(check);
	}

}
