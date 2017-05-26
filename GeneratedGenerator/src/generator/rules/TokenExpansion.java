package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TokenExpansion extends ConcreteRule {

	public static final IRule parser = new TokenExpansion();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public TokenExpansion(){
		super("token_expansion");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					new ListNameElementParser("token_names"),
					
					new ChoiceParser(
							new WithParser((IRule)Rules.clause_type_tokens,this.tabs),
							new WithParser((IRule)Rules.all_type_tokens,this.tabs))));

	}

}