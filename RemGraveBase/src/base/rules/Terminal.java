package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Terminal extends ConcreteRule {

	public static final IRule parser = new Terminal();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public Terminal(){
		super("terminal");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					new AddTokenParser(
						
							Tokens.ANYLIST,"anyListNameToken"),
					new AddTokenParser(
						
							Listnames.parser,"listsToken"),
					new AddTokenParser(
						
							AnyListNameParser.parser,"listToken")));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}