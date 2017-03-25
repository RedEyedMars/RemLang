package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class OptionalParser extends ConcreteParser implements IParser{

	private IParser subParser;
	public OptionalParser(){
		subParser = null;
	}
	public OptionalParser(IParser initialParser){
		subParser = initialParser;
	}
	public void setParser(IParser newParser){
		subParser = newParser;
	}
	
	@Override
	public void real_parse(ParseContext data) {
		if(subParser==null||data.isDone())return;
		int position = data.getFrontPosition();
		subParser.parse(data);
		if(!data.isValid()){
			data.setFrontPosition(position);
			data.validate();
		}
	}
	
}