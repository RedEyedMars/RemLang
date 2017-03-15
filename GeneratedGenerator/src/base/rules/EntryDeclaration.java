package base.rules;

import com.rem.parser.*;
import lists.*;

public class EntryDeclaration extends ConcreteRule {

	public static final IRule parser = new EntryDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
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
							new WithParser((IRule)Rules.entry_definition,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
							Tokens.NULL)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}