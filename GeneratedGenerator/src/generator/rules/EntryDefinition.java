package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class EntryDefinition extends ConcreteRule {

	public static final IRule parser = new EntryDefinition();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

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
									new ListNameElementParser("entry_names"),
									Tokens.SINGLE),"getSingle"),
							Rules.generate_call,
						new ChainParser(
							new ListNameElementParser("entry_class_names"),
							Braces.CUSTOM_ENTRY_DEFINITION),
						new ChainParser(
							Tokens.BACKSLASH,
							new AddTokenParser(
								
							new ChoiceParser(
									Braces.QUOTE,
									Rules.variable_or_token_name),"string")),
							new AddTokenParser(
								Braces.QUOTE_ENTRY,"quoted"),
						new ChainParser(
							new AddTokenParser(
								new ListNameElementParser("generator_names"),"gen"),
							new ReContextParser(
								
							new ChoiceParser(
									new ListNameElementParser("element_names"),
									new ListNameElementParser("variable_names")),"gen","class_definitions"),
							Rules.list_entry_definition),
						new ChainParser(
							new ListNameElementParser("element_names"),
							Rules.list_entry_definition),
							Rules.list_entry_definition,
							Braces.TAB_BRACES)));

	}

}