package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class EntryDefinition extends ConcreteRule {

	public static final IRule parser = new EntryDefinition();
	public EntryDefinition(){
		super("entry_definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0))),
					
					new ChoiceParser(
							new AddTokenParser(
								
								new ChainParser(
									new ListNameElementParser("entry_names"),
									Tokens.SINGLE),"getSingle"),
							Braces.CUSTOM_ENTRY_DEFINITION,
							new AddTokenParser(
								Braces.QUOTE_ENTRY,"quoted"),
						new ChainParser(
							new ListNameElementParser("generator_names"),
							
							new ChoiceParser(
									new ListNameElementParser("element_names"),
									new ListNameElementParser("variable_names")),
							Rules.list_entry_definition),
						new ChainParser(
							new ListNameElementParser("element_names"),
							Rules.list_entry_definition),
							Rules.list_entry_definition,
							Braces.TAB_BRACES)));

	}

}