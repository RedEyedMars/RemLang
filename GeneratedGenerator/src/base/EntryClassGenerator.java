package base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;

import com.rem.parser.parser.*;


public class EntryClassGenerator extends Generator{

	public File directory = null;
	private int constructorIndex = 0;
	public EntryClassGenerator(){
		addElement("outline",new String[]{
				"package gen.entries;\n\n"+
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
		addElement("body",new String[]{
				"{",/*Body*/"\n\t}"});
		addElement("implements",new String[]{
				",",/*Properties*/""});
		addElement("publicCall",new String[]{
				"public ",/*Contents*/""});
		addElement("privateCall",new String[]{
				"private ",/*Contents*/""});
		addElement("appendToBuilder",new String[]{
				"",/*Entry*/".get(builder);"});
		addElement("methodDeclaration",new String[]{
				"public ",/*Type*/" ",/*Method Header*/"",/*MethodBody*/""});
		addElement("getMethodDeclaration",new String[]{
				"public void get(StringBuilder builder){",/*MethodBody*/"\n\t}"});
		addElement("constructorDeclaration",new String[]{
				"public ",/*Type*/"Entry(",/*Parameters*/")",/*MethodBody*/""});
	}

	@Override
	public void setup(ParseContext data) {
		directory = new File(Generators.generator.getDirectory(),"entries");
		directory.mkdirs();

	}

	@Override
	public void generate(ParseContext data){
		generateAll(data.getList("entry_class_definitions").getNewTokens(),"entry_dec");
	}

	//	ENTRY NAME as entryClassName in entry_class_names (USES property_name+) as implements? entry_class_element*
	@Override
	public void generateRoot(IToken root) {
		String className = root.get("entryClassName").getString();
		Map<String,Entry> variables = new LinkedHashMap<String,Entry>();
		Map<String,Entry> methods = new LinkedHashMap<String,Entry>();
		ListEntry propertyNames = new ListEntry();
		ListEntry constants = new ListEntry();
		constants.setDelimiter("");
		Set<String> methodNameSet = new HashSet<String>();
		Set<String> variableNameSet = new HashSet<String>();
		ListEntry constructors = new ListEntry();
		constructors.setDelimiter("");
		String trueClassName = camelize(className);
		Generators.generator.addContext(className, null, Generators.generator.getLocalContext(),  Generators.generator.getNoDefaultToken());
		if(root.get("implements")!=null){
			List<IToken> properties = root.get("implements").getAll("property_names");
			if(properties!=null){
				for(IToken property:properties){
					String propertyName = property.getString();
					propertyNames.add(new StringEntry("I"+camelize(propertyName)));
					if(Generators.property.getPropertyVariables().containsKey(propertyName)){
						for(String key:Generators.property.getPropertyVariables().get(propertyName).keySet()){
							variables.put(key,Generators.property.getPropertyVariables().get(propertyName).get(key));
							variableNameSet.add(key);
							Generators.generator.getContexts().get(className).get(Generators.generator.getLocalContext())
								.put(key,Generators.property.getPropertyActualVariables().get(propertyName).get(key));
						}
					}
					else {
						ListEntry listener = new ListEntry();
						Generators.property.addVariableListener(propertyName,className,listener,variableNameSet);
						variables.put("$"+propertyName,listener);
						Generators.generator.addCheck(new PropertyDefinedCheck(propertyName,"EntryClass generateRoot(001)"));
					}
					if(Generators.property.getPropertyMethods().containsKey(propertyName)){
						for(String key:Generators.property.getPropertyMethods().get(propertyName).keySet()){
							methods.put(key,Generators.property.getPropertyMethods().get(propertyName).get(key));
							methodNameSet.add(key);
						}
					}
					else {
						ListEntry listener = new ListEntry();
						Generators.property.addMethodListener(propertyName,listener,methodNameSet);
						methods.put("$"+propertyName,listener);
						Generators.generator.addCheck(new PropertyDefinedCheck(propertyName,"EntryClass generateRoot(002)"));
					}
				}
			}
		}
		for(IToken element:root.getAll("entry_class_element")){
			for(IToken.Key key:element.keySet()){
				if("constant_declaration".equals(key.getName())){
					IToken varDeclaration = element.get(key);
					String variableName = varDeclaration.get("variableName").getString();
					constants.add(new TabEntry(1,Generators.entryClass.generateVariableDeclaration(varDeclaration, className, true)));
					variableNameSet.add(variableName);
					Generators.generator.getContexts().get(className).get(Generators.generator.getLocalContext()).get(variableName).setFinal(true);
					Generators.generator.getContexts().get(className).get(Generators.generator.getLocalContext()).get(variableName).setStatic(true);
				}
				else if("variable_declaration".equals(key.getName())){
					IToken varDeclaration = element.get(key);
					String variableName = varDeclaration.get("variableName").getString();
					variables.put(variableName,new TabEntry(1,Generators.entryClass.generateVariableDeclaration(varDeclaration, className, false)));
					methods.put(variableName,new TabEntry(0,Generators.entryClass.generateVariableDeclarationMethod(varDeclaration, className)));
					variableNameSet.add(variableName);
				}
				else if("entry_declaration".equals(key.getName())){
					IToken varDeclaration = element.get(key);
					String variableName = varDeclaration.get("entryName").getString();
					Generators.generator.generateEntryDeclaration(varDeclaration, 1, className, Generators.generator.getLocalContext());
					GeneratorGenerator.VariableEntry var = Generators.generator.getContexts().get(className).get(Generators.generator.getLocalContext()).get(variableName);
					GeneratorGenerator.TypeEntry type = Generators.generator.new TypeEntry(var);
					variables.put(variableName,new TabEntry(1,
							new ElementEntry(Generators.entryClass,"privateCall",new ListEntry(var))));
					methods.put(variableName,new ElementEntry(Generators.generator,"methodDeclaration",
							new ListEntry(type,new StringEntry("get"+camelize(variableName)),new ListEntry(), new TabEntry(2,new ElementEntry(Generators.generator,"returnCall",new ListEntry(new StringEntry(variableName)))))));
					variableNameSet.add(variableName);
				}
				else if("constructor".equals(key.getName())){
					String constructorName = "constructor"+constructorIndex ++;
					Generators.generator.addContext(className, Generators.generator.getLocalContext(), constructorName, Generators.generator.getNoDefaultToken());
					IToken constructor = element.get(key);
					List<IToken> parameterList = constructor.getAll("parameter");
					ListEntry parameters = new ListEntry();
					if(parameterList!=null){
						for(IToken parameter:parameterList){
							GeneratorGenerator.VariableEntry variable = Generators.generator.getParameter(parameter, className, Generators.generator.getLocalContext(), constructorName);
							parameters.add(variable);
						}
					}
					constructors.add(
							new TabEntry(1,new ElementEntry(Generators.entryClass,"constructorDeclaration",new ListEntry(
									new StringEntry(trueClassName),parameters,
									Generators.entryClass.generateEntryMethodBody(constructor, null, className, constructorName)))));										

				}
				else if("output_method".equals(key.getName())){
					GeneratorGenerator.MethodEntry header = 
							Generators.generator.new MethodEntry("noSubjectCall",new ListEntry(new StringEntry("get"),new StringEntry("StringBuilder builder")));
					Generators.generator.addContext(className, Generators.generator.getLocalContext(), "$OUTPUT", Generators.generator.getNoDefaultToken());
					ListEntry body = new ListEntry();
					body.setDelimiter("");
					if(element.get(key).get("body")!=null){
						body.add(Generators.entryClass.generateEntryMethodBody(element.get(key).get("body"), header, className, null));
					}
					List<IToken> values = element.get(key).getAll("entry_values");
					boolean first = true;
					for(IToken statement:values){
						IToken breck = statement.get("break");
						if(breck!=null){
							first = true;
						}
						else {
							IToken value = statement.get("value");
							IToken ifStatement = statement.get("ifStatement");
							if(ifStatement!=null){							
								ListEntry ifBody = new ListEntry();
								ifBody.setDelimiter("");
								for(IToken.Key valueKey:value.keySet()){
									if("entry_definition".equals(valueKey.getName())){
										ifBody.add(new TabEntry(3,new ElementEntry(Generators.entryClass,"appendToBuilder", new ListEntry(
												Generators.generator.generateEntryDefinition(value.get(valueKey), className, "$OUTPUT")))));
									}
									else if("entry_names".equals(valueKey.getName())){
										ifBody.add(new TabEntry(3,new ElementEntry(Generators.entryClass,"appendToBuilder", new ListEntry(
												new StringEntry(value.get(valueKey).getString())))));
									}
								}
								if(ifStatement.get("otherwise")==null){
									if(first){
										body.add(new TabEntry(2,new ElementEntry(Generators.generator,"ifStatementCall",new ListEntry(Generators.generator.generateBooleanStatement(ifStatement.get("boolean_statement"), className,"$OUTPUT"),ifBody))));							
									}
									else {
										body.add(new TabEntry(2,new ElementEntry(Generators.generator,"elseIfStatementCall",new ListEntry(Generators.generator.generateBooleanStatement(ifStatement.get("boolean_statement"), className,"$OUTPUT"),ifBody))));
									}
								}
								else {
									body.add(new TabEntry(2,new ElementEntry(Generators.generator,"elseStatementCall",new ListEntry(ifBody))));
								}
								body.add(new TabEntry(2,new StringEntry("}")));
								first = false;
							}
							else {
								first = true;
								for(IToken.Key valueKey:value.keySet()){
									if("entry_definition".equals(valueKey.getName())){
										body.add(new TabEntry(2,new ElementEntry(Generators.entryClass,"appendToBuilder", new ListEntry(
												Generators.generator.generateEntryDefinition(value.get(valueKey), className, "$OUTPUT")))));
									}
									else if("entry_names".equals(valueKey.getName())){
										body.add(new TabEntry(2,new ElementEntry(Generators.entryClass,"appendToBuilder", new ListEntry(
												new StringEntry(value.get(valueKey).getString())))));
									}
								}
							}
						}
					}
					methods.put("$OUTPUT",
							new TabEntry(1,new ElementEntry(Generators.entryClass,"getMethodDeclaration",new ListEntry(body))));											

				}
				else if("entry_method".equals(key.getName())){
					GeneratorGenerator.MethodEntry header = 
							(GeneratorGenerator.MethodEntry) Generators.entryClass.generateEntryMethodHeader(element.get(key), className);
					GeneratorGenerator.TypeEntry type = Generators.generator.new TypeEntry(header);
					methods.put(header.getMethodName(),
							new TabEntry(1,new ElementEntry(Generators.entryClass,"methodDeclaration",new ListEntry(
									type,header,Generators.entryClass.generateEntryMethodBody(element.get(key), header, className, header.getMethodName())))));											

				}
			}
		}


		Entry implementsProperties = new ListEntry();
		if(!propertyNames.isEmpty()){
			implementsProperties = new ElementEntry(Generators.entryClass,"implements",new ListEntry(propertyNames));
		}
		ListEntry variableList = new ListEntry();
		variableList.setDelimiter("");
		for(String variable:variables.keySet()){
			variableList.add(variables.get(variable));
		}
		ListEntry methodList = new ListEntry();
		methodList.setDelimiter("");
		for(String method:methods.keySet()){
			methodList.add(methods.get(method));
		}
		ListEntry complete = new ListEntry(constants,variableList,constructors,methodList);
		complete.setDelimiter("\n");

		addFile(directory,trueClassName+"Entry.java", 
				new ListEntry(new StringEntry(trueClassName),
						implementsProperties,
						new StringEntry(trueClassName),
						complete));
	}
	public Entry generateVariableDeclaration(IToken varDeclaration, String contextName, boolean isPublic){

		return new ElementEntry(Generators.entryClass,isPublic?"publicCall":"privateCall",new ListEntry(
				Generators.generator.generateVariableDeclaration(varDeclaration, contextName, Generators.generator.getLocalContext())));
	}
	public Entry generateVariableDeclarationMethod(IToken varDeclaration, String contextName){

		String variableName = varDeclaration.get("variableName").getString();
		GeneratorGenerator.VariableEntry variable = null;
		if(!Generators.generator.getContexts().get(contextName).get(Generators.generator.getLocalContext()).containsKey(variableName)){
			variable = Generators.generator.new VariableEntry(variableName,Generators.generator.getUnknownType());

			String castToType = Generators.generator.getCastType(varDeclaration, contextName, Generators.generator.getLocalContext());
			if(castToType!=null){
				variable.changeType(castToType);
			}
			Generators.generator.getContexts().get(contextName).get(Generators.generator.getLocalContext()).put(variableName,variable);
		}
		else {
			variable = Generators.generator.getContexts().get(contextName).get(Generators.generator.getLocalContext()).get(variableName); 
		}
		GeneratorGenerator.TypeEntry type = Generators.generator.new TypeEntry(variable);	
		return new ElementEntry(Generators.generator,"methodDeclaration",
				new ListEntry(type,new StringEntry("get"+camelize(variableName)),new ListEntry(), new TabEntry(2,new ElementEntry(Generators.generator,"returnCall",new ListEntry(new StringEntry(variableName))))));
	}

	//whitetab{1} NAME as methodName ( AS NAME as methodType )? takes_statement{1}? ( entry_declaration{2} | body_element{2} )+
	public Entry generateEntryMethodHeader(IToken entryMethod, String contextName) {
		String returnType = Generators.generator.getCastType(entryMethod, contextName, Generators.generator.getLocalContext());

		ListEntry parameters = new ListEntry();
		String methodName = null;
		for(IToken.Key key:entryMethod.keySet()){
			if("methodName".equals(key.getName())){
				methodName = entryMethod.get(key).getString();
				Generators.generator.addContext(contextName, Generators.generator.getLocalContext(), methodName, Generators.generator.getNoDefaultToken());
			}
			else if("parameter".equals(key.getName())){
				parameters.add(Generators.generator.getParameter(entryMethod.get(key), contextName, Generators.generator.getLocalContext(),methodName));
			}
		}
		GeneratorGenerator.MethodEntry ret = Generators.generator.new MethodEntry("noSubjectCall",new ListEntry(new StringEntry(methodName),parameters));
		if(returnType!=null){
			ret.changeType(returnType);
		}
		else {
			ret.setDefaultType("void");
		}
		ret.setMethodName(methodName);
		return ret;
	}
	public Entry generateEntryMethodBody(IToken entryMethod, GeneratorGenerator.MethodEntry method, String contextName, String methodName) {
		ListEntry body = new ListEntry();
		body.setDelimiter("");
		GeneratorGenerator.TypeEntry retType = Generators.generator.new TypeEntry();
		boolean isOutput = methodName == null;
		if(isOutput){
			methodName = "$OUTPUT";
		}
		for(IToken.Key key:entryMethod.keySet()){
			if("entry_declaration".equals(key.getName())){
				body.add(
						Generators.generator.
						generateEntryDeclaration(entryMethod.get(key),2,contextName, methodName));
			}
			else if("body_element".equals(key.getName())){
				body.add(
						Generators.generator.
						generateBodyElement(entryMethod.get(key),2,contextName, methodName,retType));
			}
		}
		if(method!=null&&retType.getSubject()!=null&&retType.getSubject().hasType()){
			method.changeType(retType.getSubject().getType());
		}
		else if(method!=null){
			method.changeType(retType.getDefaultType());
		}
		if(isOutput){
			return body;
		}
		else {
			return new ElementEntry(Generators.entryClass,"body",new ListEntry(body));
		}
	}

	@Override
	public String getName() {
		return "Entry";
	}

	public class PropertyDefinedCheck implements ICheck {
		private String propertyName;
		private String errorMessage;
		public PropertyDefinedCheck(String propertyName, String errorMessage){
			this.propertyName = propertyName;
			this.errorMessage = errorMessage;
		}
		public void check(){
			if(!Generators.property.getPropertyMethods().containsKey(propertyName)&&!Generators.property.getPropertyVariables().containsKey(propertyName)){
				System.err.println(errorMessage+":\""+propertyName+"\" property was not defined.");
			}
		}
	}



}
