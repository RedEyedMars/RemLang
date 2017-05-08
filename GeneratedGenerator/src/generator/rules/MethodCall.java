package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MethodCall extends ConcreteRule {

	public static final IRule parser = new MethodCall();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public MethodCall(){
		super("method_call");
	}
	@Override
	public void setup(){
		set(
			new ChoiceParser(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					new AddTokenParser(
						Tokens.NEW,"subject"),
					new OptionalParser(
							new AddTokenParser(
								Rules.angle_brace_class,"angle_class")),
					new ManyParser(
							
							new ChoiceParser(
								new ChainParser(
									new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(1))),
									Rules.boolean_statement),
									new WithParser((IRule)Rules.method_call,new Argument.Add(this.tabs,new Argument.Number(1))),
								new ChainParser(
									new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(1))),
									Rules.method_parameter)))),
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					
							new OptionalParser(
									new AddTokenParser(
										Tokens.STATIC,"isStatic")),
					new AddTokenParser(
						
					new ChoiceParser(
							Tokens.ASTRO_GENERATE,
							Tokens.THIS,
							Rules.method_parameter),"subject"),
					
					new ChoiceParser(
						new ChainParser(
							new ListNameElementParser("generator_names"),
							new AddTokenParser(
								Tokens.NAME,"methodName")),
							new AddTokenParser(
								Tokens.NAME,"methodName")),
					
					new ChoiceParser(
							new AddTokenParser(
								Braces.INLINE_PARAMETERS,"inline_parameters"),
							new ManyParser(
									
									new ChoiceParser(
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(1))),
											Rules.boolean_statement),
											new WithParser((IRule)Rules.method_call,new Argument.Add(this.tabs,new Argument.Number(1))),
										new ChainParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(1))),
											Rules.method_parameter)))))));

	}

}