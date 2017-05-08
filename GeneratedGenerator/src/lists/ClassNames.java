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
	public static final RegexParser Boolean = new RegexParser("Boolean","class_names","Boolean");
	public static final RegexParser Integer = new RegexParser("Integer","class_names","Integer");
	public static final RegexParser ParseList = new RegexParser("ParseList","class_names","ParseList");
	public static final RegexParser ParseContext = new RegexParser("ParseContext","class_names","ParseContext");
	public static final RegexParser ParseUtil = new RegexParser("ParseUtil","class_names","ParseUtil");
	public static final RegexParser IParser = new RegexParser("IParser","class_names","IParser");
	public static final RegexParser IToken = new RegexParser("IToken","class_names","IToken");
	public static final RegexParser Listnames = new RegexParser("Listnames","class_names","Listnames");
	public static final RegexParser List = new RegexParser("List","class_names","List");
	public static final RegexParser ArrayList = new RegexParser("ArrayList","class_names","ArrayList");
	public static final RegexParser Map = new RegexParser("Map","class_names","Map");
	public static final RegexParser HashMap = new RegexParser("HashMap","class_names","HashMap");
	public static final RegexParser Set = new RegexParser("Set","class_names","Set");
	public static final RegexParser HashSet = new RegexParser("HashSet","class_names","HashSet");
	public static final RegexParser TreeSet = new RegexParser("TreeSet","class_names","TreeSet");
	public static final RegexParser File = new RegexParser("File","class_names","File");
	public static final RegexParser Entry = new RegexParser("Entry","class_names","Entry");
	public static final RegexParser ListEntry = new RegexParser("ListEntry","class_names","ListEntry");

	public static final ChoiceParser parser = new ChoiceParser(
				String,StringBuilder,Boolean,Integer,ParseList,ParseContext,ParseUtil,IParser,IToken,Listnames,List,ArrayList,Map,HashMap,Set,HashSet,TreeSet,File,Entry,ListEntry);
}