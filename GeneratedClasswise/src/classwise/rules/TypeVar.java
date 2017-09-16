package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TypeVar extends ConcreteRule {

	public static final IRule parser = new TypeVar();

	public TypeVar(){
		super("type_var");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									Tokens.ISTYPENAME),
							new OptionalParser(
									Tokens.CAMEL),
							new AddTokenParser(
								Rules.type_var_element,"type_var"),
							new MultipleParser(
									
										new ChainParser(
											
											new ChoiceParser(
													Tokens.ACCESS,
													Tokens.SPECIAL_ACCESS),
											new AddTokenParser(
												Rules.type_var_element,"type_var"))),
							new OptionalParser(
									new AddTokenParser(
										
										new ChainParser(
											
													Tokens.ACCESS,
											Tokens.AS_METHOD_NAME,
											new OptionalParser(
													Rules.name_var)),"as_method"))),"access_multi"),
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									Tokens.ISTYPENAME),
							new OptionalParser(
									Tokens.CAMEL),
							new AddTokenParser(
								Rules.type_var_element,"type_var"),
							
									Tokens.ACCESS,
							Tokens.AS_METHOD_NAME,
							new OptionalParser(
									Rules.name_var)),"access_method"),
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									Tokens.ISTYPENAME),
							new OptionalParser(
									Tokens.CAMEL),
							new AddTokenParser(
								Rules.type_var_element,"type_var"),
							new MultipleParser(
									
										new ChainParser(
											Tokens.PLUS,
											new AddTokenParser(
												Rules.type_var_element,"type_var")))),"concat"),
				new ChainParser(
					new OptionalParser(
							Tokens.ISTYPENAME),
					Rules.type_var_element)));

	}

}