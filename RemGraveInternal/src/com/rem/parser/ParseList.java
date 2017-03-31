package com.rem.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.rem.parser.parser.IParser;
import com.rem.parser.parser.NameParser;
import com.rem.parser.token.BranchToken;
import com.rem.parser.token.IToken;
import com.rem.parser.token.NodeToken;

public abstract class ParseList extends BranchToken{

	private BranchToken newTokensToken;
	private NameParser name_parser;
	
	public abstract String getName();	
	public abstract String getSingular();
	
	public NameParser getNamesParser(){
		return name_parser;
	}
	
	
	@Override
	public IToken put(IToken.Key key,IToken value){
		getNamesParser().addName(value.getString());
		return newTokensToken.put(key, value);
	}
	
	public IToken getNewTokens(){
		return newTokensToken;
	}

	public void reset() {
		newTokensToken = new BranchToken();
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
		try {
			for(Field field:ParseList.class.getDeclaredFields()){
				if("serialVersionUID".equals(field.getName())||
						"parser".equals(field.getName())||
						"name_parser".equals(field.getName())||
						"this$0".equals(field.getName())||
						"val$listName".equals(field.getName())||
						"val$singleName".equals(field.getName())){
					continue;
				}
				else {
					newList.put(new NodeToken(field.getName(),IParser.DEFAULT,field.get(newList),-1));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		newList.newTokensToken = new BranchToken();
		newList.name_parser = new NameParser(parentContext,listName);
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
