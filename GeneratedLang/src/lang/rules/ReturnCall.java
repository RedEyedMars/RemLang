package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ReturnCall extends ConcreteRule {

	public static final IRule parser = new ReturnCall();

	public ReturnCall(){
		super("return_call");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					Tokens.RETURN,
					Rules.method_parameter));

	}

}