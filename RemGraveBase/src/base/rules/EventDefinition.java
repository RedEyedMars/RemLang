package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class EventDefinition extends ConcreteRule {

	public static final IRule parser = new EventDefinition();

	public EventDefinition(){
		super("event_definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							Tokens.TAB),
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.START,
							Tokens.END,
							Tokens.NAME),"eventName"),
					Tokens.COLON,
					new AddTokenToListParser(
						Tokens.NAME,"variableName","custom_variable_names"),
					Tokens.BACK_ARROW,
					Rules.event_element));

	}

}