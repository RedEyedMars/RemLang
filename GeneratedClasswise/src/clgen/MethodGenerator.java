package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.parser.generation.classwise.*;
import com.rem.parser.generation.*;
import com.rem.parser.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.*;
import clgen.MethodGenerator;
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
public class  MethodGenerator   {
	public static class classes {
	}
	public static MethodGenerator variables = null;
	public static MethodGenerator methods = null;
	//Externals


	//Internals
protected final Set<String> definedMethodNames = new HashSet<String>();

	public Set<String> getDefinedMethodNames()  {
		return definedMethodNames;
	}
	public Set<String> get_definedMethodNames()  {
		return definedMethodNames;
	}
public void addDefinedMethodName(final ExternalMethodEntry newMethod)  {
	definedMethodNames.add(newMethod.getName());
}
public ExternalMethodEntry declaration(final Token declaration,final Boolean isInner,final ExternalContext parentContext)  {
	return definition(declaration,isInner,parentContext);
}
public ExternalMethodEntry definition(final Token input,final Boolean mustInner,final ExternalContext parentContext)  {
	final Boolean isInner = mustInner || input.get("inner") != null;
	final ExternalMethodEntry newMethod = new ExternalMethodEntry();
	final Type methodType = new Type();
	for (final Token element :  input.getAllSafely("typeName")) {
		MainFlow.variables.get_classwise().all_type(element,methodType,isInner,parentContext);
	}
	if (input.get("ARRAY_TYPE") != null) {
		for (final Token element :  input.getAllSafely("ARRAY_TYPE")) {
			methodType.addArraySymbol();
		}
	}
	newMethod.setType(methodType.getAsStatement());
	final ExternalStatement.Body methodBody = new ExternalStatement.Body();
	methodBody.setParentContext(parentContext);
	final ExternalContext context = methodBody.getContext();
	if ((input.get("inline") != null)) {
		if (input.get("inline").get("method_parameters") != null) {
			final List<ExternalVariableEntry> parameters = new ArrayList<ExternalVariableEntry>();
			for (final Token element :  input.get("inline").get("method_parameters").getAllSafely("variable_declaration")) {
				parameters.add(MainFlow.variables.get_variable().declaration(element,isInner,parentContext));
			}
			newMethod.setParameters(parameters);
		}
	}
	else if (input.get("variableParameters") != null) {
		newMethod.setParametersAsStatement(MainFlow.variables.get_body().statement(input.get("variableParameters").get("body_statement"),true,parentContext));
	}
	else  {
		newMethod.setParameters(new ArrayList<ExternalVariableEntry>());
	}
	if (input.get("throwException") != null) {
		final StringBuilder exceptionBuilder = new StringBuilder();
		String comma = "";
		for (final Token element :  input.getAllSafely("throwException")) {
			exceptionBuilder.append(comma);
			exceptionBuilder.append(element.toString());
			exceptionBuilder.append("Exception");
			comma = ",";
		}
		newMethod.setThrowsStatement(exceptionBuilder.toString());
	}
	for (final Token element :  input.getAllSafely("body_element")) {
		methodBody.add(MainFlow.variables.get_body().element(element,isInner,context));
	}
	if (input.get("methodName").get("NAME") != null) {
		newMethod.setName(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(input.get("methodName").get("NAME").toString())))));
	}
	else  {
		final NameVar nameVar = new NameVar();
		MainFlow.variables.get_classwise().name_var(input.get("methodName").get("name_var"),nameVar,isInner,parentContext);
		newMethod.setName(nameVar.getAsStatement());
	}
	newMethod.setBody(methodBody);
	if (input.get("static") != null) {
		newMethod.setIsStatic(true);
	}
	return newMethod;
}

}