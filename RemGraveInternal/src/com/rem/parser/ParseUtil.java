package com.rem.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ParseUtil {

	public static Map<String,PrintStream> debugStreams = new HashMap<String,PrintStream>();
	public static String currentParser = "";
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

	public static void parse(IParser parser, File file, Generator generator, ParseList rules, ParseList listnames) {
		long time = System.currentTimeMillis();
		for(IToken.Key key:rules.keySet()){
			((IRule)rules.get(key).getValue()).setup();
		}
		String fileString = getString(file);
		ParseData data = new ParseData(file.getName(),fileString);			
		System.out.println("File Length:"+data.getFile().length());
		data.addList(listnames);
		if(generator!=null&&generator.getLazyNameParser()!=null){
			NameParser.lazyParser=generator.getLazyNameParser();
			parser.parse(data);
			NameParser.lazyParser=null;
			data.accumlateLists(generator);
			data.resetLists();
			if(data.isDone()){
				data = new ParseData(data);
				System.out.println("First-Pass Successful");
			}
			else {
				System.out.println("First-Pass Failed!");
			}
		}
		if(data.isValid()){
			parser.parse(data);
			data.accumlateLists(generator);
		}

		result(data,time);
		if(generator!=null){
			generator.generate(data);
		}
	}

	public static void debug_parse(IParser parser, File file, Generator generator, ParseList rules, ParseList listnames) {
		for(IToken.Key key:rules.keySet()){
			((IRule)rules.get(key).getValue()).setup();
		}
		ParseData data = new ParseData(file.getName(),getString(file));
		data.addList(listnames);
		debug = true;
		parser.debug_parse(data);

		result(data,0);
		if(generator!=null){
			generator.generate(data);
		}
	}

	public static void result(ParseData data, long time){
		System.out.println(data.isValid()?"Final State:Success":("Final State:Failure("+currentParser+" unable to parse)"));
		System.out.println("Parse Time:"+((double)(System.currentTimeMillis()-time)/1000.0));
		System.out.println(data.isDone()?"End Reached":("End not Reached:"+currentParser+" left hanging"));
		if(data.getFrontPosition()!=data.getFurthestPosition()){
			System.out.println("Furthest Valid Position:"+data.getFrontPosition());
			System.out.println("Furthest:"+data.getFurthestParser()+":"+data.getFurthestPosition());
			System.out.println(data.get().substring(0,data.getFurthestPosition()-data.getFrontPosition())+"$"+data.get().substring(data.getFurthestPosition()-data.getFrontPosition()));
		}
		else {
			System.out.println("Furthest Position:"+data.getFrontPosition());
			System.out.println(data.get());
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
