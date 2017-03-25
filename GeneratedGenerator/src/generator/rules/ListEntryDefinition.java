package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ListEntryDefinition extends ConcreteRule {

	public static final IRule parser = new ListEntryDefinition();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public ListEntryDefinition(){
		super("list_entry_definition");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					new AddTokenParser(
						Braces.ENTRY_LIST,"list"),
					new AddTokenParser(
						Braces.ENTRY_STRING,"string"),
					new AddTokenParser(
						
						new ChainParser(
							Tokens.PIPE,
							new ListNameParser("variable_names"),
							Tokens.PIPE),"exact_variable")));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}