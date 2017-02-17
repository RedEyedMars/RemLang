package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Arithmatic extends AddTokenParser implements IRule {

	public static final IParser parser = new Arithmatic();
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
							new AddTokenParser(Tokens.ADDITIVE_OPERAND,"operand"),
							Rules.arithmatic),
					new ChainParser(
							Rules.arithmatic,
							new AddTokenParser(
							Tokens.MULTIPLICATIVE_OPERAND,"operand"),
							Rules.arithmatic),
					new ListNameParser("rule_parameters"),
					Tokens.NUMBER));

	}
	@Override
	public Parameter getParameter(int i) {
		switch(i){
		case 0: return tabCount;
		default: return null;
		}
	}

}