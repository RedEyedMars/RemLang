package base;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rem.parser.Generator;
import com.rem.parser.IParser;
import com.rem.parser.IToken;
import com.rem.parser.ParseData;
import com.rem.parser.ParseList;
import com.rem.parser.RegexParser;

import base.lists.ClassNames;
import base.lists.Listnames;
import base.lists.Tokens;

public class GeneratorGenerator extends Generator {

	private static final String LOCAL_CONTEXT = "$local";
	private static final String DEFAULT_TOKEN = "$token";
	private static final String TYPE_UNKNOWN = "$UNKNOWN";
	private static final Entry METHOD_NEW = new StringEntry("new");
	private final VariableEntry NO_DEFAULT_TOKEN = new VariableEntry("$NO_DEFAULT","IToken",null);
	private File directory;
	private Set<String> classNames = new HashSet<String>();
	private Map<String,Map<String,Map<String,VariableEntry>>> contexts = new HashMap<String,Map<String,Map<String,VariableEntry>>>();
	private List<Check> checks  = new ArrayList<Check>();
	private ListEntry constructorBody = new ListEntry();
	private String[] outline = new String[]{
			"package gen;\n\n"+
					"import java.io.*;\n"+
					"import java.util.*;\n"+
					"import com.rem.parser.*;\n\n"+
					"public class ",/*Class Name*/" extends Generator {\n",/*Contents*/ "\n}"};
	private String[] variableDeclaration = new String[]{
			"",/*Type Name*/" ",/*Variable Name*/" = ",/*Assignment*/";"};
	private String[] variablePrototype = new String[]{
			"",/*Type Name*/" ",/*Variable Name*/""};
	private String[] methodCall = new String[]{
			"",/*Subject Name*/".",/*Method Name*/"(",/*Parameters*/")"};
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
			"new Boolean(",/*Parameters*/")"};
	private String[] setCall = new String[]{
			"",/*Subject*/" = ",/*Argument*/";"};
	private String[] equalsCall = new String[]{
			"",/*Subject*/".equals(",/*Argument*/")"};
	private String[] notEqualsCall = new String[]{
			"!",/*Subject*/".equals(",/*Argument*/")"};
	private String[] newStringListCall = new String[]{
			"new String[]{",/*Parameters*/"}"};
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
			"\tpublic ",/*Class Name*/"(){",/*Constructor Body*/"\n\t}"};
	private String[] semicoloned = new String[]{
			"",/*Body*/";"};


	public GeneratorGenerator(){
		addElement("outline",outline);
		addElement("variableDeclaration",variableDeclaration);
		addElement("variablePrototype",variablePrototype);
		addElement("methodCall",methodCall);
		addElement("exactCall",exactCall);
		addElement("exactDuoCall",exactDuoCall);
		addElement("newObjectCall",newObjectCall);
		addElement("operatorCall",operatorCall);
		addElement("noSubjectCall",noSubjectCall);
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
		addElement("constructor",constructor);
		addElement("semicoloned",semicoloned);
	}

	@Override
	public void generateRoot(IToken root){

		constructorBody.setDelimiter("\n");
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

		for(IToken.Key key:root.keySet()){
			if("className".equals(key.getName())){
				className = camelize(root.get(key).getString());
				classNames.add(className);
				contexts.put(className, new HashMap<String,Map<String,VariableEntry>>());
				Map<String,VariableEntry> varMap = new HashMap<String,VariableEntry>();
				varMap.put(DEFAULT_TOKEN, new VariableEntry("root","IToken",null));
				contexts.get(className).put(LOCAL_CONTEXT, varMap);
				methodDeclarations.add(new ElementEntry("constructor",new ListEntry(new StringEntry(className),constructorBody)));
			}
			else if("auxillary_declaration".equals(key.getName())){
				methodDeclarations.add(generateAuxillaryDeclaration(root.get(key),className,LOCAL_CONTEXT));
			}
			else if("variable_declaration".equals(key.getName())){
				variableDeclarations.add(generateVariableDeclaration(root.get(key),className,LOCAL_CONTEXT));
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
		addFile(getName(),directory,className+".java",new ListEntry(new StringEntry(className)));
		addEntry(getName(),directory,className+".java","celements",new ListEntry(classElements));

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

				ret = new ElementEntry("methodDeclaration",new ListEntry(new StringEntry("void"),new StringEntry(methodName),parameters,body));
			}
			else if("takeName".equals(key.getName())){
				String parameter = auxillary.get(key).getString();
				VariableEntry var = new VariableEntry(parameter,TYPE_UNKNOWN);
				parameters.add(var);
				contexts.get(contextName).get(methodName).put(parameter,var);
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
						elementParameters.add(new ElementEntry("exactDuoCall",new ListEntry(entryD,entry)));
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

				this.constructorBody.add(
						new TabEntry(2,
								new ElementEntry("semicoloned",new ListEntry(new ElementEntry("noSubjectCall",
										new ListEntry(new StringEntry("addElement"),new ListEntry(new QuoteEntry(elementName),
												new ElementEntry("newStringListCall",new ListEntry(elementParameters)))))))));
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
		String methodName = "";
		ListEntry parameters = new ListEntry();
		ListEntry body = new ListEntry();
		body.setDelimiter("");
		for(IToken.Key key:generation.keySet()){
			if("tokenName".equals(key.getName())){
				String generateName = generation.get(key).getString();
				methodName = "generate"+camelize(generateName);
				VariableEntry defaultToken = new VariableEntry(generateName,"IToken");
				addContext(contextName,contextSubName,methodName,defaultToken);
				parameters.add(defaultToken);

				ret = new ElementEntry("methodDeclaration",new ListEntry(new StringEntry("Entry"),new StringEntry(methodName),parameters,body));
			}
			else if("takeName".equals(key.getName())){
				String parameter = generation.get(key).getString();
				VariableEntry var = new VariableEntry(parameter,TYPE_UNKNOWN);
				parameters.add(var);
				contexts.get(contextName).get(methodName).put(parameter,var);
			}
			else if("entry_declaration".equals(key.getName())){
				body.add(generateEntryDeclaration(generation.get(key),2,contextName,methodName));
			}
			else if("body_element".equals(key.getName())){
				body.add(generateBodyElement(generation.get(key),2,contextName,methodName));
			}
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
		if(definition!=null){
			assignment = generateEntryDefinition(definition,contextName,contextSubName);
		}
		else {
			assignment = new StringEntry("null");
		}
		VariableEntry entry = new VariableEntry(entryName,"Entry",assignment);
		contexts.get(contextName).get(contextSubName).put(entryName, entry);

		ListEntry section = new ListEntry();
		section.setDelimiter("");
		section.add(new TabEntry(tabs,entry));
		IToken delimToken = declaration.get("delimiter");
		if(delimToken!=null){
			section.add(
					new TabEntry(tabs,
							new ElementEntry("semicoloned",
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
								new ListEntry(new ElementEntry("semicoloned",new ListEntry(generateMethodCall(bodyElement.get(key),contextName,contextSubName)))));
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
		return new ElementEntry("errorCall",new ListEntry(output));
	}
	//whitetab{tabs} RETURN ( generate_call{tabs+1} | entry_definition{tabs+1} | method_call{tabs+1} | whitetab{tabs+1} (entry_name|NULL) )
	public Entry generateReturn(IToken returnToken, String contextName, String contextSubName){		
		for(IToken.Key key:returnToken.keySet()){
			if("generate_call".equals(key.getName())||"method_call".equals(key.getName())){
				return new ElementEntry("returnCall",
						new ListEntry(generateMethodCall(returnToken.get(key), contextName, contextSubName)));
			}
			else if("entry_definition".equals(key.getName())){
				return new ElementEntry("returnCall",
						new ListEntry(generateEntryDefinition(returnToken.get(key), contextName, contextSubName))); 
			}
			else if("NULL".equals(key.getName())){
				return new ElementEntry("returnCall",
						new ListEntry(new StringEntry("null")));
			}
			else if("entry_names".equals(key.getName())){
				String entryName = returnToken.get(key).getString();
				checks.add(new ContextCheck(contextName,contextSubName,entryName,"(001) Entry name"));
				return new ElementEntry("returnCall",
						new ListEntry(new StringEntry(entryName))); 
			}
		}
		throw new UnableToGenerateException("Return (005)",returnToken);
	}

	//whitetab{tabs} IF boolean_statement ( body_element{tabs+1} )+ else_statement{tabs}?
	public Entry generateIfStatement(IToken ifStatement, int tabs, String contextName, String contextSubName){
		ListEntry ifPart = new ListEntry();
		ifPart.setDelimiter("");
		ListEntry ifBody = new ListEntry();
		ifBody.setDelimiter("\n");
		ListEntry elseBody = null;
		for(IToken.Key key:ifStatement.keySet()){
			if("boolean_statement".equals(key.getName())){
				ifPart.add(generateBooleanStatement(ifStatement.get(key),contextName,contextSubName));
			}
			else if("body_element".equals(key.getName())){
				ifBody.add(generateBodyElement(ifStatement.get(key),tabs+1,contextName,contextSubName));
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
		ret.add(new TabEntry(tabs,new ElementEntry("ifStatementCall",ifPart)));
		ret.add(new TabEntry(tabs,new StringEntry("}")));
		if(elseBody!=null){
			ret.add(new TabEntry(tabs,new ElementEntry("elseStatementCall",new ListEntry(elseBody))));
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
				left = new ElementEntry("equalsCall", 
						new ListEntry(new StringEntry(varName),new QuoteEntry(flip.get(key).getString())));
			}
			else if("right".equals(key.getName())){
				Entry right = new ElementEntry("setCall", 
						new ListEntry(new StringEntry(varName),new QuoteEntry(flip.get(key).getString())));
				Entry ifPart = new ElementEntry(first?"ifStatementCall":"elseIfStatementCall",new ListEntry(
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
				statement.add(new TabEntry(tabs,new ElementEntry("elseStatementCall",new ListEntry(body))));
				statement.add(new TabEntry(tabs,new StringEntry("}")));
				ret.add(statement);
			}
		}

		return ret;
	}

	public Entry generateSetCall(IToken call, String contextName, String contextSubName){
		String varName = null;
		VariableEntry variable = null;
		Entry assignment = null;
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
				assignment = generateBooleanStatement(call.get(key),contextName,contextSubName);
			}
			else if("method_call".equals(key.getName())){
				assignment = generateMethodCall(call.get(key),contextName,contextSubName);
			}
			else if("method_parameter".equals(key.getName())){
				assignment = generateMethodParameter(call.get(key),contextName,contextSubName);
			}
		}
		variable.changeType(((MethodEntry)assignment).getType());
		return new ElementEntry("setCall",new ListEntry(new StringEntry(varName),assignment));
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
				StringEntry specificName = new StringEntry(camelize(allTypeTokens.get("specificTokenName").getString()));
				StringEntry childName = new StringEntry(allTypeTokens.get("tokenName").getString());
				String contextTokenSubName = contextSubName +"."+ childName.getString();
				VariableEntry childToken = new VariableEntry(childName.getString(),"IToken");
				addContext(contextName,contextSubName,contextTokenSubName,childToken);
				if(!specificName.equals("*")){
					StringEntry allName = new StringEntry(childName.getString()+specificName.getString());
					VariableEntry alls = new VariableEntry(allName.getString(),"List<IToken>",new MethodEntry(tokenName,"getAll",new ListEntry(new QuoteEntry(specificName.getString()))));
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
					ifBody.add(new TabEntry(tabs+1,new ElementEntry("forStatementCall",new ListEntry(
							new ElementEntry("variablePrototype",new ListEntry(new StringEntry("IToken"),childName)),allName,forBody))));
					ifBody.add(new TabEntry(tabs+1,new StringEntry("}")));

					ret.add(
							new TabEntry(tabs,new ElementEntry("ifStatementCall",new ListEntry(
									new ElementEntry("operatorCall",new ListEntry(allName,new StringEntry("!="),new StringEntry("null"))),
									ifBody))));
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
								Entry ifPart = new TabEntry(tabs+1,new ElementEntry(
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
						forBody.add(new TabEntry(tabs+1,new ElementEntry(
								"elseStatementCall",new ListEntry(elseBody))));
						forBody.add(new TabEntry(tabs+1,new StringEntry("}")));
					}
				}
				Entry forPart = new ElementEntry("tokenForStatementCall",new ListEntry(childKeyName,tokenName,forBody));
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
		for(IToken.Key key:varElement.keySet()){
			if("variableName".equals(key.getName())){
				varName = varElement.get(key).getString();
			}
			else if("castToType".equals(key.getName())){
				typeName = varElement.get(key).getString();
			}
			else if("method_call".equals(key.getName())){
				MethodEntry method = (MethodEntry)generateMethodCall(varElement.get(key),contextName, contextSubName);
				assignment = method;
				if(method.hasType()){
					typeName = method.getType();
				}
			}
			else if("method_parameter".equals(key.getName())){
				MethodEntry parameter = (MethodEntry)generateMethodParameter(varElement.get(key),contextName, contextSubName);
				assignment = parameter;
				if(parameter.hasType()){
					typeName = parameter.getType();
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
			var.setAssignment(assignment);
			return var;
		}
		else {
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
	public Entry generateMethodCall(IToken method, String contextName, String contextSubName){
		IToken subject = method.get("subject");
		IToken subjectAsMethodParameter = subject.get("method_parameter");

		Entry subjectName = null;
		boolean isGenerate = false;
		if(subjectAsMethodParameter!=null){

			subjectName = generateMethodParameter(subjectAsMethodParameter,contextName,contextSubName);
		}
		else {
			String subName = method.get("subject").getString();
			subjectName = new StringEntry(subName);
			if("generate".equals(subName)){
				isGenerate = true;
			}
			else if("new".equals(subName)){
				subjectName = METHOD_NEW;
			}
		}
		String methodName = method.get("methodName").getString();
		if(subjectName.equals(METHOD_NEW)){
			methodName = camelize(methodName);
		}
		ListEntry parameters = new ListEntry();
		MethodEntry ret = null;
		if(isGenerate){
			parameters.add(contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN));
			ret = new MethodEntry("noSubjectCall",new ListEntry(new StringEntry("generate"+camelize(methodName)),parameters));
			ret.changeType("Entry");
		}
		else {
			ret = new MethodEntry(subjectName,methodName,parameters);
			if(subjectName.equals(METHOD_NEW)){
				ret.changeType(methodName);
			}
		}
		
		for(IToken.Key key:method.keySet()){
			if("angle_braces".equals(key.getName())){
				generateAngleBraces(method.get(key),parameters,contextName,contextSubName);
				if(!isGenerate){
					ret.setElementName("newMethodWithBracesCall");
				}
			}
			else if("boolean_statement".equals(key.getName())){
				parameters.add(generateBooleanStatement(method.get(key),contextName,contextSubName));
			}
			else if("method_parameter".equals(key.getName())){
				parameters.add(generateMethodParameter(method.get(key),contextName,contextSubName));
			}
			else if("method_call".equals(key.getName())){
				parameters.add(generateMethodCall(method.get(key),contextName,contextSubName));
			}
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
				else if("entry_names".equals(key.getName())){
					param = new MethodEntry(null,parameter.get(key).getString(),null);
					param.changeType("Entry");
				}
				else if("braces".equals(key.getName())){
					ListEntry braces = new ListEntry();
					generateAngleBraces(parameter.get(key),braces,contextName,contextSubName);
					param.getEntries().add(braces);
					param.setElementName("exactWithAngleCall");
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
	public Entry generateMethodParameter(IToken parameter, String contextName, String contextSubName){
		for(IToken.Key key:parameter.keySet()){
			if("NULL".equals(key.getName())){

				return new MethodEntry(null,parameter.getString(),null);
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
			else if("entry_definition".equals(key.getName())){
				return generateEntryDefinition(parameter.get(key),contextName,contextSubName);
			}
			else if("entry_names".equals(key.getName())){
				MethodEntry entry = new MethodEntry(null,parameter.getString(),null);
				entry.changeType("");
				return entry;
			}
			else if("class_names".equals(key.getName())){
				String type = camelize(parameter.getString());
				MethodEntry entry = new MethodEntry(METHOD_NEW,type,new ListEntry());
				entry.changeType(type);
				return entry;
			}
			else if("string".equals(key.getName())){
				return new MethodEntry(null,"\""+parameter.getString()+"\"",null);
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
				ret = new MethodEntry(ret,"get",StringEntry.getEntry(votName.get(key).getString()));
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
					System.out.println(variableName);
				}
				ret = new MethodEntry(null,var.getName(),null);
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
				if(boolean_statement.get(key).get("continuationOperator").getString().equals("and")){
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
			return new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),
					generateMethodParameter(notStatement.get("method_parameter"),contextName,contextSubName)));
		}
		else {
			MethodEntry ret = new MethodEntry("operatorCall",new ListEntry());
			IToken statement = boolean_clause.get("operatedStatement");
			int operatorState = -1;
			for(IToken.Key key:statement.keySet()){
				if("method_parameter".equals(key.getName())){
					ret.getEntries().add(generateMethodParameter(statement.get(key),contextName,contextSubName));
				}
				else if("SINGULAR".equals(key.getName())){
					Entry subject = ret.get(0);
					if(operatorState == 0){
						ret = new MethodEntry(subject,"isSingular",new ListEntry());
					}
					else if(operatorState==1){
						ret = new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),
								new MethodEntry(subject,"isSingular",new ListEntry())));
					}
				}
				else if("EMPTY".equals(key.getName())){
					Entry subject = ret.get(0);
					if(operatorState==0){
						ret = new MethodEntry(subject,"isEmpty",new ListEntry());
					}
					else if(operatorState==1){
						ret = new MethodEntry("exactDuoCall",new ListEntry(new StringEntry("!"),
								new MethodEntry(subject,"isEmpty",new ListEntry())));
					}
				}
				else if("operator".equals(key.getName())){
					if(statement.get(key).get("IS")!=null){
						if(((MethodEntry)ret.get(0)).getType().equals("Integer")||((MethodEntry)ret.get(0)).getType().equals("Boolean")){
							if(statement.get(key).get("NOT")!=null){
								ret.getEntries().add(new StringEntry("!="));
								operatorState = 1;
							}
							else {
								ret.getEntries().add(new StringEntry("=="));
								operatorState = 0;
							}
						}
						else {
							if(statement.get(key).get("NOT")!=null){
								ret = new MethodEntry("notEqualsCall",new ListEntry(ret.get(0)));
								operatorState = 1;
							}
							else {
								ret = new MethodEntry("equalsCall",new ListEntry(ret.get(0)));
								operatorState = 0;
							}
						}
					}
					else {
						ret.getEntries().add(new StringEntry(statement.get(key).getString()));
						operatorState = 2;
					}

				}
			}
			return ret;
		}
	}

	/*
	 * ( element_name list_entry_definition | list_entry_definition | TAB_BRACES )
	 */

	public Entry generateEntryDefinition(IToken definition,String contextName, String contextSubName){
		IToken elementNameToken = definition.get("element_names");
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
				ret = (MethodEntry)listEntry;
			}
		}
		else {
			ret =  new MethodEntry(METHOD_NEW,"ElementEntry",new ListEntry(
					new QuoteEntry(elementNameToken.getString()),
					new ListEntry(listEntry)));
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
						entry.add(new MethodEntry(null,list.get(subKey).getString(),null));
						checks.add(new ContextCheck(contextName,contextSubName,varName,"(005) Variable name"));						
					}
					else if("entry_names".equals(subKey.getName())){
						entry.add(new MethodEntry(null,list.get(subKey).getString(),null));
					}
				}
				MethodEntry ret = new MethodEntry(METHOD_NEW,"ListEntry",entry);
				ret.changeType("Entry");
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
					else {
						entry.add(
								new MethodEntry(METHOD_NEW,"StringEntry",
										new ListEntry(generateVariableOrTokenName(string.get(subKey), contextName, contextSubName))));
					}
				}
				MethodEntry ret = new MethodEntry(METHOD_NEW,"ListEntry",entry);
				ret.changeType("Entry");
				return ret;
			}
		}
		throw new UnableToGenerateException("List Entry (003)",led);
	}

	//TODO
	
	public void addContext(String contextName, String contextSubName, String newContextName, VariableEntry newDefaultEntry){

		Map<String,VariableEntry> context = new HashMap<String,VariableEntry>();
		contexts.get(contextName).put(newContextName, context);
		for(String varName:contexts.get(contextName).get(contextSubName).keySet()){
			context.put(varName, contexts.get(contextName).get(contextSubName).get(varName));
		}
		context.put(DEFAULT_TOKEN,newDefaultEntry);
		context.put(newDefaultEntry.getName(),newDefaultEntry);
		VariableEntry previousDefault = contexts.get(contextName).get(contextSubName).get(DEFAULT_TOKEN);
		context.put(previousDefault.getName(), previousDefault);
	}
	
	@Override
	public IParser getLazyNameParser(){
		return Tokens.NAME;
	}

	public String getName(){
		return "gen";
	}

	@Override
	protected void generate(ParseData data) {
		String fileName = data.getFileName();
		int indexOfDot = fileName.lastIndexOf('.');
		if(indexOfDot>-1)fileName = fileName.substring(0, indexOfDot);
		directory = new File("../Generated"+camelize(fileName)+"/src/gen/");
		directory.mkdirs();

		generateAll(this,((ParseList)data.getList("class_definitions")).getNewTokens(),"class_def");
		outputAll();

	}

	@Override
	public void assignListElementNames(Map<String,ParseList> listMap, IToken root){		
		for(IParser parser:Listnames.parser){
			String pattern = ((RegexParser)parser).getPattern();
			String listName = pattern.substring(0, pattern.length()-1);
			if(!listMap.containsKey(listName)){
				listMap.put(listName, ParseList.createNew(listName));
			}
		}
		for(IParser parser:ClassNames.parser){
			String className = ((RegexParser)parser).getPattern();
			listMap.get("class_names").getNamesParser().addName(className);
		}
	}

	public class VariableEntry extends ElementEntry {
		private StringEntry typeEntry;
		private String name;
		private boolean defined = true;

		public VariableEntry(String name, String type, Entry assignment) {
			super(assignment!=null?"variableDeclaration":"exactCall", new ListEntry());
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

		public void setAssignment(Entry assignment) {
			this.setElementName("variableDeclaration");
			this.parameters.add(assignment);
		}

		public void setDefined(boolean b) {
			defined = b;
		}

		public VariableEntry(String name, String type) {
			super("exactDuoCall", new ListEntry());
			this.typeEntry = new StringEntry(type);
			this.name = name;
			this.parameters.add(typeEntry);
			this.parameters.add(new StringEntry(" "+name));
		}

		public String getType() {
			return this.typeEntry.getString();
		}

		public String getName() {
			return name;
		}

		@Override
		public void get(StringBuilder builder){
			if(typeEntry.getString().equals(TYPE_UNKNOWN)){
				typeEntry.set("String");
			}
			super.get(builder);
		}

		public void changeType(String newType){
			this.typeEntry.set(newType);
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
	}

	public class MethodEntry extends ElementEntry {
		private StringEntry typeEntry;
		private String methodName;

		public MethodEntry(String element, ListEntry parameters) {
			super(element,parameters);
			this.typeEntry = new StringEntry(TYPE_UNKNOWN);
		}
		public MethodEntry(Entry subject, String methodName, ListEntry parameters) {
			super(subject==null?"exactCall":METHOD_NEW.equals(subject)?"newObjectCall":"methodCall", new ListEntry());
			this.typeEntry = new StringEntry(TYPE_UNKNOWN);
			if(subject!=null&&!METHOD_NEW.equals(subject)){
				this.parameters.add(subject);
			}
			//this.parameters.add(typeEntry);
			this.methodName = methodName;
			this.parameters.add(new StringEntry(methodName));
			if(subject!=null){
				this.parameters.add(parameters);
			}
		}

		public ListEntry getParameters() {
			return this.parameters;
		}

		@Override
		public void get(StringBuilder builder){
			if(typeEntry.getString().equals(TYPE_UNKNOWN)){
				typeEntry.set("String");
			}
			super.get(builder);
		}

		public void changeType(String newType){
			this.typeEntry.set(newType);
		}

		public boolean hasType(){
			return !this.typeEntry.getString().equals(TYPE_UNKNOWN);
		}

		public String getType(){
			return this.typeEntry.getString();
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
	
	private static class UnableToGenerateException extends RuntimeException {
		private static final long serialVersionUID = -7770164685406782500L;

		public UnableToGenerateException(String name, IToken offender){
			super(name+" failed to generate:"+generateTokenErrorMessage(offender)+"!");
		}
	}
	private static String generateTokenErrorMessage(IToken offender){
		String error = "";
		for(IToken.Key key:offender.keySet()){
			error+="("+key.getName()+":"+offender.get(key).getString()+")"+",";
		}
		return error;
	}
}
