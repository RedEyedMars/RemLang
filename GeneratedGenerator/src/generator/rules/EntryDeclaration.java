package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class EntryDeclaration extends ConcreteRule {

	public static final IRule parser = new EntryDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public EntryDeclaration(){
		super("entry_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.ENTRY,
					new AddTokenToListParser(
						Tokens.NAME,"entryName","entry_names"),
					new OptionalParser(
							
									new AddTokenParser(
										Braces.QUOTE,"delimiter")),
					Tokens.EQUALSIGN,
					
					new ChoiceParser(
							new WithParser((IRule)Rules.entry_definition,new Argument.Add(this.tabs,new Argument.Number(1))),
							Tokens.NULL)));

	}

}