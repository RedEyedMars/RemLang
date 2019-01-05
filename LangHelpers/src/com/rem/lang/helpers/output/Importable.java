package com.rem.lang.helpers.output;

import java.util.function.Consumer;

public interface Importable {

	public void getImports(Consumer<String> imports);
}
