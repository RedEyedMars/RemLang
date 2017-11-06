package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class RegexOption extends ConcreteRule {

	public static final IRule parser = new RegexOption();

	public RegexOption(){
		super("regex_option");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new AddTokenParser(
						
						new ChainParser(
							new AddTokenParser(
								CargonTokens.ANYCHAR,"left"),
							CargonTokens.DASH,
							new AddTokenParser(
								CargonTokens.ANYCHAR,"right")),"range"),
					Rules.regex_special,
					new AddTokenParser(
						CargonTokens.ANYCHAR,"standAlone")));

	}

}