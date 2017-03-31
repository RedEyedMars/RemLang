package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ErrorStatement extends ConcreteRule {

	public static final IRule parser = new ErrorStatement();
	public ErrorStatement(){
		super("error_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					Tokens.ERROR,
					new MultipleParser(
							
								new ChainParser(
									new OptionalParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1)))),
									
									new ChoiceParser(
											Braces.QUOTE,
											Rules.variable_or_token_name)))));

	}

}