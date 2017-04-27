package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class CastAsStatement extends ConcreteRule {

	public static final IRule parser = new CastAsStatement();

	public CastAsStatement(){
		super("cast_as_statement");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					Tokens.AS,
					new AddTokenParser(
						
						new ChainParser(
							Tokens.NAME,
							new OptionalParser(
									new AddTokenParser(
										Braces.ANGLE_BRACES,"angle_braces"))),"castToType")));

	}

}