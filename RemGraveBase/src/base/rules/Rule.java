package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Rule extends AddTokenParser implements IRule {

	public static final IParser parser = new Rule();
	public Rule(){
		super("rule");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(new ChainParser(
				new AddTokenToListParser(
					Tokens.NAME,"rulename","rules"),
				new OptionalParser(
					
						new AddTokenParser(
							Tokens.SILENCE,"silence")),
				new OptionalParser(
					Tokens.SPACES),
				Tokens.NEWLINE,
				Tokens.TAB,
				Definition.parser));

	}

}