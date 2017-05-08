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

	public static final RegexParser NEWLINE = new RegexParser("NEWLINE","tokens","\\n");
	public static final ExactParser NAME_WORD = new ExactParser("NAME_WORD","tokens","name");
	public static final RegexParser NAME = new RegexParser("NAME","tokens","[a-zA-Z_][a-zA-Z0-9_]*");
	public static final RegexParser WILD = new RegexParser("WILD","tokens",".*");
	public static final ExactParser ASTRO_GENERATE = new ExactParser("ASTRO_GENERATE","tokens","generate ");
	public static final ExactParser EQUALSIGN = new ExactParser("EQUALSIGN","tokens","=");
	public static final ExactParser PLUS = new ExactParser("PLUS","tokens","+");
	public static final ExactParser STAR = new ExactParser("STAR","tokens","*");
	public static final ExactParser USES = new ExactParser("USES","tokens","uses ");
	public static final ExactParser ELEMENTS = new ExactParser("ELEMENTS","tokens","elements");
	public static final ExactParser GENERATE = new ExactParser("GENERATE","tokens","generate");
	public static final ExactParser TAKES = new ExactParser("TAKES","tokens","takes");
	public static final ExactParser VARIABLE = new ExactParser("VARIABLE","tokens","var ");
	public static final ExactParser SET = new ExactParser("SET","tokens","set ");
	public static final ExactParser ENTRY = new ExactParser("ENTRY","tokens","entry ");
	public static final ExactParser RETURN = new ExactParser("RETURN","tokens","return");
	public static final ExactParser BACK_SLASH = new ExactParser("BACK_SLASH","tokens","\\");
	public static final ExactParser COLON = new ExactParser("COLON","tokens",":");
	public static final ExactParser COMMA = new ExactParser("COMMA","tokens",",");
	public static final ExactParser PRIME = new ExactParser("PRIME","tokens","\'");
	public static final ExactParser TO = new ExactParser("TO","tokens","to");
	public static final ExactParser NEW = new ExactParser("NEW","tokens","new ");
	public static final ExactParser CONTAINS = new ExactParser("CONTAINS","tokens","contains ");
	public static final ExactParser BACKSLASH = new ExactParser("BACKSLASH","tokens","\\");
	public static final RegexParser ADDITIVE_OPERAND = new RegexParser("ADDITIVE_OPERAND","tokens","[+-]");
	public static final RegexParser MULTIPLICATIVE_OPERAND = new RegexParser("MULTIPLICATIVE_OPERAND","tokens","[*/]");
	public static final RegexParser NUMBER = new RegexParser("NUMBER","tokens","(-)?\\d+");
	public static final ExactParser NULL = new ExactParser("NULL","tokens","null");
	public static final ExactParser IF = new ExactParser("IF","tokens","if ");
	public static final ExactParser ELSE = new ExactParser("ELSE","tokens","else");
	public static final ExactParser AND = new ExactParser("AND","tokens","and ");
	public static final ExactParser OR = new ExactParser("OR","tokens","or ");
	public static final ExactParser NOT = new ExactParser("NOT","tokens","not ");
	public static final ExactParser IS = new ExactParser("IS","tokens","is ");
	public static final ExactParser STATIC = new ExactParser("STATIC","tokens","static");
	public static final RegexParser ORDINAL_OPERATOR = new RegexParser("ORDINAL_OPERATOR","tokens","[<>](=)?");
	public static final ExactParser EMPTY = new ExactParser("EMPTY","tokens","empty");
	public static final ExactParser SINGULAR = new ExactParser("SINGULAR","tokens","singular");
	public static final ExactParser SINGLE = new ExactParser("SINGLE","tokens","single");
	public static final ExactParser DOES = new ExactParser("DOES","tokens","does ");
	public static final RegexParser ACCESS = new RegexParser("ACCESS","tokens","[-][>]");
	public static final RegexParser NON_SPACE = new RegexParser("NON_SPACE","tokens","[^\\s]+");
	public static final ExactParser FLIP = new ExactParser("FLIP","tokens","flip ");
	public static final ExactParser ERROR = new ExactParser("ERROR","tokens","error");
	public static final ExactParser TRUE = new ExactParser("TRUE","tokens","true");
	public static final ExactParser FALSE = new ExactParser("FALSE","tokens","false");
	public static final ExactParser AUXILLARY = new ExactParser("AUXILLARY","tokens","aux ");
	public static final ExactParser CAST = new ExactParser("CAST","tokens","cast ");
	public static final ExactParser AS = new ExactParser("AS","tokens","as ");
	public static final ExactParser TOKEN = new ExactParser("TOKEN","tokens","token ");
	public static final ExactParser PROPERTY = new ExactParser("PROPERTY","tokens","property ");
	public static final ExactParser EACH = new ExactParser("EACH","tokens","each ");
	public static final ExactParser IN = new ExactParser("IN","tokens","in ");
	public static final ExactParser CONSTRUCTOR = new ExactParser("CONSTRUCTOR","tokens","constructor");
	public static final ExactParser OUTPUT = new ExactParser("OUTPUT","tokens","output");
	public static final ExactParser THIS = new ExactParser("THIS","tokens","this");
	public static final ExactParser CONSTANT = new ExactParser("CONSTANT","tokens","constant");
	public static final ExactParser OTHERWISE = new ExactParser("OTHERWISE","tokens","otherwise");
	public static final ExactParser PIPE = new ExactParser("PIPE","tokens","|");
	public static final ExactParser META = new ExactParser("META","tokens","meta");
	public static final ExactParser CHECK = new ExactParser("CHECK","tokens","check ");
	public static final RegexParser NON_NEWLINE = new RegexParser("NON_NEWLINE","tokens","[^\\n]+");
	public static final ExactParser DOTDOT = new ExactParser("DOTDOT","tokens","..");

	public static final ChoiceParser parser = new ChoiceParser(
				NEWLINE,NAME_WORD,NAME,WILD,ASTRO_GENERATE,EQUALSIGN,PLUS,STAR,USES,ELEMENTS,GENERATE,TAKES,VARIABLE,SET,ENTRY,RETURN,BACK_SLASH,COLON,COMMA,PRIME,TO,NEW,CONTAINS,BACKSLASH,ADDITIVE_OPERAND,MULTIPLICATIVE_OPERAND,NUMBER,NULL,IF,ELSE,AND,OR,NOT,IS,STATIC,ORDINAL_OPERATOR,EMPTY,SINGULAR,SINGLE,DOES,ACCESS,NON_SPACE,FLIP,ERROR,TRUE,FALSE,AUXILLARY,CAST,AS,TOKEN,PROPERTY,EACH,IN,CONSTRUCTOR,OUTPUT,THIS,CONSTANT,OTHERWISE,PIPE,META,CHECK,NON_NEWLINE,DOTDOT);
}