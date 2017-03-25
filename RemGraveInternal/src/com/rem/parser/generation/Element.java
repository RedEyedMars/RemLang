package com.rem.parser.generation;

public class Element {
	public static String FIRST = "$FIRST$";
	public static String SECOND = "$SECOND$";
	private String[] outLine;
	private int numberOfParameters;
	private String name;

	public Element(String name) {
		this.name = name;
	}
	
	public Element(String name, String[] outline) {
		this.name = name;
		setOutline(outline);
	}

	public String[] getOutline() {
		return outLine;
	}

	public String getName() {
		return name;
	}

	public void setOutline(String[] pageOutline) {
		numberOfParameters = pageOutline.length;
		this.outLine = new String[numberOfParameters];
		for (int i = 0; i < numberOfParameters; ++i) {
			outLine[i] = pageOutline[i];
		}
	}

	public void getString(ListEntry entries, StringBuilder builder) {
		//System.out.println(name);
		for (int i = 0; i < numberOfParameters-1; ++i) {				
			builder.append(outLine[i]);
			entries.get(i).get(builder);
		}
		builder.append(outLine[numberOfParameters-1]);
	}

	public int numberOfParameters() {
		return numberOfParameters;
	}
}