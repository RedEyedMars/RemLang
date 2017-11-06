package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import gen.checks.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class IsConcreteGenerator extends Generator {

	public static final Integer DEFAULT = 0;
	public static final Integer CONCAT = 1;
	public static final Integer ACCESS_CLASS = 2;
	public static final Integer ACCESS_METHOD = 3;
	public static final Integer ACCESS_DEFAULT = 4;


	public IsConcreteGenerator(){
	}


	public String getName(){
		return "IsConcrete";
	}

		public void generateRoot(IToken root){
	}

		public void generate(ParseContext data){
	}

		public void setup(ParseContext context){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}