package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class AuxillaryDeclaration extends ConcreteRule {

	public static final IRule parser = new AuxillaryDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public AuxillaryDeclaration(){
		super("auxillary_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.AUXILLARY,
					new AddTokenParser(
						Tokens.NAME,"methodName"),
					new OptionalParser(
							new WithParser((IRule)Rules.takes_statement,this.tabs)),
					new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Add(this.tabs,new Argument.Number(1))),
									new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Argument.Number(1)))))));

	}

}