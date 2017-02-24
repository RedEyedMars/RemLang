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
									Tokens.AS,
									new AddTokenParser(
										Tokens.NAME,"name"))),
					new OptionalParser(
							
								new ChainParser(
									Tokens.IN,
									new AddTokenParser(
										Listnames.parser,"list")))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}