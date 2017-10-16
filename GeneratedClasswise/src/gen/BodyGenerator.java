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

public class BodyGenerator extends Generator {

	private Integer exceptionIndex = 0;


	public BodyGenerator(){
	}
	public Entry generateElement(IToken element,Boolean isInner,ContextEntry parentContext){
		String semicolon = ";";
		for(IToken.Key atomKey:element.keySet()){
			if("body_manipulate".equals(atomKey.getName())){
				IToken atom = element.get(atomKey);
				return generateManipulate(atom,parentContext);
			}
			else if("body_line".equals(atomKey.getName())){
				IToken atom = element.get(atomKey);
				Entry in = null;
				for(IToken.Key quarkKey:atom.keySet()){
					if("variable_declaration".equals(quarkKey.getName())){
						IToken quark = atom.get(quarkKey);
						in = Generators.variable.generateDeclaration(quark,isInner);
					}
					else if("variable_assignment".equals(quarkKey.getName())){
						IToken quark = atom.get(quarkKey);
						in = Generators.variable.generateAssignment(quark,isInner);
					}
					else if("body_statement".equals(quarkKey.getName())){
						IToken quark = atom.get(quarkKey);
						in = generateStatement(quark,isInner);
					}
				}
				IInnerable inb = (IInnerable)in;
				if((inb.getIsInner())){
					return new IElementEntry("",in,semicolon,parentContext);
				}
				else {
					return new EElementEntry("",in,semicolon,parentContext);
				}
			}
			else if("body_return".equals(atomKey.getName())){
				IToken atom = element.get(atomKey);
				List<IToken> quarkMethodArgument = atom.getAll("method_argument");
				if(quarkMethodArgument != null){
					for(IToken quark:quarkMethodArgument){
						if((isInner == true || atom.get("inner") != null)){
							return new IElementEntry("return ",generateArgument(quark,true),semicolon,parentContext);
						}
						else {
							return new EElementEntry("return ",generateArgument(quark,false),semicolon,parentContext);
						}
					}
				}
				if((isInner == true || atom.get("inner") != null)){
					return new IElementEntry("return ",new IExactEntry(new StringEntry("")),semicolon,parentContext);
				}
				else {
					return new EElementEntry("return ",new EExactEntry(new StringEntry("")),semicolon,parentContext);
				}
			}
			else if("body_throw".equals(atomKey.getName())){
				IToken atom = element.get(atomKey);
				if((atom.get("inner") != null)){
					isInner = true;
				}
				Entry statement = (Entry)null;
				List<IToken> quarkBodyStatement = atom.getAll("body_statement");
				if(quarkBodyStatement != null){
					for(IToken quark:quarkBodyStatement){
						statement = Generators.body.generateStatement(quark,isInner);
					}
				}
				if((isInner == true || atom.get("inner") != null)){
					return new IElementEntry("throw ",new IExactEntry(new ElementEntry(ClasswiseGenerator.throwElement,new ListEntry(statement))),semicolon,parentContext);
				}
				else {
					return new EThrowEntry(statement,parentContext);
				}
			}
			else if("class_declaration".equals(atomKey.getName())){
				IToken atom = element.get(atomKey);
				EClassEntry aClass = (EClassEntry)Generators.clazz.generateDeclaration(atom,false,new ContextEntry());
				Entry argument = (Entry)aClass.getAsArgument();
				String variableName = Generators.body.buildString(aClass.getName(),"Class");
				Entry className = (Entry)aClass.getName();
				ImportListEntry classImport = (ImportListEntry)aClass.getImportPackage();
				Entry getPackageName = (Entry)classImport.getMyPackages();
				return new IBodyEntry(new ListEntry(new IElementEntry("",new IVariableEntry(new IExactEntry(new StringEntry("ExternalClassEntry")),new StringEntry(variableName),argument),semicolon,parentContext),new IElementEntry("",new ICallEntry(new IExactEntry(new StringEntry(variableName)),new StringEntry("__INIT__"),new ListEntry()),semicolon,parentContext),new IElementEntry("",new IVariableEntry(new IExactEntry(new StringEntry("StringBuilder")),new StringEntry("__BUILDER__"),null),semicolon,parentContext),new IElementEntry("",new IVariableEntry(new IExactEntry(new StringEntry("File")),new StringEntry("__DIRECTORY__"),null),semicolon,parentContext),new IElementEntry("",new IExactEntry(new ElementEntry(ClasswiseGenerator.addFileElement,new ListEntry(getPackageName,className,className))),"",parentContext)),parentContext);
			}
			else if("body_conditional".equals(atomKey.getName())){
				IToken atom = element.get(atomKey);
				if((isInner == true || atom.get("inner") != null)){
					isInner = true;
				}
				ContextEntry conditionalContext = new ContextEntry(parentContext);
				StringBuilder conditionalName = new StringBuilder();
				Boolean isCase = false;
				List<IToken> quarkConditional = atom.getAll("conditional");
				if(quarkConditional != null){
					for(IToken quark:quarkConditional){
						for(IToken.Key quantaKey:quark.keySet()){
							IToken quanta = quark.get(quantaKey);
							if((quanta.getString().contains("case"))){
								isCase = true;
							}
							conditionalName.append(quanta.getString());
							conditionalName.append(" ");
						}
					}
				}
				Entry statement = (Entry)null;
				List<IToken> quarkBodyStatement = atom.getAll("body_statement");
				if(quarkBodyStatement != null){
					for(IToken quark:quarkBodyStatement){
						statement = generateStatement(quark,isInner);
					}
				}
				List<IToken> quarkVariableDeclaration = atom.getAll("variable_declaration");
				if(quarkVariableDeclaration != null){
					for(IToken quark:quarkVariableDeclaration){
						String operator = atom.get("OPERATOR").getString();
						if((operator.contains(":"))){
							if((isInner == true)){
								statement = new IOperatorEntry(Generators.variable.generateDeclaration(quark,isInner),operator,statement);
							}
							else {
								statement = new EOperatorEntry(Generators.variable.generateDeclaration(quark,isInner),operator,statement);
							}
						}
						else {
							if((isInner == true)){
								statement = new IForIntHeaderEntry(Generators.variable.generateDeclaration(quark,isInner),operator,statement);
							}
							else {
								statement = new EForIntHeaderEntry(Generators.variable.generateDeclaration(quark,isInner),operator,statement);
							}
						}
					}
				}
				List<IToken> quarkException = atom.getAll("exception");
				if(quarkException != null){
					for(IToken quark:quarkException){
						String exceptionType = null;
						if((quark.getString().equals("*"))){
							exceptionType = "Exception";
						}
						else {
							exceptionType = Generators.body.buildString(Generator.camelize(quark.getString()),"Exception");
						}
						if((statement == null)){
							if((isInner == true)){
								statement = new IExactEntry(new StringEntry(exceptionType));
							}
							else {
								statement = new EExactEntry(new StringEntry(exceptionType));
							}
						}
						else {
							if((isInner == true)){
								statement = new IOperatorEntry(statement,"|",new IExactEntry(new StringEntry(exceptionType)));
							}
							else {
								statement = new EOperatorEntry(statement,"|",new EExactEntry(new StringEntry(exceptionType)));
							}
						}
					}
				}
				if((atom.get("exception") != null)){
					String exceptionVariable = Generators.body.buildString("e",exceptionIndex.toString());
					if((isInner == true)){
						statement = new IOperatorEntry(statement," ",new IExactEntry(new StringEntry(exceptionVariable)));
					}
					else {
						statement = new EOperatorEntry(statement," ",new EExactEntry(new StringEntry(exceptionVariable)));
					}
				}
				String coloncolon = ":";
				List<IToken> quarkAsBody = atom.getAll("as_body");
				if(quarkAsBody != null){
					for(IToken quark:quarkAsBody){
						ListEntry conditionalBody = new ListEntry();
						conditionalBody.setDelimiter("");
						if((atom.get("PRINT") != null)){
							String printTrace = Generators.body.buildString("e",exceptionIndex,".printStackTrace()");
							if((isInner == true)){
								conditionalBody.add(new IElementEntry("",new IExactEntry(new StringEntry(printTrace)),semicolon,parentContext));
							}
							else {
								conditionalBody.add(new EElementEntry("",new EExactEntry(new StringEntry(printTrace)),semicolon,parentContext));
							}
						}
						List<IToken> quantaBodyElement = quark.getAll("body_element");
						if(quantaBodyElement != null){
							for(IToken quanta:quantaBodyElement){
								Entry bodyElem = generateElement(quanta,isInner,conditionalContext);
								if((bodyElem != null)){
									conditionalBody.add(bodyElem);
								}
							}
						}
						if((isCase == true)){
							if((isInner == true)){
								return new IConditionalEntry(conditionalName.toString(),new IElementEntry("",statement,coloncolon,parentContext),conditionalBody,parentContext);
							}
							else {
								return new ECaseEntry(statement,conditionalBody,parentContext);
							}
						}
						else {
							if((isInner == true)){
								return new IConditionalEntry(conditionalName.toString(),statement,conditionalBody,parentContext);
							}
							else {
								return new EConditionalEntry(conditionalName.toString(),statement,conditionalBody,parentContext);
							}
						}
					}
				}
				List<IToken> quarkAsMethod = atom.getAll("as_method");
				if(quarkAsMethod != null){
					for(IToken quark:quarkAsMethod){
						Entry subject = (Entry)Generators.body.generateStatement(quark.get("body_statement"),true);
						if((isCase == true)){
							return new ECaseEntry(statement,new EInnerCallEntry(subject),parentContext);
						}
						else {
							return new EConditionalEntry(conditionalName.toString(),statement,new EInnerCallEntry(subject),parentContext);
						}
					}
				}
				if((atom.get("exception") != null)){
					exceptionIndex = exceptionIndex + 1;
				}
			}
		}
		return null;
	}
	public Entry generateStatement(IToken statement,Boolean isInner){
		if((isInner == false)){
			isInner = (statement.get("inner") != null);
		}
		Entry operand = (Entry)null;
		String operator = null;
		for(IToken.Key elementKey:statement.keySet()){
			if("as_string".equals(elementKey.getName())){
				IToken element = statement.get(elementKey);
				Entry subject = (Entry)Generators.body.generateStatement(element.get("body_statement"),isInner);
				IInnerable subjectAsInnerable = (IInnerable)subject;
				Boolean subjectIsInner = subjectAsInnerable.getIsInner();
				if((isInner == true || subjectIsInner == true)){
					return new ICallEntry(subject,new StringEntry("toString"),new ListEntry());
				}
				else {
					return new ECallEntry(subject,new StringEntry("toString"),new ListEntry());
				}
			}
			else if("as_braced".equals(elementKey.getName())){
				IToken element = statement.get(elementKey);
				Entry subject = (Entry)Generators.body.generateStatement(element.get("left").get("body_statement"),isInner);
				Entry oper = (Entry)null;
				if((element.get("OPERATOR") != null)){
					oper = new StringEntry(element.get("OPERATOR").getString());
				}
				Entry right = (Entry)null;
				if((element.get("right") != null)){
					right = Generators.body.generateStatement(element.get("right").get("body_statement"),isInner);
				}
				IInnerable subjectAsInnerable = (IInnerable)subject;
				Boolean subjectIsInner = subjectAsInnerable.getIsInner();
				if((isInner == true || subjectIsInner == true)){
					return new IBracedEntry(subject,oper,right);
				}
				else {
					return new EBracedEntry(subject,right);
				}
			}
			else if("body_call".equals(elementKey.getName())){
				IToken element = statement.get(elementKey);
				if((operand == null)){
					if((isInner == true)){
						operand = new IOperatorEntry(generateCall(element,true));
					}
					else {
						operand = new EOperatorEntry(generateCall(element,false));
					}
				}
				else {
					if((isInner == true)){
						operand = new IOperatorEntry(operand,operator,generateCall(element,true));
					}
					else {
						operand = new EOperatorEntry(operand,operator,generateCall(element,false));
					}
				}
			}
			else if("OPERATOR".equals(elementKey.getName())){
				IToken element = statement.get(elementKey);
				operator = element.getString();
				operator = operator.trim();
			}
		}
		return operand;
	}
	public Entry generateCall(IToken call,Boolean isInner){
		if((isInner == false)){
			if((call.get("inner") != null)){
				isInner = true;
			}
			else {
				isInner = false;
			}
		}
		Entry ret = (Entry)null;
		List<IToken> elementGroup = call.getAll("group");
		if(elementGroup != null){
			for(IToken element:elementGroup){
				ListEntry parameters = new ListEntry();
				ListEntry array_parameters = new ListEntry();
				Entry name_var = (Entry)null;
				if((isInner == true)){
					if((element.get("type_var") != null)){
						ITypeVarEntry tVar = new ITypeVarEntry();
						List<IToken> atomTypeVar = element.getAll("type_var");
						if(atomTypeVar != null){
							for(IToken atom:atomTypeVar){
								if((element.get("NEW") != null)){
									tVar.addSubClass(Generators.classwise.generateTypeVar(atom,isInner,2));
								}
								else {
									tVar.addSubClass(Generators.classwise.generateTypeVar(atom,true,2));
								}
							}
						}
						name_var = tVar;
					}
					else {
						if((element.get("typeName") != null)){
							ITypeVarEntry tVar = new ITypeVarEntry();
							List<IToken> atomTypeName = element.getAll("typeName");
							if(atomTypeName != null){
								for(IToken atom:atomTypeName){
									if((element.get("NEW") != null)){
										tVar.addSubClass(Generators.classwise.generateAllType(atom,isInner));
									}
									else {
										tVar.addSubClass(Generators.classwise.generateAllType(atom,true));
									}
								}
							}
							name_var = tVar;
						}
						else {
							List<IToken> atomNameVar = element.getAll("name_var");
							if(atomNameVar != null){
								for(IToken atom:atomNameVar){
									name_var = Generators.classwise.generateNameVar(atom,true);
								}
							}
							List<IToken> atomNAME = element.getAll("NAME");
							if(atomNAME != null){
								for(IToken atom:atomNAME){
									name_var = new IExactEntry(new StringEntry(atom.getString()));
								}
							}
						}
					}
					Boolean containsParameters = false;
					List<IToken> quarkParameters = element.getAll("parameters");
					if(quarkParameters != null){
						for(IToken quark:quarkParameters){
							containsParameters = true;
							List<IToken> atomMethodArgument = quark.getAll("method_argument");
							if(atomMethodArgument != null){
								for(IToken atom:atomMethodArgument){
									parameters.add(generateArgument(atom,true));
								}
							}
						}
					}
					Boolean containsArrayParameters = false;
					List<IToken> quarkArrayParameters = element.getAll("array_parameters");
					if(quarkArrayParameters != null){
						for(IToken quark:quarkArrayParameters){
							containsArrayParameters = true;
							List<IToken> atomMethodArgument = quark.getAll("method_argument");
							if(atomMethodArgument != null){
								for(IToken atom:atomMethodArgument){
									array_parameters.add(generateArgument(atom,true));
								}
							}
						}
					}
					if((element.get("NEW") != null)){
						if((containsParameters == true)){
							if((containsArrayParameters == true)){
								ret = new INewObjEntry(name_var,parameters,array_parameters);
							}
							else {
								ret = new INewObjEntry(name_var,parameters,null);
							}
						}
						else {
							if((containsArrayParameters == true)){
								ret = new INewObjEntry(name_var,null,array_parameters);
							}
							else {
								ret = new INewObjEntry(name_var,null,null);
							}
						}
					}
					else {
						if((ret == null)){
							if((containsParameters == true)){
								if((containsArrayParameters == true)){
									ret = new ICallEntry(name_var,parameters,array_parameters);
								}
								else {
									ret = new ICallEntry(name_var,parameters);
								}
							}
							else {
								if((containsArrayParameters == true)){
									ret = new ICallEntry(name_var,null,array_parameters);
								}
								else {
									ret = new ICallEntry(name_var);
								}
							}
						}
						else {
							if((containsParameters == true)){
								if((containsArrayParameters == true)){
									ret = new ICallEntry(ret,name_var,parameters,array_parameters);
								}
								else {
									ret = new ICallEntry(ret,name_var,parameters);
								}
							}
							else {
								if((containsArrayParameters == true)){
									ret = new ICallEntry(ret,name_var,null,array_parameters);
								}
								else {
									ret = new ICallEntry(ret,name_var);
								}
							}
						}
					}
				}
				else {
					if((element.get("type_var") != null)){
						ETypeVarEntry tVar = new ETypeVarEntry();
						List<IToken> atomTypeVar = element.getAll("type_var");
						if(atomTypeVar != null){
							for(IToken atom:atomTypeVar){
								tVar.addSubClass(Generators.classwise.generateTypeVar(atom,false,2));
							}
						}
						name_var = tVar;
					}
					else {
						if((element.get("typeName") != null)){
							ETypeVarEntry tVar = new ETypeVarEntry();
							List<IToken> atomTypeName = element.getAll("typeName");
							if(atomTypeName != null){
								for(IToken atom:atomTypeName){
									tVar.addSubClass(Generators.classwise.generateAllType(atom,false));
								}
							}
							name_var = tVar;
						}
						else {
							List<IToken> atomNameVar = element.getAll("name_var");
							if(atomNameVar != null){
								for(IToken atom:atomNameVar){
									name_var = Generators.classwise.generateNameVar(atom,false);
								}
							}
							List<IToken> atomNAME = element.getAll("NAME");
							if(atomNAME != null){
								for(IToken atom:atomNAME){
									name_var = new EEntryEntry(new QuoteEntry(atom.getString()));
								}
							}
						}
					}
					Boolean containsParameters = false;
					List<IToken> quarkParameters = element.getAll("parameters");
					if(quarkParameters != null){
						for(IToken quark:quarkParameters){
							containsParameters = true;
							List<IToken> atomMethodArgument = quark.getAll("method_argument");
							if(atomMethodArgument != null){
								for(IToken atom:atomMethodArgument){
									parameters.add(generateArgument(atom,false));
								}
							}
						}
					}
					Boolean containsArrayParameters = false;
					List<IToken> quarkArrayParameters = element.getAll("array_parameters");
					if(quarkArrayParameters != null){
						for(IToken quark:quarkArrayParameters){
							containsArrayParameters = true;
							List<IToken> atomMethodArgument = quark.getAll("method_argument");
							if(atomMethodArgument != null){
								for(IToken atom:atomMethodArgument){
									array_parameters.add(generateArgument(atom,false));
								}
							}
						}
					}
					if((element.get("NEW") != null)){
						if((containsParameters == true)){
							if((containsArrayParameters == true)){
								ret = new ENewObjEntry(name_var,parameters,array_parameters);
							}
							else {
								ret = new ENewObjEntry(name_var,parameters);
							}
						}
						else {
							if((containsArrayParameters == true)){
								ret = new ENewObjEntry(name_var,null,array_parameters);
							}
							else {
								ret = new ENewObjEntry(name_var,parameters);
							}
						}
					}
					else {
						if((ret == null)){
							if((containsParameters == true)){
								if((containsArrayParameters == true)){
									ret = new ECallEntry(name_var,parameters,array_parameters);
								}
								else {
									ret = new ECallEntry(name_var,parameters);
								}
							}
							else {
								if((containsArrayParameters == true)){
									ret = new ECallEntry(name_var,null,array_parameters);
								}
								else {
									ret = new ECallEntry(name_var);
								}
							}
						}
						else {
							if((containsParameters == true)){
								if((containsArrayParameters == true)){
									ret = new ECallEntry(ret,name_var,parameters,array_parameters);
								}
								else {
									ret = new ECallEntry(ret,name_var,parameters);
								}
							}
							else {
								if((containsArrayParameters == true)){
									ret = new ECallEntry(ret,name_var,null,array_parameters);
								}
								else {
									ret = new ECallEntry(ret,name_var);
								}
							}
						}
					}
				}
			}
		}
		return ret;
	}
	public Entry generateManipulate(IToken manipulate,ContextEntry parentContext){
		if((manipulate.get("type_var") != null)){
			Entry type = null;
			Entry realType = null;
			Entry access = (Entry)new ListEntry();
			ListEntry arguments = new ListEntry();
			ImportListEntry importList = (ImportListEntry)null;
			List<IToken> elementTypeVar = manipulate.getAll("type_var");
			if(elementTypeVar != null){
				for(IToken element:elementTypeVar){
					type = Generators.classwise.generateTypeVar(element,true,0);
					realType = Generators.classwise.generateTypeVar(element,true,3);
					StringBuilder typeBuilder = new StringBuilder();
					type.get(typeBuilder);
					IImportable getType = (IImportable)Generators.classwise.getType(typeBuilder.toString());
					importList = getType.getImportPackage();
					IVariablizable typeAsVariablizable = (IVariablizable)type;
					typeAsVariablizable.setIsVariable(true);
				}
			}
			List<IToken> elementNameVar = manipulate.getAll("name_var");
			if(elementNameVar != null){
				for(IToken element:elementNameVar){
					access = Generators.classwise.generateNameVar(element,true);
				}
			}
			Integer arg_type = -1;
			for(IToken.Key atomKey:manipulate.keySet()){
				if("class_declaration".equals(atomKey.getName())){
					IToken atom = manipulate.get(atomKey);
					EClassEntry aClass = (EClassEntry)Generators.clazz.generateDeclaration(atom,false,new ContextEntry());
					arguments.add(aClass.getAsArgument());
					aClass.setIsSubClass(type,realType,true);
					importList.addImports(aClass.getImportPackage());
					arg_type = 0;
				}
				else if("method_declaration".equals(atomKey.getName())){
					IToken atom = manipulate.get(atomKey);
					Entry aMethod = (Entry)Generators.method.generateDeclaration(atom,false,new ContextEntry(new ContextEntry()));
					arguments.add(aMethod);
					arg_type = 1;
				}
				else if("variable_declaration".equals(atomKey.getName())){
					IToken atom = manipulate.get(atomKey);
					Entry aVariable = (Entry)Generators.variable.generateDeclaration(atom,false);
					arguments.add(aVariable);
					arg_type = 2;
				}
				else if("body".equals(atomKey.getName())){
					IToken atom = manipulate.get(atomKey);
					List<IToken> quarkBodyElement = atom.getAll("body_element");
					if(quarkBodyElement != null){
						for(IToken quark:quarkBodyElement){
							Entry bodyelem = (Entry)generateElement(quark,false,new ContextEntry(new ContextEntry(new ContextEntry())));
							if((bodyelem != null)){
								arguments.add(bodyelem);
							}
						}
					}
					arg_type = 3;
				}
			}
			if((manipulate.get("methodName").getString().equals("+="))){
				if((arg_type == 0)){
					return new ManObjEntry(type,"addSubClass",arguments);
				}
				if((arg_type == 1)){
					return new ManObjEntry(type,"addMethod",arguments);
				}
				if((arg_type == 2)){
					return new ManObjEntry(type,"addVariable",arguments);
				}
				if((arg_type == 3)){
					return new ManObjEntry(type,"appendToBody",arguments);
				}
				return new ManObjEntry(type,"add",arguments);
			}
			else {
				return new ManObjEntry(type,manipulate.get("methodName").getString(),arguments);
			}
		}
		else {
			Entry subject = null;
			String elementName = null;
			if((manipulate.get("variableName") != null)){
				elementName = manipulate.get("variableName").getString();
			}
			List<IToken> elementNameVar = manipulate.getAll("name_var");
			if(elementNameVar != null){
				for(IToken element:elementNameVar){
					subject = Generators.classwise.generateNameVar(element,true);
				}
			}
			ListEntry man_body = new ListEntry();
			man_body.setDelimiter("");
			List<IToken> elementTokenInstance = manipulate.getAll("tokenInstance");
			if(elementTokenInstance != null){
				for(IToken element:elementTokenInstance){
					String tokenName = element.get("tokenName").getString();
					ListEntry instance_body = new ListEntry();
					instance_body.setDelimiter("");
					ContextEntry bodyContext = new ContextEntry(parentContext);
					List<IToken> atomBodyElement = element.getAll("body_element");
					if(atomBodyElement != null){
						for(IToken atom:atomBodyElement){
							Entry bodyElem = (Entry)generateElement(atom,true,bodyContext);
							if((bodyElem != null)){
								instance_body.add(bodyElem);
							}
						}
					}
					man_body.add(new ManTokInstEntry(subject,elementName,tokenName,instance_body,bodyContext));
				}
			}
			return new ManTokEntry(subject,elementName,man_body,parentContext);
		}
	}
	public Entry generateArgument(IToken argument,Boolean isInner){
		for(IToken.Key elementKey:argument.keySet()){
			if("class_declaration".equals(elementKey.getName())){
				IToken element = argument.get(elementKey);
				return Generators.clazz.generateDeclaration(element,false,new ContextEntry());
			}
			else if("method_declaration".equals(elementKey.getName())){
				IToken element = argument.get(elementKey);
				return Generators.method.generateDeclaration(element,false,new ContextEntry(new ContextEntry()));
			}
			else if("variable_declaration".equals(elementKey.getName())){
				IToken element = argument.get(elementKey);
				return Generators.variable.generateDeclaration(element,false);
			}
			else if("body_statement".equals(elementKey.getName())){
				IToken element = argument.get(elementKey);
				return generateStatement(element,isInner);
			}
			else if("as_statement".equals(elementKey.getName())){
				IToken element = argument.get(elementKey);
				ListEntry body = new ListEntry();
				List<IToken> atomBodyElement = element.getAll("body_element");
				if(atomBodyElement != null){
					for(IToken atom:atomBodyElement){
						Entry bodyElem = generateElement(atom,false,new ContextEntry(new ContextEntry(new ContextEntry())));
						if((bodyElem != null)){
							body.add(bodyElem);
						}
					}
				}
				List<IToken> atomBodyStatement = element.getAll("body_statement");
				if(atomBodyStatement != null){
					for(IToken atom:atomBodyStatement){
						return generateStatement(atom,false);
					}
				}
				return new EInnerCallEntry(body);
			}
			else if("body_entries".equals(elementKey.getName())){
				IToken element = argument.get(elementKey);
				ListEntry elements = new ListEntry();
				elements.setDelimiter("");
				List<IToken> atomBodyElement = element.getAll("body_element");
				if(atomBodyElement != null){
					for(IToken atom:atomBodyElement){
						Entry bodyelem = (Entry)Generators.body.generateElement(atom,false,new ContextEntry(new ContextEntry(new ContextEntry())));
						if((bodyelem != null)){
							elements.add(bodyelem);
						}
					}
				}
				return new EBodyEntry(elements);
			}
		}
		return null;
	}

	public Integer getExceptionIndex(){
		return exceptionIndex;
	}

	public String getName(){
		return "Body";
	}

		public void generateRoot(IToken root){
	}

		public void generate(ParseContext data){
	}

		public void setup(ParseContext context){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}