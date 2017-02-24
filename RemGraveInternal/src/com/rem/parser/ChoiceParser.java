package com.rem.parser;


public class ChoiceParser extends ConcreteListParser implements IParser{

	protected boolean flag = false;

	public ChoiceParser(IParser... parsers){
		super();
		for(IParser parser:parsers){
			add(parser);
		}
	}
	
	@Override
	public void handleMustEnd(ParseData data){
		
	}

	@Override
	public void real_parse(ParseData data) {
		if(isEmpty())return;
		boolean mustEnd = data.mustEnd();
		if(mustEnd){
			data.setMustEnd(false);
		}
		data.invalidate();
		int position = data.getPosition();
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
				if(flag )System.out.println(parser.getName()+":"+data.isValid());
				if(data.isValid()){
					data.resetPap(position,this,index);
					if(mustEnd){
						if(!data.isDone()){
							data.invalidate();
							data.setPosition(position);
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
