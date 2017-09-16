package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Imports extends ConcreteRule {

	public static final IRule parser = new Imports();

	public Imports(){
		super("imports");
	}
	@Override
	public void setup(){
		set(
					Simports.IMPORT_CLASS);

	}

}