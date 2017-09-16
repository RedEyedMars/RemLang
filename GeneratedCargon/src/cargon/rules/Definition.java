package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Definition extends ConcreteRule {

	public static final IRule parser = new Definition();

	public Definition(){
		super("definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new MultipleParser(
							new AddTokenParser(
								Rules.element,"chain")),
					new OptionalParser(
							new AddTokenParser(
								
								new ChainParser(
									
									new ChoiceParser(
											CargonTokens.PIPE,
											CargonTokens.NEWTAB),
									Rules.definition),"choice"))));

	}

}