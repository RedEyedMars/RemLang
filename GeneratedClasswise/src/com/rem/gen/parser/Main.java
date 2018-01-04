package com.rem.gen.parser;

import clgen.Classwise;
import clgen.MainFlow;

public class Main {
	public static void main(String[] args){
		parse1(args[0]);
	}
	public static void parse1(String fileName){
		Parser parser = new Parser();
		Parser.Result result = parser.parse(fileName);
		System.out.println(result);
		if (result instanceof Parser.Result.Pass) {
		  new MainFlow().initializeFlowController();
		  MainFlow.variables.setup("");
		  MainFlow.variables.get_classwise().setup(result);
		  MainFlow.variables.get_classwise().generate(result);
		  MainFlow.variables.output(null);
		  //((Result.Pass)result).getRoot().print();
		}
	}
}