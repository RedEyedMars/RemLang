package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClassElement extends ConcreteRule {

	public static final IRule parser = new ClassElement();
	public ClassElement(){
		super("class_element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new WithParser((IRule)Rules.constant_declaration,new Parameter<Integer>(0)),
					new AddTokenToListParser(
						
							new WithParser((IRule)Rules.auxillary_declaration,new Parameter<Integer>(0)),"auxillary_declaration","method_declarations"),
					new WithParser((IRule)Rules.element_declaration,new Parameter<Integer>(0)),
					new AddTokenToListParser(
						
							new WithParser((IRule)Rules.generation_declaration,new Parameter<Integer>(0)),"generation_declaration","method_declarations"),
					new WithParser((IRule)Rules.variable_declaration,new Parameter<Integer>(0))));

	}

}