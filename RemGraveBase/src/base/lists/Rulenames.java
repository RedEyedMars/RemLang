package base.lists;
import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.NameParser;
import com.rem.parser.ParseList;
import com.rem.parser.RegexParser;
public class Rulenames extends ParseList{

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
	public static final IParser rule = new RegexParser("rule","rulenames","rule");
	public static final IParser definition = new RegexParser("definition","rulenames","definition");
	public static final IParser atom = new RegexParser("atom","rulenames","atom");
	public static final IParser list_definition = new RegexParser("list_rule","rulenames","list_rule");
	public static final IParser element = new RegexParser("element","rulenames","element");
	public static final IParser terminal = new RegexParser("terminal","rulenames","terminal");
	
	
	/*public static final IParser parser = new ChoiceParser(
			base,parameters,rule,definition,list_definition,element,terminal);*/
	public static final IParser name_parser = new NameParser(
			"rulenames",
			"base","parameters","rule","definition","list_rule","atom","element","terminal");

}

