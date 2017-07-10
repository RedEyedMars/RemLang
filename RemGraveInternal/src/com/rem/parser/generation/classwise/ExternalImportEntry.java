package com.rem.parser.generation.classwise;
import java.util.*;
import com.rem.parser.generation.*;

public abstract class ExternalImportEntry implements Entry{
	public static final Map<String,Entry> packages = new HashMap<String,Entry>();
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
	private List<ExternalImportEntry> subImports = new ArrayList<ExternalImportEntry>();
	private List<ImportEntry> imports = new ArrayList<ImportEntry>();
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
	public void outputImport(StringBuilder builder){
		SetEntry list = new SetEntry();
		list.add(header);
		for(ExternalImportEntry imp: subImports){
			imp.spread(list);
		}
		for(ImportEntry entry:imports){
			list.add(entry);
		}
		list.get(builder);
	}
	private void spread(SetEntry list){
		list.add(header);
		for(ExternalImportEntry imp: subImports){
			imp.spread(list);
		}
		for(ImportEntry entry:imports){
			list.add(entry);
		}
	}
	public static class ImportEntry implements Entry {

		private Entry className;
		public ImportEntry(Entry className){
			this.className = className;
		}
		@Override
		public void get(StringBuilder builder) {
			StringBuilder classFinder = new StringBuilder();
			className.get(classFinder);
			if(packages.containsKey(classFinder.toString())){
				builder.append("\nimport ");
				packages.get(className).get(builder);
				builder.append(".");
				className.get(builder);
				builder.append(";");
			}
		}
	}
}