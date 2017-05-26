package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClassDeclaration extends ConcreteRule {

	public static final IRule parser = new ClassDeclaration();

	public ClassDeclaration(){
		super("class_declaration");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenToListParser(
						Tokens.NAME,"className","class_names"),
					new AddTokenToListParser(
						Braces.CLASS_BODY,"definition","class_definitions")));

	}

}