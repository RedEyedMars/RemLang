package com.rem.parser;

import java.util.List;
import java.util.Map;

public interface IToken extends Map<IToken.Id,IToken>{

	public Object getValue();
	public String getString();
	public IToken getParent();
	public void setParent(IToken parent);
	public void put(NodeToken nodeToken);
	public IToken getLast();
	public List<IToken> getAll(String key);
	public void accumlateLists(Map<String,ParseList> listMap);
	public void setName(String name);
	public void setList(String name);


	public static class Id {
		private String name;
		private int index;
		public Id(String name){
			this.name = name;
		}
		public Id(String name, int index){
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
	}




}
