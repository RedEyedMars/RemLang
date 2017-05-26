package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

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
	public static final ExactParser FROM = new ExactParser("FROM","tokens","from");
	public static final ExactParser DOT = new ExactParser("DOT","tokens",".");
	public static final RegexParser ADDITIVE_OPERAND = new RegexParser("ADDITIVE_OPERAND","tokens","[+-]");
	public static final RegexParser MULTIPLICATIVE_OPERAND = new RegexParser("MULTIPLICATIVE_OPERAND","tokens","[*/]");
	public static final ExactParser HAS = new ExactParser("HAS","tokens","has");
	public static final RegexParser SILENCE = new RegexParser("SILENCE","tokens","is[ \\t]+silent");
	public static final RegexParser CHOOSY = new RegexParser("CHOOSY","tokens","is[ \\t]+empty[ \\t]+choice");
	public static final RegexParser ANYLIST = new RegexParser("ANYLIST","tokens","any[\\t ]+list");
	public static final RegexParser TAB = new RegexParser("TAB","tokens","\\t");
	public static final RegexParser TABS = new RegexParser("TABS","tokens","\\t\\t");
	public static final RegexParser NEWLINE = new RegexParser("NEWLINE","tokens","\\n");
	public static final RegexParser NAME = new RegexParser("NAME","tokens","[a-zA-Z_][a-zA-Z0-9_]*");
	public static final RegexParser LISTNAME = new RegexParser("LISTNAME","tokens","[a-zA-Z_][a-zA-Z0-9_]*([ \\t]*[-][ \\t]*([a-zA-Z_][a-zA-Z0-9_]*))?");
	public static final RegexParser SPACES = new RegexParser("SPACES","tokens","[\\t ]+");
	public static final RegexParser WILD = new RegexParser("WILD","tokens",".*");
	public static final RegexParser NUMBER = new RegexParser("NUMBER","tokens","[-]?\\d+");
	public static final ExactParser COLON = new ExactParser("COLON","tokens",":");
	public static final ExactParser CUSTOM = new ExactParser("CUSTOM","tokens","custom ");
	public static final ExactParser START = new ExactParser("START","tokens","START");
	public static final ExactParser END = new ExactParser("END","tokens","END");
	public static final ExactParser DEFINE = new ExactParser("DEFINE","tokens","define");
	public static final ExactParser BACK_ARROW = new ExactParser("BACK_ARROW","tokens","<-");
	public static final ExactParser FOR_ARROW = new ExactParser("FOR_ARROW","tokens","->");

	public static final ChoiceParser parser = new ChoiceParser(
				PIPE,PLUS,OPTIONAL,MANY,AS,IN,WITH,ARE,COMMA,FROM,DOT,ADDITIVE_OPERAND,MULTIPLICATIVE_OPERAND,HAS,SILENCE,CHOOSY,ANYLIST,TAB,TABS,NEWLINE,NAME,LISTNAME,SPACES,WILD,NUMBER,COLON,CUSTOM,START,END,DEFINE,BACK_ARROW,FOR_ARROW);
}