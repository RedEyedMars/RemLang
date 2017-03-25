package com.rem.parser;

public class ContextId {

	private ContextId parent;
	private String listName;
	private String tokenNameOfSubId;

	public ContextId(ContextId parent, String listName, String tokenNameOfSubId) {
		this.parent = parent;
		this.listName = listName;
		this.tokenNameOfSubId = tokenNameOfSubId;
	}

	
}
