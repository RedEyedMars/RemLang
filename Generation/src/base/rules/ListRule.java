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
		set(
			new ChoiceParser(new ChainParser(
				
				new ChoiceParser(
					Braces.QUOTE,
					Braces.SQUARE),
				Parameters.parser),new ChainParser(
				new AddTokenParser(
					Listnames.parser,"list"),
				Tokens.SPACES,
				Tokens.ARE,
				Tokens.SPACES,
				new AddTokenParser(
					Tokens.NAME,"listType"))));

	}

}