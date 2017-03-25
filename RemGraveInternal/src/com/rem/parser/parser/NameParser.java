package com.rem.parser.parser;

import java.util.TreeSet;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseList;

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
	private NameParser parent = null;


	public NameParser(String listName, String... parsers) {
		super(listName, listName);
		if(parsers!=null){
			for(int i=0;i<parsers.length;++i){
				this.parsers.add(parsers[i]);
			}			
		}
	}

	@Override
	public void real_parse(ParseContext data){

		if(lazyParser!=null){
			System.out.println(data.getLine());
			lazyParser.parse(data);
			System.out.println(data.isValid());
			if(data.isValid()){
				System.out.println(data.getLine());
			}
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
		if(!data.isValid()&&(parent!=null&&parent.containsNames())){
			data.validate();
			parent.real_parse(data);				
		}
	}

	public void addName(String parser){
		if(!this.parsers.contains(parser)){
			this.parsers.add(parser);
		}
	}

	public boolean containsNames(){
		return !parsers.isEmpty()||(parent!=null&&parent.containsNames());
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

	public void setParent(NameParser newParent) {
		this.parent = newParent;
	}
}
