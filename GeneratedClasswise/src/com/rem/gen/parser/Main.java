package com.rem.gen.parser;

import java.util.Scanner;

import com.rem.output.helpers.OutputHelper;

import clgen.Classwise;
import clgen.MainFlow;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		  new MainFlow().initializeFlowController();
		OutputHelper.parse(args,new Parser(),
				MainFlow.variables.get_classwise()::setup,
				MainFlow.variables.get_classwise()::generate);
		//parse1(args[0]);
	}
	public static void parse1(String fileName){
		Scanner scanner = new Scanner(System.in);
		//scanner.nextLine();
		long start = System.currentTimeMillis();
		Parser parser = new Parser();
		Parser.Result result = parser.parse(fileName);
		System.out.println(result);
		if (result instanceof Parser.Result.Pass) {
		  new MainFlow().initializeFlowController();
		  MainFlow.variables.setup("");
		  //MainFlow.variables.get_classwise().setup(result);
		  //MainFlow.variables.get_classwise().generate(result);
		  MainFlow.variables.output(null);
		  //((Result.Pass)result).getRoot().print();
		}
		//scanner.nextLine();
	}
}
