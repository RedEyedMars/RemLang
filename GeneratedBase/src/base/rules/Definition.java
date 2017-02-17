package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Definition extends AddTokenParser implements IRule {

	public static final IRule parser = new Definition();
	public Definition(){
		super("definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new AddTokenParser(
						
						new ChainParser(
							Rules.atom,
							new ManyParser(
									Rules.atom)),"chain"),
					new AddTokenParser(
						new OptionalParser(
							
								new ChainParser(
									new OptionalParser(
											Tokens.SPACES),
									
									new ChoiceParser(
											Tokens.PIPE,
										new ChainParser(
											Tokens.NEWLINE,
											Tokens.TAB)),
									Rules.definition)),"choice")));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?> getParameter(int i) {
		switch(i){
		default: return null;
		}
	}

}