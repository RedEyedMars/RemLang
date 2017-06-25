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
											new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
											new WithParser((IRule)Rules.generate_call,new Argument.Number(-1)),
										new ChainParser(
											new OptionalParser(
													new WithParser((IRule)Rules.whitetab,new Argument.Number(-1))),
											Rules.variable_or_token_name))),
							new ManyParser(
									
											
											new ChoiceParser(
													new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
													new WithParser((IRule)Rules.generate_call,new Argument.Number(-1)),
												new ChainParser(
													new OptionalParser(
															new WithParser((IRule)Rules.whitetab,new Argument.Number(-1))),
													Rules.variable_or_token_name))),
							new ManyParser(
									Tokens.NL)),"ENTRY_LIST","braces","{,}");
	public static final BracedParser ENTRY_SET = new BracedParser(
						new ChainParser(
							new OptionalParser(
									
									new ChoiceParser(
											new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
											new WithParser((IRule)Rules.generate_call,new Argument.Number(-1)),
										new ChainParser(
											new OptionalParser(
													new WithParser((IRule)Rules.whitetab,new Argument.Number(-1))),
											Rules.variable_or_token_name))),
							new ManyParser(
									
											
											new ChoiceParser(
													new WithParser((IRule)Rules.entry_definition,new Argument.Number(-1)),
													new WithParser((IRule)Rules.generate_call,new Argument.Number(-1)),
												new ChainParser(
													new OptionalParser(
															new WithParser((IRule)Rules.whitetab,new Argument.Number(-1))),
													Rules.variable_or_token_name))),
							new ManyParser(
									Tokens.NL)),"ENTRY_SET","braces","[,]");
	public static final BracedParser ANGLE_CLASSES = new BracedParser(
						new ChainParser(
							Rules.angle_brace_class,
							new ManyParser(
									
										new ChainParser(
											Tokens.COMMA,
											Rules.angle_brace_class))),"ANGLE_CLASSES","braces","<,>");
	public static final BracedParser INLINE_PARAMETERS = new BracedParser(
							new ManyParser(
									Rules.method_parameter),"INLINE_PARAMETERS","braces",":,;");
	public static final BracedParser TAB_BRACES = new BracedParser(
							new AddTokenParser(
								Rules.tab_brace_parameters,"tab_braces"),"TAB_BRACES","braces","(,)");
	public static final BracedParser CUSTOM_ENTRY_DEFINITION = new BracedParser(
						new ChainParser(
							new ManyParser(
									
										new ChainParser(
											new ManyParser(
													Tokens.NL),
											Rules.method_parameter)),
							new ManyParser(
									Tokens.NL)),"CUSTOM_ENTRY_DEFINITION","braces",":,;");
	public static final BracedParser PIPE_ENTRY = new BracedParser(
							new ListNameElementParser("variable_names"),"PIPE_ENTRY","braces","|,|");

	public static final ChoiceParser parser = new ChoiceParser(
				QUOTE,QUOTE_ENTRY,ENTRY_LIST,ENTRY_SET,ANGLE_CLASSES,INLINE_PARAMETERS,TAB_BRACES,CUSTOM_ENTRY_DEFINITION,PIPE_ENTRY);
}