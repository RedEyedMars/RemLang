package clgen;
import java.util.*;
import java.io.*;
import lists.*;
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
import com.rem.crg.parser.Token;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import com.rem.parser.generation.classwise.ExternalStatement;
import clgen.TypeStatement;
import java.lang.StringBuilder;
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
public void addDefinedVariableName(final ExternalVariableEntry definedVariable)  {
	definedVariableNames.add(definedVariable.getName());
}
public ExternalVariableEntry declaration(final Token declaration,final Boolean mustInner,final ExternalContext parentContext)  {
	final Boolean isInner = mustInner || declaration.get("inner") != null;
	final ExternalVariableEntry newVariable = new ExternalVariableEntry();
	final Type type = new Type();
	for (final Token element :  declaration.getAllSafely("typeName")) {
		MainFlow.variables.get_classwise().all_type(element,type,isInner,parentContext);
	}
	if (declaration.get("ARRAY_TYPE") != null) {
		for (final Token element :  declaration.getAllSafely("ARRAY_TYPE")) {
			type.addArraySymbol();
		}
	}
	if (declaration.get("INLINE_LIST") != null) {
		type.setIsInlineList(true);
	}
	newVariable.setType(type.getAsStatement());
	if (declaration.get("method_argument") != null) {
		newVariable.setAssignment(MainFlow.variables.get_body().argument(declaration.get("method_argument"),isInner,parentContext));
	}
	if (declaration.get("WEAK") != null) {
		newVariable.setIsFinal(false);
	}
	if (declaration.get("static") != null) {
		newVariable.setIsStatic(true);
	}
	if (declaration.get("variableName").get("NAME") != null) {
		newVariable.setName(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(declaration.get("variableName").toString())))));
	}
	else  {
		final NameVar nameVar = new NameVar();
		MainFlow.variables.get_classwise().name_var(declaration.get("variableName").get("name_var"),nameVar,isInner,parentContext);
		newVariable.setName(nameVar.getAsStatement());
	}
	return newVariable;
}
public ExternalStatement assignment(final Token input,final Boolean mustInner,final ExternalContext parentContext)  {
	final Boolean isInner = mustInner || input.get("inner") != null;
	final NameVar nameVar = new NameVar();
	MainFlow.variables.get_classwise().name_var(input.get("name_var"),nameVar,isInner,parentContext);
	return /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(nameVar.getAsStatement()))), /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().argument(input.get("method_argument"),isInner,parentContext))));
}

}