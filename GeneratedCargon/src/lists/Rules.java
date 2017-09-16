package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import cargon.rules.*;

public class Rules extends ParseList {

	@Override
	public String getName() {
		return "rules";
	}
	@Override
	public String getSingular() {
		return "rule";
	}

	public static final IRule braced_parameters = BracedParameters.parser;
	public static final IRule import_parameters = ImportParameters.parser;
	public static final IRule base = Base.parser;
	public static final IRule rule = Rule.parser;
	public static final IRule list = List.parser;
	public static final IRule definition = Definition.parser;
	public static final IRule element = Element.parser;
	public static final IRule atom = Atom.parser;
	public static final IRule regex = Regex.parser;
	public static final IRule regex_element = RegexElement.parser;
	public static final IRule regex_special = RegexSpecial.parser;

	public static final ChoiceParser parser = new ChoiceParser(
				braced_parameters,import_parameters,base,rule,list,definition,element,atom,regex,regex_element,regex_special);
}