package cargon.rules;

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
					new AddTokenParser(
						CargonTokens.NAME,"ruleName"),
					new OptionalParser(
							
							new ChoiceParser(
									Altbraces.RULE_PARAMETERS,
									Altbraces.EQUALS_RULE_PARAMETERS)),
					
					new ChoiceParser(
							CargonTokens.NEWTAB,
							CargonTokens.EQUALSIGN),
					Rules.definition));

	}

}