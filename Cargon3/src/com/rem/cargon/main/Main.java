package com.rem.cargon.main;

import java.util.Scanner;

import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Token;

public class Main {

	public static Token.Id CURRENT_ID = Token.Id.ROOT;
	public static void main(String[] args){
		//Scanner scanner = new Scanner(System.in);
		//scanner.nextLine();
		Parser.Result  result = new Parser().parse("cargon3.cgon");
		result.print(System.out::print, true);
		//scanner.nextLine();
		//scanner.close();
	}
	
}
