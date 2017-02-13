package com.rem.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BranchToken implements IToken {
	private Map<String,List<IToken>> tokens = new HashMap<String,List<IToken>>();
	private LinkedListSet<IToken.Id> keys = new LinkedListSet<IToken.Id>();
	private IToken parent;
	private String listName = null;
	private String name;

	@Override
	public Object getValue(){
		return keys.isEmpty()?"empty":get(keys.getFirst()).getValue();
	}

	@Override
	public String getString(){
		return (String)getValue();
	}

	@Override
	public IToken getParent(){
		return parent;
	}

	@Override
	public void setParent(IToken parent){
		this.parent = parent;
	}

	@Override
	public IToken getLast(){
		return tokens.get(keys.getLast().getName()).get(keys.getLast().getIndex());
	}

	@Override
	public List<IToken> getAll(String key){
		return tokens.get(key);
	}

	@Override
	public void accumlateLists(Map<String,ParseList> listMap){
		if(listName!=null){
			if(!listMap.containsKey(listName)){
				listMap.put(listName, ParseList.createNew(listName));
			}
			listMap.get(listName).put(new IToken.Id(name),this);
			listName = null;			
		}
		for(IToken.Id key:keys){
			get(key).accumlateLists(listMap);
		}
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}

	@Override
	public void setList(String name){
		this.listName  = name;
	}

	@Override
	public void clear() {
		tokens.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		if(key instanceof String){
			return tokens.containsKey(key);
		}
		else if(key instanceof IToken.Id){
			return tokens.containsKey(((IToken.Id)key).getName());
		}
		else return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return tokens.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<IToken.Id, IToken>> entrySet() {
		return null;
	}

	@Override
	public IToken get(Object key) {
		if(key instanceof String){

			String id = (String)key;

			if(tokens.containsKey(id)&&tokens.get(id).size()==1){
				return tokens.get(id).get(0);
			}
			else return null;
		}
		else {
			IToken.Id id = (IToken.Id)key;
			return tokens.get(id.getName()).get(id.getIndex());
		}
	}

	@Override
	public boolean isEmpty() {
		return tokens.isEmpty();
	}

	@Override
	public Set<IToken.Id> keySet() {
		return keys;
	}

	@Override
	public IToken put(IToken.Id key, IToken value) {		
		value.setParent(this);
		if(!tokens.containsKey(key.getName())){
			tokens.put(key.getName(), new ArrayList<IToken>());
		}
		key.setIndex(tokens.get(key.getName()).size());
		tokens.get(key.getName()).add(value);		
		keys.add(key);
		return null;
	}

	@Override
	public void put(NodeToken value){
		value.setParent(this);
		String name = value.getName();
		if(!tokens.containsKey(name)){
			tokens.put(name, new ArrayList<IToken>());
		}

		IToken.Id key = new IToken.Id(name,tokens.get(name).size());
		tokens.get(name).add(value);		
		keys.add(key);
	}

	@Override
	public void putAll(Map<? extends IToken.Id, ? extends IToken> m) {
		for(IToken.Id key: m.keySet()){
			put(key,m.get(key));
		}
	}

	@Override
	public IToken remove(Object key) {
		IToken.Id id = (IToken.Id)key;
		return tokens.get(id.getName()).remove(id.getIndex());
	}

	@Override
	public int size() {
		return tokens.size();
	}

	@Override
	public Collection<IToken> values() {
		List<IToken> all = new ArrayList<IToken>();
		for(IToken.Id key:keys){
			all.add(tokens.get(key.getName()).get(key.getIndex()));
		}
		return all;
	}


	public static class LinkedListSet <Type> extends LinkedList<Type> implements Set<Type>{
		private static final long serialVersionUID = 2525203688305986154L;

	}
}
