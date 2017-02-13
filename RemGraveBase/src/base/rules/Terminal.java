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
						new AddTokenParser(	Tokens.ANYLIST,"anyListNameToken"),
						new AddTokenParser(	Listnames.parser,"listsToken"),
						new AddTokenParser(	AnyListNameParser.parser,"listToken")
						
						));
		/*
		set(
			new ChoiceParser(
				new AddTokenParser(

					Rulenames.name_parser,"ruleToken"),
				new AddTokenParser(

					Tokens.name_parser,"token"),
				new AddTokenParser(

					Listnames.parser,"listsToken"),
				new AddTokenParser(

					Listnames.name_parser,"anyListNameToken"),
				new AddTokenParser(

					Braces.name_parser,"braceToken")));*/

	}

}