package com.rem.parser.parser;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.rem.parser.ParseContext;
import com.rem.parser.token.NodeToken;

public class AnyListNameParser extends ConcreteParser {

	public static final IParser parser = new AnyListNameParser();

	private Set<String> listnames;

	@Override
	public void real_parse(ParseContext data) {
		if(NameParser.lazyParser!=null){
			NameParser.lazyParser.parse(data);
			return;
		}
		if(data.isDone()){
			data.invalidate();
			return;
		}
		data.invalidate();
		for(String listName:data.getListNames()){
			NameParser nameParser = data.getList(listName).getNamesParser();
			if(nameParser.containsNames()){
				nameParser.parse(data);
				if(data.isValid()){
					return;
				}
			}
		}


		if(listnames==null){
			listnames = new TreeSet<String>(new Comparator<String>(){
				@Override
				public int compare(String o1, String o2) {
					if(o1.length()==o2.length()&&!o1.equals(o2)){
						return 1;
					}
					else return o2.length()-o1.length();
				}}
					);
			for(String listName:data.getListNames()){
				//System.out.println(">>>"+data.getList(listName).getSingular());
				listnames.add(data.getList(listName).getSingular());
			}
		}
		else {
			if(data.getListNames().size()!=listnames.size()){
				listnames.clear();
				listnames.addAll(data.getListNames());
			}
		}
		String toExamine = data.get();
		//System.out.println("::"+data.getLine());
		for(String listName:listnames){
			boolean isValid = true;
			for(int i=0;i<listName.length()&&i<toExamine.length();++i){
				if(listName.charAt(i)!=toExamine.charAt(i)){
					isValid = false;
					break;					
				}
			}
			if(isValid){
				int found = listName.length();
				for(;found<toExamine.length();++found){
					if(toExamine.charAt(found)!=' '&&toExamine.charAt(found)!='\t'){
						break;
					}
				}
				data.getToken().put(new NodeToken("listnames",listName,data.getFrontPosition()));
				data.setFrontPosition(data.getFrontPosition()+found);
				data.validate();
				return;
			}
		}
		data.invalidate();
	}

}
