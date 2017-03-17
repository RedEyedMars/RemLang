package com.rem.parser;

public class ManyParser extends ConcreteParser implements IParser{

	private IParser subParser;
	public ManyParser(){
		subParser = null;
	}
	public ManyParser(IParser initialParser){
		subParser = initialParser;
	}
	public void setParser(IParser newParser){
		subParser = newParser;
	}
	
	@Override
	public void real_parse(ParseData data) {
		if(subParser==null||data.isDone())return;
		int position = -1;		
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
