package base.lists;

import com.rem.parser.*;
import base.rules.*;

public class Comments extends ParseList {

	@Override
	public String getName() {
		return "comments";
	}
	@Override
	public String getSingular() {
		return "comment";
	}

	public static final IParser comment = new RegexParser("comment","comments","#[^\n]*\n");

	public static final IParser parser = new ChoiceParser(
				comment);

	public static final NameParser name_parser = new NameParser(
				"comments","comment");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}