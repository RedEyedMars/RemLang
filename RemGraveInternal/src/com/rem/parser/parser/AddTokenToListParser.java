package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.token.IToken;

public class AddTokenToListParser extends AddTokenParser implements IParser {
	private String listName;
	private String withTokenName = null;
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
	public AddTokenToListParser(IParser initialParser,String initialName, String initialListName, String initialWithTokenName){
		super(initialParser,initialName);
		listName = initialListName;
		withTokenName  = initialWithTokenName;
	}
	@Override
	public void real_parse(ParseContext data) {
		if(subParser==null) return;
		ParseContext newContext = new ParseContext(data);
		newContext.setBackPosition(data.getBackPosition());
		newContext.setFrontPosition(data.getFrontPosition());
		IToken token = newContext.addTokenLayer();
		subParser.parse(newContext);
		newContext.collectTokens();
		if(newContext.isValid()){

			data.getToken().put(new IToken.Key(name,-1,data.getFrontPosition()), token);
			token.setList(listName);
			token.setName(name);
			data.setFrontPosition(newContext.getFrontPosition());
		}
		else {
			data.invalidate();
		}
	}
}