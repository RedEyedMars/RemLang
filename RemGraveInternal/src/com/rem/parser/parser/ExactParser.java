package com.rem.parser.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rem.parser.ParseContext;
import com.rem.parser.token.NodeToken;

public class ExactParser extends ConcreteParser {

	private String listName;
	protected String name;
	protected String pattern;
	protected boolean fail;

	public ExactParser(String name, String listName){
		this.name = name;
		this.listName = listName;
	}
	public ExactParser(String listName){
		this(IParser.DEFAULT,listName);
	}
	
	public ExactParser(String name, String listName, String regex) {
		this(name,listName);
		setup(regex);		
	}
	protected void setup(String regex){
		if("".equals(regex)){
			fail = true;
		}
		this.pattern = regex;
	}
	@Override
	public void real_parse(ParseContext data) {
		if(fail){
			data.invalidate();
			data.addError("Tried to parse an empty regex");
			return;
		}
		if(this.pattern ==  null){
			setup((data.getList(listName).get(name).getString()));
		}

		String toExamine = data.get();
		if(toExamine.startsWith(pattern)){
			data.getToken().put(new NodeToken(name,pattern,data.getFrontPosition()));
			int found = pattern.length();
			for(;found<toExamine.length();++found){
				if(toExamine.charAt(found)!=' '&&toExamine.charAt(found)!='\t'){
					break;
				}
			}
			data.setFrontPosition(data.getFrontPosition()+found);
			data.validate();
		}
		else {
			data.invalidate();
			return;
		}
	}
	
	@Override
	public String getName(){
		return name;
	}
	
}
