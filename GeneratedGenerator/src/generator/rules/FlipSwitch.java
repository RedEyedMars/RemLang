package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class FlipSwitch extends ConcreteRule {

	public static final IRule parser = new FlipSwitch();
	public FlipSwitch(){
		super("flip_switch");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					Tokens.FLIP,
					new ListNameElementParser("variable_names"),
					new ManyParser(
							
								new ChainParser(
									new WithParser((IRule)Rules.whitetab,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
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
							
									new WithParser((IRule)Rules.else_statement,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))))));

	}

}