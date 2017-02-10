package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class Braces extends ParseList {

	@Override
	public String getName() {
		return "braces";
	}
	@Override
	public String getSingular() {
		return "brace";
	}

	public static final IParser BRACE = new BracedParser(
						Definition.parser,"BRACE","braces","(,)");
	public static final IParser SQUARE = new BracedParser(
						new AddTokenParser(
							Tokens.WILD,"regex"),"SQUARE","braces","[,]");
	public static final IParser QUOTE = new BracedParser(
						new AddTokenParser(
							Tokens.WILD,"regex"),"QUOTE","braces","\",\"");

	public static final IParser parser = new ChoiceParser(
				BRACE,SQUARE,QUOTE);

	public static final NameParser name_parser = new NameParser(
				"braces","BRACE","SQUARE","QUOTE");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}