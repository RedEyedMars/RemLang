package base.rules;

import com.rem.parser.*;
import lists.*;

public class TokenClause extends ConcreteRule {

	public static final IRule parser = new TokenClause();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public TokenClause(){
		super("token_clause");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					new AddTokenParser(
						Tokens.NAME,"specificTokenName"),
					new AddTokenParser(
						new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Parameter<Integer>(1))))),"body")));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}