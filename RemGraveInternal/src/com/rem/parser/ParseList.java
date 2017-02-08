package com.rem.parser;

import java.lang.reflect.Field;

public abstract class ParseList extends BranchToken{

	private BranchToken newTokensToken;

	public ParseList(){
		super();
		try {
			for(Field field:this.getClass().getDeclaredFields()){
				if("serialVersionUID".equals(field.getName())||"parser".equals(field.getName())||"name_parser".equals(field.getName())){
					continue;
				}
				else {
					super.put(new NodeToken(field.getName(),field.get(this)));
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
	
	@Override
	public IToken put(IToken.Id key,IToken value){
		return newTokensToken.put(key, value);
	}
	
	public IToken getNewTokens(){
		return newTokensToken;
	}
}
