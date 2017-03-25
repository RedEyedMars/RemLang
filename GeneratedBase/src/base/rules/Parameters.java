package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Parameters extends ConcreteRule {

	public static final IRule parser = new Parameters();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Parameters(){
		super("parameters");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new OptionalParser(
							
								new ChainParser(
									Tokens.AS,
									new AddTokenParser(
										Tokens.NAME,"name"))),
					Tokens.IN,
					new AddTokenParser(
						new com.rem.parser.parser.Listnames(),"list"),
					new OptionalParser(
							
								new ChainParser(
									Tokens.WITH,
									new AddTokenParser(
										Tokens.NAME,"tokenName")))),
				new ChainParser(
					Tokens.AS,
					new AddTokenParser(
						Tokens.NAME,"name"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}