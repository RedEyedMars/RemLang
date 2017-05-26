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

public class ClassifierGenerator extends Generator {

	private HashMap<String,ClassEntry> classes = new HashMap<String,ClassEntry>();
	private ClassEntry defaultContext = new ClassEntry("$");
	private HashMap<String,String> constructorAssociations = new HashMap<String,String>();


	public ClassifierGenerator(){
	}
	public ClassEntry getType(String className){
		return (ClassEntry)classes.get(className);
	}
	public String getClassNameFromConstructor(String constructorName){
		return constructorAssociations.get(constructorName);
	}
	public MethodEntry getStaticMethod(String methodName){
		return (MethodEntry)defaultContext.getMethod(methodName);
	}
	public void generate(ParseContext data){
		MethodEntry systemMethod = new MethodEntry(new SystemEntry(),"println");
		systemMethod.addParameter("default");
		defaultContext.setMethod("println(1)",systemMethod);
		systemMethod = new MethodEntry(new SystemEntry(),"concat");
		systemMethod.addParameter("leftParameter");
		systemMethod.addParameter("rightParameter");
		defaultContext.setMethod("concat(2)",systemMethod);
		ParseList classDecs = (ParseList)data.getList("class_declarations");
		Generators.classifier.generateAll(classDecs.getNewTokens(),"class");
		IToken rootX = data.getRoot();
		Generators.classifier.println(Generators.classifier.completeTokenErrorMessage(rootX));
		List<IToken> elementRuntime = rootX.getAll("runtime");
		if(elementRuntime != null){
			for(IToken element:elementRuntime){
				for(IToken.Key atomKey:element.keySet()){
					IToken atom = element.get(atomKey);
					generateBodyElement(atom,defaultContext);
				}
			}
		}
		defaultContext.execute(defaultContext);
	}
	public void generateRoot(IToken root){
		ClassEntry newClass = null;
		for(IToken.Key elementKey:root.keySet()){
			if("className".equals(elementKey.getName())){
				IToken element = root.get(elementKey);
				newClass = (ClassEntry)defaultContext.createChild(element.getString());
				classes.put(element.getString(),newClass);
			}
			else if("definition".equals(elementKey.getName())){
				IToken element = root.get(elementKey);
				List<IToken> atomClassDefinition = element.getAll("class_definition");
				if(atomClassDefinition != null){
					for(IToken atom:atomClassDefinition){
						generateClassDefinition(atom,newClass);
					}
				}
			}
		}
	}
	public Entry generateClassDefinition(IToken classDefinition,ClassEntry newClass){
		List<IToken> elementVariableDeclaration = classDefinition.getAll("variable_declaration");
		if(elementVariableDeclaration != null){
			for(IToken element:elementVariableDeclaration){
				IExecutable newBody = (IExecutable)newClass;
				generateVariableDeclaration(element,newBody);
			}
		}
		String className = newClass.getClassName();
		MethodEntry defaultConstructor = new MethodEntry("$()");
		defaultConstructor.addToBody(new MakeNewObjectEntry(className));
		for(IToken.Key elementKey:classDefinition.keySet()){
			if("variable_declaration".equals(elementKey.getName())){
				IToken element = classDefinition.get(elementKey);
				generateVariableDeclaration(element,newClass);
			}
			else if("method_declaration".equals(elementKey.getName())){
				IToken element = classDefinition.get(elementKey);
				IToken parameters = element.get("parameters");
				ArrayList<String> parameterNames = new ArrayList<String>();
				StringBuilder methodName = new StringBuilder();
				methodName.append(element.get("methodName").getString());
				methodName.append("(");
				for(IToken.Key parameterKey:parameters.keySet()){
					if("variableName".equals(parameterKey.getName())){
						IToken parameter = parameters.get(parameterKey);
						parameterNames.add(parameter.getString());
					}
				}
				methodName.append(parameterNames.size());
				methodName.append(")");
				MethodEntry method = new MethodEntry(methodName.toString(),parameterNames);
				IToken body = element.get("body");
				for(IToken.Key actionKey:body.keySet()){
					if("body_element".equals(actionKey.getName())){
						IToken action = body.get(actionKey);
						generateBodyElement(action,method);
					}
					else if("return_call".equals(actionKey.getName())){
						IToken action = body.get(actionKey);
						generateReturnCall(action,method);
					}
				}
				newClass.setMethod(method.getMethodName(),method);
			}
			else if("define_declaration".equals(elementKey.getName())){
				IToken element = classDefinition.get(elementKey);
				IToken parameters = element.get("parameters");
				ArrayList<String> parameterNames = new ArrayList<String>();
				StringBuilder methodName = new StringBuilder();
				if((element.get("DEFINE") != null)){
					methodName.append("$");
				}
				for(IToken.Key parameterKey:parameters.keySet()){
					if("left".equals(parameterKey.getName())){
						IToken parameter = parameters.get(parameterKey);
						methodName.append(parameter.getString());
					}
					else if("right".equals(parameterKey.getName())){
						IToken parameter = parameters.get(parameterKey);
						methodName.append(parameter.getString());
					}
					else if("syntax".equals(parameterKey.getName())){
						IToken parameter = parameters.get(parameterKey);
						methodName.append(parameter.get("NON_SPACE").getString());
					}
					else if("namedSyntax".equals(parameterKey.getName())){
						IToken parameter = parameters.get(parameterKey);
						methodName.append(parameter.get("NON_SPACE").getString());
					}
				}
				methodName.append("(");
				for(IToken.Key parameterKey:parameters.keySet()){
					if("syntax".equals(parameterKey.getName())){
						IToken parameter = parameters.get(parameterKey);
						parameterNames.add(parameter.get("NON_SPACE").getString());
						methodName.append("s");
					}
					else if("namedSyntax".equals(parameterKey.getName())){
						IToken parameter = parameters.get(parameterKey);
						parameterNames.add(parameter.get("syntaxName").getString());
						methodName.append("s");
					}
					else if("variableName".equals(parameterKey.getName())){
						IToken parameter = parameters.get(parameterKey);
						parameterNames.add(parameter.getString());
						methodName.append("v");
					}
				}
				methodName.append(")");
				MethodEntry method = new MethodEntry(methodName.toString(),parameterNames);
				IToken body = element.get("body");
				for(IToken.Key actionKey:body.keySet()){
					if("body_element".equals(actionKey.getName())){
						IToken action = body.get(actionKey);
						generateBodyElement(action,method);
					}
					else if("return_call".equals(actionKey.getName())){
						IToken action = body.get(actionKey);
						generateReturnCall(action,method);
					}
				}
				newClass.setMethod(method.getMethodName(),method);
				constructorAssociations.put(method.getMethodName(),newClass.getClassName());
			}
		}
		return null;
	}
	public Entry generateVariableDeclaration(IToken variableDeclaration,IExecutable body){
		if((variableDeclaration.get("method_parameter") == null)){
			body.addToBody(new SetToNullEntry(variableDeclaration.get("variableName").getString()));
		}
		else {
			IExecutable assignment = (IExecutable)Generators.classifier.generateMethodParameter(variableDeclaration.get("method_parameter"));
			body.addToBody(new SetVariableEntry(variableDeclaration.get("variableName").getString(),assignment));
		}
		return null;
	}
	public Entry generateMethodCall(IToken methodCall,IExecutable body){
		IExecutable ret = (IExecutable)null;
		IExecutable subject = (IExecutable)null;
		if((methodCall.get("subject") != null)){
			subject = (IExecutable)Generators.classifier.generateMethodParameter(methodCall.get("subject").get("method_parameter"));
		}
		List<IToken> elementNameParameterPair = methodCall.getAll("name_parameter_pair");
		if(elementNameParameterPair != null){
			for(IToken element:elementNameParameterPair){
				if((element.get("defined_operator") != null)){
					List<IToken> operatorDefinedOperator = element.getAll("defined_operator");
					if(operatorDefinedOperator != null){
						for(IToken operator:operatorDefinedOperator){
							for(IToken.Key atomKey:operator.keySet()){
								IToken atom = operator.get(atomKey);
								String currentName = atomKey.getName();
								ArrayList<IExecutable> parameters = new ArrayList<IExecutable>();
								for(IToken.Key quarkKey:atom.keySet()){
									if("syntax".equals(quarkKey.getName())){
										IToken quark = atom.get(quarkKey);
										parameters.add(new GetDataEntry(quark.getString()));
									}
									else {
										IToken quark = atom.get(quarkKey);
										parameters.add((IExecutable)Generators.classifier.generateMethodParameter(quark.get("method_parameter")));
									}
								}
								if((subject != null)){
									ret = new CallMethodFromScopeEntry(subject,currentName,parameters);
									subject = null;
								}
								else {
									ret = new CallMethodFromScopeEntry(ret,currentName,parameters);
								}
							}
						}
					}
				}
				else {
					String currentName = element.get("method_names").getString();
					ArrayList<IExecutable> parameters = new ArrayList<IExecutable>();
					List<IToken> parameterMethodParameter = element.getAll("method_parameter");
					if(parameterMethodParameter != null){
						for(IToken parameter:parameterMethodParameter){
							parameters.add((IExecutable)generateMethodParameter(parameter));
						}
					}
					currentName = Generators.classifier.buildString(currentName,"(",(Integer)parameters.size(),")");
					if((subject != null)){
						ret = new CallMethodFromScopeEntry(subject,currentName,parameters);
						subject = null;
					}
					else {
						ret = new CallMethodFromScopeEntry(ret,currentName,parameters);
					}
				}
			}
		}
		if((body == null)){
			return (Entry)ret;
		}
		else {
			body.addToBody(ret);
			return null;
		}
	}
	public Entry generateConstructorCall(IToken constructorCall,IExecutable body){
		String constructorName = null;
		String className = null;
		ArrayList<IExecutable> parameters = new ArrayList<IExecutable>();
		if((constructorCall.get("class_names") != null)){
			for(IToken.Key elementKey:constructorCall.keySet()){
				if("class_names".equals(elementKey.getName())){
					IToken element = constructorCall.get(elementKey);
					className = className.toString();
				}
				else if("methodParameter".equals(elementKey.getName())){
					IToken element = constructorCall.get(elementKey);
					parameters.add((IExecutable)generateMethodParameter(element));
				}
			}
			Integer parameterSize = (Integer)parameters.size();
			constructorName = Generators.classifier.buildString(className,"$(",parameterSize.toString(),")");
		}
		else {
			IToken definedMethod = constructorCall.get("defined_method");
			for(IToken.Key parameterTokensKey:definedMethod.keySet()){
				IToken parameterTokens = definedMethod.get(parameterTokensKey);
				constructorName = parameterTokensKey.getName();
				List<Integer> charArray = (List<Integer>)Generators.classifier.getCharArray(constructorName);
				Integer index = (Integer)constructorName.lastIndexOf("(");
				for(IToken.Key parameterKey:parameterTokens.keySet()){
					IToken parameter = parameterTokens.get(parameterKey);
					index = index + 1;
					if((charArray.get(index) == 118)){
						IExecutable methodParameter = (IExecutable)Generators.classifier.generateMethodParameter(parameter.get("method_parameter"));
						parameters.add(methodParameter);
					}
					else {
						if((charArray.get(index) == 115)){
							parameters.add(new GetDataEntry(parameter.getString()));
						}
					}
				}
			}
		}
		if((body == null)){
			return new CallConstructorEntry(className,constructorName,parameters);
		}
		else {
			body.addToBody(new CallConstructorEntry(className,constructorName,parameters));
			return null;
		}
	}
	public Entry generateMethodParameter(IToken methodParameter){
		for(IToken.Key parameterKey:methodParameter.keySet()){
			if("method_parameter".equals(parameterKey.getName())){
				IToken parameter = methodParameter.get(parameterKey);
				return generateMethodParameter(parameter);
			}
			else if("variable_names".equals(parameterKey.getName())){
				IToken parameter = methodParameter.get(parameterKey);
				return new GetVariableEntry(parameter.getString());
			}
			else if("constructor_call".equals(parameterKey.getName())){
				IToken parameter = methodParameter.get(parameterKey);
				return generateConstructorCall(parameter,null);
			}
			else if("method_call".equals(parameterKey.getName())){
				IToken parameter = methodParameter.get(parameterKey);
				return generateMethodCall(parameter,null);
			}
		}
		return null;
	}
	public Entry generateBodyElement(IToken bodyElement,IExecutable body){
		for(IToken.Key elementKey:bodyElement.keySet()){
			if("variable_declaration".equals(elementKey.getName())){
				IToken element = bodyElement.get(elementKey);
				generateVariableDeclaration(element,body);
			}
			else if("method_call".equals(elementKey.getName())){
				IToken element = bodyElement.get(elementKey);
				generateMethodCall(element,body);
			}
		}
		return null;
	}
	public Entry generateReturnCall(IToken returnCall,IExecutable body){
		List<IToken> elementMethodParameter = returnCall.getAll("method_parameter");
		if(elementMethodParameter != null){
			for(IToken element:elementMethodParameter){
				IExecutable parameter = (IExecutable)generateMethodParameter(element);
				if((body != null)){
					body.addToBody(new ExecuteReturnEntry(parameter));
					return null;
				}
				else {
					return new ExecuteReturnEntry(parameter);
				}
			}
		}
		return null;
	}

	public HashMap<String,ClassEntry> getClasses(){
		return classes;
	}

	public ClassEntry getDefaultContext(){
		return defaultContext;
	}

	public HashMap<String,String> getConstructorAssociations(){
		return constructorAssociations;
	}

	public String getName(){
		return "Classifier";
	}

		public void setup(ParseContext context){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}