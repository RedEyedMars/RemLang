package base.rules;

import com.rem.parser.*;
import base.lists.*;

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
									Rules.used_classes_list,
									new AddTokenToListParser(
										Rules.class_declaration,"class_def","class_definitions"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}