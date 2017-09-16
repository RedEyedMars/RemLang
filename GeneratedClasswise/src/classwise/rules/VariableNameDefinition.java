package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class VariableNameDefinition extends ConcreteRule {

	public static final IRule parser = new VariableNameDefinition();

	public VariableNameDefinition(){
		super("variable_name_definition");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenParser(
						Rules.all_type_name,"typeName"),
					new OptionalParser(
							Tokens.INLINE_LIST),
					new AddTokenToListParser(
						
					new ChoiceParser(
							Rules.name_var,
							Tokens.NAME),"variableName","variable_names")));

	}

}