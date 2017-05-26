package com.rem.parser.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.rem.parser.ParseContext;

public class TabbedParser extends ConcreteRule {
	
	public static boolean debug_flag = false;

	private String original_regex;
	private String name;
	private String listName;
	private Map<String,LinkedList<ExactParser>> regexParsers = new HashMap<String,LinkedList<ExactParser>>();

	public TabbedParser(String name, String listName, String regex){
		super(name);
		this.name = name;
		this.original_regex = regex;
		this.listName = listName;
	}

	@Override
	public void setup() {		
	}
	@Override
	public void real_parse(ParseContext data) {
		int tabs = (Integer) data.getParameter(0);
		if(tabs==-1){
			tabs=0;
		}
		String fileName = data.getFileName();
		LinkedList<ExactParser> parsers;
		if(!regexParsers.containsKey(fileName)){
			parsers = new LinkedList<ExactParser>();
			regexParsers.put(fileName, parsers);
			parsers.add(
					new ExactParser(name,listName,""));
		}
		else {
			 parsers = regexParsers.get(fileName);
		}
		while(tabs>=parsers.size()){			
			parsers.add(new ExactParser(name,listName,parsers.getLast().getPattern()+original_regex));
		}
		parsers.get(tabs).real_parse(data);
	}	

}
