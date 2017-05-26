package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Listnames extends ParseList {

	@Override
	public String getName() {
		return "listnames";
	}
	@Override
	public String getSingular() {
		return "listname";
	}

	public static final RegexParser token = new RegexParser("token","listnames","tokens\b");
	public static final RegexParser brace = new RegexParser("brace","listnames","braces\b");
	public static final RegexParser class_declaration = new RegexParser("class_declaration","listnames","class_declarations\b");
	public static final RegexParser class_definition = new RegexParser("class_definition","listnames","class_definitions\b");
	public static final RegexParser class_name = new RegexParser("class_name","listnames","class_names\b");
	public static final RegexParser variable_name = new RegexParser("variable_name","listnames","variable_names\b");
	public static final RegexParser method_name = new RegexParser("method_name","listnames","method_names\b");

	public static final ChoiceParser parser = new ChoiceParser(
				token,brace,class_declaration,class_definition,class_name,variable_name,method_name);
}