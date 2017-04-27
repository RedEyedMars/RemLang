package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class AllTypeTokens extends ConcreteRule {

	public static final IRule parser = new AllTypeTokens();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public AllTypeTokens(){
		super("all_type_tokens");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new AddTokenParser(
						
					new ChoiceParser(
							new AddTokenParser(
								Tokens.STAR,"wild"),
							Tokens.NAME),"specificTokenName"),
					Tokens.TO,
					new AddTokenToListParser(
						Tokens.NAME,"tokenName","token_names"),
					new AddTokenParser(
						new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Add(this.tabs,new Argument.Number(1))),
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Argument.Number(1))))),"body")));

	}

}