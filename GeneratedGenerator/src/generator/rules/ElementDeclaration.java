package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ElementDeclaration extends ConcreteRule {

	public static final IRule parser = new ElementDeclaration();
	public ElementDeclaration(){
		super("element_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					Tokens.ELEMENTS,
					new MultipleParser(
							new WithParser((IRule)Rules.element_definition,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))))));

	}

}