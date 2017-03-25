package com.rem.parser.parser;

import java.util.ArrayList;
import java.util.List;

import com.rem.parser.ParseContext;

public class TabbedParser extends ConcreteRule {

	private Parameter<Integer> tabCount = new Parameter<Integer>(0);
	private Parameter<?>[] params = new Parameter<?>[]{tabCount};
	private String regex;
	private String original_regex;
	private String name;
	private String listName;
	private List<RegexParser> regexParsers = new ArrayList<RegexParser>();

	public TabbedParser(String name, String listName, String regex){
		super(name);
		this.name = name;
		this.regex = regex;
		this.original_regex = regex;
		this.listName = listName;
		regexParsers.add(
				new RegexParser(name,listName,"("+regex+")*"));
	}

	@Override
	public void setup() {		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parameter<?>[] getParameters() {		
		return params;
	}

	@Override
	public void real_parse(ParseContext data) {
		int tabs = tabCount.evaluate();
		if(tabs==-1){
			tabs=0;
		}
		while(tabs>=regexParsers.size()){			
			regexParsers.add(new RegexParser(name,listName,regex));
			regex = regex + original_regex;
		}
		regexParsers.get(tabs).real_parse(data);
	}	

}
