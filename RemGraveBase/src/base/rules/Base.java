package base.rules;

import base.lists.Comments;
import base.lists.Tokens;
import com.rem.parser.AddTokenToListParser;
import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.MultipleParser;
import com.rem.parser.IRule;

public class Base extends ChoiceParser implements IRule {
	public static final IParser parser = new Base();
	
	private Base(){
		super();
	}
	@Override
	public void setup(){
		add(new MultipleParser(
				new ChoiceParser(
						Tokens.SPACES,
						Tokens.NEWLINE,
						Comments.comment,
						new AddTokenToListParser(
								Rule.parser,"rule","rules"),
						new AddTokenToListParser(
								ListRule.parser,"list_rule","list_rules"))));
	}
		
	@Override
	public String toString(){
		return "Base";
	}
	
}
