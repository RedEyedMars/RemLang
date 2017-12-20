package com.rem.crg.parser;

import clgen.Classwise;
import clgen.MainFlow;

public class Main {
	public static void main(String[] args){
		parse1(null);
	}
	public static void parse1(String fileName){
		Parser parser = new Parser();
		Parser.Result result = parser.parse("test.clws");
		System.out.println(result);
		if (result instanceof Parser.Result.Pass) {
		  new MainFlow().initializeFlowController();
		  MainFlow.variables.get_classwise().setup(result);
		  MainFlow.variables.get_classwise().generate(result);
		  //((Result.Pass)result).getRoot().print();
		}
	}
}
