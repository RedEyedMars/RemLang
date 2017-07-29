package com.rem.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
import com.rem.parser.parser.IParser;
import com.rem.parser.parser.IPatterned;
import com.rem.parser.parser.NameParser;
import com.rem.parser.token.BranchToken;
import com.rem.parser.token.IToken;

public class ParseContext {

	private static Set<String> listnames = new TreeSet<String>(new Comparator<String>(){
		@Override
		public int compare(String o1, String o2) {
			if(o1.length()==o2.length()&&!o1.equals(o2)){
				return 1;
			}
			else return o2.length()-o1.length();
		}}
			);
	private static Map<String,Map<String,ParseContext>> namedContexts = new HashMap<String,Map<String,ParseContext>>();
	private static Map<String,Map<Integer,ParseContext>> allSubContexts = new HashMap<String,Map<Integer,ParseContext>>();
	private static List<Object[]> parameterList = Collections.synchronizedList(new ArrayList<Object[]>());
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
	private String input;
	private int length;
	private boolean mustEnd;
	private File file;
	private String fileName;
	private String directory;
	private List<ParseContext> doneDependencies;
	public ParseContext(IParser rootParser, File file, String input){
		ParseContext.rootParser = rootParser;
		this.length = input.length();
		this.input = input;
		this.file = file;
		this.fileName = file.getName();
		if(file.getParentFile()==null){
			this.directory = "";
		}
		else {
			this.directory = file.getParentFile().getPath();
		}
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
		this.file = data.file;
		this.directory = data.directory;
		this.fileName = data.fileName;
		this.length = data.length;
		this.input = data.input;
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
		this.input = positional.input;
		this.parentContext = positional;
		this.rootToken = positional.rootToken;
		this.currentToken = positional.currentToken;
		this.furthestPoint = positional.furthestPoint;
		this.parameterIndex = positional.parameterIndex;
		this.frontPosition = positional.frontPosition;
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
		ParseContext copy = new ParseContext(rootParser,data.file, data.input);
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
			allSubContexts.get(fileName).put(position, newContext);
			return newContext;
		}
		else return this;
	}

	public ParseContext getContextFromFile(String fileName, File file) {
		ParseContext newContext = getContextFromPosition(-frontPosition,true);
		if(!newContext.getFileName().equals(fileName)){
			newContext.setFile(file);
		}
		return newContext;
	}

	public boolean isDone() {
		if(backPosition==-1){
			return valid&&frontPosition==length;
		}
		else {
			return valid&&frontPosition==backPosition; 
		}
	}
	public boolean isComplete() {
		if(doneDependencies!=null){
			for(ParseContext dependency:doneDependencies){
				if(!dependency.isComplete()){
					return false;
				}
			}
		}
		return isValid()&&isDone();
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
		//System.err.println(input.length()+":"+frontPosition+","+backPosition);
		if(backPosition==-1){
			return input.substring(frontPosition);
		}
		else {
			return input.substring(frontPosition,backPosition);
		}
	}

	public String getInput(){
		return input;
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
			//IParser[] path = getPathWith(incoming);
			for(int i=0;i<successes.size();++i){
				if(successes.get(i).path==incoming){
					return successes.get(i);
				}
			}
			return null;
		}
		public void succeed(IParser parser,int newPosition, IToken token) {
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
		public IParser[] getPath(){
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
			return parentContext.getList(listName, new HashSet<ParseContext>());
		}
		else return null;
	}
	private ParseList getList(String listName, Set<ParseContext> previous){
		if(previous.add(this)){
			if(lists.containsKey(listName)){
				return lists.get(listName);
			}
			else if(parentContext != null) {
				return parentContext.getList(listName,previous);
			}
			else {
				return null;
			}
		}
		else {
			StringBuilder error = new StringBuilder();
			for(ParseContext prev:previous){
				error.append(prev.fileName);
				error.append(",");
			}
			return null;
			//throw new RuntimeException("Looped at:"+fileName+"("+error+")");
		}
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
		listnames.clear();
		rootToken.accumulateLists(this);
		if(controller!=null){
			controller.assignListElementNames(this, rootToken);
		}
	}



	public String getLine(){
		int newline = input.indexOf('\n',frontPosition);
		if(newline>=0&&(backPosition>=0&&newline>backPosition)){
			return "("+frontPosition+")"+input.substring(frontPosition,backPosition);	
		}
		else if(newline>=0){
			return "("+frontPosition+")"+input.substring(frontPosition,newline);
		}
		else return "("+frontPosition+")"+input.substring(frontPosition);
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
	public void pushParameters(Object[] values){
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
		parameterIndex.push(parameterList.size());
		parameterList.add(values);
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

	public void setFile(File file) {
		this.file = file;
		this.fileName = file.getName();
		if(file.getParentFile()==null){
			this.directory = "";
		}
		else {
			this.directory = file.getParentFile().getPath();
		}
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

		this.input = ParseUtil.getString(file);
		this.length = input.length();
		this.parameterIndex = new Stack<Integer>();
		this.parameterIndex.push(-1);	
	}
	public IParser getRootParser() {
		return rootParser;
	}

	public int getLineNumber(int position){
		int current = 0;
		for(int i=0;i<position&&i<input.length();){
			int next = input.indexOf('\n',i);
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



	public void retainList(ParseList list){
		addList(list.getName());
		ParseList contextList = (ParseList)getList(list.getName());
		NameParser namesParser = (NameParser)contextList.getNamesParser();
		for(IParser parser:list.getParsers()){
			namesParser.addName(((IPatterned)parser).getPattern());
		}
	}

	public String getDirectory() {
		return FlowController.getDirectoryFrom(fileName,directory);
	}
	
	public Set<String> getListElements(String listName){
		return getList(listName).getNamesParser().getElements();
	}

}
