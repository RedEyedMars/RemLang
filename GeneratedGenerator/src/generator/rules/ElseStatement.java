package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ElseStatement extends ConcreteRule {

	public static final IRule parser = new ElseStatement();
	public ElseStatement(){
		super("else_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					Tokens.ELSE,
					new MultipleParser(
							
									new WithParser((IRule)Rules.body_element,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))))));

	}

}