package com.rem.parser;

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
	public void real_parse(ParseData data) {
		if(subParser==null)return;
		int position = data.getPosition();
		subParser.parse(data);
		if(!data.isValid()){
			data.setPosition(position);
			return;
		}
		while(!data.isDone()){
			position = data.getPosition();
			subParser.parse(data);
			if(!data.isValid()){
				data.setPosition(position);
				data.validate();
				break;
			}
		}
	}
}
