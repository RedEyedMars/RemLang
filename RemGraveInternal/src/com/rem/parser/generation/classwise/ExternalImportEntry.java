package com.rem.parser.generation.classwise;
import java.util.*;
import com.rem.parser.generation.*;

public abstract class ExternalImportEntry implements Entry{
	public static final Map<String,Entry> packages = new HashMap<String,Entry>();
	private static final Map<String,String> topClasses = new HashMap<String,String>();

	public static void addTopClass(String dom, String sub) {
		topClasses.put(sub, dom);
	}
	static {
		packages.put("List", new StringEntry("java.util"));
		packages.put("ArrayList", new StringEntry("java.util"));
		packages.put("LinkedList", new StringEntry("java.util"));
		packages.put("Arrays", new StringEntry("java.util"));
		packages.put("Map", new StringEntry("java.util"));
		packages.put("HashMap", new StringEntry("java.util"));
		packages.put("Set", new StringEntry("java.util"));
		packages.put("HashSet", new StringEntry("java.util"));

		packages.put("File", new StringEntry("java.io"));
	}
	List<ExternalImportEntry> subImports = new ArrayList<ExternalImportEntry>();
	List<ImportEntry> imports = new ArrayList<ImportEntry>();
	private Entry header = new Entry(){
		@Override
		public void get(StringBuilder arg0) {
		}
	};
	public ExternalImportEntry(){
	}
	public ExternalImportEntry(Entry initialImports){
		this.header = initialImports;
	}

	public void __SETUP__(Entry preImports) {
		this.header = preImports;
	}
	public void addSubImport(ExternalImportEntry importEntry){
		subImports.add(importEntry);
	}
	public void addImport(ImportEntry importEntry){
		imports.add(importEntry);
	}
	public void addParentImport(Entry parentClass) {
		imports.add(new ImportClassEntry(parentClass));
	}
	public void outputImport(StringBuilder builder){
		ImportSet list = new ImportSet();
		list.add(header);
		for(ExternalImportEntry imp: subImports){
			imp.spread(list);
		}
		for(ImportEntry entry:imports){
			list.add(entry);
		}
		list.get(builder);
	}
	private void spread(ImportSet list){
		list.add(header);
		for(ExternalImportEntry imp: subImports){
			imp.spread(list);
		}
		for(ImportEntry entry:imports){
			list.add(entry);
		}
	}
	private static class ImportSet extends HashMap<String,Set<String>> implements Entry{

		private static final long serialVersionUID = 372051253943029641L;
		public void add(String packageName, String className){
			if(!containsKey(packageName)){
				put(packageName, new HashSet<String>());
			}
			get(packageName).add(className);
		}
		public void add(ImportEntry entry){
			if(entry instanceof ImportClassEntry){
				StringBuilder classFinder = new StringBuilder();
				entry.getName().get(classFinder);
				ExternalClassEntry parentClass = ExternalClassEntry.allClasses.get(classFinder.toString());
				if(parentClass!=null){
					for(String key: parentClass.getVariables().keySet()){
						StringBuilder typeFinder = new StringBuilder();
						parentClass.getVariables().get(key).getType().get(typeFinder);

						ExternalClassEntry variableType = ExternalClassEntry.allClasses.get(typeFinder.toString());
						if(variableType!=null){
							addImport(new ImportEntry(parentClass.getVariables().get(key).getType()));
						}
					}
				}
			}
			else {
				addImport(entry);
			}
		}
		private void addImport(ImportEntry entry){
			StringBuilder classFinder = new StringBuilder();
			entry.getName().get(classFinder);
			String firstName = classFinder.toString();
			int indexOfDot = firstName.indexOf('.');
			if(indexOfDot>-1){
				firstName = firstName.substring(0, indexOfDot);
			}

			if(packages.containsKey(firstName)){
				StringBuilder actualClassName = new StringBuilder();
				entry.getName().get(actualClassName);

				StringBuilder packageBuilder = new StringBuilder();
				packages.get(firstName).get(packageBuilder);

				String domName = firstName;
				while(topClasses.containsKey(domName)){
					domName = topClasses.get(domName);
				}
				add(packageBuilder.toString(),domName);
			}
		}
		public void add(Entry header){
			StringBuilder headerBuilder = new StringBuilder();
			header.get(headerBuilder);
			String[] newLineSplit = headerBuilder.toString().split(";");
			for(String line:newLineSplit){
				int lastDot = line.lastIndexOf('.');
				if(lastDot>-1){
					int firstSpace = line.indexOf(' ');
					add(line.substring(firstSpace+1,lastDot),line.substring(lastDot+1));
				}
			}

		}
		@Override
		public void get(StringBuilder builder) {

			builder.append("\n");
			for(String key: keySet()){
				for(String name:get(key)){
					builder.append("import ");
					builder.append(key);
					builder.append(".");
					builder.append(name);
					builder.append(";\n");
				}
			}
		}
	}
	public static class ImportEntry implements Entry {

		private Entry className;
		public ImportEntry(Entry className){
			this.className = className;
		}
		public Entry getName(){
			return className;
		}
		@Override
		public void get(StringBuilder builder) {
			StringBuilder classFinder = new StringBuilder();
			className.get(classFinder);
			String firstName = classFinder.toString();
			int indexOfDot = firstName.indexOf('.');
			if(indexOfDot>-1){
				firstName = firstName.substring(0, indexOfDot);
			}

			if(packages.containsKey(firstName)){
				builder.append("\nimport ");
				StringBuilder actualClassName = new StringBuilder();
				className.get(actualClassName);
				packages.get(firstName).get(builder);
				builder.append(".");
				String domName = firstName;
				while(topClasses.containsKey(domName)){
					domName = topClasses.get(domName);
				}
				builder.append(domName);
				builder.append(";");
			}
		}
	}
	public static class ImportClassEntry extends ImportEntry {
		public ImportClassEntry(Entry className){
			super(className);
		}
	}
}