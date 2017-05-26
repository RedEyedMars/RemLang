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

	public static final BracedParser CUSTOM_BRACE = new BracedParser(
						new ChainParser(
							new OptionalParser(
									Rules.event_element),
							new ManyParser(
									
										new ChainParser(
											Tokens.COMMA,
											Rules.event_element))),"CUSTOM_BRACE","braces","(,)");
	public static final BracedParser BRACE = new BracedParser(
							Rules.definition,"BRACE","braces","(,)");
	public static final BracedParser SQUARE = new BracedParser(
							new AddTokenParser(
								Tokens.WILD,"regex"),"SQUARE","braces","[,]");
	public static final BracedParser QUOTE = new BracedParser(
							new AddTokenParser(
								Tokens.WILD,"quote"),"QUOTE","braces","\",\"");
	public static final BracedParser PARAM_BRACE = new BracedParser(
						new ChainParser(
							new AddTokenParser(
								Rules.arithmatic,"parameter"),
							new ManyParser(
									
										new ChainParser(
											Tokens.COMMA,
											new AddTokenParser(
												Rules.arithmatic,"parameter")))),"PARAM_BRACE","braces","{,}");
	public static final BracedParser SEMICOLONED = new BracedParser(
							Rules.definition,"SEMICOLONED","braces",",;");

	public static final ChoiceParser parser = new ChoiceParser(
				CUSTOM_BRACE,BRACE,SQUARE,QUOTE,PARAM_BRACE,SEMICOLONED);
}