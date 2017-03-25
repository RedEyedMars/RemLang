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
	public static final RegexParser altbrace = new RegexParser("altbrace","listnames","altbraces\b");
	public static final RegexParser list_rule = new RegexParser("list_rule","listnames","list_rules\b");
	public static final RegexParser rule = new RegexParser("rule","listnames","rules\b");
	public static final RegexParser rule_parameter = new RegexParser("rule_parameter","listnames","rule_parameters\b");
	public static final RegexParser definition = new RegexParser("definition","listnames","definitions\b");

	public static final ChoiceParser parser = new ChoiceParser(
				comment,token,brace,altbrace,list_rule,rule,rule_parameter,definition);

	public static final NameParser name_parser = new NameParser(
				"listnames");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}