package base.rules;

import com.rem.parser.*;
import base.lists.*;

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
					Braces.ENTRY_LIST,
					Braces.ENTRY_STRING));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}