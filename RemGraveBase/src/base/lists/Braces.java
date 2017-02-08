package base.lists;

import com.rem.parser.AddTokenParser;
import com.rem.parser.BracedParser;
import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.NameParser;
import com.rem.parser.ParseList;

import base.rules.Definition;

public class Braces extends ParseList{
	
	public String getName(){
		return "braces";
	}
	
	public String getSingular(){
		return "brace";
	}
	
	public static final IParser QUOTE = new BracedParser(new AddTokenParser(Tokens.WILD,"regex"),"QUOTE", "braces", "\",\"");
	
	public static final IParser SQUARE = new BracedParser(new AddTokenParser(Tokens.WILD,"regex"), "SQUARE", "braces" ,"[,]");
	
	public static final IParser BRACE = new BracedParser(Definition.parser, "BRACE", "braces","(,)");
	
	//public static final IParser parser = new ChoiceParser(QUOTE,SQUARE,BRACE);//

	public static final IParser name_parser = new NameParser("braces","QUOTE","SQUARE","BRACE");
}