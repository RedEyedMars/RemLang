package base.rules;

import com.rem.parser.*;
import lists.*;

public class FlipSwitch extends ConcreteRule {

	public static final IRule parser = new FlipSwitch();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public FlipSwitch(){
		super("flip_switch");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.FLIP,
					new ListNameParser("variable_names"),
					new ManyParser(
							
								new ChainParser(
									new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
									new AddTokenParser(
										
									new ChoiceParser(
											Braces.QUOTE,
											Tokens.NON_SPACE),"left"),
									Tokens.EQUALSIGN,
									new AddTokenParser(
										
									new ChoiceParser(
											Braces.QUOTE,
											Tokens.NON_SPACE),"right"))),
					new OptionalParser(
							
									new WithParser((IRule)Rules.else_statement,new Argument.Add(this.tabs,new Parameter<Integer>(1))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}