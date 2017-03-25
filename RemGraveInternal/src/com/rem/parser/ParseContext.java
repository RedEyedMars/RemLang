package com.rem.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.rem.parser.generation.Generator;
import com.rem.parser.parser.IParser;
import com.rem.parser.parser.Parameter;
import com.rem.parser.token.BranchToken;
import com.rem.parser.token.IToken;

public class ParseContext {

	private Set<String> listnames = new HashSet<String>();
	private Map<String,ParseList> lists = new HashMap<String,ParseList>();

	private static IToken currentToken = new BranchToken();
	private static IToken rootToken = currentToken;
	private static Object[] contextParameters;
	private Map<Integer,Map<IParser,Set<AccessPoint>>> paps = new HashMap<Integer,Map<IParser,Set<AccessPoint>>>();
	private int length;

	private boolean valid = true;
	private int frontPosition = 0;
	private int backPosition = -1;
	private int furthestPosition = 0;
	private String furthestParser = "";
	private String file;
	private boolean mustEnd;
	private String fileName;

	public ParseContext(String fileName, String file){
		this.length = file.length();
		this.file = file;
		this.fileName = fileName;
	}

	public ParseContext(ParseContext data) {
		this.fileName = data.fileName;
		this.length = data.length;
		this.file = data.file;
		for(String listName:data.getListNames()){
			ParseList newList = ParseList.createNew(listName,data.lists.get(listName).getSingular());
			addList(newList);
			newList.getNamesParser().setParent(data.lists.get(listName).getNamesParser());
		}
	}

	public boolean isDone() {
		if(backPosition==-1){
			return valid&&frontPosition==length;
		}
		else {
			return valid&&frontPosition==backPosition; 
		}
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

	public int getFrontPosition() {
		return frontPosition;
	}
	public int getBackPosition() {
		return backPosition;
	}

	public int getFurthestPosition(){
		return this.furthestPosition;
	}

	public String getFurthestParser(){
		return furthestParser;
	}

	public void setFrontPosition(int newPosition) {
		frontPosition = newPosition;
		if(newPosition>furthestPosition){
			furthestPosition = newPosition;
			furthestParser = ParseUtil.currentParser;
		}
	}
	public void setBackPosition(int newPosition) {
		backPosition = newPosition;
	}

	public String get(){
		if(backPosition==-1){
			return file.substring(frontPosition);
		}
		else {
			return file.substring(frontPosition,backPosition);
		}
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
		if(!paps.containsKey(frontPosition)){
			paps.put(frontPosition,new HashMap<IParser,Set<AccessPoint>>());
		}
		if(!paps.get(frontPosition).containsKey(parent)){
			paps.get(frontPosition).put(parent, new TreeSet<AccessPoint>());
		}
		if(ParseContext.contextParameters==null){
			paps.get(frontPosition).get(parent).add(new AccessPoint(index));
		}
		else {
			paps.get(frontPosition).get(parent).add(new AccessPoint(index,ParseContext.contextParameters));
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
		if(!paps.containsKey(frontPosition)){
			paps.put(frontPosition,new HashMap<IParser,Set<AccessPoint>>());
		}
		if(!paps.get(frontPosition).containsKey(parent)){
			paps.get(frontPosition).put(parent, new TreeSet<AccessPoint>());
		}
		for(AccessPoint point: paps.get(frontPosition).get(parent)){
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
		return listnames;
	}

	public void addList(ParseList list) {
		if(listnames.add(list.getName())){
			lists.put(list.getName(), list);
		}
	}
	
	public void removeList(ParseList list){
		listnames.remove(list.getName());
		lists.remove(list.getName());
	}


	public void accumlateLists(Generator generator) {
		rootToken.accumlateLists(this);
		if(generator!=null){
			generator.assignListElementNames(this, rootToken);
		}
	}

	public void resetLists(){
		for(String listName:lists.keySet()){
			lists.get(listName).reset();
		}
	}

	public String getLine(){
		int newline = file.indexOf('\n',frontPosition);
		if(newline>=0&&(backPosition>=0&&newline>backPosition)){
			return file.substring(frontPosition,backPosition);	
		}
		else if(newline>=0){
			return file.substring(frontPosition,newline);
		}
		else return file.substring(frontPosition);
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

	public void addError(String error) {
		System.err.println(ParseUtil.currentParser+"("+frontPosition +"):"+ error);
	}

	public String printPap(IParser parser) {
		return paps.get(frontPosition).get(parser).contains(parser)+" at "+frontPosition;
	}

	public boolean mustEnd() {
		return mustEnd;
	}
	public void setMustEnd(boolean me){
		this.mustEnd = me;
	}

	public void setContextParameters(Parameter<Object>[] parameters) {
		if(parameters.length>0){
			ParseContext.contextParameters = new Object[parameters.length];
			for(int i=0;i<parameters.length;++i){
				ParseContext.contextParameters[i] = parameters[i].evaluate();	
			}
		}
		else {
			ParseContext.contextParameters = null;
		}
	}


}