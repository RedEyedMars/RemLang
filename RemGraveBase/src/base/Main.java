package base;

import java.io.File;

import lists.Listnames;
import lists.Rules;
import base.rules.Base;
import com.rem.parser.ParseUtil;

public class Main {
	
	public static void main(String[] args){

		ParseUtil.parse(
				Base.parser, new File("generator.ruleset"), new BaseGenerator(),
				new Rules(),new Listnames());
	}

}
