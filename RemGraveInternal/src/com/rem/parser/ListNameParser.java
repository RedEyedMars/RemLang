package com.rem.parser;

public class ListNameParser extends ConcreteParser{

	private String listName;

	public ListNameParser(String listName){
		this.listName = listName;
	}

	@Override
	public void real_parse(ParseData data) {

		if(NameParser.lazyParser!=null){
			NameParser.lazyParser.parse(data);
		}
		else {
			data.getList(listName).getNamesParser().parse(data);
		}
	}

}
