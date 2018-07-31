package com.rem.output.helpers;

import java.util.stream.Stream;

public interface Flattenable {

	public Stream<? extends Importable> flatStream();
}
