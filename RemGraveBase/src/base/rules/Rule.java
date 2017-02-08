package base.rules;
import base.lists.Braces;
import base.lists.Tokens;
import com.rem.parser.AddTokenParser;
import com.rem.parser.AddTokenToListParser;
import com.rem.parser.ChainParser;
import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.IRule;
import com.rem.parser.MultipleParser;
import com.rem.parser.OptionalParser;
import com.rem.parser.ParseData;
import com.rem.parser.ParseUtil;

public class Rule extends ChoiceParser implements IRule{
	public final static IParser parser = new Rule();

	private Rule(){
		super();
	}

	@Override
	public void setup(){
		add(new ChainParser(
				new AddTokenToListParser(Tokens.NAME,"rulename","rulenames"),
				new OptionalParser(new AddTokenParser(Tokens.SILENCE,"silence")),
				new OptionalParser(Tokens.SPACES),
				Tokens.NEWLINE,
				Tokens.TAB,
				Definition.parser
				));
	}

	@Override
	public String toString(){
		return "Rule";
	}
}
