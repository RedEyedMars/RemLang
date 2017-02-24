package base.rules;

import com.rem.parser.*;
import base.lists.*;

public class UsedClassesList extends ConcreteRule {

	public static final IRule parser = new UsedClassesList();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public UsedClassesList(){
		super("used_classes_list");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					Tokens.USES,
					new MultipleParser(
							
									new AddTokenToListParser(
										Tokens.NAME,"className","class_names"))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}