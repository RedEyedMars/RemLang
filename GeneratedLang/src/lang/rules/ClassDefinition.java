package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClassDefinition extends ConcreteRule {

	public static final IRule parser = new ClassDefinition();

	public ClassDefinition(){
		super("class_definition");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					Rules.define_declaration,
					Rules.method_declaration,
					Rules.variable_declaration));

	}

}