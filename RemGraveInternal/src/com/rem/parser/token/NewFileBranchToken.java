package com.rem.parser.token;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseList;

public class NewFileBranchToken extends BranchToken {
	private ParseContext context;
	public NewFileBranchToken(ParseContext parentContext){
		this.context = parentContext;
	}
	@Override
	public ParseContext getContext(ParseContext rootContext){
		return context;
	}
	@Override
	public void accumulateLists(ParseContext data){
		for(IToken.Key key:keys){
			get(key).accumulateLists(context);
		}
		for(String listName:context.getListNames()){
			ParseList fromList = context.getList(listName);
			if(fromList!=null){
				ParseList toList = data.getList(listName);
				if(toList==fromList)continue;
				else if(toList==null){
					data.addList(listName,fromList.getSingular());
					toList = data.getList(listName);
				}
				for(IToken.Key key:fromList.getNewTokens().keySet()){
					toList.put(key, fromList.getNewTokens().get(key));
				}
			}
		}
	}
}
