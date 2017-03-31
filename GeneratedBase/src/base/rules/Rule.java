package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Rule extends ConcreteRule {

	public static final IRule parser = new Rule();
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
					Tokens.NEWLINE,
					Tokens.TAB,
					Rules.definition));

	}
}