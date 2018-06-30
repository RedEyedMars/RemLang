package com.rem.parser.generation.classwise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rem.parser.generation.Entry;
import com.rem.parser.generation.classwise.ExternalFlow.Parser.Result;

public class ExternalFlow {
	public static interface Parser {
		public enum State {
			SUCCESS, FAIL
		}
		public static interface Result {
			public State getState();
		}
		public Result parse(String string);
	}
	public static interface Generator {
		public void onSuccessfulParse(Result result);
	}
	public static void parse(String[] args, Parser parser, Generator generator) {
		if (args.length==1 ) {
			Parser.Result result = parser.parse(args[0]);
			System.out.println(result);
			if (result.getState()==Parser.State.SUCCESS) {
				setupRootDirectory(args[0]);
				generator.onSuccessfulParse(result);
				output();
			}
		}
		else  {
			System.err.println("No Filename Provided!");
		}
	}
	public static File __ROOTDIRECTORY__;
	public static Set<ExternalClassEntry> outputClasses = new HashSet<ExternalClassEntry>();

	public static void setupRootDirectory(String fileName) {
		int indexOfDot = fileName.lastIndexOf(".");
		if (indexOfDot>=0 ) {
			int indexOfSlash = fileName.lastIndexOf("/");
			if (indexOfSlash>=0 ) {
				__ROOTDIRECTORY__=new File("../"+camelize(fileName.substring(indexOfSlash,indexOfDot))+"/src");
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
	public static String camelize(String name) {
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
	public static void addFile(File outputDirectory, String fileName, Entry entry){
		StringBuilder builder = new StringBuilder();
		entry.get(builder);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputDirectory,fileName)));
			writer.write(builder.toString());
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void output() {
		ExternalImportEntry.solidify();
		outputClasses.parallelStream().forEach(O->O.outputToFile(__ROOTDIRECTORY__));
		System.out.println("Output Complete");
	}
}
