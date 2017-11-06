package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Braces extends ParseList {

	@Override
	public String getName() {
		return "braces";
	}
	@Override
	public String getSingular() {
		return "brace";
	}

	public static final BracedParser BRACE = new BracedParser(
							Rules.definition,"BRACE","braces","(,)");
	public static final BracedParser REGEX = new BracedParser(
							Rules.regex,"REGEX","braces","[,]");
	public static final BracedParser REGEX_OPTION = new BracedParser(
						new ChainParser(
							new OptionalParser(
									new AddTokenParser(
										CargonTokens.CARROT,"negate")),
							new ManyParser(
									Rules.regex_option)),"REGEX_OPTION","braces","[,]");
	public static final BracedParser REGEX_GROUP = new BracedParser(
							Rules.regex,"REGEX_GROUP","braces","(,)");

	public static final ChoiceParser parser = new ChoiceParser(
				BRACE,REGEX,REGEX_OPTION,REGEX_GROUP);
}