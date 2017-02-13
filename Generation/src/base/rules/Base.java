package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Base extends AddTokenParser implements IRule {

	public static final IParser parser = new Base();
	public Base(){
		super("base");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new MultipleParser(
					
					new ChoiceParser(new ChainParser(
						new OptionalParser(
							Tokens.SPACES),
						Tokens.NEWLINE),
						Comments.parser,
						new AddTokenToListParser(
							Rules.rule,"rule","rules"),
						new AddTokenToListParser(
							Rules.list_rule,"list_rule","list_rules"))));

	}

}