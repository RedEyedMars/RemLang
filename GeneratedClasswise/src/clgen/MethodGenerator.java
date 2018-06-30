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
import clgen.MethodGenerator;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import com.rem.gen.parser.Token;
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
public void addDefinedMethodName(final OutputMethod newMethod)  {
	definedMethodNames.add(newMethod.getName().evaluate());
}
public OutputMethod declaration(final Token declaration,final Boolean isInner,final OutputContext parentContext)  {
	return definition(declaration,isInner,parentContext);
}
public OutputMethod definition(final Token input,final Boolean mustInner,final OutputContext parentContext)  {
	final Boolean isInner = mustInner || input.get("inner") != null;
	final OutputMethod newMethod = new OutputMethod();
	newMethod.setParent(parentContext);
	final OutputType methodType = MainFlow.variables.get_classwise().all_type(input.get("all_type_name"),isInner,parentContext);
	if (input.get("ARRAY_TYPE") != null) {
		for (final Token element :  input.getAllSafely("ARRAY_TYPE")) {
			methodType.array();
		}
	}
	if ((input.get("inline") != null)) {
		if (input.get("inline").get("method_parameters") != null) {
			final OutputParameters parameters = new OutputParameters();
			for (final Token element :  input.get("inline").get("method_parameters").getAllSafely("parameter")) {
				parameters.add(MainFlow.variables.get_variable().declaration(element,isInner,parentContext));
			}
			newMethod.parameters(parameters);
		}
	}
	else if (input.get("variableParameters") != null) {
		newMethod.parametersAsVariable(MainFlow.variables.get_body_gen().statement(input.get("variableParameters").get("statement_as_method").get("body_statement"),true,parentContext));
	}
	if (input.get("body") != null) {
		final OutputBody methodBody = new OutputBody();
		methodBody.setParent(parentContext);
		for (final Token element :  input.get("body").getAllSafely("body_element")) {
			methodBody.add(MainFlow.variables.get_body_gen().element(element,isInner,methodBody));
		}
		newMethod.body(methodBody);
	}
	if (input.get("methodName") == null) {
		newMethod.set(methodType,new OutputExact(""));
	}
	else if (input.get("methodName").get("NAME") != null) {
		newMethod.set(methodType,new OutputExact(input.get("methodName").get("NAME").toString()));
	}
	else  {
		newMethod.set(methodType,MainFlow.variables.get_classwise().name_var(input.get("methodName").get("name_var"),isInner,parentContext));
	}
	if (input.get("static") != null) {
		newMethod.isStatic();
	}
	if (input.get("weak") != null) {
		newMethod.isAbstract();
	}
	return newMethod;
}

}