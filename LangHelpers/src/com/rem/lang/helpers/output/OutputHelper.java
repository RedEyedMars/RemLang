package com.rem.lang.helpers.output;

public class OutputHelper {
	public static String asGetMethod(Object toMethodObj){
		if(toMethodObj==null)return null;
		StringBuilder result = new StringBuilder();
		result.append("get");
		String toMethod = toMethodObj.toString();
		boolean isFirst = true;
		for(int i=0;i<toMethod.length();++i){
			char c = toMethod.charAt(i);
			if(c>=65&&c<=90){
				result.append(c);
				isFirst = false;
			}
			else if(c>=97&&c<=122){
				if(isFirst){
					result.append((char)(c-32));
					isFirst = false;
				}
				else {
					result.append(c);
				}
			}
			else {
				if(c!='_'&&c!='-'){
					result.append(c);
				}
				isFirst = true;
			}
		}
		return result.toString();
	}
	public static String asGetMethod(String toMethod){
		if(toMethod==null)return null;
		StringBuilder result = new StringBuilder();
		result.append("get");
		boolean isFirst = true;
		for(int i=0;i<toMethod.length();++i){
			char c = toMethod.charAt(i);
			if(c>=65&&c<=90){
				result.append(c);
				isFirst = false;
			}
			else if(c>=97&&c<=122){
				if(isFirst){
					result.append((char)(c-32));
					isFirst = false;
				}
				else {
					result.append(c);
				}
			}
			else {
				if(c!='_'&&c!='-'){
					result.append(c);
				}
				isFirst = true;
			}
		}
		return result.toString();
	}
	public static String camelize(String toCamel){
		if(toCamel==null)return null;
		StringBuilder result = new StringBuilder();
		boolean isFirst = true;
		for(int i=0;i<toCamel.length();++i){
			char c = toCamel.charAt(i);
			if(c>=65&&c<=90){
				result.append(c);
				isFirst = false;
			}
			else if(c>=97&&c<=122){
				if(isFirst){
					result.append((char)(c-32));
					isFirst = false;
				}
				else {
					result.append(c);
				}
			}
			else {
				if(c!='_'&&c!='-'){
					result.append(c);
				}
				isFirst = true;
			}
		}
		return result.toString();
	}
	public static String camelize(Object toCamelObj){
		if(toCamelObj==null)return null;
		StringBuilder result = new StringBuilder();
		boolean isFirst = true;
		String toCamel = toCamelObj.toString();
		for(int i=0;i<toCamel.length();++i){
			char c = toCamel.charAt(i);
			if(c>=65&&c<=90){
				result.append(c);
				isFirst = false;
			}
			else if(c>=97&&c<=122){
				if(isFirst){
					result.append((char)(c-32));
					isFirst = false;
				}
				else {
					result.append(c);
				}
			}
			else {
				if(c!='_'&&c!='-'){
					result.append(c);
				}
				isFirst = true;
			}
		}
		return result.toString();
	}
	public static void setupRootDirectory(String fileName) {
		OutputClassStructure.setupRootDirectory(fileName);
	}
}
