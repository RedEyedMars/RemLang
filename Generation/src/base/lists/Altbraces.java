package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class Altbraces extends ParseList {

	@Override
	public String getName() {
		return "altbraces";
	}
	@Override
	public String getSingular() {
		return "altbrace";
	}

	public static final IParser NEWLINED = new CharitableBracedParser(
					new ChoiceParser(
						Rules.arithmatic,
						Rules.definition),"NEWLINED","altbraces",",\n");

	public static final ChoiceParser parser = new ChoiceParser(
				NEWLINED);

	public static final NameParser name_parser = new NameParser(
				"altbraces");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}