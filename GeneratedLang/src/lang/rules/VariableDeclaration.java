package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class VariableDeclaration extends ConcreteRule {

	public static final IRule parser = new VariableDeclaration();

	public VariableDeclaration(){
		super("variable_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(new PrintParser("??Var?"),
					new AddTokenToListParser(
						Tokens.NAME,"variableName","variable_names"),
					new OptionalParser(
							
								new ChainParser(
									Tokens.EQUALSIGN,new PrintParser("Var?"),
									Rules.method_parameter,new PrintParser("SuccessVar?")))));

	}

}