package base;

import java.io.File;

import lists.Rules;
import generator.rules.Base;
import com.rem.parser.ParseUtil;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;


public class Main {
	
	public static void main(String[] args){

		ParseUtil.parse(
				Base.parser, new File("base.generator"), Generators.generator,
				new Rules());
	}
}
