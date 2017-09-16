package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import classwise.rules.*;

public class Rules extends ParseList {

	@Override
	public String getName() {
		return "rules";
	}
	@Override
	public String getSingular() {
		return "rule";
	}

	public static final IRule base = Base.parser;
	public static final IRule base_element = BaseElement.parser;
	public static final IRule imports = Imports.parser;
	public static final IRule anonymous_class = AnonymousClass.parser;
	public static final IRule class_declaration = ClassDeclaration.parser;
	public static final IRule class_element = ClassElement.parser;
	public static final IRule body_element = BodyElement.parser;
	public static final IRule body_conditional = BodyConditional.parser;
	public static final IRule body_statement = BodyStatement.parser;
	public static final IRule body_call = BodyCall.parser;
	public static final IRule body_manipulate = BodyManipulate.parser;
	public static final IRule method_argument = MethodArgument.parser;
	public static final IRule method_declaration = MethodDeclaration.parser;
	public static final IRule method_definition = MethodDefinition.parser;
	public static final IRule variable_declaration = VariableDeclaration.parser;
	public static final IRule variable_assignment = VariableAssignment.parser;
	public static final IRule variable_name_definition = VariableNameDefinition.parser;
	public static final IRule class_name_definition = ClassNameDefinition.parser;
	public static final IRule all_type_name = AllTypeName.parser;
	public static final IRule name_var = NameVar.parser;
	public static final IRule name_var_element = NameVarElement.parser;
	public static final IRule type_var = TypeVar.parser;
	public static final IRule type_var_element = TypeVarElement.parser;

	public static final ChoiceParser parser = new ChoiceParser(
				base,base_element,imports,anonymous_class,class_declaration,class_element,body_element,body_conditional,body_statement,body_call,body_manipulate,method_argument,method_declaration,method_definition,variable_declaration,variable_assignment,variable_name_definition,class_name_definition,all_type_name,name_var,name_var_element,type_var,type_var_element);
}