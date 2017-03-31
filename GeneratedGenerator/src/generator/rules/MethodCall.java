package generator.rules;

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
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					new OptionalParser(
							new AddTokenParser(
								Tokens.STATIC,"isStatic")),
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.NEW,
							Tokens.GENERATE,
							Tokens.THIS,
							Rules.method_parameter),"subject"),
					new AddTokenParser(
						Tokens.NAME,"methodName"),
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.ANGLE_BRACES,"angle_braces"),
							new ManyParser(
									
									new ChoiceParser(
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
											Rules.boolean_statement),
											new WithParser((IRule)Rules.method_call,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
											Rules.method_parameter))))));

	}

}