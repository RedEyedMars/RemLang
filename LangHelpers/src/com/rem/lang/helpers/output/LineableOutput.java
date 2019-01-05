package com.rem.lang.helpers.output;

import java.util.function.Consumer;

public abstract class LineableOutput extends Output implements Lineable {


	@Override
	public void output(Consumer<String> builder){
		line().output(0,builder);
	}
}
