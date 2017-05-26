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

	public static final BracedParser METHOD_BODY = new BracedParser(
							new ManyParser(
									
									new ChoiceParser(
											Tokens.NEWLINE,
											Rules.return_call,
											Rules.body_element)),"METHOD_BODY","braces","{,}");
	public static final BracedParser CLASS_BODY = new BracedParser(
							new ManyParser(
									
									new ChoiceParser(
											Tokens.NEWLINE,
											Rules.class_definition)),"CLASS_BODY","braces","{,}");
	public static final BracedParser REGEX = new BracedParser(
							Tokens.NON_SPACE,"REGEX","braces","[,]");
	public static final BracedParser BRACED_CONSTRUCTOR = new BracedParser(
						new ChainParser(
							new AddTokenParser(
								Tokens.NON_SPACE,"left"),
							new ManyParser(
									Rules.define_parameter),
							new AddTokenParser(
								Tokens.NON_SPACE,"right")),"BRACED_CONSTRUCTOR","braces","[,]");
	public static final BracedParser QUOTE = new BracedParser(
							new AddTokenParser(
								Tokens.WILD,"NON_SPACE"),"QUOTE","braces","\",\"");
	public static final BracedParser PARAMETERS = new BracedParser(
							new OptionalParser(
									
										new ChainParser(
											Rules.variable_declaration,
											new ManyParser(
													
														new ChainParser(
															Tokens.COMMA,
															Rules.variable_declaration)))),"PARAMETERS","braces","(,)");
	public static final BracedParser METHOD_PARAMETERS = new BracedParser(
							new OptionalParser(
									
										new ChainParser(
											Rules.method_parameter,
											new ManyParser(
													
														new ChainParser(
															Tokens.COMMA,
															Rules.method_parameter)))),"METHOD_PARAMETERS","braces","(,)");
	public static final BracedParser METHOD_PARAMETER = new BracedParser(
							Rules.method_parameter,"METHOD_PARAMETER","braces","(,)");

	public static final ChoiceParser parser = new ChoiceParser(
				METHOD_BODY,CLASS_BODY,REGEX,BRACED_CONSTRUCTOR,QUOTE,PARAMETERS,METHOD_PARAMETERS,METHOD_PARAMETER);
}