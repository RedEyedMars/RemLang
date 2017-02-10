package com.rem.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NodeToken implements IToken{

	private String name;
	private Object value;
	private IToken parent;
	private String listName = null;
	public NodeToken(Object obj){
		this.value = obj;
	}
	public NodeToken(String name,Object obj){
		this.name = name;
		this.value = obj;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setList(String name){
		this.listName  = name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public Object getValue(){
		return value;
	}

	@Override
	public String getString(){
		return (String)getValue();
	}

	@Override
	public void setParent(IToken parent){
		this.parent = parent;
	}
	@Override
	public IToken getParent(){
		return parent;
	}
	
	@Override
	public IToken getLast(){
		return null;
	}

	@Override
	public void accumlateLists(Map<String,ParseList> listMap){
		if(listName!=null){
			listMap.get(listName).put(this);
			listName = null;
		}
	}
	@Override
	public List<IToken> getAll(String key){
		return new ArrayList<IToken>();
	}
	
	@Override
	public void clear() {
		value = null;
	}

	@Override
	public boolean containsKey(Object key) {
		return name.equals(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.value == value;
	}

	@Override
	public Set<Map.Entry<IToken.Id, IToken>> entrySet() {
		Set<Map.Entry<IToken.Id, IToken>> set = new HashSet<Map.Entry<IToken.Id, IToken>>();
		return set;
	}

	@Override
	public IToken get(Object key) {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return value==null;
	}

	@Override
	public Set<Id> keySet() {
		Set<Id> set = new HashSet<Id>();
		return set;
	}

	@Override
	public IToken put(IToken.Id key, IToken token) {
		return null;
	}
	
	@Override
	public void put(NodeToken token) {
	}

	@Override
	public void putAll(Map<? extends IToken.Id, ? extends IToken> m) {
		
	}

	@Override
	public IToken remove(Object key) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Collection<IToken> values() {
		List<IToken> tokens = new ArrayList<IToken>(0);
		return tokens;
	}

}
