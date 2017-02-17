package com.rem.parser;

public class AddTokenParser extends ConcreteParser implements IParser {
	protected IParser subParser;
	protected String name;
	private boolean silent;
	public AddTokenParser(){
		this(null,IParser.DEFAULT);
	}
	public AddTokenParser(String initialName){
		this(null,initialName);
	}
	public AddTokenParser(IParser initialParser){
		this(initialParser,IParser.DEFAULT);
	}
	public AddTokenParser(IParser initialParser,String initialName){
		subParser = initialParser;
		name = initialName;

	}
	public void set(IParser newParser){
		subParser = newParser;
	}
	public void isSilent(boolean silence){
		this.silent = silence;
	}

	@Override
	public void real_parse(ParseData data) {
		if(subParser==null) return;
		if(silent){
			subParser.parse(data);
		}
		else {
			int position = data.getPosition();
			IToken token = data.addTokenLayer();
			subParser.parse(data);
			data.collectTokens();
			if(data.isValid()){
				ParseUtil.debug("internal|addtoken",this,"("+ParseUtil.currentParser+"):"+name+":"+token.getValue());
				//System.out.println("("+ParseUtil.currentParser+"):"+name+":"+token);
				data.getToken().put(new IToken.Key(name,-1,position), token);
			}
		}
	}
}
