package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class IgnoresElement extends ConcreteRule {

	public static final IRule parser = new IgnoresElement();

	public IgnoresElement(){
		super("ignores_element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenParser(
						
							Braces.SINGLE_CHAR_CAPTURE,"ignoreCharacter"),
					
					new ChoiceParser(
							Rules.ignores_element,
							CargonTokens.NEWLINE)));

	}

}