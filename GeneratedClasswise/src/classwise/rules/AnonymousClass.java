package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class AnonymousClass extends ConcreteRule {

	public static final IRule parser = new AnonymousClass();

	public AnonymousClass(){
		super("anonymous_class");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					
					new ChoiceParser(
							Tokens.HID,
							Tokens.DEN),
					new AddTokenToListParser(
						Tokens.NAME,"className","class_names"),
					
					new ChoiceParser(
							Tokens.COLON,
							Tokens.FROM),
					new OptionalParser(
							
								new ChainParser(
									new AddTokenParser(
										Rules.name_var,"packageName"),
									new ManyParser(
											
												new ChainParser(
													Tokens.DOT,
													new AddTokenParser(
														Rules.name_var,"packageName"))))),
					Tokens.SEMICOLON));

	}

}