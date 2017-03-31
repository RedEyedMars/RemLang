package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class GenerateCall extends ConcreteRule {

	public static final IRule parser = new GenerateCall();
	public GenerateCall(){
		super("generate_call");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0))),
					new AddTokenParser(
						Tokens.GENERATE,"subject"),
					new AddTokenParser(
						Tokens.NAME,"methodName"),
					new OptionalParser(
							new AddTokenParser(
								Braces.ANGLE_BRACES,"angle_braces"))));

	}

}