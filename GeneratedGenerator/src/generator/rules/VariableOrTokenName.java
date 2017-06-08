package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class VariableOrTokenName extends ConcreteRule {

	public static final IRule parser = new VariableOrTokenName();

	public VariableOrTokenName(){
		super("variable_or_token_name");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new OptionalParser(
							
							new ChoiceParser(
									new AddTokenParser(
										Tokens.PRIME,"getString"),
									new AddTokenParser(
										Tokens.CARROT,"camelize"))),
					
					new ChoiceParser(
						new ChainParser(
							
							new ChoiceParser(
									new ListNameElementParser("token_names"),
									new ListNameElementParser("variable_names")),
							new MultipleParser(
									
										new ChainParser(
											Tokens.ACCESS,
											new AddTokenParser(
												
											new ChoiceParser(
													new ListNameElementParser("variable_names"),
													Tokens.NAME),"option")))),
							Rules.arithmatic)),
				new ChainParser(
					new OptionalParser(
							
							new ChoiceParser(
									new AddTokenParser(
										Tokens.PRIME,"getString"),
									new AddTokenParser(
										Tokens.CARROT,"camelize"))),
					new ListNameElementParser("token_names"))));

	}

}