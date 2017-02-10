package com.rem.parser;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class NameParser extends RegexParser{
	public static IParser lazyParser = null;

	private TreeSet<String> parsers = new TreeSet<String>(new Comparator<String>(){
		@Override
		public int compare(String o1, String o2) {
			if(o1.length()==o2.length()&&!o1.equals(o2)){
				return 1;
			}
			else return o2.length()-o1.length();
		}});
	private boolean isLazy = true;
	
	
	public NameParser(String listName, String... parsers) {
		super(listName, listName);
		if(parsers!=null){
			for(int i=0;i<parsers.length;++i){
				this.parsers.add(parsers[i]);
			}			
		}
	}
	
	@Override
	public void parse(ParseData data){
		if(lazyParser!=null){
			lazyParser.parse(data);
		}
		else if(isLazy&&lazyParser==null){
			solidify();
			isLazy = false;
			super.parse(data);
		}
		else {
			super.parse(data);
		}		
	}
	
	public void addName(String parser){
		this.parsers.add(parser);
	}

	
	public void solidify(){
		if(parsers==null||parsers.isEmpty()){
			setup("");
		}
		StringBuilder builder = new StringBuilder();
		String pipe = "";
		System.out.print(name+":");
		for(String parser:parsers){
			builder.append(pipe);
			builder.append(parser);
			builder.append("\\b");
			pipe = "|";
		}
		System.out.println(builder.toString());
		setup(builder.toString());
	}
}
