package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class CargonTokens extends ParseList {

	@Override
	public String getName() {
		return "cargon_tokens";
	}
	@Override
	public String getSingular() {
		return "cargon_token";
	}

	public static final RegexParser NAME = new RegexParser("NAME","cargon_tokens","[a-zA-Z_][a-zA-Z0-9_-]*");
	public static final RegexParser NEWLINE = new RegexParser("NEWLINE","cargon_tokens","\\n");
	public static final RegexParser WILD = new RegexParser("WILD","cargon_tokens",".*");
	public static final RegexParser ANYCHAR = new RegexParser("ANYCHAR","cargon_tokens",".");
	public static final RegexParser NUMBER = new RegexParser("NUMBER","cargon_tokens","[-]?\\d+");
	public static final RegexParser NONSPACE = new RegexParser("NONSPACE","cargon_tokens","[^\\s]+");
	public static final ExactParser CHAR_QUOTE = new ExactParser("CHAR_QUOTE","cargon_tokens","\"");
	public static final RegexParser quote = new RegexParser("quote","cargon_tokens","[^\"]*");
	public static final ExactParser PIPE = new ExactParser("PIPE","cargon_tokens","|");
	public static final ExactParser PLUS = new ExactParser("PLUS","cargon_tokens","+");
	public static final ExactParser OPTIONAL = new ExactParser("OPTIONAL","cargon_tokens","?");
	public static final ExactParser MANY = new ExactParser("MANY","cargon_tokens","*");
	public static final ExactParser AS = new ExactParser("AS","cargon_tokens","as");
	public static final ExactParser IN = new ExactParser("IN","cargon_tokens","in");
	public static final ExactParser WITH = new ExactParser("WITH","cargon_tokens","with");
	public static final ExactParser ARE = new ExactParser("ARE","cargon_tokens","are");
	public static final ExactParser COMMA = new ExactParser("COMMA","cargon_tokens",",");
	public static final ExactParser FROM = new ExactParser("FROM","cargon_tokens","from");
	public static final ExactParser DOT = new ExactParser("DOT","cargon_tokens",".");
	public static final ExactParser TAB = new ExactParser("TAB","cargon_tokens","\t");
	public static final ExactParser NEWTAB = new ExactParser("NEWTAB","cargon_tokens","\n\t");
	public static final ExactParser COLON = new ExactParser("COLON","cargon_tokens",":");
	public static final ExactParser SEMICOLON = new ExactParser("SEMICOLON","cargon_tokens",";");
	public static final ExactParser SILENT = new ExactParser("SILENT","cargon_tokens","silent");
	public static final ExactParser BRACED = new ExactParser("BRACED","cargon_tokens","Braced");
	public static final ExactParser IMPORTS = new ExactParser("IMPORTS","cargon_tokens","Imports");
	public static final ExactParser ACCESS = new ExactParser("ACCESS","cargon_tokens","->");
	public static final ExactParser LIST = new ExactParser("LIST","cargon_tokens","list");
	public static final ExactParser EQUALSIGN = new ExactParser("EQUALSIGN","cargon_tokens","=");
	public static final ExactParser CARROT = new ExactParser("CARROT","cargon_tokens","^");
	public static final ExactParser DASH = new ExactParser("DASH","cargon_tokens","-");
	public static final ExactParser REGEX_NUMBER = new ExactParser("REGEX_NUMBER","cargon_tokens","\\d");
	public static final ExactParser REGEX_WHITESPACE = new ExactParser("REGEX_WHITESPACE","cargon_tokens","\\s");
	public static final ExactParser REGEX_DOT = new ExactParser("REGEX_DOT","cargon_tokens","\\.");
	public static final ExactParser REGEX_QUOTE = new ExactParser("REGEX_QUOTE","cargon_tokens","\\\"");
	public static final ExactParser REGEX_APOS = new ExactParser("REGEX_APOS","cargon_tokens","\\'");
	public static final ExactParser REGEX_SLASH = new ExactParser("REGEX_SLASH","cargon_tokens","\\\\");
	public static final ExactParser PASS_SYMBOL = new ExactParser("PASS_SYMBOL","cargon_tokens","@");
	public static final ExactParser GLOBAL = new ExactParser("GLOBAL","cargon_tokens","global");
	public static final ExactParser IGNORE = new ExactParser("IGNORE","cargon_tokens","ignore");
	public static final ExactParser IGNORE_PARAM = new ExactParser("IGNORE_PARAM","cargon_tokens","Ignore");
	public static final ExactParser NONE = new ExactParser("NONE","cargon_tokens","None");
	public static final ExactParser BACKSLASH = new ExactParser("BACKSLASH","cargon_tokens","\\");

	public static final ChoiceParser parser = new ChoiceParser(
				NAME,NEWLINE,WILD,ANYCHAR,NUMBER,NONSPACE,CHAR_QUOTE,quote,PIPE,PLUS,OPTIONAL,MANY,AS,IN,WITH,ARE,COMMA,FROM,DOT,TAB,NEWTAB,COLON,SEMICOLON,SILENT,BRACED,IMPORTS,ACCESS,LIST,EQUALSIGN,CARROT,DASH,REGEX_NUMBER,REGEX_WHITESPACE,REGEX_DOT,REGEX_QUOTE,REGEX_APOS,REGEX_SLASH,PASS_SYMBOL,GLOBAL,IGNORE,IGNORE_PARAM,NONE,BACKSLASH);
}