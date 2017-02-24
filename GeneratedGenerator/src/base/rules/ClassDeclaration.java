package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class ClassDeclaration extends ConcreteRule {

	public static final IRule parser = new ClassDeclaration();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public ClassDeclaration(){
		super("class_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenToListParser(
						Tokens.NAME,"className","class_names"),
					new OptionalParser(
							
								new ChainParser(
									Tokens.TAKES,
									new AddTokenParser(
										Tokens.NAME,"parameter"),
									new ManyParser(
											
												new ChainParser(
													Tokens.COMMA,
													new AddTokenParser(
														Tokens.NAME,"parameter"))))),
					new MultipleParser(
							
									new WithParser((IRule)Rules.class_element,new Parameter<Integer>(1)))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}