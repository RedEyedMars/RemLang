package com.rem.parser;

import java.lang.reflect.Field;

public abstract class ParseList extends BranchToken{

	private BranchToken newTokensToken;

	public ParseList(){
		super();
		try {
			for(Field field:this.getClass().getDeclaredFields()){
				if("serialVersionUID".equals(field.getName())||
						"parser".equals(field.getName())||
						"name_parser".equals(field.getName())||
						"this$0".equals(field.getName())||
						"val$listName".equals(field.getName())){
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
	
	public static ParseList createNew(final String listName){
		return new ParseList(){
			private NameParser name_parser = new NameParser(listName);
			@Override
			public String getName() {
				return listName;
			}

			@Override
			public String getSingular() {
				return listName.substring(0,listName.length()-1);
			}

			@Override
			public NameParser getNamesParser() {
				return name_parser;
			}
		};
	}
}
