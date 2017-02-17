package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Element extends AddTokenParser implements IRule {

	public static final IParser parser = new Element();
	public Element(){
		super("element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
				new AddTokenParser(
					new ChainParser(
					Rules.definition,
					new OptionalParser(
						Tokens.SPACES),
					new AddTokenParser(
						
					new ChoiceParser(
						Tokens.OPTIONAL,
						Tokens.MANY,
						Tokens.PLUS),"option")),"multiple"),
				new AddTokenParser(
					Braces.BRACE,"braced"),
				Rules.terminal));

	}
	@Override
	public Parameter getParameter(int i) {
		switch(i){
		default: return null;
		}
	}

}