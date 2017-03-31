package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MetaDeclaration extends ConcreteRule {

	public static final IRule parser = new MetaDeclaration();
	public MetaDeclaration(){
		super("meta_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					Tokens.META,
					new AddTokenParser(
						Tokens.NAME,"metaName"),
					new MultipleParser(
							
							new ChoiceParser(
									new WithParser((IRule)Rules.variable_declaration,new Argument.Number(1)),
									new AddTokenToListParser(
										Rules.meta_method_declaration,null,"method_declarations")))));

	}

}