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

	public static final RegexParser define_parameter = new RegexParser("define_parameter","rulenames","define_parameter");
	public static final RegexParser defined_method = new RegexParser("defined_method","rulenames","defined_method");
	public static final RegexParser defined_operator = new RegexParser("defined_operator","rulenames","defined_operator");
	public static final RegexParser base = new RegexParser("base","rulenames","base");
	public static final RegexParser class_declaration = new RegexParser("class_declaration","rulenames","class_declaration");
	public static final RegexParser class_definition = new RegexParser("class_definition","rulenames","class_definition");
	public static final RegexParser body_element = new RegexParser("body_element","rulenames","body_element");
	public static final RegexParser define_declaration = new RegexParser("define_declaration","rulenames","define_declaration");
	public static final RegexParser variable_declaration = new RegexParser("variable_declaration","rulenames","variable_declaration");
	public static final RegexParser method_declaration = new RegexParser("method_declaration","rulenames","method_declaration");
	public static final RegexParser method_parameter = new RegexParser("method_parameter","rulenames","method_parameter");
	public static final RegexParser method_call = new RegexParser("method_call","rulenames","method_call");
	public static final RegexParser return_call = new RegexParser("return_call","rulenames","return_call");
	public static final RegexParser define_braced_constructor = new RegexParser("define_braced_constructor","rulenames","define_braced_constructor");
	public static final RegexParser define_constructor = new RegexParser("define_constructor","rulenames","define_constructor");
	public static final RegexParser define_operator = new RegexParser("define_operator","rulenames","define_operator");

	public static final ChoiceParser parser = new ChoiceParser(
				define_parameter,defined_method,defined_operator,base,class_declaration,class_definition,body_element,define_declaration,variable_declaration,method_declaration,method_parameter,method_call,return_call,define_braced_constructor,define_constructor,define_operator);
}