package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class VariableNames extends ParseList {

	@Override
	public String getName() {
		return "variable_names";
	}
	@Override
	public String getSingular() {
		return "variable_name";
	}

	public static final RegexParser this_ = new RegexParser("this_","variable_names","this ");

	public static final ChoiceParser parser = new ChoiceParser(
				this_);
}