package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class EachCall extends ConcreteRule {

	public static final IRule parser = new EachCall();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public EachCall(){
		super("each_call");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.EACH,
					new AddTokenToListParser(
						Tokens.NAME,"eachName","variable_names"),
					Tokens.IN,
					new AddTokenParser(
						new ListNameParser("variable_names"),"iterable"),
					new MultipleParser(
							new WithParser((IRule)Rules.body_element,new Argument.Add(this.tabs,new Parameter<Integer>(1))))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}