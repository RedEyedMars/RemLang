package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class TakesStatement extends ConcreteRule {

	public static final IRule parser = new TakesStatement();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public TakesStatement(){
		super("takes_statement");
	}
	@Override
	public void setup(){
		isSilent(true);
		set(
				new ChainParser(
					Tokens.TAKES,
					new AddTokenParser(
						
						new ChainParser(
							new OptionalParser(
									new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(2)))),
							new AddTokenToListParser(
								Tokens.NAME,"takeName","variable_names"),
							new OptionalParser(
									Rules.cast_as_statement)),"parameter"),
					new ManyParser(
							
								new ChainParser(
									
									new ChoiceParser(
											Tokens.COMMA,
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(2)))),
									new AddTokenParser(
										
										new ChainParser(
											new AddTokenToListParser(
												Tokens.NAME,"takeName","variable_names"),
											new OptionalParser(
													Rules.cast_as_statement)),"parameter")))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}