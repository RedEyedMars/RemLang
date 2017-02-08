package base.rules;

import com.rem.parser.AddTokenParser;
import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.IRule;
import com.rem.parser.ParseData;
import com.rem.parser.ParseUtil;

import base.lists.Braces;
import base.lists.Listnames;
import base.lists.Rulenames;
import base.lists.Tokens;

public class Terminal extends ChoiceParser implements IRule {
	public static final IParser parser = new Terminal();
	@Override
	public void setup() {
		add(new AddTokenParser(Rulenames.name_parser,"ruleToken"){
			@Override
			public String getName(){
				return "Terminal.addRulename";
			}
		});
		add(new AddTokenParser(Listnames.parser,"listsToken"){
			@Override
			public String getName(){
				return "Terminal.addLists";
			}
		});
		add(new AddTokenParser(Listnames.name_parser,"listToken"){
			@Override
			public String getName(){
				return "Terminal.addSingleListname";
			}
		});
		add(new AddTokenParser(Tokens.name_parser,"token"){
			@Override
			public String getName(){
				return "Terminal.addToken";
			}
		});
		add(new AddTokenParser(Braces.name_parser,"braceToken"){
			@Override
			public String getName(){
				return "Terminal.addBrace";
			}
		});
	}
	@Override
	public void parse(ParseData data){
		ParseUtil.currentParser = "Terminal";
		super.parse(data);
	}
	
	@Override
	public String toString(){
		return "Terminal";
	}
}
