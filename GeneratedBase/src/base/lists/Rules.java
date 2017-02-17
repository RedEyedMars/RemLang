package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class Rules extends ParseList {

	@Override
	public String getName() {
		return "rules";
	}
	@Override
	public String getSingular() {
		return "rule";
	}

	public static final IRule base = Base.parser;
	public static final IRule parameters = Parameters.parser;
	public static final IRule arithmatic = Arithmatic.parser;
	public static final IRule rule = Rule.parser;
	public static final IRule list_rule = ListRule.parser;
	public static final IRule definition = Definition.parser;
	public static final IRule atom = Atom.parser;
	public static final IRule element = Element.parser;
	public static final IRule terminal = Terminal.parser;

	public static final ChoiceParser parser = new ChoiceParser(
				base,parameters,arithmatic,rule,list_rule,definition,atom,element,terminal);

	public static final NameParser name_parser = new NameParser(
				"rules");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}