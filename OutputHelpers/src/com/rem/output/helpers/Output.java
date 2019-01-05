package com.rem.output.helpers;

import java.util.function.Consumer;

public abstract class Output implements Outputable, Stasisable, Verifyable, Flattenable {
	protected String value = null;
	public String evaluate(){
		if(value!=null){
			return value;
		}
		StringBuilder builder = new StringBuilder();
		output(builder::append);
		value = builder.toString();
		return builder.toString();
	}
	public void add(Consumer<String> builder){
		builder.accept(evaluate());
	}
	public OutputVibrant vibrate(){
		return new OutputVibrant(this);
	}
}
