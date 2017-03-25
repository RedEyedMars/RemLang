package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class PropertyDeclaration extends ConcreteRule {

	public static final IRule parser = new PropertyDeclaration();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public PropertyDeclaration(){
		super("property_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					Tokens.PROPERTY,
					new AddTokenToListParser(
						Tokens.NAME,"propertyName","property_names"),
					new ManyParser(
							Rules.entry_class_element)));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}