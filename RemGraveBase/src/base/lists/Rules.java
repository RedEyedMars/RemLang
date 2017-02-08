package base.lists;

import base.rules.Base;
import base.rules.Definition;
import base.rules.Element;
import base.rules.Parameters;
import base.rules.Rule;
import base.rules.Terminal;

import com.rem.parser.IParser;
import com.rem.parser.NameParser;
import com.rem.parser.ParseList;

public class Rules extends ParseList{


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
	public static final IParser rule = Rule.parser;	
	public static final IParser definition = Definition.parser;
	public static final IParser element = Element.parser;
	public static final IParser terminal = Terminal.parser;
	
	public static final IParser name_parser = new NameParser(
			"rules",
			"base","parameters","rule","definition","element","terminal");
	

}
