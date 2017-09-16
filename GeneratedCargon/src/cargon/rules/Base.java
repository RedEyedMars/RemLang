package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Base extends ConcreteRule {

	public static final IRule parser = new Base();

	public Base(){
		super("base");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
					new MultipleParser(
							
							new ChoiceParser(
									CargonTokens.NEWLINE,
									Comments.parser,
									new AddTokenToListParser(
										Rules.list,"list","lists"),
									new AddTokenToListParser(
										Rules.rule,"rule","rules"))));

	}

}