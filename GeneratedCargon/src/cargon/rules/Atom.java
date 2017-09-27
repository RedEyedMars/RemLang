package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Atom extends ConcreteRule {

	public static final IRule parser = new Atom();

	public Atom(){
		super("atom");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new AddTokenParser(
						
						new ChainParser(
							Rules.definition,
							new AddTokenParser(
								
							new ChoiceParser(
									CargonTokens.OPTIONAL,
									CargonTokens.MANY,
									CargonTokens.PLUS),"option")),"multiple"),
					new AddTokenParser(
						Braces.BRACE,"braced"),
					new AddTokenParser(
						Rules.uote,"quoteToken"),
					new AddTokenParser(
						Braces.REGEX,"regexToken"),
					new AddTokenParser(
						CargonTokens.NAME,"ruleToken")));

	}

}