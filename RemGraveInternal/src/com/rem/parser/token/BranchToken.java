package com.rem.parser.token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseList;

public class BranchToken implements IToken {
	private Map<String,List<IToken>> tokens = new HashMap<String,List<IToken>>();
	protected LinkedListSet<IToken.Key> keys = new LinkedListSet<IToken.Key>();
	private IToken parent;
	protected String listName = null;
	protected String name;

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

	public int getLineNumber(String file){
		if(keys.size()>0){
			return get(keys.get(0)).getLineNumber(file);
		}
		else return -1;
	}

	@Override
	public void accumulateLists(ParseContext data){
		if(listName!=null){
			data.addList(listName);
			data.getList(listName).put(new IToken.Key(name,-1,0),this);
			listName = null;
			int position = getPosition();
			if(position>-1){
				data = data.getContextFromPosition(position);
			}
		}
		for(IToken.Key key:keys){
			get(key).accumulateLists(data);
		}
	}

	public int getPosition(){
		if(!keys.isEmpty()){
			return tokens.get(keys.get(0).getName()).get(0).getPosition();
		}
		return -1;
	}
	public String getFileName(){
		if(!keys.isEmpty()){
			return tokens.get(keys.get(0).getName()).get(0).getFileName();
		}
		return "none";
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
		else if(key instanceof IToken.Key){
			return tokens.containsKey(((IToken.Key)key).getName());
		}
		else return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return tokens.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<IToken.Key, IToken>> entrySet() {
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
			IToken.Key id = (IToken.Key)key;
			return tokens.get(id.getName()).get(id.getIndex());
		}
	}

	@Override
	public boolean isEmpty() {
		return tokens.isEmpty();
	}

	@Override
	public Set<IToken.Key> keySet() {
		return keys;
	}

	@Override
	public IToken put(IToken.Key key, IToken value) {		
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

		IToken.Key key = new IToken.Key(name,tokens.get(name).size(),value.getPosition());
		tokens.get(name).add(value);		
		keys.add(key);
	}

	@Override
	public void putAll(Map<? extends IToken.Key, ? extends IToken> m) {
		for(IToken.Key key: m.keySet()){
			put(key,m.get(key));
		}
	}

	@Override
	public IToken remove(Object key) {
		IToken.Key id = (IToken.Key)key;
		this.keys.remove(key);
		return tokens.get(id.getName()).remove(id.getIndex());
	}

	@Override
	public int size() {
		return tokens.size();
	}

	@Override
	public Collection<IToken> values() {
		List<IToken> all = new ArrayList<IToken>();
		for(IToken.Key key:keys){
			all.add(tokens.get(key.getName()).get(key.getIndex()));
		}
		return all;
	}


	public static class LinkedListSet <Type> extends LinkedList<Type> implements Set<Type>{
		private static final long serialVersionUID = 2525203688305986154L;

	}
}
