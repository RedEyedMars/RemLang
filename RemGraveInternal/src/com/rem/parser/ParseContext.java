package com.rem.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.rem.parser.generation.FlowController;
import com.rem.parser.parser.IParser;import com.rem.parser.parser.NameParser;
import com.rem.parser.token.BranchToken;
import com.rem.parser.token.IToken;

public class ParseContext {

	private static Set<String> listnames = new HashSet<String>();
	private static Map<String,Map<String,ParseContext>> namedContexts = new HashMap<String,Map<String,ParseContext>>();
	private static Map<String,Map<Integer,ParseContext>> allSubContexts = new HashMap<String,Map<Integer,ParseContext>>();
	//private List<ParseContext> subContexts = new ArrayList<ParseContext>();

	private Map<String,ParseList> lists = new HashMap<String,ParseList>();

	private static IParser rootParser;
	private IToken currentToken;
	private IToken rootToken;
	private Stack<Object[]> parameters;// = new Stack<Object[]>();
	private Map<Integer,Map<IParser,Set<AccessPoint>>> paps = new HashMap<Integer,Map<IParser,Set<AccessPoint>>>();

	private ParseContext parentContext;

	private boolean valid = true;
	private int frontPosition = 0;
	private int backPosition = -1;
	private static Map<String,FurthestPoint> furthestPosition = new HashMap<String,FurthestPoint>();
	private FurthestPoint furthestPoint;
	private String file;
	private int length;
	private boolean mustEnd;
	private String fileName;
	private List<ParseContext> doneDependencies;
	public ParseContext(IParser rootParser, String fileName, String file){
		ParseContext.rootParser = rootParser;
		this.length = file.length();
		this.file = file;
		this.fileName = fileName;
		this.currentToken = new BranchToken();
		this.rootToken = currentToken;
		this.furthestPoint = new FurthestPoint();
		this.furthestPoint.position=0;
		this.furthestPoint.parser=rootParser.getName();
		this.parameters = new Stack<Object[]>();
		furthestPosition.put(fileName, this.furthestPoint);
		if(!allSubContexts.containsKey(fileName)){
			allSubContexts.put(fileName, new HashMap<Integer,ParseContext>());
		}
		allSubContexts.get(fileName).put(-1, this);
	}

	private ParseContext(ParseContext data) {
		this.fileName = data.fileName;
		this.length = data.length;
		this.file = data.file;
		this.parentContext = data;
		this.rootToken = data.rootToken;
		this.currentToken = new BranchToken();
		this.currentToken.setParent(data.currentToken);
		this.furthestPoint = data.furthestPoint;
		this.parameters = data.parameters;
		List<String> newListNames = new ArrayList<String>();
		List<String> newSingleNames = new ArrayList<String>();
		for(String listName:data.getListNames()){
			ParseList parentList = data.getList(listName);
			if(parentList==null){
				newListNames.add(ParseList.createPluralName(listName));
				newSingleNames.add(ParseList.createSingleName(listName));
			}
			else {
				newListNames.add(listName);
				newSingleNames.add(parentList.getSingular());
			}
		}
		for(int i=0;i<newListNames.size();++i){
			addList(newListNames.get(i),newSingleNames.get(i));			
		}
	}

	private ParseContext(ParseContext positional,ParseContext listial) {
		this.fileName = positional.fileName;
		this.length = positional.length;
		this.file = positional.file;
		this.parentContext = positional;
		this.rootToken = positional.rootToken;
		this.currentToken = positional.currentToken;
		this.furthestPoint = positional.furthestPoint;
		this.parameters = positional.parameters;
		this.frontPosition = positional.frontPosition;
		//if(fileName.contains("test")){
		//	//System.out.println("Create::"+this+":2:"+positional.getLineNumber(positional.getFrontPosition()));
		//}
		List<String> newListNames = new ArrayList<String>();
		List<String> newSingleNames = new ArrayList<String>();
		List<Set<String>> newListElements = new ArrayList<Set<String>>();
		for(String listName:listial.getListNames()){
			ParseList parentList = listial.getList(listName);
			if(parentList==null){
				newListNames.add(ParseList.createPluralName(listName));
				newSingleNames.add(ParseList.createSingleName(listName));
				newListElements.add(null);
			}
			else {
				newListNames.add(listName);
				newSingleNames.add(parentList.getSingular());
				newListElements.add(parentList.getNamesParser().getElements());
			}
		}
		for(int i=0;i<newListNames.size();++i){
			addList(newListNames.get(i),newSingleNames.get(i));
			if(newListElements.get(i)!=null){
				NameParser newParser = getList(newListNames.get(i)).getNamesParser();
				for(String newElement:newListElements.get(i)){
					newParser.addName(newElement);
				}
			}
		}
	}

	public static ParseContext copy(ParseContext data) {
		ParseContext copy = new ParseContext(rootParser,data.fileName, data.file);
		copy.lists = data.lists;
		return copy;
	}

	public static void reset(){

		for(String fileName:allSubContexts.keySet()){
			Map<Integer,ParseContext> contexts = allSubContexts.get(fileName);
			for(Integer position:contexts.keySet()){
				ParseContext context = contexts.get(position);
				for(String listName:context.lists.keySet()){
					context.lists.get(listName).reset();
				}
				context.paps = new HashMap<Integer,Map<IParser,Set<AccessPoint>>>();
				context.furthestPoint.position=0;
				context.furthestPoint.parser=rootParser.getName();
			}
		}
	}

	public void resetPaps(){
		this.paps = new HashMap<Integer,Map<IParser,Set<AccessPoint>>>();
	}

	public ParseContext getContextFromPosition(){
		return getContextFromPosition(frontPosition,true);
	}
	public ParseContext getContextFromPosition(int position, boolean createNew){
		if(allSubContexts.get(fileName).containsKey(position)){
			return allSubContexts.get(fileName).get(position);
		}
		else if(createNew){
			ParseContext newContext =  new ParseContext(this);
			if(fileName.contains("test")){
				//System.out.println("NEW CONTEXT:"+this+"<:>"+newContext+":"+position+":"+newContext.getLineNumber(position)+":"+getLine());
			}
			allSubContexts.get(fileName).put(position, newContext);
			return newContext;
		}
		else return this;
	}

	public boolean isDone() {
		boolean dependencyDone = true;
		if(doneDependencies!=null){
			for(ParseContext dependency:doneDependencies){
				if(!dependency.isDone()){
					dependencyDone = false;
					break;
				}
			}
		}
		if(backPosition==-1){			
			return dependencyDone&&valid&&frontPosition==length;
		}
		else {
			return dependencyDone&&valid&&frontPosition==backPosition; 
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
		return furthestPosition.get(fileName).position;
	}

	public String getFurthestParser(){
		return furthestPosition.get(fileName).parser;
	}

	public void setFrontPosition(int newPosition) {
		frontPosition = newPosition;
		if(newPosition>furthestPoint.position){
			furthestPoint.position = newPosition;
			furthestPoint.parser = ParseUtil.currentParser;
		}
	}
	public void setBackPosition(int newPosition) {
		backPosition = newPosition;
	}

	public String get(){
		if(backPosition==-1){
			//System.out.println(fileName+":"+frontPosition+":"+length+":"+file.length()+":"+getLineNumber(frontPosition));
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
		if(parameters.isEmpty()){
			paps.get(frontPosition).get(parent).add(new AccessPoint(index));
		}
		else {
			paps.get(frontPosition).get(parent).add(new AccessPoint(index,parameters.peek()));
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
				if(parameters.isEmpty()&&point.context==null){
					return true;
				}
				else if(!parameters.isEmpty()&&point.context!=null&&parameters.peek().length==point.context.length){
					boolean isSame = true;
					for(int i=0;i<point.context.length;++i){
						if(!point.context[i].equals(parameters.peek()[i])){
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
		if(lists.containsKey(listName)){
			return lists.get(listName);
		}
		else if(parentContext!=null){
			return parentContext.getList(listName);
		}
		else return null;
	}


	public Set<String> getListNames() {
		return listnames;
	}

	public void addList(ParseList list) {
		listnames.add(list.getName());
		if(!lists.containsKey(list.getName())){
			lists.put(list.getName(), list);
		}		
	}

	public void addList(String listName) {
		listnames.add(listName);
		if(!lists.containsKey(listName)){
			lists.put(listName, ParseList.createNew(
					ParseList.createPluralName(listName),
					ParseList.createSingleName(listName), parentContext));
		}		
	}
	public void addList(String pluralName, String singleName) {
		listnames.add(pluralName);
		if(!lists.containsKey(pluralName)){
			lists.put(pluralName, ParseList.createNew(
					pluralName,
					singleName, parentContext));
		}		
	}

	public void removeList(ParseList list){
		listnames.remove(list.getName());
		lists.remove(list.getName());
	}


	public void accumulateLists(FlowController controller) {
		rootToken.accumulateLists(this);
		if(controller!=null){
			controller.assignListElementNames(this, rootToken);
		}
	}



	public String getLine(){
		int newline = file.indexOf('\n',frontPosition);
		if(newline>=0&&(backPosition>=0&&newline>backPosition)){
			return "("+frontPosition+")"+file.substring(frontPosition,backPosition);	
		}
		else if(newline>=0){
			return "("+frontPosition+")"+file.substring(frontPosition,newline);
		}
		else return "("+frontPosition+")"+file.substring(frontPosition);
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


	public IToken addTokenLayer(IToken newToken) {
		IToken previousToken = currentToken;
		currentToken = newToken;
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

	public Object getParameter(int i) {
		return parameters.peek()[i];
	}
	public void pushParameters(Object[] values){
		parameters.push(values);
	}
	public void popParameters(){
		parameters.pop();
	}


	public void listParents() {
		if(this.parentContext!=null){
			System.out.println(this.parentContext);
			this.parentContext.listParents();
		}
		else {
			System.out.println("root:"+this);
		}
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		if(!furthestPosition.containsKey(fileName)){
			this.furthestPoint = new FurthestPoint();
			this.furthestPoint.position=0;
			this.furthestPoint.parser=rootParser.getName();
			furthestPosition.put(fileName, this.furthestPoint);
		}
		this.furthestPoint = furthestPosition.get(fileName);
		if(!allSubContexts.containsKey(fileName)){
			allSubContexts.put(fileName, new HashMap<Integer,ParseContext>());
			allSubContexts.get(fileName).put(-1, this);
		}
	}
	public void setFile(String file){
		this.file = file;
		this.length = file.length();
		this.parameters = new Stack<Object[]>();
	}

	public IParser getRootParser() {
		return rootParser;
	}

	public int getLineNumber(int position){
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

	private static class FurthestPoint {
		private int position;
		private String parser;
	}

	public void addDoneDependency(ParseContext newContext) {
		if(doneDependencies==null){
			doneDependencies = new ArrayList<ParseContext>();
		}
		doneDependencies .add(newContext);
	}

	public ParseContext getContextFromName(String listName,String contextName) {
		if(namedContexts.containsKey(listName)&&namedContexts.get(listName).containsKey(contextName)){
			return new ParseContext(this,namedContexts.get(listName).get(contextName));
		}
		else {
			return null;
		}

	}

	public ParseContext getContextFromNoName(String listName, String name) {
		ParseContext newContext = new ParseContext(this,this);
		if(!namedContexts.containsKey(listName)){
			namedContexts.put(listName,new HashMap<String,ParseContext>());
			namedContexts.get(listName).put(name, newContext);
		}
		else if(!namedContexts.get(listName).containsKey(name)){
			namedContexts.get(listName).put(name, newContext);
		}
		return newContext;

	}

	public void mergeNamedContext(String listName, String name, ParseContext newContext) {
		if(!namedContexts.containsKey(listName)){
			namedContexts.put(listName,new HashMap<String,ParseContext>());
			namedContexts.get(listName).put(name, newContext);
		}
		else if(!namedContexts.get(listName).containsKey(name)){
			namedContexts.get(listName).put(name, newContext);
		}
		else {
			ParseContext firstContext = namedContexts.get(listName).get(name);
			firstContext.parentContext = newContext;
		}
	}


}
