package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BaseElement extends ConcreteRule {

	public static final IRule parser = new BaseElement();

	public BaseElement(){
		super("base_element");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
			new ChoiceParser(
					Tokens.NEWLINE,
					Comments.COMMENT,
					Rules.imports,
					Rules.anonymous_class,
					Rules.class_declaration,
					Rules.method_declaration,
				new ChainParser(
					Rules.variable_declaration,
					Tokens.SEMICOLON)));

	}

}