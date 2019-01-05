package com.rem.lang.helpers.output;

import java.util.stream.Stream;

public interface Flattenable {

	public Stream<? extends Importable> flatStream();
}
