package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BodyConditional extends ConcreteRule {

	public static final IRule parser = new BodyConditional();

	public BodyConditional(){
		super("body_conditional");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.IF,
						new ChainParser(
							Tokens.ELSE,
							Tokens.IF),
							Tokens.WHILE,
							Tokens.SYNCHRONIZED,
							Tokens.SWITCH,
							Tokens.CASE),"conditional"),
					Rules.body_statement,
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.METHOD_BODY,"as_body"),
							new AddTokenParser(
								Braces.STATEMENT_AS_METHOD,"as_method"))),
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new AddTokenParser(
						Tokens.ELSE,"conditional"),
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.METHOD_BODY,"as_body"),
							new AddTokenParser(
								Braces.STATEMENT_AS_METHOD,"as_method"))),
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new AddTokenParser(
						Tokens.FOR,"conditional"),
					Rules.variable_declaration,
					Tokens.OPERATOR,
					Rules.body_statement,
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.METHOD_BODY,"as_body"),
							new AddTokenParser(
								Braces.STATEMENT_AS_METHOD,"as_method"))),
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new AddTokenParser(
						Tokens.TRY,"conditional"),
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.METHOD_BODY,"as_body"),
							new AddTokenParser(
								Braces.STATEMENT_AS_METHOD,"as_method"))),
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new OptionalParser(
							Tokens.PRINT),
					new AddTokenParser(
						Tokens.CATCH,"conditional"),
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.AS_GENERIC,
							Tokens.NAME),"exception"),
					new ManyParser(
							
								new ChainParser(
									Tokens.PIPE,
									new AddTokenParser(
										
									new ChoiceParser(
											Tokens.AS_GENERIC,
											Tokens.NAME),"exception"))),
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.METHOD_BODY,"as_body"),
							new AddTokenParser(
								Braces.STATEMENT_AS_METHOD,"as_method")))));

	}

}