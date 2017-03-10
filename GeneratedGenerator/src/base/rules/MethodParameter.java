package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class MethodParameter extends ConcreteRule {

	public static final IRule parser = new MethodParameter();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public MethodParameter(){
		super("method_parameter");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					Tokens.NULL,
					Tokens.TRUE,
					Tokens.FALSE,
					Rules.entry_definition,
					new AddTokenParser(
						Braces.QUOTE,"string"),
					Rules.variable_or_token_name,
					new ListNameParser("class_names"),
					new ListNameParser("entry_names"),
					Tokens.NUMBER));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}