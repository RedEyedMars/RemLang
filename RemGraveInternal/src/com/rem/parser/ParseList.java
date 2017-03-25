package com.rem.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.rem.parser.parser.NameParser;
import com.rem.parser.token.BranchToken;
import com.rem.parser.token.IToken;
import com.rem.parser.token.NodeToken;

public abstract class ParseList extends BranchToken{

	private BranchToken newTokensToken;
	private List<ParseList> children = new ArrayList<ParseList>();

	public ParseList(){
		super();
		try {
			for(Field field:this.getClass().getDeclaredFields()){
				if("serialVersionUID".equals(field.getName())||
						"parser".equals(field.getName())||
						"name_parser".equals(field.getName())||
						"this$0".equals(field.getName())||
						"val$listName".equals(field.getName())||
						"val$singleName".equals(field.getName())){
					continue;
				}
				else {
					super.put(new NodeToken(field.getName(),field.get(this),-1));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		newTokensToken = new BranchToken();
	}

	public abstract String getName();	
	public abstract String getSingular();
	public abstract NameParser getNamesParser();
	
	
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
	
	public static ParseList createNew(final String listName, final String singleName){
		return new ParseList(){
			private NameParser name_parser = new NameParser(listName);
			@Override
			public String getName() {
				return listName;
			}

			@Override
			public String getSingular() {
				return singleName;
			}

			@Override
			public NameParser getNamesParser() {
				return name_parser;
			}
		};
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
