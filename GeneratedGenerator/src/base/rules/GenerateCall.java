package base.rules;

import com.rem.parser.*;
import lists.*;

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
					new AddTokenParser(
						Tokens.GENERATE,"subject"),
					new AddTokenParser(
						Tokens.NAME,"methodName"),
					new OptionalParser(
							new AddTokenParser(
								Braces.ANGLE_BRACES,"angle_braces"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}