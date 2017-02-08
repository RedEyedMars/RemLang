package com.rem.parser;


public class ChoiceParser extends ConcreteListParser implements IParser{

	public ChoiceParser(IParser... parsers){
		super();
		for(IParser parser:parsers){
			add(parser);
		}
	}

	@Override
	public void real_parse(ParseData data) {
		if(isEmpty())return;
		data.invalidate();
		int position = data.getPosition();
		for(int index=0;!data.isValid()&&index<size();++index){

			IParser parser = get(index);
			if(size()>1&&data.isAtPreviousAccessPoint(this, index)){
				//ParseUtil.debug("internal","Choice","(lock:"+this+"):"+parser+"::"+data.printPap(parser));
			}
			else {

				data.validate();
				data.setPap(this,index);
				parser.parse(data);
				if(data.isValid()){
					data.resetPap(position,this,index);
				}
			}
		}

	}

	@Override
	public String toString(){
		return "Choice";
	}
}
