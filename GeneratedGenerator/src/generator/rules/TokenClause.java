package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TokenClause extends ConcreteRule {

	public static final IRule parser = new TokenClause();
	public TokenClause(){
		super("token_clause");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					new AddTokenParser(
						Tokens.NAME,"specificTokenName"),
					new AddTokenParser(
						new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
									new WithParser((IRule)Rules.body_element,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))))),"body")));

	}

}