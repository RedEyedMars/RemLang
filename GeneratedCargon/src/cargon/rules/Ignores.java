package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Ignores extends ConcreteRule {

	public static final IRule parser = new Ignores();

	public Ignores(){
		super("ignores");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					CargonTokens.IGNORE,
					CargonTokens.COLON,
					Rules.ignores_element));

	}

}