package com.rem.gen;
import com.rem.gen.ClasswiseGenerator;
import com.rem.gen.ClassGenerator;
import com.rem.gen.MethodGenerator;
import com.rem.gen.BodyGenerator;
import com.rem.gen.Classwise2;
import com.rem.gen.VariableGenerator;
public class Classwise2 {
	public static ClassGenerator classGenerator = new ClassGenerator();
	public static MethodGenerator method = new MethodGenerator();
	public static BodyGenerator body_gen = new BodyGenerator();
	public static VariableGenerator variable = new VariableGenerator();
	public static String innerPackageName = "com.rem.gen";
	public static com.rem.output.helpers.OutputBody setupClassList = new com.rem.output.helpers.OutputBody();
	public static ClasswiseGenerator classwise = new ClasswiseGenerator();
	public static void main(String[] args){
		Classwise2.init();
		com.rem.output.helpers.OutputHelper.parse(args,new com.rem.gen.parser.Parser(),Classwise2::setup,Classwise2::generate);
	}
	public static void init(){
		com.rem.output.helpers.OutputHelper.suppliment("com.rem.output.helpers","OutputHelper");
com.rem.output.helpers.OutputHelper.suppliment("java.util.stream","IntStream");
;
	}
	public static void setup(com.rem.gen.parser.Parser.Result.Pass result){
		classwise.setup(result);
	}
	public static void generate(com.rem.gen.parser.Parser.Result.Pass result){
		classwise.generate(result);
	}
}
