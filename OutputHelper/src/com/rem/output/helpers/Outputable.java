package com.rem.output.helpers;

import java.util.function.Consumer;

public interface Outputable {
	public void output(Consumer<String> builder);
}
