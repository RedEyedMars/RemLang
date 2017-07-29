package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.token.NodeToken;

public class ListNameParser extends ConcreteParser{
	public static final IParser parser = new ListNameParser();

	public void real_parse(ParseContext data){
		if(NameParser.lazyParser!=null){
			NameParser.lazyParser.parse(data);
		}
		else {
			String toExamine = data.get();
			for(String listName:data.getListNames()){

				boolean isValid = true;
				if(listName.length()>toExamine.length()){
					continue;
				}
				for(int i=0;i<listName.length();++i){
					if(listName.charAt(i)!=toExamine.charAt(i)){
						isValid = false;
						break;					
					}
				}
				if(isValid){
					int found = listName.length();
					boolean foundSpace = found>=toExamine.length()||toExamine.charAt(found)=='\n';
					if(!foundSpace){
						char c = toExamine.charAt(found);
						if((c>=48&&c<58)||(c>=65&&c<91)||(c>=97&&c<123)){						
						}
						else {
							foundSpace = true;
							for(;found<toExamine.length()&&(toExamine.charAt(found)==' '||toExamine.charAt(found)=='\t');++found){
							}
						}
					}
					if(foundSpace){
						data.getToken().put(new NodeToken("listnames",data.getFileName(),listName,data.getFrontPosition()));
						data.setFrontPosition(data.getFrontPosition()+found);
						data.validate();
						return;
					}
				}
			}
			data.invalidate();
		}
	}
}
