package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class AngleBraceParameters extends ConcreteRule {

	public static final IRule parser = new AngleBraceParameters();

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
							new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
						new ChainParser(
							
							new ChoiceParser(
									new ListNameElementParser("entry_class_names"),
									new ListNameElementParser("class_names"),
									new ListNameElementParser("property_names")),
							new OptionalParser(
									new AddTokenParser(
										Braces.ANGLE_BRACES,"braces"))),
							Rules.arithmatic,
							new ListNameElementParser("generator_names"),
							new ListNameElementParser("entry_names"),
							Tokens.TRUE,
							Tokens.FALSE,
							Tokens.NULL),"parameter"),
					new ManyParser(
							
								new ChainParser(
									Tokens.COMMA,
									new AddTokenParser(
										
									new ChoiceParser(
											new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
										new ChainParser(
											
											new ChoiceParser(
													new ListNameElementParser("entry_class_names"),
													new ListNameElementParser("class_names"),
													new ListNameElementParser("property_names")),
											new OptionalParser(
													new AddTokenParser(
														Braces.ANGLE_BRACES,"braces"))),
											Rules.arithmatic,
											new ListNameElementParser("generator_names"),
											new ListNameElementParser("entry_names"),
											Tokens.TRUE,
											Tokens.FALSE,
											Tokens.NULL),"parameter")))));

	}

}