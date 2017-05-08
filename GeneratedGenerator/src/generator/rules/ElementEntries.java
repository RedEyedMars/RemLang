package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ElementEntries extends ConcreteRule {

	public static final IRule parser = new ElementEntries();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public ElementEntries(){
		super("element_entries");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new WithParser((IRule)Rules.element_entry,this.tabs),
					new ManyParser(
							
								new ChainParser(
									new AddTokenParser(
										new ManyParser(
											
													Tokens.NAME),"entry"),
									new WithParser((IRule)Rules.element_entry,this.tabs)))));

	}

}