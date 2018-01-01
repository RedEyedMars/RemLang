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
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuilder;
import java.lang.Character;
import com.rem.parser.ParseContext;
import com.rem.parser.token.IToken;
import com.rem.parser.parser.RegexParser;
import com.rem.parser.parser.IParser;
import lists.CargonTokens;
import lists.Rules;
import lists.Listnames;
public class MainFlow extends FlowController  {
	public static class classes {
		public static final Parser ParserClass = new Parser();
		public static final Tokens TokensClass = new Tokens();
		public static final Token TokenClass = new Token();
	}
	public static MainFlow variables = null;
	public static MainFlow methods = null;
	//Externals


	//Internals
	protected final IParser __VAR__rootParser = Rules.base;
	protected final List __VAR__listnames = Listnames.parser;
	protected final List __VAR__parserRules = Rules.parser;
	protected final RegexParser __VAR__lazyNameParser = CargonTokens.NAME;
	protected final ExternalStatement.Body __VAR__variableDeclarations = new ExternalStatement.Body();
	protected final Set<String> __VAR__variableDeclarationNames = new HashSet<String>();
	protected final ExternalStatement __VAR__globalIgnoresHeader = new ExternalStatement();
	protected final Map<String,ExternalStatement> __VAR__ruleIgnoresHeaders = new HashMap<String,ExternalStatement>();
	protected final String __VAR__packageName = "com.rem.gen";
	protected final String __VAR__charArray = "char[]";

	public static void main(final String[] args)  {
		if(args.length==1) {
	new MainFlow().parse(args[0]);
		}
		else {
	System.err.println("No filename provided!");
		}
	}
	public List getRules()  {
		return MainFlow.variables.get_parserRules();
	}
	public void assignListElementNames(final ParseContext context,final IToken root)  {
	}
	public void setup(final ParseContext data)  {
	}
	public void generate(final ParseContext data)  {
		String projectName = data.getFileName();
		final Integer dotIndex = projectName.indexOf(".");
		if (dotIndex > -1 ) {
			projectName = projectName.substring(0,dotIndex);
		}
		MainFlow.methods.setup("../" + FlowController.camelize(projectName.toString()) + "/src");
		MainFlow.classes.ParserClass.setupCompile();
		final IToken root = data.getRoot();
		final ExternalStatement.Body vb = new ExternalStatement.Body();
		vb.add(MainFlow.variables.get_variableDeclarations());
		MainFlow.classes.ParserClass.ContextClass.getMethod("parse").appendToBody(vb);
		final ExternalStatement globalIgnoresHeaderVariableSection = new ExternalStatement();
		MainFlow.methods.setupIgnoresHeader(MainFlow.variables.get_globalIgnoresHeader(),globalIgnoresHeaderVariableSection);
		if (root.get("ignores") != null) {
			for(final IToken element:root.getAllSafely("ignores")) {
					for(final IToken atom:element.getAllSafely("ignoreCharacter")) {
						MainFlow.methods.addIgnoresCharacter(atom.toString(),globalIgnoresHeaderVariableSection);
					}
			}
		}
		else  {
			MainFlow.methods.addIgnoresCharacter(" ",globalIgnoresHeaderVariableSection);
			MainFlow.methods.addIgnoresCharacter("\\t",globalIgnoresHeaderVariableSection);
			MainFlow.methods.addIgnoresCharacter("\\n",globalIgnoresHeaderVariableSection);
		}
		for(final IToken list:root.getAllSafely("list")) {
				MainFlow.classes.ParserClass.list(list);
		}
		for(final IToken rule:root.getAllSafely("rule")) {
				MainFlow.classes.ParserClass.findSilentRule(rule);
		}
		for(final IToken rule:root.getAllSafely("rule")) {
				MainFlow.classes.ParserClass.findRuleHeirachy(rule);
		}
		MainFlow.classes.ParserClass.consolidateRuleHeirachy();
		for(final IToken rule:root.getAllSafely("rule")) {
				MainFlow.classes.ParserClass.define(rule,null);
		}
		for(final IToken list:root.getAllSafely("list")) {
				MainFlow.classes.ParserClass.list(list);
		}
		MainFlow.classes.ParserClass.outputBraces();
		MainFlow.classes.ParserClass.output();
		output(data);
	}
	public void setupIgnoresHeader(final ExternalStatement toSetup,final ExternalStatement variableSection)  {
		toSetup.set("&&");
		variableSection.set("||");
		variableSection.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("false")))));
		toSetup.add(/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))));
		toSetup.add(/*Name*/new ExternalStatement(/*Brac*/new ExternalStatement(new StringEntry("("),new StringEntry(")"),"", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(variableSection))))));
	}
	public void addIgnoresCharacter(final String ignoresCharacter,final ExternalStatement variableSection)  {
		if (ignoresCharacter.equals("")) {
			variableSection.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("' '")))));
		}
		else  {
			final StringBuilder characterBuilder = new StringBuilder();
			characterBuilder.append("'");
			characterBuilder.append(ignoresCharacter);
			characterBuilder.append("'");
			variableSection.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(characterBuilder.toString())))));
			if (ignoresCharacter.equals("\\n")) {
				variableSection.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\r'")))));
			}
		}
	}
	public IParser getRootParser()  {
		return __VAR__rootParser;
	}
	public IParser get_rootParser()  {
		return __VAR__rootParser;
	}
	public List getListnames()  {
		return __VAR__listnames;
	}
	public List get_listnames()  {
		return __VAR__listnames;
	}
	public List getParserRules()  {
		return __VAR__parserRules;
	}
	public List get_parserRules()  {
		return __VAR__parserRules;
	}
	public RegexParser getLazyNameParser()  {
		return __VAR__lazyNameParser;
	}
	public RegexParser get_lazyNameParser()  {
		return __VAR__lazyNameParser;
	}
	public ExternalStatement.Body getVariableDeclarations()  {
		return __VAR__variableDeclarations;
	}
	public ExternalStatement.Body get_variableDeclarations()  {
		return __VAR__variableDeclarations;
	}
	public Set<String> getVariableDeclarationNames()  {
		return __VAR__variableDeclarationNames;
	}
	public Set<String> get_variableDeclarationNames()  {
		return __VAR__variableDeclarationNames;
	}
	public ExternalStatement getGlobalIgnoresHeader()  {
		return __VAR__globalIgnoresHeader;
	}
	public ExternalStatement get_globalIgnoresHeader()  {
		return __VAR__globalIgnoresHeader;
	}
	public Map<String,ExternalStatement> getRuleIgnoresHeaders()  {
		return __VAR__ruleIgnoresHeaders;
	}
	public Map<String,ExternalStatement> get_ruleIgnoresHeaders()  {
		return __VAR__ruleIgnoresHeaders;
	}
	public String getPackageName()  {
		return __VAR__packageName;
	}
	public String get_packageName()  {
		return __VAR__packageName;
	}
	public String getCharArray()  {
		return __VAR__charArray;
	}
	public String get_charArray()  {
		return __VAR__charArray;
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
				__BUILDER__ = new StringBuilder();
	/*Enty*/new ExternalStatement(new StringEntry(MainFlow.variables.get_packageName().toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
	__DIRECTORY__ = new File(__ROOT_DIRECTORY__, __BUILDER__.toString().replace(".","/"));
	__DIRECTORY__.mkdirs();
	addFile(__DIRECTORY__,FlowController.camelize("Parser")+".java", MainFlow.classes.ParserClass);
				__BUILDER__ = new StringBuilder();
	/*Enty*/new ExternalStatement(new StringEntry(MainFlow.variables.get_packageName().toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
	__DIRECTORY__ = new File(__ROOT_DIRECTORY__, __BUILDER__.toString().replace(".","/"));
	__DIRECTORY__.mkdirs();
	addFile(__DIRECTORY__,FlowController.camelize("Tokens")+".java", MainFlow.classes.TokensClass);
				__BUILDER__ = new StringBuilder();
	/*Enty*/new ExternalStatement(new StringEntry(MainFlow.variables.get_packageName().toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
	__DIRECTORY__ = new File(__ROOT_DIRECTORY__, __BUILDER__.toString().replace(".","/"));
	__DIRECTORY__.mkdirs();
	addFile(__DIRECTORY__,FlowController.camelize("Token")+".java", MainFlow.classes.TokenClass);
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
		ExternalClassEntry.suppliment("Stack", "java.util");
		ExternalClassEntry.suppliment("List", "java.util");
		ExternalClassEntry.suppliment("ArrayList", "java.util");
		ExternalClassEntry.suppliment("Set", "java.util");
		ExternalClassEntry.suppliment("HashSet", "java.util");
		ExternalClassEntry.suppliment("Map", "java.util");
		ExternalClassEntry.suppliment("HashMap", "java.util");
		ExternalClassEntry.suppliment("BufferedReader", "java.io");
		ExternalClassEntry.suppliment("FileReader", "java.io");
		ExternalClassEntry.suppliment("StringBuilder", "java.lang");
		ExternalClassEntry.suppliment("Character", "java.lang");
		ExternalClassEntry.suppliment("ParseContext", "com.rem.parser");
		ExternalClassEntry.suppliment("IToken", "com.rem.parser.token");
		ExternalClassEntry.suppliment("RegexParser", "com.rem.parser.parser");
		ExternalClassEntry.suppliment("IParser", "com.rem.parser.parser");
		ExternalClassEntry.suppliment("CargonTokens", "lists");
		ExternalClassEntry.suppliment("Rules", "lists");
		ExternalClassEntry.suppliment("Listnames", "lists");
		MainFlow.classes.ParserClass.__INIT__();
		MainFlow.classes.TokensClass.__INIT__();
		MainFlow.classes.TokenClass.__INIT__();
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