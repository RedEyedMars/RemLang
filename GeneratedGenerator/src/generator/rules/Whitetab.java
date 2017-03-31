package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Whitetab extends ConcreteRule {

	public static final IRule parser = new Whitetab();
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
									new WithParser((IRule)Tabs.TAB,new Parameter<Integer>(0)))));

	}

}