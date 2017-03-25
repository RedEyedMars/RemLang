package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseUtil;

public abstract class ConcreteParser implements IParser{


	public abstract void real_parse(ParseContext data);
	
	protected String name = getClass().getSimpleName();

	@Override
	public void parse(ParseContext data) {
		handleMustEnd(data);
		if(ParseUtil.debug){
			debug_parse(data);
		}
		else {
			real_parse(data);
		}
	}
	
	@Override
	public void debug_parse(ParseContext data) {
		ParseUtil.debug("verbose", this, "start("+data.getFrontPosition()+")");
		ParseUtil.currentParser = this.getName();
		real_parse(data);
		ParseUtil.debug("verbose", this, "end:"+data.isValid()+"("+data.getFrontPosition()+")");
	}
	
	public void handleMustEnd(ParseContext data){
		if(data.mustEnd()){
			data.setMustEnd(false);
		}
	}


	@Override
	public String getName() {
		return name;
	}
	
	
}
