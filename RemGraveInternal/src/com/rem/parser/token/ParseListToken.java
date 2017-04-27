package com.rem.parser.token;

import java.util.ArrayList;

public class ParseListToken extends BranchToken{


	@Override
	public IToken put(IToken.Key key, IToken value) {	
		if(!tokens.containsKey(key.getName())){
			tokens.put(key.getName(), new ArrayList<IToken>());
		}
		key.setIndex(tokens.get(key.getName()).size());
		tokens.get(key.getName()).add(value);		
		keys.add(key);
		return null;
	}

	@Override
	public void put(NodeToken value){
		String name = value.getName();
		if(!tokens.containsKey(name)){
			tokens.put(name, new ArrayList<IToken>());
		}

		IToken.Key key = new IToken.Key(name,tokens.get(name).size(),value.getPosition());
		tokens.get(name).add(value);		
		keys.add(key);
	}


}
