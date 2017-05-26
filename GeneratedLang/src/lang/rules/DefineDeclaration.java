package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class DefineDeclaration extends ConcreteRule {

	public static final IRule parser = new DefineDeclaration();

	public DefineDeclaration(){
		super("define_declaration");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					Tokens.OPERATOR,
					new AddTokenParser(
						Rules.define_operator,"parameters"),
					new AddTokenParser(
						Braces.METHOD_BODY,"body")),
				new ChainParser(
					Tokens.DEFINE,
					new AddTokenParser(
						Rules.define_braced_constructor,"parameters"),
					new AddTokenParser(
						Braces.METHOD_BODY,"body")),
				new ChainParser(
					Tokens.DEFINE,
					new AddTokenParser(
						Rules.define_constructor,"parameters"),
					new AddTokenParser(
						Braces.METHOD_BODY,"body"))));

	}

}