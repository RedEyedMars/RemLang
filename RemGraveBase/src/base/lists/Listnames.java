package base.lists;

import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.NameParser;
import com.rem.parser.ParseData;
import com.rem.parser.ParseList;
import com.rem.parser.RegexParser;

public class Listnames extends ParseList {

	@Override
	public String getName() {
		return "listnames";
	}

	@Override
	public String getSingular() {
		return "listname";
	}

	public static final RegexParser listname = new RegexParser("listname","listnames","listnames");
	public static final RegexParser rule = new RegexParser("rule","listnames","rules");
	public static final RegexParser token = new RegexParser("token","listnames","tokens");
	public static final RegexParser rulename = new RegexParser("rulename","listnames","rulenames");
	public static final RegexParser list_rule = new RegexParser("list_rule","listnames","list_rules");
	public static final RegexParser brace = new RegexParser("brace","listnames","braces");
	public static final RegexParser comment = new RegexParser("comment","listnames","comments");
	
	public static final IParser parser = new ChoiceParser(
			listname,rule,token,rulename,list_rule,brace,comment);
	public static final IParser name_parser = new NameParser("listnames",
			"listname","rule","token","rulename","list_rule","brace","comment");


}
