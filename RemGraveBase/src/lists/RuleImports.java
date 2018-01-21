package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class RuleImports extends ParseList {

	@Override
	public String getName() {
		return "ruleImports";
	}
	@Override
	public String getSingular() {
		return "ruleImport";
	}

	public static final ImportParser IMPORT_RULE = new ImportParser(
							Altbraces.RULE_FILE,"IMPORT_RULE","ruleImports","import <<file_name,\".ruleset\">>\n",new NamedParserContainer(
							Rules.base,"base"));

	public static final ChoiceParser parser = new ChoiceParser(
				IMPORT_RULE);
}