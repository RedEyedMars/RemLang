package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class VariableOrTokenName extends ConcreteRule {

	public static final IRule parser = new VariableOrTokenName();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public VariableOrTokenName(){
		super("variable_or_token_name");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new OptionalParser(
							
									new AddTokenParser(
										Tokens.PRIME,"getString")),
					
					new ChoiceParser(
						new ChainParser(
							new ListNameParser("token_names"),
							new MultipleParser(
									
										new ChainParser(
											Tokens.ACCESS,
											new AddTokenParser(
												Tokens.NAME,"option")))),
							Rules.arithmatic)),
				new ChainParser(
					new OptionalParser(
							
									new AddTokenParser(
										Tokens.PRIME,"getString")),
					new ListNameParser("token_names"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}