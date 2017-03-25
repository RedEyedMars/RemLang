package com.rem.parser.generation;

import java.util.ArrayList;
import java.util.List;


public class ListEntry implements Entry {
		private String delimiter = ",";
		private List<Entry> list = new ArrayList<Entry>();
		private Entry emptyEntry;
		private boolean startWithDelimiter = false;

		public ListEntry(Entry... entries) {
			if (entries != null && entries.length > 0) {
				for (Entry entry : entries) {
					list.add(entry);
				}
			}
		}

		public Entry get(int index) {
			return list.get(index);
		}

		public boolean add(String e) {
			return list.add(new StringEntry(e));
		}

		public boolean add(Entry e) {
			return list.add(e);
		}

		public boolean isEmpty() {
			return list.isEmpty();
		}

		public int size(){
			return list.size();
		}

		public boolean isSingular() {
			return list.size() == 1;
		}

		public Entry getSingle() {
			return list.get(0);
		}

		public void addAll(ListEntry entry) {
			this.list.addAll(entry.list);
		}

		public Entry getLast() {
			return list.get(list.size() - 1);
		}

		public void replaceLast(Entry entry) {
			list.remove(list.size() - 1);
			list.add(entry);
		}

		public void setEmptyEntry(Entry entry) {
			this.emptyEntry = entry;
		}

		public void setDelimiter(String delim) {
			this.delimiter = delim;
		}

		public void startWithDelimiter(boolean bool){
			this.startWithDelimiter = bool;
		}

		public void get(StringBuilder builder) {
			if (list.isEmpty()) {
				if (this.emptyEntry != null) {
					this.emptyEntry.get(builder);
				}
			} else {
				String delim = startWithDelimiter?delimiter:"";
				for (Entry e : list) {
					builder.append(delim);
					e.get(builder);
					delim = delimiter;
				}
			}
		}
		/*
		@Override
		public boolean equals(Object object){
			if(object==null){
				return false;
			}
			else if(object instanceof ListEntry){
				ListEntry otherList = (ListEntry)object;
				if(otherList.list.size()!=this.list.size()){
					return false;
				}
				for(int i=0;i<list.size();++i){
					if(otherList.list.get(i)==null){
						if(list.get(i)!=null){
							return false;
						}
					}
					else if(!otherList.list.get(i).equals(list.get(i))){
						return false;
					}
					
				}
				return true;
			}
			else if(object instanceof StringEntry){
				if(this.isSingular()&&this.list.get(0) instanceof StringEntry){
					return ((StringEntry)object).getString().equals(((StringEntry)list.get(0)).getString());
				}
			}
			return false;
		}*/
	}

