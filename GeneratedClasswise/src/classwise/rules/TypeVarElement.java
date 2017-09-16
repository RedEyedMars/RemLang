package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TypeVarElement extends ConcreteRule {

	public static final IRule parser = new TypeVarElement();

	public TypeVarElement(){
		super("type_var_element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new AddTokenParser(
						Braces.STATEMENT_AS_METHOD,"as_method"),
					new AddTokenParser(
						Braces.STATEMENT_AS_QUOTE,"as_quote"),
					new AddTokenParser(
						Braces.STATEMENT_AS_STRING,"as_string"),
					new AddTokenParser(
						
						new ChainParser(
							Tokens.BACKSLASH,
							new OptionalParser(
									Tokens.CAMEL),
							
							new ChoiceParser(
									new ListNameElementParser("variable_names"),
									Tokens.NAME),
							new OptionalParser(
									new AddTokenParser(
										Braces.TEMPLATE_PARAMETERS,"template_parameters"))),"exact"),
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									Tokens.CAMEL),
							
							new ChoiceParser(
									new ListNameElementParser("class_variable_names"),
									new ListNameElementParser("class_names")),
							new OptionalParser(
									new AddTokenParser(
										Braces.TEMPLATE_PARAMETERS,"template_parameters"))),"class")));

	}

}