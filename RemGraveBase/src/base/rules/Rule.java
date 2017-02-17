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
					new ChainParser(
						Tokens.HAS,
						new ManyParser(
							
								new AddTokenToListParser(
									Tokens.NAME,"rule_param","rule_parameters")))),
				new OptionalParser(
					Tokens.SPACES),
				Tokens.NEWLINE,
				Tokens.TAB,
				Rules.definition));

	}
	@Override
	public Parameter getParameter(int i) {
		switch(i){
		default: return null;
		}
	}

}