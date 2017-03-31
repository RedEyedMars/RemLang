package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseUtil;

public abstract class ConcreteRule extends AddTokenParser implements IRule {

	public ConcreteRule(String name){
		super(name);
	}
	
	@Override
	public void parse(ParseContext data) {
		ParseUtil.currentRule = this;
		ParseUtil.currentParser = this.getName();
		super.parse(data);
	}

}
