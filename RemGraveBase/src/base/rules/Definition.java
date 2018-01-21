package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Definition extends ConcreteRule {

	public static final IRule parser = new Definition();

	public Definition(){
		super("definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new AddTokenParser(
						
						new ChainParser(
							Rules.atom,
							new ManyParser(
									Rules.atom)),"chain"),
					new AddTokenParser(
						new OptionalParser(
							
								new ChainParser(
									
									new ChoiceParser(
											Tokens.PIPE,
										new ChainParser(
											Tokens.NEWLINE,
											Tokens.TAB)),
									Rules.definition)),"choice")));

	}

}