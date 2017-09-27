package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BodyCall extends ConcreteRule {

	public static final IRule parser = new BodyCall();

	public BodyCall(){
		super("body_call");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new AddTokenParser(
						
						new ChainParser(
							Tokens.NEW,
							new AddTokenParser(
								Rules.all_type_name,"typeName"),
							new OptionalParser(
									new AddTokenParser(
										Braces.PARAMETERS,"parameters"))),"group"),
					new ManyParser(
							
								new ChainParser(
									new ManyParser(
											Tokens.NEWLINE),
									Tokens.DOT,
									new ManyParser(
											Tokens.NEWLINE),
									new AddTokenParser(
										
										new ChainParser(
											Tokens.NAME,
											new OptionalParser(
													new AddTokenParser(
														Braces.PARAMETERS,"parameters"))),"group")))),
				new ChainParser(
					new AddTokenParser(
						
						new ChainParser(
							Rules.name_var,
							new OptionalParser(
									new AddTokenParser(
										Braces.PARAMETERS,"parameters"))),"group"),
					new ManyParser(
							
								new ChainParser(
									new ManyParser(
											Tokens.NEWLINE),
									Tokens.DOT,
									new ManyParser(
											Tokens.NEWLINE),
									new AddTokenParser(
										
										new ChainParser(
											
											new ChoiceParser(
													Rules.name_var,
													Tokens.NAME),
											new OptionalParser(
													new AddTokenParser(
														Braces.PARAMETERS,"parameters"))),"group")))),
				new ChainParser(
					new AddTokenParser(
						
						new ChainParser(
							Rules.type_var,
							new ManyParser(
									
										new ChainParser(
											new ManyParser(
													Tokens.NEWLINE),
											Tokens.DOT,
											new ManyParser(
													Tokens.NEWLINE),
											Rules.type_var)),
							new OptionalParser(
									new AddTokenParser(
										Braces.PARAMETERS,"parameters"))),"group"),
					new ManyParser(
							
								new ChainParser(
									new ManyParser(
											Tokens.NEWLINE),
									Tokens.DOT,
									new ManyParser(
											Tokens.NEWLINE),
									new AddTokenParser(
										
										new ChainParser(
											Tokens.NAME,
											new OptionalParser(
													new AddTokenParser(
														Braces.PARAMETERS,"parameters"))),"group"))))));

	}

}