package com.rem.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser extends ConcreteParser implements IParser{

	private String listName;
	protected String name;
	protected Pattern pattern;
	private boolean fail = false;

	public RegexParser(String name, String listName){
		this.name = name;
		this.listName = listName;
	}
	public RegexParser(String listName){
		this(IParser.DEFAULT,listName);
	}
	
	public RegexParser(String name, String listName, String regex) {
		this(name,listName);
		setup(regex);		
	}
	protected void setup(String regex){
		if("".equals(regex)){
			fail = true;
		}
		else {
			this.pattern = Pattern.compile("("+regex+").*",Pattern.DOTALL);
		}
	}
	@Override
	public void real_parse(ParseData data) {
		if(fail){
			data.invalidate();
			data.addError("Tried to parse an empty regex");
			return;
		}
		if(this.pattern ==  null){
			setup((data.getList(listName).get(name).getString()));
		}
		Matcher matcher = this.pattern.matcher(data.get());
		if(matcher.matches()){
			data.getToken().put(new NodeToken(name,matcher.group(1),data.getPosition()));
			data.setPosition(data.getPosition()+matcher.end(1));
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
	
	public String getPattern(){
		return pattern==null?"pattern is null":pattern.pattern();
	}

}
