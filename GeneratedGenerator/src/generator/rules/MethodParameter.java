package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MethodParameter extends ConcreteRule {

	public static final IRule parser = new MethodParameter();

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
					new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
					new WithParser((IRule)Rules.generate_call,new Argument.Number(-1)),
					new AddTokenParser(
						Braces.QUOTE,"string"),
				new ChainParser(
					Tokens.ACCESS,
					new AddTokenParser(
						Tokens.NAME_WORD,"getKeyName")),
					Rules.variable_or_token_name,
				new ChainParser(
					new OptionalParser(
							
									new AddTokenParser(
										Tokens.PRIME,"getString")),
					new ListNameElementParser("entry_names")),
					new ListNameElementParser("class_names"),
					new ListNameElementParser("generator_names"),
					new ListNameElementParser("property_names"),
					new ListNameElementParser("entry_class_names"),
					Tokens.NUMBER));

	}

}