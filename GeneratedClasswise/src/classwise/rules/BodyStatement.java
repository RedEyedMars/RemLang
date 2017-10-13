package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BodyStatement extends ConcreteRule {

	public static final IRule parser = new BodyStatement();

	public BodyStatement(){
		super("body_statement");
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
					Rules.body_call,
					new ManyParser(
							
								new ChainParser(
									new ManyParser(
											Tokens.NEWLINE),
									
										new ChainParser(
											new OptionalParser(
													Tokens.BACKSLASH),
											Tokens.OPERATOR),
									new ManyParser(
											Tokens.NEWLINE),
									Rules.body_call))),
					new AddTokenParser(
						Braces.STATEMENT_AS_STRING,"as_string"),
					new AddTokenParser(
						
						new ChainParser(
							new AddTokenParser(
								Braces.STATEMENT_AS_BRACED,"left"),
							new OptionalParser(
									
										new ChainParser(
											new OptionalParser(
													Tokens.BACKSLASH),
											Tokens.OPERATOR)),
							new OptionalParser(
									new AddTokenParser(
										Rules.body_statement,"right"))),"as_braced")));

	}

}