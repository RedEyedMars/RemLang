package com.rem.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;

import com.rem.parser.parser.IParser;

public class ParseProfiler {

	public static boolean running = false;
	private static Map<String,Long> timeSpent = new HashMap<String,Long>();
	private static Map<String,Stack<String>> stacks = new HashMap<String,Stack<String>>();
	private static Map<String,Long> times = new HashMap<String,Long>();
	public synchronized static void open(String fileName, String name, long nanoTime) {
		if(!stacks.containsKey(fileName)){
			stacks.put(fileName, new Stack<String>());			
		}
		Stack<String> stack = stacks.get(fileName);
		if(!stack.isEmpty()){
			Long oldTime = timeSpent.get(stack.peek());
			if(oldTime!=null){
				timeSpent.put(stack.peek(), oldTime+(nanoTime-times.get(fileName)));
			}
			else {
				timeSpent.put(stack.peek(), nanoTime-times.get(fileName));
			}
		}
		times.put(fileName, nanoTime);
		stack.push(name);
	}
	public synchronized static void close(String fileName, String name, long nanoTime) {
		Stack<String> stack = stacks.get(fileName);
		Long oldTime = timeSpent.get(stack.peek());
		if(oldTime!=null){
			timeSpent.put(stack.pop(), oldTime+(nanoTime-times.get(fileName)));
		}
		else {
			timeSpent.put(stack.pop(), nanoTime-times.get(fileName));
		}
		times.put(fileName, nanoTime);
	}
	
	public static void getTop(int numberOfParsersToSee){
		TreeSet<DataPoint> tree = new TreeSet<DataPoint>();
		for(String parser:timeSpent.keySet()){
			tree.add(new DataPoint(parser,timeSpent.get(parser)));
		}
		int i=0;
		for(DataPoint point:tree){
			System.out.println(point.parser+":"+point.time);
			if(i>=numberOfParsersToSee){
				break;
			}
			++i;
		}
	}
	
	private static class DataPoint implements Comparable<DataPoint>{
		private String parser;
		private long time;
		public DataPoint(String parser2, Long long1) {
			parser = parser2;
			time = long1;
		}
		@Override
		public int compareTo(DataPoint o) {
			return (int) (o.time/1000-time/1000);
		}
	}
	 
}
