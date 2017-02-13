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

	public static final IParser base = Base.parser;
	public static final IParser parameters = Parameters.parser;
	public static final IParser arithmatic = Arithmatic.parser;
	public static final IParser rule = Rule.parser;
	public static final IParser list_rule = ListRule.parser;
	public static final IParser definition = Definition.parser;
	public static final IParser atom = Atom.parser;
	public static final IParser element = Element.parser;
	public static final IParser terminal = Terminal.parser;

	public static final ChoiceParser parser = new ChoiceParser(
				base,parameters,arithmatic,rule,list_rule,definition,atom,element,terminal);

	public static final NameParser name_parser = new NameParser(
				"rules");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}