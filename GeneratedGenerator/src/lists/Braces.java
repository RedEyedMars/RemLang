package lists;

import com.rem.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;

public class Braces extends ParseList {

	@Override
	public String getName() {
		return "braces";
	}
	@Override
	public String getSingular() {
		return "brace";
	}

	public static final BracedParser QUOTE = new BracedParser(
							new AddTokenParser(
								Tokens.WILD,"entry"),"QUOTE","braces","\",\"");
	public static final BracedParser QUOTE_ENTRY = new BracedParser(
							
							new ChoiceParser(
									Braces.QUOTE,
									Rules.variable_or_token_name),"QUOTE_ENTRY","braces","``,``");
	public static final BracedParser ENTRY_LIST = new BracedParser(
						new ChainParser(
							new OptionalParser(
									
									new ChoiceParser(
											new WithParser((IRule)Rules.entry_definition,new Parameter<Integer>(-1)),
											new WithParser((IRule)Rules.generate_call,new Parameter<Integer>(-1)),
										new ChainParser(
											new OptionalParser(
													new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(-1))),
											
											new ChoiceParser(
													new ListNameParser("entry_names"),
													new ListNameParser("variable_names"))))),
							new ManyParser(
									
											
											new ChoiceParser(
													new WithParser((IRule)Rules.entry_definition,new Parameter<Integer>(-1)),
													new WithParser((IRule)Rules.generate_call,new Parameter<Integer>(-1)),
												new ChainParser(
													new OptionalParser(
															new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(-1))),
													
													new ChoiceParser(
															new ListNameParser("entry_names"),
															new ListNameParser("variable_names")))))),"ENTRY_LIST","braces","{,}");
	public static final BracedParser ENTRY_STRING = new BracedParser(
						new ChainParser(
							
							new ChoiceParser(
									Braces.QUOTE_ENTRY,
									Braces.QUOTE,
									Rules.variable_or_token_name),
							new ManyParser(
									
											
											new ChoiceParser(
													Braces.QUOTE_ENTRY,
													Braces.QUOTE,
													Rules.variable_or_token_name))),"ENTRY_STRING","braces","[,]");
	public static final BracedParser ANGLE_BRACES = new BracedParser(
							Rules.angle_brace_parameters,"ANGLE_BRACES","braces","<,>");
	public static final BracedParser TAB_BRACES = new BracedParser(
							new AddTokenParser(
								Rules.tab_brace_parameters,"tab_braces"),"TAB_BRACES","braces","(,)");
	public static final BracedParser CUSTOM_ENTRY_DEFINITION = new BracedParser(
						new ChainParser(
							new ListNameParser("entry_class_names"),
							new ManyParser(
									Rules.method_parameter)),"CUSTOM_ENTRY_DEFINITION","braces","@,@");

	public static final ChoiceParser parser = new ChoiceParser(
				QUOTE,QUOTE_ENTRY,ENTRY_LIST,ENTRY_STRING,ANGLE_BRACES,TAB_BRACES,CUSTOM_ENTRY_DEFINITION);

	public static final NameParser name_parser = new NameParser(
				"braces");
	@Override
	public NameParser getNamesParser(){
		return name_parser;
	}
}