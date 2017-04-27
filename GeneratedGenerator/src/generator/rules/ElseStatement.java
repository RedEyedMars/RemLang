package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ElseStatement extends ConcreteRule {

	public static final IRule parser = new ElseStatement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

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
							
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Argument.Number(1))))));

	}

}