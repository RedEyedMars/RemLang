package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class CastStatement extends ConcreteRule {

	public static final IRule parser = new CastStatement();
	public CastStatement(){
		super("cast_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					Tokens.CAST,
					new ListNameElementParser("variable_names"),
					Rules.cast_as_statement));

	}

}