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

	public static final RegexParser _String = new RegexParser("_String","class_names","String");
	public static final RegexParser _void = new RegexParser("_void","class_names","void");
	public static final RegexParser _Boolean = new RegexParser("_Boolean","class_names","Boolean");
	public static final RegexParser _boolean = new RegexParser("_boolean","class_names","boolean");
	public static final RegexParser _Byte = new RegexParser("_Byte","class_names","Byte");
	public static final RegexParser _byte = new RegexParser("_byte","class_names","byte");
	public static final RegexParser _Character = new RegexParser("_Character","class_names","Character");
	public static final RegexParser _char = new RegexParser("_char","class_names","char");
	public static final RegexParser _Integer = new RegexParser("_Integer","class_names","Integer");
	public static final RegexParser _int = new RegexParser("_int","class_names","int");
	public static final RegexParser _Double = new RegexParser("_Double","class_names","Double");
	public static final RegexParser _double = new RegexParser("_double","class_names","double");
	public static final RegexParser _Float = new RegexParser("_Float","class_names","Float");
	public static final RegexParser _float = new RegexParser("_float","class_names","float");
	public static final RegexParser _Long = new RegexParser("_Long","class_names","Long");
	public static final RegexParser _long = new RegexParser("_long","class_names","long");
	public static final RegexParser _Short = new RegexParser("_Short","class_names","Short");
	public static final RegexParser _short = new RegexParser("_short","class_names","short");
	public static final RegexParser _System = new RegexParser("_System","class_names","System");
	public static final RegexParser _Object = new RegexParser("_Object","class_names","Object");

	public static final ChoiceParser parser = new ChoiceParser(
				_String,_void,_Boolean,_boolean,_Byte,_byte,_Character,_char,_Integer,_int,_Double,_double,_Float,_float,_Long,_long,_Short,_short,_System,_Object);
}