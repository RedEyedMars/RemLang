package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class MethodParameter extends ConcreteRule {

	public static final IRule parser = new MethodParameter();

	public MethodParameter(){
		super("method_parameter");
	}
	@Override
	public void setup(){
		set(
				new ChainParser(new PrintParser("METHOD_PARAMETER"),
			new ChoiceParser(
					new ChainParser(Rules.method_call){
						@Override
						public String toString(){
							return "$METH_PARAMTEER.methcall";
						}
					},
					new ChainParser(Braces.METHOD_PARAMETER){
						@Override
						public String toString(){
							return "$METH_PARAMTEER.braced";
						}
					},
					new ChainParser(Tokens.NEW,	new ListNameElementParser("class_names"),new AddTokenParser(Braces.METHOD_PARAMETERS,"constructor_call")){
							@Override
							public String toString(){
								return "$METH_PARAMTEER.new";
							}
						
					},
					new ChainParser(new PrintParser(">>CON?"),new AddTokenParser(Rules.defined_method,"constructor_call"), new PrintParser("<<CON!")){
						@Override
						public String toString(){
							return "$METH_PARAMTEER.def_con";
						}
					},
					new ChainParser(new PrintParser(">varname"),new ListNameElementParser("variable_names"){
						@Override
						public String toString(){
							return "$METH_PARAMTEER.varnames";
						}
					},new PrintParser("<varname")))));

	}

}