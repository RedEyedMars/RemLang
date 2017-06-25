package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class ListNameElementParser extends ConcreteParser{

	private String listName;
	private RegexParser myLazyParser = null;

	public ListNameElementParser(String listName){
		this.listName = listName;
	}

	@Override
	public void real_parse(ParseContext data) {

		if(NameParser.lazyParser!=null){
			if(myLazyParser==null){
				myLazyParser = new RegexParser(NameParser.lazyParser);
				myLazyParser.setName(listName);
			}
			myLazyParser.real_parse(data);
		}
		else {
			if(data.getList(listName)!=null){
				data.getList(listName).getNamesParser().real_parse(data);	
			}

			else {
				System.err.println(data.getLine()+":"+data.getLineNumber(data.getFrontPosition()));
				throw new RuntimeException("ListNameParser:"+listName+" is not recognized listName");
			}
		}
	}

}
