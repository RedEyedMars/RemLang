package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class Terminal extends AddTokenParser implements IRule {

	public static final IParser parser = new Terminal();
	public Terminal(){
		super("terminal");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new AddTokenParser(
					
					Rulenames.name_parser,"ruleToken"),
				new AddTokenParser(
					
					Tokens.name_parser,"token"),
				new AddTokenParser(
					
					Listnames.name_parser,"listToken"),
				new AddTokenParser(
					
					Listnames.parser,"listsToken"),
				new AddTokenParser(
					
					Braces.name_parser,"braceToken")));

	}

}