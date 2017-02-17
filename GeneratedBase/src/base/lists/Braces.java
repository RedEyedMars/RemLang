package base.lists;

import com.rem.parser.*;

public class Braces extends ParseList {

	@Override
	public String getName() {
		return "braces";
	}
	@Override
	public String getSingular() {
		return "brace";
	}

	public static final BracedParser BRACE = new BracedParser(
							Rules.definition,"BRACE","braces","(,)");
	public static final BracedParser SQUARE = new BracedParser(
							new AddTokenParser(
								Tokens.WILD,"regex"),"SQUARE","braces","[,]");
	public static final BracedParser QUOTE = new BracedParser(
							new AddTokenParser(
								Tokens.WILD,"regex"),"QUOTE","braces","\",\"");
	public static final BracedParser SEMICOLON = new BracedParser(
					new ChoiceParser(
							Rules.arithmatic,
							Rules.definition),"SEMICOLON","braces",",;");

	public static final ChoiceParser parser = new ChoiceParser(
				BRACE,SQUARE,QUOTE,SEMICOLON);

	public static final NameParser name_parser = new NameParser(
				"braces");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}