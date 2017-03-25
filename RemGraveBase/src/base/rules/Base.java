package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Base extends ConcreteRule {

	public static final IRule parser = new Base();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Base(){
		super("base");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
					new MultipleParser(
							
							new ChoiceParser(
									Tokens.NEWLINE,
									Comments.parser,
									new AddTokenToListParser(
										Rules.rule,"rule","rules"),
									new AddTokenToListParser(
										Rules.list_rule,"list_rule","list_rules"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}