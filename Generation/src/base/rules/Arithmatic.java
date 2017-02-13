package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Arithmatic extends AddTokenParser implements IRule {

	public static final IParser parser = new Arithmatic();
	public Arithmatic(){
		super("arithmatic");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(new ChainParser(
				Rules.arithmatic,
				new AddTokenParser(
					Tokens.MULTIPLICATIVE_OPERAND,"operand"),
				Rules.arithmatic),new ChainParser(
				Rules.arithmatic,
				new AddTokenParser(
					Tokens.ADDITIVE_OPERAND,"operand"),
				Rules.arithmatic),
				RuleParameters.name_parser,
				Tokens.NUMBER));

	}

}