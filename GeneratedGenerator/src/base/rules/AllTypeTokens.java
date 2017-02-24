package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class AllTypeTokens extends ConcreteRule {

	public static final IRule parser = new AllTypeTokens();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public AllTypeTokens(){
		super("all_type_tokens");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.STAR,
							Tokens.NAME),"specificTokenName"),
					Tokens.TO,
					new AddTokenToListParser(
						Tokens.NAME,"tokenName","token_names"),
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