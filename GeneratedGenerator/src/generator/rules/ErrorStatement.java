package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ErrorStatement extends ConcreteRule {

	public static final IRule parser = new ErrorStatement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public ErrorStatement(){
		super("error_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.ERROR,
					new OptionalParser(
							new ListNameElementParser("token_names")),
					new MultipleParser(
							
								new ChainParser(
									new OptionalParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(1)))),
									
									new ChoiceParser(
											Braces.QUOTE,
											Rules.variable_or_token_name)))));

	}

}