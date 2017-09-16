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
									new AddTokenParser(
										
										new ChainParser(
											new AddTokenParser(
												CargonTokens.ANYCHAR,"left"),
											CargonTokens.DASH,
											new AddTokenParser(
												CargonTokens.ANYCHAR,"right")),"range")),
							new ManyParser(
									Rules.regex_special),
							new ManyParser(
									new AddTokenParser(
										CargonTokens.ANYCHAR,"standAlone"))),"REGEX_OPTION","braces","[,]");
	public static final BracedParser REGEX_GROUP = new BracedParser(
							Rules.regex,"REGEX_GROUP","braces","(,)");
	public static final BracedParser QUOTE = new BracedParser(
							new AddTokenParser(
								CargonTokens.WILD,"quote"),"QUOTE","braces","\",\"");

	public static final ChoiceParser parser = new ChoiceParser(
				BRACE,REGEX,REGEX_OPTION,REGEX_GROUP,QUOTE);
}