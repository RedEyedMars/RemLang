package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class DefinedOperator extends ConcreteRule {

	public static final IRule parser = new DefinedOperator();

	public DefinedOperator(){
		super("defined_operator");
	}
	@Override
	public void setup(){
		set(new ChoiceParser());

	}

}