package base.rules;

import base.lists.Braces;
import base.lists.Listnames;
import base.lists.Tokens;
import com.rem.parser.AddTokenParser;
import com.rem.parser.AddTokenToListParser;
import com.rem.parser.ChainParser;
import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.IRule;
import com.rem.parser.MultipleParser;
import com.rem.parser.OptionalParser;

public class ListRule extends ChoiceParser implements IRule{
		public final static IParser parser = new ListRule();

		private ListRule(){
			super();
		}
		
		@Override
		public void setup(){
			add(new ChainParser(
					Braces.QUOTE,
					Parameters.parser
					));
			add(new ChainParser(
					Braces.SQUARE,
					Parameters.parser));
			add(new ChainParser(
					new AddTokenParser(Listnames.parser,"list"),
					Tokens.SPACES,
					Tokens.ARE,
					Tokens.SPACES,
					new AddTokenParser(Tokens.NAME,"listType")));
		}

		@Override
		public String toString(){
			return "ListDefinition";
		}
}
