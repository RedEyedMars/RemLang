package com.rem.parser.generation.classwise;
import java.util.*;
import com.rem.parser.generation.*;

public abstract class ExternalImportEntry implements Entry{
	public static final Map<String,String> solidPackages = new HashMap<String,String>();
	public static final Map<String,Entry> packages = new HashMap<String,Entry>();
	private static final Map<String,String> domClasses = new HashMap<String,String>();

	static {
		solidPackages.put("List", "java.util");
		solidPackages.put("ArrayList", "java.util");
		solidPackages.put("LinkedList", "java.util");
		solidPackages.put("Arrays", "java.util");
		solidPackages.put("Map", "java.util");
		solidPackages.put("HashMap", "java.util");
		solidPackages.put("Set", "java.util");
		solidPackages.put("HashSet", "java.util");

		solidPackages.put("File", "java.io");
	}
	public static void solidify(){
		for(String key:packages.keySet()){
			StringBuilder packageBuilder = new StringBuilder();
			packages.get(key).get(packageBuilder);
			solidPackages.put(key, packageBuilder.toString());
		}
		for(ExternalClassEntry subClass:ExternalClassEntry.allClasses){
			String firstName = subClass.getName();
			String fullName = subClass.getFullName();
			ExternalClassEntry parentClass = subClass.getEnclosingClass();
			while(parentClass!=null){
				subClass = parentClass;
				parentClass = subClass.getEnclosingClass();
			}
			if(solidPackages.containsKey(subClass.getName())){
				solidPackages.put(fullName, solidPackages.get(subClass.getName()));
				domClasses.put(fullName, subClass.getName());
				domClasses.put(firstName, subClass.getName());
			}
		}
	}
	List<ExternalImportEntry> subImports = new ArrayList<ExternalImportEntry>();
	List<ImportEntry> imports = new ArrayList<ImportEntry>();
	public ExternalImportEntry(){
	}
	public void addSubImport(ExternalImportEntry importEntry){
		subImports.add(importEntry);
	}
	public void addImport(ImportEntry importEntry){
		imports.add(importEntry);
	}
	public void removeImport(ImportEntry importEntry){
		imports.remove(importEntry);
	}
	public void addParentImport(Entry parentClass) {
		imports.add(new ImportClassEntry(parentClass));
	}
	public void outputImport(StringBuilder builder){
		ImportSet list = new ImportSet();
		spread(list);
		list.get(builder);
	}
	private void spread(ImportSet list){
		for(ExternalImportEntry imp: subImports){
			imp.spread(list);
		}
		for(ImportEntry entry:imports){
			list.add(entry);
		}
	}
	private static class ImportSet extends HashMap<String,Set<String>> implements Entry{

		private static final long serialVersionUID = 372051253943029641L;
		private static String lul = "[]";
		public void add(String packageName, String className){
			if(!containsKey(packageName)){
				put(packageName, new HashSet<String>());
			}
			if(get(packageName).add(className)){
				//System.out.println(packageName+"::"+className + "::"+lul);
			}
		}
		public void add(ImportEntry entry){
			if(entry instanceof ImportClassEntry){
				StringBuilder classFinder = new StringBuilder();
				entry.getName().get(classFinder);
				ExternalClassEntry parentClass = ExternalClassEntry.classMap.get(classFinder.toString());
				if(parentClass!=null){
					for(String key: parentClass.getVariables().keySet()){
						if(!parentClass.getVariables().get(key).isWeak()){
							StringBuilder typeFinder = new StringBuilder();
							parentClass.getVariables().get(key).getType().get(typeFinder);

							ExternalClassEntry variableType = ExternalClassEntry.classMap.get(typeFinder.toString());
							if(variableType!=null){
								addImport(new ImportEntry(parentClass.getVariables().get(key).getType()));
							}
						}
					}
				}
				addImport(entry);
			}
			else {
				addImport(entry);
			}
		}
		private void addImport(ImportEntry entry){
			StringBuilder classFinder = new StringBuilder();
			entry.getName().get(classFinder);
			String firstName = classFinder.toString();
			lul = firstName;
			int indexOfAngle = firstName.indexOf('<');
			if(indexOfAngle>-1){
				firstName = firstName.substring(0, indexOfAngle);
			}
			if(solidPackages.containsKey(firstName)){
				if(domClasses.containsKey(firstName)){
					add(solidPackages.get(domClasses.get(firstName)),domClasses.get(firstName));
				}
				else {
					add(solidPackages.get(firstName),firstName);
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
	public static class ImportEntry {

		private Entry className;
		public ImportEntry(Entry className){
			this.className = className;
		}
		public Entry getName(){
			return className;
		}
	}
	public static class ImportClassEntry extends ImportEntry {
		public ImportClassEntry(Entry className){
			super(className);
		}
	}
}