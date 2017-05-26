package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.token.IToken;

public class BracedParser extends ConcreteParser implements IParser{
	private IParser subParser;
	private String name;
	private String listName;
	private String open;
	private String close;
	private String escapeOpen;
	private String escapeClose;

	public BracedParser(IParser initialParser, String parameters){
		subParser = initialParser;
		this.setup(parameters);
	}
	public BracedParser(IParser initialParser, String left, String right){
		subParser = initialParser;
		this.open = left;
		this.close = right;
		this.escapeOpen = "\\"+left;
		this.escapeClose = "\\"+right;
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
	public void real_parse(ParseContext data) {
		if(this.open ==  null || this.close == null){
			if(listName!=null){
				setup(data.getList(listName).get(name).getString());
			}
			else {
				data.invalidate();
				System.err.println("BraceParser has no parameters!");
				return;
			}
		}

		if(data.get().startsWith(open)){
			int position = data.getFrontPosition();
			data.setFrontPosition(position+open.length());
			int currentLayer = 1;
			boolean isQuote = false;
			String toExamine = data.get();
			int sectionLength=0;
			int sectionStart = 0;
			if(!"\"".equals(open)){
				for(;sectionStart<toExamine.length();++sectionStart){
					if(toExamine.charAt(sectionStart)!=' '&&toExamine.charAt(sectionStart)!='\t'){
						break;
					}
				}
			}

			for(;sectionLength+(close.length()-1)<toExamine.length();++sectionLength){
				if(sectionLength+2<toExamine.length()&&toExamine.substring(sectionLength,sectionLength+2).equals("\\\"")){
					++sectionLength;
				}
				else if(isQuote&&toExamine.substring(sectionLength,sectionLength+1).equals("\"")){
					isQuote=false;
				}
				else if(sectionLength+2<toExamine.length()&&toExamine.substring(sectionLength,sectionLength+2).equals("\\\\")){
					sectionLength+=1;
				}
				else if(sectionLength+escapeClose.length()<toExamine.length()&&toExamine.substring(sectionLength,sectionLength+escapeClose.length()).equals(escapeClose)){
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
				else if(open.equals("")){					
				}
				else if(sectionLength+escapeOpen.length()<toExamine.length()&&
						toExamine.substring(sectionLength,sectionLength+escapeOpen.length()).equals(escapeOpen)){
					sectionLength+=escapeOpen.length()-1;
				}
				else if(sectionLength+open.length()<toExamine.length()&&
						toExamine.substring(sectionLength,sectionLength+open.length()).equals(open)){
					++currentLayer;
				}
			}
			if(currentLayer>0){				
				data.invalidate();
				return;
			}
			ParseContext newParseData = data.getContextFromPosition();
			newParseData.setFrontPosition(data.getFrontPosition()+sectionStart);
			newParseData.setBackPosition(data.getFrontPosition()+sectionLength);
			newParseData.setMustEnd(true);
			IToken token = newParseData.addTokenLayer();
			subParser.parse(newParseData);
			newParseData.collectTokens();

			//ParseUtil.debug("internal",this,subParser.getClass().getSimpleName()+"{"+newParseData.get()+"}:"+newParseData.isValid());
			if(!newParseData.isDone()){
				data.setFrontPosition(position);
				newParseData.resetAccessPoints();
				newParseData.setFrontPosition(data.getFrontPosition());
				data.invalidate();
			}
			else {
				int found = sectionLength+close.length();
				
				for(;found<toExamine.length();++found){
					if(toExamine.charAt(found)!=' '&&toExamine.charAt(found)!='\t'){
						break;
					}
				}
				data.setFrontPosition(data.getFrontPosition()+found);
				data.validate();
				for(IToken.Key key:token.keySet()){
					data.getToken().put(key,token.get(key));
				}
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
