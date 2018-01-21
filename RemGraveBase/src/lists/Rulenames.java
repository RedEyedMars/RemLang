package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

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
	public static final RegexParser base_element = new RegexParser("base_element","rulenames","base_element");
	public static final RegexParser parameters = new RegexParser("parameters","rulenames","parameters");
	public static final RegexParser arithmatic = new RegexParser("arithmatic","rulenames","arithmatic");
	public static final RegexParser rule = new RegexParser("rule","rulenames","rule");
	public static final RegexParser member_declaration = new RegexParser("member_declaration","rulenames","member_declaration");
	public static final RegexParser list_rule = new RegexParser("list_rule","rulenames","list_rule");
	public static final RegexParser custom_declaration = new RegexParser("custom_declaration","rulenames","custom_declaration");
	public static final RegexParser definition = new RegexParser("definition","rulenames","definition");
	public static final RegexParser atom = new RegexParser("atom","rulenames","atom");
	public static final RegexParser element = new RegexParser("element","rulenames","element");
	public static final RegexParser terminal = new RegexParser("terminal","rulenames","terminal");
	public static final RegexParser custom_definition = new RegexParser("custom_definition","rulenames","custom_definition");
	public static final RegexParser custom_element = new RegexParser("custom_element","rulenames","custom_element");
	public static final RegexParser event_definition = new RegexParser("event_definition","rulenames","event_definition");
	public static final RegexParser event_element = new RegexParser("event_element","rulenames","event_element");

	public static final ChoiceParser parser = new ChoiceParser(
				base,base_element,parameters,arithmatic,rule,member_declaration,list_rule,custom_declaration,definition,atom,element,terminal,custom_definition,custom_element,event_definition,event_element);
}