package com.rem.parser.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseProfiler;
import com.rem.parser.token.NodeToken;

public class RegexParser extends ConcreteParser implements IParser{

	private String listName;
	protected String name;
	protected Pattern pattern;
	private String regex = "";
	private boolean fail = false;

	public RegexParser(String name, String regex){
		this.name = name;
		this.regex = regex;
		setup(regex);		
	}
	public RegexParser(String regex){
		this(IParser.DEFAULT,regex);
	}
	
	public RegexParser(String name, String listName, String regex) {
		this(name,listName);
		setup(regex);		
	}
	public RegexParser(RegexParser otherParser) {
		listName = otherParser.listName;
		name = otherParser.name;
		pattern = otherParser.pattern;
		regex = otherParser.regex;
	}
	protected void setup(String regex){

		this.regex = regex;
		if("".equals(regex)){
			fail = true;
		}
		else if(regex.contains(" ")||regex.contains("\\t")||regex.contains("\t")){
			this.pattern = Pattern.compile("("+regex+")([ \\t]*)",Pattern.DOTALL);
		}
		else if(regex.contains("\\n")||regex.contains("\n")){
			this.pattern = Pattern.compile("("+regex+")",Pattern.DOTALL);
		}
		else {
			this.pattern = Pattern.compile("("+regex+")([ \\t]*)",Pattern.DOTALL);
		}
	}
	@Override
	public void real_parse(ParseContext data) {
		if(fail){
			data.invalidate();
			data.addError("Tried to parse an empty regex");
			return;
		}
		if(this.pattern ==  null){
			if(listName!=null){
				setup((data.getList(listName).get(name).getString()));
			}
			else {
				data.invalidate();
				return;
			}
		}
		Matcher matcher;
		if(ParseProfiler.running){
			ParseProfiler.open(data.getFileName(), "MATCHER:"+pattern, System.nanoTime());
			matcher = this.pattern.matcher(data.get());
			ParseProfiler.close(data.getFileName(), "MATCHER:"+pattern, System.nanoTime());
		}
		else {
			matcher = this.pattern.matcher(data.get());
		}
		if(matcher.lookingAt()){

			ParseProfiler.open(data.getFileName(), "REGEX:"+pattern, System.nanoTime());
			data.getToken().put(new NodeToken(name,data.getFileName(),matcher.group(1),data.getFrontPosition()));
			data.setFrontPosition(data.getFrontPosition()+matcher.end(matcher.groupCount()));
			data.validate();
			ParseProfiler.close(data.getFileName(), "REGEX:"+pattern, System.nanoTime());
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

	public void setName(String name){
		this.name = name;
	}
	public String getPattern(){
		return pattern==null?"pattern is null":regex;
	}

	@Override
	public boolean isTerminalParser(){
		return true;
	}
}
