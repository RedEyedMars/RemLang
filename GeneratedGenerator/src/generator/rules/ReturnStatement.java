package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ReturnStatement extends ConcreteRule {

	public static final IRule parser = new ReturnStatement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public ReturnStatement(){
		super("return_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.RETURN,
					
					new ChoiceParser(
							new WithParser((IRule)Rules.generate_call,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
							new WithParser((IRule)Rules.entry_definition,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
							Rules.boolean_statement),
							new WithParser((IRule)Rules.method_call,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
							
							new ChoiceParser(
									new ListNameParser("entry_names"),
									Tokens.NULL)))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}