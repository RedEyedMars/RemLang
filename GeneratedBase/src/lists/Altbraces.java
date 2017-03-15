package lists;

import com.rem.parser.*;

public class Altbraces extends ParseList {

	@Override
	public String getName() {
		return "altbraces";
	}
	@Override
	public String getSingular() {
		return "altbrace";
	}

	public static final CharitableBracedParser NEWLINED_DEF = new CharitableBracedParser(
							Rules.definition,"NEWLINED_DEF","altbraces",",\n");

	public static final ChoiceParser parser = new ChoiceParser(
				NEWLINED_DEF);

	public static final NameParser name_parser = new NameParser(
				"altbraces");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}