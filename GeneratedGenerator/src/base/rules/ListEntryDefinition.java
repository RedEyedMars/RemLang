package base.rules;

import com.rem.parser.*;
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
						Braces.ENTRY_STRING,"string")));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}