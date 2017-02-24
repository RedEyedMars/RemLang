package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class BooleanStatement extends ConcreteRule {

	public static final IRule parser = new BooleanStatement();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public BooleanStatement(){
		super("boolean_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					Rules.boolean_clause,
					new ManyParser(
							
									new AddTokenParser(
										
										new ChainParser(
											new AddTokenParser(
												
											new ChoiceParser(
													Tokens.AND,
													Tokens.OR),"continuationOperator"),
											Rules.boolean_clause),"continuationStatement"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}