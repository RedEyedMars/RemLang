package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ListRule extends ConcreteRule {

	public static final IRule parser = new ListRule();

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
									Tokens.ARE,
									new AddTokenParser(
										Tokens.NAME,"listType"))),
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
															Tokens.FROM,
															new ManyParser(
																	new AddTokenParser(
																		
																		new ChainParser(
																			new OptionalParser(
																					
																						new ChainParser(
																							new AddTokenParser(
																								Tokens.NAME,"parserName"),
																							Tokens.COLON)),
																			Braces.SEMICOLONED),"parser")))),
											new OptionalParser(
													
														new ChainParser(
															Tokens.WITH,
															new AddTokenParser(
																Altbraces.NEWLINED_DEF,"parameter")))),"list_def"),
									Tokens.NEWLINE))));

	}

}