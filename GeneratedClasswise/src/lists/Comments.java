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

	public static final BracedParser COMMENT = new BracedParser(
							new AddTokenParser(
								Tokens.WILD,"comment"),"COMMENT","comments","#,#");

	public static final ChoiceParser parser = new ChoiceParser(
				COMMENT);
}