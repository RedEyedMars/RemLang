package com.rem.parser.generation;

public class StringEntry implements Entry{
	private String value;

	public StringEntry(String value) {
		this.value = value;
	}
	
	public StringEntry(Integer value){
		this.value = ""+value;
	}

	public void get(StringBuilder builder) {
		builder.append(value);
	}

	public static ListEntry getEntry(String... strings) {
		ListEntry entries = new ListEntry();
		for (int i = 0; i < strings.length; ++i) {
			entries.add(new StringEntry(strings[i]));
		}
		return entries;
	}

	public String getString(){
		return value;
	}
	@Override
	public String toString(){
		return value;
	}
	public void set(String value){
		this.value = value;
	}
	public void set(StringEntry value){
		this.value = value.value;
	}
}
