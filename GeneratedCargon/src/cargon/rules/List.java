package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class List extends ConcreteRule {

	public static final IRule parser = new List();

	public List(){
		super("list");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenToListParser(
						CargonTokens.NAME,"listName","list_names"),
					new OptionalParser(
							CargonTokens.GLOBAL),
					CargonTokens.LIST,
					new OptionalParser(
							
								new ChainParser(
									CargonTokens.WITH,
									new AddTokenParser(
										CargonTokens.NAME,"listRuleName"))),
					new ManyParser(
							
								new ChainParser(
									CargonTokens.NEWTAB,
									Rules.uote))));

	}

}