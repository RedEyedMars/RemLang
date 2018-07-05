package com.rem.output.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


public class OutputHelper {
	public static interface Parser <R,P extends R>{
		public R parse(String fileName);
		public P asPass(R result);
	}
	@SuppressWarnings("unchecked")
	public static <R,P extends R>
	  void parse(String[] args, Parser<R,P> parser, Consumer<P>... onSuccessMethods) {
		if (args.length==1 ) {
			R result = parser.parse(args[0]);
			System.out.println(result);
			P pass = parser.asPass(result);
			if (pass!=null) {
				setup(args[0]);
				Arrays.asList(onSuccessMethods).forEach(C->C.accept(pass));
				output();
			}
		}
		else  {
			System.err.println("No Filename Provided!");
		}
	}
	public static File __ROOTDIRECTORY__;
	public static List<OutputFile> outputFiles = new ArrayList<OutputFile>();
    public static Map<String,Output> packages = Collections.synchronizedMap(new HashMap<String,Output>());
	public static Map<String,OutputClass> classMap = new HashMap<String,OutputClass>();
	public static Map<String, List<OutputClass>> waitingClassMap = Collections.synchronizedMap(new HashMap<String, List<OutputClass>>());
	public static Set<String> inLang = new HashSet<String>();

	public static void setupRootDirectory(String fileName) {
		int indexOfDot = fileName.lastIndexOf(".");
		if (indexOfDot>=0 ) {
			int indexOfSlash = fileName.lastIndexOf("/");
			if (indexOfSlash>=0 ) {
				__ROOTDIRECTORY__=new File("../"+camelize(fileName.substring(indexOfSlash+1,indexOfDot))+"/src");
			}
			else  {
				__ROOTDIRECTORY__=new File("../"+camelize(fileName.substring(0,indexOfDot))+"/src");
			}
		}
		else  {
			__ROOTDIRECTORY__=new File("../"+camelize(fileName)+"/src");
		}
		__ROOTDIRECTORY__.mkdirs();
	}
	public static String camelize(Object obj) {
		String name = obj.toString();
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
	public static void addClass(OutputClass newClass){
		classMap.put(newClass.getFullName().evaluate(), newClass);
	}
	public static void addInternalClass(OutputClass newClass){
		classMap.put(newClass.getFullName().evaluate(), newClass);
		newClass.solidifyImports(newClass.getPackageName(), packages);
	}
	public static void addOutputClass(OutputClass newClass){
		classMap.put(newClass.getFullName().evaluate(), newClass);
		outputFiles.add(new OutputFile(newClass));
		newClass.solidifyImports(newClass.getPackageName(), packages);
	}
	public static void writeFile(File outputDirectory, String fileName, Outputable outputFile){
		try {
			if(!outputDirectory.exists()){
				outputDirectory.mkdirs();
			}
			System.out.println("Output:"+outputDirectory.getAbsolutePath()+":"+fileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputDirectory,fileName)));
			outputFile.output(S->{try{writer.write(S);}catch(IOException e){e.printStackTrace();}});
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public static void output() {
		outputFiles.parallelStream().forEach(O->O.outputToFile(__ROOTDIRECTORY__));
		System.out.println("Output Complete");
	}
	
	
	
	
	

	public static void setup(String rootName){
		setupRootDirectory(rootName);
		OutputHelper.addInternalClass(new OutputClass()
				._package("java.util").isClass().name("List").template("T1").method(
						new OutputMethod().type("void").name("add")).method(
						new OutputMethod().type("void").name("removeAll")).method(
						new OutputMethod().type("boolean").name("isEmpty")).method(
						new OutputMethod().type("void").name("addAll")).method(
						new OutputMethod().type("void").name("sort")).method(
						new OutputMethod().type("int").name("size")).method(
						new OutputMethod().type("T1").name("get")));
		OutputHelper.addInternalClass(new OutputClass()
				._package("java.util").isClass().name("ArrayList").template("T1").method(
				new OutputMethod().type("void").name("add")).method(
				new OutputMethod().type("void").name("removeAll")).method(
				new OutputMethod().type("boolean").name("isEmpty")).method(
				new OutputMethod().type("void").name("addAll")).method(
				new OutputMethod().type("void").name("sort")).method(
				new OutputMethod().type("int").name("size")).method(
				new OutputMethod().type("T1").name("get")));
		OutputHelper.addInternalClass(new OutputClass()
				._package("java.util").isClass().name("Map").template("T1").method(
				new OutputMethod().type("T1").name("put")).method(
				new OutputMethod().type("void").name("removeAll")).method(
				new OutputMethod().type("boolean").name("isEmpty")).method(
				new OutputMethod().type("void").name("addAll")).method(
				new OutputMethod().type("void").name("sort")).method(
				new OutputMethod().type("int").name("size")).method(
				new OutputMethod().type("T1").name("get")));
		OutputHelper.addInternalClass(new OutputClass()
				._package("java.util").isClass().name("HashMap").template("T1").method(
				new OutputMethod().type("T1").name("put")).method(
				new OutputMethod().type("void").name("removeAll")).method(
				new OutputMethod().type("boolean").name("isEmpty")).method(
				new OutputMethod().type("void").name("addAll")).method(
				new OutputMethod().type("void").name("sort")).method(
				new OutputMethod().type("int").name("size")).method(
				new OutputMethod().type("T1").name("get")));
		String[] collectionMethods = new String[]{
				"Object", "add",
				"void", "remove",
				"Boolean", "isEmpty",
				"void", "addAll",
				"Integer", "size"
		};
		supplimentClass("java.util","Set",collectionMethods);
		supplimentClass("java.util","HashSet",collectionMethods);
		supplimentClass("java.util","TreeSet",collectionMethods);
		supplimentClass("java.util","Stack",collectionMethods);
		supplimentClass("java.util","Deque",collectionMethods);
		
		supplimentClass("java.io","File", "String", "getPath", "void", "mkdirs");
		supplimentClass("java.io","FileReader");
		supplimentClass("java.io","FileWriter");
		supplimentClass("java.io","BufferedReader");
		supplimentClass("java.io","BufferedWriter");
		
		supplimentClass("java.util.stream","Stream");
		supplimentClass("java.util.stream","IntStream");
		
		Arrays.asList(new String[]{
				"Byte","byte",
				"Double", "double",
				"Float", "float",
				"Integer", "int",
				"Long", "long",
				"Short", "short",
				"Character", "char",
				"Boolean", "boolean",
				"String", "System",
				"Thread", "Math",
				"Runnable", "Object",
				"Void"
		}).stream().forEach(S->OutputHelper.inLang.add(S));
	}
	public static void suppliment(final String packageName, final String className){
		OutputHelper.classMap.put(className, new OutputClass()._package(packageName).name(className));
		OutputHelper.packages.put(className,  new OutputExact(packageName));
	}
	private static void supplimentClass(final String packageName, final String className,String... methodParameters){
		final OutputClass outputClass = new OutputClass()._package(packageName).name(className);
		if(methodParameters!=null){
			for(int i=0;i<methodParameters.length;i+=2){
				outputClass.method(new OutputMethod()
						.type(methodParameters[i]).name(methodParameters[i+1]));
			}
		}
		OutputHelper.classMap.put(className, outputClass);
		OutputHelper.packages.put(className,  new OutputExact(packageName));
	}
}
