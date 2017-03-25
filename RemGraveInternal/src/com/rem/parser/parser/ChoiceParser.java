package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseUtil;
import com.rem.parser.token.IToken;

public class ChoiceParser extends ConcreteListParser implements IParser{
	public ChoiceParser(IParser... parsers){
		super();
		for(IParser parser:parsers){
			add(parser);
		}
	}
	
	@Override
	public void handleMustEnd(ParseContext data){
		
	}

	@Override
	public void real_parse(ParseContext data) {
		if(isEmpty())return;
		boolean mustEnd = data.mustEnd();
		if(mustEnd){
			data.setMustEnd(false);
		}
		data.invalidate();
		int position = data.getFrontPosition();
		for(int index=0;!data.isValid()&&index<size();++index){

			IParser parser = get(index);
			if(size()>1&&data.isAtPreviousAccessPoint(this, index)){
				//ParseUtil.debug("internal","Choice","(lock:"+this+"):"+parser+"::"+data.printPap(parser));
			}
			else {
				
				IToken token = data.addTokenLayer();
				data.validate();
				data.setPap(this,index);
				parser.parse(data);				
				data.collectTokens();
				if(data.isValid()){
					data.resetPap(position,this,index);
					if(mustEnd){
						if(!data.isDone()){
							data.invalidate();
							data.setFrontPosition(position);
							continue;
						}
					}
					for(IToken.Key key:token.keySet()){
						data.getToken().put(key,token.get(key));
					}
				}
			}
		}

	}

	@Override
	public String toString(){
		return "Choice";
	}
}
