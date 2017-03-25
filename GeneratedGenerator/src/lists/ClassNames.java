package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class ClassNames extends ParseList {

	@Override
	public String getName() {
		return "class_names";
	}
	@Override
	public String getSingular() {
		return "class_name";
	}

	public static final RegexParser String = new RegexParser("String","class_names","String");
	public static final RegexParser StringBuilder = new RegexParser("StringBuilder","class_names","StringBuilder");
	public static final RegexParser Integer = new RegexParser("Integer","class_names","Integer");
	public static final RegexParser ParseList = new RegexParser("ParseList","class_names","ParseList");
	public static final RegexParser ParseContext = new RegexParser("ParseContext","class_names","ParseContext");
	public static final RegexParser ParseUtil = new RegexParser("ParseUtil","class_names","ParseUtil");
	public static final RegexParser IParser = new RegexParser("IParser","class_names","IParser");
	public static final RegexParser IToken = new RegexParser("IToken","class_names","IToken");
	public static final RegexParser Listnames = new RegexParser("Listnames","class_names","Listnames");
	public static final RegexParser List = new RegexParser("List","class_names","List");
	public static final RegexParser Map = new RegexParser("Map","class_names","Map");
	public static final RegexParser File = new RegexParser("File","class_names","File");

	public static final ChoiceParser parser = new ChoiceParser(
				String,StringBuilder,Integer,ParseList,ParseContext,ParseUtil,IParser,IToken,Listnames,List,Map,File);

	public static final NameParser name_parser = new NameParser(
				"class_names");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}