package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TokenExpansion extends ConcreteRule {

	public static final IRule parser = new TokenExpansion();
	public TokenExpansion(){
		super("token_expansion");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					new ListNameElementParser("token_names"),
					
					new ChoiceParser(
							new AddTokenParser(
								Tokens.NAME_WORD,"getName"),
							new WithParser((IRule)Rules.clause_type_tokens,new Parameter<Integer>(0)),
							new WithParser((IRule)Rules.all_type_tokens,new Parameter<Integer>(0)))));

	}

}