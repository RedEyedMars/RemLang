package com.rem.parser;

import java.lang.reflect.Field;

import com.rem.parser.parser.ChoiceParser;
import com.rem.parser.parser.IParser;
import com.rem.parser.parser.NameParser;
import com.rem.parser.token.BranchToken;
import com.rem.parser.token.IToken;
import com.rem.parser.token.NodeToken;
import com.rem.parser.token.ParseListToken;

public abstract class ParseList extends BranchToken{

	private ParseListToken newTokensToken;
	private NameParser defaultNameParser;
	private ChoiceParser _parser;
	
	public abstract String getName();	
	public abstract String getSingular();
	
	public NameParser getNamesParser(){
		return defaultNameParser;
	}
	protected ParseList(){

		try {
			for(Field field:this.getClass().getDeclaredFields()){
				if("parser".equals(field.getName())){
					_parser = (ChoiceParser)field.get(this);
				}
				else if("serialVersionUID".equals(field.getName())||
						"_parser".equals(field.getName())||
						"defaultNameParser".equals(field.getName())||
						"this$0".equals(field.getName())||
						"val$listName".equals(field.getName())||
						"val$singleName".equals(field.getName())){
					continue;
				}
				else {
					put(new NodeToken(field.getName(),IParser.DEFAULT,field.get(this),-1));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public IToken put(IToken.Key key,IToken value){
		if(getNamesParser().getName()!=null&&getNamesParser().getName().equals("variable_names")&&value!=null&&value.getString().equals("generator")){
			System.out.println("!"+value.getPosition());
		}
		getNamesParser().addName(value.getString());
		return newTokensToken.put(key, value);
	}
	
	public ParseListToken getNewTokens(){
		return newTokensToken;
	}

	public void reset() {
		newTokensToken = new ParseListToken();
	}

	public ChoiceParser getParsers() {
		return _parser;
	}
	static ParseList createNew(final String listName, final String singleName, final ParseContext parentContext){
		//System.out.println(listName+"::"+singleName);
		ParseList newList = new ParseList(){
			@Override
			public String getName() {
				return listName;
			}

			@Override
			public String getSingular() {
				return singleName;
			}
		};
		
		newList.newTokensToken = new ParseListToken();
		newList.defaultNameParser = new NameParser(parentContext,listName);
		return newList;
	}

	public static String createSingleName(String listName) {
		int indexOfDash = listName.indexOf('-');
		if(indexOfDash>-1){
			return listName.substring(0, indexOfDash);
		}
		return listName.substring(0,listName.length()-1);
	}
	public static String createPluralName(String listName) {
		int indexOfDash = listName.indexOf('-');
		if(indexOfDash>-1){
			return listName.substring(0, indexOfDash)+listName.substring(indexOfDash+1);
		}
		return listName;
	}
}
