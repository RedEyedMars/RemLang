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
import clgen.VariableGenerator;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import com.rem.gen.parser.Token;
public class  VariableGenerator   {
	public static class classes {
	}
	public static VariableGenerator variables = null;
	public static VariableGenerator methods = null;
	//Externals


	//Internals
protected final Set<String> definedVariableNames = new HashSet<String>();

	public Set<String> getDefinedVariableNames()  {
		return definedVariableNames;
	}
	public Set<String> get_definedVariableNames()  {
		return definedVariableNames;
	}
public void addDefinedVariableName(final OutputVariable definedVariable)  {
	definedVariableNames.add(definedVariable.getName().evaluate());
}
public OutputVariable declaration(final Token declaration,final Boolean mustInner,final OutputContext parentContext)  {
	final Boolean isInner = mustInner || declaration.get("inner") != null;
	final OutputVariable newVariable = new OutputVariable();
	final OutputType type = MainFlow.variables.get_classwise().all_type(declaration.get("typeName"),isInner,parentContext);
	for (final Token element :  declaration.getAllSafely("ARRAY_TYPE")) {
		type.array();
	}
	if (declaration.get("INLINE_LIST") != null) {
		type.isInlineList();
	}
	if (declaration.get("method_argument") != null) {
		newVariable.assign(MainFlow.variables.get_body_gen().argument(declaration.get("method_argument"),isInner,parentContext));
	}
	if (declaration.get("isFinal") != null) {
		newVariable.isFinal();
	}
	if (declaration.get("static") != null) {
		newVariable.isStatic();
		newVariable.isPublic();
	}
	if (declaration.get("variableName").get("NAME") != null || isInner) {
		newVariable.set(type,new OutputExact(declaration.get("variableName").toString()));
	}
	else  {
		newVariable.set(type,MainFlow.variables.get_classwise().name_var(declaration.get("variableName").get("name_var"),isInner,parentContext));
	}
	return newVariable;
}
public LineableOutput assignment(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	return new OutputStatement().set(new OutputOperator().left(MainFlow.variables.get_classwise().name_var(input.get("name_var"),isInner,parentContext)).operator("=").right(MainFlow.variables.get_body_gen().argument(input.get("method_argument"),isInner,parentContext)));
}

}