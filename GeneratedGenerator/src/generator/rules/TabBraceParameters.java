package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TabBraceParameters extends ConcreteRule {

	public static final IRule parser = new TabBraceParameters();

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
							new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
							new ListNameElementParser("entry_names"))));

	}

}