package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Atom extends ChoiceParser implements IRule {

	public static final IParser parser = new Atom();
	@Override
	public void setup(){
		add(
				new AddTokenParser(
						new ChainParser(
								new OptionalParser(Tokens.SPACES),
								Element.parser,
								new AddTokenParser(Parameters.parser,"parameters")),"atom"));

	}

}