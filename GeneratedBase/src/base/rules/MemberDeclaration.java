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
					new AddTokenToListParser(
						Tokens.NAME,"rule_param","rule_parameters"),
					new ManyParser(
							
								new ChainParser(
									Tokens.COMMA,
									new AddTokenToListParser(
										Tokens.NAME,"rule_param","rule_parameters")))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}