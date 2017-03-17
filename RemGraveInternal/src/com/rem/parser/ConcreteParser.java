package com.rem.parser;

public abstract class ConcreteParser implements IParser{


	public abstract void real_parse(ParseData data);
	
	protected String name = getClass().getSimpleName();

	@Override
	public void parse(ParseData data) {
		handleMustEnd(data);
		if(ParseUtil.debug){
			debug_parse(data);
		}
		else {
			real_parse(data);
		}
	}
	
	@Override
	public void debug_parse(ParseData data) {
		ParseUtil.debug("verbose", this, "start("+data.getFrontPosition()+")");
		ParseUtil.currentParser = this.getName();
		real_parse(data);
		ParseUtil.debug("verbose", this, "end:"+data.isValid()+"("+data.getFrontPosition()+")");
	}
	
	public void handleMustEnd(ParseData data){
		if(data.mustEnd()){
			data.setMustEnd(false);
		}
	}


	@Override
	public String getName() {
		return name;
	}
	
	
}
