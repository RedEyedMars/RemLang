package com.rem.gen;
import java.util.*;
import java.io.*;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import com.rem.parser.generation.VariableNameEntry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.GeneralFlowController;
import com.rem.parser.generation.Generator;
import java.lang.StringBuilder;
import java.io.File;
import com.rem.gen.MainFlow;
import com.rem.gen.Parser;
import com.rem.parser.generation.classwise.ExternalStatement;
import com.rem.parser.generation.classwise.ExternalImportEntry;
import com.rem.parser.generation.classwise.ExternalClassHelper;
import com.rem.parser.generation.classwise.ExternalClassEntry;

public  class MainFlow extends GeneralFlowController{
	public static final MainFlow self = new MainFlow();
	public static final Set<ExternalClassEntry> outputClasses = new HashSet<ExternalClassEntry>();
	protected File __ROOTDIRECTORY__ = new File(".");
	protected final ExternalStatement.Body variableDeclarations = new ExternalStatement.Body();
	protected final ExternalStatement globalIgnoresHeader = new ExternalStatement();
	protected final Map<String,ExternalStatement> ruleIgnoresHeaders = new HashMap<String,ExternalStatement>();
	protected final Set<String> variableDeclarationNames = new HashSet<String>();
	protected final Map<String,Set<String>> ruleHeirachy = new HashMap<String,Set<String>>();
	protected final Set<String> listNames = new HashSet<String>();
	protected final Map<String,com.rem.gen.parser.Token> subRulesProposed = new HashMap<String,com.rem.gen.parser.Token>();
	protected final Set<String> declaredContexts = new HashSet<String>();
	protected String previousContextClassName = "AnonymousContext";
	protected final String parserPackageName = "com.rem.gen.parser";
	protected final String packageName = "com.rem.gen";
	protected final String charArray = "char[]";
	public File get_ROOTDIRECTORY(){
		return __ROOTDIRECTORY__;
	}
	public void set_ROOTDIRECTORY(File new_ROOTDIRECTORY){
		__ROOTDIRECTORY__ = new_ROOTDIRECTORY;
	}
	public static void main(String[] args){
		if (args.length==1 ){
			com.rem.gen.parser.Parser parser = new com.rem.gen.parser.Parser();
			com.rem.gen.parser.Parser.Result result = parser.parse(args[0]);
			System.out.println(result);
			if (result.getState()==com.rem.gen.parser.Parser.SUCCESS){
				self.setupRootDirectory(args[0]);
				self.setupGenerators();
				self.setup((com.rem.gen.parser.Parser.Result.Pass)result);
				self.generate((com.rem.gen.parser.Parser.Result.Pass.Pass)result);
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
			ExternalClassEntry.suppliment("CargonTokens","lists");
			ExternalClassEntry.suppliment("Rules","lists");
			ExternalClassEntry.suppliment("Listnames","lists");
			ExternalClassEntry.suppliment("Arrays","java.util");
			ExternalClassEntry.suppliment("CargonTokens","lists");
			ExternalClassEntry.suppliment("Rules","lists");
			ExternalClassEntry.suppliment("Listnames","lists");
			ExternalClassEntry.suppliment("Arrays","java.util");
			Parser._.__INIT__();
			MainFlow.outputClasses.add(Parser._);
			AnonymousContext._.__INIT__();
			MainFlow.outputClasses.add(AnonymousContext._);
			Tokens._.__INIT__();
			MainFlow.outputClasses.add(Tokens._);
			Token._.__INIT__();
			MainFlow.outputClasses.add(Token._);
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
	public ExternalStatement getGlobalIgnoresHeader(){
		return globalIgnoresHeader;
	}
	public Map<String,ExternalStatement> getRuleIgnoresHeaders(){
		return ruleIgnoresHeaders;
	}
	public Set<String> getVariableDeclarationNames(){
		return variableDeclarationNames;
	}
	public Map<String,Set<String>> getRuleHeirachy(){
		return ruleHeirachy;
	}
	public Set<String> getListNames(){
		return listNames;
	}
	public Map<String,com.rem.gen.parser.Token> getSubRulesProposed(){
		return subRulesProposed;
	}
	public Set<String> getDeclaredContexts(){
		return declaredContexts;
	}
	public String getPreviousContextClassName(){
		return previousContextClassName;
	}
	public void setPreviousContextClassName(String newPreviousContextClassName){
		previousContextClassName = newPreviousContextClassName;
	}
	public String getParserPackageName(){
		return parserPackageName;
	}
	public String getPackageName(){
		return packageName;
	}
	public String getCharArray(){
		return charArray;
	}
	public void generate(final com.rem.gen.parser.Parser.Result.Pass data){
		final com.rem.gen.parser.Token root = data.getRoot();
		final ExternalStatement.Body vb = new ExternalStatement.Body();
		vb.add(variableDeclarations);
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(vb);
		final ExternalStatement globalIgnoresHeaderVariableSection = new ExternalStatement();
		setupIgnoresHeader(globalIgnoresHeader,globalIgnoresHeaderVariableSection);
		if(root.get("ignores")!=null){
			for (final com.rem.gen.parser.Token element: root.getAllSafely("ignores")){
				for (final com.rem.gen.parser.Token atom: element.getAllSafely("ignoreCharacter")){
					addIgnoresCharacter((atom).toString(),globalIgnoresHeaderVariableSection);
				}
			}
		}
		else{
			addIgnoresCharacter(" ",globalIgnoresHeaderVariableSection);
			addIgnoresCharacter("\\t",globalIgnoresHeaderVariableSection);
			addIgnoresCharacter("\\n",globalIgnoresHeaderVariableSection);
		}
		for (final com.rem.gen.parser.Token list: root.getAllSafely("list")){
			Parser._.list(list);
		}
		for (final com.rem.gen.parser.Token rule: root.getAllSafely("rule")){
			Parser._.findSilentRule(rule);
		}
		for (final com.rem.gen.parser.Token rule: root.getAllSafely("rule")){
			Parser._.findRuleHeirachy(rule);
		}
		boolean hasAllRulesDefined = true;
		for(final String ruleName:subRulesProposed.keySet()){
			if(MainFlow.self.ruleHeirachy.containsKey(ruleName)==false&&MainFlow.self.listNames.contains(ruleName)==false){
				System.out.println("Could not find rule name: "+ruleName+" from :");
				MainFlow.self.subRulesProposed.get(ruleName).print();
				hasAllRulesDefined=false;
			}
		}
		if(hasAllRulesDefined){
			Parser._.consolidateRuleHeirachy();
			for (final com.rem.gen.parser.Token rule: root.getAllSafely("rule")){
				Parser._.define(rule,null);
			}
			Parser._.outputBraces();
			Parser._.output();
		}
	}
	public void setup(final com.rem.gen.parser.Parser.Result data){
		Parser._.setupCompile();
	}
	public void setupIgnoresHeader(final ExternalStatement toSetup,final ExternalStatement variableSection){
		toSetup.set("&&");
		variableSection.set("||");
		variableSection.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")));
		toSetup.add(new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))));
		toSetup.add(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(variableSection),""))));
	}
	public void addIgnoresCharacter(final String ignoresCharacter,final ExternalStatement variableSection){
		if(ignoresCharacter==null||ignoresCharacter.equals("")){
			variableSection.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),""))));
		}
		else{
			final StringBuilder characterBuilder = new StringBuilder();
			characterBuilder.append("'");
			characterBuilder.append(ignoresCharacter);
			characterBuilder.append("'");
			variableSection.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((characterBuilder).toString()),""))));
			if(ignoresCharacter.equals("\\n")){
				variableSection.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),""))));
			}
		}
	}
}