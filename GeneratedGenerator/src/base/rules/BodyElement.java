package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class BodyElement extends ConcreteRule {

	public static final IRule parser = new BodyElement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public BodyElement(){
		super("body_element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new WithParser((IRule)Rules.error_statement,this.tabs),
					new WithParser((IRule)Rules.return_statement,this.tabs),
					new WithParser((IRule)Rules.if_statement,this.tabs),
					new WithParser((IRule)Rules.token_declaration,this.tabs),
					new WithParser((IRule)Rules.variable_declaration,this.tabs),
					new WithParser((IRule)Rules.flip_switch,this.tabs),
					new WithParser((IRule)Rules.token_expansion,this.tabs),
					new WithParser((IRule)Rules.method_call,this.tabs)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}