package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class Tokens extends ParseList {

	@Override
	public String getName() {
		return "tokens";
	}
	@Override
	public String getSingular() {
		return "token";
	}

	public static final IParser PIPE = new RegexParser("PIPE","tokens","[|]");

	public static final IParser PLUS = new RegexParser("PLUS","tokens","[+]");

	public static final IParser OPTIONAL = new RegexParser("OPTIONAL","tokens","[?]");

	public static final IParser MANY = new RegexParser("MANY","tokens","[*]");

	public static final IParser AS = new RegexParser("AS","tokens","as");

	public static final IParser IN = new RegexParser("IN","tokens","in");

	public static final IParser WITH = new RegexParser("WITH","tokens","with");

	public static final IParser ARE = new RegexParser("ARE","tokens","are");

	public static final IParser ADDITIVE_OPERAND = new RegexParser("ADDITIVE_OPERAND","tokens","[+-]");

	public static final IParser MULTIPLICATIVE_OPERAND = new RegexParser("MULTIPLICATIVE_OPERAND","tokens","[*/]");

	public static final IParser HAS = new RegexParser("HAS","tokens","[ \\t]+has[ \\t]+");

	public static final IParser SILENCE = new RegexParser("SILENCE","tokens","[ \\t]+is[ \\t]+silent");

	public static final IParser ANYLIST = new RegexParser("ANYLIST","tokens","any[\\t ]+list");

	public static final IParser TAB = new RegexParser("TAB","tokens","\\t");

	public static final IParser NEWLINE = new RegexParser("NEWLINE","tokens","\\n");

	public static final IParser NAME = new RegexParser("NAME","tokens","[a-zA-Z_-][a-zA-Z0-9_-]*");

	public static final IParser LISTNAME = new RegexParser("LISTNAME","tokens","[a-zA-Z_-][a-zA-Z0-9_-]*([ \\t]*\\{[ \\t]*([a-zA-Z_-][a-zA-Z0-9_-]*)[ \\t]*\\})?");

	public static final IParser SPACES = new RegexParser("SPACES","tokens","[\\t ]+");

	public static final IParser WILD = new RegexParser("WILD","tokens",".*");

	public static final IParser NUMBER = new RegexParser("NUMBER","tokens","\\d+");


	public static final ChoiceParser parser = new ChoiceParser(
				PIPE,PLUS,OPTIONAL,MANY,AS,IN,WITH,ARE,ADDITIVE_OPERAND,MULTIPLICATIVE_OPERAND,HAS,SILENCE,ANYLIST,TAB,NEWLINE,NAME,LISTNAME,SPACES,WILD,NUMBER);


	public static final NameParser name_parser = new NameParser(
				"tokens");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}