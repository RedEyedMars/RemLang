package base.lists;

import com.rem.parser.*;

public class Tokens extends ParseList {

	@Override
	public String getName() {
		return "tokens";
	}
	@Override
	public String getSingular() {
		return "token";
	}

	public static final ExactParser PIPE = new ExactParser("PIPE","tokens","|");
	public static final ExactParser PLUS = new ExactParser("PLUS","tokens","+");
	public static final ExactParser OPTIONAL = new ExactParser("OPTIONAL","tokens","?");
	public static final ExactParser MANY = new ExactParser("MANY","tokens","*");
	public static final ExactParser AS = new ExactParser("AS","tokens","as");
	public static final ExactParser IN = new ExactParser("IN","tokens","in");
	public static final ExactParser WITH = new ExactParser("WITH","tokens","with");
	public static final ExactParser ARE = new ExactParser("ARE","tokens","are");
	public static final ExactParser COMMA = new ExactParser("COMMA","tokens",",");
	public static final RegexParser ADDITIVE_OPERAND = new RegexParser("ADDITIVE_OPERAND","tokens","[+-]");
	public static final RegexParser MULTIPLICATIVE_OPERAND = new RegexParser("MULTIPLICATIVE_OPERAND","tokens","[*/]");
	public static final ExactParser HAS = new ExactParser("HAS","tokens","has");
	public static final RegexParser SILENCE = new RegexParser("SILENCE","tokens","is[ \\t]+silent");
	public static final RegexParser ANYLIST = new RegexParser("ANYLIST","tokens","any[\\t ]+list");
	public static final RegexParser TAB = new RegexParser("TAB","tokens","\\t");
	public static final RegexParser NEWLINE = new RegexParser("NEWLINE","tokens","\\n");
	public static final RegexParser NAME = new RegexParser("NAME","tokens","[a-zA-Z_][a-zA-Z0-9_]*");
	public static final RegexParser LISTNAME = new RegexParser("LISTNAME","tokens","[a-zA-Z_][a-zA-Z0-9_]*([ \\t]*[-][ \\t]*([a-zA-Z_][a-zA-Z0-9_]*))?");
	public static final RegexParser SPACES = new RegexParser("SPACES","tokens","[\\t ]+");
	public static final RegexParser WILD = new RegexParser("WILD","tokens",".*");
	public static final RegexParser NUMBER = new RegexParser("NUMBER","tokens","[-]?\\d+");

	public static final ChoiceParser parser = new ChoiceParser(
				PIPE,PLUS,OPTIONAL,MANY,AS,IN,WITH,ARE,COMMA,ADDITIVE_OPERAND,MULTIPLICATIVE_OPERAND,HAS,SILENCE,ANYLIST,TAB,NEWLINE,NAME,LISTNAME,SPACES,WILD,NUMBER);

	public static final NameParser name_parser = new NameParser(
				"tokens");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}