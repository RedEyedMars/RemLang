package com.rem.gen;
import java.util.*;
import java.io.*;
import java.util.Set;
import java.util.HashSet;
import com.rem.parser.generation.VariableNameEntry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.GeneralFlowController;
import com.rem.parser.generation.Generator;
import java.lang.StringBuilder;
import java.io.File;
import com.rem.gen.ExternalParser;
import com.rem.gen.MainFlow;
import com.rem.parser.generation.classwise.ExternalStatement;
import com.rem.parser.generation.classwise.ExternalImportEntry;
import com.rem.parser.generation.classwise.ExternalClassHelper;
import com.rem.parser.generation.classwise.ExternalClassEntry;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;

public  class MainFlow extends GeneralFlowController{
	public static final MainFlow self = new MainFlow();
	public static final Set<ExternalClassEntry> outputClasses = new HashSet<ExternalClassEntry>();
	protected File __ROOTDIRECTORY__ = new File(".");
	protected final ExternalStatement.Body variableDeclarations = new ExternalStatement.Body();
	protected final Set<String> variableDeclarationNames = new HashSet<String>();
	protected final String packageName = "com.rem.gen";
	protected final String charArray = "char[]";
	protected final ExternalStatement ignoresHeaderVariableSection = new ExternalStatement();
	protected final ExternalStatement ignoresHeaderStatement = new ExternalStatement();
	public File get_ROOTDIRECTORY(){
		return __ROOTDIRECTORY__;
	}
	public void set_ROOTDIRECTORY(File new_ROOTDIRECTORY){
		__ROOTDIRECTORY__ = new_ROOTDIRECTORY;
	}
	public static void main(String[] args){
		if (args.length==1 ){
			Parser parser = new Parser();
			Parser.Result result = parser.parse(args[0]);
			System.out.println(result);
			if (result.getState()==Parser.SUCCESS){
				self.setupRootDirectory(args[0]);
				self.setupGenerators();
				self.setup((Parser.Result.Pass)result);
				self.generate((Parser.Result.Pass)result);
				self.output();
			}
		}
		else {
			System.err.println("No Filename Provided!");
		}
	}
	public void setupRootDirectory(String fileName){
		int indexOfDot = fileName.lastIndexOf(".");
		if (indexOfDot>=0 ){
			int indexOfSlash = fileName.lastIndexOf("/");
			if (indexOfSlash>=0 ){
				__ROOTDIRECTORY__=new File("../"+camelize(fileName.substring(indexOfSlash,indexOfDot))+"/src");
			}
			else {
				__ROOTDIRECTORY__=new File("../"+camelize(fileName.substring(0,indexOfDot))+"/src");
			}
		}
		else {
			__ROOTDIRECTORY__=new File("../"+camelize(fileName)+"/src");
		}
		__ROOTDIRECTORY__.mkdirs();
	}
	public void setupGenerators(){
		ExternalClassHelper.setup();
		{
			ExternalClassEntry.suppliment("CargonTokens","");
			ExternalClassEntry.suppliment("Rules","");
			ExternalClassEntry.suppliment("Listnames","");
			ExternalParser._.__INIT__();
			MainFlow.outputClasses.add(ExternalParser._);
			ExternalParserTokens._.__INIT__();
			MainFlow.outputClasses.add(ExternalParserTokens._);
			ExternalParserToken._.__INIT__();
			MainFlow.outputClasses.add(ExternalParserToken._);
		};
	}
	public void output(){
		ExternalImportEntry.solidify();
		for (ExternalClassEntry outputClass: outputClasses){
			outputClass.outputToFile(this,__ROOTDIRECTORY__);
		}
		for (Generator gen:privateFiles){
			gen.outputAll();
		}
		System.out.println("Output Complete");
	}
	public ExternalStatement.Body getVariableDeclarations(){
		return variableDeclarations;
	}
	public Set<String> getVariableDeclarationNames(){
		return variableDeclarationNames;
	}
	public String getPackageName(){
		return packageName;
	}
	public String getCharArray(){
		return charArray;
	}
	public ExternalStatement getIgnoresHeaderVariableSection(){
		return ignoresHeaderVariableSection;
	}
	public ExternalStatement getIgnoresHeaderStatement(){
		return ignoresHeaderStatement;
	}
	public void setup(final Parser.Result data){
		ExternalParser._.setupCompile();
	}
	public void generate(final Parser.Result.Pass data){
		final Token root = data.getRoot();
		final ExternalStatement.Body vb = new ExternalStatement.Body();
		vb.add(variableDeclarations);
		ExternalClassEntry.classMap.get("ExternalParser").getSubClass("Context").getMethod("parse").appendToBody(vb);
		setupIgnoresStatement();
		if(root.get("ignores")!=null){
			for (Token element: root.getAllSafely("ignores")){
				for (Token atom: element.getAllSafely("ignoreCharacter")){
					addIgnoresCharacter((atom).toString());
				}
			}
		}
		else{
			addIgnoresCharacter("");
			addIgnoresCharacter("\\t");
			addIgnoresCharacter("\\n");
		}
		for (Token list: root.getAllSafely("list")){
			ExternalParser._.list(list);
		}
		for (Token rule: root.getAllSafely("rule")){
			ExternalParser._.define(rule,null);
		}
		ExternalParser._.outputBraces();
		ExternalParser._.output();
	}
	public void setupIgnoresStatement(){
		ignoresHeaderStatement.set("&&");
		ignoresHeaderVariableSection.set("||");
		ignoresHeaderVariableSection.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")));
		ignoresHeaderStatement.add(new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))));
		ignoresHeaderStatement.add(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(ignoresHeaderVariableSection),"")))));
	}
	public void addIgnoresCharacter(final String ignoresCharacter){
		if(ignoresCharacter.equals("")){
			MainFlow.self.ignoresHeaderVariableSection.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),""))));
		}
		else{
			final StringBuilder characterBuilder = new StringBuilder();
			characterBuilder.append("'");
			characterBuilder.append(ignoresCharacter);
			characterBuilder.append("'");
			MainFlow.self.ignoresHeaderVariableSection.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((characterBuilder).toString()),""))));
			if(ignoresCharacter.equals("\\n")){
				MainFlow.self.ignoresHeaderVariableSection.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),""))));
			}
		}
	}
}