package com.rem.parser.parser;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.rem.parser.ParseContext;
import com.rem.parser.token.NodeToken;

public class ListNameParser extends ConcreteParser{
	public static final IParser parser = new ListNameParser();
	private Set<String> listnames;
	
	public void real_parse(ParseContext data){

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
			listnames.addAll(data.getListNames());
			listnames.add("listnames");
		}
		else {
			if(data.getListNames().size()!=listnames.size()){
				listnames.clear();
				listnames.addAll(data.getListNames());
				listnames.add("listnames");
			}
		}
		String toExamine = data.get();
		//System.out.println("::"+data.getLine());
		for(String listName:listnames){

			boolean isValid = true;
			if(listName.length()>toExamine.length()){
				continue;
			}
			for(int i=0;i<listName.length();++i){
				if(listName.charAt(i)!=toExamine.charAt(i)){
					isValid = false;
					break;					
				}
			}
			if(isValid){
				int found = listName.length();
				boolean foundSpace = found>=toExamine.length()||toExamine.charAt(found)=='\n';
				for(;found<toExamine.length();++found){
					if(toExamine.charAt(found)!=' '&&toExamine.charAt(found)!='\t'){
						break;
					}
					foundSpace = true;
				}
				if(foundSpace){
					data.getToken().put(new NodeToken("listnames",data.getFileName(),listName,data.getFrontPosition()));
					data.setFrontPosition(data.getFrontPosition()+found);
					data.validate();
					return;
				}
			}
		}
		data.invalidate();
	}
}
