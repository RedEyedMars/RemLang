package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class Element extends ConcreteRule {

	public static final IRule parser = new Element();

	public Element(){
		super("element");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					Rules.atom,
					new OptionalParser(
							
								new ChainParser(
									CargonTokens.AS,
									new AddTokenParser(
										CargonTokens.NAME,"newName"))),
					new OptionalParser(
							
								new ChainParser(
									CargonTokens.IN,
									new AddTokenParser(
										CargonTokens.NAME,"listName")))));

	}

}