package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class ChainParser extends ConcreteListParser implements IParser{

	public ChainParser(IParser... parsers){
		super(parsers.length);
		for(IParser parser:parsers){
			add(parser);
		}
	}
	@Override
	public void real_parse(ParseContext data) {
		if(isEmpty()){
			data.invalidate();
			return;
		}
		int index = 0;
		int position = data.getFrontPosition();
		get(index++).parse(data);
		while(data.isValid()&&index<size()){
			get(index++).parse(data);
		}
		if(!data.isValid()){
			data.setFrontPosition(position);
		}
	}
}
