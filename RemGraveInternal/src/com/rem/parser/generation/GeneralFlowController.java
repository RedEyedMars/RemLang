package com.rem.parser.generation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GeneralFlowController {

	protected List<Generator> privateFiles = new ArrayList<Generator>();
	public void initializeFlowController(){
	}
	public void addFile(File directory, String fileName, Entry file){
		Generator privateGenerator = new Generator.Concrete();
		privateGenerator.addFile(directory, fileName, file);
		privateFiles.add(privateGenerator);
	}


	public static String camelize(String name) {
		StringBuilder builder = new StringBuilder();
		builder.append(("" + name.charAt(0)).toUpperCase());
		boolean cap = false;
		for (int i = 1; i < name.length(); ++i) {
			char c = name.charAt(i);
			if (c == '_') {
				cap = true;
				continue;
			} else if (cap) {
				builder.append(("" + c).toUpperCase());
				cap = false;
			} else {
				builder.append(c);
			}
		}
		return builder.toString();

	}

}
