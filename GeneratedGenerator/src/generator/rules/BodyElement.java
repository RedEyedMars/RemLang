package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BodyElement extends ConcreteRule {

	public static final IRule parser = new BodyElement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public BodyElement(){
		super("body_element");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					new WithParser((IRule)Rules.error_statement,this.tabs),
					new WithParser((IRule)Rules.return_statement,this.tabs),
					new WithParser((IRule)Rules.if_statement,this.tabs),
					new WithParser((IRule)Rules.check_call,this.tabs),
					new WithParser((IRule)Rules.token_declaration,this.tabs),
					new WithParser((IRule)Rules.variable_declaration,this.tabs),
					new WithParser((IRule)Rules.set_call,this.tabs),
					new WithParser((IRule)Rules.each_call,this.tabs),
					new WithParser((IRule)Rules.flip_switch,this.tabs),
					new WithParser((IRule)Rules.token_expansion,this.tabs),
					new WithParser((IRule)Rules.method_call,this.tabs)));

	}

}