package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import generator.rules.*;

public class Rules extends ParseList {

	@Override
	public String getName() {
		return "rules";
	}
	@Override
	public String getSingular() {
		return "rule";
	}

	public static final IRule angle_brace_class = AngleBraceClass.parser;
	public static final IRule tab_brace_parameters = TabBraceParameters.parser;
	public static final IRule arithmatic = Arithmatic.parser;
	public static final IRule base = Base.parser;
	public static final IRule whitetab = Whitetab.parser;
	public static final IRule used_classes_list = UsedClassesList.parser;
	public static final IRule meta_declaration = MetaDeclaration.parser;
	public static final IRule meta_method_declaration = MetaMethodDeclaration.parser;
	public static final IRule property_declaration = PropertyDeclaration.parser;
	public static final IRule entry_class_declaration = EntryClassDeclaration.parser;
	public static final IRule entry_class_element = EntryClassElement.parser;
	public static final IRule class_declaration = ClassDeclaration.parser;
	public static final IRule class_element = ClassElement.parser;
	public static final IRule element_declaration = ElementDeclaration.parser;
	public static final IRule element_definition = ElementDefinition.parser;
	public static final IRule element_entry = ElementEntry.parser;
	public static final IRule takes_statement = TakesStatement.parser;
	public static final IRule auxillary_declaration = AuxillaryDeclaration.parser;
	public static final IRule generation_declaration = GenerationDeclaration.parser;
	public static final IRule entry_declaration = EntryDeclaration.parser;
	public static final IRule entry_definition = EntryDefinition.parser;
	public static final IRule list_entry_definition = ListEntryDefinition.parser;
	public static final IRule body_element = BodyElement.parser;
	public static final IRule constant_declaration = ConstantDeclaration.parser;
	public static final IRule variable_declaration = VariableDeclaration.parser;
	public static final IRule token_declaration = TokenDeclaration.parser;
	public static final IRule token_expansion = TokenExpansion.parser;
	public static final IRule clause_type_tokens = ClauseTypeTokens.parser;
	public static final IRule token_clause = TokenClause.parser;
	public static final IRule all_type_tokens = AllTypeTokens.parser;
	public static final IRule return_statement = ReturnStatement.parser;
	public static final IRule error_statement = ErrorStatement.parser;
	public static final IRule each_call = EachCall.parser;
	public static final IRule range = Range.parser;
	public static final IRule if_statement = IfStatement.parser;
	public static final IRule else_statement = ElseStatement.parser;
	public static final IRule boolean_statement = BooleanStatement.parser;
	public static final IRule boolean_clause = BooleanClause.parser;
	public static final IRule check_call = CheckCall.parser;
	public static final IRule inline_addition_call = InlineAdditionCall.parser;
	public static final IRule method_call = MethodCall.parser;
	public static final IRule set_call = SetCall.parser;
	public static final IRule generate_call = GenerateCall.parser;
	public static final IRule method_parameter = MethodParameter.parser;
	public static final IRule variable_or_token_name = VariableOrTokenName.parser;
	public static final IRule flip_switch = FlipSwitch.parser;
	public static final IRule cast_statement = CastStatement.parser;
	public static final IRule cast_as_statement = CastAsStatement.parser;

	public static final ChoiceParser parser = new ChoiceParser(
				angle_brace_class,tab_brace_parameters,arithmatic,base,whitetab,used_classes_list,meta_declaration,meta_method_declaration,property_declaration,entry_class_declaration,entry_class_element,class_declaration,class_element,element_declaration,element_definition,element_entry,takes_statement,auxillary_declaration,generation_declaration,entry_declaration,entry_definition,list_entry_definition,body_element,constant_declaration,variable_declaration,token_declaration,token_expansion,clause_type_tokens,token_clause,all_type_tokens,return_statement,error_statement,each_call,range,if_statement,else_statement,boolean_statement,boolean_clause,check_call,inline_addition_call,method_call,set_call,generate_call,method_parameter,variable_or_token_name,flip_switch,cast_statement,cast_as_statement);
}