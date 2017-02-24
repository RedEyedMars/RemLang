package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class BooleanClause extends ConcreteRule {

	public static final IRule parser = new BooleanClause();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public BooleanClause(){
		super("boolean_clause");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					new AddTokenParser(
						
						new ChainParser(
							Tokens.NOT,
							Rules.method_parameter),"notStatement"),
					new AddTokenParser(
						
						new ChainParser(
							Rules.method_parameter,
							new AddTokenParser(
								
							new ChoiceParser(
								new ChainParser(
									Tokens.IS,
									new OptionalParser(
											Tokens.NOT)),
									Tokens.ORDINAL_OPERATOR),"operator"),
							
							new ChoiceParser(
									Rules.method_parameter,
									Tokens.EMPTY,
									Tokens.SINGULAR)),"operatedStatement")));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}