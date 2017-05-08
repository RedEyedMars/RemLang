package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ElementDeclaration extends ConcreteRule {

	public static final IRule parser = new ElementDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public ElementDeclaration(){
		super("element_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.ELEMENTS,
					
					new ChoiceParser(
							Ingests.ELEMENT_IMPORT,
							new MultipleParser(
									new WithParser((IRule)Rules.element_definition,new Argument.Add(this.tabs,new Argument.Number(1)))))));

	}

}