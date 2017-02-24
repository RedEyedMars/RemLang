package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Arithmatic extends ConcreteRule {

	public static final IRule parser = new Arithmatic();
	private Parameter<Integer> tabCount = new Parameter<Integer>(0);
	private Parameter<Integer> tabLength = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabCount,tabLength};
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
					new WithParser((IRule)Rules.arithmatic,new Parameter<Integer>(1),new Argument.Add(new Parameter<Integer>(2),new Parameter<Integer>(3)))),
				new ChainParser(
					Rules.arithmatic,
					new AddTokenParser(
						Tokens.MULTIPLICATIVE_OPERAND,"operand"),
					Rules.arithmatic),
					new ListNameParser("rule_parameters"),
					Tokens.NUMBER));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}