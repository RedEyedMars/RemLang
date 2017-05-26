package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class CUSTOMBRACE extends ConcreteRule {

	public static final IRule parser = new CUSTOMBRACE();

	public CUSTOMBRACE(){
		super("CUSTOM_BRACE");
	}
	@Override
	public void setup(){
		set(
					new ListNameElementParser("custom_variable_names"));

	}

}