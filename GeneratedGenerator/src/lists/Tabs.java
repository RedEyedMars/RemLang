package lists;

import com.rem.parser.*;

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

	public static final NameParser name_parser = new NameParser(
				"tabs");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}