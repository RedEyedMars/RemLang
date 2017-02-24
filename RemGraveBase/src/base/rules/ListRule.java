package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class ListRule extends ConcreteRule {

	public static final IRule parser = new ListRule();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public ListRule(){
		super("list_rule");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenToListParser(
						Tokens.LISTNAME,"listname","listnames"),
					new OptionalParser(
							
								new ChainParser(
									Tokens.SPACES,
									Tokens.ARE,
									Tokens.SPACES,
									new AddTokenParser(
										Tokens.NAME,"listType"))),
					new OptionalParser(
							Tokens.SPACES),
					Tokens.NEWLINE,
					new ManyParser(
							
								new ChainParser(
									Tokens.TAB,
									new AddTokenParser(
										
										new ChainParser(
											
											new ChoiceParser(
													Braces.QUOTE,
													Braces.SQUARE),
											Rules.parameters,
											new OptionalParser(
													
														new ChainParser(
															Tokens.WITH,
															Tokens.SPACES,
															new AddTokenParser(
																Altbraces.NEWLINED_DEF,"parameter")))),"list_def"),
									Tokens.NEWLINE))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}