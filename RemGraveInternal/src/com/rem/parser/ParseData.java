package com.rem.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParseData {

	private static Map<String,ParseList> lists = new HashMap<String,ParseList>();
	private static IToken currentToken = new BranchToken();
	private static IToken rootToken = currentToken;
	private Map<Integer,Map<IParser,Set<Integer>>> paps = new HashMap<Integer,Map<IParser,Set<Integer>>>();
	private int length;

	private boolean valid = true;
	private int position = 0;
	private String file;
	
	public ParseData(String file){
		this.length = file.length();
		this.file = file;
	}
	
	public boolean isDone() {
		return valid&&position==length;
	}

	public boolean isValid() {
		return valid;
	}

	public void invalidate() {
		valid = false;
	}
	public void validate(){
		valid = true;		
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int newPosition) {
		position = newPosition;
	}
	
	public String get(){
		return file.substring(position);
	}
	
	public String getFile(){
		return file;
	}
	
	public void setPap(IParser parent, int index){
		if(!paps.containsKey(position)){
			paps.put(position,new HashMap<IParser,Set<Integer>>());
		}
		if(!paps.get(position).containsKey(parent)){
			paps.get(position).put(parent, new HashSet<Integer>());
		}
		paps.get(position).get(parent).add(index);
	}
	public void resetPap(int pos, IParser parent, int index){
		Map<IParser,Set<Integer>> pap = paps.get(pos);

		if(pap!=null){
			Set<Integer> set = pap.get(parent);
			if(set!=null){
				set.remove(index);
			}
		}
	}
	public boolean isAtPreviousAccessPoint(IParser parent, int index){
		if(!paps.containsKey(position)){
			paps.put(position,new HashMap<IParser,Set<Integer>>());
		}
		if(!paps.get(position).containsKey(parent)){
			paps.get(position).put(parent, new HashSet<Integer>());
		}
		return paps.get(position).get(parent).contains(index);
	}

	public ParseList getList(String listName) {
		return lists.get(listName);
	}

	public void addList(ParseList list) {
		lists.put(list.getName(), list);
	}

	
	public IToken getToken(){
		return currentToken;
	}
	
	public IToken getRoot(){
		return rootToken;
	}
	
	public void collectTokens(){
		currentToken = currentToken.getParent();
	}

	public IToken addTokenLayer() {
		IToken previousToken = currentToken;
		currentToken = new BranchToken();
		currentToken.setParent(previousToken);
		return currentToken;
	}

	public void accumlateLists(Generator generator) {
		rootToken.accumlateLists(lists);
		if(generator!=null){
			generator.assignListElementNames(lists, rootToken);
		}
	}
	
	public void resetLists(){
		for(String listName:lists.keySet()){
			lists.get(listName).reset();
		}
	}
	
	public void addError(String error) {
		System.err.println(ParseUtil.currentParser+"("+position +"):"+ error);
	}

	public String printPap(IParser parser) {
		return paps.get(position).get(parser).contains(parser)+" at "+position;
	}


}
