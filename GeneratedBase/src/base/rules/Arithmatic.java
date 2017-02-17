package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Arithmatic extends AddTokenParser implements IRule {

	public static final IRule parser = new Arithmatic();
	private Parameter<Integer> tabCount = new Parameter<Integer>(0);
	public Arithmatic(){
		super("arithmatic");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					Rules.arithmatic,
					new OptionalParser(
							Tokens.SPACES),
					new AddTokenParser(
						Tokens.ADDITIVE_OPERAND,"operand"),
					new OptionalParser(
							Tokens.SPACES),
					new WithParser((IRule)Rules.arithmatic,new Argument.Add(this.tabCount,new Argument.Multiply(new Parameter<Integer>(1),new Parameter<Integer>(2))))),
				new ChainParser(
					Rules.arithmatic,
					new OptionalParser(
							Tokens.SPACES),
					new AddTokenParser(
						Tokens.MULTIPLICATIVE_OPERAND,"operand"),
					new OptionalParser(
							Tokens.SPACES),
					Rules.arithmatic),
					new ListNameParser("rule_parameters"),
					Tokens.NUMBER));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?> getParameter(int i) {
		switch(i){
		case 0: return tabCount;
		default: return null;
		}
	}

}