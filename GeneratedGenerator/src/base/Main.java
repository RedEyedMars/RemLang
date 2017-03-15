package base;

import java.io.File;

import lists.Listnames;
import lists.Rules;
import base.rules.Base;
import com.rem.parser.ParseUtil;

public class Main {
	
	public static void main(String[] args){

		ParseUtil.parse(
				Base.parser, new File("base.generator"), new GeneratorGenerator(),
				new Rules(),new Listnames());
		Integer i = new Integer(1);
	}
}
