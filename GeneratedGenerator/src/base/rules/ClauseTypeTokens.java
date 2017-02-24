package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class ClauseTypeTokens extends ConcreteRule {

	public static final IRule parser = new ClauseTypeTokens();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public ClauseTypeTokens(){
		super("clause_type_tokens");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					Tokens.TO,
					new AddTokenToListParser(
						Tokens.NAME,"tokenName","token_names"),
					new MultipleParser(
							new WithParser((IRule)Rules.token_clause,new Argument.Add(this.tabs,new Parameter<Integer>(1))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}