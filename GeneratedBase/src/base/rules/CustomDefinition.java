package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class CustomDefinition extends ConcreteRule {

	public static final IRule parser = new CustomDefinition();

	public CustomDefinition(){
		super("custom_definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					Tokens.AS,
					new AddTokenParser(
						Tokens.NAME,"name"),
					new MultipleParser(
							
								new ChainParser(
									Tokens.NEWLINE,
									Tokens.TAB,
									Rules.custom_element))));

	}

}