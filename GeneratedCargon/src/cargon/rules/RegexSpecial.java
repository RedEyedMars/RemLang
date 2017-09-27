package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class RegexSpecial extends ConcreteRule {

	public static final IRule parser = new RegexSpecial();

	public RegexSpecial(){
		super("regex_special");
	}
	@Override
	public void setup(){
		set(
					
					new ChoiceParser(
							CargonTokens.REGEX_NUMBER,
							CargonTokens.REGEX_DOT,
							CargonTokens.REGEX_WHITESPACE,
							CargonTokens.REGEX_QUOTE,
							CargonTokens.REGEX_APOS));

	}

}