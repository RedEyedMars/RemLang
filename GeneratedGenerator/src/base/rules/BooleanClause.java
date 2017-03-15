package base.rules;

import com.rem.parser.*;
import lists.*;

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
							new ListNameParser("token_names"),
							Tokens.CONTAINS,
							new AddTokenParser(
								Tokens.NAME,"argument")),"containsStatement"),
					new AddTokenParser(
						
						new ChainParser(
							new AddTokenParser(
								Rules.method_parameter,"left"),
							new AddTokenParser(
								
							new ChoiceParser(
								new ChainParser(
									Tokens.IS,
									new OptionalParser(
											Tokens.NOT)),
									Tokens.ORDINAL_OPERATOR),"operator"),
							new AddTokenParser(
								
							new ChoiceParser(
									Rules.method_parameter,
									Tokens.EMPTY,
									Tokens.SINGULAR),"right")),"operatedStatement")));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}