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
		Object[] values = new Object[args.length];
		for(int i=0;i<args.length;++i){
			values[i] = args[i].evaluate(data);
		}
		if(TabbedParser.debug_flag){
			System.out.println("with>"+data.getFileName());
		data.pushParameters(values);
		this.subParser.parse(data);
		data.popParameters();
		System.out.println("with<"+data.getFileName());
		}
		else {
			data.pushParameters(values);
			this.subParser.parse(data);
			data.popParameters();
		}
	}

}
