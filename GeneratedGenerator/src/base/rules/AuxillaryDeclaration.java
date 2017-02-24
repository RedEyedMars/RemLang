package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class AuxillaryDeclaration extends ConcreteRule {

	public static final IRule parser = new AuxillaryDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public AuxillaryDeclaration(){
		super("auxillary_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.AUXILLARY,
					new AddTokenParser(
						Tokens.NAME,"methodName"),
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
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Parameter<Integer>(1)))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}