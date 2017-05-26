package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MethodCall extends ConcreteRule {

	public static final IRule parser = new MethodCall();

	public MethodCall(){
		super("method_call");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new AddTokenParser(
						Rules.method_parameter,"subject"),
					new MultipleParser(
							new AddTokenParser(
								
							new ChoiceParser(
									Rules.defined_operator,
								new ChainParser(
									new ListNameElementParser("method_names"),
									Braces.METHOD_PARAMETERS)),"name_parameter_pair"))){
					@Override
					public String toString(){
										return "$METHOD_CALL_1";
									}
				},
					new MultipleParser(
							new AddTokenParser(
								
								new ChainParser(
									new ListNameElementParser("method_names"),
									Braces.METHOD_PARAMETERS),"name_parameter_pair")){
					@Override
					public String toString(){
										return "$METHOD_CALL_2";
									}
				}));

	}

}