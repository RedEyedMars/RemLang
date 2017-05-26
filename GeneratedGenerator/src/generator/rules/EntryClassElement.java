package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class EntryClassElement extends ConcreteRule {

	public static final IRule parser = new EntryClassElement();

	public EntryClassElement(){
		super("entry_class_element");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
					new WithParser((IRule)Rules.constant_declaration,new Argument.Number(1)),
					new WithParser((IRule)Rules.variable_declaration,new Argument.Number(1)),
					new WithParser((IRule)Rules.entry_declaration,new Argument.Number(1)),
					new AddTokenParser(
						
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Number(1)),
							Tokens.CONSTRUCTOR,
							new OptionalParser(
									new WithParser((IRule)Rules.takes_statement,new Argument.Number(1))),
							new MultipleParser(
									
									new ChoiceParser(
											new WithParser((IRule)Rules.entry_declaration,new Argument.Number(2)),
											new WithParser((IRule)Rules.body_element,new Argument.Number(2))))),"constructor"),
					new AddTokenParser(
						
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Number(1)),
							new AddTokenParser(
								Tokens.OUTPUT,"methodName"),
							new MultipleParser(
									new AddTokenParser(
										
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Number(3)),
											
											new ChoiceParser(
													new AddTokenParser(
														Tokens.PLUS,"break"),
												new ChainParser(
													new AddTokenParser(
														
													new ChoiceParser(
															new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
															new ListNameElementParser("entry_names")),"value"),
													new OptionalParser(
															new AddTokenParser(
																
															new ChoiceParser(
																	new AddTokenParser(
																		Tokens.OTHERWISE,"otherwise"),
																new ChainParser(
																	Tokens.IF,
																	Rules.boolean_statement)),"ifStatement"))))),"entry_values")),
							new AddTokenParser(
								
									new ManyParser(
											
											new ChoiceParser(
													new WithParser((IRule)Rules.entry_declaration,new Argument.Number(2)),
													new WithParser((IRule)Rules.body_element,new Argument.Number(2)))),"body")),"output_method"),
					new AddTokenParser(
						
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Number(1)),
							new AddTokenParser(
								Tokens.NAME,"methodName"),
							new OptionalParser(
									Rules.cast_as_statement),
							new OptionalParser(
									new WithParser((IRule)Rules.takes_statement,new Argument.Number(1))),
							new MultipleParser(
									
									new ChoiceParser(
											new WithParser((IRule)Rules.entry_declaration,new Argument.Number(2)),
											new WithParser((IRule)Rules.body_element,new Argument.Number(2))))),"entry_method")));

	}

}