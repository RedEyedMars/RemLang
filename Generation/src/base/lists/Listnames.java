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

	public static final IParser listname = new RegexParser("listname","listnames","listnames");
	public static final IParser rule = new RegexParser("rule","listnames","rules");
	public static final IParser token = new RegexParser("token","listnames","tokens");
	public static final IParser rulename = new RegexParser("rulename","listnames","rulenames");
	public static final IParser list_rule = new RegexParser("list_rule","listnames","list_rules");
	public static final IParser brace = new RegexParser("brace","listnames","braces");
	public static final IParser comment = new RegexParser("comment","listnames","comments");

	public static final IParser parser = new ChoiceParser(
				listname,rule,token,rulename,list_rule,brace,comment);

	public static final NameParser name_parser = new NameParser(
				"listnames","listname","rule","token","rulename","list_rule","brace","comment");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}