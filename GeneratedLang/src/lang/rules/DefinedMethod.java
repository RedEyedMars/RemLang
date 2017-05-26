package lang.rules;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import lists.*;

public class DefinedMethod extends ConcreteRule {

	public static final IRule parser = new DefinedMethod();

	public DefinedMethod(){
		super("defined_method");
	}
	@Override
	public void setup(){set(new ChoiceParser(){
		@Override
		public boolean add(IParser parser){
			//System.out.println(parser+">>"+((AddTokenParser)parser).getTokenName()+":"+this.size());
			return super.add(parser);
		}
		@Override
		public void parse(ParseContext data){
			super.parse(data);
		}
	});


	}

}