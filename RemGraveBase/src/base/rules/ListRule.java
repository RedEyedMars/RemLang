package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class ListRule extends AddTokenParser implements IRule {

	public static final IParser parser = new ListRule();
	public ListRule(){
		super("list_rule");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(new ChainParser(
				new AddTokenToListParser(
					Tokens.LISTNAME,"listname","listnames"),
				new OptionalParser(
					new ChainParser(
						Tokens.SPACES,
						Tokens.ARE,
						Tokens.SPACES,
						new AddTokenParser(
							Tokens.NAME,"listType"))),
				new OptionalParser(
					Tokens.SPACES),
				Tokens.NEWLINE,
				new ManyParser(
					new ChainParser(
						Tokens.TAB,
						new AddTokenParser(
							new ChainParser(
							
							new ChoiceParser(
								Braces.QUOTE,
								Braces.SQUARE),
							Rules.parameters),"list_def"),Tokens.NEWLINE))));

	}
	@Override
	public Parameter getParameter(int i) {
		switch(i){
		default: return null;
		}
	}

}