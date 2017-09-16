package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TypeAndName extends ConcreteRule {

	public static final IRule parser = new TypeAndName();

	public TypeAndName(){
		super("type_and_name");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					Rules.type_var,
					new ManyParser(
							
								new ChainParser(
									Tokens.DOT,
									Rules.type_var)),
					new AddTokenToListParser(
						Tokens.NAME,"variableName","variable_names")));

	}

}