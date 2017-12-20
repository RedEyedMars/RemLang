package clgen;
import java.util.*;
import java.io.*;
import lists.*;
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
import com.rem.crg.parser.Token;
import clgen.ClassGenerator;
import com.rem.parser.generation.classwise.ExternalStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.StringBuilder;
import clgen.TypeStatement;
import clgen.MethodGenerator;
import clgen.BodyGenerator;
import clgen.VariableGenerator;
import com.rem.crg.parser.Parser;
import com.rem.parser.parser.IParser;
import com.rem.parser.parser.RegexParser;
import com.rem.parser.token.IToken;
import com.rem.parser.generation.FlowController;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.ParseContext;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
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
	protected final BodyGenerator __VAR__body = new BodyGenerator();
	protected final VariableGenerator __VAR__variable = new VariableGenerator();
	protected final String __VAR__packageName = "com.rem.crg.generator";
	protected final String __VAR__outerPackageName = "com.rem.cls";
	protected final String __VAR__innerPackageName = "com.rem.cli";
	protected final Map<String,ExternalClassEntry> __VAR__innerClasses = new HashMap<String,ExternalClassEntry>();
	protected final Map<String,ExternalClassEntry> __VAR__outerClasses = new HashMap<String,ExternalClassEntry>();
	protected final List<ExternalClassEntry> __VAR__outerClassList = new ArrayList<ExternalClassEntry>();
	protected final ExternalStatement.Body __VAR__addClassFileList = new ExternalStatement.Body();
	protected final ExternalStatement.Body __VAR__setupClassList = new ExternalStatement.Body();
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
	public BodyGenerator getBody()  {
		return __VAR__body;
	}
	public BodyGenerator get_body()  {
		return __VAR__body;
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
	public Map<String,ExternalClassEntry> getInnerClasses()  {
		return __VAR__innerClasses;
	}
	public Map<String,ExternalClassEntry> get_innerClasses()  {
		return __VAR__innerClasses;
	}
	public Map<String,ExternalClassEntry> getOuterClasses()  {
		return __VAR__outerClasses;
	}
	public Map<String,ExternalClassEntry> get_outerClasses()  {
		return __VAR__outerClasses;
	}
	public List<ExternalClassEntry> getOuterClassList()  {
		return __VAR__outerClassList;
	}
	public List<ExternalClassEntry> get_outerClassList()  {
		return __VAR__outerClassList;
	}
	public ExternalStatement.Body getAddClassFileList()  {
		return __VAR__addClassFileList;
	}
	public ExternalStatement.Body get_addClassFileList()  {
		return __VAR__addClassFileList;
	}
	public ExternalStatement.Body getSetupClassList()  {
		return __VAR__setupClassList;
	}
	public ExternalStatement.Body get_setupClassList()  {
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
		ExternalClassEntry.suppliment("Token", "com.rem.crg.parser");
		ExternalClassEntry.suppliment("Token", "com.rem.crg.parser");
		ExternalClassEntry.suppliment("Token", "com.rem.crg.parser");
		ExternalClassEntry.suppliment("Parser", "com.rem.crg.parser");
		ExternalClassEntry.suppliment("Token", "com.rem.crg.parser");
		ExternalClassEntry.suppliment("IParser", "com.rem.parser.parser");
		ExternalClassEntry.suppliment("RegexParser", "com.rem.parser.parser");
		ExternalClassEntry.suppliment("IToken", "com.rem.parser.token");
		ExternalClassEntry.suppliment("FlowController", "com.rem.parser.generation");
		ExternalClassEntry.suppliment("StringEntry", "com.rem.parser.generation");
		ExternalClassEntry.suppliment("ParseContext", "com.rem.parser");
		ExternalClassEntry.suppliment("File", "java.io");
		ExternalClassEntry.suppliment("List", "java.util");
		ExternalClassEntry.suppliment("ArrayList", "java.util");
		ExternalClassEntry.suppliment("Set", "java.util");
		ExternalClassEntry.suppliment("HashSet", "java.util");
		ExternalClassEntry.suppliment("Map", "java.util");
		ExternalClassEntry.suppliment("HashMap", "java.util");
		ExternalClassEntry.suppliment("StringBuilder", "java.lang");
		ExternalClassEntry.suppliment("ExternalStatement", "com.rem.parser.generation.classwise");
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