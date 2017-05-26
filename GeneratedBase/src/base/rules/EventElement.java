package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class EventElement extends ConcreteRule {

	public static final IRule parser = new EventElement();

	public EventElement(){
		super("event_element");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					Braces.QUOTE,
					Tokens.NUMBER,
					new AddTokenParser(
						
							new MultipleParser(
									
										new ChainParser(
											Tokens.FOR_ARROW,
											new OptionalParser(
													new AddTokenParser(
														Tokens.NAME,"tokenName")))),"token"),
					new AddTokenParser(
						
						new ChainParser(
							new AddTokenParser(
								Tokens.NAME,"className"),
							Braces.CUSTOM_BRACE),"classDefinition"),
					new ListNameElementParser("custom_variable_names")));

	}

}