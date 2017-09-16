package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class VariableAssignment extends ConcreteRule {

	public static final IRule parser = new VariableAssignment();

	public VariableAssignment(){
		super("variable_assignment");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					Rules.name_var,
					Tokens.EQUALS,
					Rules.method_argument));

	}

}