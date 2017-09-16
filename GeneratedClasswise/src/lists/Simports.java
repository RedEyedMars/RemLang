package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Simports extends ParseList {

	@Override
	public String getName() {
		return "simports";
	}
	@Override
	public String getSingular() {
		return "simport";
	}

	public static final ImportParser IMPORT_CLASS = new ImportParser(
						new ChainParser(
							Braces.CLASS_FILE,
							Tokens.CLWS),"IMPORT_CLASS","simports","import <<class_file_name,\".clws\">>\n",new NamedParserContainer(
							new ManyParser(
									Rules.base_element),"base_element"));

	public static final ChoiceParser parser = new ChoiceParser(
				IMPORT_CLASS);
}