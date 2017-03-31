package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Ingests extends ParseList {

	@Override
	public String getName() {
		return "ingests";
	}
	@Override
	public String getSingular() {
		return "ingest";
	}

	public static final ImportParser IMPORT = new ImportParser(
							Braces.QUOTE,"IMPORT","ingests","import<<entry>>\\n");

	public static final ChoiceParser parser = new ChoiceParser(
				IMPORT);
}