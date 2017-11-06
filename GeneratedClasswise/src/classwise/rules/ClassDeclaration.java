package classwise.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class ClassDeclaration extends ConcreteRule {

	public static final IRule parser = new ClassDeclaration();

	public ClassDeclaration(){
		super("class_declaration");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new OptionalParser(
							Tokens.WEAK),
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.CLASS,
							Tokens.INTERFACE,
							Tokens.ENUMERATION),"objectType"),
					
						new ChainParser(
							new AddTokenParser(
								Rules.name_var,"packageName"),
							new ManyParser(
									
										new ChainParser(
											Tokens.DOT,
											new AddTokenParser(
												Rules.name_var,"packageName")))),
					new AddTokenToListParser(
						
					new ChoiceParser(
							new ListNameElementParser("variable_names"),
							new ListNameElementParser("class_variable_names"),
							Tokens.NAME),"className","class_names"),
					new ManyParser(
							Tokens.NEWLINE),
					new OptionalParser(
							
								new ChainParser(
									Tokens.HID,
									new AddTokenToListParser(
										Tokens.NAME,"templateTypeName","class_variable_names"),
									Tokens.IN)),
					new ManyParser(
							Tokens.NEWLINE),
					new OptionalParser(
							
									new AddTokenParser(
										Rules.type_var,"parentName")),
					new OptionalParser(
							Tokens.NEWLINE),
					new ManyParser(
							
									new AddTokenParser(
										Rules.type_var,"interfaceName")),
					Braces.CLASS_BODY),
				new ChainParser(
					new OptionalParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Tokens.IN,
									Tokens.NER),"inner")),
					new OptionalParser(
							Tokens.WEAK),
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.CLASS,
							Tokens.INTERFACE,
							Tokens.ENUMERATION),"objectType"),
					new AddTokenToListParser(
						
					new ChoiceParser(
							new ListNameElementParser("variable_names"),
							new ListNameElementParser("class_variable_names"),
							Tokens.NAME),"className","class_names"),
					new ManyParser(
							Tokens.NEWLINE),
					new OptionalParser(
							
								new ChainParser(
									Tokens.HID,
									new AddTokenToListParser(
										Tokens.NAME,"templateTypeName","class_variable_names"),
									Tokens.IN)),
					new ManyParser(
							Tokens.NEWLINE),
					Tokens.FORSLASH,
					new OptionalParser(
							
									new AddTokenParser(
										Rules.type_var,"parentName")),
					new ManyParser(
							Tokens.NEWLINE),
					Tokens.FORSLASH,
					new ManyParser(
							
									new AddTokenParser(
										Rules.type_var,"interfaceName")),
					Braces.CLASS_BODY)));

	}

}