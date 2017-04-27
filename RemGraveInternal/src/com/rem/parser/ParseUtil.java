package com.rem.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rem.parser.generation.FlowController;
import com.rem.parser.parallelism.JobCreator;
import com.rem.parser.parser.IParser;
import com.rem.parser.parser.IRule;
import com.rem.parser.parser.NameParser;
import com.rem.parser.parser.RegexParser;
import com.rem.parser.token.IToken;

public class ParseUtil {

	public static Map<String,PrintStream> debugStreams = new HashMap<String,PrintStream>();
	public static String currentParser = "";
	public static IRule currentRule = null;
	public static boolean debug = false;

	public static String getString(File file){
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while(line!=null){
				builder.append(line);
				builder.append('\n');
				line = reader.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(reader!=null){
			try{
				reader.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}

		return builder.toString();
	}


	public static void setupRules(Class<? extends IRule>... ruleClasses) {
		try {
			for(Class<? extends IRule> ruleClass:ruleClasses){
				((IRule)(ruleClass.getField("parser").get(ruleClass))).setup();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static void parse(IParser parser, File file, FlowController controller, List<IParser> rules, List<IParser> listnames) {
		long time = System.currentTimeMillis();
		for(int i=0;i<rules.size();++i){
			((IRule)rules.get(i)).setup();
		}
		String fileString = getString(file);
		ParseContext data = new ParseContext(parser,file.getName(),fileString);			
		System.out.println("File Length:"+data.getFile().length());
		if(controller!=null&&controller.getLazyNameParser()!=null){
			NameParser.lazyParser=controller.getLazyNameParser();
			parser.parse(data);
			JobCreator.waitUntilDoneProcessing();
			NameParser.lazyParser=null;
			if(!data.isDone()){
				System.out.println("First-Pass Failed!");
				data.invalidate();
			}
			else {
				data.accumulateLists(controller);
				for(IParser list:listnames){
					String pattern = ((RegexParser)list).getPattern();
					String listName = pattern.substring(0, pattern.length()-1);
					data.addList(listName);
				}
				data = ParseContext.copy(data);
				ParseContext.reset();
				System.out.println("First-Pass Successful");
			}
		}
		if(data.isValid()){
			parser.parse(data);
			JobCreator.waitUntilDoneProcessing();
			data.accumulateLists(controller);
		}

		result(data,time);
		if(controller!=null&&data.isDone()){
			controller.generate(data);
		}
	}

	public static void debug_parse(IParser parser, File file, FlowController controller, ParseList rules, ParseList listnames) {
		for(IToken.Key key:rules.keySet()){
			((IRule)rules.get(key).getValue()).setup();
		}
		ParseContext data = new ParseContext(parser,file.getName(),getString(file));
		debug = true;
		parser.debug_parse(data);

		result(data,0);
		if(controller!=null){
			controller.generate(data);
		}
	}

	public static void result(ParseContext data, long time){
		System.out.println(data.isValid()?"Final State:Success":("Final State:Failure("+currentParser+" unable to parse)"));
		System.out.println("Parse Time:"+((double)(System.currentTimeMillis()-time)/1000.0));
		System.out.println(data.isDone()?"End Reached":("End not Reached:"+currentParser+" left hanging"));
		if(data.getFrontPosition()!=data.getFurthestPosition()){
			System.out.println("Furthest Valid Position:"+data.getFrontPosition());
			System.out.println("Furthest:"+data.getFurthestParser()+":"+data.getFurthestPosition());
			System.out.println(data.get().substring(0,data.getFurthestPosition()-data.getFrontPosition())+"$>"+data.get().substring(data.getFurthestPosition()-data.getFrontPosition()));
		}
		else {
			System.out.print("Furthest Position:"+data.getFrontPosition());
			if(!data.isDone()){
				System.out.println("\nLine Number:"+data.getLineNumber(data.getFrontPosition()));
				System.out.println("$>"+data.get());
			}
			else {
				System.out.println();
			}
		}
	}


	public static void debug(String catagories, Object object, String string) {
		if(debugStreams.containsKey(currentParser)&&!catagories.contains("internal")){
			debugStreams.get(currentParser).println(object+":"+string);
		}
		else if(catagories.contains("|")){
			for(String catagory:catagories.split("[|]")){
				if(debugStreams.containsKey(catagory)){
					debugStreams.get(catagory).println(object+":"+string);
				}
			}
		}
		else {
			if(debugStreams.containsKey(catagories)){
				debugStreams.get(catagories).println(object+":"+string);
			}
		}
	}


	public static boolean compareFront(String input, String template) {
		if(input.length()<template.length())return false;
		if(input.length()==template.length()){
			return input.equals(template);
		}
		else {
			for(int i=0;i<template.length();++i){
				if(input.charAt(i)!=template.charAt(i)){
					return false;
				}
			}
			char c = input.charAt(template.length());
			if(c>=48&&c<=57){
				return false;
			}
			else if(c>=65&&c<=90){
				return false;
			}
			else if(c>=97&&c<=122){
				return false;
			}
			else if(c==95||c==45){
				return false;
			}
			else return true;
		}
	}

}
