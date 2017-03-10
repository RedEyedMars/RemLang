package com.rem.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ParseData {

	private static Map<String,ParseList> lists = new HashMap<String,ParseList>();
	private static IToken currentToken = new BranchToken();
	private static IToken rootToken = currentToken;
	private static Object[] contextParameters;
	private Map<Integer,Map<IParser,Set<AccessPoint>>> paps = new HashMap<Integer,Map<IParser,Set<AccessPoint>>>();
	private int length;

	private boolean valid = true;
	private int position = 0;
	private int furthestPosition = 0;
	private String furthestParser = "";
	private ParseData relativeData = null;
	private String file;
	private boolean mustEnd;
	private String fileName;
	
	public ParseData(String fileName, String file){
		this.length = file.length();
		this.file = file;
		this.fileName = fileName;
	}
	
	public ParseData(ParseData data) {
		this.fileName = data.fileName;
		this.length = data.length;
		this.file = data.file;
		relativeData = data;
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

	public String getFileName(){
		return fileName;
	}
	
	public int getPosition() {
		return position;
	}
	
	public int getFurthestPosition(){
		return this.furthestPosition;
	}
	
	public String getFurthestParser(){
		return furthestParser;
	}

	public void setPosition(int newPosition) {
		position = newPosition;
		if(newPosition>furthestPosition){
			furthestPosition = newPosition;
			furthestParser = ParseUtil.currentParser;
		}
	}
	
	public String get(){
		return file.substring(position);
	}
	
	public String getFile(){
		return file;
	}
	
	private class AccessPoint implements Comparable<AccessPoint>{
		private int index;
		private Object[] context = null;
		public AccessPoint(int index){
			this.index = index;
		}
		public AccessPoint(int index, Object[] context){
			this.index = index;
			this.context = context;
		}
		@Override
		public int hashCode(){
			return index;
		}
		@Override
		public boolean equals(Object obj){
			if(obj instanceof Integer){
				return ((Integer)obj) == index; 
			}
			else return super.equals(obj);
		}
		@Override
		public int compareTo(AccessPoint o) {
			if(o.context == null||context==null){
				return o.index-index;
			}
			else if(o.context != null&&context!=null&&context.length==o.context.length){
				if(o.index!=index){
					return o.index-index;
				}
				for(int i=0;i<context.length;++i){
					if(!context[i].equals(o.context)){
						return -1;
					}
				}
				return 0;
			}
			else return o.index-index;
		}
	}
	
	public void setPap(IParser parent, int index){
		if(!paps.containsKey(position)){
			paps.put(position,new HashMap<IParser,Set<AccessPoint>>());
		}
		if(!paps.get(position).containsKey(parent)){
			paps.get(position).put(parent, new TreeSet<AccessPoint>());
		}
		if(ParseData.contextParameters==null){
			paps.get(position).get(parent).add(new AccessPoint(index));
		}
		else {
			paps.get(position).get(parent).add(new AccessPoint(index,ParseData.contextParameters));
		}
	}
	public void resetPap(int pos, IParser parent, int index){
		Map<IParser,Set<AccessPoint>> pap = paps.get(pos);

		if(pap!=null){
			Set<AccessPoint> set = pap.get(parent);
			if(set!=null){
				set.remove(new AccessPoint(index));
			}
		}
	}
	public boolean isAtPreviousAccessPoint(IParser parent, int index){
		if(!paps.containsKey(position)){
			paps.put(position,new HashMap<IParser,Set<AccessPoint>>());
		}
		if(!paps.get(position).containsKey(parent)){
			paps.get(position).put(parent, new TreeSet<AccessPoint>());
		}
		for(AccessPoint point: paps.get(position).get(parent)){
			if(point.index == index){
				if(contextParameters==null&&point.context==null){
					return true;
				}
				else if(contextParameters!=null&&point.context!=null&&contextParameters.length==point.context.length){
					boolean isSame = true;
					for(int i=0;i<point.context.length;++i){
						if(!point.context[i].equals(contextParameters[i])){
							isSame = false;
							break;
						}
					}
					if(isSame){
						return true;
					}
				}
			}
		}
		return false;
	}

	public ParseList getList(String listName) {
		return lists.get(listName);
	}


	public Set<String> getListNames() {
		return lists.keySet();
	}

	public void addList(ParseList list) {
		lists.put(list.getName(), list);
	}

	public String getLine(){
		int newline = file.indexOf('\n',position);
		if(newline>=0){
			return file.substring(position,newline);	
		}
		else return file.substring(position);
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

	public boolean mustEnd() {
		return mustEnd;
	}
	public void setMustEnd(boolean me){
		this.mustEnd = me;
	}

	public void setContextParameters(Parameter<Object>[] parameters) {
		if(parameters.length>0){
			ParseData.contextParameters = new Object[parameters.length];
			for(int i=0;i<parameters.length;++i){
				ParseData.contextParameters[i] = parameters[i].evaluate();	
			}
		}
		else {
			ParseData.contextParameters = null;
		}
	}


}
