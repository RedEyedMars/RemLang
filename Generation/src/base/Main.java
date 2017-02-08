package base;

import java.io.File;

import base.lists.Braces;
import base.lists.Comments;
import base.lists.ListRules;
import base.lists.Listnames;
import base.lists.Rulenames;
import base.lists.Rules;
import base.lists.Tokens;
import base.rules.Atom;
import base.rules.Base;
import base.rules.Definition;
import base.rules.Element;
import base.rules.ListRule;
import base.rules.Parameters;
import base.rules.Rule;
import base.rules.Terminal;
import com.rem.parser.ParseUtil;

public class Main {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		ParseUtil.setupRules(
				Base.class,
				Parameters.class,
				Rule.class,
				ListRule.class,
				Definition.class,
				Atom.class,
				Element.class,
				Terminal.class);
		ParseUtil.parse(
				Base.parser, new File("base.ruleset"), new BaseGenerator(),
					new Listnames(),
					new Tokens(), new Braces(), new Rulenames(), new ListRules(), new Rules(), new Comments());
	}

}
