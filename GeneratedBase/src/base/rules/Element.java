package base.rules;

import com.rem.parser.*;
import lists.*;

public class Element extends ConcreteRule {

	public static final IRule parser = new Element();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Element(){
		super("element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new AddTokenParser(
						
						new ChainParser(
							Rules.definition,
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.OPTIONAL,
									Tokens.MANY,
									Tokens.PLUS),"option")),"multiple"),
					new AddTokenParser(
						Braces.BRACE,"braced"),
					Rules.terminal));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}