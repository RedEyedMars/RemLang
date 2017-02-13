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

	public static final IParser listname = new RegexParser("listname","listnames","listnames\b");
	public static final IParser rule = new RegexParser("rule","listnames","rules\b");
	public static final IParser token = new RegexParser("token","listnames","tokens\b");
	public static final IParser rulename = new RegexParser("rulename","listnames","rulenames\b");
	public static final IParser rule_parameter = new RegexParser("rule_parameter","listnames","rule_parameters\b");
	public static final IParser list_rule = new RegexParser("list_rule","listnames","list_rules\b");
	public static final IParser brace = new RegexParser("brace","listnames","braces\b");
	public static final IParser altbrace = new RegexParser("altbrace","listnames","altbraces\b");
	public static final IParser comment = new RegexParser("comment","listnames","comments\b");

	public static final ChoiceParser parser = new ChoiceParser(
				listname,rule,token,rulename,rule_parameter,list_rule,brace,altbrace,comment);

	public static final NameParser name_parser = new NameParser(
				"listnames");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}