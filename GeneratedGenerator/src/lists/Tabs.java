package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Tabs extends ParseList {

	@Override
	public String getName() {
		return "tabs";
	}
	@Override
	public String getSingular() {
		return "tab";
	}

	public static final TabbedParser TAB = new TabbedParser("TAB","tabs","\\t");

	public static final ChoiceParser parser = new ChoiceParser(
				TAB);
}