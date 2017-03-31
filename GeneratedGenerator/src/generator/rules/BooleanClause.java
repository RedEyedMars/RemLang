package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BooleanClause extends ConcreteRule {

	public static final IRule parser = new BooleanClause();
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
							new ListNameElementParser("token_names"),
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

}