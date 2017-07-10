package com.rem.parser.generation;

import java.util.HashSet;
import java.util.Set;

public class SetEntry extends ListEntry{

	private static Set<String> set;
	private static Set<Entry> processing;
	private static StringBuilder setBuilder;
	public SetEntry(Entry... in){
		super(in);
		this.delimiter = "";
	}
	public void get(StringBuilder builder) {
		if (list.isEmpty()) {
			if (this.emptyEntry != null) {
				this.emptyEntry.get(builder);
			}
		}
		else {
			boolean wasSet = false;
			if(set==null){
				set = new HashSet<String>();
				processing = new HashSet<Entry>();
				setBuilder = new StringBuilder();
				wasSet = true;
			}
			if(processing.add(this)){
				String delim = startWithDelimiter?delimiter:"";
				for (Entry e : list) {
					StringBuilder tempBuilder = new StringBuilder();
					e.get(tempBuilder);
					if(set.add(tempBuilder.toString())){
						setBuilder.append(delim);
						setBuilder.append(tempBuilder.toString());
						delim = delimiter;
					}
				}
				if(wasSet){
					set = null;
					builder.append(setBuilder.toString());
				}
			}
		}
	}
}
