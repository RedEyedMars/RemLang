package com.rem.parser.generation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseList;
import com.rem.parser.parser.IParser;
import com.rem.parser.token.IToken;

public abstract class Generator {

	private Map<String, PageOverview> pages = new HashMap<String, PageOverview>();
	private Map<String, Element> elements = new LinkedHashMap<String, Element>();

	public abstract void generate(ParseContext data);

	public abstract IParser getLazyNameParser();

	public abstract void assignListElementNames(ParseContext data, IToken rootToken);

	public abstract String getName();

	public abstract void generateRoot(IToken rootToken);
	
	public Element getElement(String elementName){
		return elements.get(elementName);
	}

	protected void generateAll(InterpreterDyn gen, IToken token, String name) {
		addPage(gen.getName(), gen.getOutline(), gen.numberOfParameters());
		List<IToken> list = token.getAll(name);
		if(list!=null){
			for (IToken subToken : list) {
				gen.interpret(subToken);
			}
		}
	}

	protected void generateAll(Interpreter gen, IToken token, String name) {
		addPage(gen.getName(), gen.getOutline());

		for (IToken subToken : token.getAll(name)) {
			gen.interpret(subToken);
		}
	}

	protected void generateAll(Generator gen, IToken token, String name) {
		addPage(gen.getName(), elements.get("outline").getOutline());
		List<IToken> list = token.getAll(name);
		if(list!=null){
			for (IToken subToken : token.getAll(name)) {
				gen.generateRoot(subToken);
			}
		}
		else {
			System.out.println("No "+name+" found");
		}
	}

	public void generateAll(ParseContext data) {
		addPage(getName(), elements.get("outline").getOutline());

		for (IToken.Key key : data.getRoot().keySet()) {
			generateRoot(data.getRoot().get(key));
		}
	}

	public void generateAll(IToken root,String name) {
		addPage(getName(), elements.get("outline").getOutline());

		List<IToken> list =root.getAll(name);
		if(list!=null){

			for (IToken token : list) {
				generateRoot(token);
			}
		}
		else {
			System.out.println("No "+name+" found");
		}
	}

	protected void println(String... subStrings){
		System.out.println(buildString(subStrings));
	}

	protected String buildString(String... subStrings){
		StringBuilder builder = new StringBuilder();
		for(String subString:subStrings){
			builder.append(subString);
		}
		return builder.toString();
	}

	protected ParseList createNewParseList(String listName, String singleName){
		return ParseList.createNew(listName,singleName);
	}

	protected String camelize(String name) {
		StringBuilder builder = new StringBuilder();
		builder.append(("" + name.charAt(0)).toUpperCase());
		boolean cap = false;
		for (int i = 1; i < name.length(); ++i) {
			char c = name.charAt(i);
			if (c == '_') {
				cap = true;
				continue;
			} else if (cap) {
				builder.append(("" + c).toUpperCase());
				cap = false;
			} else {
				builder.append(c);
			}
		}
		return builder.toString();

	}

	public void outputAll() {
		for (String pageName : pages.keySet()) {
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

	protected void addPage(String pageName, String pageOutline, int numberOfParameters) {
		if (!pages.containsKey(pageName)) {
			pages.put(pageName, new PageOverview());
		}
		pages.get(pageName).setOutline(pageOutline, numberOfParameters);
	}

	protected void addPage(String pageName, String[] pageOutline) {

		if (!pages.containsKey(pageName)) {
			pages.put(pageName, new PageOverview());
		}
		pages.get(pageName).setOutline(pageOutline);
	}

	protected void addFile(String pageName, File directory, String fileName, ListEntry parameters) {
		if (!pages.containsKey(pageName)) {
			throw new RuntimeException("Page: " + pageName + " not defined");
		}
		pages.get(pageName).addFile(directory, fileName, parameters);
	}
	protected void addFile(File directory, String fileName, ListEntry parameters) {
		if (!pages.containsKey(getName())) {
			throw new RuntimeException("Page: " + getName() + " not defined");
		}
		pages.get(getName()).addFile(directory, fileName, parameters);
	}

	protected void addElement(String elementName, String[] elementOutline) {
		if (!elements.containsKey(elementName)) {
			Element element = new Element(elementName);
			element.setOutline(elementOutline);
			elements.put(elementName, element);
		}
	}
	protected void addElement(String elementName, Element element) {
		if (!elements.containsKey(elementName)) {
			elements.put(elementName, element);
		}
	}

	protected void addEntry(String pageName, File directory, String fileName, String entryName, Entry entry) {
		if (!pages.containsKey(pageName)) {
			throw new RuntimeException("Page: " + pageName + " not defined");
		}
		pages.get(pageName).addEntry(directory, fileName, entryName, entry);
	}

	protected void addEntry(File directory, String fileName, String entryName, Entry entry) {
		if (!pages.containsKey(getName())) {
			throw new RuntimeException("Page: " + getName() + " not defined");
		}
		pages.get(getName()).addEntry(directory, fileName, entryName, entry);
	}

	protected Entry getOrAddEntry(String pageName, File directory, String fileName, String entryName, Entry entry) {
		if (!pages.containsKey(pageName)) {
			throw new RuntimeException("Page: " + pageName + " not defined");
		}
		return pages.get(pageName).getOrAddEntry(directory, fileName, entryName, entry);
	}

	protected Entry getOrAddEntry(File directory, String fileName, String entryName, Entry entry) {
		if (!pages.containsKey(getName())) {
			throw new RuntimeException("Page: " + getName() + " not defined");
		}
		return pages.get(getName()).getOrAddEntry(directory, fileName, entryName, entry);
	}

	public static String P(int i) {
		return "$" + i + "$";
	}

	protected class PageOverview {
		private List<File> dirs = new ArrayList<File>();
		private List<List<PageDetails>> details = new ArrayList<List<PageDetails>>();

		private String[] outLineParts;
		private String outLineEnd;
		private int numberOfParameters;

		public void setOutline(String[] pageOutline) {
			this.numberOfParameters = pageOutline.length - 1;
			this.outLineParts = new String[numberOfParameters];
			for (int i = 0; i < numberOfParameters; ++i) {
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
			for (int i = 0; i < numberOfParameters; ++i) {
				rightHandSide = pageOutline.indexOf(P(i), leftHandSide);
				outLineParts[index++] = pageOutline.substring(leftHandSide, rightHandSide);
				leftHandSide = rightHandSide + P(i).length();
			}
			this.outLineEnd = pageOutline.substring(leftHandSide);
		}

		public Entry getEntry(File directory, String fileName, String entryName) {
			int dirIndex = dirs.indexOf(directory);
			for (int i = 0; i < details.get(dirIndex).size(); ++i) {
				if (details.get(dirIndex).get(i).filename.equals(fileName)) {
					return details.get(dirIndex).get(i).getParameter(entryName);
				}
			}
			return null;
		}

		public Entry getOrAddEntry(File directory, String fileName, String entryName, Entry parameter) {
			int dirIndex = dirs.indexOf(directory);
			for (int i = 0; i < details.get(dirIndex).size(); ++i) {
				if (details.get(dirIndex).get(i).filename.equals(fileName)) {
					Entry entry = details.get(dirIndex).get(i).getParameter(entryName);
					if (entry == null) {
						details.get(dirIndex).get(i).addParameter(entryName, parameter);
						entry = parameter;
					}
					return entry;
				}
			}
			return null;
		}

		public void addEntry(File directory, String fileName, String entryName, Entry entry) {
			int dirIndex = dirs.indexOf(directory);
			for (int i = 0; i < details.get(dirIndex).size(); ++i) {
				if (details.get(dirIndex).get(i).filename.equals(fileName)) {
					details.get(dirIndex).get(i).addParameter(entryName, entry);
				}
			}
		}

		public void addFile(File directory, String fileName, ListEntry parameters) {
			int dirIndex = dirs.indexOf(directory);
			if (dirIndex == -1) {
				dirIndex = dirs.size();
				dirs.add(directory);
				details.add(new ArrayList<PageDetails>());

			}
			if (!details.get(dirIndex).contains(new PageComparer(fileName))) {
				PageDetails dets = new PageDetails(fileName);
				details.get(dirIndex).add(dets);
				dets.addParameters(parameters);
			}
		}

		public void output() {
			for (int i = 0; i < dirs.size(); ++i) {
				for (int j = 0; j < details.get(i).size(); ++j) {
					BufferedWriter writer = null;
					try {
						StringBuilder builder = new StringBuilder();
						for (int k = 0; k < outLineParts.length; ++k) {
							builder.append(outLineParts[k]);
							details.get(i).get(j).getParameter(k).get(builder);
						}
						writer = new BufferedWriter(
								new FileWriter(new File(dirs.get(i), details.get(i).get(j).filename)));

						writer.write(builder.toString());
						writer.write(outLineEnd);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (writer != null) {
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
		private Map<String, Entry> parameters = new HashMap<String, Entry>();
		private List<String> keys = new ArrayList<String>();

		public PageDetails(String filename) {
			this.filename = filename;
		}

		public Entry getParameter(int i) {
			return parameters.get(keys.get(i));
		}

		public Entry getParameter(String key) {
			return parameters.get(key);
		}

		public void addParameters(ListEntry parameters) {
			for (int i = 0; i < parameters.size(); ++i) {
				this.addParameter("$" + this.keys.size(), parameters.get(i));
			}
		}

		public void addParameter(String name, Entry parameter) {
			this.parameters.put(name, parameter);
			this.keys.add(name);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof PageDetails) {
				return super.equals(obj);
			} else if (obj instanceof String) {
				return filename.equals(obj);
			} else
				return false;
		}
	}


	protected class Entries extends ArrayList<Entry[]> {
		private static final long serialVersionUID = -5640039974062765580L;
		private String elementName;

		public Entries(String elementName) {
			this.elementName = elementName;
		}

		public boolean add(String[] entry) {
			if (entry.length != elements.get(elementName).numberOfParameters()) {
				StringBuilder builder = new StringBuilder();
				String comma = "";
				for (int i = 0; i < entry.length; ++i) {
					builder.append(comma);
					builder.append(entry[i]);
					comma = ",";
				}
				throw new RuntimeException("Generator: Entry does not have "
						+ elements.get(elementName).numberOfParameters() + " parameters:" + builder.toString());
			} else {
				Entry[] newEntry = new Entry[entry.length];
				for (int i = 0; i < entry.length; ++i) {
					newEntry[i] = new StringEntry(entry[i]);
				}
				return super.add(newEntry);
			}
		}

		@Override
		public boolean add(Entry[] entry) {
			if (entry.length != elements.get(elementName).numberOfParameters()) {
				StringBuilder builder = new StringBuilder();
				String comma = "";
				for (int i = 0; i < entry.length; ++i) {
					builder.append(comma);
					builder.append(entry[i]);
					comma = ",";
				}
				throw new RuntimeException("Generator: Entry does not have "
						+ elements.get(elementName).numberOfParameters() + " parameters:" + builder.toString());
			} else
				return super.add(entry);
		}

	}


	

	private static class PageComparer {
		private String fileName;

		protected PageComparer(String fileName) {
			this.fileName = fileName;
		}

		@Override
		public boolean equals(Object obj) {
			return obj.equals(fileName);
		}
	}

	public String tokenErrorMessage(IToken offender){
		String error = "";
		for(IToken.Key key:offender.keySet()){
			error+="("+key.getName()+":"+offender.get(key).getString()+")"+",";
		}
		return error;
	}
}
