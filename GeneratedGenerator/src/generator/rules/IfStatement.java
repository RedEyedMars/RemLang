package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class IfStatement extends ConcreteRule {

	public static final IRule parser = new IfStatement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public IfStatement(){
		super("if_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.IF,
					Rules.boolean_statement,
					new MultipleParser(
							
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Parameter<Integer>(1)))),
					new OptionalParser(
							new WithParser((IRule)Rules.else_statement,this.tabs))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}