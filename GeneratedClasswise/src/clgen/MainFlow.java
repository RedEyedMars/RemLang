package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.output.helpers.*;
import com.rem.parser.generation.classwise.*;
import com.rem.parser.generation.*;
import com.rem.parser.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.*;
import clgen.MainFlow;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import com.rem.gen.parser.Token;
import clgen.ClassGenerator;
import clgen.MethodGenerator;
import clgen.BodyGenerator;
import clgen.VariableGenerator;
import com.rem.output.helpers.OutputStatement;
import com.rem.output.helpers.OutputBlankStatement;
import com.rem.output.helpers.LineableOutput;
import com.rem.output.helpers.CallableOutput;
import com.rem.output.helpers.OutputBraced;
import com.rem.output.helpers.OutputCall;
import com.rem.output.helpers.OutputNewNumber;
import com.rem.output.helpers.OutputNewObject;
import com.rem.output.helpers.OutputClass;
import com.rem.output.helpers.OutputMethod;
import com.rem.output.helpers.OutputVariable;
import com.rem.output.helpers.OutputContext;
import com.rem.output.helpers.OutputOperator;
import com.rem.output.helpers.OutputLambda;
import com.rem.output.helpers.OutputStaticCall;
import com.rem.output.helpers.OutputArguments;
import com.rem.output.helpers.OutputParameters;
import com.rem.output.helpers.OutputBody;
import com.rem.output.helpers.OutputConditional;
import com.rem.output.helpers.OutputConditionalHeader;
import com.rem.output.helpers.OutputQuote;
import com.rem.output.helpers.OutputExact;
import com.rem.output.helpers.OutputConcatenation;
import com.rem.output.helpers.OutputString;
import com.rem.output.helpers.OutputCast;
import com.rem.output.helpers.OutputType;
import com.rem.output.helpers.OutputStasis;
import com.rem.output.helpers.OutputHelper;
import com.rem.output.helpers.OutputFile;
import com.rem.output.helpers.Output;
import com.rem.gen.parser.Parser;
import com.rem.parser.parser.IParser;
import com.rem.parser.parser.RegexParser;
import com.rem.parser.token.IToken;
import com.rem.parser.generation.Generator;
import com.rem.parser.generation.GeneralFlowController;
import com.rem.parser.generation.FlowController;
import com.rem.parser.ParseContext;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;
import clgen.Classwise;
public class MainFlow extends FlowController  {
	public static class classes {
	}
	public static MainFlow variables = null;
	public static MainFlow methods = null;
	//Externals


	//Internals
	protected final ClassGenerator __VAR__classGenerator = new ClassGenerator();
	protected final MethodGenerator __VAR__method = new MethodGenerator();
	protected final BodyGenerator __VAR__body_gen = new BodyGenerator();
	protected final VariableGenerator __VAR__variable = new VariableGenerator();
	protected final String __VAR__packageName = "com.rem.crg.generator";
	protected final String __VAR__outerPackageName = "com.rem.cls";
	protected final String __VAR__innerPackageName = "com.rem.gen";
	protected final OutputBody __VAR__setupClassList = new OutputBody();
	protected final Classwise __VAR__classwise = new Classwise();

	public static void main(final String[] args)  {
		if(args.length==1) {
	new MainFlow().parse(args[0]);
		}
		else {
	System.err.println("No filename provided!");
		}
	}
	public ClassGenerator getClassGenerator()  {
		return __VAR__classGenerator;
	}
	public ClassGenerator get_classGenerator()  {
		return __VAR__classGenerator;
	}
	public MethodGenerator getMethod()  {
		return __VAR__method;
	}
	public MethodGenerator get_method()  {
		return __VAR__method;
	}
	public BodyGenerator getBodyGen()  {
		return __VAR__body_gen;
	}
	public BodyGenerator get_body_gen()  {
		return __VAR__body_gen;
	}
	public VariableGenerator getVariable()  {
		return __VAR__variable;
	}
	public VariableGenerator get_variable()  {
		return __VAR__variable;
	}
	public IParser getRootParser()  {
		return null;
	}
	public List<IParser> getRules()  {
		return null;
	}
	public List<IParser> getListnames()  {
		return null;
	}
	public RegexParser getLazyNameParser()  {
		return null;
	}
	public void assignListElementNames(final ParseContext data,final IToken rootToken)  {
	}
	public void setup(final ParseContext data)  {
	}
	public String getPackageName()  {
		return __VAR__packageName;
	}
	public String get_packageName()  {
		return __VAR__packageName;
	}
	public String getOuterPackageName()  {
		return __VAR__outerPackageName;
	}
	public String get_outerPackageName()  {
		return __VAR__outerPackageName;
	}
	public String getInnerPackageName()  {
		return __VAR__innerPackageName;
	}
	public String get_innerPackageName()  {
		return __VAR__innerPackageName;
	}
	public OutputBody getSetupClassList()  {
		return __VAR__setupClassList;
	}
	public OutputBody get_setupClassList()  {
		return __VAR__setupClassList;
	}
	public Classwise getClasswise()  {
		return __VAR__classwise;
	}
	public Classwise get_classwise()  {
		return __VAR__classwise;
	}
	public Generator[] getGenerators()  {
		final MainFlow self = this;
		return new Generator[]{
		new Generator(){
			public String getName(){
				return "Main";
			}
			public void setup(ParseContext data){
			}
			public void generate(ParseContext data){
				StringBuilder __BUILDER__;
				File __DIRECTORY__;
				
				outputAll();
			}
			public void generateRoot(IToken rootToken){
			}
		}};
	}
	public static String __ROOT_DIRECTORY__ = ".";
	@Override
	public void initializeFlowController(){
	  MainFlow.variables = this;
	  MainFlow.methods = this;
	}
	public void setup(String rootDirectory){
		__ROOT_DIRECTORY__ = rootDirectory;
		ExternalClassEntry.suppliment("Token", "com.rem.gen.parser");
		ExternalClassEntry.suppliment("Token", "com.rem.gen.parser");
		ExternalClassEntry.suppliment("Token", "com.rem.gen.parser");
		ExternalClassEntry.suppliment("OutputStatement", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputBlankStatement", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("LineableOutput", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("CallableOutput", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputBraced", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputCall", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputNewNumber", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputNewObject", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputClass", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputMethod", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputVariable", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputContext", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputOperator", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputLambda", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputStaticCall", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputArguments", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputParameters", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputBody", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputConditional", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputConditionalHeader", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputQuote", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputExact", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputConcatenation", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputString", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputCast", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputType", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputStasis", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputHelper", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("OutputFile", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("Output", "com.rem.output.helpers");
		ExternalClassEntry.suppliment("Parser", "com.rem.gen.parser");
		ExternalClassEntry.suppliment("Token", "com.rem.gen.parser");
		ExternalClassEntry.suppliment("IParser", "com.rem.parser.parser");
		ExternalClassEntry.suppliment("RegexParser", "com.rem.parser.parser");
		ExternalClassEntry.suppliment("IToken", "com.rem.parser.token");
		ExternalClassEntry.suppliment("Generator", "com.rem.parser.generation");
		ExternalClassEntry.suppliment("GeneralFlowController", "com.rem.parser.generation");
		ExternalClassEntry.suppliment("FlowController", "com.rem.parser.generation");
		ExternalClassEntry.suppliment("ParseContext", "com.rem.parser");
		ExternalClassEntry.suppliment("File", "java.io");
		ExternalClassEntry.suppliment("List", "java.util");
		ExternalClassEntry.suppliment("ArrayList", "java.util");
		ExternalClassEntry.suppliment("Set", "java.util");
		ExternalClassEntry.suppliment("HashSet", "java.util");
		ExternalClassEntry.suppliment("Map", "java.util");
		ExternalClassEntry.suppliment("HashMap", "java.util");
		ExternalClassEntry.suppliment("StringBuilder", "java.lang");
	}
	public void output(ParseContext data){
		ExternalImportEntry.solidify();
		getGenerators()[0].generate(data);
		for(Generator gen:privateFiles){
			gen.outputAll();
		}
		System.out.println("Output Complete");
	}

}