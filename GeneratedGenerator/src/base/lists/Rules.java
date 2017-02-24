package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class Rules extends ParseList {

	@Override
	public String getName() {
		return "rules";
	}
	@Override
	public String getSingular() {
		return "rule";
	}

	public static final IRule angle_brace_parameters = AngleBraceParameters.parser;
	public static final IRule tab_brace_parameters = TabBraceParameters.parser;
	public static final IRule arithmatic = Arithmatic.parser;
	public static final IRule base = Base.parser;
	public static final IRule whitetab = Whitetab.parser;
	public static final IRule used_classes_list = UsedClassesList.parser;
	public static final IRule class_declaration = ClassDeclaration.parser;
	public static final IRule class_element = ClassElement.parser;
	public static final IRule element_declaration = ElementDeclaration.parser;
	public static final IRule element_definition = ElementDefinition.parser;
	public static final IRule element_entry = ElementEntry.parser;
	public static final IRule auxillary_declaration = AuxillaryDeclaration.parser;
	public static final IRule generation_declaration = GenerationDeclaration.parser;
	public static final IRule entry_declaration = EntryDeclaration.parser;
	public static final IRule entry_definition = EntryDefinition.parser;
	public static final IRule body_element = BodyElement.parser;
	public static final IRule variable_declaration = VariableDeclaration.parser;
	public static final IRule token_declaration = TokenDeclaration.parser;
	public static final IRule list_entry_definition = ListEntryDefinition.parser;
	public static final IRule token_expansion = TokenExpansion.parser;
	public static final IRule clause_type_tokens = ClauseTypeTokens.parser;
	public static final IRule token_clause = TokenClause.parser;
	public static final IRule all_type_tokens = AllTypeTokens.parser;
	public static final IRule return_statement = ReturnStatement.parser;
	public static final IRule error_statement = ErrorStatement.parser;
	public static final IRule if_statement = IfStatement.parser;
	public static final IRule else_statement = ElseStatement.parser;
	public static final IRule boolean_statement = BooleanStatement.parser;
	public static final IRule boolean_clause = BooleanClause.parser;
	public static final IRule method_call = MethodCall.parser;
	public static final IRule generate_call = GenerateCall.parser;
	public static final IRule method_parameter = MethodParameter.parser;
	public static final IRule variable_or_token_name = VariableOrTokenName.parser;
	public static final IRule flip_switch = FlipSwitch.parser;
	public static final IRule cast_statement = CastStatement.parser;
	public static final IRule cast_as_statement = CastAsStatement.parser;

	public static final ChoiceParser parser = new ChoiceParser(
				angle_brace_parameters,tab_brace_parameters,arithmatic,base,whitetab,used_classes_list,class_declaration,class_element,element_declaration,element_definition,element_entry,auxillary_declaration,generation_declaration,entry_declaration,entry_definition,body_element,variable_declaration,token_declaration,list_entry_definition,token_expansion,clause_type_tokens,token_clause,all_type_tokens,return_statement,error_statement,if_statement,else_statement,boolean_statement,boolean_clause,method_call,generate_call,method_parameter,variable_or_token_name,flip_switch,cast_statement,cast_as_statement);

	public static final NameParser name_parser = new NameParser(
				"rules");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}