package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class QUOTE extends ConcreteRule {

	public static final IRule parser = new QUOTE();

	public QUOTE(){
		super("QUOTE");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					CargonTokens.CHAR_QUOTE,
					CargonTokens.quote,
					CargonTokens.CHAR_QUOTE));

	}

}