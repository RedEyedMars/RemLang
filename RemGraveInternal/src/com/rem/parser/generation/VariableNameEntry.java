package com.rem.parser.generation;

import com.rem.parser.generation.classwise.ExternalStatement;

public class VariableNameEntry extends StringEntry{

	private ExternalStatement asStatement;
	private String asString;
	private boolean isString = false;

	public VariableNameEntry(String value) {
		super(value);
		asString = value;
	}

	public VariableNameEntry(ExternalStatement statement){
		super(evaluate(statement, false));
		asStatement = statement;
	}
	public VariableNameEntry(ExternalStatement statement, Boolean isString){
		super(evaluate(statement, isString));
		asStatement = statement;
		this.isString = isString;
	}
	private static String evaluate(Entry statement, Boolean isString){
		StringBuilder builder = new StringBuilder();
		if(isString){
			builder.append("(");
		}
		statement.get(builder);
		if(isString ){
			builder.append(").toString()");
		}
		return builder.toString();
	}
	public void get(StringBuilder builder){
		if(asStatement!=null){
			if(isString){
				builder.append("(");
			}
			asStatement.get(builder);
			if(isString ){
				builder.append(").toString()");
			}
		}
		else {
			super.get(builder);
		}
	}

	public ExternalStatement getAsStatement(){
		if (asString!=null){
			return new ExternalStatement(
					new ExternalStatement.NewObject(new ExternalStatement.TypeName("VariableNameEntry"),
							new ExternalStatement.Parameters(new ExternalStatement(new StringEntry(
									asString.replace("\"", "\\\""))))));
		}
		else {
			return new ExternalStatement(
					new ExternalStatement.NewObject(new ExternalStatement.TypeName("VariableNameEntry"),
							new ExternalStatement.Parameters(
									new ExternalStatement(new StringEntry("("), new StringEntry(").toString()"),asStatement))));
		}
	}

	public VariableNameEntry asString(){
		return new VariableNameEntry(asStatement,true);
	}
}
