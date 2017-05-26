package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class MethodNames extends ParseList {

	@Override
	public String getName() {
		return "method_names";
	}
	@Override
	public String getSingular() {
		return "method_name";
	}

	public static final RegexParser println = new RegexParser("println","method_names","println");
	public static final RegexParser concat = new RegexParser("concat","method_names","concat");

	public static final ChoiceParser parser = new ChoiceParser(
				println,concat);
}