package com.rem.parser.parser;

import java.util.TreeSet;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseList;

import java.util.Comparator;
import java.util.Set;

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
	private ParseContext parent = null;


	public NameParser(ParseContext parentContext, String listName, String... parsers) {
		super(listName, listName);
		if(parsers!=null){
			for(int i=0;i<parsers.length;++i){
				this.parsers.add(parsers[i]);
			}			
		}
		this.parent = parentContext;
	}

	@Override
	public void real_parse(ParseContext data){

		if(lazyParser!=null){
			lazyParser.parse(data);
			return;
		}
		else if(!containsNames()){
			data.invalidate();
			return;
		}
		else if(isLazy&&lazyParser==null){
			solidify();
			isLazy = false;			
		}
		if(!parsers.isEmpty()){
			super.real_parse(data);
		}
		else {
			data.invalidate();
		}
		if(!data.isValid()&&parent!=null){
			ParseList parentList = parent.getList(name);
			if(parentList!=null&&parentList.getNamesParser().containsNames()){
				data.validate();
				parentList.getNamesParser().real_parse(data);				
			}
		}
	}

	public void addName(String parser){
		if(!this.parsers.contains(parser)){
			this.parsers.add(parser);
		}
	}

	public boolean containsNames(){
		if(!parsers.isEmpty()){
			return true;
		}
		else if(parent!=null){
			ParseList parentList = parent.getList(name);
			if(parentList!=null&&parentList.getNamesParser().containsNames()){
				return true;			
			}
		}
		return false;
	}

	public void solidify(){
		if(parsers==null||parsers.isEmpty()){
			setup("");
			return;
		}
		StringBuilder builder = new StringBuilder();
		String pipe = "";
		for(String parser:parsers){
			builder.append(pipe);
			builder.append(parser);
			builder.append("\\b");
			pipe = "|";
		}
		setup(builder.toString());
	}

	public void clear(){
		
		parsers.clear();
	}


	public Set<String> getElements() {
		return parsers;
	}
}
