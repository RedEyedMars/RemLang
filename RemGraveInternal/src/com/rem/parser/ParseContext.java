package com.rem.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.rem.parser.generation.FlowController;
import com.rem.parser.parser.ConcreteListParser;
import com.rem.parser.parser.IParser;import com.rem.parser.parser.NameParser;
import com.rem.parser.token.BranchToken;
import com.rem.parser.token.IToken;
import com.rem.parser.token.IToken.Key;

public class ParseContext {

	private static Set<String> listnames = new HashSet<String>();
	private static Map<String,Map<String,ParseContext>> namedContexts = new HashMap<String,Map<String,ParseContext>>();
	private static Map<String,Map<Integer,ParseContext>> allSubContexts = new HashMap<String,Map<Integer,ParseContext>>();
	private static List<Object[]> parameterList = new ArrayList<Object[]>();;
	//private List<ParseContext> subContexts = new ArrayList<ParseContext>();

	private Map<String,ParseList> lists = new HashMap<String,ParseList>();

	private static IParser rootParser;
	private IToken currentToken;
	private IToken rootToken;
	private Stack<Integer> parameterIndex;
	private Map</*Position*/Integer,Map</*Parameter*/Integer,AccessPoint>> accessPoints = 
			new HashMap</*Position*/Integer,Map</*Parameter*/Integer,AccessPoint>>();

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
		parameterIndex = new Stack<Integer>();
		parameterIndex.push(-1);	

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
		this.parameterIndex = data.parameterIndex;
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
		this.parameterIndex = positional.parameterIndex;
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
		copy.resetAccessPoints();
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
				context.accessPoints.clear();
				context.furthestPoint.position=0;
				context.furthestPoint.parser=rootParser.getName();
			}
		}
	}

	public void resetAccessPoints(){
		this.accessPoints.clear();
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


	public static class AccessSuccess {
		private IToken token;
		private IParser path;
		private int position;
		public AccessSuccess(IParser newPath,int newPosition, IToken newToken) {
			token = newToken;
			position = newPosition;
			path = newPath;
		}
		public IToken getToken() {
			return token;
		}
		public int getPosition(){
			return position;
		}
	}
	public static class AccessPoint {
		private List<AccessSuccess> successes = new ArrayList<AccessSuccess>(); 
		private LinkedList<IParser> path = new LinkedList<IParser>();
		public void printPath(IParser[] printPath){
			System.out.print("[");
			for(IParser parser:printPath){
				System.out.print(parser);
				System.out.print(",");
			}
			System.out.print("]");
		}
		private List<IParser[]> restricted = new ArrayList<IParser[]>();
		public AccessPoint(){
		}
		public AccessSuccess access(IParser incoming){
			IParser[] path = getPathWith(incoming);
			for(int i=0;i<successes.size();++i){
				if(successes.get(i).path==incoming){
					return successes.get(i);
				}
			}
			return null;
		}
		public void succeed(IParser parser,int newPosition, IToken token) {
			//printPath(getPath());
			//System.out.println("<");
			successes.add(new AccessSuccess(parser,newPosition,token));
		}
		public void ascend(){
			path.pop();
		}
		public void descend(IParser newParser){
			restricted.add(getPathWith(newParser));
			path.add(newParser);
		}

		public boolean canUse(IParser question){
			IParser[] other = getSingletonPathWith(question);
			for(IParser[] mine:restricted){
				int shorten = mine.length-other.length;
				for(;shorten>=0;--shorten){
					boolean same = true;
					int j=mine.length-1;
					int i=other.length-1;
					for(;i>=0;--i){
						if(other[i]!=mine[j]){
							same = false;
							break;
						}						
						--j;
					}
					if(same){
						return false;
					}
				}

				//printPath(mine);
				//System.out.println("VS");
				//printPath(other);
				//System.out.println(":<!");
			}
			return true;
		}
		private IParser[] getSingletonPathWith(IParser addition){
			int start=path.size()-1;
			while(start>=0){
				if(path.get(start)==addition){
					break;
				}
				else {
					--start;
				}
			}
			++start;
			IParser[] newPath = new IParser[path.size()-start+1];
			int i=0;
			for(;start<path.size();++start){
				newPath[i] = path.get(start);
				++i;
			}
			newPath[i] = addition;
			return newPath;
		}
		private IParser[] getPathWith(IParser addition){
			IParser[] newPath = new IParser[path.size()+1];
			int i=0;
			for(;i<path.size();++i){
				newPath[i] = path.get(i);
			}
			newPath[i] = addition;
			return newPath;
		}
		private IParser[] getPath(){
			IParser[] newPath = new IParser[path.size()];
			
			for(int i=0;i<path.size();++i){
				newPath[i] = path.get(i);
			}
			return newPath;
		}
	}


	public AccessPoint getAccessPoint() {
		Map<Integer,AccessPoint> points = accessPoints.get(frontPosition);
		if(points == null){
			points = new HashMap<Integer,AccessPoint>();
			accessPoints.put(frontPosition, points);
		}
		AccessPoint accessPoint = points.get(parameterIndex.peek());
		if(accessPoint == null){
			accessPoint = new AccessPoint();
			points.put(parameterIndex.peek(), accessPoint);
		}
		return accessPoint;
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

	public boolean mustEnd() {
		return mustEnd;
	}
	public void setMustEnd(boolean me){
		this.mustEnd = me;
	}

	public Object getParameter(int i) {
		return parameterList.get(parameterIndex.peek())[i];
	}
	public synchronized void pushParameters(Object[] values){
		for(int i=0;i<parameterList.size();++i){
			if(parameterList.get(i)!=null&&parameterList.get(i).length==values.length){
				Object[] compare = parameterList.get(i);
				boolean isSame = true;
				for(int j=0;j<compare.length;++j){
					if(!compare[j].equals(values[j])){
						isSame = false;
						break;
					}
				}
				if(isSame){
					parameterIndex.push(i);
					return;
				}
			}
		}
		parameterIndex.push(addParameters(values));
	}
	private synchronized int addParameters(Object[] values){
		parameterList.add(values);
		return parameterList.size()-1;
	}
	public void popParameters(){
		parameterIndex.pop();
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
		this.parameterIndex = new Stack<Integer>();
		this.parameterIndex.push(-1);	
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

	public String getParameters() {
		List<Integer> nParameters = new ArrayList<Integer>();
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		while(!parameterIndex.isEmpty()){
			Integer objs = parameterIndex.pop();
			for(Object obj:parameterList.get(objs)){
				builder.append(obj);
			}
			builder.append(("\n"));
			nParameters.add(objs);
		}
		for(int i=nParameters.size()-1;i>=0;--i){
			parameterIndex.push(nParameters.get(i));
		}
		return builder.toString();
	}

	public int getEnd() {
		if(backPosition==-1){
			return length;
		}
		else return backPosition;
	}



}
