package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lang.rules.*;

public class Rules extends ParseList {

	@Override
	public String getName() {
		return "rules";
	}
	@Override
	public String getSingular() {
		return "rule";
	}

	public static final IRule define_parameter = DefineParameter.parser;
	public static final IRule defined_method = DefinedMethod.parser;
	public static final IRule defined_operator = DefinedOperator.parser;
	public static final IRule base = Base.parser;
	public static final IRule class_declaration = ClassDeclaration.parser;
	public static final IRule class_definition = ClassDefinition.parser;
	public static final IRule body_element = BodyElement.parser;
	public static final IRule define_declaration = DefineDeclaration.parser;
	public static final IRule variable_declaration = VariableDeclaration.parser;
	public static final IRule method_declaration = MethodDeclaration.parser;
	public static final IRule method_parameter = MethodParameter.parser;
	public static final IRule method_call = MethodCall.parser;
	public static final IRule return_call = ReturnCall.parser;
	public static final IRule define_braced_constructor = DefineBracedConstructor.parser;
	public static final IRule define_constructor = DefineConstructor.parser;
	public static final IRule define_operator = DefineOperator.parser;

	public static final ChoiceParser parser = new ChoiceParser(
				define_parameter,defined_method,defined_operator,base,class_declaration,class_definition,body_element,define_declaration,variable_declaration,method_declaration,method_parameter,method_call,return_call,define_braced_constructor,define_constructor,define_operator);
}