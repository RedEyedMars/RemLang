package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Parameters extends AddTokenParser implements IRule {

	public static final IRule parser = new Parameters();
	public Parameters(){
		super("parameters");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							
								new ChainParser(
									Tokens.SPACES,
									Tokens.AS,
									Tokens.SPACES,
									new AddTokenParser(
										Tokens.NAME,"name"))),
					new OptionalParser(
							
								new ChainParser(
									Tokens.SPACES,
									Tokens.IN,
									Tokens.SPACES,
									new AddTokenParser(
										Listnames.parser,"list"))),
					new OptionalParser(
							
								new ChainParser(
									Tokens.SPACES,
									Tokens.WITH,
									Tokens.SPACES,
									new AddTokenParser(
										
									new ChoiceParser(
											Altbraces.NEWLINED,
											Braces.SEMICOLON),"parameter"))),
					new OptionalParser(
							Tokens.SPACES)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?> getParameter(int i) {
		switch(i){
		default: return null;
		}
	}

}