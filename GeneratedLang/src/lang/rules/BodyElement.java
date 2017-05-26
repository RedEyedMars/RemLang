package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BodyElement extends ConcreteRule {

	public static final IRule parser = new BodyElement();

	public BodyElement(){
		super("body_element");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					Rules.method_call,
					Rules.variable_declaration));

	}

}