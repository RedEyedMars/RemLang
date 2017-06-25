package com.rem.parser.generation;


public class TabEntry implements Entry {
	private int numberOfTabs;
	private Entry subEntry;

	public TabEntry(int numberOfTabs, Entry entry) {
		this.numberOfTabs = numberOfTabs;
		this.subEntry = entry;
	}
	public void get(StringBuilder builder) {
		builder.append('\n');
		for (int i = 0; i < numberOfTabs; ++i) {
			builder.append('\t');
		}
			subEntry.get(builder);
	}
}

