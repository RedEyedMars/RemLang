package com.rem.parser.parser;

import java.util.ArrayList;
import java.util.List;

import com.rem.parser.ParseContext;

public class WithParser extends ConcreteParser{

	private IRule subParser;
	private Argument<?>[] args;
	public WithParser(IRule subParser, Argument<?>... arguments){
		this.subParser = subParser;
		this.args = arguments;
	}
	
	@Override
	public void real_parse(ParseContext data) {
		List<Object> originalValues = new ArrayList<Object>();
		for(int i=0;i<args.length;++i){
			originalValues.add(this.subParser.getParameters()[i].evaluate());
			this.subParser.getParameters()[i].set(args[i].evaluate());
		}
		this.subParser.parse(data);
		for(int i=0;i<originalValues.size();++i){
			this.subParser.getParameters()[i].set(originalValues.get(i));
		}
	}

}
