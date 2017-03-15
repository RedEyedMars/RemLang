package base.rules;

import com.rem.parser.*;
import lists.*;

public class VariableDeclaration extends ConcreteRule {

	public static final IRule parser = new VariableDeclaration();
	private Parameter<Integer> tabs = new Parameter<Integer>(0);
	private Parameter<?>[] parameters = new Parameter<?>[]{tabs};
	public VariableDeclaration(){
		super("variable_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,this.tabs),
					Tokens.VARIABLE,
					new AddTokenToListParser(
						Tokens.NAME,"variableName","variable_names"),
					new OptionalParser(
							Rules.cast_as_statement),
					
					new ChoiceParser(
							new WithParser((IRule)Rules.cast_statement,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
							Rules.boolean_statement),
							new WithParser((IRule)Rules.method_call,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Add(this.tabs,new Parameter<Integer>(1))),
							Rules.method_parameter))));

	}
	@Override @SuppressWarnings("unchecked")
	public Parameter<?>[] getParameters(){
		return parameters;
	}

}