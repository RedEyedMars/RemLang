package com.rem.parser.token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rem.parser.ParseContext;
import com.rem.parser.generation.Generator;

public class NodeToken implements IToken{

	private String name;
	private Object value;
	private IToken parent;
	private String listName = null;
	private int position;
	private String fileName;
	public NodeToken(String name,String fileName, Object obj, int position){
		this.name = name;
		this.value = obj;
		this.position = position;
		this.fileName = fileName;
	}
	@Override
	public ParseContext getContext(ParseContext rootContext){
		if(parent==null){
			return rootContext;
		}
		else {
			return parent.getContext(rootContext);
		}
	}
	@Override
	public int getPosition() {
		return position;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	
	public int getLineNumber(String file){
		int current = 0;
		for(int i=0;i<position&&i<file.length();){
			int next = file.indexOf('\n',i);
			if(next==-1){
				return current;
			}
			else {
				++current;
				i=next+1;
			}
		}
		return current;
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
	public IToken getLast(String tokenName){
		return null;
	}

	@Override
	public void accumulateLists(ParseContext data){
		if(listName!=null){
			data.getList(listName).put(this);
			listName = null;
		}
	}
	@Override
	public List<IToken> getAll(String key){
		return new ArrayList<IToken>();
	}
	@Override
	public List<IToken> getAllSafely(String key){
		return new ArrayList<IToken>();
	}
	@Override
	public void print(){
		System.out.println(">:"+Generator.completeTokenErrorMessage(this));
	}
	@Override
	public void printShort(){
		System.out.println(">:"+Generator.tokenErrorMessage(this));
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
	public Set<Map.Entry<IToken.Key, IToken>> entrySet() {
		Set<Map.Entry<IToken.Key, IToken>> set = new HashSet<Map.Entry<IToken.Key, IToken>>();
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
	public Set<Key> keySet() {
		Set<Key> set = new HashSet<Key>();
		return set;
	}

	@Override
	public IToken put(IToken.Key key, IToken token) {
		return null;
	}
	
	@Override
	public void put(NodeToken token) {
	}

	@Override
	public void putAll(Map<? extends IToken.Key, ? extends IToken> m) {
		
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
