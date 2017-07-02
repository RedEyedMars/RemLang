package com.rem.parser.token;

import java.util.List;
import java.util.Map;

import com.rem.parser.ParseContext;

public interface IToken extends Map<IToken.Key,IToken>{

	public Object getValue();
	public String getString();
	public IToken getParent();
	public String getFileName();
	public void setParent(IToken parent);
	public void put(NodeToken nodeToken);
	public IToken getLast();
	public IToken getLast(String tokenName);
	public List<IToken> getAll(String key);
	public List<IToken> getAllSafely(String key);
	public void accumulateLists(ParseContext data);
	public void setName(String name);
	public void setList(String name);
	public ParseContext getContext(ParseContext rootContext);
	public void print();
	public void printShort();

	public int getPosition();
	public int getLineNumber(String file);


	public static class Key {
		private String name;
		private int index;
		private int position;

		public Key(String name, int index, int position){
			this.name = name;
			this.index = index;
		}
		public void setIndex(int index){
			this.index = index;
		}
		public int getIndex(){
			return index;
		}
		public String getName(){
			return name;
		}
		public int getPosition() {
			return position;
		}
	}





}
