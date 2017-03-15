package base.rules;

import com.rem.parser.*;
import lists.*;

public class ElementEntry extends ConcreteRule {

	public static final IRule parser = new ElementEntry();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public ElementEntry(){
		super("element_entry");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new WithParser((IRule)Rules.whitetab,this.tabs)),
					Braces.QUOTE,
					new ManyParser(
							
								new ChainParser(
									Tokens.PLUS,
									new WithParser((IRule)Rules.whitetab,this.tabs),
									Braces.QUOTE))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}