package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Comments extends ParseList {

	@Override
	public String getName() {
		return "comments";
	}
	@Override
	public String getSingular() {
		return "comment";
	}

	public static final RegexParser comment = new RegexParser("comment","comments","#[^\n]*\n");

	public static final ChoiceParser parser = new ChoiceParser(
				comment);

	public static final NameParser name_parser = new NameParser(
				"comments");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}