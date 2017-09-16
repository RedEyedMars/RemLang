package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Altbraces extends ParseList {

	@Override
	public String getName() {
		return "altbraces";
	}
	@Override
	public String getSingular() {
		return "altbrace";
	}

	public static final CharitableBracedParser RULE_PARAMETERS = new CharitableBracedParser(
						new ChainParser(
							new OptionalParser(
									
									new ChoiceParser(
											CargonTokens.SILENT,
										new ChainParser(
											CargonTokens.BRACED,
											Rules.braced_parameters),
										new ChainParser(
											CargonTokens.IMPORTS,
											Rules.import_parameters))),
							new ManyParser(
									
										new ChainParser(
											CargonTokens.COMMA,
											
											new ChoiceParser(
													CargonTokens.SILENT,
												new ChainParser(
													CargonTokens.BRACED,
													Rules.braced_parameters),
												new ChainParser(
													CargonTokens.IMPORTS,
													Rules.import_parameters))))),"RULE_PARAMETERS","altbraces",",\n");

	public static final ChoiceParser parser = new ChoiceParser(
				RULE_PARAMETERS);
}