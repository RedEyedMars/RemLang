package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ElementDefinition extends ConcreteRule {

	public static final IRule parser = new ElementDefinition();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public ElementDefinition(){
		super("element_definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					new AddTokenToListParser(
						Tokens.NAME,"elementName","element_names"),
					new WithParser((IRule)Rules.element_entry,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
					new ManyParser(
							
								new ChainParser(
									new AddTokenParser(
										new ManyParser(
											
													Tokens.NAME),"entry"),
									new WithParser((IRule)Rules.element_entry,new Argument.Add(this.tabs,new Parameter<Integer>(1)))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}