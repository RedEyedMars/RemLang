package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MethodDeclaration extends ConcreteRule {

	public static final IRule parser = new MethodDeclaration();

	public MethodDeclaration(){
		super("method_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new AddTokenToListParser(
						Tokens.NAME,"methodName","method_names"),
					new AddTokenParser(
						Braces.PARAMETERS,"parameters"),
					new AddTokenParser(
						Braces.METHOD_BODY,"body")));

	}

}