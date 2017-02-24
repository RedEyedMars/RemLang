package base.rules;

import com.rem.parser.*;
import base.lists.*;

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
							Rules.arithmatic,
							new ListNameParser("class_names"),
							new ListNameParser("entry_names")),"parameter"),
					new OptionalParser(
							Braces.ANGLE_BRACES),
					new ManyParser(
							
								new ChainParser(
									Tokens.COMMA,
									new AddTokenParser(
										
									new ChoiceParser(
											new WithParser((IRule)Rules.entry_definition,new Parameter<Integer>(-1)),
											Rules.arithmatic,
											new ListNameParser("class_names"),
											new ListNameParser("entry_names")),"parameter"),
									new OptionalParser(
											Braces.ANGLE_BRACES)))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}