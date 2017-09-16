package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Regex extends ConcreteRule {

	public static final IRule parser = new Regex();

	public Regex(){
		super("regex");
	}
	@Override
	public void setup(){
		set(
					new MultipleParser(
							Rules.regex_element));

	}

}