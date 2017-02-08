package com.rem.parser;

public class BracedParser extends ConcreteParser implements IParser{
	private IParser subParser;
	private String name;
	private String listName;
	private String open;
	private String close;
	private String escapeOpen;
	private String escapeClose;
	public BracedParser(IParser initialParser, String name, String listName){
		subParser = initialParser;
		this.name = name;
		this.listName = listName;
	}
	public BracedParser(IParser initialParser, String name, String listName, String parameters){
		subParser = initialParser;
		this.name = name;
		this.listName = listName;
		this.setup(parameters);
	}
	private void setup(String parameters){
		String[] split = parameters.split(",");
		this.open = split[0];
		this.close = split[1];
		this.escapeOpen = "\\"+split[0];
		this.escapeClose = "\\"+split[1];
	}
	@Override
	public void real_parse(ParseData data) {
		if(this.open ==  null || this.close == null){
			setup(data.getList(listName).get(name).getString());
		}

		if(data.get().startsWith(open)){
			int position = data.getPosition();
			data.setPosition(position+open.length());
			int currentLayer = 1;
			boolean isQuote = false;
			String toExamine = data.get();
			int sectionLength=0;
			for(;sectionLength+(close.length()-1)<toExamine.length();++sectionLength){
				if(toExamine.substring(sectionLength,sectionLength+2).equals("\\\"")){
					++sectionLength;
				}
				else if(isQuote&&toExamine.substring(sectionLength,sectionLength+1).equals("\"")){
					isQuote=false;
				}
				else if(toExamine.substring(sectionLength,sectionLength+escapeClose.length()).equals(escapeClose)){
					sectionLength+=escapeClose.length()-1;
				}				
				else if(toExamine.substring(sectionLength,sectionLength+close.length()).equals(close)){
					--currentLayer;
					if(currentLayer==0){
						break;
					}
				}
				else if(toExamine.substring(sectionLength,sectionLength+1).equals("\"")){
					isQuote=true;
				}
				else if(toExamine.substring(sectionLength,sectionLength+escapeOpen.length()).equals(escapeOpen)){
					sectionLength+=escapeOpen.length()-1;
				}
				else if(toExamine.substring(sectionLength,sectionLength+open.length()).equals(open)){
					++currentLayer;
				}
			}
			if(currentLayer>0){
				data.invalidate();
				return;
			}
			ParseData newParseData = new ParseData(toExamine.substring(0,sectionLength));
			subParser.parse(newParseData);

			ParseUtil.debug("internal",this,subParser.getClass().getSimpleName()+"{"+newParseData.get()+"}:"+newParseData.isValid());
			if(!newParseData.isDone()){
				data.setPosition(position);
				data.invalidate();
			}
			else {
				data.setPosition(data.getPosition()+sectionLength+close.length());
			}
		}
		else {
			data.invalidate();
		}
	}
	
	@Override
	public String getName(){
		return name;
	}
}
