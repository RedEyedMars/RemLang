package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class MethodCall extends ConcreteRule {

	public static final IRule parser = new MethodCall();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public MethodCall(){
		super("method_call");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.NEW,
							Tokens.GENERATE,
							Rules.method_parameter),"subject"),
					new AddTokenParser(
						Tokens.NAME,"methodName"),
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.ANGLE_BRACES,"angle_braces"),
							new ManyParser(
									
									new ChoiceParser(
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
											Rules.boolean_statement),
											new WithParser((IRule)Rules.method_call,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
											Rules.method_parameter))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}