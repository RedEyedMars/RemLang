package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BodyElement extends ConcreteRule {

	public static final IRule parser = new BodyElement();

	public BodyElement(){
		super("body_element");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					Tokens.NEWLINE,
					Comments.COMMENT,
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									new AddTokenParser(
										
									new ChoiceParser(
											Tokens.IN,
											Tokens.NER),"inner")),
							Tokens.RETURN,
							
							new ChoiceParser(
									Tokens.VOID,
									Rules.method_argument),
							Tokens.SEMICOLON),"body_return"),
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									new AddTokenParser(
										
									new ChoiceParser(
											Tokens.IN,
											Tokens.NER),"inner")),
							Tokens.THROW,
							Rules.body_statement,
							Tokens.SEMICOLON),"body_throw"),
					Rules.class_declaration,
					new AddTokenParser(
						
						new ChainParser(
							Rules.variable_declaration,
							Tokens.SEMICOLON),"body_line"),
					new AddTokenParser(
						
						new ChainParser(
							Rules.variable_assignment,
							Tokens.SEMICOLON),"body_line"),
					Rules.body_manipulate,
					Rules.body_conditional,
					new AddTokenParser(
						
						new ChainParser(
							Rules.body_statement,
							Tokens.SEMICOLON),"body_line")));

	}

}