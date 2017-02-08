package com.rem.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Generator {

	private Map<String,PageOverview> pages = new HashMap<String,PageOverview>();
	private Map<String,Element> elements = new LinkedHashMap<String,Element>();

	protected abstract void generate(ParseData data);
	protected void generateAll(InterpreterDyn gen, IToken token, String name){
		addPage(gen.getName(),gen.getOutline(),gen.numberOfParameters());
		for(IToken subToken:token.getAll(name)){
			gen.interpret(subToken);
		}
	}
	protected void generateAll(Interpreter gen, IToken token, String name){
		addPage(gen.getName(),gen.getOutline());
		
		for(IToken subToken:token.getAll(name)){
			gen.interpret(subToken);
		}
	}
	protected String camelize(String name){
		StringBuilder builder = new StringBuilder();
		builder.append((""+name.charAt(0)).toUpperCase());
		boolean cap = false;
		for(int i=1;i<name.length();++i){
			char c = name.charAt(i);
			if(c=='_'){
				cap = true;
				continue;
			}
			else if(cap){
				builder.append((""+c).toUpperCase());
				cap = false;
			}
			else {
				builder.append(c);
			}
		}
		return builder.toString();

	}

	protected void outputAll(){
		for(String pageName:pages.keySet()){
			pages.get(pageName).output();
		}
	}
	public static interface Interpreter {
		public void interpret(IToken token);

		public String getName();
		public String[] getOutline();
	}
	public static interface InterpreterDyn {
		public void interpret(IToken token);

		public String getName();
		public String getOutline();
		public int numberOfParameters();
	}
	protected void addPage(String pageName, String pageOutline, int numberOfParameters){
		if(!pages.containsKey(pageName)){
			pages.put(pageName, new PageOverview());
		}
		pages.get(pageName).setOutline(pageOutline,numberOfParameters);
	}
	protected void addPage(String pageName, String[] pageOutline){
		if(!pages.containsKey(pageName)){
			pages.put(pageName, new PageOverview());
		}
		pages.get(pageName).setOutline(pageOutline);
	}
	protected void addFile(String pageName, File directory, String fileName, String[] parameters){
		if(!pages.containsKey(pageName)){
			throw new RuntimeException("Page: "+ pageName+" not defined");
		}
		pages.get(pageName).addFile(directory, fileName, parameters);
	}
	protected void addElement(String elementName, String[] elementOutline){
		if(!elements.containsKey(elementName)){
			Element element = new Element();
			element.setOutline(elementOutline);
			elements.put(elementName, element);
		}
	}
	protected Entries addElement(
			String pageName, 
			File directory, String fileName,
			String elementName,String[] elementOutline){
		if(!pages.containsKey(pageName)){
			throw new RuntimeException("Page: "+ pageName+" not defined");
		}
		Entries parameters = pages.get(pageName).addElement(directory,fileName,elementName,elementOutline);
		return parameters;
	}
	protected void addParameters(
			String pageName, 
			File directory, String fileName,
			List<String> parameters){
		if(!pages.containsKey(pageName)){
			throw new RuntimeException("Page: "+ pageName+" not defined");
		}
	}

	public static String P(int i){
		return "$"+i+"$";
	}
	protected class PageOverview {
		private List<File> dirs = new ArrayList<File>();
		private List<List<PageDetails>> details = new ArrayList<List<PageDetails>>();


		private String[] outLineParts;
		private String outLineEnd;
		private int numberOfParameters;

		public void setOutline(String[] pageOutline){
			this.numberOfParameters = pageOutline.length-1;
			this.outLineParts = new String[numberOfParameters];
			for(int i=0;i<numberOfParameters;++i){
				this.outLineParts[i] = pageOutline[i];
			}
			outLineEnd = pageOutline[numberOfParameters];
		}


		public void setOutline(String pageOutline, int numberOfParameters) {
			this.numberOfParameters = numberOfParameters;
			this.outLineParts = new String[numberOfParameters];
			int index = 0;
			int leftHandSide = 0;
			int rightHandSide = 0;
			for(int i=0;i<numberOfParameters;++i){
				rightHandSide = pageOutline.indexOf(P(i),leftHandSide);
				outLineParts[index++]= pageOutline.substring(leftHandSide,rightHandSide);
				leftHandSide = rightHandSide+P(i).length();
			}
			this.outLineEnd = pageOutline.substring(leftHandSide);
		}

		public Entries addElement(
				File directory, String fileName, String elementName, String[] elementOutline) {
			if(!elements.containsKey(elementName)){
				Element element = new Element();
				element.setOutline(elementOutline);
				elements.put(elementName, element);
			}
			int dirIndex = dirs.indexOf(directory);
			int fileIndex = details.get(dirIndex).indexOf(new PageComparer(fileName));
			if(fileIndex!=-1){
				Entries parameters = details.get(dirIndex).get(fileIndex).addElement(elementName);
				return parameters;
			}
			return null;
		}

		public Entries getElement(File directory, String fileName, String elementName){
			int dirIndex = dirs.indexOf(directory);
			for(int i=0;i<details.get(dirIndex).size();++i){
				if(details.get(dirIndex).get(i).filename.equals(fileName)){
					return details.get(dirIndex).get(i).getElement(elementName);
				}
			}
			return null;
		}

		public void addFile(File directory, String fileName, String[] parameters) {
			int dirIndex = dirs.indexOf(directory);
			if(dirIndex==-1){
				dirIndex = dirs.size();
				dirs.add(directory);
				details.add(new ArrayList<PageDetails>());

			}
			if(!details.get(dirIndex).contains(new PageComparer(fileName))){
				PageDetails dets = new PageDetails(fileName);
				details.get(dirIndex).add(dets);
				dets.addParameters(parameters);
			}
		}

		public void output(){
			for(int i=0;i<dirs.size();++i){
				for(int j=0;j<details.get(i).size();++j){
					BufferedWriter writer = null;
					try {
						writer = new BufferedWriter(
								new FileWriter(new File(dirs.get(i),details.get(i).get(j).filename)));
						for(int k=0;k<outLineParts.length;++k){
							writer.write(outLineParts[k]);
							if(k<numberOfParameters-1){
								writer.write(details.get(i).get(j).getParameter(k));
							}
						}
						details.get(i).get(j).output(writer);
						writer.write(outLineEnd);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(writer!=null){
						try {
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	protected class PageDetails {
		private String filename;
		private String[] parameters;
		private Map<String,Entries> elementParameters = new LinkedHashMap<String,Entries>();

		public PageDetails(String filename) {
			this.filename = filename;
		}

		public String getParameter(int i) {
			return parameters[i];
		}

		public void addParameters(String[] parameters) {
			this.parameters = parameters;
		}

		public Entries addElement(String elementName) {
			if(!elementParameters.containsKey(elementName)){
				this.elementParameters.put(elementName,new Entries(elementName));
			}
			return elementParameters.get(elementName);
		}
		public Entries getElement(String name){
			return elementParameters.get(name);
		}
		public void output(Writer writer) throws IOException{
			for(String key:elementParameters.keySet()){
				StringBuilder builder = new StringBuilder();
				elements.get(key).output(elementParameters.get(key),builder);
				writer.write(builder.toString());
			}
		}

		@Override
		public boolean equals(Object obj){
			if(obj instanceof PageDetails){
				return super.equals(obj);
			}
			else if(obj instanceof String){
				return filename.equals(obj);
			}
			else return false;
		}
	}

	protected static class Element {
		public static String FIRST = "$FIRST$";
		public static String SECOND = "$SECOND$";
		private String[] outLine;
		private String outLineEnd;
		private int numberOfParameters;
		public void setOutline(String[] pageOutline){
			numberOfParameters = pageOutline.length-1;
			this.outLine = new String[numberOfParameters];
			for(int i=0;i<numberOfParameters;++i){
				outLine[i] = pageOutline[i];
			}
			this.outLineEnd = pageOutline[pageOutline.length-1];
		}
		public void output(List<Entry[]> parameters,StringBuilder builder) {
			for(Entry[] entry:parameters){
				getString(entry,builder);
			}
		}
		public void getString(Entry[] entry, StringBuilder builder) {
			for(int i=0;i<numberOfParameters;++i){
				builder.append(outLine[i]);
				entry[i].get(builder);
			}
			builder.append(outLineEnd);
		}
	}

	protected static interface Entry {
		public void get(StringBuilder builder);
	}


	protected class Entries extends ArrayList<Entry[]> {
		private static final long serialVersionUID = -5640039974062765580L;
		private String elementName;

		public Entries(String elementName){
			this.elementName = elementName;
		}

		public boolean add(String[] entry){
			if(entry.length!=elements.get(elementName).numberOfParameters){
				StringBuilder builder = new StringBuilder();
				String comma = "";
				for(int i=0;i<entry.length;++i){
					builder.append(comma);
					builder.append(entry[i]);
					comma = ",";
				}
				throw new RuntimeException("Generator: Entry does not have "+elements.get(elementName).numberOfParameters+" parameters:"+builder.toString());			
			}
			else {
				Entry[] newEntry = new Entry[entry.length];
				for(int i=0;i<entry.length;++i){
					newEntry[i] = new StringEntry(entry[i]);
				}
				return super.add(newEntry);
			}
		}

		@Override
		public boolean add(Entry[] entry){
			if(entry.length!=elements.get(elementName).numberOfParameters){
				StringBuilder builder = new StringBuilder();
				String comma = "";
				for(int i=0;i<entry.length;++i){
					builder.append(comma);
					builder.append(entry[i]);
					comma = ",";
				}
				throw new RuntimeException("Generator: Entry does not have "+elements.get(elementName).numberOfParameters+" parameters:"+builder.toString());			
			}
			else return super.add(entry);
		}

	}

	protected static class StringEntry implements Entry {
		private String value; 
		public StringEntry(String value){
			this.value = value;
		}
		public void get(StringBuilder builder){
			builder.append(value);
		}
		public static Entry[] getEntry(String...strings){
			Entry[] entries = new Entry[strings.length];
			for(int i=0;i<strings.length;++i){
				entries[i] = new StringEntry(strings[i]);
			}
			return entries;
		}
	}

	protected static class ListEntry implements Entry {
		private String delimiter = ",";
		private List<Entry> list = new ArrayList<Entry>();
		public ListEntry(){
		}
		public boolean add(String e){
			return list.add(new StringEntry(e));
		}
		public boolean add(Entry e){
			return list.add(e);
		}
		public boolean isSingluar(){
			return list.size()==1;
		}
		public Entry getSingle(){
			return list.get(0);
		}
		public void addAll(ListEntry entry){
			this.list.addAll(entry.list);
		}
		public Entry getLast(){
			return list.get(list.size()-1);
		}
		public void replaceLast(Entry entry){
			list.remove(list.size()-1);
			list.add(entry);
		}
		public void setDelimiter(String delim){
			this.delimiter = delim;
		}
		public void get(StringBuilder builder){
			String delim = "";
			for(Entry e:list){
				builder.append(delim);
				e.get(builder);
				delim = delimiter;
			}
		}
	}
	
	protected static class TabEntry implements Entry {
		private int numberOfTabs;
		private Entry subEntry;
		public TabEntry(int numberOfTabs, Entry entry){
			this.numberOfTabs = numberOfTabs;
			this.subEntry = entry;
		}
		
		public void get(StringBuilder builder){
			builder.append('\n');
			for(int i=0;i<numberOfTabs;++i){
				builder.append('\t');
			}
			subEntry.get(builder);
		}
	}

	protected class ElementEntry implements Entry {
		private String elementName;
		private Entry[] parameters;
		public ElementEntry(String elementName, Entry[] parameters){
			this.elementName = elementName;
			this.parameters = parameters;
		}
		public ElementEntry(String elementName, List<Entry> entries){
			this.elementName = elementName;
			parameters = new Entry[entries.size()];
			for(int i=0;i<entries.size();++i){
				parameters[i] = entries.get(i);
			}
		}
		public ElementEntry(String elementName, Entry entry){
			this.elementName = elementName;
			parameters = new Entry[]{entry};
		}

		public void get(StringBuilder builder){
			elements.get(elementName).getString(parameters,builder);
		}
	}

	private static class PageComparer {
		private String fileName;
		protected PageComparer(String fileName){
			this.fileName = fileName;
		}

		@Override
		public boolean equals(Object obj){
			return obj.equals(fileName);
		}
	}
}
