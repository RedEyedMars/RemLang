package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class EntryClassDeclaration extends ConcreteRule {

	public static final IRule parser = new EntryClassDeclaration();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public EntryClassDeclaration(){
		super("entry_class_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					Tokens.ENTRY,
					new AddTokenToListParser(
						Tokens.NAME,"entryClassName","entry_class_names"),
					new OptionalParser(
							new AddTokenParser(
								
								new ChainParser(
									Tokens.USES,
									new MultipleParser(
											new ListNameParser("property_names"))),"implements")),
					new ManyParser(
							Rules.entry_class_element)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}