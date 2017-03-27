package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClassElement extends ConcreteRule {

	public static final IRule parser = new ClassElement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public ClassElement(){
		super("class_element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new WithParser((IRule)Rules.constant_declaration,this.tabs),
					new AddTokenToListParser(
						
							new WithParser((IRule)Rules.auxillary_declaration,this.tabs),"auxillary_declaration","method_declarations"),
					new WithParser((IRule)Rules.element_declaration,this.tabs),
					new AddTokenToListParser(
						
							new WithParser((IRule)Rules.generation_declaration,this.tabs),"generation_declaration","method_declarations"),
					new WithParser((IRule)Rules.variable_declaration,this.tabs)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}