package base.rules;

import com.rem.parser.*;
import base.lists.*;

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