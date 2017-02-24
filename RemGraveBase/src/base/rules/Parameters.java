package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Parameters extends ConcreteRule {

	public static final IRule parser = new Parameters();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Parameters(){
		super("parameters");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							
								new ChainParser(
									Tokens.SPACES,
									Tokens.AS,
									Tokens.SPACES,
									new AddTokenParser(
										Tokens.NAME,"name"))),
					new OptionalParser(
							
								new ChainParser(
									Tokens.SPACES,
									Tokens.IN,
									Tokens.SPACES,
									new AddTokenParser(
										Listnames.parser,"list"))),
					new OptionalParser(
							Tokens.SPACES)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}