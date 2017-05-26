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

	public static final RegexParser NAME = new RegexParser("NAME","tokens","[a-zA-Z][a-zA-Z0-9]*");
	public static final ExactParser CLASS = new ExactParser("CLASS","tokens","class ");
	public static final ExactParser EQUALSIGN = new ExactParser("EQUALSIGN","tokens","=");
	public static final ExactParser COMMA = new ExactParser("COMMA","tokens",",");
	public static final ExactParser THIS = new ExactParser("THIS","tokens","this ");
	public static final ExactParser FOR_ARROW = new ExactParser("FOR_ARROW","tokens","->");
	public static final ExactParser NEW = new ExactParser("NEW","tokens","new ");
	public static final ExactParser BACKSLASH = new ExactParser("BACKSLASH","tokens","\\");
	public static final RegexParser NEWLINE = new RegexParser("NEWLINE","tokens","\\s+");
	public static final RegexParser NON_SPACE = new RegexParser("NON_SPACE","tokens","[^\\s]+");
	public static final ExactParser DEFINE = new ExactParser("DEFINE","tokens","define ");
	public static final ExactParser OPERATOR = new ExactParser("OPERATOR","tokens","operator ");
	public static final ExactParser RETURN = new ExactParser("RETURN","tokens","return ");
	public static final RegexParser WILD = new RegexParser("WILD","tokens",".*");

	public static final ChoiceParser parser = new ChoiceParser(
				NAME,CLASS,EQUALSIGN,COMMA,THIS,FOR_ARROW,NEW,BACKSLASH,NEWLINE,NON_SPACE,DEFINE,OPERATOR,RETURN,WILD);
}