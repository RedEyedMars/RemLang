package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class MultipleParser extends ConcreteParser implements IParser{

	private IParser subParser;
	public MultipleParser(){
		subParser = null;
	}
	public MultipleParser(IParser initialParser){
		subParser = initialParser;
	}
	public void setParser(IParser newParser){
		subParser = newParser;
	}
	
	@Override
	public void real_parse(ParseContext data) {
		if(subParser==null)return;
		int position = data.getFrontPosition();
		subParser.parse(data);
		if(!data.isValid()){
			data.setFrontPosition(position);
			return;
		}
		while(!data.isDone()){
			position = data.getFrontPosition();
			subParser.parse(data);
			if(!data.isValid()){
				data.setFrontPosition(position);
				data.validate();
				break;
			}
		}
	}
}
