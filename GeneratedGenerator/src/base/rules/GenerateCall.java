package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class GenerateCall extends ConcreteRule {

	public static final IRule parser = new GenerateCall();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public GenerateCall(){
		super("generate_call");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new WithParser((IRule)Rules.whitetab,this.tabs)),
					Tokens.GENERATE,
					new AddTokenParser(
						Tokens.NAME,"methodName"),
					new OptionalParser(
							Braces.ANGLE_BRACES)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}