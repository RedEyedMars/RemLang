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

	public static final RegexParser base = new RegexParser("base","rulenames","base");
	public static final RegexParser base_element = new RegexParser("base_element","rulenames","base_element");
	public static final RegexParser imports = new RegexParser("imports","rulenames","imports");
	public static final RegexParser anonymous_class = new RegexParser("anonymous_class","rulenames","anonymous_class");
	public static final RegexParser class_declaration = new RegexParser("class_declaration","rulenames","class_declaration");
	public static final RegexParser class_element = new RegexParser("class_element","rulenames","class_element");
	public static final RegexParser body_element = new RegexParser("body_element","rulenames","body_element");
	public static final RegexParser body_conditional = new RegexParser("body_conditional","rulenames","body_conditional");
	public static final RegexParser body_statement = new RegexParser("body_statement","rulenames","body_statement");
	public static final RegexParser body_call = new RegexParser("body_call","rulenames","body_call");
	public static final RegexParser body_manipulate = new RegexParser("body_manipulate","rulenames","body_manipulate");
	public static final RegexParser method_argument = new RegexParser("method_argument","rulenames","method_argument");
	public static final RegexParser method_declaration = new RegexParser("method_declaration","rulenames","method_declaration");
	public static final RegexParser method_definition = new RegexParser("method_definition","rulenames","method_definition");
	public static final RegexParser variable_declaration = new RegexParser("variable_declaration","rulenames","variable_declaration");
	public static final RegexParser variable_assignment = new RegexParser("variable_assignment","rulenames","variable_assignment");
	public static final RegexParser variable_name_definition = new RegexParser("variable_name_definition","rulenames","variable_name_definition");
	public static final RegexParser class_name_definition = new RegexParser("class_name_definition","rulenames","class_name_definition");
	public static final RegexParser all_type_name = new RegexParser("all_type_name","rulenames","all_type_name");
	public static final RegexParser name_var = new RegexParser("name_var","rulenames","name_var");
	public static final RegexParser name_var_element = new RegexParser("name_var_element","rulenames","name_var_element");
	public static final RegexParser type_var = new RegexParser("type_var","rulenames","type_var");
	public static final RegexParser type_var_element = new RegexParser("type_var_element","rulenames","type_var_element");

	public static final ChoiceParser parser = new ChoiceParser(
				base,base_element,imports,anonymous_class,class_declaration,class_element,body_element,body_conditional,body_statement,body_call,body_manipulate,method_argument,method_declaration,method_definition,variable_declaration,variable_assignment,variable_name_definition,class_name_definition,all_type_name,name_var,name_var_element,type_var,type_var_element);
}