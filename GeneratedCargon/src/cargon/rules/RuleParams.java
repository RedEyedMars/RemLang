package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class RuleParams extends ConcreteRule {

	public static final IRule parser = new RuleParams();

	public RuleParams(){
		super("rule_params");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					CargonTokens.SILENT,
				new ChainParser(
					new OptionalParser(
							
								new ChainParser(
									CargonTokens.PASS_SYMBOL,
									new AddTokenParser(
										CargonTokens.NUMBER,"passConstraint"))),
					CargonTokens.BRACED,
					Rules.braced_parameters),
				new ChainParser(
					CargonTokens.IMPORTS,
					Rules.import_parameters),
				new ChainParser(
					CargonTokens.IGNORE_PARAM,
					
					new ChoiceParser(
							new AddTokenParser(
								CargonTokens.NONE,"ignores_none"),
							new MultipleParser(
									new AddTokenParser(
										Braces.SINGLE_CHAR_CAPTURE,"ignores_character"))))));

	}

}