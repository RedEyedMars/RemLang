package com.rem.parser;

public class TabbedParser extends RegexParser implements IRule {

	private Parameter<Integer> tabCount = new Parameter<Integer>(0);
	
	public TabbedParser(String name, String listName, String regex){
		super(name,listName,regex);
	}
	
	@Override
	public void setup() {		
	}

	@Override
	public Parameter<?> getParameter(int i) {		
		return tabCount;
	}

	@Override
	public void real_parse(ParseData data) {
		int tabs = tabCount.evaluate();
		int position = data.getPosition();
		
		for(int i=0;i<tabs;++i){
			super.real_parse(data);
			if(!data.isValid()){
				data.setPosition(position);
				return;
			}
		}
	}

}
