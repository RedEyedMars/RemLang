package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class Rulenames extends ParseList {

	@Override
	public String getName() {
		return "rulenames";
	}
	@Override
	public String getSingular() {
		return "rulename";
	}

	public static final IParser base = new RegexParser("base","rulenames","base");
	public static final IParser parameters = new RegexParser("parameters","rulenames","parameters");
	public static final IParser arithmatic = new RegexParser("arithmatic","rulenames","arithmatic");
	public static final IParser rule = new RegexParser("rule","rulenames","rule");
	public static final IParser list_rule = new RegexParser("list_rule","rulenames","list_rule");
	public static final IParser definition = new RegexParser("definition","rulenames","definition");
	public static final IParser atom = new RegexParser("atom","rulenames","atom");
	public static final IParser element = new RegexParser("element","rulenames","element");
	public static final IParser terminal = new RegexParser("terminal","rulenames","terminal");

	public static final ChoiceParser parser = new ChoiceParser(
				base,parameters,arithmatic,rule,list_rule,definition,atom,element,terminal);

	public static final NameParser name_parser = new NameParser(
				"rulenames");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}