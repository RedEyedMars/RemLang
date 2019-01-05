package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ListOfOutputs <T extends Output > extends ArrayList<T> implements Flattenable {
	private static final long serialVersionUID = 1441170912888830102L;

	@Override
	public Stream<? extends Importable> flatStream() {
		return stream().flatMap(Flattenable::flatStream);
	}

}
