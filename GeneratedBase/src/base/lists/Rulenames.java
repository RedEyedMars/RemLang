package base.lists;

import com.rem.parser.*;

public class Rulenames extends ParseList {

	@Override
	public String getName() {
		return "rulenames";
	}
	@Override
	public String getSingular() {
		return "rulename";
	}

	public static final RegexParser base = new RegexParser("base","rulenames","base");
	public static final RegexParser parameters = new RegexParser("parameters","rulenames","parameters");
	public static final RegexParser arithmatic = new RegexParser("arithmatic","rulenames","arithmatic");
	public static final RegexParser rule = new RegexParser("rule","rulenames","rule");
	public static final RegexParser member_declaration = new RegexParser("member_declaration","rulenames","member_declaration");
	public static final RegexParser list_rule = new RegexParser("list_rule","rulenames","list_rule");
	public static final RegexParser definition = new RegexParser("definition","rulenames","definition");
	public static final RegexParser atom = new RegexParser("atom","rulenames","atom");
	public static final RegexParser element = new RegexParser("element","rulenames","element");
	public static final RegexParser terminal = new RegexParser("terminal","rulenames","terminal");

	public static final ChoiceParser parser = new ChoiceParser(
				base,parameters,arithmatic,rule,member_declaration,list_rule,definition,atom,element,terminal);

	public static final NameParser name_parser = new NameParser(
				"rulenames");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}