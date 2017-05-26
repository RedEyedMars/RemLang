package com.rem.parser;

public class ParsePattern {

	private ParsePattern[] nexts = new ParsePattern[128];
	private String returnOnEnd = null;
	public String get(String input,int index){
		if(index>=input.length()){
			return returnOnEnd;
		}
		ParsePattern next = nexts[input.charAt(index)];
		if(next!=null){
			String result = next.get(input, index+1);
			if(result!=null){
				return result;
			}
			else {				
				return returnOnEnd;			
			}
		}
		return returnOnEnd;
	}
	public void add(String newPattern,int index){
		if(index>=newPattern.length()){
			returnOnEnd = newPattern;
			return;
		}
		int c = newPattern.charAt(index);
		if(nexts[c]==null){
			nexts[c] = new ParsePattern();
		}
		nexts[c].add(newPattern, index+1);
	}
}
