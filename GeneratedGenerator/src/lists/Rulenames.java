package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Rulenames extends ParseList {

	@Override
	public String getName() {
		return "rulenames";
	}
	@Override
	public String getSingular() {
		return "rulename";
	}

	public static final RegexParser angle_brace_parameters = new RegexParser("angle_brace_parameters","rulenames","angle_brace_parameters");
	public static final RegexParser tab_brace_parameters = new RegexParser("tab_brace_parameters","rulenames","tab_brace_parameters");
	public static final RegexParser arithmatic = new RegexParser("arithmatic","rulenames","arithmatic");
	public static final RegexParser base = new RegexParser("base","rulenames","base");
	public static final RegexParser whitetab = new RegexParser("whitetab","rulenames","whitetab");
	public static final RegexParser used_classes_list = new RegexParser("used_classes_list","rulenames","used_classes_list");
	public static final RegexParser meta_declaration = new RegexParser("meta_declaration","rulenames","meta_declaration");
	public static final RegexParser meta_method_declaration = new RegexParser("meta_method_declaration","rulenames","meta_method_declaration");
	public static final RegexParser property_declaration = new RegexParser("property_declaration","rulenames","property_declaration");
	public static final RegexParser entry_class_declaration = new RegexParser("entry_class_declaration","rulenames","entry_class_declaration");
	public static final RegexParser entry_class_element = new RegexParser("entry_class_element","rulenames","entry_class_element");
	public static final RegexParser class_declaration = new RegexParser("class_declaration","rulenames","class_declaration");
	public static final RegexParser class_element = new RegexParser("class_element","rulenames","class_element");
	public static final RegexParser element_declaration = new RegexParser("element_declaration","rulenames","element_declaration");
	public static final RegexParser element_definition = new RegexParser("element_definition","rulenames","element_definition");
	public static final RegexParser element_entry = new RegexParser("element_entry","rulenames","element_entry");
	public static final RegexParser takes_statement = new RegexParser("takes_statement","rulenames","takes_statement");
	public static final RegexParser auxillary_declaration = new RegexParser("auxillary_declaration","rulenames","auxillary_declaration");
	public static final RegexParser generation_declaration = new RegexParser("generation_declaration","rulenames","generation_declaration");
	public static final RegexParser entry_declaration = new RegexParser("entry_declaration","rulenames","entry_declaration");
	public static final RegexParser entry_definition = new RegexParser("entry_definition","rulenames","entry_definition");
	public static final RegexParser list_entry_definition = new RegexParser("list_entry_definition","rulenames","list_entry_definition");
	public static final RegexParser body_element = new RegexParser("body_element","rulenames","body_element");
	public static final RegexParser constant_declaration = new RegexParser("constant_declaration","rulenames","constant_declaration");
	public static final RegexParser variable_declaration = new RegexParser("variable_declaration","rulenames","variable_declaration");
	public static final RegexParser token_declaration = new RegexParser("token_declaration","rulenames","token_declaration");
	public static final RegexParser token_expansion = new RegexParser("token_expansion","rulenames","token_expansion");
	public static final RegexParser clause_type_tokens = new RegexParser("clause_type_tokens","rulenames","clause_type_tokens");
	public static final RegexParser token_clause = new RegexParser("token_clause","rulenames","token_clause");
	public static final RegexParser all_type_tokens = new RegexParser("all_type_tokens","rulenames","all_type_tokens");
	public static final RegexParser return_statement = new RegexParser("return_statement","rulenames","return_statement");
	public static final RegexParser error_statement = new RegexParser("error_statement","rulenames","error_statement");
	public static final RegexParser each_call = new RegexParser("each_call","rulenames","each_call");
	public static final RegexParser if_statement = new RegexParser("if_statement","rulenames","if_statement");
	public static final RegexParser else_statement = new RegexParser("else_statement","rulenames","else_statement");
	public static final RegexParser boolean_statement = new RegexParser("boolean_statement","rulenames","boolean_statement");
	public static final RegexParser boolean_clause = new RegexParser("boolean_clause","rulenames","boolean_clause");
	public static final RegexParser check_call = new RegexParser("check_call","rulenames","check_call");
	public static final RegexParser method_call = new RegexParser("method_call","rulenames","method_call");
	public static final RegexParser set_call = new RegexParser("set_call","rulenames","set_call");
	public static final RegexParser generate_call = new RegexParser("generate_call","rulenames","generate_call");
	public static final RegexParser method_parameter = new RegexParser("method_parameter","rulenames","method_parameter");
	public static final RegexParser variable_or_token_name = new RegexParser("variable_or_token_name","rulenames","variable_or_token_name");
	public static final RegexParser flip_switch = new RegexParser("flip_switch","rulenames","flip_switch");
	public static final RegexParser cast_statement = new RegexParser("cast_statement","rulenames","cast_statement");
	public static final RegexParser cast_as_statement = new RegexParser("cast_as_statement","rulenames","cast_as_statement");

	public static final ChoiceParser parser = new ChoiceParser(
				angle_brace_parameters,tab_brace_parameters,arithmatic,base,whitetab,used_classes_list,meta_declaration,meta_method_declaration,property_declaration,entry_class_declaration,entry_class_element,class_declaration,class_element,element_declaration,element_definition,element_entry,takes_statement,auxillary_declaration,generation_declaration,entry_declaration,entry_definition,list_entry_definition,body_element,constant_declaration,variable_declaration,token_declaration,token_expansion,clause_type_tokens,token_clause,all_type_tokens,return_statement,error_statement,each_call,if_statement,else_statement,boolean_statement,boolean_clause,check_call,method_call,set_call,generate_call,method_parameter,variable_or_token_name,flip_switch,cast_statement,cast_as_statement);
}