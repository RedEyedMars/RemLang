package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class SetCall extends ConcreteRule {

	public static final IRule parser = new SetCall();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public SetCall(){
		super("set_call");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new WithParser((IRule)Rules.whitetab,this.tabs)),
					Tokens.SET,
					new AddTokenParser(
						Tokens.NAME,"subject"),
					new OptionalParser(
							Rules.cast_as_statement),
					
					new ChoiceParser(
						new ChainParser(
							Tokens.EQUALSIGN,
							
							new ChoiceParser(
									Rules.boolean_statement,
									Rules.method_parameter)),
							
							new ChoiceParser(
								new ChainParser(
									new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(1))),
									Rules.boolean_statement),
									new WithParser((IRule)Rules.method_call,new Argument.Add(this.tabs,new Argument.Number(1))),
								new ChainParser(
									new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(1))),
									Rules.method_parameter)))));

	}

}