package generator.rules;

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
						Tokens.NAME,"className","generator_names"),
					new MultipleParser(
							
									new WithParser((IRule)Rules.class_element,new Argument.Number(1)))));

	}

}