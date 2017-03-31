package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class UsedClassesList extends ConcreteRule {

	public static final IRule parser = new UsedClassesList();
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

}