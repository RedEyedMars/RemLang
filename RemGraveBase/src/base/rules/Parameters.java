package base.rules;

import base.lists.Listnames;
import base.lists.Tokens;
import com.rem.parser.AddTokenParser;
import com.rem.parser.ChainParser;
import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.IRule;
import com.rem.parser.OptionalParser;
import com.rem.parser.ParseData;
import com.rem.parser.ParseUtil;

public class Parameters extends ChoiceParser implements IRule{
	public static final IParser parser = new Parameters();

	private Parameters(){
		super();
	}
	public void setup(){
		add(
				new ChainParser(						
						new OptionalParser(
								new ChainParser(										
										Tokens.SPACES,Tokens.AS,Tokens.SPACES,
										new AddTokenParser(Tokens.NAME,"name"))),
						new OptionalParser(
								new ChainParser(
										Tokens.SPACES,Tokens.IN,Tokens.SPACES,
										new AddTokenParser(Listnames.parser,"list"))),
						new OptionalParser(
								new ChainParser(
										Tokens.SPACES,Tokens.WITH,new OptionalParser(Tokens.SPACES),
										new AddTokenParser(Definition.parser,"parameter"))),						
						new OptionalParser(Tokens.SPACES)
					)
						
				);
	}
	
	@Override
	public void parse(ParseData data){
		ParseUtil.currentParser = "Parameters";
		super.parse(data);
	}
	
	@Override
	public String toString(){
		return "Parameters";
	}
}
