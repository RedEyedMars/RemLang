package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Altbraces extends ParseList {

	@Override
	public String getName() {
		return "altbraces";
	}
	@Override
	public String getSingular() {
		return "altbrace";
	}

	public static final CharitableBracedParser NEWLINED_DEF = new CharitableBracedParser(
							Rules.definition,"NEWLINED_DEF","altbraces",",\n");
	public static final CharitableBracedParser RULE_FILE = new CharitableBracedParser(
							new AddTokenParser(
								Tokens.WILD,"file_name"),"RULE_FILE","altbraces",",\n");

	public static final ChoiceParser parser = new ChoiceParser(
				NEWLINED_DEF,RULE_FILE);
}