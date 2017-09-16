package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClassNameDefinition extends ConcreteRule {

	public static final IRule parser = new ClassNameDefinition();

	public ClassNameDefinition(){
		super("class_name_definition");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenParser(
						Tokens.CLASS_TYPE,"typeName"),
					new AddTokenToListParser(
						
					new ChoiceParser(
							Rules.name_var,
							Tokens.NAME),"variableName","class_variable_names")));

	}

}