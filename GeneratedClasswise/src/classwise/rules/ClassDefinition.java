package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClassDefinition extends ConcreteRule {

	public static final IRule parser = new ClassDefinition();

	public ClassDefinition(){
		super("class_definition");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new AddTokenToListParser(
						Tokens.NAME,"className","class_names"),
					new ManyParser(
							Tokens.NEWLINE),
					
							new AddTokenParser(
								new ListNameElementParser("class_names"),"parentName"),
					new ManyParser(
							Tokens.NEWLINE),
					new ManyParser(
							
									new AddTokenParser(
										new ListNameElementParser("class_names"),"interfaceName")),
					Braces.CLASS_BODY));

	}

}