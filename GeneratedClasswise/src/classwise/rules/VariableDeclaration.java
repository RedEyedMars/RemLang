package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class VariableDeclaration extends ConcreteRule {

	public static final IRule parser = new VariableDeclaration();

	public VariableDeclaration(){
		super("variable_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new OptionalParser(
							Tokens.WEAK),
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.STA,
									Tokens.TIC),"static")),
					new OptionalParser(
							Tokens.WEAK),
					
					new ChoiceParser(
							Rules.class_name_definition,
							Rules.variable_name_definition),
					new OptionalParser(
							
								new ChainParser(
									Tokens.EQUALS,
									Rules.method_argument))));

	}

}