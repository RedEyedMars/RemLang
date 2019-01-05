package com.rem.gen;
import java.util.List;
public class BodyGenerator {
	protected static Integer exceptionIndex = 0;
	protected static Integer tempTokenElementIndex = 0;
	public static boolean elementHasReturn(com.rem.gen.parser.Token input){
		for(com.rem.gen.parser.Token element:input.getAll()){
			switch(element.getName()){
				case _body_return :{
					return true;
				}
				case _body_conditional :{
					return conditionalHasReturn(element);
				}
			}
		}
		return false;
	}
	public static boolean conditionalHasReturn(com.rem.gen.parser.Token input){
		for(com.rem.gen.parser.Token atom:input.getAllSafely(com.rem.gen.parser.Token.Id._as_body)){
			com.rem.gen.parser.Token lastElement = null;
			List<com.rem.gen.parser.Token> elements = atom.getAllSafely(com.rem.gen.parser.Token.Id._body_element);
			int i = elements.size()-1;
			while(i>=0 &&lastElement==null){
				String currentName = (elements.get(i).getAll().get(0).getName()).toString();
				if(currentName.equals("comments")==false&&currentName.equals("SYNTAX")==false){
					lastElement=elements.get(i);
					break;
				}
				i-=1;
			}
			if(lastElement!=null){
				return elementHasReturn(lastElement);
			}
		}
		return false;
	}
	public static com.rem.output.helpers.LineableOutput element(com.rem.gen.parser.Token input,Boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		for(com.rem.gen.parser.Token element:input.getAll()){
			switch(element.getName()){
				case _body_add_to_class :{
					return addToClass(element,isInner,parentContext);
				}
				case _body_access_token :{
					return accessToken(element,isInner,parentContext);
				}
				case _variable_declaration :{
					return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(Classwise2.variable.declaration(element,isInner,parentContext)));
				}
				case _variable_assignment :{
					return Classwise2.variable.assignment(element,isInner,parentContext);
				}
				case _body_statement :{
					return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(Classwise2.body_gen.statement(element,isInner,parentContext)));
				}
				case _body_return :{
					if(element.get(com.rem.gen.parser.Token.Id._method_argument)!=null){
						return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().prefix("return ").set(Classwise2.body_gen.argument(element.get(com.rem.gen.parser.Token.Id._method_argument),isInner,parentContext)));
					}
					else{
						return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().prefix("return "));
					}
				}
				case _body_throw :{
					return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().prefix("throw ").set(Classwise2.body_gen.statement(element.get(com.rem.gen.parser.Token.Id._body_statement),isInner,parentContext)));
				}
				case _body_conditional :{
					com.rem.output.helpers.OutputConditional resultingConditional = new com.rem.output.helpers.OutputConditional();
					Boolean isCase = false;
					String conditionalName = "";
					exceptionIndex=0;
					if(element.get(com.rem.gen.parser.Token.Id._conditional_choice)!=null){
					}
					else if(element.get(com.rem.gen.parser.Token.Id._conditional).getAll()==null){
						conditionalName=element.get(com.rem.gen.parser.Token.Id._conditional).getValue().trim();
					}
					else{
						StringBuilder conditionalNameBuilder = new StringBuilder();
						String space = "";
						for(com.rem.gen.parser.Token conditionName:element.get(com.rem.gen.parser.Token.Id._conditional).getAllSafely(com.rem.gen.parser.Token.Id._SYNTAX)){
							conditionalNameBuilder.append(space);
							conditionalNameBuilder.append(conditionName.getValue().trim());
							space=" ";
						}
						conditionalName=conditionalNameBuilder.toString();
					}
					com.rem.output.helpers.OutputConditionalHeader header = new com.rem.output.helpers.OutputConditionalHeader();
					com.rem.output.helpers.Output headerCall = null;
					if(element.get(com.rem.gen.parser.Token.Id._body_statement)!=null){
						headerCall=Classwise2.body_gen.statement(element.get(com.rem.gen.parser.Token.Id._body_statement),isInner,parentContext);
					}
					if(element.get(com.rem.gen.parser.Token.Id._variable_declaration)!=null){
						for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._variable_declaration)){
							String operator = (element.get(com.rem.gen.parser.Token.Id._OPERATOR)).toString();
							if(operator.contains(":")){
								header.declare(Classwise2.variable.declaration(atom,isInner,parentContext));
								header.separator(":");
								header.call(headerCall);
							}
							else{
								header.separator(";");
								com.rem.output.helpers.OutputVariable headerVariable = Classwise2.variable.declaration(atom,isInner,parentContext);
								headerVariable.assign(new com.rem.output.helpers.OutputExact("0"));
								header.declare(headerVariable);
								com.rem.output.helpers.OutputOperator rightSide = new com.rem.output.helpers.OutputOperator().operator(";");
								rightSide.left(new com.rem.output.helpers.OutputOperator().left(headerVariable.getName()).operator(operator).right(headerCall));
								rightSide.right(new com.rem.output.helpers.OutputOperator().operator("++").right(headerVariable.getName()));
								header.call(rightSide);
							}
						}
					}
					else if(element.get(com.rem.gen.parser.Token.Id._exception)!=null){
						com.rem.output.helpers.OutputType exceptionType = new com.rem.output.helpers.OutputType();
						for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._exception)){
							String exceptionTypeName;
							if(atom.getValue().contains("*")){
								exceptionTypeName="Exception";
							}
							else{
								exceptionTypeName=com.rem.output.helpers.OutputHelper.camelize(atom.getValue())+"Exception";
							}
							exceptionType=exceptionType.or(new com.rem.output.helpers.OutputType(exceptionTypeName));
						}
						header=new com.rem.output.helpers.OutputConditionalHeader().declare(new com.rem.output.helpers.OutputVariable().set(exceptionType,new com.rem.output.helpers.OutputExact(("e"+exceptionIndex).toString())));
					}
					else if(headerCall!=null){
						header.call(headerCall);
					}
					else{
						header=null;
					}
					if(element.get(com.rem.gen.parser.Token.Id._statement_as_method)!=null){
						resultingConditional.body(Classwise2.body_gen.statement(element.get(com.rem.gen.parser.Token.Id._statement_as_method).get(com.rem.gen.parser.Token.Id._body_statement),true,parentContext));
					}
					else{
						resultingConditional.body(new com.rem.output.helpers.OutputBody());
						for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._as_body)){
							if(element.get(com.rem.gen.parser.Token.Id._PRINT)!=null){
								resultingConditional.add(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact("e"+(exceptionIndex).toString())).add(new com.rem.output.helpers.OutputExact("printStackTrace"),new com.rem.output.helpers.OutputArguments()));
							}
							for(com.rem.gen.parser.Token quark:atom.getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
								com.rem.output.helpers.LineableOutput bodyElem = Classwise2.body_gen.element(quark,isInner,resultingConditional.body());
								if(bodyElem!=null){
									resultingConditional.add(bodyElem);
								}
							}
						}
						if(element.get(com.rem.gen.parser.Token.Id._exception)!=null){
							++exceptionIndex;
						}
					}
					if(element.get(com.rem.gen.parser.Token.Id._conditional_choice)!=null){
						return resultingConditional.optionalIf((element.get(com.rem.gen.parser.Token.Id._conditional_choice)).toString(),(element.get(com.rem.gen.parser.Token.Id._conditional_operator)).toString().contains("+")).header(header);
					}
					else if(conditionalName.contains("case")){
						return resultingConditional.init(conditionalName.trim()+" "+headerCall.evaluate()+":");
					}
					else{
						return resultingConditional.init(conditionalName.trim()).header(header);
					}
				}
			}
		}
		return null;
	}
	public static com.rem.output.helpers.Output statement(com.rem.gen.parser.Token input,Boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		com.rem.output.helpers.OutputOperator statement = new com.rem.output.helpers.OutputOperator();
		Boolean isFirst = true;
		for(com.rem.gen.parser.Token element:input.getAll()){
			switch(element.getName()){
				case _body_call :{
					if(isFirst){
						statement.left(Classwise2.body_gen.call(element,isInner,parentContext));
					}
					else{
						statement.right(Classwise2.body_gen.call(element,isInner,parentContext));
					}
					isFirst=false;
					break;
				}
				case _NOT :{
					statement.operator(statement.operator()+" !");
					isFirst=false;
					break;
				}
				case _OPERATOR :{
					com.rem.output.helpers.OutputOperator nextStatement = new com.rem.output.helpers.OutputOperator().left(statement);
					statement=nextStatement;
					if(element.get(com.rem.gen.parser.Token.Id._exact)!=null){
						statement.operator(element.getValue().trim());
					}
					else if(element.get(com.rem.gen.parser.Token.Id._instance)!=null){
						statement.operator(" instanceof ");
					}
					else{
						statement.operator(element.getValue().substring(1).trim());
					}
					isFirst=false;
					break;
				}
			}
		}
		return statement;
	}
	public static com.rem.output.helpers.Output call(com.rem.gen.parser.Token input,Boolean mustInner,com.rem.output.helpers.OutputContext parentContext){
		Boolean isInner = mustInner||input.get(com.rem.gen.parser.Token.Id._inner)!=null;
		if(input.get(com.rem.gen.parser.Token.Id._as_braced)!=null){
			return new com.rem.output.helpers.OutputBraced().set(Classwise2.body_gen.statement(input.get(com.rem.gen.parser.Token.Id._as_braced).get(com.rem.gen.parser.Token.Id._statement_as_braced).get(com.rem.gen.parser.Token.Id._body_statement),isInner,parentContext));
		}
		else if(input.get(com.rem.gen.parser.Token.Id._as_statement)!=null){
			for(com.rem.gen.parser.Token element:input.get(com.rem.gen.parser.Token.Id._as_statement).getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
				com.rem.output.helpers.Output newBodyElement = Classwise2.body_gen.element(element,false,parentContext);
				if(newBodyElement!=null){
					return newBodyElement.stasis();
				}
			}
			for(com.rem.gen.parser.Token element:input.get(com.rem.gen.parser.Token.Id._as_statement).getAllSafely(com.rem.gen.parser.Token.Id._body_statement)){
				com.rem.output.helpers.Output newBodyElement = Classwise2.body_gen.statement(element,false,parentContext);
				if(newBodyElement!=null){
					return newBodyElement.stasis();
				}
			}
		}
		else if(input.get(com.rem.gen.parser.Token.Id._inline_method_reference)!=null){
			if(isInner==false){
				if(input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._name_var)!=null){
					return new com.rem.output.helpers.OutputOperator().left(Classwise2.classwise.name_var(input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._name_var),isInner,parentContext)).operator("::").right(new com.rem.output.helpers.OutputExact((input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._NAME)).toString()));
				}
				else{
					return new com.rem.output.helpers.OutputOperator().left(Classwise2.classwise.type_var(input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._type_var),isInner,parentContext)).operator("::").right(new com.rem.output.helpers.OutputExact((input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._NAME)).toString()));
				}
			}
			else{
				if(input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._method_prototype_parameters)!=null){
					com.rem.output.helpers.OutputArguments parameters = new com.rem.output.helpers.OutputArguments();
					for(com.rem.gen.parser.Token type_var:input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._method_prototype_parameters).getAllSafely(com.rem.gen.parser.Token.Id._type_var)){
						parameters.add(Classwise2.classwise.type_var(type_var,isInner,parentContext));
					}
					return Classwise2.classwise.type_var(input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._type_var),isInner,parentContext).getAsGetClass().add(new com.rem.output.helpers.OutputExact("getMethod"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact((input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._NAME)).toString()),parameters))));
				}
				else{
					return Classwise2.classwise.type_var(input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._type_var),isInner,parentContext).getAsGetClass().add(new com.rem.output.helpers.OutputExact("getMethod"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact((input.get(com.rem.gen.parser.Token.Id._inline_method_reference).get(com.rem.gen.parser.Token.Id._NAME)).toString())))));
				}
			}
		}
		com.rem.output.helpers.CallableOutput call = null;
		int groupIndex = 0;
		boolean hasMethodOrVariable = input.getAllSafely(com.rem.gen.parser.Token.Id._group).size()>1;
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._group)){
			com.rem.output.helpers.OutputArguments arguments = new com.rem.output.helpers.OutputArguments();
			boolean hasArguments = false;
			for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._method_arguments)){
				hasArguments=true;
				for(com.rem.gen.parser.Token quark:atom.getAllSafely(com.rem.gen.parser.Token.Id._method_argument)){
					arguments.add(Classwise2.body_gen.argument(quark,isInner,parentContext));
				}
			}
			for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._array_parameters)){
				hasArguments=true;
				if(atom.get(com.rem.gen.parser.Token.Id._method_argument)==null||(atom.get(com.rem.gen.parser.Token.Id._method_argument)).toString()==null){
					arguments.array(new com.rem.output.helpers.OutputBraced().style("[","]"));
				}
				else{
					for(com.rem.gen.parser.Token quark:atom.getAllSafely(com.rem.gen.parser.Token.Id._method_argument)){
						arguments.array(new com.rem.output.helpers.OutputBraced().set(Classwise2.body_gen.argument(quark,isInner,parentContext)).style("[","]"));
					}
				}
			}
			if(element.get(com.rem.gen.parser.Token.Id._all_type_name)!=null){
				com.rem.output.helpers.OutputType type = Classwise2.classwise.all_type(element.get(com.rem.gen.parser.Token.Id._all_type_name),isInner,parentContext);
				if(element.get(com.rem.gen.parser.Token.Id._NEW)!=null||(element.get(com.rem.gen.parser.Token.Id._all_type_name).get(com.rem.gen.parser.Token.Id._non_class_name)!=null&&((element.get(com.rem.gen.parser.Token.Id._all_type_name).get(com.rem.gen.parser.Token.Id._non_class_name)).toString().startsWith("H")||(element.get(com.rem.gen.parser.Token.Id._all_type_name).get(com.rem.gen.parser.Token.Id._non_class_name)).toString().startsWith("Helper"))==false)){
					call=new com.rem.output.helpers.OutputNewObject().set(type,arguments);
				}
				else{
					if(hasMethodOrVariable||isInner==false){
						call=new com.rem.output.helpers.OutputStaticCall().set(type);
					}
					else{
						call=type.getAsGetClass();
					}
				}
			}
			else{
				com.rem.output.helpers.Output nameVar;
				if(element.get(com.rem.gen.parser.Token.Id._name_var)!=null){
					nameVar=Classwise2.classwise.name_var(element.get(com.rem.gen.parser.Token.Id._name_var),isInner,parentContext);
				}
				else{
					String value = (element.get(com.rem.gen.parser.Token.Id._NAME)).toString();
					if(groupIndex==0&&Classwise2.classwise.mainClass.getMethod(value)!=null&&(parentContext==null||parentContext.hasVariableInContext(value)==false)){
						value=Classwise2.classwise.mainClassName+"."+value;
					}
					nameVar=new com.rem.output.helpers.OutputExact(value);
				}
				if(groupIndex==0 ){
					call=new com.rem.output.helpers.OutputCall();
				}
				if(hasArguments){
					call.add(nameVar,arguments);
				}
				else{
					call.add(nameVar);
				}
			}
			++groupIndex;
		}
		if(input.get(com.rem.gen.parser.Token.Id._cast_statement)!=null){
			com.rem.output.helpers.OutputCast cast = new com.rem.output.helpers.OutputCast();
			for(com.rem.gen.parser.Token atom:input.getAllSafely(com.rem.gen.parser.Token.Id._cast_statement)){
				cast.type(Classwise2.classwise.all_type(atom.get(com.rem.gen.parser.Token.Id._all_type_name),isInner,parentContext));
			}
			return cast.subject(call);
		}
		else{
			return call;
		}
	}
	public static com.rem.output.helpers.LineableOutput addToClass(com.rem.gen.parser.Token input,Boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		com.rem.output.helpers.OutputType type = Classwise2.classwise.type_var(input.get(com.rem.gen.parser.Token.Id._type_var),true,parentContext);
		com.rem.output.helpers.Output accessMethod;
		if(input.get(com.rem.gen.parser.Token.Id._accessMethod)!=null){
			if(input.get(com.rem.gen.parser.Token.Id._accessMethod).get(com.rem.gen.parser.Token.Id._name_var)!=null){
				accessMethod=Classwise2.classwise.name_var(input.get(com.rem.gen.parser.Token.Id._name_var),true,parentContext);
			}
			else{
				accessMethod=new com.rem.output.helpers.OutputExact((input.get(com.rem.gen.parser.Token.Id._accessMethod).get(com.rem.gen.parser.Token.Id._NAME)).toString());
			}
			if(input.get(com.rem.gen.parser.Token.Id._accessMethod).get(com.rem.gen.parser.Token.Id._method_prototype_parameters)!=null){
				com.rem.output.helpers.OutputArguments types = new com.rem.output.helpers.OutputArguments();
				for(com.rem.gen.parser.Token type_var:input.get(com.rem.gen.parser.Token.Id._accessMethod).get(com.rem.gen.parser.Token.Id._method_prototype_parameters).getAllSafely(com.rem.gen.parser.Token.Id._type_var)){
					types.add(Classwise2.classwise.type_var(type_var,true,parentContext));
				}
				accessMethod=new com.rem.output.helpers.OutputCall().add(accessMethod,types);
			}
			else{
				accessMethod=new com.rem.output.helpers.OutputCall().add(accessMethod,new com.rem.output.helpers.OutputArguments());
			}
		}
		else{
			accessMethod=null;
		}
		if(input.get(com.rem.gen.parser.Token.Id._class_declaration)!=null){
			return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputHelper")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("getClass"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(type))).add(new com.rem.output.helpers.OutputExact().set("encloseClass"),new com.rem.output.helpers.OutputArguments().add(Classwise2.classGenerator.declaration(input.get(com.rem.gen.parser.Token.Id._class_declaration),new com.rem.output.helpers.OutputClass(),false,parentContext).stasis())))));
		}
		else if(input.get(com.rem.gen.parser.Token.Id._method_declaration)!=null){
			return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputHelper")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("getClass"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(type))).add(new com.rem.output.helpers.OutputExact().set("method"),new com.rem.output.helpers.OutputArguments().add(Classwise2.method.declaration(input.get(com.rem.gen.parser.Token.Id._method_declaration),false,parentContext).stasis())))));
		}
		else if(input.get(com.rem.gen.parser.Token.Id._variable_declaration)!=null){
			return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputHelper")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("getClass"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(type))).add(new com.rem.output.helpers.OutputExact().set("variable"),new com.rem.output.helpers.OutputArguments().add(Classwise2.variable.declaration(input.get(com.rem.gen.parser.Token.Id._variable_declaration),false,parentContext).stasis())))));
		}
		else{
			com.rem.output.helpers.OutputBody arguments = new com.rem.output.helpers.OutputBody();
			if(input.get(com.rem.gen.parser.Token.Id._body).get(com.rem.gen.parser.Token.Id._as_statement)!=null){
				for(com.rem.gen.parser.Token element:input.get(com.rem.gen.parser.Token.Id._body).get(com.rem.gen.parser.Token.Id._as_statement).getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
					arguments.add(Classwise2.body_gen.element(element,false,parentContext));
				}
				for(com.rem.gen.parser.Token element:input.get(com.rem.gen.parser.Token.Id._body).get(com.rem.gen.parser.Token.Id._as_statement).getAllSafely(com.rem.gen.parser.Token.Id._body_statement)){
					arguments.add(Classwise2.body_gen.statement(element,false,parentContext));
				}
			}
			else{
				for(com.rem.gen.parser.Token element:input.get(com.rem.gen.parser.Token.Id._body).getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
					arguments.add(Classwise2.body_gen.element(element,false,parentContext));
				}
			}
			return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputHelper")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("getClass"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(type))).add(new com.rem.output.helpers.OutputExact().set("getMethod"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(accessMethod))).add(new com.rem.output.helpers.OutputExact().set("add"),new com.rem.output.helpers.OutputArguments().add(arguments.stasis())))));
		}
	}
	public static com.rem.output.helpers.LineableOutput accessToken(com.rem.gen.parser.Token input,Boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		String elementName = null;
		if(input.get(com.rem.gen.parser.Token.Id._variableName)!=null){
			elementName=input.get(com.rem.gen.parser.Token.Id._variableName).getValue();
		}
		com.rem.output.helpers.Output subject = Classwise2.classwise.tokenAccess(input.get(com.rem.gen.parser.Token.Id._tokenAccess),true,parentContext);
		Integer numberOfInstances = 0;
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._tokenInstance)){
			++numberOfInstances;
		}
		com.rem.output.helpers.OutputBody manipulateBody = new com.rem.output.helpers.OutputBody();
		String singleTokenName = null;
		com.rem.output.helpers.OutputType tokenType = new com.rem.output.helpers.OutputType("com.rem.gen.parser.Token");
		for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._tokenInstance)){
			String tokenName = (element.get(com.rem.gen.parser.Token.Id._tokenName)).toString();
			singleTokenName=tokenName;
			com.rem.output.helpers.OutputBody instanceBody = new com.rem.output.helpers.OutputBody().setParent(parentContext);
			String elementNameValue;
			if(elementName==null){
				elementNameValue=tokenName;
			}
			else{
				elementNameValue=elementName;
			}
			if(numberOfInstances!=1 &&elementName==null){
				instanceBody.add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(tokenType),new com.rem.output.helpers.OutputExact((elementName).toString())).assign(new com.rem.output.helpers.OutputExact(("__TEMP_TOKEN_ELEMENT_NAME__"+tempTokenElementIndex).toString())));
			}
			com.rem.gen.parser.Token lastElement = null;
			for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
				com.rem.output.helpers.LineableOutput newBodyElement = Classwise2.body_gen.element(atom,true,instanceBody);
				if(newBodyElement!=null){
					instanceBody.add(newBodyElement);
					lastElement=atom;
				}
			}
			if(numberOfInstances==1 ){
				manipulateBody.add(instanceBody);
			}
			else{
				if(lastElement!=null&&Classwise2.body_gen.elementHasReturn(lastElement)==false){
					instanceBody.add(new com.rem.output.helpers.OutputExact("break"));
				}
				manipulateBody.add(new com.rem.output.helpers.OutputConditional("case _"+(tokenName).toString()+" :").body(instanceBody));
			}
		}
		if(numberOfInstances==1 ){
			return new com.rem.output.helpers.OutputConditional("for").header(new com.rem.output.helpers.OutputConditionalHeader().declare(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(tokenType),new com.rem.output.helpers.OutputExact((elementName).toString()))).separator(":").call(new com.rem.output.helpers.OutputCall().add(subject).add(new com.rem.output.helpers.OutputExact("getAllSafely"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact("com.rem.gen.parser.Token.Id._"+(singleTokenName).toString()))))).body(manipulateBody);
		}
		else{
			if(elementName!=null){
				return new com.rem.output.helpers.OutputConditional("for").header(new com.rem.output.helpers.OutputConditionalHeader().declare(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(tokenType),new com.rem.output.helpers.OutputExact((elementName).toString()))).separator(":").call(new com.rem.output.helpers.OutputCall().add(subject).add(new com.rem.output.helpers.OutputExact("getAll"),new com.rem.output.helpers.OutputArguments()))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputConditional("switch").header(new com.rem.output.helpers.OutputConditionalHeader().call(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact((elementName).toString())).add(new com.rem.output.helpers.OutputExact("getName"),new com.rem.output.helpers.OutputArguments()))).body(manipulateBody)));
			}
			else{
				Integer tempTokenElementIndexValue = tempTokenElementIndex;
				++tempTokenElementIndex;
				return new com.rem.output.helpers.OutputConditional("for").header(new com.rem.output.helpers.OutputConditionalHeader().declare(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(tokenType),new com.rem.output.helpers.OutputExact(("__TEMP_TOKEN_ELEMENT_NAME__"+tempTokenElementIndexValue).toString()))).separator(":").call(new com.rem.output.helpers.OutputCall().add(subject).add(new com.rem.output.helpers.OutputExact("getAllSafely"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact("com.rem.gen.parser.Token.Id._"+(singleTokenName).toString()))))).body(manipulateBody);
			}
		}
	}
	public static com.rem.output.helpers.Output argument(com.rem.gen.parser.Token input,Boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		for(com.rem.gen.parser.Token element:input.getAll()){
			switch(element.getName()){
				case _class_declaration :{
					return Classwise2.classGenerator.declaration(element,new com.rem.output.helpers.OutputClass(),false,parentContext).stasis();
				}
				case _method_declaration :{
					return Classwise2.method.declaration(element,false,parentContext).stasis();
				}
				case _variable_declaration :{
					return Classwise2.variable.declaration(element,false,parentContext).stasis();
				}
				case _body_statement :{
					return Classwise2.body_gen.statement(element,isInner,parentContext);
				}
				case _as_statement :{
					if(element.get(com.rem.gen.parser.Token.Id._body_element)!=null){
						com.rem.output.helpers.OutputBody argumentBody = new com.rem.output.helpers.OutputBody();
						for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
							argumentBody.add(Classwise2.body_gen.element(atom,false,parentContext));
						}
						return argumentBody.stasis();
					}
					else if(element.get(com.rem.gen.parser.Token.Id._body_statement)!=null){
						return Classwise2.body_gen.statement(element.get(com.rem.gen.parser.Token.Id._body_statement),false,parentContext).stasis();
					}
				}
				case _body_entries :{
					com.rem.output.helpers.OutputBody elements = new com.rem.output.helpers.OutputBody();
					for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
						elements.add(Classwise2.body_gen.element(atom,false,parentContext));
					}
					return elements.stasis();
				}
				case _lambda :{
					com.rem.output.helpers.OutputLambda lambda = new com.rem.output.helpers.OutputLambda();
					for(com.rem.gen.parser.Token atom:element.getAllSafely(com.rem.gen.parser.Token.Id._variableName)){
						lambda.declare((atom).toString());
					}
					if(element.get(com.rem.gen.parser.Token.Id._body)!=null){
						for(com.rem.gen.parser.Token atom:element.get(com.rem.gen.parser.Token.Id._body).getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
							lambda.add(Classwise2.body_gen.element(atom,isInner,parentContext));
						}
					}
					else{
						lambda.call(Classwise2.body_gen.statement(element.get(com.rem.gen.parser.Token.Id._body_statement),isInner,parentContext));
					}
					return lambda;
				}
			}
		}
		return null;
	}
}
