package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BracedParameters extends ConcreteRule {

	public static final IRule parser = new BracedParameters();

	public BracedParameters(){
		super("braced_parameters");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new AddTokenParser(
						CargonTokens.NONSPACE,"left"),
					new AddTokenParser(
						CargonTokens.NONSPACE,"right")));

	}

}