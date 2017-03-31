package generator.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class VariableDeclaration extends ConcreteRule {

	public static final IRule parser = new VariableDeclaration();
	public VariableDeclaration(){
		super("variable_declaration");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(
					new WithParser((IRule)Rules.whitetab,new Parameter<Integer>(0)),
					Tokens.VARIABLE,
					new AddTokenToListParser(
						Tokens.NAME,"variableName","variable_names"),
					new OptionalParser(
							Rules.cast_as_statement),
					
					new ChoiceParser(
							new WithParser((IRule)Rules.cast_statement,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
							Rules.boolean_statement),
							new WithParser((IRule)Rules.method_call,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
						new ChainParser(
							new WithParser((IRule)Rules.whitetab,new Argument.Add(new Parameter<Integer>(0),new Argument.Number(1))),
							Rules.method_parameter))));

	}

}