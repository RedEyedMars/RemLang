package base.rules;

import com.rem.parser.*;
import lists.*;

public class TabBraceParameters extends ConcreteRule {

	public static final IRule parser = new TabBraceParameters();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public TabBraceParameters(){
		super("tab_brace_parameters");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					Rules.arithmatic,
					
					new ChoiceParser(
							Rules.entry_definition,
							new ListNameParser("entry_names"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}