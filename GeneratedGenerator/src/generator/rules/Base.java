package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Base extends ConcreteRule {

	public static final IRule parser = new Base();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Base(){
		super("base");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
					new MultipleParser(
							
							new ChoiceParser(
									Tokens.NEWLINE,
									Rules.used_classes_list,
									new AddTokenToListParser(
										Rules.property_declaration,"property_dec","property_definitions"),
									new AddTokenToListParser(
										Rules.entry_class_declaration,"entry_dec","entry_class_definitions"),
									new AddTokenToListParser(
										Rules.class_declaration,"class_dec","class_definitions"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}