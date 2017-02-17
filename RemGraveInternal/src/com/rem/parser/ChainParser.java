package com.rem.parser;

public class ChainParser extends ConcreteListParser implements IParser{

	public ChainParser(IParser... parsers){
		super(parsers.length);
		for(IParser parser:parsers){
			add(parser);
		}
	}
	@Override
	public void real_parse(ParseData data) {
		if(isEmpty())return;
		if(data.mustEnd()){
			data.setMustEnd(false);
		}
		int index = 0;
		int position = data.getPosition();
		get(index++).parse(data);
		while(data.isValid()&&index<size()){
			get(index++).parse(data);
		}
		if(!data.isValid()){
			data.setPosition(position);
		}
	}
}
