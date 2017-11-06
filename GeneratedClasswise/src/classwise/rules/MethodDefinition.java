package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MethodDefinition extends ConcreteRule {

	public static final IRule parser = new MethodDefinition();

	public MethodDefinition(){
		super("method_definition");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.STA,
									Tokens.TIC),"static")),
					new AddTokenParser(
						Rules.all_type_name,"typeName"),
					new ManyParser(
							Tokens.ARRAY_TYPE),
					new AddTokenParser(
						
					new ChoiceParser(
							new AddTokenParser(
								Tokens.AS_METHOD_NAME,"NAME"),
							Rules.name_var,
							Tokens.NAME),"methodName"),
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.METHOD_PARAMETERS,"inline"),
							new AddTokenParser(
								Braces.STATEMENT_AS_METHOD,"variableParameters")),
					new OptionalParser(
							
								new ChainParser(
									Tokens.THROWS,
									new AddTokenParser(
										Tokens.NAME,"throwException"),
									new ManyParser(
											
												new ChainParser(
													Tokens.COMMA,
													new AddTokenParser(
														Tokens.NAME,"throwException"))))),
					Braces.METHOD_BODY));

	}

}