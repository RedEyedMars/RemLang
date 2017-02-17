package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class ListRules extends ParseList {

	@Override
	public String getName() {
		return "list_rules";
	}
	@Override
	public String getSingular() {
		return "list_rule";
	}

	public static final NameParser name_parser = new NameParser("list_rules");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}