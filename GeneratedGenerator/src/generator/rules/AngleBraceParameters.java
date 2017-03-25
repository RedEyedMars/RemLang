package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class AngleBraceParameters extends ConcreteRule {

	public static final IRule parser = new AngleBraceParameters();
	private Parameter<?>[] parameters = new Parameter<?>[]{};
	public AngleBraceParameters(){
		super("angle_brace_parameters");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new AddTokenParser(
						
					new ChoiceParser(
							new WithParser((IRule)Rules.entry_definition,new Parameter<Integer>(-1)),
						new ChainParser(
							
							new ChoiceParser(
									new ListNameParser("class_names"),
									new ListNameParser("property_names")),
							new OptionalParser(
									new AddTokenParser(
										Braces.ANGLE_BRACES,"braces"))),
							Rules.arithmatic,
							new ListNameParser("generator_names"),
							new ListNameParser("entry_names")),"parameter"),
					new ManyParser(
							
								new ChainParser(
									Tokens.COMMA,
									new AddTokenParser(
										
									new ChoiceParser(
											new WithParser((IRule)Rules.entry_definition,new Parameter<Integer>(-1)),
										new ChainParser(
											
											new ChoiceParser(
													new ListNameParser("class_names"),
													new ListNameParser("property_names")),
											new OptionalParser(
													new AddTokenParser(
														Braces.ANGLE_BRACES,"braces"))),
											Rules.arithmatic,
											new ListNameParser("generator_names"),
											new ListNameParser("entry_names")),"parameter")))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}