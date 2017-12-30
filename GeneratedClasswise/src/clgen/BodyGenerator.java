package clgen;
import java.util.*;
import java.io.*;
import lists.*;
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
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import com.rem.parser.generation.classwise.ExternalStatement;
import clgen.TypeStatement;
import java.lang.StringBuilder;
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
public ExternalStatement element(final Token input,final Boolean isInner,final ExternalContext parentContext)  {
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("body_manipulate")) {
			return manipulate(element,isInner,parentContext);
		}
		else if (element.getName().equals("body_line")) {
			if (element.get("variable_declaration") != null) {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_variable().declaration(element.get("variable_declaration"),isInner,parentContext))))));
			}
			else if (element.get("variable_assignment") != null) {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_variable().assignment(element.get("variable_assignment"),isInner,parentContext))))));
			}
			else if (element.get("body_statement") != null) {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().statement(element.get("body_statement"),isInner,parentContext))))));
			}
		}
		else if (element.getName().equals("body_return")) {
			if (element.get("method_argument") != null) {
				final Boolean returnIsInner = isInner || element.get("inner") != null;
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().argument(element.get("method_argument"),returnIsInner,parentContext))))));
			}
			else  {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Exac*/new ExternalStatement(new StringEntry(""))));
			}
		}
		else if (element.getName().equals("body_throw")) {
			final Boolean throwIsInner = isInner || element.get("inner") != null;
			return /*InCl*/new ExternalStatement(
		/*Thrw*/new ExternalStatement(new TabEntry(new StringEntry("throw new RuntimeException(\"")), new StringEntry("\");"),"", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().statement(element.get("body_statement"),throwIsInner,parentContext))))));
		}
		else if (element.getName().equals("class_declaration")) {
			final ExternalClassEntry innerClass = new ExternalClassEntry();
			final ExternalClassEntry outerClass = new ExternalClassEntry();
			MainFlow.variables.get_classGenerator().declaration(element.get("class_declaration"),innerClass,outerClass,false,parentContext);
			final String variableName = outerClass.getName() + "Class";
			parentContext.add(new ExternalVariableEntry(false,false, false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("ExternalClassEntry"))),"", /*Enty*/new ExternalStatement(new StringEntry(variableName.toString()))));
			return /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false,false, false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Exac*/new ExternalStatement(new StringEntry("ExternalClassEntry")))),"", /*Enty*/new ExternalStatement(new StringEntry(variableName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(outerClass.getAsStatement()))))),
/*BODY*/				
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(variableName.toString()))), /*Enty*/new ExternalStatement(new StringEntry("__INIT__"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))),
/*BODY*/				
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("MainFlow"))), /*Enty*/new ExternalStatement(new StringEntry("outputClasses"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(variableName.toString()))))))))));
		}
		else if (element.getName().equals("body_conditional")) {
			final Boolean conditionalIsInner = isInner || element.get("inner") != null;
			Boolean isCase = false;
			String conditionalName = "";
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
			ExternalStatement statement = null;
			if (element.get("body_statement") != null) {
				statement = MainFlow.variables.get_body().statement(element.get("body_statement"),isInner,parentContext);
			}
			for (final Token atom :  element.getAllSafely("variable_declaration")) {
				final String operator = element.get("OPERATOR").toString();
				if (operator.contains(":")) {
					final ExternalStatement headerStatement = new ExternalStatement(":");
					final ExternalVariableEntry headerVariable = MainFlow.variables.get_variable().declaration(atom,conditionalIsInner,parentContext);
					headerStatement.add(headerVariable);
					headerStatement.add(statement);
					statement = headerStatement;
				}
				else  {
					final ExternalStatement headerStatement = new ExternalStatement(";");
					final ExternalVariableEntry headerVariable = MainFlow.variables.get_variable().declaration(atom,conditionalIsInner,parentContext);
					headerVariable.setAssignment(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))));
					headerVariable.setIsFinal(false);
					headerStatement.add(headerVariable);
					final ExternalStatement evaluationStatement = new ExternalStatement(operator);
					evaluationStatement.add(headerVariable.getNameAsStatement());
					evaluationStatement.add(statement);
					headerStatement.add(evaluationStatement);
					final ExternalStatement incrementationStatement = new ExternalStatement();
					incrementationStatement.add(headerVariable.getNameAsStatement());
					incrementationStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++")))));
					headerStatement.add(incrementationStatement);
					statement = headerStatement;
				}
			}
			for (final Token atom :  element.getAllSafely("exception")) {
				final String exceptionType;
				if (atom.getValue().contains("*")) {
					exceptionType = "Exception";
				}
				else  {
					final String exceptionTypeName = atom.getValue();
					exceptionType = FlowController.camelize(exceptionTypeName.toString()) + "Exception";
				}
				if (statement == null) {
					statement = /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(exceptionType.toString()))));
				}
				else  {
					final ExternalStatement previousStatement = statement;
					statement = /*Optr*/new ExternalStatement("|", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(previousStatement))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(exceptionType.toString()))));
				}
			}
			if (element.get("exception") != null) {
				final String exceptionVariableName = "e" + exceptionIndex.toString();
				final ExternalStatement exceptionStatement = new ExternalStatement(" ");
				exceptionStatement.add(statement);
				exceptionStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(exceptionVariableName.toString())))));
				statement = exceptionStatement;
			}
			final ExternalStatement.Body conditionalBody = new ExternalStatement.Body();
			for (final Token atom :  element.getAllSafely("as_body")) {
				if (element.get("PRINT") != null) {
					conditionalBody.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("e")), /*Enty*/new ExternalStatement(new StringEntry(exceptionIndex.toString()))))), /*Enty*/new ExternalStatement(new StringEntry("printStackTrace"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))));
				}
				for (final Token quark :  atom.getAllSafely("body_element")) {
					final ExternalStatement bodyElem = MainFlow.variables.get_body().element(quark,conditionalIsInner,conditionalBody.getContext());
					if (bodyElem != null) {
						conditionalBody.add(bodyElem);
					}
				}
			}
			if (element.get("exception") != null) {
				exceptionIndex += 1;
			}
			if (element.get("as_method") != null) {
				conditionalBody.add(MainFlow.variables.get_body().statement(element.get("as_method").get("body_statement"),true,parentContext));
			}
			if (conditionalName.contains("case")) {
				final ExternalStatement previousStatement = statement;
				return new ExternalStatement.Conditional(conditionalName,/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*InCl*/new ExternalStatement(previousStatement), /*Name*/new ExternalStatement(new StringEntry(":")))))),conditionalBody);
			}
			else  {
				return new ExternalStatement.Conditional(conditionalName,statement,conditionalBody);
			}
		}
	}
	return null;
}
public ExternalStatement statement(final Token input,final Boolean mustInner,final ExternalContext parentContext)  {
	final Boolean isInner = mustInner || input.get("inner") != null;
	final ExternalStatement statement = new ExternalStatement();
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("statement_as_char")) {
			return new ExternalStatement(new StringEntry("\'"),new StringEntry("\'"),new ExternalStatement(new StringEntry(element.toString())));
		}
		if (element.getName().equals("statement_as_string")) {
			if (isInner) {
				return /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().statement(element.get("body_statement"),true,parentContext))), /*Enty*/new ExternalStatement(new StringEntry("toString"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())));
			}
			else  {
				return new ExternalStatement(new VariableNameEntry(MainFlow.variables.get_body().statement(element.get("body_statement"),true,parentContext)));
			}
		}
		else if (element.getName().equals("body_call")) {
			statement.add(MainFlow.variables.get_body().call(element,isInner,parentContext));
		}
		else if (element.getName().equals("OPERATOR")) {
			statement.set(element.getValue().trim());
		}
	}
	return statement;
}
public ExternalStatement call(final Token input,final Boolean mustInner,final ExternalContext parentContext)  {
	final Boolean isInner = mustInner || input.get("inner") != null;
	if (input.get("as_braced") != null) {
		final ExternalStatement call = new ExternalStatement();
		call.add(/*Name*/new ExternalStatement(/*Brac*/new ExternalStatement(new StringEntry("("),new StringEntry(")"),"", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().statement(input.get("as_braced").get("left").get("statement_as_braced").get("body_statement"),isInner,parentContext)))))));
		if (input.get("as_braced").get("OPERATOR") != null) {
			call.set(input.get("as_braced").get("OPERATOR").getValue().trim());
		}
		if (input.get("as_braced").get("right") != null) {
			call.add(MainFlow.variables.get_body().statement(input.get("as_braced").get("right").get("body_statement"),isInner,parentContext));
		}
		return call;
	}
	final ExternalStatement statement = new ExternalStatement();
	statement.set(".");
	for (final Token element :  input.getAllSafely("group")) {
		final ExternalStatement.Parameters parameters = new ExternalStatement.Parameters();
		final ExternalStatement.Parameters arrayParameters = new ExternalStatement.Parameters();
		ExternalStatement subject = null;
		if (element.get("type_var") != null) {
			final Type subjectAsType = new Type();
			for (final Token atom :  element.getAllSafely("type_var")) {
				MainFlow.variables.get_classwise().type_var(atom,subjectAsType,isInner,parentContext);
			}
			if (element.get("NEW") == null) {
				subjectAsType.as_variable();
			}
			subject = subjectAsType.getAsStatement();
		}
		else if (element.get("typeName") != null) {
			final Type subjectAsType = new Type();
			for (final Token atom :  element.getAllSafely("typeName")) {
				MainFlow.variables.get_classwise().all_type(atom,subjectAsType,isInner,parentContext);
			}
			if (element.get("NEW") == null) {
				subjectAsType.as_variable();
			}
			subject = subjectAsType.getAsStatement();
		}
		else if (element.get("name_var") != null) {
			final NameVar nameVar = new NameVar();
			MainFlow.variables.get_classwise().name_var(element.get("name_var"),nameVar,isInner,parentContext);
			subject = nameVar.getAsStatement();
		}
		else  {
			subject = /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.get("NAME").toString()))));
		}
		Boolean containsParameters = false;
		for (final Token atom :  element.getAllSafely("method_arguments")) {
			containsParameters = true;
			for (final Token quark :  atom.getAllSafely("method_argument")) {
				parameters.add(MainFlow.variables.get_body().argument(quark,isInner,parentContext));
			}
		}
		Boolean containsArrayParameters = false;
		for (final Token atom :  element.getAllSafely("array_parameters")) {
			containsArrayParameters = true;
			if (atom.get("method_argument") == null) {
				arrayParameters.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("")))));
			}
			else  {
				for (final Token quark :  atom.getAllSafely("method_argument")) {
					arrayParameters.add(MainFlow.variables.get_body().argument(quark,isInner,parentContext));
				}
			}
		}
		if (element.get("NEW") != null) {
			final ExternalStatement subjectAsType = subject;
			if (containsArrayParameters) {
				if (containsParameters) {
					statement.add(/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*InCl*/new ExternalStatement(subjectAsType))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(parameters)))), new ExternalStatement.ArrayParameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(arrayParameters)))))));
				}
				else  {
					statement.add(/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*InCl*/new ExternalStatement(subjectAsType))),new ExternalStatement.ArrayParameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(arrayParameters)))))));
				}
			}
			else  {
				statement.add(/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*InCl*/new ExternalStatement(subjectAsType))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(parameters)))))));
			}
		}
		else  {
			final ExternalStatement subjectAsSubject = subject;
			if (containsParameters) {
				if (containsArrayParameters) {
					statement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",/*InCl*/new ExternalStatement(subjectAsSubject),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(parameters))))),
			 	new ExternalStatement.ArrayParameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(arrayParameters)))))));
				}
				else  {
					statement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*InCl*/new ExternalStatement(subjectAsSubject),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(parameters)))))));
				}
			}
			else  {
				if (containsArrayParameters) {
					statement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement("", /*InCl*/new ExternalStatement(subjectAsSubject), new ExternalStatement.ArrayParameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(arrayParameters)))))));
				}
				else  {
					statement.add(subject);
				}
			}
		}
	}
	if (statement.size() == 1 ) {
		return statement.get(0);
	}
	else  {
		return statement;
	}
}
public ExternalStatement manipulate(final Token input,final Boolean isInner,final ExternalContext parentContext)  {
	if (input.get("type_var") != null) {
		Type type = new Type();
		MainFlow.variables.get_classwise().type_var(input.get("type_var"),type,true,parentContext);
		final NameVar access = new NameVar();
		if (input.get("name_var") != null) {
			MainFlow.variables.get_classwise().name_var(input.get("name_var"),access,true,parentContext);
		}
		type.as_entry();
		if (input.get("class_declaration") != null) {
			final ExternalClassEntry innerClass = new ExternalClassEntry();
			final ExternalClassEntry outerClass = new ExternalClassEntry();
			MainFlow.variables.get_classGenerator().declaration(input.get("class_declaration"),innerClass,outerClass,false,parentContext);
			if (input.get("methodName").getValue().contains("+=")) {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(type.getAsStatement())), /*Enty*/new ExternalStatement(new StringEntry("addSubClass"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(outerClass.getAsStatement())))))))));
			}
			else  {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(type.getAsStatement())), /*Enty*/new ExternalStatement(new StringEntry(input.get("methodName").toString()))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(outerClass.getAsStatement())))))))));
			}
		}
		else if (input.get("method_declaration") != null) {
			final ExternalMethodEntry newMethod = MainFlow.variables.get_method().declaration(input.get("method_declaration"),false,parentContext);
			if (input.get("methodName").getValue().contains("+=")) {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(type.getAsStatement())), /*Enty*/new ExternalStatement(new StringEntry("addMethod"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(newMethod.getAsStatement())))))))));
			}
			else  {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(type.getAsStatement())), /*Enty*/new ExternalStatement(new StringEntry(input.get("methodName").toString()))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(newMethod.getAsStatement())))))))));
			}
		}
		else if (input.get("variable_declaration") != null) {
			final ExternalVariableEntry newVariable = MainFlow.variables.get_variable().declaration(input.get("variable_declaration"),false,parentContext);
			if (input.get("methodName").getValue().contains("+=")) {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(type.getAsStatement())), /*Enty*/new ExternalStatement(new StringEntry("addVariable"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(newVariable.getAsStatement())))))))));
			}
			else  {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(type.getAsStatement())), /*Enty*/new ExternalStatement(new StringEntry(input.get("methodName").toString()))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(newVariable.getAsStatement())))))))));
			}
		}
		else if (input.get("body") != null) {
			final ExternalStatement.Parameters arguments = new ExternalStatement.Parameters();
			for (final Token element :  input.get("body").getAllSafely("body_element")) {
				final ExternalStatement newBodyElement = MainFlow.variables.get_body().element(element,false,new ExternalContext(true,parentContext));
				if (newBodyElement != null) {
					arguments.add(newBodyElement);
				}
			}
			if (input.get("methodName").getValue().contains("+=")) {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(type.getAsStatement())), /*Enty*/new ExternalStatement(new StringEntry("appendToBody"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(arguments)))))))));
			}
			else  {
				return /*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(type.getAsStatement())), /*Enty*/new ExternalStatement(new StringEntry(input.get("methodName").toString()))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(arguments)))))))));
			}
		}
	}
	else  {
		final ExternalStatement subject;
		String elementName = null;
		if (input.get("variableName") != null) {
			elementName = input.get("variableName").getValue();
		}
		if (input.get("name_var") != null) {
			final NameVar nameVar = new NameVar();
			MainFlow.variables.get_classwise().name_var(input.get("name_var"),nameVar,true,parentContext);
			subject = nameVar.getAsStatement();
		}
		else  {
			subject = new ExternalStatement();
		}
		final ExternalStatement.Body manipulateBody = new ExternalStatement.Body();
		Integer numberOfInstances = 0;
		for (final Token element :  input.getAllSafely("tokenInstance")) {
			numberOfInstances += 1;
		}
		String singleTokenName = null;
		for (final Token element :  input.getAllSafely("tokenInstance")) {
			final String tokenName = element.get("tokenName").toString();
			singleTokenName = tokenName;
			final ExternalStatement.Body instanceBody = new ExternalStatement.Body();
			final ExternalContext bodyContext = new ExternalContext(true,parentContext);
			final String elementNameValue;
			if (elementName == null) {
				elementNameValue = tokenName;
			}
			else  {
				elementNameValue = elementName;
			}
			if (numberOfInstances != 1  && elementName == null) {
				final String finalTokenType = "final Token";
				final Integer tempTokenElementIndexValue = tempTokenElementIndex;
				instanceBody.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false,false, false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(finalTokenType.toString())))),"", /*Enty*/new ExternalStatement(new StringEntry(elementName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("__TEMP_TOKEN_ELEMENT_NAME__")), /*Enty*/new ExternalStatement(new StringEntry(tempTokenElementIndexValue.toString()))))))))));
			}
			for (final Token atom :  element.getAllSafely("body_element")) {
				final ExternalStatement newBodyElement = MainFlow.variables.get_body().element(atom,true,bodyContext);
				if (newBodyElement != null) {
					instanceBody.add(newBodyElement);
				}
			}
			if (numberOfInstances == 1 ) {
				manipulateBody.add(instanceBody);
			}
			else  {
				manipulateBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(elementNameValue.toString()))), /*Enty*/new ExternalStatement(new StringEntry("getName"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())), /*Enty*/new ExternalStatement(new StringEntry("equals"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(tokenName.toString().toString())))))))),
			/*InCl*/new ExternalStatement(instanceBody))));
			}
		}
		if (numberOfInstances == 1 ) {
			final String singleTokenNameValue = singleTokenName;
			return /*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"for ", 
			/*Optr*/new ExternalStatement(": ", new ExternalVariableEntry(false,false, false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))),"", /*Enty*/new ExternalStatement(new StringEntry(elementName.toString()))), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(subject)), /*Enty*/new ExternalStatement(new StringEntry("getAllSafely"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(singleTokenNameValue.toString().toString()))))))))),
			/*InCl*/new ExternalStatement(manipulateBody)));
		}
		else  {
			if (elementName != null) {
				return /*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"for ", 
			/*Optr*/new ExternalStatement(": ", new ExternalVariableEntry(false,false, false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))),"", /*Enty*/new ExternalStatement(new StringEntry(elementName.toString()))), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(subject)), /*Enty*/new ExternalStatement(new StringEntry("getAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))),
			/*InCl*/new ExternalStatement(manipulateBody)));
			}
			else  {
				final Integer tempTokenElementIndexValue = tempTokenElementIndex;
				tempTokenElementIndex += 1;
				return /*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"for ", 
			/*Optr*/new ExternalStatement(": ", new ExternalVariableEntry(false,false, false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))),"", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("__TEMP_TOKEN_ELEMENT_NAME__")), /*Enty*/new ExternalStatement(new StringEntry(tempTokenElementIndexValue.toString()))))), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(subject)), /*Enty*/new ExternalStatement(new StringEntry("getAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))),
			/*InCl*/new ExternalStatement(manipulateBody)));
			}
		}
	}
	return null;
}
public ExternalStatement argument(final Token input,final Boolean isInner,final ExternalContext parentContext)  {
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("class_declaration")) {
			final ExternalClassEntry innerClass = new ExternalClassEntry();
			final ExternalClassEntry outerClass = new ExternalClassEntry();
			MainFlow.variables.get_classGenerator().declaration(element,innerClass,outerClass,false,parentContext);
			return outerClass.getAsStatement();
		}
		else if (element.getName().equals("method_declaration")) {
			return MainFlow.variables.get_method().declaration(element,false,parentContext).getAsStatement();
		}
		else if (element.getName().equals("variable_declaration")) {
			return MainFlow.variables.get_variable().declaration(element,false,parentContext).getAsStatement();
		}
		else if (element.getName().equals("body_statement")) {
			return MainFlow.variables.get_body().statement(element,isInner,parentContext);
		}
		else if (element.getName().equals("as_statement")) {
			if (element.get("body_element") != null) {
				final ExternalStatement.Body argumentBody = new ExternalStatement.Body();
				for (final Token atom :  element.getAllSafely("body_element")) {
					final ExternalStatement newBodyElement = MainFlow.variables.get_body().element(atom,false,parentContext);
					if (newBodyElement != null) {
						argumentBody.add(newBodyElement);
					}
				}
				return argumentBody.getAsStatement();
			}
			else if (element.get("body_statement") != null) {
				return MainFlow.variables.get_body().statement(element.get("body_statement"),false,parentContext).getAsStatement();
			}
		}
		else if (element.getName().equals("body_entries")) {
			final ExternalStatement.Body elements = new ExternalStatement.Body();
			for (final Token atom :  element.getAllSafely("body_element")) {
				final ExternalStatement newBodyElement = MainFlow.variables.get_body().element(atom,false,parentContext);
				if (newBodyElement != null) {
					elements.add(newBodyElement);
				}
			}
			return elements.getAsStatement();
		}
	}
	return null;
}

}