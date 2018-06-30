package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.output.helpers.*;
import com.rem.parser.generation.classwise.*;
import com.rem.parser.generation.*;
import com.rem.parser.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.*;
import clgen.BodyGenerator;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import com.rem.gen.parser.Token;
public class  BodyGenerator   {
	public static class classes {
	}
	public static BodyGenerator variables = null;
	public static BodyGenerator methods = null;
	//Externals


	//Internals
protected Integer exceptionIndex = 0;
protected Integer tempTokenElementIndex = 0;

	public Integer getExceptionIndex()  {
		return exceptionIndex;
	}
	public Integer get_exceptionIndex()  {
		return exceptionIndex;
	}
	public Integer getTempTokenElementIndex()  {
		return tempTokenElementIndex;
	}
	public Integer get_tempTokenElementIndex()  {
		return tempTokenElementIndex;
	}
public boolean elementHasReturn(final Token input)  {
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("body_return")) {
			return true;
		}
		else if (element.getName().equals("body_conditional")) {
			return conditionalHasReturn(element);
		}
	}
	return false;
}
public boolean conditionalHasReturn(final Token input)  {
	for (final Token atom :  input.getAllSafely("as_body")) {
		Token lastElement = null;
		final List<Token> elements = atom.getAllSafely("body_element");
		int i = elements.size() - 1;
		while (i >= 0  && lastElement == null) {
			final String currentName = elements.get(i).getAll().get(0).getName();
			if (currentName.equals("comments") == false && currentName.equals("SYNTAX") == false) {
				lastElement = elements.get(i);
			}
			i -= 1;
		}
		if (lastElement != null) {
			return elementHasReturn(lastElement);
		}
	}
	return false;
}
public LineableOutput element(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("body_add_to_class")) {
			return addToClass(element,isInner,parentContext);
		}
		else if (element.getName().equals("body_access_token")) {
			return accessToken(element,isInner,parentContext);
		}
		else if (element.getName().equals("variable_declaration")) {
			return new OutputStatement().set(MainFlow.variables.get_variable().declaration(element,isInner,parentContext));
		}
		else if (element.getName().equals("variable_assignment")) {
			return MainFlow.variables.get_variable().assignment(element,isInner,parentContext);
		}
		else if (element.getName().equals("body_statement")) {
			return new OutputStatement().set(MainFlow.variables.get_body_gen().statement(element,isInner,parentContext));
		}
		else if (element.getName().equals("body_return")) {
			if (element.get("method_argument") != null) {
				return new OutputStatement().prefix("return ").set(MainFlow.variables.get_body_gen().argument(element.get("method_argument"),isInner,parentContext));
			}
			else  {
				return new OutputStatement().prefix("return ");
			}
		}
		else if (element.getName().equals("body_throw")) {
			return new OutputStatement().prefix("throw ").set(MainFlow.variables.get_body_gen().statement(element.get("body_statement"),isInner,parentContext));
		}
		else if (element.getName().equals("body_conditional")) {
			final OutputConditional resultingConditional = new OutputConditional();
			Boolean isCase = false;
			String conditionalName = "";
			exceptionIndex = 0;
			if (element.get("conditional").getAll() == null) {
				conditionalName = element.get("conditional").getValue().trim();
			}
			else  {
				final StringBuilder conditionalNameBuilder = new StringBuilder();
				String space = "";
				for (final Token conditionName :  element.get("conditional").getAll()) {
					conditionalNameBuilder.append(space);
					conditionalNameBuilder.append(conditionName.getValue().trim());
					space = " ";
				}
				conditionalName = conditionalNameBuilder.toString();
			}
			OutputConditionalHeader header = new OutputConditionalHeader();
			Output headerCall = null;
			if (element.get("body_statement") != null) {
				headerCall = MainFlow.variables.get_body_gen().statement(element.get("body_statement"),isInner,parentContext);
			}
			if (element.get("variable_declaration") != null) {
				for (final Token atom :  element.getAllSafely("variable_declaration")) {
					final String operator = element.get("OPERATOR").toString();
					if (operator.contains(":")) {
						header.declare(MainFlow.variables.get_variable().declaration(atom,isInner,parentContext));
						header.separator(":");
						header.call(headerCall);
					}
					else  {
						header.separator(";");
						final OutputVariable headerVariable = MainFlow.variables.get_variable().declaration(atom,isInner,parentContext);
						headerVariable.assign(new OutputExact("0"));
						header.declare(headerVariable);
						final OutputOperator rightSide = new OutputOperator().operator(";");
						rightSide.left(new OutputOperator().left(headerVariable.getName()).operator(operator).right(headerCall));
						rightSide.right(new OutputOperator().operator("++").right(headerVariable.getName()));
						header.call(rightSide);
					}
				}
			}
			else if (element.get("exception") != null) {
				final OutputType exceptionType = new OutputType();
				for (final Token atom :  element.getAllSafely("exception")) {
					final String exceptionTypeName;
					if (atom.getValue().contains("*")) {
						exceptionTypeName = "Exception";
					}
					else  {
						exceptionTypeName = OutputHelper.camelize(atom.getValue()) + "Exception";
					}
					if (exceptionType == null) {
						exceptionType.set(exceptionTypeName.toString());
					}
					else  {
						exceptionType.or(new OutputType().set(exceptionTypeName.toString()));
					}
				}
				header = new OutputConditionalHeader().declare(new OutputVariable().set(exceptionType,"e" + exceptionIndex.toString()));
			}
			else if (headerCall != null) {
				header.call(headerCall);
			}
			else  {
				header = null;
			}
			if (element.get("statement_as_method") != null) {
				resultingConditional.body(MainFlow.variables.get_body_gen().statement(element.get("statement_as_method").get("body_statement"),true,parentContext));
			}
			else  {
				final OutputBody resultingBody = new OutputBody();
				for (final Token atom :  element.getAllSafely("as_body")) {
					if (element.get("PRINT") != null) {
						resultingBody.add(new OutputCall().add(new OutputExact().set("e"+exceptionIndex.toString())).add(new OutputExact("printStackTrace"),new OutputArguments()));
					}
					for (final Token quark :  atom.getAllSafely("body_element")) {
						final LineableOutput bodyElem = MainFlow.variables.get_body_gen().element(quark,isInner,resultingBody);
						if (bodyElem != null) {
							resultingBody.add(bodyElem);
						}
					}
				}
				resultingConditional.body(resultingBody);
				if (element.get("exception") != null) {
					exceptionIndex += 1;
				}
			}
			if (conditionalName.contains("case")) {
				return resultingConditional.init(conditionalName.trim() + " " + headerCall.evaluate() + ":");
			}
			else  {
				return resultingConditional.init(conditionalName.trim()).header(header);
			}
		}
	}
	return null;
}
public Output statement(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	OutputOperator statement = new OutputOperator();
	Boolean isFirst = true;
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("body_call")) {
			if (isFirst) {
				statement.left(MainFlow.variables.get_body_gen().call(element,isInner,parentContext));
			}
			else  {
				statement.right(MainFlow.variables.get_body_gen().call(element,isInner,parentContext));
			}
			isFirst = false;
		}
		else if (element.getName().equals("OPERATOR")) {
			final OutputOperator nextStatement = new OutputOperator().left(statement);
			statement = nextStatement;
			if (element.get("exact") != null) {
				statement.operator(element.getValue().trim());
			}
			else  {
				statement.operator(element.getValue().substring(1).trim());
			}
		}
	}
	return statement;
}
public Output call(final Token input,final Boolean mustInner,final OutputContext parentContext)  {
	final Boolean isInner = mustInner || input.get("inner") != null;
	if (input.get("as_braced") != null) {
		return new OutputBraced().set(MainFlow.variables.get_body_gen().statement(input.get("as_braced").get("statement_as_braced").get("body_statement"),isInner,parentContext));
	}
	else if (input.get("as_statement") != null) {
		for (final Token element :  input.get("as_statement").getAllSafely("body_element")) {
			final Output newBodyElement = MainFlow.variables.get_body_gen().element(element,false,parentContext);
			if (newBodyElement != null) {
				return newBodyElement.stasis();
			}
		}
		for (final Token element :  input.get("as_statement").getAllSafely("body_statement")) {
			final Output newBodyElement = MainFlow.variables.get_body_gen().statement(element,false,parentContext);
			if (newBodyElement != null) {
				return newBodyElement.stasis();
			}
		}
	}
	CallableOutput call = null;
	int groupIndex = 0;
	final boolean hasMethodOrVariable = input.getAllSafely("group").size() > 1;
	for (final Token element :  input.getAllSafely("group")) {
		final OutputArguments arguments = new OutputArguments();
		boolean hasArguments = false;
		for (final Token atom :  element.getAllSafely("method_arguments")) {
			hasArguments = true;
			for (final Token quark :  atom.getAllSafely("method_argument")) {
				arguments.add(MainFlow.variables.get_body_gen().argument(quark,isInner,parentContext));
			}
		}
		for (final Token atom :  element.getAllSafely("array_parameters")) {
			hasArguments = true;
			if (atom.get("method_argument") == null || atom.get("method_argument").toString() == null) {
				arguments.array(new OutputBraced().style("[","]"));
			}
			else  {
				for (final Token quark :  atom.getAllSafely("method_argument")) {
					arguments.array(new OutputBraced().style("[","]"));
				}
			}
		}
		if (element.get("all_type_name") != null) {
			final OutputType type = MainFlow.variables.get_classwise().all_type(element.get("all_type_name"),isInner,parentContext);
			if (element.get("NEW") != null || element.get("all_type_name").get("non_class_name") != null) {
				call = new OutputNewObject().set(type,arguments);
			}
			else  {
				if (hasMethodOrVariable) {
					call = new OutputStaticCall().set(type);
				}
				else  {
					call = new OutputStaticCall().set(type).add(new OutputExact("_"));
				}
			}
		}
		else  {
			final Output nameVar;
			if (element.get("name_var") != null) {
				nameVar = MainFlow.variables.get_classwise().name_var(element.get("name_var"),isInner,parentContext);
			}
			else  {
				String value = element.get("NAME").toString();
				if (groupIndex == 0 && MainFlow.variables.get_classwise().getMainClass().getMethod(value) != null && (parentContext == null || parentContext.hasVariableInContext(value) == false)) {
					value = "MainFlow.self." + value;
				}
				nameVar = new OutputExact().set(value);
			}
			if (groupIndex == 0 ) {
				call = new OutputCall();
			}
			if (hasArguments) {
				call.add(nameVar,arguments);
			}
			else  {
				call.add(nameVar);
			}
		}
		groupIndex += 1;
	}
	if (input.get("cast_statement") != null) {
		final OutputCast cast = new OutputCast();
		for (final Token atom :  input.getAllSafely("cast_statement")) {
			cast.type(MainFlow.variables.get_classwise().all_type(atom.get("all_type_name"),isInner,parentContext));
		}
		return cast.subject(call);
	}
	else  {
		return call;
	}
}
public LineableOutput addToClass(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	final OutputType type = MainFlow.variables.get_classwise().type_var(input.get("type_var"),true,parentContext);
	final Output accessMethod;
	if (input.get("accessMethod") != null) {
		if (input.get("accessMethod").get("name_var") != null) {
			accessMethod = MainFlow.variables.get_classwise().name_var(input.get("name_var"),true,parentContext);
		}
		else  {
			accessMethod = new OutputExact().set(input.get("accessMethod").get("NAME").toString());
		}
	}
	else  {
		accessMethod = null;
	}
	if (input.get("class_declaration") != null) {
		return new OutputStatement().set(new OutputCall().add(type).add(new OutputExact("getAsClass"),new OutputArguments()).add(new OutputExact("addEnclosedClass"),new OutputArguments().add(MainFlow.variables.get_classGenerator().declaration(input.get("class_declaration"),new OutputClass(),false,parentContext).stasis())));
	}
	else if (input.get("method_declaration") != null) {
		return new OutputStatement().set(new OutputCall().add(type).add(new OutputExact("getAsClass"),new OutputArguments()).add(new OutputExact("addMethod"),new OutputArguments().add(MainFlow.variables.get_method().declaration(input.get("method_declaration"),false,parentContext).stasis())));
	}
	else if (input.get("variable_declaration") != null) {
		return new OutputStatement().set(new OutputCall().add(type).add(new OutputExact("getAsClass"),new OutputArguments()).add(new OutputExact("addVariable"),new OutputArguments().add(MainFlow.variables.get_variable().declaration(input.get("variable_declaration"),false,parentContext).stasis())));
	}
	else  {
		final OutputBody arguments = new OutputBody();
		if (input.get("body").get("as_statement") != null) {
			for (final Token element :  input.get("body").get("as_statement").getAllSafely("body_element")) {
				arguments.add(MainFlow.variables.get_body_gen().element(element,false,parentContext));
			}
			for (final Token element :  input.get("body").get("as_statement").getAllSafely("body_statement")) {
				arguments.add(MainFlow.variables.get_body_gen().statement(element,false,parentContext));
			}
		}
		else  {
			for (final Token element :  input.get("body").getAllSafely("body_element")) {
				arguments.add(MainFlow.variables.get_body_gen().element(element,false,parentContext));
			}
		}
		return new OutputStatement().set(new OutputCall().add(type).add(new OutputExact("getAsClass"),new OutputArguments()).add(new OutputExact("getMethod"),new OutputArguments().add(accessMethod)).add(new OutputExact("add"),new OutputArguments().add(arguments.stasis())));
	}
}
public LineableOutput accessToken(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	String elementName = null;
	if (input.get("variableName") != null) {
		elementName = input.get("variableName").getValue();
	}
	final Output subject = MainFlow.variables.get_classwise().tokenAccess(input.get("tokenAccess"),true,parentContext);
	Integer numberOfInstances = 0;
	for (final Token element :  input.getAllSafely("tokenInstance")) {
		numberOfInstances += 1;
	}
	final OutputBody manipulateBody = new OutputBody();
	String singleTokenName = null;
	final OutputType tokenType = new OutputType("com.rem.gen.parser.Token");
	for (final Token element :  input.getAllSafely("tokenInstance")) {
		final String tokenName = element.get("tokenName").toString();
		singleTokenName = tokenName;
		final OutputBody instanceBody = new OutputBody().setParent(parentContext);
		final String elementNameValue;
		if (elementName == null) {
			elementNameValue = tokenName;
		}
		else  {
			elementNameValue = elementName;
		}
		if (numberOfInstances != 1  && elementName == null) {
			instanceBody.add(new OutputVariable().set(tokenType,new OutputExact(elementName.toString())).assign(new OutputExact().set("__TEMP_TOKEN_ELEMENT_NAME__" + tempTokenElementIndex)));
		}
		Token lastElement = null;
		for (final Token atom :  element.getAllSafely("body_element")) {
			final LineableOutput newBodyElement = MainFlow.variables.get_body_gen().element(atom,true,instanceBody);
			if (newBodyElement != null) {
				instanceBody.add(newBodyElement);
				lastElement = atom;
			}
		}
		if (numberOfInstances == 1 ) {
			manipulateBody.add(instanceBody);
		}
		else  {
			if (lastElement != null && MainFlow.variables.get_body_gen().elementHasReturn(lastElement) == false) {
				instanceBody.add(new OutputExact().set("break"));
			}
			manipulateBody.add(new OutputConditional().init("case _" + tokenName.toString() + " :").body(instanceBody));
		}
	}
	if (numberOfInstances == 1 ) {
		return new OutputConditional().init("for").header(new OutputConditionalHeader().declare(new OutputVariable().set(tokenType,new OutputExact(elementName.toString()))).separator(":").call(new OutputCall().add(subject).add(new OutputExact("getAllSafely"),new OutputArguments().add(new OutputExact("com.rem.gen.parser.Token.Id._" + singleTokenName.toString()))))).body(manipulateBody);
	}
	else  {
		if (elementName != null) {
			return new OutputConditional().init("for").header(new OutputConditionalHeader().declare(new OutputVariable().set(tokenType,new OutputExact(elementName))).separator(":").call(new OutputCall().add(subject).add(new OutputExact("getAll"),new OutputArguments()))).body(new OutputBody().add(new OutputConditional().init("switch").header(new OutputConditionalHeader().call(new OutputCall().add(new OutputExact(elementName.toString())).add(new OutputExact("getName"),new OutputArguments()))).body(manipulateBody)));
		}
		else  {
			final Integer tempTokenElementIndexValue = tempTokenElementIndex;
			tempTokenElementIndex += 1;
			return new OutputConditional().init("for").header(new OutputConditionalHeader().declare(new OutputVariable().set(tokenType,new OutputExact("__TEMP_TOKEN_ELEMENT_NAME__" + tempTokenElementIndexValue.toString()))).separator(":").call(new OutputCall().add(subject).add(new OutputExact("getAllSafely"),new OutputArguments().add(new OutputExact("com.rem.gen.parser.Token.Id._" + singleTokenName.toString()))))).body(manipulateBody);
		}
	}
}
public Output argument(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("class_declaration")) {
			return MainFlow.variables.get_classGenerator().declaration(element,new OutputClass(),false,parentContext).stasis();
		}
		else if (element.getName().equals("method_declaration")) {
			return MainFlow.variables.get_method().declaration(element,false,parentContext).stasis();
		}
		else if (element.getName().equals("variable_declaration")) {
			return MainFlow.variables.get_variable().declaration(element,false,parentContext).stasis();
		}
		else if (element.getName().equals("body_statement")) {
			return MainFlow.variables.get_body_gen().statement(element,isInner,parentContext);
		}
		else if (element.getName().equals("as_statement")) {
			if (element.get("body_element") != null) {
				final OutputBody argumentBody = new OutputBody();
				for (final Token atom :  element.getAllSafely("body_element")) {
					argumentBody.add(MainFlow.variables.get_body_gen().element(atom,false,parentContext));
				}
				return argumentBody.stasis();
			}
			else if (element.get("body_statement") != null) {
				return MainFlow.variables.get_body_gen().statement(element.get("body_statement"),false,parentContext).stasis();
			}
		}
		else if (element.getName().equals("body_entries")) {
			final OutputBody elements = new OutputBody();
			for (final Token atom :  element.getAllSafely("body_element")) {
				elements.add(MainFlow.variables.get_body_gen().element(atom,false,parentContext));
			}
			return elements.stasis();
		}
		else if (element.getName().equals("lambda")) {
			final OutputLambda lambda = new OutputLambda();
			for (final Token atom :  element.getAllSafely("variableName")) {
				lambda.declare(atom.toString());
			}
			if (element.get("body") != null) {
				for (final Token atom :  element.get("body").getAllSafely("body_element")) {
					lambda.add(MainFlow.variables.get_body_gen().element(atom,isInner,parentContext));
				}
			}
			else  {
				lambda.call((CallableOutput) MainFlow.variables.get_body_gen().call(element.get("body_call"),isInner,parentContext));
			}
			return lambda;
		}
	}
	return null;
}

}