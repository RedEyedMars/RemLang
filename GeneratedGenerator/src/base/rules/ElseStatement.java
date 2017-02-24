package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class ElseStatement extends ConcreteRule {

	public static final IRule parser = new ElseStatement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public ElseStatement(){
		super("else_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.ELSE,
					new MultipleParser(
							
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Parameter<Integer>(1))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}