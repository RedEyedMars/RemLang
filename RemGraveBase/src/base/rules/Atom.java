package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Atom extends ConcreteRule {

	public static final IRule parser = new Atom();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Atom(){
		super("atom");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					Rules.element,
					Rules.parameters,
					new OptionalParser(
							Braces.PARAM_BRACE)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}