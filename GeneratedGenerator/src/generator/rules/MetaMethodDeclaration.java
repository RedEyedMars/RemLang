package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MetaMethodDeclaration extends ConcreteRule {

	public static final IRule parser = new MetaMethodDeclaration();

	public MetaMethodDeclaration(){
		super("meta_method_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Argument.Number(1)),
					new AddTokenParser(
						Tokens.NAME,"methodName"),
					new OptionalParser(
							Rules.cast_as_statement),
					new OptionalParser(
							new WithParser((IRule)Rules.takes_statement,new Argument.Number(1))),
					new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.entry_declaration,new Argument.Number(2)),
									new WithParser((IRule)Rules.body_element,new Argument.Number(2))))));

	}

}