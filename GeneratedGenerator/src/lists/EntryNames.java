package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class EntryNames extends ParseList {

	@Override
	public String getName() {
		return "entry_names";
	}
	@Override
	public String getSingular() {
		return "entry_name";
	}

	public static final RegexParser self = new RegexParser("self","entry_names","self");

	public static final ChoiceParser parser = new ChoiceParser(
				self);

	public static final NameParser name_parser = new NameParser(
				"entry_names");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}