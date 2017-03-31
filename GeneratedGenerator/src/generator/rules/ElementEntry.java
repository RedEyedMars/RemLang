package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ElementEntry extends ConcreteRule {

	public static final IRule parser = new ElementEntry();
	public ElementEntry(){
		super("element_entry");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0))),
					Braces.QUOTE,
					new ManyParser(
							
								new ChainParser(
									Tokens.PLUS,
									new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
									Braces.QUOTE))));

	}

}