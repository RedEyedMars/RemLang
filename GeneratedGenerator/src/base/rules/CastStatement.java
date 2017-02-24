package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class CastStatement extends ConcreteRule {

	public static final IRule parser = new CastStatement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public CastStatement(){
		super("cast_statement");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.CAST,
					new ListNameParser("variable_names"),
					Rules.cast_as_statement));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}