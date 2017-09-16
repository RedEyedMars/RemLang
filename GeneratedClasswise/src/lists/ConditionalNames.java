package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class ConditionalNames extends ParseList {

	@Override
	public String getName() {
		return "conditional_names";
	}
	@Override
	public String getSingular() {
		return "conditional_name";
	}

	public static final ExactParser IF = new ExactParser("IF","conditional_names","if");
	public static final ExactParser FOR = new ExactParser("FOR","conditional_names","for");
	public static final ExactParser WHILE = new ExactParser("WHILE","conditional_names","while");

	public static final ChoiceParser parser = new ChoiceParser(
				IF,FOR,WHILE);
}