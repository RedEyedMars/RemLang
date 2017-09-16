package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class AllTypeName extends ConcreteRule {

	public static final IRule parser = new AllTypeName();

	public AllTypeName(){
		super("all_type_name");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					Tokens.CLASS_TYPE,
					Tokens.METHOD_TYPE,
					Tokens.VARIABLE_TYPE,
					Tokens.BODY_TYPE,
					Tokens.STATEMENT_TYPE,
					Tokens.PARAMETERS_TYPE,
					Tokens.CONTEXT_TYPE,
				new ChainParser(
					Rules.type_var,
					new ManyParser(
							
								new ChainParser(
									Tokens.DOT,
									Rules.type_var)))));

	}

}