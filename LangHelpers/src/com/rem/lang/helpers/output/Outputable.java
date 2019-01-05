package com.rem.lang.helpers.output;

import java.util.function.Consumer;

public interface Outputable {
	public void output(Consumer<String> builder);
}
