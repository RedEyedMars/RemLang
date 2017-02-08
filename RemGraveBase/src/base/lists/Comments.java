package base.lists;

import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.NameParser;
import com.rem.parser.ParseList;
import com.rem.parser.RegexParser;

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
	//public static final IParser parser = new ChoiceParser(comment);	
	public static final IParser name_parser = new NameParser("comments","comment");
	

}
