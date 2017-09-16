package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MethodArgument extends ConcreteRule {

	public static final IRule parser = new MethodArgument();

	public MethodArgument(){
		super("method_argument");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					Rules.class_declaration,
					Rules.method_declaration,
				new ChainParser(
					Rules.variable_declaration,
					Tokens.SEMICOLON),
					new AddTokenParser(
						Braces.AS_STATEMENT,"as_statement"),
					Rules.body_statement,
					new AddTokenParser(
						Braces.METHOD_BODY,"body_entries")));

	}

}