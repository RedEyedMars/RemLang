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
	public static final RegexParser cargon_token = new RegexParser("cargon_token","listnames","cargon_tokens\b");
	public static final RegexParser brace = new RegexParser("brace","listnames","braces\b");
	public static final RegexParser altbrace = new RegexParser("altbrace","listnames","altbraces\b");
	public static final RegexParser rule = new RegexParser("rule","listnames","rules\b");
	public static final RegexParser list = new RegexParser("list","listnames","lists\b");
	public static final RegexParser list_name = new RegexParser("list_name","listnames","list_names\b");

	public static final ChoiceParser parser = new ChoiceParser(
				comment,cargon_token,brace,altbrace,rule,list,list_name);
}