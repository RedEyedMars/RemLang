package cargon.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ImportParameters extends ConcreteRule {

	public static final IRule parser = new ImportParameters();

	public ImportParameters(){
		super("import_parameters");
	}
	@Override
	public void setup(){
		set(
					new MultipleParser(
							
							new ChoiceParser(
									Rules.uote,
									new AddTokenParser(
										CargonTokens.NAME,"ruleName"))));

	}

}