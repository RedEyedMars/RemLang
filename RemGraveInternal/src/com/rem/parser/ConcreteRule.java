package com.rem.parser;

public abstract class ConcreteRule extends AddTokenParser implements IRule {

	public ConcreteRule(String name){
		super(name);
	}
	
	@Override
	public void parse(ParseData data) {
		data.setContextParameters(getParameters());
		ParseUtil.currentParser = this.getName();
		super.parse(data);
	}

}
