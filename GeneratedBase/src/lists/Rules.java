package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
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
	public static final IRule member_declaration = MemberDeclaration.parser;
	public static final IRule list_rule = ListRule.parser;
	public static final IRule definition = Definition.parser;
	public static final IRule atom = Atom.parser;
	public static final IRule element = Element.parser;
	public static final IRule terminal = Terminal.parser;

	public static final ChoiceParser parser = new ChoiceParser(
				base,parameters,arithmatic,rule,member_declaration,list_rule,definition,atom,element,terminal);
}