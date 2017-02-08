package base.lists;
import com.rem.parser.IParser;
import com.rem.parser.NameParser;
import com.rem.parser.ParseList;
public class ListRules extends ParseList {
	

	@Override
	public String getName() {
		return "list_rules";
	}

	@Override
	public String getSingular() {
		return "list_rule";
	}

	//public static final IParser parser = new NameParser("list_rules");

}

