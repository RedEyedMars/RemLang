package base.rules;

import com.rem.parser.*;
import lists.*;

public class CastAsStatement extends ConcreteRule {

	public static final IRule parser = new CastAsStatement();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
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
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}