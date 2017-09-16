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

	public static final RegexParser comment = new RegexParser("comment","listnames","comments\b");
	public static final RegexParser token = new RegexParser("token","listnames","tokens\b");
	public static final RegexParser brace = new RegexParser("brace","listnames","braces\b");
	public static final RegexParser class_name = new RegexParser("class_name","listnames","class_names\b");
	public static final RegexParser class_variable_name = new RegexParser("class_variable_name","listnames","class_variable_names\b");
	public static final RegexParser variable_name = new RegexParser("variable_name","listnames","variable_names\b");
	public static final RegexParser method_definitions = new RegexParser("method_definitions","listnames","method_definitions\b");
	public static final RegexParser simport = new RegexParser("simport","listnames","simports\b");

	public static final ChoiceParser parser = new ChoiceParser(
				comment,token,brace,class_name,class_variable_name,variable_name,method_definitions,simport);
}