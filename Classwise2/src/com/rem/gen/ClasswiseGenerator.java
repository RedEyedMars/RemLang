package com.rem.gen;
import java.io.File;
public class ClasswiseGenerator {
	public static String mainClassName = "MainFlow";
	protected static File innerDirectory = null;
	protected static File outerDirectory = null;
	public static com.rem.output.helpers.OutputClass mainClass = null;
	protected static String sourceDirectory = null;
	public static void setup(com.rem.gen.parser.Parser.Result.Pass result){
		String fileName = result.getFileName();
		int indexOfDot = fileName.lastIndexOf(".");
		if(indexOfDot>-1 ){
			fileName=fileName.substring(0,indexOfDot);
		}
		int indexOfSlash = fileName.lastIndexOf("/");
		if(indexOfSlash>-1 ){
			fileName=fileName.substring(indexOfSlash+1);
		}
		indexOfSlash=fileName.lastIndexOf("\\");
		if(indexOfSlash>-1 ){
			fileName=fileName.substring(indexOfSlash+1);
		}
		fileName=com.rem.output.helpers.OutputHelper.camelize(fileName);
		mainClassName=fileName;
		innerDirectory=new File("../"+fileName+"/src");
		innerDirectory.mkdirs();
		outerDirectory=new File("../"+fileName+"/src",Classwise2.innerPackageName.replace(".","/"));
		outerDirectory.mkdirs();
		sourceDirectory="../"+fileName+"/src/";
	}
	public static void importAllClasses(com.rem.gen.parser.Token input){
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._anonymous_classes)){
			com.rem.output.helpers.OutputCall classPackageName = new com.rem.output.helpers.OutputCall();
			for(com.rem.gen.parser.Token packageElement:element.getAllSafely(com.rem.gen.parser.Token.Id._packageName)){
				if(packageElement.get(com.rem.gen.parser.Token.Id._NAME)!=null){
					classPackageName.add(new com.rem.output.helpers.OutputExact((packageElement.get(com.rem.gen.parser.Token.Id._NAME)).toString()));
				}
				else if(packageElement.get(com.rem.gen.parser.Token.Id._quote)!=null){
					classPackageName.add(new com.rem.output.helpers.OutputExact((packageElement.get(com.rem.gen.parser.Token.Id._quote)).toString()));
				}
				else if(packageElement.get(com.rem.gen.parser.Token.Id._statement_as_string)!=null){
					classPackageName.add(Classwise2.body_gen.statement(packageElement.get(com.rem.gen.parser.Token.Id._statement_as_string).get(com.rem.gen.parser.Token.Id._body_statement),true,mainClass));
				}
			}
			for(com.rem.gen.parser.Token anonymousClass:element.getAllSafely(com.rem.gen.parser.Token.Id._anonymous_class)){
				defineAnonymousClass(anonymousClass,true,classPackageName.evaluate(),"");
			}
		}
	}
	public static void defineAnonymousClass(com.rem.gen.parser.Token anonymousClass,boolean isFirst,String anonymousPackageName,String parentClass){
		StringBuilder currentClass = new StringBuilder();
		currentClass.append(parentClass);
		for(com.rem.gen.parser.Token className:anonymousClass.getAllSafely(com.rem.gen.parser.Token.Id._className)){
			if(isFirst==false){
				currentClass.append(".");
			}
			currentClass.append(className);
			String currentClassValue = (currentClass).toString();
			Classwise2.setupClassList.add(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputHelper")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("suppliment"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(anonymousPackageName)).add(new com.rem.output.helpers.OutputQuote().set(currentClassValue)))))));
			com.rem.output.helpers.OutputHelper.suppliment((currentClassValue).toString(),(anonymousPackageName).toString());
		}
		for(com.rem.gen.parser.Token subAnonymousClass:anonymousClass.getAllSafely(com.rem.gen.parser.Token.Id._anonymous_class)){
			defineAnonymousClass(subAnonymousClass,false,anonymousPackageName,(currentClass).toString());
		}
	}
	public static void findAllClasses(com.rem.gen.parser.Token input){
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._import_imports)){
			importAllClasses(element);
		}
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._import_clws)){
			findAllClasses(element);
		}
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._class_declaration)){
			Classwise2.classGenerator.collectClassNames(element);
		}
	}
	public static void generateGlobals(com.rem.gen.parser.Token input){
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._import_clws)){
			generateGlobals(element);
		}
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._variable_declaration)){
			com.rem.output.helpers.OutputVariable newVariable = Classwise2.variable.declaration(element,true,mainClass).isStatic().isPublic();
			mainClass.addVariable(newVariable);
			Classwise2.variable.addDefinedVariableName(newVariable);
		}
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._method_declaration)){
			com.rem.output.helpers.OutputMethod newMethod = Classwise2.method.declaration(element,true,mainClass).isStatic();
			mainClass.addMethod(newMethod);
			Classwise2.method.addDefinedMethodName(newMethod);
		}
	}
	public static void generateAll(com.rem.gen.parser.Token input){
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._import_clws)){
			generateAll(element);
		}
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._class_declaration)){
			Classwise2.classGenerator.collectClassNames(element);
		}
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._class_declaration)){
			com.rem.output.helpers.OutputClass innerClass = new com.rem.output.helpers.OutputClass();
			com.rem.output.helpers.OutputClass outerClass = Classwise2.classGenerator.declaration(element,innerClass,element.get(com.rem.gen.parser.Token.Id._inner)!=null,null);
			if(element.get(com.rem.gen.parser.Token.Id._inner)==null){
				innerClass.addVariable(new com.rem.output.helpers.OutputVariable().isPublic().isStatic().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputClass")),new com.rem.output.helpers.OutputExact().set("OUTPUT")).assign(outerClass.stasis()));
				Classwise2.setupClassList.add(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputHelper")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("addOutputClass"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputCall().add(outerClass.getName(),null).add(new com.rem.output.helpers.OutputExact().set("OUTPUT"),null)))))));
			}
			com.rem.output.helpers.OutputHelper.addOutputClass(innerClass);
		}
	}
	public static void generate(com.rem.gen.parser.Parser.Result.Pass result){
		mainClass=new com.rem.output.helpers.OutputClass()._package(new com.rem.output.helpers.OutputExact((Classwise2.innerPackageName).toString())).name(new com.rem.output.helpers.OutputExact((mainClassName).toString())).extendType(null).method(new com.rem.output.helpers.OutputMethod().access("public ").isStatic().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("main")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("String")).array(),new com.rem.output.helpers.OutputExact().set("args")))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact((mainClassName).toString()),null).add(new com.rem.output.helpers.OutputExact().set("init"),new com.rem.output.helpers.OutputArguments()))).add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputHelper")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("parse"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact().set("args")).add(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.gen.parser.Parser")),new com.rem.output.helpers.OutputArguments())).add(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputType().add(mainClassName)).operator("::").right(new com.rem.output.helpers.OutputExact().set("setup"))).add(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputType().add(mainClassName)).operator("::").right(new com.rem.output.helpers.OutputExact().set("generate"))))))))).method(new com.rem.output.helpers.OutputMethod().access("public ").isStatic().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("init")).parameters(new com.rem.output.helpers.OutputParameters()).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(Classwise2.setupClassList))));
		findAllClasses(result.getRoot());
		generateGlobals(result.getRoot());
		generateAll(result.getRoot());
		com.rem.output.helpers.OutputHelper.addOutputClass(mainClass);
	}
	public static com.rem.output.helpers.Output name_var(com.rem.gen.parser.Token input,boolean isInner,com.rem.output.helpers.OutputContext context){
		if(input.get(com.rem.gen.parser.Token.Id._tokenAccess)!=null){
			return tokenAccess(input.get(com.rem.gen.parser.Token.Id._tokenAccess),isInner,context);
		}
		else{
			com.rem.output.helpers.OutputConcatenation result = new com.rem.output.helpers.OutputConcatenation();
			for(com.rem.gen.parser.Token atom:input.getAllSafely(com.rem.gen.parser.Token.Id._name_atom)){
				result.add(name_atom(atom,isInner,context));
			}
			if(input.get(com.rem.gen.parser.Token.Id._cast_statement)!=null){
				com.rem.output.helpers.OutputCast cast = new com.rem.output.helpers.OutputCast();
				for(com.rem.gen.parser.Token atom:input.getAllSafely(com.rem.gen.parser.Token.Id._cast_statement)){
					cast.type(Classwise2.classwise.all_type(atom.get(com.rem.gen.parser.Token.Id._all_type_name),isInner,context));
				}
				return cast.subject(result);
			}
			else{
				return result;
			}
		}
	}
	public static com.rem.output.helpers.Output tokenAccess(com.rem.gen.parser.Token element,boolean isInner,com.rem.output.helpers.OutputContext context){
		com.rem.output.helpers.CallableOutput call = new com.rem.output.helpers.OutputCall().add(name_atom(element.get(com.rem.gen.parser.Token.Id._name_atom),isInner,context));
		for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._access)){
			String name = (atom.get(com.rem.gen.parser.Token.Id._NAME)).toString();
			if(atom.get(com.rem.gen.parser.Token.Id._get)!=null){
				call.add(new com.rem.output.helpers.OutputExact("get"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact("com.rem.gen.parser.Token.Id._"+name)));
			}
			else if(atom.get(com.rem.gen.parser.Token.Id._getAllSafely)!=null){
				call.add(new com.rem.output.helpers.OutputExact("getAllSafely"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact("com.rem.gen.parser.Token.Id._"+name)));
			}
		}
		return call;
	}
	public static com.rem.output.helpers.Output name_atom(com.rem.gen.parser.Token input,boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		if(input.get(com.rem.gen.parser.Token.Id._statement_as_char)!=null){
			String value = (input.get(com.rem.gen.parser.Token.Id._statement_as_char).get(com.rem.gen.parser.Token.Id._value)).toString();
			if(value.equals("\\")){
				value="\\\\";
			}
			else if(value.equals("\"")){
				value="\\\"";
			}
			else if(value.equals("\'")){
				value="\\\'";
			}
			return new com.rem.output.helpers.OutputBraced().set(new com.rem.output.helpers.OutputExact(value.replace("\\","\\\\"))).style("'","'");
		}
		else if(input.get(com.rem.gen.parser.Token.Id._statement_as_method)!=null){
			if(isInner){
				return Classwise2.body_gen.statement(input.get(com.rem.gen.parser.Token.Id._statement_as_method).get(com.rem.gen.parser.Token.Id._body_statement),true,parentContext);
			}
			else{
				return Classwise2.body_gen.statement(input.get(com.rem.gen.parser.Token.Id._statement_as_method).get(com.rem.gen.parser.Token.Id._body_statement),true,parentContext).vibrate();
			}
		}
		else if(input.get(com.rem.gen.parser.Token.Id._statement_as_quote)!=null){
			com.rem.output.helpers.Output asQuoteStatement = Classwise2.body_gen.statement(input.get(com.rem.gen.parser.Token.Id._statement_as_quote).get(com.rem.gen.parser.Token.Id._body_statement),true,parentContext);
			if(isInner){
				return new com.rem.output.helpers.OutputQuote().set(asQuoteStatement);
			}
			else{
				return new com.rem.output.helpers.OutputQuote().set(asQuoteStatement.vibrate()).stasis();
			}
		}
		else if(input.get(com.rem.gen.parser.Token.Id._statement_as_string)!=null){
			com.rem.output.helpers.Output result = new com.rem.output.helpers.OutputString().set(Classwise2.body_gen.statement(input.get(com.rem.gen.parser.Token.Id._statement_as_string).get(com.rem.gen.parser.Token.Id._body_statement),true,parentContext).vibrate());
			return result;
		}
		else if(input.get(com.rem.gen.parser.Token.Id._quote)!=null){
			if(isInner){
				return new com.rem.output.helpers.OutputQuote().set((input.get(com.rem.gen.parser.Token.Id._quote)).toString());
			}
			else{
				return new com.rem.output.helpers.OutputQuote().set((input.get(com.rem.gen.parser.Token.Id._quote)).toString()).stasis();
			}
		}
		else if(input.get(com.rem.gen.parser.Token.Id._NUMBER)!=null){
			return new com.rem.output.helpers.OutputExact((input.get(com.rem.gen.parser.Token.Id._NUMBER)).toString());
		}
		else if(input.get(com.rem.gen.parser.Token.Id._variable_names)!=null){
			String value = (input.get(com.rem.gen.parser.Token.Id._variable_names)).toString();
			if(isInner&&mainClass.hasVariable(value)&&(parentContext==null||parentContext.hasVariableInContext(value)==false)){
				value=mainClassName+"."+value;
			}
			return new com.rem.output.helpers.OutputExact(value);
		}
		else{
			input.err();
			return null;
		}
	}
	public static com.rem.output.helpers.OutputType type_var(com.rem.gen.parser.Token input,boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		com.rem.output.helpers.OutputType type = new com.rem.output.helpers.OutputType();
		for(com.rem.gen.parser.Token element:input.getAll()){
			switch(element.getName()){
				case _type_atom :{
					type.add(type_atom(element,isInner,parentContext));
					break;
				}
				case _template_parameters :{
					for(com.rem.gen.parser.Token quark:element.getAllSafely(com.rem.gen.parser.Token.Id._template_parameter)){
						type.template(all_type(quark,isInner,parentContext));
					}
					break;
				}
			}
		}
		return type;
	}
	public static com.rem.output.helpers.OutputType type_atom(com.rem.gen.parser.Token input,boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		if(input.get(com.rem.gen.parser.Token.Id._statement_as_method)!=null){
			return new com.rem.output.helpers.OutputType(Classwise2.body_gen.statement(input.get(com.rem.gen.parser.Token.Id._statement_as_method).get(com.rem.gen.parser.Token.Id._body_statement),true,parentContext));
		}
		else if(input.get(com.rem.gen.parser.Token.Id._statement_as_string)!=null){
			return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputString().set(Classwise2.body_gen.statement(input.get(com.rem.gen.parser.Token.Id._statement_as_string).get(com.rem.gen.parser.Token.Id._body_statement),true,parentContext)));
		}
		else if(input.get(com.rem.gen.parser.Token.Id._class)!=null){
			if(input.get(com.rem.gen.parser.Token.Id._class).get(com.rem.gen.parser.Token.Id._class_variable_names)!=null){
				if(isInner){
					return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact((input.get(com.rem.gen.parser.Token.Id._class).get(com.rem.gen.parser.Token.Id._class_variable_names)).toString())).add(new com.rem.output.helpers.OutputExact("getFullName"),new com.rem.output.helpers.OutputArguments()));
				}
				else{
					return new com.rem.output.helpers.OutputType((input.get(com.rem.gen.parser.Token.Id._class).get(com.rem.gen.parser.Token.Id._class_variable_names)).toString());
				}
			}
			else{
				return new com.rem.output.helpers.OutputType((input.get(com.rem.gen.parser.Token.Id._class).get(com.rem.gen.parser.Token.Id._class_names)).toString());
			}
		}
		else{
			return null;
		}
	}
	public static com.rem.output.helpers.OutputType all_type(com.rem.gen.parser.Token input,Boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		if(input.get(com.rem.gen.parser.Token.Id._non_class_name)!=null){
			String elementName = (input.getAllSafely(com.rem.gen.parser.Token.Id._non_class_name).get(0).getAll().get(0).getName()).toString().substring(1);
			if(elementName.startsWith("O")){
				return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputExact("com.rem.output.helpers."+elementName));
			}
			else if(elementName.startsWith("Callable")){
				return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputExact("com.rem.output.helpers.CallableOutput"));
			}
			else if(elementName.startsWith("Lineable")){
				return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputExact("com.rem.output.helpers.LineableOutput"));
			}
			else{
				if(elementName.equals("Id")){
					return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputExact("com.rem.gen.parser.Token.Id"));
				}
				else if(elementName.equals("Result")){
					return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputExact("com.rem.gen.parser.Parser.Result"));
				}
				else if(elementName.equals("Pass")){
					return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputExact("com.rem.gen.parser.Parser.Result.Pass"));
				}
				else if(elementName.equals("Fail")){
					return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputExact("com.rem.gen.parser.Parser.Result.Fail"));
				}
				else{
					return new com.rem.output.helpers.OutputType(new com.rem.output.helpers.OutputExact("com.rem.gen.parser."+elementName));
				}
			}
		}
		else if(input.get(com.rem.gen.parser.Token.Id._type_var)!=null){
			return type_var(input.get(com.rem.gen.parser.Token.Id._type_var),isInner,parentContext);
		}
		else{
			if(input.get(com.rem.gen.parser.Token.Id._all_type_name)!=null){
				return all_type(input.get(com.rem.gen.parser.Token.Id._all_type_name),isInner,parentContext);
			}
			else{
				return null;
			}
		}
	}
}
