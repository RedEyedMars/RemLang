package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class MemberDeclaration extends ConcreteRule {

	public static final IRule parser = new MemberDeclaration();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public MemberDeclaration(){
		super("member_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					Tokens.HAS,
					Tokens.SPACES,
					new AddTokenToListParser(
						Tokens.NAME,"rule_param","rule_parameters"),
					new ManyParser(
							
								new ChainParser(
									new OptionalParser(
											Tokens.SPACES),
									Tokens.COMMA,
									new OptionalParser(
											Tokens.SPACES),
									new AddTokenToListParser(
										Tokens.NAME,"rule_param","rule_parameters")))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}