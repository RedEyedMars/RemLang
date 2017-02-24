package base.rules;

import com.rem.parser.*;
import base.lists.*;

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
									Braces.ANGLE_BRACES)),"castToType")));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}