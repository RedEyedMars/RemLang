package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClassElement extends ConcreteRule {

	public static final IRule parser = new ClassElement();

	public ClassElement(){
		super("class_element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					Tokens.NEWLINE,
					Comments.COMMENT,
					Rules.class_declaration,
					Rules.method_declaration,
				new ChainParser(
					Rules.variable_declaration,
					Tokens.SEMICOLON)));

	}

}