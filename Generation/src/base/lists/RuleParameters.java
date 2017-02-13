package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class RuleParameters extends ParseList {

	@Override
	public String getName() {
		return "rule_parameters";
	}
	@Override
	public String getSingular() {
		return "rule_parameter";
	}

	public static final NameParser name_parser = new NameParser("rule_parameters");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}