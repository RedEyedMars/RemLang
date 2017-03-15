package lists;

import com.rem.parser.*;

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
	public static final RegexParser tab = new RegexParser("tab","listnames","tabs\b");
	public static final RegexParser brace = new RegexParser("brace","listnames","braces\b");
	public static final RegexParser class_definition = new RegexParser("class_definition","listnames","class_definitions\b");
	public static final RegexParser generator_name = new RegexParser("generator_name","listnames","generator_names\b");
	public static final RegexParser class_name = new RegexParser("class_name","listnames","class_names\b");
	public static final RegexParser element_name = new RegexParser("element_name","listnames","element_names\b");
	public static final RegexParser variable_name = new RegexParser("variable_name","listnames","variable_names\b");
	public static final RegexParser entry_name = new RegexParser("entry_name","listnames","entry_names\b");
	public static final RegexParser token_name = new RegexParser("token_name","listnames","token_names\b");

	public static final ChoiceParser parser = new ChoiceParser(
				token,tab,brace,class_definition,generator_name,class_name,element_name,variable_name,entry_name,token_name);

	public static final NameParser name_parser = new NameParser(
				"listnames");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}