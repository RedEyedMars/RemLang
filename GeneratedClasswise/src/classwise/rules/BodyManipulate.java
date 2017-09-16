package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class BodyManipulate extends ConcreteRule {

	public static final IRule parser = new BodyManipulate();

	public BodyManipulate(){
		super("body_manipulate");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new OptionalParser(
							
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER)),
					Rules.type_var,
					new AddTokenParser(
						Tokens.ADD,"methodName"),
					new ManyParser(
							Tokens.NEWLINE),
					
					new ChoiceParser(
							Rules.class_declaration,
							Rules.method_declaration,
							Rules.variable_declaration,
							new AddTokenParser(
								Braces.METHOD_BODY,"body"),
							new AddTokenParser(
								Braces.AS_STATEMENT,"body"))),
				new ChainParser(
					new OptionalParser(
							
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER)),
					Rules.name_var,
					Tokens.COLON,
					new AddTokenToListParser(
						Tokens.NAME,"variableName","variable_names"),
					new MultipleParser(
							new AddTokenParser(
								
								new ChainParser(
									new ManyParser(
											Tokens.NEWLINE),
									Tokens.COLON,
									new AddTokenParser(
										Tokens.NAME,"tokenName"),
									new ManyParser(
											Tokens.NEWLINE),
									Braces.METHOD_BODY),"tokenInstance")))));

	}

}