package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TokenDeclaration extends ConcreteRule {

	public static final IRule parser = new TokenDeclaration();
	public TokenDeclaration(){
		super("token_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					Tokens.TOKEN,
					new AddTokenToListParser(
						Tokens.NAME,"tokenName","token_names"),
					
					new ChoiceParser(
							new WithParser((IRule)Rules.method_call,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
							new AddTokenParser(
								
									new AddTokenParser(
										
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
											new ListNameElementParser("token_names"),
											new MultipleParser(
													
														new ChainParser(
															Tokens.ACCESS,
															new AddTokenParser(
																Tokens.NAME,"option")))),"variable_or_token_name"),"method_parameter"))));

	}

}