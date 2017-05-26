package com.rem.parser.parser;


import com.rem.parser.ParseContext;
import com.rem.parser.ParseList;
import com.rem.parser.ParsePattern;
import com.rem.parser.token.NodeToken;

import java.util.HashSet;
import java.util.Set;

public class NameParser extends RegexParser{
	public static RegexParser lazyParser = null;


	private ParsePattern pattern = new ParsePattern();
	private Set<String> added = new HashSet<String>();
	private ParseContext parent = null;


	public NameParser(ParseContext parentContext, String listName) {
		super(listName, listName);
		this.parent = parentContext;
	}

	@Override
	public void real_parse(ParseContext data){

		if(lazyParser!=null){
			new AddTokenParser(lazyParser,name).parse(data);
			return;
		}

		String toExamine = data.get();
		String found = pattern.get(toExamine, 0);
		if(found!=null){
			int foundIndex = found.length();
			boolean foundSpace = foundIndex>=toExamine.length()||toExamine.charAt(foundIndex)=='\n';
			if(!foundSpace){
				char c = toExamine.charAt(foundIndex);
				if((c>=48&&c<58)||(c>=65&&c<91)||(c>=97&&c<123)||(c==95)){						
				}
				else {
					foundSpace = true;
					for(;foundIndex<toExamine.length()&&(toExamine.charAt(foundIndex)==' '||toExamine.charAt(foundIndex)=='\t');++foundIndex){
					}
				}
			}
			if(foundSpace){
				data.getToken().put(new NodeToken(name,data.getFileName(),found,data.getFrontPosition()));
				data.setFrontPosition(data.getFrontPosition()+foundIndex);
				data.validate();
				return;
			}			
		}
		
		if(parent!=null){
			ParseList parentList = parent.getList(name);
			if(parentList!=null){
				parentList.getNamesParser().real_parse(data);
			}
			else {
				data.invalidate();
			}
			return;
		}
		data.invalidate();
	}		


	public void addName(String parser){
		if(!this.added.contains(parser)){
			this.added.add(parser);
			this.pattern.add(parser, 0);
		}
	}

	public void clear(){
		added.clear();
		pattern = new ParsePattern();
	}


	public Set<String> getElements() {
		return added;
	}

	public ParseContext getParent() {
		return parent;
	}

	public void setParent(ParseContext newParent) {
		this.parent = newParent;
	}

}
