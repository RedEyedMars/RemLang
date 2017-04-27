package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClauseTypeTokens extends ConcreteRule {

	public static final IRule parser = new ClauseTypeTokens();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

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
							new WithParser((IRule)Rules.token_clause,new Argument.Add(this.tabs,new Argument.Number(1))))));

	}

}