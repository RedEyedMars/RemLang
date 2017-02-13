package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Atom extends AddTokenParser implements IRule {

	public static final IParser parser = new Atom();
	public Atom(){
		super("atom");
	}
	@Override
	public void setup(){
		set(new ChainParser(
				new OptionalParser(
					Tokens.SPACES),
				Rules.element,
				Rules.parameters));

	}

}