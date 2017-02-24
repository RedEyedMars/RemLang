package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Whitetab extends ConcreteRule {

	public static final IRule parser = new Whitetab();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public Whitetab(){
		super("whitetab");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
					new MultipleParser(
							
								new ChainParser(
									Tokens.NEWLINE,
									new WithParser((IRule)Tabs.TAB,this.tabs))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}