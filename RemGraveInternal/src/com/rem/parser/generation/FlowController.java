package com.rem.parser.generation;

import java.io.File;
import java.util.List;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseUtil;
import com.rem.parser.parallelism.JobCreator;
import com.rem.parser.parser.IParser;
import com.rem.parser.parser.RegexParser;
import com.rem.parser.token.IToken;

public abstract class FlowController {

	private static FlowController flow;
	public void parse(String... fileNames){
		flow = this;
		try {
		for(String fileName:fileNames){
			ParseUtil.parse(getRootParser(), new File(fileName), this, getRules(), getListnames());
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			JobCreator.end();
		}
		
	}


	public abstract IParser getRootParser();
	public abstract List<IParser> getRules();
	public abstract List<IParser> getListnames();
	public abstract Generator[] getGenerators();
	public abstract RegexParser getLazyNameParser();
	public abstract void assignListElementNames(ParseContext data, IToken rootToken);
	public abstract void setup(ParseContext data);
	
	public void generate(ParseContext data){
		setup(data);
		for(Generator gen:getGenerators()){
			gen.setup(data);
		}
		for(Generator gen:getGenerators()){
			gen.generate(data);
		}
		for(Generator gen:getGenerators()){
			gen.check();
		}
		for(Generator gen:getGenerators()){
			gen.outputAll();
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

	protected String getDirectory(String fileName, String knownDirectory){
		if("".equals(fileName)){
			return "..";
		}
		else {
			if("".equals(knownDirectory))
				return ".";
			else
				return knownDirectory;

		}
	}

	public static String getDirectoryFrom(String fileName, String knownDirectory) {
		return flow.getDirectory(fileName,knownDirectory);
	}
}
