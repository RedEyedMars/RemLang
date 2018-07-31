package com.rem.output.helpers;

import java.util.function.Consumer;

public interface Importable {

	public void getImports(Consumer<String> imports);
}
