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

public class ClasswiseGenerator extends Generator {

	private String nxtPackageName = null;
	private String inrPackageName = null;
	private File inDir = (File)null;
	private File nrDir = (File)null;
	private HashMap<String,Entry> innerEntries = new HashMap<String,Entry>();
	private HashMap<String,Entry> nextEntries = new HashMap<String,Entry>();
	private ArrayList<EClassEntry> nextClasses = new ArrayList<EClassEntry>();
	private HashMap<String,IImportable> importables = new HashMap<String,IImportable>();
	private ListEntry addClassFileList = new ListEntry();
	private ListEntry setupClassList = new ListEntry();
	private IClassEntry main_class = (IClassEntry)null;


	public static final Element exactElement = new Element("exact",new String[]{"",/*In*/""});
	public static final Element returnCallElement = new Element("returnCall",new String[]{"return ",/*Statement*/";"});
	public static final Element throwElement = new Element("throw",new String[]{"new RuntimeException(",/*Name Var*/")"});
	public static final Element semicolonedElement = new Element("semicoloned",new String[]{"",/*Statement*/";"});
	public static final Element asImportElement = new Element("asImport",new String[]{"import ",/*Package*/".",/*Name*/";"});
	public static final Element asExternalImportElement = new Element("asExternalImport",new String[]{"__BUILDER__.append(\"\\nimport \");",/*Package*/".get(__BUILDER__);__BUILDER__.append(\".\");new StringEntry(",/*Name*/").get(__BUILDER__);__BUILDER__.append(\";\");"});
	public static final Element concatElement = new Element("concat",new String[]{"",/*Left*/"+",/*Right*/""});
	public static final Element accessElement = new Element("access",new String[]{"",/*Left*/".get(",/*Right*/")"});
	public static final Element accessDefaultElement = new Element("accessDefault",new String[]{"",/*Left*/".",/*Right*/""});
	public static final Element accessClassElement = new Element("accessClass",new String[]{"",/*Left*/".getSubClass(",/*Right*/")"});
	public static final Element accessMethodElement = new Element("accessMethod",new String[]{"",/*Left*/".getMethod(",/*Right*/")"});
	public static final Element accessVariableElement = new Element("accessVariable",new String[]{"",/*Left*/".getVariable(",/*Right*/")"});
	public static final Element classAsVariableElement = new Element("classAsVariable",new String[]{"",/*Class Name*/"Class"});
	public static final Element retrieveVariableElement = new Element("retrieveVariable",new String[]{"MainFlow.variables.get_",/*Variable Name*/"()"});
	public static final Element retrieveMethodElement = new Element("retrieveMethod",new String[]{"MainFlow.methods.",/*Method Name*/""});
	public static final Element retrieveClassElement = new Element("retrieveClass",new String[]{"MainFlow.classes.",/*Class Name*/"Class"});
	public static final Element variableAsGlobalElement = new Element("variableAsGlobal",new String[]{"__VAR__",/*Name*/""});
	public static final Element extendsElement = new Element("extends",new String[]{"extends ",/*Parent Class*/""});
	public static final Element implementsElement = new Element("implements",new String[]{"implements ",/*Parent Class*/""});
	public static final Element generatorsElement = new Element("generators",new String[]{"new Generator[]{"+
			"\n		new Generator(){"+
			"\n			public String getName(){"+
			"\n				return \"Main\";"+
			"\n			}"+
			"\n			public void setup(ParseContext data){"+
			"\n			}"+
			"\n			public void generate(ParseContext data){"+
			"\n				StringBuilder __BUILDER__;"+
			"\n				File __DIRECTORY__;"+
			"\n				",/*Class Wise Add File*/""+
			"\n				outputAll();"+
			"\n			}"+
			"\n			public void generateRoot(IToken rootToken){"+
			"\n			}"+
			"\n		}};"+
			"\n	}"+
			"\n	public static String __ROOT_DIRECTORY__ = \".\";"+
			"\n	@Override"+
			"\n	public void initializeFlowController(){"+
			"\n	  MainFlow.variables = this;"+
			"\n	  MainFlow.methods = this;"+
			"\n	}"+
			"\n	public void setup(String rootDirectory){"+
			"\n		__ROOT_DIRECTORY__ = rootDirectory;"+
			"\n		",/*Setup All classes*/""+
			"\n	}"+
			"\n	public void output(ParseContext data){"+
			"\n		ExternalImportEntry.solidify();"+
			"\n		getGenerators()[0].generate(data);"+
			"\n		for(Generator gen:privateFiles){"+
			"\n			gen.outputAll();"+
			"\n		}"+
			"\n		System.out.println(\"Output Complete\")"});
	public static final Element addFileElement = new Element("addFile",new String[]{"__BUILDER__ = new StringBuilder();"+
			"\n	",/*Package Name*/".get(__BUILDER__);"+
			"\n	__DIRECTORY__ = new File(__ROOT_DIRECTORY__, __BUILDER__.toString().replace(\".\",\"/\"));"+
			"\n	__DIRECTORY__.mkdirs();"+
			"\n	addFile(__DIRECTORY__,FlowController.camelize(",/*Class Name*/")+\".java\", MainFlow.classes.",/*Class Name*/"Class);"});
	public static final Element addFileInMethodElement = new Element("addFileInMethod",new String[]{"__BUILDER__ = new StringBuilder();"+
			"\n	",/*Package Name*/".get(__BUILDER__);"+
			"\n	__DIRECTORY__ = new File(MainFlow.__ROOT_DIRECTORY__, __BUILDER__.toString().replace(\".\",\"/\"));"+
			"\n	__DIRECTORY__.mkdirs();"+
			"\n	MainFlow.methods.addFile(__DIRECTORY__,FlowController.camelize(",/*Class Name*/")+\".java\", ",/*Class Name*/"Class);"});
	public static final Element setupClassElement = new Element("setupClass",new String[]{"MainFlow.classes.",/*Class Name*/"Class.__INIT__();"});
	public static final Element addAnonymousClassElement = new Element("addAnonymousClass",new String[]{"ExternalClassEntry.suppliment(\"",/*ClassName*/"\", \"",/*PackageName*/"\");"});
	public ClasswiseGenerator(){
		addElement("exact",exactElement);
		addElement("returnCall",returnCallElement);
		addElement("throw",throwElement);
		addElement("semicoloned",semicolonedElement);
		addElement("asImport",asImportElement);
		addElement("asExternalImport",asExternalImportElement);
		addElement("concat",concatElement);
		addElement("access",accessElement);
		addElement("accessDefault",accessDefaultElement);
		addElement("accessClass",accessClassElement);
		addElement("accessMethod",accessMethodElement);
		addElement("accessVariable",accessVariableElement);
		addElement("classAsVariable",classAsVariableElement);
		addElement("retrieveVariable",retrieveVariableElement);
		addElement("retrieveMethod",retrieveMethodElement);
		addElement("retrieveClass",retrieveClassElement);
		addElement("variableAsGlobal",variableAsGlobalElement);
		addElement("extends",extendsElement);
		addElement("implements",implementsElement);
		addElement("generators",generatorsElement);
		addElement("addFile",addFileElement);
		addElement("addFileInMethod",addFileInMethodElement);
		addElement("setupClass",setupClassElement);
		addElement("addAnonymousClass",addAnonymousClassElement);
	}
	public void setup(ParseContext data){
		this.addPage();
		inrPackageName = "clgen";
		nxtPackageName = "clent";
		String fileName = data.getFileName();
		Integer indexOfDot = (Integer)fileName.lastIndexOf(".");
		if((indexOfDot > -1)){
			fileName = fileName.substring(0,indexOfDot);
		}
		inDir = new File(Generators.classwise.buildString("../Generated",fileName,"/src"),inrPackageName);
		nrDir = new File(Generators.classwise.buildString("../Generated",fileName,"/src"),nxtPackageName);
		inDir.mkdirs();
		nrDir.mkdirs();
	}
	public Entry getInternalImports(){
		ImportListEntry mainClassImports = (ImportListEntry)main_class.getImportPackage();
		return (Entry)mainClassImports.get("INTERNAL");
	}
	public Entry generateAll(IToken all){
		List<IToken> elementImports = all.getAll("imports");
		if(elementImports != null){
			for(IToken element:elementImports){
				generateAll(element);
			}
		}
		List<IToken> elementIMPORTCLASS = all.getAll("IMPORT_CLASS");
		if(elementIMPORTCLASS != null){
			for(IToken element:elementIMPORTCLASS){
				generateAll(element);
			}
		}
		List<IToken> elementAnonymousClass = all.getAll("anonymous_class");
		if(elementAnonymousClass != null){
			for(IToken element:elementAnonymousClass){
				ListEntry packageName = new ListEntry();
				packageName.setDelimiter(".");
				String className = element.get("className").getString();
				ContextEntry context = new ContextEntry(null);
				List<IToken> atomPackageName = element.getAll("packageName");
				if(atomPackageName != null){
					for(IToken atom:atomPackageName){
						List<IToken> quarkNameVar = atom.getAll("name_var");
						if(quarkNameVar != null){
							for(IToken quark:quarkNameVar){
								packageName.add(generateNameVar(quark,true));
							}
						}
					}
				}
				ImportListEntry mainClassImport = (ImportListEntry)main_class.getImportPackage();
				mainClassImport.addPackage(new ISingleImportEntry(new ListEntry(packageName),new ListEntry(new StringEntry(className))));
				importables.put(className,new ImportHolderEntry(packageName,new StringEntry(className)));
				setupClassList.add(new ElementEntry(ClasswiseGenerator.addAnonymousClassElement,new ListEntry(new StringEntry(className),packageName)));
			}
		}
		List<IToken> elementClassDeclaration = all.getAll("class_declaration");
		if(elementClassDeclaration != null){
			for(IToken element:elementClassDeclaration){
				Entry newCl = (Entry)Generators.clazz.generateDeclaration(element,false,null);
				IInnerable newIn = (IInnerable)newCl;
				INameable newNa = (INameable)newCl;
				if((newIn.getIsInner())){
					innerEntries.put(newNa.getFullName(),newCl);
				}
				else {
					EClassEntry newClass = (EClassEntry)newCl;
					Entry className = (Entry)newClass.getName();
					newClass.setIsGlobal(true);
					Generators.classwise.addFile(inDir,Generators.classwise.buildString(newClass.getName(),".java"),newClass.getAsInternalFile());
					main_class.addClass(newClass);
					ImportListEntry classImport = (ImportListEntry)newClass.getImportPackage();
					Entry getPackageName = (Entry)classImport.getMyPackages();
					StringBuilder classNameBuilder = new StringBuilder();
					className.get(classNameBuilder);
					String classNameAsString = classNameBuilder.toString();
					addClassFileList.add(new ElementEntry(ClasswiseGenerator.addFileElement,new ListEntry(getPackageName,new QuoteEntry(classNameAsString),className)));
					setupClassList.add(new ElementEntry(ClasswiseGenerator.setupClassElement,new ListEntry(className)));
				}
			}
		}
		ContextEntry methodContext = new ContextEntry(new ContextEntry());
		List<IToken> elementMethodDeclaration = all.getAll("method_declaration");
		if(elementMethodDeclaration != null){
			for(IToken element:elementMethodDeclaration){
				IMethodEntry newMt = (IMethodEntry)Generators.method.generateDeclaration(element,true,methodContext);
				main_class.addMethod(newMt);
				Generators.method.addDefinedMethodName(newMt.getName());
			}
		}
		ContextEntry variableContext = new ContextEntry(new ContextEntry());
		List<IToken> elementVariableDeclaration = all.getAll("variable_declaration");
		if(elementVariableDeclaration != null){
			for(IToken element:elementVariableDeclaration){
				IVariableEntry newVa = (IVariableEntry)Generators.variable.generateDeclaration(element,true,variableContext);
				newVa.setIsGlobal();
				main_class.addVariable(newVa);
				Generators.variable.addDefinedVariableName(newVa.getRealName());
			}
		}
		return null;
	}
	public void generate(ParseContext data){
		String semicolon = ";";
		IMethodEntry mainMethod = new IMethodEntry(new ITypeVarEntry(new StringEntry("void")),new INameVarEntry(new StringEntry("main")),new ListEntry(new IVariableEntry(new ITypeVarEntry(new StringEntry("String[]")),new INameVarEntry(new StringEntry("args")),null)),new ListEntry(),new ListEntry(new IConditionalEntry("if",new IExactEntry(new StringEntry("args.length==1")),new ListEntry(new IElementEntry("",new IExactEntry(new StringEntry("new MainFlow().parse(args[0])")),semicolon,new ContextEntry())),new ContextEntry()),new IConditionalEntry("else",null,new ListEntry(new IElementEntry("",new IExactEntry(new StringEntry("System.err.println(\"No filename provided!\")")),semicolon,new ContextEntry())),new ContextEntry())),new ContextEntry(new ContextEntry()));
		mainMethod.setIsStatic(true);
		main_class = new IClassEntry(new StringEntry(inrPackageName),"class",new StringEntry("MainFlow"),new ITypeVarEntry(new StringEntry("FlowController")),new ListEntry(),new ListEntry(),new ListEntry(mainMethod),new ContextEntry());
		Generators.classwise.generateAll(data.getRoot());
		Set<String> inKeySet = (Set<String>)innerEntries.keySet();
		for(String key:inKeySet){
			Generators.classwise.addFile(inDir,Generators.classwise.buildString(Generator.camelize(key.toString()),".java"),innerEntries.get(key));
		}
		Generators.classwise.addFile(inDir,Generators.classwise.buildString("MainFlow.java"),main_class);
		for(EClassEntry nextClass:nextClasses){
			Generators.classwise.addFile(nrDir,Generators.classwise.buildString(nextClass.getFullName(),".java"),nextClass);
		}
		addClassFileList.setDelimiter("\n\t\t\t\t");
		setupClassList.setDelimiter("\n\t\t");
		main_class.addMethod(new IMethodEntry(new ITypeVarEntry(new StringEntry("Generator[]")),new INameVarEntry(new StringEntry("getGenerators")),new ListEntry(),new ListEntry(),new ListEntry(new IElementEntry("",new IVariableEntry(new ITypeVarEntry(new StringEntry("MainFlow")),new INameVarEntry(new StringEntry("self")),new IExactEntry(new StringEntry("this"))),semicolon,new ContextEntry()),new IElementEntry("return ",new IExactEntry(new ElementEntry(ClasswiseGenerator.generatorsElement,new ListEntry(addClassFileList,setupClassList))),semicolon,new ContextEntry())),new ContextEntry(new ContextEntry())));
	}
	public Entry generateNameVar(IToken name_var,Boolean isInner){
		for(IToken.Key elementKey:name_var.keySet()){
			if("access".equals(elementKey.getName())){
				IToken element = name_var.get(elementKey);
				Entry left = (Entry)null;
				Entry right = (Entry)null;
				for(IToken.Key atomKey:element.keySet()){
					if("name_var".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						if((left == null)){
							left = generateNameVar(atom,isInner);
						}
						else {
							if((right == null)){
								right = generateNameVar(atom,isInner);
							}
							else {
								if((isInner == true)){
									left = new INameVarEntry(left,right);
								}
								else {
									left = new ENameVarEntry(left,right);
								}
								right = generateNameVar(atom,isInner);
							}
						}
					}
				}
				if((isInner == true)){
					if((element.get("CAMEL") != null)){
						return new INameVarEntry(new ElementEntry(InternalGenerator.camelizeElement,new ListEntry(new INameVarEntry(left,right))));
					}
					else {
						return new INameVarEntry(left,right);
					}
				}
				else {
					if((element.get("CAMEL") != null)){
						return new ENameVarEntry(new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(new ENameVarEntry(left,right))));
					}
					else {
						return new ENameVarEntry(left,right);
					}
				}
			}
			else if("concat".equals(elementKey.getName())){
				IToken element = name_var.get(elementKey);
				Entry left = (Entry)null;
				Entry right = (Entry)null;
				for(IToken.Key atomKey:element.keySet()){
					if("name_var".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						if((left == null)){
							left = generateNameVar(atom,isInner);
						}
						else {
							if((right == null)){
								right = generateNameVar(atom,isInner);
							}
							else {
								if((isInner == true)){
									left = new INameVarEntry(left,"+",right);
								}
								else {
									left = new ENameVarEntry(left,"+",right);
								}
								right = generateNameVar(atom,isInner);
							}
						}
					}
				}
				if((isInner == true)){
					if((element.get("CAMEL") != null)){
						return new INameVarEntry(new ElementEntry(InternalGenerator.camelizeElement,new ListEntry(new INameVarEntry(left,"+",right))));
					}
					else {
						return new INameVarEntry(left,"+",right);
					}
				}
				else {
					if((element.get("CAMEL") != null)){
						return new ENameVarEntry(new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(new ENameVarEntry(left,"+",right))));
					}
					else {
						return new ENameVarEntry(left,"+",right);
					}
				}
			}
			else if("as_method".equals(elementKey.getName())){
				IToken element = name_var.get(elementKey);
				Entry subject = (Entry)Generators.body.generateStatement(element.get("body_statement"),true,new ContextEntry());
				if((isInner == true)){
					return new IExactEntry(new StringEntry("\"$AS METHOD NOT IMPLEMENTED FOR INNER$\""));
				}
				else {
					return new EInnerCallEntry(subject);
				}
			}
			else if("as_quote".equals(elementKey.getName())){
				IToken element = name_var.get(elementKey);
				Entry subject = (Entry)Generators.body.generateStatement(element.get("body_statement"),true,new ContextEntry());
				if((isInner == true)){
					return new IExactEntry(new StringEntry("\"$AS QUOTE NOT IMPLEMENTED FOR INNER$\""));
				}
				else {
					return new EQuoteEntry(new ICallEntry(subject,new StringEntry("toString"),new ListEntry()));
				}
			}
			else if("as_string".equals(elementKey.getName())){
				IToken element = name_var.get(elementKey);
				Entry subject = (Entry)Generators.body.generateStatement(element.get("body_statement"),true,new ContextEntry());
				if((isInner == true)){
					return new ICallEntry(subject,new StringEntry("toString"),new ListEntry());
				}
				else {
					return new EEntryEntry(new ICallEntry(subject,new StringEntry("toString"),new ListEntry()));
				}
			}
			else if("exact".equals(elementKey.getName())){
				IToken element = name_var.get(elementKey);
				Entry value = null;
				if((isInner == true)){
					if((element.get("WILD") != null)){
						if((element.get("CAMEL") != null)){
							value = new QuoteEntry(Generator.camelize(element.get("WILD").getString()));
						}
						else {
							value = new QuoteEntry(element.get("WILD").getString());
						}
					}
					else {
						if((element.get("variable_names") != null)){
							if((element.get("CAMEL") != null)){
								return new IExactEntry(new ElementEntry(InternalGenerator.camelizeElement,new ListEntry(new StringEntry(element.get("variable_names").getString()))));
							}
							else {
								value = new QuoteEntry(element.get("variable_names").getString());
							}
						}
						else {
							if((element.get("CAMEL") != null)){
								value = new StringEntry(Generator.camelize(element.get("NAME").getString()));
							}
							else {
								value = new StringEntry(element.get("NAME").getString());
							}
						}
					}
					return new INameVarEntry(value);
				}
				else {
					if((element.get("WILD") != null)){
						if((element.get("CAMEL") != null)){
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(Generator.camelize(element.get("WILD").getString()))));
						}
						else {
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(element.get("WILD").getString())));
						}
					}
					else {
						if((element.get("variable_names") != null)){
							if((element.get("CAMEL") != null)){
								value = new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(new StringEntry(element.get("variable_names").getString())));
							}
							else {
								value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new StringEntry(element.get("variable_names").getString())));
							}
						}
						else {
							if((element.get("CAMEL") != null)){
								value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(Generator.camelize(element.get("NAME").getString()))));
							}
							else {
								value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(element.get("NAME").getString())));
							}
						}
					}
					return new ENameVarEntry(value);
				}
			}
			else if("variable".equals(elementKey.getName())){
				IToken element = name_var.get(elementKey);
				Entry value = (Entry)null;
				if((isInner == true)){
					if((element.get("CAMEL") != null)){
						if((element.get("class_variable_names") != null)){
							value = new ElementEntry(InternalGenerator.camelizeElement,new ListEntry(new StringEntry(element.get("class_variable_names").getString())));
						}
						else {
							value = new ElementEntry(InternalGenerator.camelizeElement,new ListEntry(new StringEntry(element.get("variable_names").getString())));
						}
					}
					else {
						if((element.get("class_variable_names") != null)){
							value = new StringEntry(element.get("class_variable_names").getString());
						}
						else {
							value = new StringEntry(element.get("variable_names").getString());
						}
					}
					return new INameVarEntry(value);
				}
				else {
					if((element.get("CAMEL") != null)){
						if((element.get("class_variable_names") != null)){
							value = new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(new QuoteEntry(element.get("class_variable_names").getString())));
						}
						else {
							value = new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(new QuoteEntry(element.get("variable_names").getString())));
						}
					}
					else {
						if((element.get("class_variable_names") != null)){
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(element.get("class_variable_names").getString())));
						}
						else {
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(element.get("variable_names").getString())));
						}
					}
					return new ENameVarEntry(value);
				}
			}
		}
		return null;
	}
	public Entry generateTypeVar(IToken type_var,Boolean isInner,Integer accessIndex,ContextEntry parentContext){
		for(IToken.Key elementKey:type_var.keySet()){
			if("access_multi".equals(elementKey.getName())){
				IToken element = type_var.get(elementKey);
				Entry ret = (Entry)null;
				ITypeVarEntry previousType = (ITypeVarEntry)null;
				List<IToken> atomTypeVar = element.getAll("type_var");
				if(atomTypeVar != null){
					for(IToken atom:atomTypeVar){
						if((accessIndex == 3)){
							if((ret == null)){
								return generateTypeVar(atom,isInner,3,parentContext);
							}
						}
						else {
							if((ret == null)){
								ret = generateTypeVar(atom,isInner,0,parentContext);
								if((isInner == true)){
									previousType = (ITypeVarEntry)ret;
								}
							}
							else {
								if((isInner == true)){
									ITypeVarEntry secondType = (ITypeVarEntry)generateTypeVar(atom,isInner,1,parentContext);
									if((secondType.getIsConcrete())){
										if((previousType.getIsConcrete())){
											ret = new ITypeVarEntry(ret,"..",secondType);
										}
										else {
											ret = new ITypeVarEntry(ret,".",secondType);
										}
									}
									else {
										ret = new ITypeVarEntry(ret,".",secondType);
									}
									previousType = (ITypeVarEntry)secondType;
								}
								else {
									ETypeVarEntry realType = new ETypeVarEntry(ret,".",generateTypeVar(atom,isInner,1,parentContext),parentContext);
									ret = realType;
								}
							}
						}
					}
				}
				List<IToken> atomAsMethod = element.getAll("as_method");
				if(atomAsMethod != null){
					for(IToken atom:atomAsMethod){
						Entry methodName = (Entry)null;
						if((isInner == true)){
							methodName = new IExactEntry(new QuoteEntry("*"));
						}
						else {
							methodName = new EExactEntry(new QuoteEntry("*"));
						}
						List<IToken> quarkNameVar = atom.getAll("name_var");
						if(quarkNameVar != null){
							for(IToken quark:quarkNameVar){
								methodName = generateNameVar(quark,isInner);
							}
						}
						if((isInner == true)){
							ret = new ITypeVarEntry(ret,".*",methodName);
						}
						else {
							ret = new ETypeVarEntry(ret,".*",methodName,parentContext);
						}
					}
				}
				if((isInner == true)){
					if((element.get("CAMEL") != null)){
						return new ITypeVarEntry(new ElementEntry(InternalGenerator.camelizeElement,new ListEntry(ret)));
					}
					else {
						return ret;
					}
				}
				else {
					if((element.get("CAMEL") != null)){
						return new ETypeVarEntry(new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(ret)),parentContext);
					}
					else {
						return ret;
					}
				}
			}
			else if("access_method".equals(elementKey.getName())){
				IToken element = type_var.get(elementKey);
				Entry ret = (Entry)null;
				List<IToken> atomTypeVar = element.getAll("type_var");
				if(atomTypeVar != null){
					for(IToken atom:atomTypeVar){
						if((accessIndex == 3)){
							if((ret == null)){
								return generateTypeVar(atom,isInner,3,parentContext);
							}
						}
						else {
							if((ret == null)){
								ret = generateTypeVar(atom,isInner,0,parentContext);
							}
							else {
								if((isInner == true)){
									ret = new ITypeVarEntry(ret,".",generateTypeVar(atom,isInner,1,parentContext));
								}
								else {
									ret = new ETypeVarEntry(ret,".",generateTypeVar(atom,isInner,1,parentContext),parentContext);
								}
							}
						}
					}
				}
				Entry methodName = (Entry)null;
				if((isInner == true)){
					methodName = new IExactEntry(new QuoteEntry("*"));
				}
				else {
					methodName = new EExactEntry(new QuoteEntry("*"));
				}
				List<IToken> atomNameVar = element.getAll("name_var");
				if(atomNameVar != null){
					for(IToken atom:atomNameVar){
						methodName = generateNameVar(atom,isInner);
					}
				}
				if((isInner == true)){
					ret = new ITypeVarEntry(ret,".*",methodName);
				}
				else {
					ret = new ETypeVarEntry(ret,".*",methodName,parentContext);
				}
				if((isInner == true)){
					if((element.get("CAMEL") != null)){
						return new ITypeVarEntry(new ElementEntry(InternalGenerator.camelizeElement,new ListEntry(ret)));
					}
					else {
						return ret;
					}
				}
				else {
					if((element.get("CAMEL") != null)){
						return new ETypeVarEntry(new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(ret)),parentContext);
					}
					else {
						return ret;
					}
				}
			}
			else if("concat".equals(elementKey.getName())){
				IToken element = type_var.get(elementKey);
				Entry left = null;
				Entry right = null;
				for(IToken.Key atomKey:element.keySet()){
					if("type_var".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						if((accessIndex == 3)){
							if((left == null)){
								left = generateTypeVar(atom,isInner,3,parentContext);
							}
							else {
								right = generateTypeVar(atom,isInner,3,parentContext);
							}
						}
						else {
							if((accessIndex == 2)){
								if((left == null)){
									left = generateTypeVar(atom,isInner,2,parentContext);
								}
								else {
									right = generateTypeVar(atom,isInner,2,parentContext);
								}
							}
							else {
								if((left == null)){
									left = generateTypeVar(atom,isInner,1,parentContext);
								}
								else {
									right = generateTypeVar(atom,isInner,1,parentContext);
								}
							}
						}
					}
				}
				if((isInner == true)){
					if((element.get("CAMEL") != null)){
						return new ITypeVarEntry(new ElementEntry(InternalGenerator.camelizeElement,new ListEntry(new ITypeVarEntry(left,"+",right))));
					}
					else {
						return new ITypeVarEntry(left,"+",right);
					}
				}
				else {
					if((element.get("CAMEL") != null)){
						return new ETypeVarEntry(new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(new ETypeVarEntry(left,"+",right,parentContext))),parentContext);
					}
					else {
						return new ETypeVarEntry(left,"+",right,parentContext);
					}
				}
			}
			else if("as_method".equals(elementKey.getName())){
				IToken element = type_var.get(elementKey);
				Entry subject = (Entry)Generators.body.generateStatement(element.get("body_statement"),true,parentContext);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("\"$AS METHOD NOT IMPLEMENTED FOR INNER$\"")));
				}
				else {
					return new ETypeVarEntry(new EInnerCallEntry(subject),parentContext);
				}
			}
			else if("as_quote".equals(elementKey.getName())){
				IToken element = type_var.get(elementKey);
				Entry subject = (Entry)Generators.body.generateStatement(element.get("body_statement"),true,parentContext);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("\"$AS QUOTE NOT IMPLEMENTED FOR INNER$\"")));
				}
				else {
					return new ETypeVarEntry(new EQuoteEntry(new ICallEntry(subject,new StringEntry("toString"),new ListEntry())),parentContext);
				}
			}
			else if("as_string".equals(elementKey.getName())){
				IToken element = type_var.get(elementKey);
				Entry subject = (Entry)Generators.body.generateStatement(element.get("body_statement"),true,parentContext);
				if((isInner == true)){
					if((accessIndex == 0)){
						ITypeVarEntry retAsRet = new ITypeVarEntry(new ICallEntry(subject,new StringEntry("toString"),new ListEntry()));
						retAsRet.surroundInNonQuotes();
						return retAsRet;
					}
					else {
						ITypeVarEntry retAsRet = new ITypeVarEntry(new ICallEntry(subject,new StringEntry("toString"),new ListEntry()));
						retAsRet.surroundInNonQuotes();
						return retAsRet;
					}
				}
				else {
					if((accessIndex == 0)){
						return new ITypeVarEntry(new IExactEntry(new ElementEntry(ExternalGenerator.bodyTypeNameElement,new ListEntry(subject))));
					}
					else {
						return new ETypeVarEntry(new EEntryEntry(new ICallEntry(subject,new StringEntry("toString"),new ListEntry())),parentContext);
					}
				}
			}
			else if("exact".equals(elementKey.getName())){
				IToken element = type_var.get(elementKey);
				ListEntry templateParameters = new ListEntry();
				templateParameters.setDelimiter(",");
				List<IToken> atomTemplateParameters = element.getAll("template_parameters");
				if(atomTemplateParameters != null){
					for(IToken atom:atomTemplateParameters){
						List<IToken> quarkTemplateParameter = atom.getAll("template_parameter");
						if(quarkTemplateParameter != null){
							for(IToken quark:quarkTemplateParameter){
								templateParameters.add(generateAllType(quark,isInner,parentContext));
							}
						}
					}
				}
				Entry value = null;
				IImportable importType = (IImportable)Generators.classwise.getType("void");
				if((isInner == true)){
					Boolean retInQuotes = false;
					if((element.get("CAMEL") != null)){
						if((element.get("NAME") != null)){
							if((accessIndex == 0)){
								value = new ElementEntry(InternalGenerator.getClassFromClassMapElement,new ListEntry(new QuoteEntry(Generator.camelize(element.get("NAME").getString()))));
							}
							else {
								value = new StringEntry(Generator.camelize(element.get("NAME").getString()));
								retInQuotes = true;
							}
						}
						else {
							if((accessIndex == 2)){
								value = new StringEntry(Generator.camelize(element.get("variable_names").getString()));
							}
							if((accessIndex == 0)){
								value = new ElementEntry(ClasswiseGenerator.classAsVariableElement,new ListEntry(new StringEntry(Generator.camelize(element.get("variable_names").getString()))));
							}
							if((accessIndex == 1)){
								value = new StringEntry(Generator.camelize(element.get("variable_names").getString()));
							}
							if((accessIndex == 3)){
								value = new StringEntry(Generator.camelize(element.get("variable_names").getString()));
							}
						}
					}
					else {
						if((element.get("NAME") != null)){
							if((accessIndex == 0)){
								value = new ElementEntry(InternalGenerator.getClassFromClassMapElement,new ListEntry(new QuoteEntry(element.get("NAME").getString())));
							}
							else {
								value = new StringEntry(element.get("NAME").getString());
								retInQuotes = true;
							}
						}
						else {
							if((accessIndex == 2)){
								value = new StringEntry(element.get("variable_names").getString());
							}
							if((accessIndex == 0)){
								value = new ElementEntry(ClasswiseGenerator.classAsVariableElement,new ListEntry(new StringEntry(element.get("variable_names").getString())));
							}
							if((accessIndex == 1)){
								value = new StringEntry(element.get("variable_names").getString());
							}
							if((accessIndex == 3)){
								value = new StringEntry(element.get("variable_names").getString());
							}
						}
					}
					ITypeVarEntry retAsRet = (ITypeVarEntry)new ITypeVarEntry(value,templateParameters,importType);
					if((retInQuotes == false)){
						retAsRet.surroundInNonQuotes();
					}
					else {
						retAsRet.surroundInQuotes();
					}
					return retAsRet;
				}
				else {
					if((element.get("CAMEL") != null)){
						if((element.get("NAME") != null)){
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(Generator.camelize(element.get("NAME").getString()))));
						}
						else {
							value = new ElementEntry(ExternalGenerator.camelizeElement,new ListEntry(new StringEntry(element.get("variable_names").getString())));
						}
					}
					else {
						if((element.get("NAME") != null)){
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(element.get("NAME").getString())));
						}
						else {
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new StringEntry(element.get("variable_names").getString())));
						}
					}
					return new ETypeVarEntry(value,templateParameters,importType,parentContext);
				}
			}
			else if("class".equals(elementKey.getName())){
				IToken element = type_var.get(elementKey);
				ListEntry templateParameters = new ListEntry();
				templateParameters.setDelimiter(",");
				List<IToken> atomTemplateParameters = element.getAll("template_parameters");
				if(atomTemplateParameters != null){
					for(IToken atom:atomTemplateParameters){
						List<IToken> quarkTemplateParameter = atom.getAll("template_parameter");
						if(quarkTemplateParameter != null){
							for(IToken quark:quarkTemplateParameter){
								templateParameters.add(generateAllType(quark,isInner,parentContext));
							}
						}
					}
				}
				Entry value = null;
				IImportable importType = (IImportable)null;
				if((isInner == true)){
					if((element.get("CAMEL") != null)){
						if((element.get("class_variable_names") != null)){
							value = new StringEntry(Generator.camelize(element.get("class_variable_names").getString()));
						}
						else {
							if((accessIndex == 3)){
								value = new StringEntry(Generator.camelize(element.get("class_names").getString()));
							}
							if((accessIndex == 2)){
								value = new StringEntry(Generator.camelize(element.get("class_names").getString()));
							}
							if((accessIndex == 0)){
								value = new ElementEntry(ClasswiseGenerator.retrieveClassElement,new ListEntry(new StringEntry(Generator.camelize(element.get("class_names").getString()))));
							}
							if((accessIndex == 1)){
								value = new ElementEntry(ClasswiseGenerator.classAsVariableElement,new ListEntry(new StringEntry(element.get("class_names").getString())));
							}
							importType = Generators.classwise.getType(Generators.classwise.buildString(Generator.camelize(element.get("class_names").getString()),"Class"));
						}
					}
					else {
						if((element.get("class_variable_names") != null)){
							value = new StringEntry(element.get("class_variable_names").getString());
						}
						else {
							if((accessIndex == 3)){
								value = new StringEntry(element.get("class_names").getString());
							}
							if((accessIndex == 2)){
								value = new StringEntry(element.get("class_names").getString());
							}
							if((accessIndex == 0)){
								String classNameValue = element.get("class_names").getString();
								if((parentContext.containsMethodBoundClass(classNameValue))){
									value = new ElementEntry(ClasswiseGenerator.classAsVariableElement,new ListEntry(new StringEntry(classNameValue)));
								}
								else {
									value = new ElementEntry(ClasswiseGenerator.retrieveClassElement,new ListEntry(new StringEntry(classNameValue)));
								}
							}
							if((accessIndex == 1)){
								value = new ElementEntry(ClasswiseGenerator.classAsVariableElement,new ListEntry(new StringEntry(element.get("class_names").getString())));
							}
							importType = Generators.classwise.getType(Generators.classwise.buildString(element.get("class_names").getString(),"Class"));
						}
					}
					if((accessIndex == 3 && element.get("class_names") != null)){
						ITypeVarEntry ret = new ITypeVarEntry(value,templateParameters,importType);
						ret.setIsVariable(false);
						return ret;
					}
					else {
						if((accessIndex == 1 || accessIndex == 0)){
							ITypeVarEntry retType = (ITypeVarEntry)new ITypeVarEntry(value,templateParameters,importType);
							retType.setIsConcrete(true);
							return retType;
						}
						else {
							return new ITypeVarEntry(value,templateParameters,importType);
						}
					}
				}
				else {
					if((element.get("CAMEL") != null)){
						if((element.get("class_variable_names") != null)){
							value = new ElementEntry(ExternalGenerator.getNameElement,new ListEntry(new StringEntry(Generator.camelize(element.get("class_variable_names").getString()))));
						}
						else {
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(Generator.camelize(element.get("class_names").getString()))));
						}
						importType = Generators.classwise.getType(Generators.classwise.buildString(Generator.camelize(element.get("class_names").getString()),"Class"));
					}
					else {
						if((element.get("class_variable_names") != null)){
							value = new ElementEntry(ExternalGenerator.getNameElement,new ListEntry(new StringEntry(element.get("class_variable_names").getString())));
						}
						else {
							value = new ElementEntry(ExternalGenerator.exactElement,new ListEntry(new QuoteEntry(element.get("class_names").getString())));
							importType = Generators.classwise.getType(Generators.classwise.buildString(element.get("class_names").getString(),"Class"));
						}
					}
					return new ETypeVarEntry(value,templateParameters,importType,parentContext);
				}
			}
		}
		return null;
	}
	public Entry generateAllType(IToken all_type,Boolean isInner,ContextEntry parentContext){
		for(IToken.Key elementKey:all_type.keySet()){
			if("type_var".equals(elementKey.getName())){
				IToken element = all_type.get(elementKey);
				return Generators.classwise.generateTypeVar(element,isInner,2,parentContext);
			}
			else if("CLASS_TYPE".equals(elementKey.getName())){
				IToken element = all_type.get(elementKey);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("ExternalClassEntry")));
				}
				else {
					return new ETypeVarEntry(new EExactEntry(new StringEntry("ExternalClassEntry")),parentContext);
				}
			}
			else if("METHOD_TYPE".equals(elementKey.getName())){
				IToken element = all_type.get(elementKey);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("ExternalMethodEntry")));
				}
				else {
					return new ETypeVarEntry(new EExactEntry(new StringEntry("ExternalMethodEntry")),parentContext);
				}
			}
			else if("VARIABLE_TYPE".equals(elementKey.getName())){
				IToken element = all_type.get(elementKey);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("ExternalVariableEntry")));
				}
				else {
					return new ETypeVarEntry(new EExactEntry(new StringEntry("ExternalVariableEntry")),parentContext);
				}
			}
			else if("BODY_TYPE".equals(elementKey.getName())){
				IToken element = all_type.get(elementKey);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("ExternalStatement.Body")));
				}
				else {
					return new ETypeVarEntry(new EExactEntry(new StringEntry("ExternalStatement.Body")),parentContext);
				}
			}
			else if("STATEMENT_TYPE".equals(elementKey.getName())){
				IToken element = all_type.get(elementKey);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("ExternalStatement")));
				}
				else {
					return new ETypeVarEntry(new EExactEntry(new StringEntry("ExternalStatement")),parentContext);
				}
			}
			else if("PARAMETERS_TYPE".equals(elementKey.getName())){
				IToken element = all_type.get(elementKey);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("ExternalStatement.Parameters")));
				}
				else {
					return new ETypeVarEntry(new EExactEntry(new StringEntry("ExternalStatement.Parameters")),parentContext);
				}
			}
			else if("CONTEXT_TYPE".equals(elementKey.getName())){
				IToken element = all_type.get(elementKey);
				if((isInner == true)){
					return new ITypeVarEntry(new IExactEntry(new StringEntry("ExternalContext")));
				}
				else {
					return new ETypeVarEntry(new EExactEntry(new StringEntry("ExternalContext")),parentContext);
				}
			}
		}
		return null;
	}
	public IImportable getType(String className){
		if((importables.get(className) == null)){
			importables.put(className,new ImportHolderEntry());
		}
		return (IImportable)importables.get(className);
	}

	public String getNxtPackageName(){
		return nxtPackageName;
	}

	public String getInrPackageName(){
		return inrPackageName;
	}

	public File getInDir(){
		return inDir;
	}

	public File getNrDir(){
		return nrDir;
	}

	public HashMap<String,Entry> getInnerEntries(){
		return innerEntries;
	}

	public HashMap<String,Entry> getNextEntries(){
		return nextEntries;
	}

	public ArrayList<EClassEntry> getNextClasses(){
		return nextClasses;
	}

	public HashMap<String,IImportable> getImportables(){
		return importables;
	}

	public ListEntry getAddClassFileList(){
		return addClassFileList;
	}

	public ListEntry getSetupClassList(){
		return setupClassList;
	}

	public IClassEntry getMainClass(){
		return main_class;
	}

	public String getName(){
		return "Classwise";
	}

		public void generateRoot(IToken root){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}