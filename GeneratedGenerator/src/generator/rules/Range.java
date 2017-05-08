package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Range extends ConcreteRule {

	public static final IRule parser = new Range();

	public Range(){
		super("range");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new AddTokenParser(
						Rules.arithmatic,"left"),
					Tokens.DOTDOT,
					new AddTokenParser(
						Rules.arithmatic,"right")));

	}

}