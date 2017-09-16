package classwise.rules;

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
					new AddTokenToListParser(
						Rules.method_definition,"methodDefinition","method_definitions"));

	}

}