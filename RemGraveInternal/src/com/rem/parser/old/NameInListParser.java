package com.rem.parser.old;

import com.rem.parser.ConcreteParser;
import com.rem.parser.IParser;
import com.rem.parser.IToken;
import com.rem.parser.NodeToken;
import com.rem.parser.ParseData;
import com.rem.parser.ParseUtil;
import com.rem.parser.IToken.Id;

public class NameInListParser extends ConcreteParser implements IParser{

	private String listName;

	public NameInListParser(String listName){
		this.listName = listName;
	}

	@Override
	public void real_parse(ParseData data) {

		IToken list = data.getList(listName);
		if(list!=null){
			String toExamine = data.get();
			String found = null;
			for(IToken.Id key:list.keySet()){
				if(ParseUtil.compareFront(toExamine,key.getName())){
					found = key.getName();
					break;
				}
			}
			if(found == null){
				data.invalidate();
			}
			else {
				data.setPosition(data.getPosition()+found.length());
				data.getToken().put(new NodeToken(listName,found));
			}
		}
		else {
			data.addError(listName+" is not a valid list name");
		}
	}
}
