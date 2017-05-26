package base.rules;

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
					Comments.parser,
					new AddTokenToListParser(
						Rules.custom_declaration,"custom_declaration","rules"),
					new AddTokenToListParser(
						Rules.rule,"rule","rules"),
					new AddTokenToListParser(
						Rules.list_rule,"list_rule","list_rules")));

	}

}