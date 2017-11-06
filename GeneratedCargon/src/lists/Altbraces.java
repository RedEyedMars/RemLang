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
									Rules.rule_params),
							new ManyParser(
									
										new ChainParser(
											CargonTokens.COMMA,
											Rules.rule_params))),"RULE_PARAMETERS","altbraces",",\n");
	public static final CharitableBracedParser EQUALS_RULE_PARAMETERS = new CharitableBracedParser(
						new ChainParser(
							new OptionalParser(
									Rules.rule_params),
							new ManyParser(
									
										new ChainParser(
											CargonTokens.COMMA,
											Rules.rule_params))),"EQUALS_RULE_PARAMETERS","altbraces",",=");

	public static final ChoiceParser parser = new ChoiceParser(
				RULE_PARAMETERS,EQUALS_RULE_PARAMETERS);
}