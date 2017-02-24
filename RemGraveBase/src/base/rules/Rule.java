package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Rule extends ConcreteRule {

	public static final IRule parser = new Rule();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Rule(){
		super("rule");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenToListParser(
						Tokens.NAME,"rulename","rules"),
					new OptionalParser(
							
									new AddTokenParser(
										Tokens.SILENCE,"silence")),
					new OptionalParser(
							Rules.member_declaration),
					new OptionalParser(
							Tokens.SPACES),
					Tokens.NEWLINE,
					Tokens.TAB,
					Rules.definition));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}