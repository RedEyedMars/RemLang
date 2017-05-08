package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class InlineAdditionCall extends ConcreteRule {

	public static final IRule parser = new InlineAdditionCall();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public InlineAdditionCall(){
		super("inline_addition_call");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					new AddTokenParser(
						Rules.method_parameter,"subject"),
					Tokens.PLUS,
					Tokens.EQUALSIGN,
					new AddTokenParser(
						
					new ChoiceParser(
							Rules.boolean_statement,
							Rules.method_parameter,
							new WithParser((IRule)Rules.method_call,new Argument.Add(this.tabs,new Argument.Number(1)))),"parameter")));

	}

}