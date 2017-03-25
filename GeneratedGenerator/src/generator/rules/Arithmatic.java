package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Arithmatic extends ConcreteRule {

	public static final IRule parser = new Arithmatic();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Arithmatic(){
		super("arithmatic");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					Rules.arithmatic,
					new AddTokenParser(
						Tokens.ADDITIVE_OPERAND,"operand"),
					Rules.arithmatic),
				new ChainParser(
					Rules.arithmatic,
					new AddTokenParser(
						Tokens.MULTIPLICATIVE_OPERAND,"operand"),
					Rules.arithmatic),
					new ListNameParser("variable_names"),
					Tokens.NUMBER));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}