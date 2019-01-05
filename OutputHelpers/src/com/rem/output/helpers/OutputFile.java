package com.rem.output.helpers;

import java.io.File;
import java.util.function.Consumer;

public class OutputFile implements Outputable {
	private OutputClass fileClass;

	public OutputFile(OutputClass fileClass){
		this.fileClass = fileClass;
	}

	public void outputToFile(File rootDirectory) {
		OutputHelper.writeFile(new File(rootDirectory,this.fileClass.getPackageName().evaluate().replace(".", File.separator)), this.fileClass.getName().evaluate()+".java", this);
	}

	@Override
	public void output(Consumer<String> builder) {
		OutputLine line = new OutputLine();
		line.exact("package ").variable(this.fileClass.getPackageName()).exact(";").nextLine()
				.all("import ",fileClass.getImports(),";").nextLine()
				.connect(fileClass.line());
		line.output(0, builder);
	}


}
