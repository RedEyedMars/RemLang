package com.rem.parser;

public class AnyListNameParser extends ConcreteParser {

	public static final IParser parser = new AnyListNameParser();

	@Override
	public void real_parse(ParseData data) {
		if(NameParser.lazyParser!=null){
			NameParser.lazyParser.parse(data);
			return;
		}
		if(data.isDone()){
			data.invalidate();
			return;
		}
		data.invalidate();
		for(String listName:data.getListNames()){
			if(listName.equals("listnames"))continue;
			NameParser nameParser = data.getList(listName).getNamesParser();
			if(nameParser.containsNames()){
				nameParser.parse(data);
				if(data.isValid()){
					return;
				}
			}
		}
		NameParser nameParser = data.getList("listnames").getNamesParser();
		if(nameParser.containsNames()){
			nameParser.parse(data);
			if(data.isValid()){
				return;
			}
		}
	}

}
