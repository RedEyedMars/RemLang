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

	public static final RegexParser NAME = new RegexParser("NAME","tokens","[a-zA-Z_][a-zA-Z0-9_]*");
	public static final RegexParser OPERATOR = new RegexParser("OPERATOR","tokens","[^a-zA-Z0-9_\\s\\n\\(\\)\\{\\}\\[\\]\\;\\\"\\\'\\\\`,]+\\s*");
	public static final RegexParser NUMBER = new RegexParser("NUMBER","tokens","[-]?(\\d*\\.)?\\d+f?(x[0-9ABCDEF]+)?\\s*");
	public static final RegexParser WILD = new RegexParser("WILD","tokens",".*");
	public static final ExactParser NEWLINE = new ExactParser("NEWLINE","tokens","\n");
	public static final ExactParser CLASS = new ExactParser("CLASS","tokens","class ");
	public static final ExactParser INTERFACE = new ExactParser("INTERFACE","tokens","interface ");
	public static final ExactParser METHOD = new ExactParser("METHOD","tokens","method");
	public static final ExactParser VARIABLE = new ExactParser("VARIABLE","tokens","variable");
	public static final ExactParser CLASS_TYPE = new ExactParser("CLASS_TYPE","tokens","Class ");
	public static final ExactParser METHOD_TYPE = new ExactParser("METHOD_TYPE","tokens","Method ");
	public static final ExactParser VARIABLE_TYPE = new ExactParser("VARIABLE_TYPE","tokens","Variable ");
	public static final ExactParser BODY_TYPE = new ExactParser("BODY_TYPE","tokens","Body ");
	public static final ExactParser CONTEXT_TYPE = new ExactParser("CONTEXT_TYPE","tokens","Context ");
	public static final ExactParser STATEMENT_TYPE = new ExactParser("STATEMENT_TYPE","tokens","Statement");
	public static final ExactParser PARAMETERS_TYPE = new ExactParser("PARAMETERS_TYPE","tokens","Parameters");
	public static final ExactParser IN = new ExactParser("IN","tokens",">");
	public static final ExactParser NER = new ExactParser("NER","tokens","inner ");
	public static final ExactParser HID = new ExactParser("HID","tokens","<");
	public static final ExactParser DEN = new ExactParser("DEN","tokens","hidden ");
	public static final ExactParser FROM = new ExactParser("FROM","tokens","from ");
	public static final ExactParser STA = new ExactParser("STA","tokens","@");
	public static final ExactParser TIC = new ExactParser("TIC","tokens","static ");
	public static final ExactParser ACCESS = new ExactParser("ACCESS","tokens","->");
	public static final ExactParser SPECIAL_ACCESS = new ExactParser("SPECIAL_ACCESS","tokens","\\>");
	public static final ExactParser EQUALS = new ExactParser("EQUALS","tokens","=");
	public static final ExactParser COMMA = new ExactParser("COMMA","tokens",",");
	public static final ExactParser DOT = new ExactParser("DOT","tokens",".");
	public static final ExactParser SEMICOLON = new ExactParser("SEMICOLON","tokens",";");
	public static final ExactParser COLON = new ExactParser("COLON","tokens",":");
	public static final ExactParser ADD = new ExactParser("ADD","tokens","+=");
	public static final ExactParser PLUS = new ExactParser("PLUS","tokens","+");
	public static final ExactParser BACKSLASH = new ExactParser("BACKSLASH","tokens","\\");
	public static final ExactParser FORSLASH = new ExactParser("FORSLASH","tokens","/");
	public static final ExactParser ISTYPENAME = new ExactParser("ISTYPENAME","tokens","$");
	public static final ExactParser CAMEL = new ExactParser("CAMEL","tokens","^");
	public static final ExactParser CDS = new ExactParser("CDS","tokens","cds");
	public static final ExactParser CLWS = new ExactParser("CLWS","tokens","clws");
	public static final ExactParser RETURN = new ExactParser("RETURN","tokens","return ");
	public static final ExactParser VOID = new ExactParser("VOID","tokens","void");
	public static final ExactParser THROW = new ExactParser("THROW","tokens","throw ");
	public static final ExactParser NEW = new ExactParser("NEW","tokens","new ");
	public static final ExactParser IF = new ExactParser("IF","tokens","if");
	public static final ExactParser ELSE = new ExactParser("ELSE","tokens","else");
	public static final ExactParser WHILE = new ExactParser("WHILE","tokens","while");
	public static final ExactParser FOR = new ExactParser("FOR","tokens","for");
	public static final ExactParser TRY = new ExactParser("TRY","tokens","try");
	public static final ExactParser CATCH = new ExactParser("CATCH","tokens","catch");
	public static final ExactParser PRINT = new ExactParser("PRINT","tokens","print");
	public static final ExactParser PIPE = new ExactParser("PIPE","tokens","|");
	public static final ExactParser SYNCHRONIZED = new ExactParser("SYNCHRONIZED","tokens","synchronized");
	public static final ExactParser SWITCH = new ExactParser("SWITCH","tokens","switch");
	public static final ExactParser CASE = new ExactParser("CASE","tokens","case");
	public static final ExactParser NULL = new ExactParser("NULL","tokens","null");
	public static final ExactParser TRUE = new ExactParser("TRUE","tokens","true");
	public static final ExactParser FALSE = new ExactParser("FALSE","tokens","false");
	public static final ExactParser WEAK = new ExactParser("WEAK","tokens","~");
	public static final ExactParser AS_METHOD_NAME = new ExactParser("AS_METHOD_NAME","tokens","*");
	public static final ExactParser AS_GENERIC = new ExactParser("AS_GENERIC","tokens","*");
	public static final ExactParser THIS = new ExactParser("THIS","tokens","this");
	public static final ExactParser SUPER = new ExactParser("SUPER","tokens","super");
	public static final ExactParser INLINE_LIST = new ExactParser("INLINE_LIST","tokens","...");
	public static final ExactParser ARRAY_TYPE = new ExactParser("ARRAY_TYPE","tokens","[]");

	public static final ChoiceParser parser = new ChoiceParser(
				NAME,OPERATOR,NUMBER,WILD,NEWLINE,CLASS,INTERFACE,METHOD,VARIABLE,CLASS_TYPE,METHOD_TYPE,VARIABLE_TYPE,BODY_TYPE,CONTEXT_TYPE,STATEMENT_TYPE,PARAMETERS_TYPE,IN,NER,HID,DEN,FROM,STA,TIC,ACCESS,SPECIAL_ACCESS,EQUALS,COMMA,DOT,SEMICOLON,COLON,ADD,PLUS,BACKSLASH,FORSLASH,ISTYPENAME,CAMEL,CDS,CLWS,RETURN,VOID,THROW,NEW,IF,ELSE,WHILE,FOR,TRY,CATCH,PRINT,PIPE,SYNCHRONIZED,SWITCH,CASE,NULL,TRUE,FALSE,WEAK,AS_METHOD_NAME,AS_GENERIC,THIS,SUPER,INLINE_LIST,ARRAY_TYPE);
}