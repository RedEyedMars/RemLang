package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class ElementDeclaration extends ConcreteRule {

	public static final IRule parser = new ElementDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public ElementDeclaration(){
		super("element_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.ELEMENTS,
					new MultipleParser(
							new WithParser((IRule)Rules.element_definition,new Argument.Add(this.tabs,new Parameter<Integer>(1))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}