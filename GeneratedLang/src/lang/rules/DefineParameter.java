package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class DefineParameter extends ConcreteRule {

	public static final IRule parser = new DefineParameter();

	public DefineParameter(){
		super("define_parameter");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					new AddTokenParser(
						
						new ChainParser(
							new AddTokenToListParser(
								Tokens.NAME,"syntaxName","variable_names"),
							Tokens.EQUALSIGN,
							Braces.REGEX),"namedSyntax"),
					new AddTokenToListParser(
						Tokens.NAME,"variableName","variable_names"),
					new AddTokenParser(
						
						new ChainParser(
							Tokens.BACKSLASH,
							Tokens.NON_SPACE),"syntax")));

	}

}