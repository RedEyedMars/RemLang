package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class NameVarElement extends ConcreteRule {

	public static final IRule parser = new NameVarElement();

	public NameVarElement(){
		super("name_var_element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new AddTokenParser(
						
							new AddTokenParser(
								Tokens.NUMBER,"NAME"),"exact"),
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
									Braces.QUOTE,
									new ListNameElementParser("variable_names"),
									Tokens.NAME)),"exact"),
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									Tokens.CAMEL),
							
									new ListNameElementParser("variable_names")),"variable"),
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									Tokens.CAMEL),
							
							new ChoiceParser(
									Braces.QUOTE,
									new AddTokenParser(
										
									new ChoiceParser(
											Tokens.NUMBER,
											Tokens.SUPER,
											Tokens.THIS,
											Tokens.NULL,
											Tokens.TRUE,
											Tokens.FALSE),"NAME"))),"exact")));

	}

}