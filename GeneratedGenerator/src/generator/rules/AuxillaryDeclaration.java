package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class AuxillaryDeclaration extends ConcreteRule {

	public static final IRule parser = new AuxillaryDeclaration();
	public AuxillaryDeclaration(){
		super("auxillary_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					Tokens.AUXILLARY,
					new AddTokenParser(
						Tokens.NAME,"methodName"),
					new OptionalParser(
							new WithParser((IRule)Rules.takes_statement,new Parameter<Integer>(0))),
					new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
									new WithParser((IRule)Rules.body_element,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1)))))));

	}

}