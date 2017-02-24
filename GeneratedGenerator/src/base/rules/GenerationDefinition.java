package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class GenerationDefinition extends ConcreteRule {

	public static final IRule parser = new GenerationDefinition();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public GenerationDefinition(){
		super("generation_definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.GENERATE,
					Tokens.SPACES,
					new AddTokenToListParser(
						Tokens.NAME,"tokenName","token_names"),
					new OptionalParser(
							
								new ChainParser(
									Tokens.SPACES,
									Tokens.TAKES,
									Tokens.SPACES,
									new AddTokenToListParser(
										Tokens.NAME,"takeName","variable_names"),
									new ManyParser(
											
												new ChainParser(
													new OptionalParser(
															Tokens.SPACES),
													Tokens.COMMA,
													new OptionalParser(
															Tokens.SPACES),
													new AddTokenToListParser(
														Tokens.NAME,"takeName","variable_names"))))),
					new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Parameter<Integer>(1)))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}