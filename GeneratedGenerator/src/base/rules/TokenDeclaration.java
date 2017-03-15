package base.rules;

import com.rem.parser.*;
import lists.*;

public class TokenDeclaration extends ConcreteRule {

	public static final IRule parser = new TokenDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public TokenDeclaration(){
		super("token_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.TOKEN,
					new AddTokenToListParser(
						Tokens.NAME,"tokenName","token_names"),
					
					new ChoiceParser(
							new WithParser((IRule)Rules.method_call,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
							new AddTokenParser(
								
									new AddTokenParser(
										
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
											new ListNameParser("token_names"),
											new MultipleParser(
													
														new ChainParser(
															Tokens.ACCESS,
															new AddTokenParser(
																Tokens.NAME,"option")))),"variable_or_token_name"),"method_parameter"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}