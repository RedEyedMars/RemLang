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

	public static final RegexParser uote = new RegexParser("uote","rulenames","uote");
	public static final RegexParser regex_option = new RegexParser("regex_option","rulenames","regex_option");
	public static final RegexParser rule_params = new RegexParser("rule_params","rulenames","rule_params");
	public static final RegexParser braced_parameters = new RegexParser("braced_parameters","rulenames","braced_parameters");
	public static final RegexParser import_parameters = new RegexParser("import_parameters","rulenames","import_parameters");
	public static final RegexParser base = new RegexParser("base","rulenames","base");
	public static final RegexParser rule = new RegexParser("rule","rulenames","rule");
	public static final RegexParser list = new RegexParser("list","rulenames","list");
	public static final RegexParser ignores = new RegexParser("ignores","rulenames","ignores");
	public static final RegexParser ignores_element = new RegexParser("ignores_element","rulenames","ignores_element");
	public static final RegexParser definition = new RegexParser("definition","rulenames","definition");
	public static final RegexParser element = new RegexParser("element","rulenames","element");
	public static final RegexParser atom = new RegexParser("atom","rulenames","atom");
	public static final RegexParser regex = new RegexParser("regex","rulenames","regex");
	public static final RegexParser regex_element = new RegexParser("regex_element","rulenames","regex_element");
	public static final RegexParser regex_special = new RegexParser("regex_special","rulenames","regex_special");

	public static final ChoiceParser parser = new ChoiceParser(
				uote,regex_option,rule_params,braced_parameters,import_parameters,base,rule,list,ignores,ignores_element,definition,element,atom,regex,regex_element,regex_special);
}