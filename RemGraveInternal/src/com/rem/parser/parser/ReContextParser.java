package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.token.IToken;

public class ReContextParser extends ConcreteParser{

	private IParser subParser;
	private String tokenName;
	private String contextName;

	public ReContextParser(IParser subParser,  String tokenName, String contextName){
		this.subParser = subParser;
		this.tokenName = tokenName;
		this.contextName = contextName;
	}

	@Override
	public void real_parse(ParseContext data) {
		IToken token = data.getToken().getLast(tokenName);
		if(token!=null){
			ParseContext otherContext = data.getContextFromName(contextName,token.getString());
			if(otherContext == null){
				if(NameParser.lazyParser == null){
					data.invalidate();
					return;
				}
				else {
					otherContext = data.getContextFromNoName(contextName,token.getString());
				}
			}
			subParser.parse(otherContext);
			if(otherContext.isValid()){
				data.validate();
				data.setFrontPosition(otherContext.getFrontPosition());
			}
			else {
				data.invalidate();
			}
		}
		else {
			System.err.println("Error on line "+data.getLineNumber(data.getFrontPosition())+" cannot find "+tokenName);
			data.invalidate();

		}

	}

}
