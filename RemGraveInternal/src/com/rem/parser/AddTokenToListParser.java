package com.rem.parser;

public class AddTokenToListParser extends AddTokenParser implements IParser {
	private String listName;
	public AddTokenToListParser(String initialListName){
		this(null,IParser.DEFAULT,initialListName);
	}
	public AddTokenToListParser(String initialName, String initialListName){
		this(null,initialName,initialListName);
	}
	public AddTokenToListParser(IParser initialParser, String initialListName){
		this(initialParser,IParser.DEFAULT,initialListName);
	}
	public AddTokenToListParser(IParser initialParser,String initialName, String initialListName){
		super(initialParser,initialName);
		listName = initialListName;
		
	}
	@Override
	public void real_parse(ParseData data) {
		if(subParser==null) return;
		super.real_parse(data);
		if(data.isValid()){
			data.getList(listName).put(new IToken.Id(name),data.getToken().getLast());
		}
	}
}
