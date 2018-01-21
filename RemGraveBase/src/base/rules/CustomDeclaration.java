package base.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class CustomDeclaration extends ConcreteRule {

	public static final IRule parser = new CustomDeclaration();

	public CustomDeclaration(){
		super("custom_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					Tokens.CUSTOM,
					Rules.custom_definition));

	}

}