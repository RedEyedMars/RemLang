package com.rem.parser.old;

import com.rem.parser.ConcreteParser;
import com.rem.parser.IParser;
import com.rem.parser.IToken;
import com.rem.parser.NodeToken;
import com.rem.parser.ParseData;
import com.rem.parser.ParseList;
import com.rem.parser.ParseUtil;
import com.rem.parser.IToken.Id;

public class ListNameParser extends ConcreteParser implements IParser{

	public ListNameParser(){
	}

	@Override
	public void real_parse(ParseData data) {

		IToken list = data.getList("listnames");
		if(list!=null){
			String toExamine = data.get();
			ParseList found = null;
			for(IToken.Id key:list.keySet()){
				found = (data.getList(key.getName()));
				if(ParseUtil.compareFront(toExamine,found.getSingular())){
					break;
				}
				else {
					found = null;
				}
			}
			if(found == null){
				data.invalidate();
			}
			else {
				data.setPosition(data.getPosition()+found.getSingular().length());
				data.getToken().put(new NodeToken(found.getSingular(),found));
			}
		}
		else {
			data.addError("listnames is not a valid list name");
		}
	}
}
