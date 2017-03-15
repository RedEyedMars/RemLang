package base.rules;

import com.rem.parser.*;
import lists.*;

public class ErrorStatement extends ConcreteRule {

	public static final IRule parser = new ErrorStatement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public ErrorStatement(){
		super("error_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.ERROR,
					new MultipleParser(
							
								new ChainParser(
									new OptionalParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1)))),
									
									new ChoiceParser(
											Braces.QUOTE,
											Rules.variable_or_token_name)))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}