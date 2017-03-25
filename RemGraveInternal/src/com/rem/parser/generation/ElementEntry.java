package com.rem.parser.generation;

import java.util.List;

public class ElementEntry implements Entry {
	protected Element element;
	protected ListEntry parameters;

	public ElementEntry(Element element, List<Entry> entries) {
		this.element = element;
		parameters = new ListEntry();
		for (int i = 0; i < entries.size(); ++i) {
			parameters.add(entries.get(i));
		}
	}

	public ElementEntry(Element element, ListEntry entry) {
		this.element = element;
		parameters = entry;
	}
	
	public ElementEntry(Generator generator, String elementName, ListEntry entry) {
		this.element = generator.getElement(elementName);
		if(this.element==null){
			throw new RuntimeException(elementName+": element name is not defined in generator"+generator);
		}
		parameters = entry;
	}

	public ElementEntry(Element element, String... entries) {
		this.element = element;
		parameters = new ListEntry();
		for (String entry : entries) {
			parameters.add(new StringEntry(entry));
		}
	}

	public void get(StringBuilder builder) {
		element.getString(parameters, builder);
	}

	public ListEntry getEntries() {
		return parameters;
	}

	public Entry get(int index) {
		return parameters.get(index);
	}

	public void setElementName(Generator generator,String elementName){
		this.element = generator.getElement(elementName);
		if(this.element==null){
			throw new RuntimeException(elementName+": element name is not defined in generator"+generator);
		}
	}
	
	public void setElement(Element element){
		this.element = element;
	}
}

