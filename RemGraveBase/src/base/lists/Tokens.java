package base.lists;

import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.NameParser;
import com.rem.parser.ParseData;
import com.rem.parser.ParseList;
import com.rem.parser.RegexParser;

public class Tokens extends ParseList {

	
	@Override
	public String getName() {
		return "tokens";
	}
	@Override
	public String getSingular() {
		return "token";
	}

	public static final RegexParser PIPE = new RegexParser("PIPE","tokens","[|]");
	public static final RegexParser PLUS = new RegexParser("PLUS","tokens","[+]");
	public static final RegexParser OPTIONAL = new RegexParser("OPTIONAL","tokens","[?]");
	public static final RegexParser MANY = new RegexParser("MANY","tokens","[*]");
	public static final RegexParser AS = new RegexParser("AS","tokens","as");
	public static final RegexParser IN = new RegexParser("IN","tokens","in");
	public static final RegexParser WITH = new RegexParser("WITH","tokens","with");
	public static final RegexParser ARE = new RegexParser("ARE","tokens","are");
	public static final IParser SILENCE = new RegexParser("SILENCE","tokens","[ \\t]+is[ \\t]+silent");
	public static final RegexParser TAB = new RegexParser("TAB","tokens","\\t");
	public static final RegexParser NEWLINE = new RegexParser("NEWLINE","tokens","\\n");
	public static final RegexParser SPACES = new RegexParser("SPACES","tokens","[\\t ]+");
	public static final RegexParser NAME = new RegexParser("NAME","tokens","[a-zA-Z_-][a-zA-Z0-9_-]*");
	public static final RegexParser WILD = new RegexParser("WILD","tokens",".*");
	
	/*public static final IParser parser = new ChoiceParser(
			PIPE,PLUS,OPTIONAL,MANY,AS,IN,WITH,ARE,TAB,NEWLINE,SPACES,NAME,WILD);*/
	public static final IParser name_parser = new NameParser(
			"tokens",
			"PIPE","PLUS","OPTIONAL","MANY","AS","IN","WITH","ARE","SILENCE","TAB","NEWLINE","SPACES","NAME","WILD");

}
