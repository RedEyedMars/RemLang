package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class CheckCall extends ConcreteRule {

	public static final IRule parser = new CheckCall();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);

	public CheckCall(){
		super("check_call");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.CHECK,
					new AddTokenParser(
						Tokens.NAME,"checkName"),
					new AddTokenParser(
						
						new ChainParser(
							new AddTokenParser(
								Tokens.NAME,"left"),
							
							new ChoiceParser(
									new AddTokenParser(
										
									new ChoiceParser(
										new ChainParser(
											Tokens.IS,
											new OptionalParser(
													Tokens.NOT)),
											Tokens.ORDINAL_OPERATOR),"operator"),
									new AddTokenParser(
										Tokens.NAME,"method")),
							new AddTokenParser(
								Tokens.NAME,"right")),"parameter"),
					new ManyParser(
							new AddTokenParser(
								
								new ChainParser(
									new OptionalParser(
											new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(2)))),
									new AddTokenParser(
										
									new ChoiceParser(
											Tokens.AND,
											Tokens.OR),"con_op"),
									new AddTokenParser(
										Tokens.NAME,"left"),
									
									new ChoiceParser(
											new AddTokenParser(
												
											new ChoiceParser(
												new ChainParser(
													Tokens.IS,
													new OptionalParser(
															Tokens.NOT)),
													Tokens.ORDINAL_OPERATOR),"operator"),
											new AddTokenParser(
												Tokens.NAME,"method")),
									new AddTokenParser(
										Tokens.NAME,"right")),"parameter")),
					new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Argument.Number(1))),
					new AddTokenParser(
						Tokens.NON_NEWLINE,"errorMessage")));

	}

}