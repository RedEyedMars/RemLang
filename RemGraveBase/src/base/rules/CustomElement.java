package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class CustomElement extends ConcreteRule {

	public static final IRule parser = new CustomElement();

	public CustomElement(){
		super("custom_element");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					Tokens.WITH,
					Altbraces.NEWLINED_DEF),
				new ChainParser(
					Tokens.DEFINE,
					new MultipleParser(
							new AddTokenParser(
								
								new ChainParser(
									Tokens.NEWLINE,
									Tokens.TABS,
									new AddTokenToListParser(
										Tokens.NAME,"variableName","custom_variable_names"),
									Tokens.COLON,
									Altbraces.NEWLINED_DEF),"define_variable"))),
					Rules.event_definition));

	}

}