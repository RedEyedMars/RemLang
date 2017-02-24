package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class ClassDefinition extends ConcreteRule {

	public static final IRule parser = new ClassDefinition();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public ClassDefinition(){
		super("class_definition");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenToListParser(
						Tokens.NAME,"className","class_names"),
					new OptionalParser(
							Tokens.SPACES),
					new OptionalParser(
							
								new ChainParser(
									Tokens.TAKES,
									Tokens.SPACES,
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