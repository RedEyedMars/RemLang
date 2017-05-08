package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class AngleBraceClasses extends ConcreteRule {

	public static final IRule parser = new AngleBraceClasses();

	public AngleBraceClasses(){
		super("angle_brace_classes");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					
					new ChoiceParser(
							new ListNameElementParser("entry_class_names"),
							new ListNameElementParser("class_names"),
							new ListNameElementParser("property_names")),
					new OptionalParser(
							new AddTokenParser(
								Braces.ANGLE_CLASSES,"braces")),
					new ManyParser(
							
								new ChainParser(
									Tokens.COMMA,
									
									new ChoiceParser(
											new ListNameElementParser("entry_class_names"),
											new ListNameElementParser("class_names"),
											new ListNameElementParser("property_names")),
									new OptionalParser(
											new AddTokenParser(
												Braces.ANGLE_CLASSES,"braces"))))));

	}

}