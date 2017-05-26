package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseUtil;
import com.rem.parser.token.IToken;

public class ChoiceParser extends ConcreteListParser implements IParser{
	public static boolean debug = false;
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
		if(isEmpty()||!data.isValid()){
			data.invalidate();
			return;
		}
		ParseContext.AccessPoint accessPoint = data.getAccessPoint();
		ParseContext.AccessSuccess alreadySucceeded = accessPoint.access(this);
		if(alreadySucceeded!=null){
			for(IToken.Key key:alreadySucceeded.getToken().keySet()){
				data.getToken().put(key,alreadySucceeded.getToken().get(key));
			}
			data.validate();
			data.setFrontPosition(alreadySucceeded.getPosition());
			return;
		}
		boolean mustEnd = data.mustEnd();
		if(mustEnd){
			data.setMustEnd(false);
		}
		int position = data.getFrontPosition();
		//System.out.println(ParseUtil.currentRule.getName()+":"+data.getLine());
		IRule rule = ParseUtil.currentRule;
		for(int index=0;index<size();++index){
			IParser parser = get(index);

			alreadySucceeded = accessPoint.access(parser);
			if(alreadySucceeded!=null){
				for(IToken.Key key:alreadySucceeded.getToken().keySet()){
					data.getToken().put(key,alreadySucceeded.getToken().get(key));
				}
				data.validate();
				data.setFrontPosition(alreadySucceeded.getPosition());
				return;
			}
			if(accessPoint.canUse(parser)){
				IToken token = data.addTokenLayer();
				data.validate();
				//System.out.println(rule.getName()+" USE:"+index+":"+parser);
				accessPoint.descend(parser);
				parser.parse(data);
				ParseUtil.currentRule = rule;
				data.collectTokens();
				accessPoint.ascend();
				if(data.isValid()){
					if(mustEnd&&!data.isDone()){
						data.invalidate();
						data.setFrontPosition(position);
						continue;
					}
					accessPoint.succeed(parser,data.getFrontPosition(),token);
					for(IToken.Key key:token.keySet()){
						data.getToken().put(key,token.get(key));
					}
					return;
				}
				//accessPoint.fail(this, index);
			}
			else {
			//	System.out.println(rule.getName()+" DENY"+index);
			}
		}
		data.invalidate();
	}

	@Override
	public String toString(){
		return "Choice";
	}
}
