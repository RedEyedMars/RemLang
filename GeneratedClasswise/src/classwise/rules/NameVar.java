package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class NameVar extends ConcreteRule {

	public static final IRule parser = new NameVar();

	public NameVar(){
		super("name_var");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									Tokens.CAMEL),
							new AddTokenParser(
								Rules.name_var_element,"name_var"),
							new MultipleParser(
									
										new ChainParser(
											
													Tokens.ACCESS,
											new AddTokenParser(
												Rules.name_var_element,"name_var")))),"access"),
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									Tokens.CAMEL),
							new AddTokenParser(
								Rules.name_var_element,"name_var"),
							new MultipleParser(
									
										new ChainParser(
											Tokens.PLUS,
											new AddTokenParser(
												Rules.name_var_element,"name_var")))),"concat"),
					Rules.name_var_element));

	}

}