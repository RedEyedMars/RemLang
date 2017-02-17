package base.lists;

import com.rem.parser.*;
import base.rules.*;

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
	public static final RegexParser rule_parameter = new RegexParser("rule_parameter","listnames","rule_parameters\b");

	public static final ChoiceParser parser = new ChoiceParser(
				comment,token,brace,altbrace,list_rule,rule_parameter);

	public static final NameParser name_parser = new NameParser(
				"listnames");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}