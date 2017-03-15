package base.rules;

import com.rem.parser.*;
import lists.*;

public class GenerationDeclaration extends ConcreteRule {

	public static final IRule parser = new GenerationDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public GenerationDeclaration(){
		super("generation_declaration");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.GENERATE,
					new OptionalParser(
							
								new ChainParser(
									Tokens.TAKES,
									new AddTokenToListParser(
										Tokens.NAME,"takeName","variable_names"),
									new ManyParser(
											
												new ChainParser(
													Tokens.COMMA,
													new AddTokenToListParser(
														Tokens.NAME,"takeName","variable_names"))))),
					new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Parameter<Integer>(1)))))),
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.GENERATE,
					new AddTokenToListParser(
						Tokens.NAME,"tokenName","token_names"),
					new OptionalParser(
							
								new ChainParser(
									Tokens.TAKES,
									new AddTokenToListParser(
										Tokens.NAME,"takeName","variable_names"),
									new ManyParser(
											
												new ChainParser(
													Tokens.COMMA,
													new AddTokenToListParser(
														Tokens.NAME,"takeName","variable_names"))))),
					new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Parameter<Integer>(1))))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}