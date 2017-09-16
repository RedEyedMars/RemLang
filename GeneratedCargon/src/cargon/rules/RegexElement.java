package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class RegexElement extends ConcreteRule {

	public static final IRule parser = new RegexElement();

	public RegexElement(){
		super("regex_element");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new AddTokenParser(
						Braces.REGEX_OPTION,"option"),
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									CargonTokens.OPTIONAL,
									CargonTokens.MANY,
									CargonTokens.PLUS),"multiple"))),
				new ChainParser(
					new AddTokenParser(
						Braces.REGEX_GROUP,"group"),
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									CargonTokens.OPTIONAL,
									CargonTokens.MANY,
									CargonTokens.PLUS),"multiple"))),
				new ChainParser(
					Rules.regex_special,
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									CargonTokens.OPTIONAL,
									CargonTokens.MANY,
									CargonTokens.PLUS),"multiple"))),
				new ChainParser(
					new AddTokenParser(
						CargonTokens.ANYCHAR,"character"),
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									CargonTokens.OPTIONAL,
									CargonTokens.MANY,
									CargonTokens.PLUS),"multiple")))));

	}

}