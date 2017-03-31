package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BodyElement extends ConcreteRule {

	public static final IRule parser = new BodyElement();
	public BodyElement(){
		super("body_element");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					new WithParser((IRule)Rules.error_statement,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.return_statement,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.if_statement,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.token_declaration,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.variable_declaration,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.set_call,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.each_call,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.flip_switch,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.token_expansion,new Parameter<Integer>(0)),
					new WithParser((IRule)Rules.method_call,new Parameter<Integer>(0))));

	}

}