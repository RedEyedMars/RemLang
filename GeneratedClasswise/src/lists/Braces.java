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

	public static final BracedParser CLASS_BODY = new BracedParser(
							new ManyParser(
									Rules.class_element),"CLASS_BODY","braces","{,}");
	public static final BracedParser METHOD_BODY = new BracedParser(
							new ManyParser(
									Rules.body_element),"METHOD_BODY","braces","{,}");
	public static final BracedParser METHOD_PARAMETERS = new BracedParser(
							new OptionalParser(
									
										new ChainParser(
											Rules.variable_declaration,
											new ManyParser(
													
														new ChainParser(
															Tokens.COMMA,
															Rules.variable_declaration)))),"METHOD_PARAMETERS","braces","(,)");
	public static final BracedParser PARAMETERS = new BracedParser(
							new OptionalParser(
									
										new ChainParser(
											new ManyParser(
													Tokens.NEWLINE),
											Rules.method_argument,
											new ManyParser(
													
														new ChainParser(
															new ManyParser(
																	Tokens.NEWLINE),
															Tokens.COMMA,
															new ManyParser(
																	Tokens.NEWLINE),
															Rules.method_argument)))),"PARAMETERS","braces","(,)");
	public static final BracedParser TEMPLATE_PARAMETERS = new BracedParser(
						new ChainParser(
							new AddTokenParser(
								Rules.all_type_name,"template_parameter"),
							new ManyParser(
									
										new ChainParser(
											Tokens.COMMA,
											new AddTokenParser(
												Rules.all_type_name,"template_parameter")))),"TEMPLATE_PARAMETERS","braces","<,>");
	public static final BracedParser QUOTE = new BracedParser(
							Tokens.WILD,"QUOTE","braces","\",\"");
	public static final BracedParser STATEMENT_AS_QUOTE = new BracedParser(
							Rules.body_statement,"STATEMENT_AS_QUOTE","braces","\'\',\'\'");
	public static final BracedParser STATEMENT_AS_STRING = new BracedParser(
							Rules.body_statement,"STATEMENT_AS_STRING","braces","\',\'");
	public static final BracedParser STATEMENT_AS_METHOD = new BracedParser(
							Rules.body_statement,"STATEMENT_AS_METHOD","braces","`,`");
	public static final BracedParser AS_STATEMENT = new BracedParser(
							
							new ChoiceParser(
									new MultipleParser(
											Rules.body_element),
									Rules.body_statement),"AS_STATEMENT","braces","|,|");
	public static final BracedParser CLASS_FILE = new BracedParser(
							new AddTokenParser(
								Tokens.WILD,"class_file_name"),"CLASS_FILE","braces",",.");

	public static final ChoiceParser parser = new ChoiceParser(
				CLASS_BODY,METHOD_BODY,METHOD_PARAMETERS,PARAMETERS,TEMPLATE_PARAMETERS,QUOTE,STATEMENT_AS_QUOTE,STATEMENT_AS_STRING,STATEMENT_AS_METHOD,AS_STATEMENT,CLASS_FILE);
}