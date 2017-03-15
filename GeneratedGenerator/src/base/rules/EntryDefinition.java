package base.rules;

import com.rem.parser.*;
import lists.*;

public class EntryDefinition extends ConcreteRule {

	public static final IRule parser = new EntryDefinition();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public EntryDefinition(){
		super("entry_definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new WithParser((IRule)Rules.whitetab,this.tabs)),
					
					new ChoiceParser(
							new AddTokenParser(
								
								new ChainParser(
									new ListNameParser("entry_names"),
									Tokens.SINGLE),"getSingle"),
						new ChainParser(
							new ListNameParser("element_names"),
							Rules.list_entry_definition),
							Rules.list_entry_definition,
							Braces.TAB_BRACES)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}