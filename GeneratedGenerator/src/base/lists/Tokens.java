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

	public static final RegexParser SPACES = new RegexParser("SPACES","tokens","[\\t ]+");
	public static final RegexParser NEWLINE = new RegexParser("NEWLINE","tokens","\\n");
	public static final RegexParser NAME_WORD = new RegexParser("NAME_WORD","tokens","name");
	public static final RegexParser NAME = new RegexParser("NAME","tokens","[a-zA-Z_][a-zA-Z0-9_-]*");
	public static final RegexParser WILD = new RegexParser("WILD","tokens",".*");
	public static final RegexParser EQUALSIGN = new RegexParser("EQUALSIGN","tokens","[=]");
	public static final RegexParser PLUS = new RegexParser("PLUS","tokens","[+]");
	public static final RegexParser STAR = new RegexParser("STAR","tokens","[*]");
	public static final RegexParser USES = new RegexParser("USES","tokens","uses");
	public static final RegexParser ELEMENTS = new RegexParser("ELEMENTS","tokens","elements");
	public static final RegexParser GENERATE = new RegexParser("GENERATE","tokens","generate");
	public static final RegexParser TAKES = new RegexParser("TAKES","tokens","takes");
	public static final RegexParser VARIABLE = new RegexParser("VARIABLE","tokens","var");
	public static final RegexParser ENTRY = new RegexParser("ENTRY","tokens","entry");
	public static final RegexParser RETURN = new RegexParser("RETURN","tokens","return");
	public static final RegexParser BACK_SLASH = new RegexParser("BACK_SLASH","tokens","\\\\");
	public static final RegexParser COLON = new RegexParser("COLON","tokens","[:]");
	public static final RegexParser COMMA = new RegexParser("COMMA","tokens","[,]");
	public static final RegexParser PRIME = new RegexParser("PRIME","tokens","\'");
	public static final RegexParser TO = new RegexParser("TO","tokens","to");
	public static final RegexParser NEW = new RegexParser("NEW","tokens","new");
	public static final RegexParser ADDITIVE_OPERAND = new RegexParser("ADDITIVE_OPERAND","tokens","[+-]");
	public static final RegexParser MULTIPLICATIVE_OPERAND = new RegexParser("MULTIPLICATIVE_OPERAND","tokens","[*/]");
	public static final RegexParser NUMBER = new RegexParser("NUMBER","tokens","(-)?\\d+");
	public static final RegexParser NULL = new RegexParser("NULL","tokens","null");
	public static final RegexParser IF = new RegexParser("IF","tokens","if");
	public static final RegexParser ELSE = new RegexParser("ELSE","tokens","else");
	public static final RegexParser AND = new RegexParser("AND","tokens","and");
	public static final RegexParser OR = new RegexParser("OR","tokens","or");
	public static final RegexParser NOT = new RegexParser("NOT","tokens","not");
	public static final RegexParser IS = new RegexParser("IS","tokens","is");
	public static final RegexParser ORDINAL_OPERATOR = new RegexParser("ORDINAL_OPERATOR","tokens","[<>](=)?");
	public static final RegexParser EMPTY = new RegexParser("EMPTY","tokens","empty");
	public static final RegexParser SINGULAR = new RegexParser("SINGULAR","tokens","singular");
	public static final RegexParser ACCESS = new RegexParser("ACCESS","tokens","[-][>]");
	public static final RegexParser NON_SPACE = new RegexParser("NON_SPACE","tokens","[^\\s]+");
	public static final RegexParser FLIP = new RegexParser("FLIP","tokens","flip");
	public static final RegexParser ERROR = new RegexParser("ERROR","tokens","error");
	public static final RegexParser TRUE = new RegexParser("TRUE","tokens","true");
	public static final RegexParser FALSE = new RegexParser("FALSE","tokens","false");
	public static final RegexParser AUXILLARY = new RegexParser("AUXILLARY","tokens","aux");
	public static final RegexParser CAST = new RegexParser("CAST","tokens","cast");
	public static final RegexParser AS = new RegexParser("AS","tokens","as");
	public static final RegexParser TOKEN = new RegexParser("TOKEN","tokens","token");

	public static final ChoiceParser parser = new ChoiceParser(
				SPACES,NEWLINE,NAME_WORD,NAME,WILD,EQUALSIGN,PLUS,STAR,USES,ELEMENTS,GENERATE,TAKES,VARIABLE,ENTRY,RETURN,BACK_SLASH,COLON,COMMA,PRIME,TO,NEW,ADDITIVE_OPERAND,MULTIPLICATIVE_OPERAND,NUMBER,NULL,IF,ELSE,AND,OR,NOT,IS,ORDINAL_OPERATOR,EMPTY,SINGULAR,ACCESS,NON_SPACE,FLIP,ERROR,TRUE,FALSE,AUXILLARY,CAST,AS,TOKEN);

	public static final NameParser name_parser = new NameParser(
				"tokens");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}