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

	public static final RegexParser Boolean = new RegexParser("Boolean","class_names","Boolean");
	public static final RegexParser String = new RegexParser("String","class_names","String");
	public static final RegexParser _void = new RegexParser("_void","class_names","void");
	public static final RegexParser Integer = new RegexParser("Integer","class_names","Integer");
	public static final RegexParser Double = new RegexParser("Double","class_names","Double");
	public static final RegexParser Float = new RegexParser("Float","class_names","Float");
	public static final RegexParser Object = new RegexParser("Object","class_names","Object");

	public static final ChoiceParser parser = new ChoiceParser(
				Boolean,String,_void,Integer,Double,Float,Object);
}