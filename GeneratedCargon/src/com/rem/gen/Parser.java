package com.rem.gen;
import java.util.*;
import java.io.*;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import com.rem.parser.generation.VariableNameEntry;
import com.rem.parser.generation.Entry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.TabEntry;
import java.lang.StringBuilder;
import com.rem.parser.generation.classwise.ExternalMethodEntry;
import com.rem.parser.generation.classwise.ExternalStatement;
import com.rem.parser.generation.classwise.ExternalVariableEntry;
import com.rem.parser.generation.classwise.ExternalClassEntry;
import com.rem.gen.Parser;

public class Parser extends ExternalClassEntry{
	public static ExternalStatement.Body readInputBody = new ExternalStatement.Body();
	protected ExternalStatement.Body braceVariableDeclaration = new ExternalStatement.Body();
	protected String notQuoting = "!quoting";
	protected String notEscaping = "!escaping";
	protected Map<String,List<ExternalStatement.Body>> rules = new HashMap<String,List<ExternalStatement.Body>>();
	protected Map<String,List<ExternalStatement.Body>> completeRules = new HashMap<String,List<ExternalStatement.Body>>();
	protected Map<ExternalStatement.Body,String> ruleHolders = new HashMap<ExternalStatement.Body,String>();
	protected Map<String,Map<Integer,List<ExternalStatement.Body>>> unsatisfiedRules = new HashMap<String,Map<Integer,List<ExternalStatement.Body>>>();
	protected Map<String,ExternalStatement.Body> ruleForeBodies = new HashMap<String,ExternalStatement.Body>();
	protected Map<String,String> parentRuleNames = new HashMap<String,String>();
	protected Set<String> silentRules = new HashSet<String>();
	protected Set<String> listNames = new HashSet<String>();
	protected Set<String> globalListNames = new HashSet<String>();
	protected Map<String,String> listFirstPassRules = new HashMap<String,String>();
	protected Map<String,Set<String>> listNamesInRule = new HashMap<String,Set<String>>();
	protected Map<String,String> handleListAdditions = new HashMap<String,String>();
	protected Map<String,ExternalStatement.Body> handleListAdditionAftBodies = new HashMap<String,ExternalStatement.Body>();
	protected Set<String> handleRecursionProtection = new HashSet<String>();
	protected Integer tokenId = 1;
	protected String ROOT_NAME = null;
	protected ExternalStatement bracedCondition = new ExternalStatement();
	protected Set<String> declaredBraces = new HashSet<String>();
	protected Map<String,List<String>> declaredBraceValues = new HashMap<String,List<String>>();
	protected Map<String,String> declaredBraceRules = new HashMap<String,String>();
	protected Map<String,Integer> braceIds = new HashMap<String,Integer>();
	protected Map<String,Integer> declaredBraceOpenLengths = new HashMap<String,Integer>();
	protected Map<String,Integer> declaredBraceCloseLengths = new HashMap<String,Integer>();
	protected Map<Integer,Map<String,List<Integer>>> declaredBraceOpenValues = new HashMap<Integer,Map<String,List<Integer>>>();
	protected Map<Integer,Map<String,List<Integer>>> declaredBraceCloseValues = new HashMap<Integer,Map<String,List<Integer>>>();
	protected Map<String,Integer> declaredBracePasses = new HashMap<String,Integer>();
	protected Map<String,Set<String>> ruleHeirachy = new HashMap<String,Set<String>>();
	protected Integer currentPositionIndex = 0;
	protected Integer anonymousRuleIndex = 0;
	protected String plainTokenClassName = "_0";
	protected Integer plainTokenIndex = 0;
	protected Set<String> createdPlainTokens = new HashSet<String>();
	protected Map<String,String> createdSyntaxTokens = new HashMap<String,String>();
	protected Map<String,Set<String>> createdSyntaxNameTokens = new HashMap<String,Set<String>>();
	protected Set<String> createdNameTokens = new HashSet<String>();
	protected Integer listIndex = 0;
	protected Integer multipleIndex = 0;
	protected Integer recursionIndex = 0;
	protected Integer groupSuccessfulPositionIndex = 0;
	public static final Parser _ = new Parser();
	public void setReadInputBody(ExternalStatement.Body newReadInputBody){
		readInputBody = newReadInputBody;
	}
	public ExternalStatement.Body getBraceVariableDeclaration(){
		return braceVariableDeclaration;
	}
	public void setBraceVariableDeclaration(ExternalStatement.Body newBraceVariableDeclaration){
		braceVariableDeclaration = newBraceVariableDeclaration;
	}
	public String getNotQuoting(){
		return notQuoting;
	}
	public void setNotQuoting(String newNotQuoting){
		notQuoting = newNotQuoting;
	}
	public String getNotEscaping(){
		return notEscaping;
	}
	public void setNotEscaping(String newNotEscaping){
		notEscaping = newNotEscaping;
	}
	public Map<String,List<ExternalStatement.Body>> getRules(){
		return rules;
	}
	public void setRules(Map<String,List<ExternalStatement.Body>> newRules){
		rules = newRules;
	}
	public Map<String,List<ExternalStatement.Body>> getCompleteRules(){
		return completeRules;
	}
	public void setCompleteRules(Map<String,List<ExternalStatement.Body>> newCompleteRules){
		completeRules = newCompleteRules;
	}
	public Map<ExternalStatement.Body,String> getRuleHolders(){
		return ruleHolders;
	}
	public void setRuleHolders(Map<ExternalStatement.Body,String> newRuleHolders){
		ruleHolders = newRuleHolders;
	}
	public Map<String,Map<Integer,List<ExternalStatement.Body>>> getUnsatisfiedRules(){
		return unsatisfiedRules;
	}
	public void setUnsatisfiedRules(Map<String,Map<Integer,List<ExternalStatement.Body>>> newUnsatisfiedRules){
		unsatisfiedRules = newUnsatisfiedRules;
	}
	public Map<String,ExternalStatement.Body> getRuleForeBodies(){
		return ruleForeBodies;
	}
	public void setRuleForeBodies(Map<String,ExternalStatement.Body> newRuleForeBodies){
		ruleForeBodies = newRuleForeBodies;
	}
	public Map<String,String> getParentRuleNames(){
		return parentRuleNames;
	}
	public void setParentRuleNames(Map<String,String> newParentRuleNames){
		parentRuleNames = newParentRuleNames;
	}
	public Set<String> getSilentRules(){
		return silentRules;
	}
	public void setSilentRules(Set<String> newSilentRules){
		silentRules = newSilentRules;
	}
	public Set<String> getListNames(){
		return listNames;
	}
	public void setListNames(Set<String> newListNames){
		listNames = newListNames;
	}
	public Set<String> getGlobalListNames(){
		return globalListNames;
	}
	public void setGlobalListNames(Set<String> newGlobalListNames){
		globalListNames = newGlobalListNames;
	}
	public Map<String,String> getListFirstPassRules(){
		return listFirstPassRules;
	}
	public void setListFirstPassRules(Map<String,String> newListFirstPassRules){
		listFirstPassRules = newListFirstPassRules;
	}
	public Map<String,Set<String>> getListNamesInRule(){
		return listNamesInRule;
	}
	public void setListNamesInRule(Map<String,Set<String>> newListNamesInRule){
		listNamesInRule = newListNamesInRule;
	}
	public Map<String,String> getHandleListAdditions(){
		return handleListAdditions;
	}
	public void setHandleListAdditions(Map<String,String> newHandleListAdditions){
		handleListAdditions = newHandleListAdditions;
	}
	public Map<String,ExternalStatement.Body> getHandleListAdditionAftBodies(){
		return handleListAdditionAftBodies;
	}
	public void setHandleListAdditionAftBodies(Map<String,ExternalStatement.Body> newHandleListAdditionAftBodies){
		handleListAdditionAftBodies = newHandleListAdditionAftBodies;
	}
	public Set<String> getHandleRecursionProtection(){
		return handleRecursionProtection;
	}
	public void setHandleRecursionProtection(Set<String> newHandleRecursionProtection){
		handleRecursionProtection = newHandleRecursionProtection;
	}
	public Integer getTokenId(){
		return tokenId;
	}
	public void setTokenId(Integer newTokenId){
		tokenId = newTokenId;
	}
	public String getROOTNAME(){
		return ROOT_NAME;
	}
	public void setROOTNAME(String newROOTNAME){
		ROOT_NAME = newROOTNAME;
	}
	public ExternalStatement getBracedCondition(){
		return bracedCondition;
	}
	public void setBracedCondition(ExternalStatement newBracedCondition){
		bracedCondition = newBracedCondition;
	}
	public Set<String> getDeclaredBraces(){
		return declaredBraces;
	}
	public void setDeclaredBraces(Set<String> newDeclaredBraces){
		declaredBraces = newDeclaredBraces;
	}
	public Map<String,List<String>> getDeclaredBraceValues(){
		return declaredBraceValues;
	}
	public void setDeclaredBraceValues(Map<String,List<String>> newDeclaredBraceValues){
		declaredBraceValues = newDeclaredBraceValues;
	}
	public Map<String,String> getDeclaredBraceRules(){
		return declaredBraceRules;
	}
	public void setDeclaredBraceRules(Map<String,String> newDeclaredBraceRules){
		declaredBraceRules = newDeclaredBraceRules;
	}
	public Map<String,Integer> getBraceIds(){
		return braceIds;
	}
	public void setBraceIds(Map<String,Integer> newBraceIds){
		braceIds = newBraceIds;
	}
	public Map<String,Integer> getDeclaredBraceOpenLengths(){
		return declaredBraceOpenLengths;
	}
	public void setDeclaredBraceOpenLengths(Map<String,Integer> newDeclaredBraceOpenLengths){
		declaredBraceOpenLengths = newDeclaredBraceOpenLengths;
	}
	public Map<String,Integer> getDeclaredBraceCloseLengths(){
		return declaredBraceCloseLengths;
	}
	public void setDeclaredBraceCloseLengths(Map<String,Integer> newDeclaredBraceCloseLengths){
		declaredBraceCloseLengths = newDeclaredBraceCloseLengths;
	}
	public Map<Integer,Map<String,List<Integer>>> getDeclaredBraceOpenValues(){
		return declaredBraceOpenValues;
	}
	public void setDeclaredBraceOpenValues(Map<Integer,Map<String,List<Integer>>> newDeclaredBraceOpenValues){
		declaredBraceOpenValues = newDeclaredBraceOpenValues;
	}
	public Map<Integer,Map<String,List<Integer>>> getDeclaredBraceCloseValues(){
		return declaredBraceCloseValues;
	}
	public void setDeclaredBraceCloseValues(Map<Integer,Map<String,List<Integer>>> newDeclaredBraceCloseValues){
		declaredBraceCloseValues = newDeclaredBraceCloseValues;
	}
	public Map<String,Integer> getDeclaredBracePasses(){
		return declaredBracePasses;
	}
	public void setDeclaredBracePasses(Map<String,Integer> newDeclaredBracePasses){
		declaredBracePasses = newDeclaredBracePasses;
	}
	public Map<String,Set<String>> getRuleHeirachy(){
		return ruleHeirachy;
	}
	public void setRuleHeirachy(Map<String,Set<String>> newRuleHeirachy){
		ruleHeirachy = newRuleHeirachy;
	}
	public Integer getCurrentPositionIndex(){
		return currentPositionIndex;
	}
	public void setCurrentPositionIndex(Integer newCurrentPositionIndex){
		currentPositionIndex = newCurrentPositionIndex;
	}
	public Integer getAnonymousRuleIndex(){
		return anonymousRuleIndex;
	}
	public void setAnonymousRuleIndex(Integer newAnonymousRuleIndex){
		anonymousRuleIndex = newAnonymousRuleIndex;
	}
	public String getPlainTokenClassName(){
		return plainTokenClassName;
	}
	public void setPlainTokenClassName(String newPlainTokenClassName){
		plainTokenClassName = newPlainTokenClassName;
	}
	public Integer getPlainTokenIndex(){
		return plainTokenIndex;
	}
	public void setPlainTokenIndex(Integer newPlainTokenIndex){
		plainTokenIndex = newPlainTokenIndex;
	}
	public Set<String> getCreatedPlainTokens(){
		return createdPlainTokens;
	}
	public void setCreatedPlainTokens(Set<String> newCreatedPlainTokens){
		createdPlainTokens = newCreatedPlainTokens;
	}
	public Map<String,String> getCreatedSyntaxTokens(){
		return createdSyntaxTokens;
	}
	public void setCreatedSyntaxTokens(Map<String,String> newCreatedSyntaxTokens){
		createdSyntaxTokens = newCreatedSyntaxTokens;
	}
	public Map<String,Set<String>> getCreatedSyntaxNameTokens(){
		return createdSyntaxNameTokens;
	}
	public void setCreatedSyntaxNameTokens(Map<String,Set<String>> newCreatedSyntaxNameTokens){
		createdSyntaxNameTokens = newCreatedSyntaxNameTokens;
	}
	public Set<String> getCreatedNameTokens(){
		return createdNameTokens;
	}
	public void setCreatedNameTokens(Set<String> newCreatedNameTokens){
		createdNameTokens = newCreatedNameTokens;
	}
	public Integer getListIndex(){
		return listIndex;
	}
	public void setListIndex(Integer newListIndex){
		listIndex = newListIndex;
	}
	public Integer getMultipleIndex(){
		return multipleIndex;
	}
	public void setMultipleIndex(Integer newMultipleIndex){
		multipleIndex = newMultipleIndex;
	}
	public Integer getRecursionIndex(){
		return recursionIndex;
	}
	public void setRecursionIndex(Integer newRecursionIndex){
		recursionIndex = newRecursionIndex;
	}
	public Integer getGroupSuccessfulPositionIndex(){
		return groupSuccessfulPositionIndex;
	}
	public void setGroupSuccessfulPositionIndex(Integer newGroupSuccessfulPositionIndex){
		groupSuccessfulPositionIndex = newGroupSuccessfulPositionIndex;
	}
	public void output(){
		if(ROOT_NAME==null){
			System.err.println("No root rule found!");
		}
		else{
			for(final String ruleName:rules.keySet()){
				final ExternalStatement.Body ruleBody = new ExternalStatement.Body();
				getRuleBody(ruleBody,ruleName,new HashMap<String,Set<Integer>>());
			}
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((ROOT_NAME).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new StringEntry("_succeedOnEnd"),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))),"",new ExternalStatement(new StringEntry("pass"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_root"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("pass"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setup"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("pass"),"")))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")))))))),new ExternalStatement.Conditional("else if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(" tail of file could not be parsed"),"")))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")))))),new ExternalStatement.Conditional("else if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),""))))))));
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new ExternalStatement(new StringEntry("parseTime"),""),new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("currentTimeMillis"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("startParseTime"),"")))))));
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setParseTime"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")))))))));
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")))));
			ExternalClassEntry.classMap.get("Parser").getMethod("parseFile").appendToBody(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new ExternalStatement(new StringEntry("context"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("parse"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(new StringEntry("_root"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("$ROOT"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(new StringEntry("_root"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("parse"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),"")))))))))));
		}
	}
	public void outputBraces(){
		Integer maxOpen = 0;
		for(final Integer key:declaredBraceOpenValues.keySet()){
			if(key>maxOpen){
				maxOpen=key;
			}
		}
		Integer maxClose = 0;
		for(final Integer key:declaredBraceCloseValues.keySet()){
			if(key>maxClose){
				maxClose=key;
			}
		}
		Integer max = 0;
		if(maxOpen>maxClose){
			max=maxOpen;
		}
		else{
			max=maxClose;
		}
		final ExternalStatement.Body readBracesBody = new ExternalStatement.Body();
		readInputBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("13 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputBuffer"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("(char)_readInput"),""))))))))),new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new StringEntry("\\n"),""),new ExternalStatement(new StringEntry("'"),"")))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))),new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new StringEntry("escaping"),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("escaping"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))))),new ExternalStatement.Conditional("else if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((notEscaping).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\\"),"")))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("escaping"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))))),new ExternalStatement.Conditional("else if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((notQuoting).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\""),"")))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("quoting"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("brace_open_0"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))),new ExternalStatement.Conditional("else if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","&&","=="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("quoting"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\""),"")))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("quoting"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("brace_0"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("brace_open_0"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("pop"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))),new ExternalStatement.Conditional("else if",new ExternalStatement("&&",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((notQuoting).toString()),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((notEscaping).toString()),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(readBracesBody),""))),new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("13 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))));
		Integer i = maxOpen;
		while(i>0 ){
			if(declaredBraceCloseValues.containsKey(i)){
				final Map<String,List<Integer>> closeMap = declaredBraceCloseValues.get(i);
				for(final String key:closeMap.keySet()){
					final ExternalStatement.Body closeBody = new ExternalStatement.Body();
					final ExternalStatement closeHeader = new ExternalStatement();
					Integer j = i;
					closeHeader.set("&&");
					final Integer jndexFirst = j;
					if(new Character(key.charAt(jndexFirst-1)).toString().equals("\'")){
						closeHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndexFirst-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					else{
						closeHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndexFirst-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					j-=1;
					while(j>0 ){
						final Integer jndex = j;
						if(new Character(key.charAt(j-1)).toString().equals("\'")){
							closeHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndex-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						}
						else{
							closeHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndex-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						}
						j-=1;
					}
					readBracesBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(closeHeader),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(closeBody),"")))));
					if(key.length()==1 ){
						for(final Integer id:closeMap.get(key)){
							closeBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("!brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("pop"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))));
						}
					}
					else{
						final Integer keyOffset = key.length()-1;
						for(final Integer id:closeMap.get(key)){
							closeBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("!brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("pop"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((keyOffset).toString()),""))))))))))));
						}
					}
				}
			}
			if(declaredBraceOpenValues.containsKey(i)){
				final Map<String,List<Integer>> openMap = declaredBraceOpenValues.get(i);
				for(final String key:openMap.keySet()){
					final ExternalStatement.Body openBody = new ExternalStatement.Body();
					final ExternalStatement openHeader = new ExternalStatement();
					Integer j = i;
					openHeader.set("&&");
					final Integer jndexFirst = j;
					if(new Character(key.charAt(jndexFirst-1)).toString().equals("\'")){
						openHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndexFirst-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					else{
						openHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndexFirst-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					j-=1;
					while(j>0 ){
						final Integer jndex = j;
						if(new Character(key.charAt(j-1)).toString().equals("\'")){
							openHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndex-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						}
						else{
							openHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndex-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						}
						j-=1;
					}
					readBracesBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(openHeader),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(openBody),"")))));
					if(key.length()==1 ){
						for(final Integer id:openMap.get(key)){
							openBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))));
						}
					}
					else{
						final Integer keyOffset = key.length()-1;
						for(final Integer id:openMap.get(key)){
							openBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((keyOffset).toString()),""))))))))));
						}
					}
				}
			}
			i-=1;
		}
		i=max-1;
		while(i>1 ){
			final Integer index = i;
			final Integer jndex = i-1;
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((index).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
			readInputBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((index).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),"")))))));
			i-=1;
		}
		if(max>0 ){
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput_1"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
			readInputBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput_1"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),""))))));
		}
		readInputBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputReader"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("read"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
	}
	public void findSilentRule(final com.rem.gen.parser.Token input){
		if(input.get("ruleName")!=null){
			final String ruleName = (input.get("ruleName")).toString();
			if(input.get("SILENT")!=null){
				silentRules.add(ruleName);
			}
			if(ROOT_NAME==null){
				ROOT_NAME=ruleName;
			}
		}
	}
	public void setupCompile(){
		declaredBraces.add("\"\"");
		final Integer braceId = braceIds.size();
		braceIds.put("\"\"",braceId);
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceId).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").prependToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Stack"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((braceId).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Stack"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
	}
	public void findRuleHeirachy(final com.rem.gen.parser.Token ruleToken){
		if(ruleToken.get("ruleName")!=null){
			final String ruleName = (ruleToken.get("ruleName")).toString();
			ruleHeirachy.put(ruleName,new HashSet<String>());
			if(ROOT_NAME==null){
				ROOT_NAME=ruleName;
			}
			for (com.rem.gen.parser.Token definition: ruleToken.getAllSafely("definition")){
				findRuleHeirachy(ruleName,definition,ruleHeirachy.get(ruleName));
			}
		}
	}
	public void findRuleHeirachy(final String ruleName,final com.rem.gen.parser.Token definition,final Set<String> subRuleSet){
		Boolean isFirst = true;
		for (com.rem.gen.parser.Token chain: definition.getAllSafely("chain")){
			for (com.rem.gen.parser.Token element: chain.getAllSafely("element")){
				for (com.rem.gen.parser.Token atom: element.getAll()){
					if (atom.getName().equals("multiple")){
						for (com.rem.gen.parser.Token quark: atom.getAllSafely("definition")){
							findRuleHeirachy(ruleName,quark,subRuleSet);
						}
					}
					if (atom.getName().equals("braced_definition")){
						for (com.rem.gen.parser.Token quark: atom.getAllSafely("definition")){
							findRuleHeirachy(ruleName,quark,subRuleSet);
						}
					}
					if (atom.getName().equals("ruleToken")){
						subRuleSet.add((element).toString());
					}
				}
			}
		}
		for (com.rem.gen.parser.Token choice: definition.getAllSafely("choice")){
			for (com.rem.gen.parser.Token element: choice.getAllSafely("definition")){
				findRuleHeirachy(ruleName,element,subRuleSet);
			}
		}
	}
	public void consolidateRuleHeirachy(){
		final Map<String,Set<String>> additions = new HashMap<String,Set<String>>();
		additions.put(ROOT_NAME,new HashSet<String>());
		consolidateRuleHeirachy(ROOT_NAME,additions,new HashSet<String>());
		for(final String ruleName:additions.keySet()){
			if(ruleHeirachy.containsKey(ruleName)){
				ruleHeirachy.get(ruleName).addAll(additions.get(ruleName));
			}
		}
	}
	public void consolidateRuleHeirachy(final String ruleName,final Map<String,Set<String>> additions,final Set<String> consulted){
		if(consulted.add(ruleName)){
			if(ruleHeirachy.containsKey(ruleName)){
				for(final String subRuleName:ruleHeirachy.get(ruleName)){
					if(additions.containsKey(subRuleName)){
						additions.get(subRuleName).add(ruleName);
					}
					else{
						additions.put(subRuleName,new HashSet<String>());
						consolidateRuleHeirachy(subRuleName,additions,consulted);
					}
				}
			}
		}
	}
	public void list(final com.rem.gen.parser.Token input){
		final String listName = (input.get("listName")).toString();
		ExternalClassEntry.classMap.get("Parser").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Root"),""))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_root"),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Root"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((input.get("listName")).toString()),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((input.get("listName")).toString()),""),new ExternalStatement(new StringEntry("_root"),"")))));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((input.get("listName")).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),"")))))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((input.get("listName")).toString()),""),new ExternalStatement(new StringEntry("_first_passes"),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),"")))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		listNames.add((input.get("listName")).toString());
		if(input.get("GLOBAL")!=null){
			globalListNames.add((input.get("listName")).toString());
		}
		if(input.get("listRuleName")!=null){
			listFirstPassRules.put((listName).toString(),(input.get("listRuleName")).toString());
		}
		for (com.rem.gen.parser.Token element: input.getAllSafely("quote")){
			ExternalClassEntry.classMap.get("Parser").getMethod("parseFile").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_root"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element).toString()),"")))))))))));
		}
	}
	public ExternalStatement.Body getListDeclarations(final String ruleName,final boolean hasSecondPass){
		final ExternalStatement.Body listDeclarations = new ExternalStatement.Body();
		for(final String listName:listNames){
			if(globalListNames.contains(listName)==false){
				final String subListName = (listName).toString()+"_"+(ruleName).toString();
				if(MainFlow.self.variableDeclarationNames.add(subListName)){
					ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((subListName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
				}
				listDeclarations.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((subListName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),""))))));
				if(hasSecondPass){
					listDeclarations.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Child"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")))))))))));
				}
				else{
					listDeclarations.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Child"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")))))))));
				}
			}
		}
		return listDeclarations;
	}
	public ExternalStatement.Body getListReallocations(final String ruleName){
		final ExternalStatement.Body listReallocations = new ExternalStatement.Body();
		for(final String listName:listNames){
			if(globalListNames.contains(listName)==false){
				final String subListName = (listName).toString()+"_"+(ruleName).toString();
				listReallocations.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((subListName).toString()),""))))));
			}
		}
		return listReallocations;
	}
	public void getRuleBody(final ExternalStatement.Body completeBody,final String ruleName,final Map<String,Set<Integer>> excludeIndicesMap){
		final String currentLengthValue = "_length_"+(ruleName).toString()+"_brace";
		final List<ExternalStatement.Body> rule = rules.get(ruleName);
		ExternalStatement.Body currentOption = completeBody;
		final ExternalStatement.Body withinBraces = new ExternalStatement.Body();
		final Integer ruleListIndex = listIndex;
		final String currentRulePositionValue = "_position_"+ruleName;
		final String currentRuleTokenValue = "_token_"+ruleName;
		currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentRulePositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))))));
		currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentRuleTokenValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))));
		if(declaredBraceRules.containsKey(ruleName)){
			listIndex+=1;
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentLengthValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))))));
			if(declaredBracePasses.containsKey(ruleName)){
				if(declaredBracePasses.get(ruleName)==1 ){
					withinBraces.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(withinBraces),"")))));
					withinBraces.add(getListDeclarations(ruleName,false));
				}
				else if(declaredBracePasses.get(ruleName)==2 ){
					final ExternalStatement.Body completeBraceBody = new ExternalStatement.Body();
					completeBraceBody.add(getListDeclarations(ruleName,true));
					completeBraceBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(withinBraces),""))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((declaredBraceCloseLengths.get(ruleName)).toString()),""))))),new ExternalStatement.Conditional("while",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))));
					currentOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("containsKey"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(completeBraceBody),"")))));
				}
			}
			else{
				currentOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("containsKey"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(withinBraces),"")))));
				withinBraces.add(getListDeclarations(ruleName,false));
			}
			currentOption=withinBraces;
			final String currentPositionValue = "_position_"+(ruleName).toString()+"_brace";
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))));
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((declaredBraceOpenLengths.get(ruleName)).toString()),""))))));
			currentOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("while",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))));
		}
		if(handleListAdditions.containsKey(ruleName)){
			final String forRuleName = handleListAdditions.get(ruleName);
			if(listNamesInRule.containsKey(forRuleName)){
				for(final String listName:listNamesInRule.get(forRuleName)){
					final String subListName = (listName).toString()+"_additions_"+(ruleName).toString();
					if(MainFlow.self.variableDeclarationNames.add(subListName)){
						ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((subListName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
					}
					currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((subListName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),"")))))));
					currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Child"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")))))))));
					handleListAdditionAftBodies.get(ruleName).add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((subListName).toString()),""))))));
				}
			}
		}
		ExternalStatement.Body previousOption = null;
		for(Integer i = 0;i<rule.size();i++){
			if(previousOption!=null){
				previousOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(currentOption),"")))));
			}
			if(previousOption!=null){
				if(handleListAdditions.containsKey(ruleName)){
					final String forRuleName = handleListAdditions.get(ruleName);
					if(listNamesInRule.containsKey(forRuleName)){
						for(final String listName:listNamesInRule.get(forRuleName)){
							currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("removeAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))))))))));
							currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("clear"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
						}
					}
				}
				currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))))));
			}
			previousOption=currentOption;
			currentOption.add(rule.get(i));
			currentOption=new ExternalStatement.Body();
		}
		if(declaredBraceRules.containsKey(ruleName)){
			final String currentPositionValue = "_position_"+(ruleName).toString()+"_brace";
			withinBraces.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry("_brace"),""))))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((declaredBraceCloseLengths.get(ruleName)).toString()),"")))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Did not reach end of braces"),"")))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry("_brace"),""))))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((declaredBraceCloseLengths.get(ruleName)).toString()),""))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_succeedOnEnd"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentLengthValue).toString()),"")))),new ExternalStatement.Conditional("while",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.globalIgnoresHeader),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))));
		}
		if(handleListAdditions.containsKey(ruleName)){
			final String forRuleName = handleListAdditions.get(ruleName);
			if(listNamesInRule.containsKey(forRuleName)){
				final ExternalStatement.Body removeAdditionsBody = new ExternalStatement.Body();
				final ExternalStatement.Body addAdditionBody = new ExternalStatement.Body();
				previousOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(removeAdditionsBody),"")))));
				previousOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("else if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(addAdditionBody),"")))));
				if(declaredBraceRules.containsKey(ruleName)){
					previousOption.add(getListReallocations(ruleName));
				}
				for(final String listName:listNamesInRule.get(forRuleName)){
					removeAdditionsBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("removeAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("clear"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))));
					addAdditionBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("addAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("clear"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))));
					final String subListName = (listName).toString()+"_additions_"+(ruleName).toString();
					previousOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((subListName).toString()),""))))));
				}
			}
		}
		else if(declaredBraceRules.containsKey(ruleName)){
			withinBraces.add(getListReallocations(ruleName));
		}
		if(declaredBraceRules.containsKey(ruleName)){
			completeBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))),new ExternalStatement.Conditional("if",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((parentRuleNames.get(ruleName)).toString()),""),new ExternalStatement(new StringEntry("("),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry(")"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))));
		}
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse_"+(ruleName).toString()).appendToBody(completeBody);
	}
	public String define(final com.rem.gen.parser.Token input,final String previousParentRuleName){
		return define(input,previousParentRuleName,null);
	}
	public String define(final com.rem.gen.parser.Token input,final String previousParentRuleName,final String handleBracedTokenName){
		final String ruleName;
		final String parentRuleName;
		if(input.get("ruleName")!=null){
			ruleName=(input.get("ruleName")).toString();
			final String ruleClassName = MainFlow.camelize((ruleName).toString())+"Token";
			ExternalClassEntry.classMap.get("Tokens").getSubClass("Rule").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(ruleClassName),
				"class ",
				new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();;__add_method_3__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("value"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getValue");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("value"),"")))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setValue");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newValue");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("value = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newValue"),"")))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))))))));}
			public void __add_method_3__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setString"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("newValue"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("newValue"),"")))))))));}});
			parentRuleName=ruleName;
		}
		else{
			ruleName="_anonymous_"+anonymousRuleIndex;
			anonymousRuleIndex+=1;
			silentRules.add(ruleName);
			parentRuleName=previousParentRuleName;
		}
		parentRuleNames.put(ruleName,parentRuleName);
		if(ROOT_NAME==null){
			ROOT_NAME=ruleName;
		}
		if(rules.containsKey(ruleName)==false){
			completeRules.put(ruleName,new ArrayList<ExternalStatement.Body>());
			rules.put(ruleName,completeRules.get(ruleName));
			completeRules.get(ruleName).add(new ExternalStatement.Body());
		}
		final List<ExternalStatement.Body> rule = completeRules.get(ruleName);
		final Boolean isSilent = input.get("SILENT")!=null;
		if(isSilent){
			silentRules.add(ruleName);
		}
		final ExternalStatement.Body foreBody = new ExternalStatement.Body();
		final String currentPositionValue = "_position_"+ruleName;
		foreBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((parentRuleNames.get(ruleName)).toString()),""),new ExternalStatement(new StringEntry("("),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry(")"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),""))))));
		ruleForeBodies.put(ruleName,foreBody);
		if(input.get("ignores_character")!=null){
			final ExternalStatement newIgnoresHeader = new ExternalStatement();
			final ExternalStatement newIgnoresVariableSection = new ExternalStatement();
			MainFlow.self.setupIgnoresHeader(newIgnoresHeader,newIgnoresVariableSection);
			for (com.rem.gen.parser.Token character: input.getAllSafely("ignores_character")){
				MainFlow.self.addIgnoresCharacter((character).toString(),newIgnoresVariableSection);
			}
			MainFlow.self.ruleIgnoresHeaders.put(ruleName,newIgnoresHeader);
		}
		else if(input.get("ignores_none")!=null){
			final ExternalStatement newIgnoresHeader = new ExternalStatement();
			MainFlow.self.setupIgnoresHeader(newIgnoresHeader,new ExternalStatement());
			MainFlow.self.ruleIgnoresHeaders.put(ruleName,newIgnoresHeader);
		}
		else{
			MainFlow.self.ruleIgnoresHeaders.put(ruleName,MainFlow.self.globalIgnoresHeader);
		}
		if(input.get("braced_parameters")!=null){
			final String left = (input.get("braced_parameters").get("left")).toString();
			final String right = (input.get("braced_parameters").get("right")).toString();
			final String both = left+right;
			final Integer openLength = left.length();
			final Integer closeLength = right.length();
			if(declaredBraceValues.containsKey(both)==false){
				declaredBraceValues.put(both,new ArrayList<String>());
			}
			if(input.get("passConstraint")!=null){
				declaredBracePasses.put(ruleName,Integer.parseInt((input.get("passConstraint")).toString().trim()));
			}
			declaredBraceValues.get(both).add(ruleName);
			declaredBraceRules.put(ruleName,both);
			declaredBraceOpenLengths.put(ruleName,openLength);
			declaredBraceCloseLengths.put(ruleName,closeLength);
			if(declaredBraces.add(both)){
				final Integer braceId = braceIds.size();
				braceIds.put(both,braceId);
				if(declaredBraceOpenValues.containsKey(left.length())==false){
					declaredBraceOpenValues.put(left.length(),new HashMap<String,List<Integer>>());
				}
				if(declaredBraceOpenValues.get(left.length()).containsKey(left)==false){
					declaredBraceOpenValues.get(left.length()).put(left,new ArrayList<Integer>());
				}
				declaredBraceOpenValues.get(left.length()).get(left).add(braceId);
				if(declaredBraceCloseValues.containsKey(right.length())==false){
					declaredBraceCloseValues.put(right.length(),new HashMap<String,List<Integer>>());
				}
				if(declaredBraceCloseValues.get(right.length()).containsKey(right)==false){
					declaredBraceCloseValues.get(right.length()).put(right,new ArrayList<Integer>());
				}
				declaredBraceCloseValues.get(right.length()).get(right).add(braceId);
				ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceId).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
				ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").prependToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Stack"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((braceId).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Stack"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			}
		}
		for (com.rem.gen.parser.Token definition: input.getAllSafely("definition")){
			define(definition,ruleName,parentRuleName,handleBracedTokenName,rule);
		}
		return ruleName;
	}
	public void define(final com.rem.gen.parser.Token definition,final String ruleName,final String parentRuleName,final String handleBracedTokenName,final List<ExternalStatement.Body> inputRule){
		Boolean isFirst = true;
		final String ruleAsClass = MainFlow.camelize((ruleName).toString())+"Token";
		final Integer choiceIndex = inputRule.size()-1;
		ExternalStatement.Body rule = inputRule.get(choiceIndex);
		ExternalStatement.Body nextBody = null;
		final String currentPositionValue = "_position_"+ruleName;
		final String currentTokenValue = "_token_"+ruleName;
		rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
		rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))));
		if(silentRules.contains(ruleName)==false){
			rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Rule"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(ruleAsClass),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
		}
		else{
			if(handleBracedTokenName!=null){
				rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(handleBracedTokenName),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			}
			else{
				rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("$ANON"),"")))))))))));
			}
		}
		final ExternalStatement.Body foreBody = ruleForeBodies.get(ruleName);
		for (com.rem.gen.parser.Token chain: definition.getAllSafely("chain")){
			for (com.rem.gen.parser.Token element: chain.getAllSafely("element")){
				parseElement(element,ruleName,parentRuleName,rule,choiceIndex,isFirst);
				isFirst=false;
				nextBody=new ExternalStatement.Body();
				final ExternalStatement.Body realNextBody = nextBody;
				rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(foreBody),""))),new ExternalStatement.Conditional("else",null,new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(realNextBody),"")))));
				rule=nextBody;
			}
		}
		if(silentRules.contains(ruleName)==false||handleBracedTokenName!=null){
			inputRule.get(inputRule.size()-1).add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),""))))));
		}
		else{
			inputRule.get(inputRule.size()-1).add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("addAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),""))))));
		}
		if(definition.get("choice")!=null){
			inputRule.add(new ExternalStatement.Body());
			define(definition.get("choice").get("definition"),ruleName,parentRuleName,handleBracedTokenName,inputRule);
		}
	}
	public void parseElement(final com.rem.gen.parser.Token element,final String ruleName,final String parentRuleName,final ExternalStatement.Body rule,final Integer choiceIndex,final Boolean isFirst){
		for (com.rem.gen.parser.Token query: element.getAll()){
			if (query.getName().equals("multiple")){
				final String subRuleName = define(query,parentRuleName);
				final ExternalStatement.Body aftBody;
				if(declaredBraceRules.containsKey(parentRuleName)){
					handleListAdditions.put(subRuleName,parentRuleName);
					aftBody=new ExternalStatement.Body();
					handleListAdditionAftBodies.put(subRuleName,aftBody);
				}
				else{
					aftBody=null;
				}
				for (com.rem.gen.parser.Token option: query.get("option").getAll()){
					if (option.getName().equals("OPTIONAL")){
						final String stateName = "_state_"+multipleIndex;
						multipleIndex+=1;
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),""))))));
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
						if(aftBody!=null){
							rule.add(aftBody);
						}
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))))))));
					}
					if (option.getName().equals("MANY")){
						final String stateName = "_state_"+multipleIndex;
						multipleIndex+=1;
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),""))))));
						final ExternalStatement.Body whileRuleBody = new ExternalStatement.Body();
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("while",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(whileRuleBody),"")))));
						whileRuleBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
						if(aftBody!=null){
							final ExternalStatement.Body breakAftBody = new ExternalStatement.Body();
							breakAftBody.add(aftBody);
							breakAftBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))));
							whileRuleBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(breakAftBody),"")))));
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(aftBody),"")))));
						}
						else{
							whileRuleBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))))));
						}
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))))))));
					}
					if (option.getName().equals("PLUS")){
						final String stateName = "_state_"+multipleIndex;
						final String multipleValueName = "_multiple_index_"+multipleIndex;
						multipleIndex+=1;
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),""))))));
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))));
						final ExternalStatement.Body breakAftBody = new ExternalStatement.Body();
						if(aftBody!=null){
							breakAftBody.add(aftBody);
						}
						breakAftBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))));
						final ExternalStatement.Body whileRuleBody = new ExternalStatement.Body();
						whileRuleBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
						whileRuleBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(breakAftBody),""))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),""))))))));
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("while",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(whileRuleBody),"")))));
						final ExternalStatement.Body noIterationFailBody = new ExternalStatement.Body();
						if(aftBody!=null){
							noIterationFailBody.add(aftBody);
						}
						noIterationFailBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))));
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(noIterationFailBody),""))),new ExternalStatement.Conditional("else if",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&",">","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))))))));
					}
				}
			}
			if (query.getName().equals("braced_definition")){
				final String handleBracedTokenName;
				if(element.get("newName")!=null){
					final String newTokenName = MainFlow.camelize((element.get("newName")).toString())+"Token";
					final String simpleTokenName = (element.get("newName")).toString();
					declareNamedToken(newTokenName,simpleTokenName);
					handleBracedTokenName=newTokenName;
				}
				else if(element.get("listName")!=null){
					handleBracedTokenName=MainFlow.camelize((element.get("listName")).toString())+"Token";
				}
				else{
					handleBracedTokenName=null;
				}
				final String subRuleName = define(query,parentRuleName,handleBracedTokenName);
				rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
				if(element.get("listName")!=null){
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_value"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLastValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"&&",Arrays.asList(new String[]{"","!=","&&"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element.get("listName")).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),""))))))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((element.get("listName")).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")))))))))))));
					if(listNamesInRule.containsKey(parentRuleName)==false){
						listNamesInRule.put(parentRuleName,new HashSet<String>());
					}
					listNamesInRule.get(parentRuleName).add((element.get("listName")).toString());
					handleListAdditions.put(ruleName,parentRuleName);
					if(handleListAdditionAftBodies.containsKey(ruleName)==false){
						handleListAdditionAftBodies.put(ruleName,new ExternalStatement.Body());
					}
				}
			}
			if (query.getName().equals("quoteToken")){
				final String quote = (element.get("quoteToken").get("quote")).toString();
				Integer quoteLength = quote.length();
				final ExternalStatement.Body subrule = new ExternalStatement.Body();
				final StringBuilder quoteValue = new StringBuilder();
				Integer ip = 0;
				for(Integer i = 0;i<quote.length();i++){
					final String ch;
					final String quoteChar = quote.charAt(i)+"";
					if(quoteChar.equals("\\")){
						if(i+1 <quote.length()){
							final String nextChar = quote.charAt(i+1)+"";
							if(nextChar.equals("t")){
								ch="\'\\t'";
								quoteValue.append("\\t");
								quoteLength-=1;
								i+=1;
							}
							else if(nextChar.equals("n")){
								ch="\'\\n'";
								quoteValue.append("\\n");
								quoteLength-=1;
								i+=1;
							}
							else if(nextChar.equals("r")){
								ch="\'\\r'";
								quoteValue.append("\\r");
								quoteLength-=1;
								i+=1;
							}
							else if(nextChar.equals("\"")){
								ch="\'\"'";
								quoteValue.append("\"");
								quoteLength-=1;
								i+=1;
							}
							else if(nextChar.equals("\'")){
								ch="\'\\\'\'";
								quoteValue.append("\'");
								quoteLength-=1;
								i+=1;
							}
							else{
								ch="\'\\\\\'";
								quoteValue.append("\\\\");
							}
						}
						else{
							ch="\'\\\\\'";
							quoteValue.append("\\\\");
						}
					}
					else if(quoteChar.equals("'")){
						ch="\'\\\'\'";
						quoteValue.append("\\'");
					}
					else{
						ch="\'"+quote.charAt(i)+"\'";
						quoteValue.append(quote.charAt(i));
					}
					final String ipValue = (ip).toString();
					if(ch.equals("\'\\n'")){
						subrule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"!=",Arrays.asList(new String[]{"","!=","&&","!="}),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement.ArrayParameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position+"),""),new ExternalStatement(new VariableNameEntry((ipValue).toString()),"")))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((ch).toString()),"")),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement.ArrayParameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position+"),""),new ExternalStatement(new VariableNameEntry((ipValue).toString()),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("\'\\r'"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
					}
					else{
						subrule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement.ArrayParameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position+"),""),new ExternalStatement(new VariableNameEntry((ipValue).toString()),"")))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((ch).toString()),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
					}
					ip+=1;
				}
				rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,">=",Arrays.asList(new String[]{"","+","-",">="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((quoteLength).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))),new ExternalStatement.Conditional("else",null,new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(subrule),"")))));
				final Integer quoteLengthValue = quoteLength;
				if(createdSyntaxTokens.containsKey(quote)==false){
					final String quoteName = "syntax_"+plainTokenIndex;
					plainTokenIndex+=1;
					createdSyntaxTokens.put(quote,quoteName);
					createdSyntaxNameTokens.put(quote,new HashSet<String>());
					ExternalClassEntry.classMap.get("Tokens").getSubClass("Syntax").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
						new ExternalStatement("."),
					new Entry(){public void get(StringBuilder builder){}},
						new VariableNameEntry(quoteName),
						"class ",
						new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
						Arrays.asList(new Entry[]{}),
						null);
					setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(quoteName),"")))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("__SYNTAX__"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(quoteName),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("SYNTAX"),"")))))))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_SYNTAX");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(quoteName),"")))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_SYNTAX");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("__SYNTAX__ = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_SYNTAX"),"")))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((quote.replace("\\","\\\\")).toString()),""))))))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setValue"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("newValue"),"")),null)}),null,new ExternalStatement.Body()));}});
				}
				final String quoteName = createdSyntaxTokens.get(quote);
				final String newTokenName;
				if(element.get("newName")!=null){
					newTokenName=(element.get("newName")).toString();
					if(createdSyntaxNameTokens.get(quote).add(newTokenName)){
						ExternalClassEntry.classMap.get("Tokens").getSubClass("Syntax").getSubClass(""+quoteName.toString()+"").addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((newTokenName).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(quoteName),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((newTokenName).toString()),"")))))))));
					}
				}
				else{
					newTokenName="__SYNTAX__";
				}
				rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(quoteName),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(newTokenName),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((quoteLengthValue).toString()),""))))),new ExternalStatement.Conditional("while",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))))),new ExternalStatement.Conditional("else if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("unexpected plain "),""),new ExternalStatement(new VariableNameEntry((quoteValue).toString()),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))));
			}
			if (query.getName().equals("regexToken")){
				final com.rem.gen.parser.Token regex = element.get("regexToken").get("regex");
				final String currentPositionValue = "_position_regex_"+currentPositionIndex;
				final StringBuilder regexValue = new StringBuilder();
				rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
				for (com.rem.gen.parser.Token regexElement: regex.getAllSafely("regex_element")){
					regexValue.append(addRegexElementToRule(regexElement,rule,currentPositionValue));
				}
				currentPositionIndex+=1;
				if(element.get("newName")!=null){
					final String newTokenName = MainFlow.camelize((element.get("newName")).toString());
					if(createdPlainTokens.add(newTokenName)){
						ExternalClassEntry.classMap.get("Tokens").getSubClass("Plain").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
							new ExternalStatement("."),
						new Entry(){public void get(StringBuilder builder){}},
							new VariableNameEntry(newTokenName),
							"class ",
							new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
							Arrays.asList(new Entry[]{}),
							null);
						setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getValue");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("value"),"")))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setValue");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newValue");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("value = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newValue"),"")))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element.get("newName")).toString()),""))))))));}});
					}
					if(element.get("listName")!=null){
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Plain"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(newTokenName),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))))))),new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element.get("listName")).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((element.get("listName")).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))))))),new ExternalStatement.Conditional("while",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))));
						if(listNamesInRule.containsKey(parentRuleName)==false){
							listNamesInRule.put(parentRuleName,new HashSet<String>());
						}
						listNamesInRule.get(parentRuleName).add((element.get("listName")).toString());
						handleListAdditions.put(ruleName,parentRuleName);
						if(handleListAdditionAftBodies.containsKey(ruleName)==false){
							handleListAdditionAftBodies.put(ruleName,new ExternalStatement.Body());
						}
					}
					else{
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Plain"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(newTokenName),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))))))),new ExternalStatement.Conditional("while",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))));
					}
				}
				else{
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))))),new ExternalStatement.Conditional("while",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))));
				}
				rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((regexValue.toString().replace("\\","\\\\").replace("\"","\\\"").replace("\'","\\\'")).toString()),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),""))))))));
			}
			if (query.getName().equals("ruleToken")){
				if(listNames.contains((query).toString())){
					final String listName = (query).toString();
					final String newTokenClassName;
					final String newTokenName;
					if(element.get("newName")!=null){
						newTokenClassName=MainFlow.camelize((element.get("newName")).toString())+"Token";
						newTokenName=(query).toString();
					}
					else{
						newTokenClassName=MainFlow.camelize((query).toString())+"Token";
						newTokenName=(query).toString();
					}
					declareNamedToken(newTokenClassName,newTokenName);
					final ExternalStatement.Body listRuleBody;
					if(listFirstPassRules.containsKey((listName).toString())){
						listRuleBody=new ExternalStatement.Body();
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((listFirstPassRules.get(listName)).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("else if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(listRuleBody),"")))));
					}
					else{
						listRuleBody=rule;
					}
					final String isNameableCharacter = "_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )";
					listRuleBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")))))))),new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"<",Arrays.asList(new String[]{"","+","<"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_next_char"),""),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement.ArrayParameters(new ExternalStatement.Parameters(new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))),new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((isNameableCharacter).toString()),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))),new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(newTokenClassName),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("while",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))),new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))));
				}
				else{
					final String subRuleName = (query).toString();
					Boolean protectAgainstRecursion = false;
					if(isFirst){
						protectAgainstRecursion=ruleHeirachy.get(subRuleName).contains(parentRuleName);
					}
					if(element.get("newName")!=null&&element.get("listName")==null){
						final String newTokenName = (element.get("newName")).toString();
						final String newTokenClassName = MainFlow.camelize((newTokenName).toString())+"Token";
						declareNamedToken(newTokenClassName,newTokenName);
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_token"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(newTokenClassName),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
					}
					else if(protectAgainstRecursion){
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
					}
					if(protectAgainstRecursion){
						final Integer recursionIndexValue = recursionIndex;
						recursionIndex+=1;
						final String recursionVariableName = "_recursion_protection_"+(subRuleName).toString()+"_"+(recursionIndexValue).toString();
						ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((recursionVariableName).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashSet"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"&&!",Arrays.asList(new String[]{"","==","&&!"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((recursionVariableName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("contains"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((recursionVariableName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((recursionVariableName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("remove"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),"")))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
					}
					else{
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
					}
					if(element.get("newName")!=null&&element.get("listName")==null){
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_token"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_token"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),"")))))));
					}
					if(element.get("listName")!=null){
						final String listTokenName;
						if(element.get("newName")!=null){
							listTokenName=MainFlow.camelize((element.get("newName")).toString())+"Token";
							final String simpleTokenName = (element.get("newName")).toString();
							declareNamedToken(listTokenName,simpleTokenName);
						}
						else{
							listTokenName=MainFlow.camelize((element.get("listName")).toString())+"Token";
						}
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLastValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"&&",Arrays.asList(new String[]{"","!=","&&"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element.get("listName")).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),""))))))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((element.get("listName")).toString()),""),new ExternalStatement(new StringEntry("_additions"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(listTokenName),"")))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),""))))))))))))));
						if(listNamesInRule.containsKey(parentRuleName)==false){
							listNamesInRule.put(parentRuleName,new HashSet<String>());
						}
						listNamesInRule.get(parentRuleName).add((element.get("listName")).toString());
						handleListAdditions.put(ruleName,parentRuleName);
						if(handleListAdditionAftBodies.containsKey(ruleName)==false){
							handleListAdditionAftBodies.put(ruleName,new ExternalStatement.Body());
						}
					}
				}
			}
		}
	}
	public String addRegexElementToRule(final com.rem.gen.parser.Token element,final ExternalStatement.Body rule,final String positionName){
		final String groupSuccessfulPositionName = "_position_of_last_success_"+groupSuccessfulPositionIndex;
		groupSuccessfulPositionIndex+=1;
		if(element.get("option")!=null){
			final StringBuilder regexValue = new StringBuilder();
			regexValue.append("[");
			final ExternalStatement option = new ExternalStatement();
			if(element.get("option").get("negate")==null){
				option.set("||");
				final com.rem.gen.parser.Token optionToken = element.get("option");
				for (com.rem.gen.parser.Token atom: optionToken.getAll()){
					if (atom.getName().equals("range")){
						final Character ch = (atom.get("left")).toString().charAt(0);
						final Integer end = (atom.get("right")).toString().charAt(0)-ch;
						for(Integer i = 0;i<=end;i++){
							final String chValue = "'"+((char)(ch+i))+"'";
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
						}
						regexValue.append((atom.get("left")).toString());
						regexValue.append("-");
						regexValue.append((atom.get("right")).toString());
					}
					if (atom.getName().equals("regex_special")){
						for (com.rem.gen.parser.Token quark: atom.getAll()){
							if (quark.getName().equals("number")){
								final Character ch = "0".charAt(0);
								final Integer end = "9".charAt(0)-ch;
								for(Integer i = 0;i<=end;i++){
									final String chValue = "'"+(char)(ch+i)+"'";
									option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
								}
								regexValue.append("\\\\d");
							}
							if (quark.getName().equals("whitespace")){
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),""))));
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\t'"),""))));
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),""))));
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),""))));
								regexValue.append("\\\\s");
							}
							if (quark.getName().equals("quote")){
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\""),"")))));
								regexValue.append("\\\"");
							}
							if (quark.getName().equals("apostrophe")){
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("'"),"")))));
								regexValue.append("\\\'");
							}
							if (quark.getName().equals("dot")){
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'.'"),""))));
								regexValue.append("\\.");
							}
							if (quark.getName().equals("slash")){
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\\\'"),""))));
								regexValue.append("\\\\");
							}
						}
					}
					if (atom.getName().equals("standAlone")){
						final String ch;
						final String quoteChar = (atom).toString();
						if(quoteChar.equals("\\")){
							ch="\\\\";
						}
						else if(quoteChar.equals("\"")){
							ch="\\\"";
						}
						else{
							ch=(atom).toString();
						}
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((ch).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						regexValue.append((atom).toString());
					}
				}
			}
			else{
				option.set("&&");
				final com.rem.gen.parser.Token optionToken = element.get("option");
				for (com.rem.gen.parser.Token atom: optionToken.getAll()){
					if (atom.getName().equals("range")){
						final Character ch = (atom.get("left")).toString().charAt(0);
						final Integer end = (atom.get("right")).toString().charAt(0)-ch;
						for(Integer i = 0;i<=end;i++){
							final String chValue = "'"+(char)(ch+i)+"'";
							option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
						}
						regexValue.append((atom.get("left")).toString());
						regexValue.append("-");
						regexValue.append((atom.get("right")).toString());
					}
					if (atom.getName().equals("regex_special")){
						for (com.rem.gen.parser.Token quark: atom.getAll()){
							if (quark.getName().equals("number")){
								final Character ch = "0".charAt(0);
								final Integer end = "9".charAt(0)-ch;
								for(Integer i = 0;i<=end;i++){
									final String chValue = "'"+(char)(ch+i)+"'";
									option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
								}
								regexValue.append("\\\\d");
							}
							if (quark.getName().equals("whitespace")){
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),""))));
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\t'"),""))));
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),""))));
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),""))));
								regexValue.append("\\\\s");
							}
							if (quark.getName().equals("quote")){
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\""),"")))));
								regexValue.append("\\\"");
							}
							if (quark.getName().equals("apostrophe")){
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("'"),"")))));
								regexValue.append("\\\'");
							}
							if (quark.getName().equals("dot")){
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'.'"),""))));
								regexValue.append("\\.");
							}
							if (quark.getName().equals("slash")){
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\\\'"),""))));
								regexValue.append("\\\\");
							}
						}
					}
					if (atom.getName().equals("standAlone")){
						final String ch;
						final String quoteChar = (atom).toString();
						if(quoteChar.equals("\\")){
							ch="\\\\";
						}
						else if(quoteChar.equals("\"")){
							ch="\\\"";
						}
						else if(quoteChar.equals("\'")){
							ch="\\\'";
						}
						else{
							ch=(atom).toString();
						}
						option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((ch).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						regexValue.append((atom).toString());
					}
				}
			}
			if(element.get("multiple")!=null){
				final String multipleValueName = "_multiple_index_"+multipleIndex;
				multipleIndex+=1;
				if(element.get("multiple").get("OPTIONAL")!=null){
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))));
				}
				else if(element.get("multiple").get("MANY")!=null){
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("while",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))))))));
				}
				else if(element.get("multiple").get("PLUS")!=null){
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement.Conditional("while",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),""))))))),new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
				}
			}
			else{
				rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
			}
			regexValue.append("]");
			if(element.get("multiple")!=null){
				if(element.get("multiple").get("OPTIONAL")!=null){
					regexValue.append("?");
				}
				else if(element.get("multiple").get("MANY")!=null){
					regexValue.append("*");
				}
				else if(element.get("multiple").get("PLUS")!=null){
					regexValue.append("+");
				}
			}
			return (regexValue).toString();
		}
		else{
			final StringBuilder regexValue = new StringBuilder();
			final ExternalStatement.Body regexBody;
			final String multipleValueName;
			final String stateName;
			if(element.get("multiple")!=null){
				multipleValueName="_multiple_index_"+multipleIndex;
				stateName="_state_"+multipleIndex;
				multipleIndex+=1;
				final String multipleValueLimit;
				if(element.get("multiple").get("OPTIONAL")!=null){
					multipleValueLimit="1";
				}
				else{
					multipleValueLimit="Integer.MAX_VALUE";
				}
				regexBody=new ExternalStatement.Body();
				if(element.get("multiple").get("PLUS")!=null){
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")))),new ExternalStatement.Conditional("while",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(regexBody),"")))));
				}
				else{
					if(element.get("multiple").get("OPTIONAL")!=null){
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")))),new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(regexBody),"")))));
					}
					else if(element.get("multiple").get("MANY")!=null){
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")))),new ExternalStatement.Conditional("while",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(regexBody),"")))));
					}
				}
			}
			else{
				regexBody=rule;
				multipleValueName=null;
				stateName=null;
			}
			if(element.get("regex_special")!=null){
				final ExternalStatement option = new ExternalStatement();
				option.set("||");
				for (com.rem.gen.parser.Token quark: element.get("regex_special").getAll()){
					if (quark.getName().equals("number")){
						final Character ch = "0".charAt(0);
						final Integer end = "9".charAt(0)-ch;
						for(Integer i = 0;i<=end;i++){
							final String chValue = "'"+(char)(ch+i)+"'";
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
						}
						regexValue.append("\\\\d");
					}
					if (quark.getName().equals("whitespace")){
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),""))));
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\t'"),""))));
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),""))));
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),""))));
						regexValue.append("\\\\s");
					}
					if (quark.getName().equals("quote")){
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\""),"")))));
						regexValue.append("\\\"");
					}
					if (quark.getName().equals("apostrophe")){
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("'"),"")))));
						regexValue.append("\\\'");
					}
					if (quark.getName().equals("dot")){
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'.'"),""))));
						regexValue.append("\\.");
					}
					if (quark.getName().equals("slash")){
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\\\'"),""))));
						regexValue.append("\\\\");
					}
				}
				regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
			}
			if(element.get("character")!=null){
				if((element.get("character")).toString().equals(".")){
					regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
				}
				else{
					regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((element.get("character")).toString()),""),new ExternalStatement(new StringEntry("'"),"")))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
				}
				regexValue.append((element.get("character")).toString());
			}
			else if(element.get("group")!=null){
				final String op = "(";
				final String cp = ")";
				regexValue.append(op);
				final com.rem.gen.parser.Token regexToken = element.get("group").get("regex");
				regexBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
				for (com.rem.gen.parser.Token regexElement: regexToken.getAllSafely("regex_element")){
					regexValue.append(addRegexElementToRule(regexElement,regexBody,positionName));
				}
			}
			if(element.get("multiple")!=null){
				if(element.get("multiple").get("PLUS")!=null){
					if(element.get("group")!=null){
						regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))));
					}
					else{
						regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),""))))))));
					}
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,">",Arrays.asList(new String[]{"","==","&&",">"}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))));
					regexValue.append("+");
				}
				else{
					if(element.get("multiple").get("OPTIONAL")!=null){
						regexValue.append("?");
						if(element.get("group")!=null){
							regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),""))))))));
						}
					}
					else if(element.get("multiple").get("MANY")!=null){
						if(element.get("group")!=null){
							regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))));
						}
						else{
							regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),""))))))));
						}
						regexValue.append("*");
					}
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))))))));
				}
			}
			return (regexValue).toString();
		}
	}
	public void declareNamedToken(final String newTokenName,final String simpleTokenName){
		if(createdNameTokens.add(newTokenName)){
			ExternalClassEntry.classMap.get("Tokens").getSubClass("Name").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(newTokenName),
				"class ",
				new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setValue"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("newValue"),"")),null)}),null,new ExternalStatement.Body()));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((simpleTokenName).toString()),""))))))));}});
		}
	}
	public void __add_variable_0__(){
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("SUCCESS"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
	}
	public void __add_variable_1__(){
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("FAILED"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))));
	}
	public void __add_variable_2__(){
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("FIRST_PASS"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
	}
	public void __add_variable_3__(){
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("SECOND_PASS"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))));
	}
	public void __add_variable_4__(){
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),"",new ExternalStatement(new StringEntry("fileNames"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
	}
	public void __add_variable_5__(){
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),"")))))),"",new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),"")))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
	}
	public void __add_method_0__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setSUCCESS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newSUCCESS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("SUCCESS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newSUCCESS"),"")))));
	}
	public void __add_method_1__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFAILED");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newFAILED");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("FAILED = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFAILED"),"")))));
	}
	public void __add_method_2__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFIRSTPASS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newFIRSTPASS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("FIRST_PASS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFIRSTPASS"),"")))));
	}
	public void __add_method_3__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setSECONDPASS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newSECONDPASS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("SECOND_PASS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newSECONDPASS"),"")))));
	}
	public void __add_method_4__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFileNames");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newFileNames");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("fileNames = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFileNames"),"")))));
	}
	public void __add_method_5__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setContexts");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),"")))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newContexts");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("contexts = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newContexts"),"")))));
	}
	public void __add_method_6__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),null,new ExternalStatement(new StringEntry("parse"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("fileName"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(new StringEntry("firstResult"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseFile"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))))))),new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("firstResult"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getState"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("First-Pass Successful"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(new StringEntry("secondResult"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseFile"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),""))))))),new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("secondResult"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getState"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Second-Pass Successful"),""))))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Second-Pass Failed"),""))))))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("secondResult"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("First-Pass Failed"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("firstResult"),""))))))));
	}
	public void __add_method_7__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),null,new ExternalStatement(new StringEntry("parseFile"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_pass"),""),null)}),null,new ExternalStatement.Body()));
	}
	public void __add_method_8__(){
		addMethod(new ExternalMethodEntry(0,true,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("readLine"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("input"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("position"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("indexOfLine"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("indexOf"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))))),new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfLine"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfLine"),""))))))))))));
	}
	public void __add_sub_class_0__(){
		addSubClass(Context._);
	}
	public void __add_sub_class_1__(){
		addSubClass(NameList._);
	}
	public void __add_sub_class_2__(){
		addSubClass(Result._);
	}
	public void __INIT__(){
		super.__SETUP__(
			new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.packageName),"")),new ExternalStatement(new StringEntry("parser"),"")),
		new Entry(){public void get(StringBuilder builder){}},
			new VariableNameEntry("Parser"),
			"class ",
			null,
			Arrays.asList(new Entry[]{}),
			null);
		setIsStatic(false);
		__add_variable_0__();
		__add_variable_1__();
		__add_variable_2__();
		__add_variable_3__();
		__add_variable_4__();
		__add_variable_5__();
		__add_method_0__();
		__add_method_1__();
		__add_method_2__();
		__add_method_3__();
		__add_method_4__();
		__add_method_5__();
		__add_method_6__();
		__add_method_7__();
		__add_method_8__();
		__add_sub_class_0__();
		__add_sub_class_1__();
		__add_sub_class_2__();
	}
	public static class Context extends ExternalClassEntry{
		public static final Context _ = new Context();
		public void __add_variable_0__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))));
		}
		public void __add_variable_1__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_position"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
		}
		public void __add_variable_2__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_inputLength"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_variable_3__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_state"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))));
		}
		public void __add_variable_4__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_furthestPosition"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_variable_5__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_lineNumber"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))));
		}
		public void __add_variable_6__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_input"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_7__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_fileName"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_8__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.charArray),""))),"",new ExternalStatement(new StringEntry("_inputArray"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_9__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(new StringEntry("_result"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_10__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Acceptor"),""))),"",new ExternalStatement(new StringEntry("_result_acceptor"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Acceptor"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_11__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Boolean"),"")),"",new ExternalStatement(new StringEntry("_succeedOnEnd"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),""))));
		}
		public void __add_variable_12__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_list_name_result"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_13__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(new StringEntry("_lineNumberRanges"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_14__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("_root"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("$ROOT"),"")))))))));
		}
		public void __add_variable_15__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("_token"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("_root"),""))));
		}
		public void __add_method_0__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_pass");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_pass"),"")))));
		}
		public void __add_method_1__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_pass");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_pass");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_pass = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_pass"),"")))));
		}
		public void __add_method_2__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_position");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_position"),"")))));
		}
		public void __add_method_3__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_position");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_position");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_position = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_position"),"")))));
		}
		public void __add_method_4__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_inputLength");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_inputLength"),"")))));
		}
		public void __add_method_5__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_inputLength");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_inputLength");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_inputLength = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_inputLength"),"")))));
		}
		public void __add_method_6__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_state");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_state"),"")))));
		}
		public void __add_method_7__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_state");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_state");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_state = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_state"),"")))));
		}
		public void __add_method_8__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_furthestPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_furthestPosition"),"")))));
		}
		public void __add_method_9__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_furthestPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_furthestPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_furthestPosition = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_furthestPosition"),"")))));
		}
		public void __add_method_10__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_lineNumber");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_lineNumber"),"")))));
		}
		public void __add_method_11__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_lineNumber");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_lineNumber");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_lineNumber = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_lineNumber"),"")))));
		}
		public void __add_method_12__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_input");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_input"),"")))));
		}
		public void __add_method_13__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_input");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_input");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_input = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_input"),"")))));
		}
		public void __add_method_14__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_fileName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_fileName"),"")))));
		}
		public void __add_method_15__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_fileName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_fileName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_fileName = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_fileName"),"")))));
		}
		public void __add_method_16__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.charArray),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_inputArray");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_inputArray"),"")))));
		}
		public void __add_method_17__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_inputArray");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.charArray),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_inputArray");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_inputArray = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_inputArray"),"")))));
		}
		public void __add_method_18__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_result");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_result"),"")))));
		}
		public void __add_method_19__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_result");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_result");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_result = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_result"),"")))));
		}
		public void __add_method_20__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Acceptor"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_resultAcceptor");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_result_acceptor"),"")))));
		}
		public void __add_method_21__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_resultAcceptor");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Acceptor"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_resultAcceptor");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_result_acceptor = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_resultAcceptor"),"")))));
		}
		public void __add_method_22__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Boolean"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_succeedOnEnd");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_succeedOnEnd"),"")))));
		}
		public void __add_method_23__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_succeedOnEnd");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Boolean"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_succeedOnEnd");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_succeedOnEnd = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_succeedOnEnd"),"")))));
		}
		public void __add_method_24__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_listNameResult");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_list_name_result"),"")))));
		}
		public void __add_method_25__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_listNameResult");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_listNameResult");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_list_name_result = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_listNameResult"),"")))));
		}
		public void __add_method_26__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_lineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")))));
		}
		public void __add_method_27__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_lineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_lineNumberRanges");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_lineNumberRanges = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_lineNumberRanges"),"")))));
		}
		public void __add_method_28__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_root");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_root"),"")))));
		}
		public void __add_method_29__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_root");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_root");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_root = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_root"),"")))));
		}
		public void __add_method_30__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_token");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_token"),"")))));
		}
		public void __add_method_31__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_token");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_token");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_token = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_token"),"")))));
		}
		public void __add_method_32__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),null,new ExternalStatement(new StringEntry("parse"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_pass_index"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new ExternalStatement(new StringEntry("startParseTime"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("currentTimeMillis"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass_index"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")),"",new ExternalStatement(new StringEntry("_inputBuffer"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Conditional("try",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("BufferedReader"),"")),"",new ExternalStatement(new StringEntry("_inputReader"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("BufferedReader"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("FileReader"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_readInput"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputReader"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("read"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),"",new ExternalStatement(new StringEntry("escaping"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),"",new ExternalStatement(new StringEntry("quoting"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))),new ExternalStatement.Conditional("while",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(readInputBody),""))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputReader"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("close"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))),new ExternalStatement.Conditional("catch",new ExternalStatement(" ",new ExternalStatement(new StringEntry("IOException"),""),new ExternalStatement(new StringEntry("e0"),"")),new ExternalStatement.Body()),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputBuffer"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toCharArray"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement(new StringEntry("length"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_fileId"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileNames"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileNames"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("this._fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")))),new ExternalStatement.Conditional("while",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","<","&&","==","||","==","||","==","||","=="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("(_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\t'"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r')"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))))));
		}
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Context"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(false);
			__add_variable_0__();
			__add_variable_1__();
			__add_variable_2__();
			__add_variable_3__();
			__add_variable_4__();
			__add_variable_5__();
			__add_variable_6__();
			__add_variable_7__();
			__add_variable_8__();
			__add_variable_9__();
			__add_variable_10__();
			__add_variable_11__();
			__add_variable_12__();
			__add_variable_13__();
			__add_variable_14__();
			__add_variable_15__();
			__add_method_0__();
			__add_method_1__();
			__add_method_2__();
			__add_method_3__();
			__add_method_4__();
			__add_method_5__();
			__add_method_6__();
			__add_method_7__();
			__add_method_8__();
			__add_method_9__();
			__add_method_10__();
			__add_method_11__();
			__add_method_12__();
			__add_method_13__();
			__add_method_14__();
			__add_method_15__();
			__add_method_16__();
			__add_method_17__();
			__add_method_18__();
			__add_method_19__();
			__add_method_20__();
			__add_method_21__();
			__add_method_22__();
			__add_method_23__();
			__add_method_24__();
			__add_method_25__();
			__add_method_26__();
			__add_method_27__();
			__add_method_28__();
			__add_method_29__();
			__add_method_30__();
			__add_method_31__();
			__add_method_32__();
		}
	}
	public static class NameList extends ExternalClassEntry{
		public static final NameList _ = new NameList();
		public void __add_variable_0__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Node"),""),new ExternalStatement(new StringEntry("Root"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Node"),""),new ExternalStatement(new StringEntry("Root"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_method_0__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Node"),""),new ExternalStatement(new StringEntry("Root"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getRoot");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("root"),"")))));
		}
		public void __add_method_1__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setRoot");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Node"),""),new ExternalStatement(new StringEntry("Root"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newRoot");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("root = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newRoot"),"")))));
		}
		public void __add_method_2__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),""))))))))));
		}
		public void __add_method_3__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("get"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.charArray),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),""))))))))));
		}
		public void __add_method_4__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),null,new ExternalStatement(".",new ExternalStatement(new StringEntry("list"),"")),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("list"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
		}
		public void __add_method_5__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("clear"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("clear"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
		}
		public void __add_method_6__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("removeAll"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(new StringEntry("fromList"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("removeAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fromList"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getRoot"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))))));
		}
		public void __add_method_7__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("addAll"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("fromList"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("addAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fromList"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getRoot"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))))));
		}
		public void __add_sub_class_0__(){
			addSubClass(Node._);
		}
		public void __add_sub_class_1__(){
			addSubClass(Root._);
		}
		public void __add_sub_class_2__(){
			addSubClass(Child._);
		}
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("NameList"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			__add_variable_0__();
			__add_method_0__();
			__add_method_1__();
			__add_method_2__();
			__add_method_3__();
			__add_method_4__();
			__add_method_5__();
			__add_method_6__();
			__add_method_7__();
			__add_sub_class_0__();
			__add_sub_class_1__();
			__add_sub_class_2__();
		}
		public static class Node extends ExternalClassEntry{
			protected String nodeArray = "Node[]";
			protected String nodeArrayWith128 = "new Node[128]";
			protected String nodeArrayAtPosition = "children[newEntry.charAt(position)]";
			protected String nodeArrayAtInputPosition = "children[input[position]]";
			public static String nodeArrayAtFirstEntry = "children[entry.charAt(0)]";
			protected String nodeArrayAtRemoveIndex = "children[toRemove.charAt(index)]";
			protected String inputAccessPosition = "input[position]";
			public static final Node _ = new Node();
			public String getNodeArray(){
				return nodeArray;
			}
			public void setNodeArray(String newNodeArray){
				nodeArray = newNodeArray;
			}
			public String getNodeArrayWith128(){
				return nodeArrayWith128;
			}
			public void setNodeArrayWith128(String newNodeArrayWith128){
				nodeArrayWith128 = newNodeArrayWith128;
			}
			public String getNodeArrayAtPosition(){
				return nodeArrayAtPosition;
			}
			public void setNodeArrayAtPosition(String newNodeArrayAtPosition){
				nodeArrayAtPosition = newNodeArrayAtPosition;
			}
			public String getNodeArrayAtInputPosition(){
				return nodeArrayAtInputPosition;
			}
			public void setNodeArrayAtInputPosition(String newNodeArrayAtInputPosition){
				nodeArrayAtInputPosition = newNodeArrayAtInputPosition;
			}
			public void setNodeArrayAtFirstEntry(String newNodeArrayAtFirstEntry){
				nodeArrayAtFirstEntry = newNodeArrayAtFirstEntry;
			}
			public String getNodeArrayAtRemoveIndex(){
				return nodeArrayAtRemoveIndex;
			}
			public void setNodeArrayAtRemoveIndex(String newNodeArrayAtRemoveIndex){
				nodeArrayAtRemoveIndex = newNodeArrayAtRemoveIndex;
			}
			public String getInputAccessPosition(){
				return inputAccessPosition;
			}
			public void setInputAccessPosition(String newInputAccessPosition){
				inputAccessPosition = newInputAccessPosition;
			}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(nodeArray),""))),"",new ExternalStatement(new StringEntry("children"),""),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayWith128).toString()),""))));
			}
			public void __add_variable_1__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("numberOfEntries"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
			}
			public void __add_variable_2__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(nodeArray),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getChildren");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("children"),"")))));
			}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setChildren");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(nodeArray),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newChildren");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("children = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newChildren"),"")))));
			}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getNumberOfEntries");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("numberOfEntries"),"")))));
			}
			public void __add_method_3__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setNumberOfEntries");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newNumberOfEntries");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("numberOfEntries = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newNumberOfEntries"),"")))));
			}
			public void __add_method_4__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getValue");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("value"),"")))));
			}
			public void __add_method_5__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setValue");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newValue");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("value = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newValue"),"")))));
			}
			public void __add_method_6__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("newEntry"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtPosition).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtPosition).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Node"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++numberOfEntries"),""))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtPosition).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"||",Arrays.asList(new String[]{"","==","||"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("!value"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("equals"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),""))))))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))))))))));
			}
			public void __add_method_7__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("get"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("length"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.charArray),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),""))))),new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtInputPosition).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("result"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtInputPosition).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")))))))),new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),""))))));
			}
			public void __add_method_8__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),null,new ExternalStatement(new StringEntry("remove"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("toRemove"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("index"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("index"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("toRemove"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("toRemove"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("equals"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")))))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("2"),""))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtRemoveIndex).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtRemoveIndex).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("remove"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("toRemove"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("index"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))))))),new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("2"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(">",new ExternalStatement(".",new ExternalStatement(new StringEntry("numberOfEntries"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtRemoveIndex).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("--numberOfEntries"),""))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("numberOfEntries"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("2"),""))))))))));
			}
			public void __add_sub_class_0__(){
				addSubClass(Root._);
			}
			public void __INIT__(){
				super.__SETUP__(
					new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Node"),
					"class ",
					null,
					Arrays.asList(new Entry[]{}),
					null);
				setIsStatic(true);
				__add_variable_0__();
				__add_variable_1__();
				__add_variable_2__();
				__add_method_0__();
				__add_method_1__();
				__add_method_2__();
				__add_method_3__();
				__add_method_4__();
				__add_method_5__();
				__add_method_6__();
				__add_method_7__();
				__add_method_8__();
				__add_sub_class_0__();
			}
			public static class Root extends ExternalClassEntry{
				public static final Root _ = new Root();
				public void __add_variable_0__(){
					addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),"",new ExternalStatement(new StringEntry("allEntries"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashSet"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
				}
				public void __add_method_0__(){
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("getAllEntries");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("allEntries"),"")))));
				}
				public void __add_method_1__(){
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setAllEntries");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newAllEntries");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("allEntries = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newAllEntries"),"")))));
				}
				public void __add_method_2__(){
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("allEntries"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")))))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))))))));
				}
				public void __add_method_3__(){
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),null,new ExternalStatement(".",new ExternalStatement(new StringEntry("list"),"")),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("allEntries"),""))))));
				}
				public void __add_method_4__(){
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("clear"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("for",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("entry"),""),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("allEntries"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nodeArrayAtFirstEntry).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))))))));
				}
				public void __add_method_5__(){
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("removeAll"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Node"),""),new ExternalStatement(new StringEntry("Root"),""))),"",new ExternalStatement(new StringEntry("fromNode"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("for",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("entry"),"")),null),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fromNode"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("allEntries"),"")))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("remove"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("entry"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("allEntries"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("removeAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fromNode"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("allEntries"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("numberOfEntries"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("allEntries"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))));
				}
				public void __add_method_6__(){
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("addAll"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Node"),""),new ExternalStatement(new StringEntry("Root"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("fromNode"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("for",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("entry"),"")),null),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fromNode"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("allEntries"),"")))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("add"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("entry"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))))))))));
				}
				public void __INIT__(){
					super.__SETUP__(
						new ExternalStatement("."),
					new Entry(){public void get(StringBuilder builder){}},
						new VariableNameEntry("Root"),
						"class ",
						new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Node"),""))),
						Arrays.asList(new Entry[]{}),
						null);
					setIsStatic(true);
					__add_variable_0__();
					__add_method_0__();
					__add_method_1__();
					__add_method_2__();
					__add_method_3__();
					__add_method_4__();
					__add_method_5__();
					__add_method_6__();
				}
			}
		}
		public static class Root extends ExternalClassEntry{
			public static final Root _ = new Root();
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("query"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized",new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("query"),""))))))))))));
			}
			public void __INIT__(){
				super.__SETUP__(
					new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Root"),
					"class ",
					new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),
					Arrays.asList(new Entry[]{}),
					null);
				setIsStatic(true);
				__add_method_0__();
			}
		}
		public static class Child extends ExternalClassEntry{
			public static final Child _ = new Child();
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),"",new ExternalStatement(new StringEntry("parentList"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getParentList");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parentList"),"")))));
			}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParentList");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newParentList");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parentList = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParentList"),"")))));
			}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("get"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.charArray),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("parentResult"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("parentList"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")))))))),new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("parentResult"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))),new ExternalStatement.Conditional("else if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("parentResult"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("parentResult"),""))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("parentResult"),""))))))));
			}
			public void __add_method_3__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),null,new ExternalStatement(".",new ExternalStatement(new StringEntry("list"),"")),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))),"",new ExternalStatement(new StringEntry("set"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashSet"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("set"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("addAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("list"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("set"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("addAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("parentList"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("list"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("set"),""))))));
			}
			public void __INIT__(){
				super.__SETUP__(
					new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Child"),
					"class ",
					new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),
					Arrays.asList(new Entry[]{}),
					null);
				setIsStatic(true);
				__add_variable_0__();
				__add_method_0__();
				__add_method_1__();
				__add_method_2__();
				__add_method_3__();
			}
		}
	}
	public static class Result extends ExternalClassEntry{
		public static final Result _ = new Result();
		public void __add_variable_0__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("state"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_variable_1__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_variable_2__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(new StringEntry("lineNumberRanges"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_3__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_4__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_5__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_method_0__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getState");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("state"),"")))));
		}
		public void __add_method_1__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setState");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newState");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("state = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newState"),"")))));
		}
		public void __add_method_2__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("position"),"")))));
		}
		public void __add_method_3__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("position = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newPosition"),"")))));
		}
		public void __add_method_4__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("getLineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("lineNumberRanges"),"")))));
		}
		public void __add_method_5__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setLineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newLineNumberRanges");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("lineNumberRanges = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newLineNumberRanges"),"")))));
		}
		public void __add_method_6__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getInput");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("input"),"")))));
		}
		public void __add_method_7__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setInput");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newInput");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("input = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newInput"),"")))));
		}
		public void __add_method_8__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getFileName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("fileName"),"")))));
		}
		public void __add_method_9__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setFileName"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("newFileName"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("newFileName"),"")))))));
		}
		public void __add_method_10__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getParseTime");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parseTime"),"")))));
		}
		public void __add_method_11__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParseTime");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newParseTime");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parseTime = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParseTime"),"")))));
		}
		public void __add_method_12__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),null,new ExternalStatement(new StringEntry("getLineNumber"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("upperBound"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("lineNumber"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement.Conditional("while",new ExternalStatement(null,null,"<",Arrays.asList(new String[]{"","<","&&","<"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),""))))));
		}
		public void __add_method_13__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),null,new ExternalStatement(new StringEntry("getLineNumber"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement.Conditional("while",new ExternalStatement(null,null,"<",Arrays.asList(new String[]{"","<","&&","<"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),""))))));
		}
		public void __add_method_14__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser.FAILED"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("getLineNumber"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("rangeIndex"),""),new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))),new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("lowerBound"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement.Conditional("if",new ExternalStatement(">",new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("lowerBound"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("errorAt"),""),null)),new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("lowerBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("$>"),"")))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),""))))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("lowerBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("<$"),"")))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))))))))))),new ExternalStatement.Conditional("if",new ExternalStatement("<=",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tError: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tLine Number: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tError: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tLine Number: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tFile Name: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1 "),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File Name: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ms"),"")))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(null,null,"+",Arrays.asList(new String[]{"","+","+","+","/","+","+","%","+"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File Name: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("."),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("s"),"")))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tError: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tLine Number: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tFile Name: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ms"),"")))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(null,null,"+",Arrays.asList(new String[]{"","+","+","+","+","+","+","+","/","+","+","%","+"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tError: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tLine Number: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tFile Name: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("."),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("s"),"")))))))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),""))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File Name: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File Name: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ms"),"")))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(null,null,"+",Arrays.asList(new String[]{"","+","+","+","/","+","+","%","+"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File Name: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("."),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("s"),"")))))))))))))));
		}
		public void __add_sub_class_0__(){
			addSubClass(Pass._);
		}
		public void __add_sub_class_1__(){
			addSubClass(Fail._);
		}
		public void __add_sub_class_2__(){
			addSubClass(Acceptor._);
		}
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.packageName),"")),new ExternalStatement(new StringEntry("parser"),"")),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Result"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			__add_variable_0__();
			__add_variable_1__();
			__add_variable_2__();
			__add_variable_3__();
			__add_variable_4__();
			__add_variable_5__();
			__add_method_0__();
			__add_method_1__();
			__add_method_2__();
			__add_method_3__();
			__add_method_4__();
			__add_method_5__();
			__add_method_6__();
			__add_method_7__();
			__add_method_8__();
			__add_method_9__();
			__add_method_10__();
			__add_method_11__();
			__add_method_12__();
			__add_method_13__();
			__add_method_14__();
			__add_sub_class_0__();
			__add_sub_class_1__();
			__add_sub_class_2__();
		}
		public static class Pass extends ExternalClassEntry{
			public static final Pass _ = new Pass();
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("parsedRoot"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_variable_1__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getParsedRoot");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parsedRoot"),"")))));
			}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParsedRoot");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newParsedRoot");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parsedRoot = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParsedRoot"),"")))));
			}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getRoot");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("root"),"")))));
			}
			public void __add_method_3__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setRoot");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newRoot");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("root = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newRoot"),"")))));
			}
			public void __add_method_4__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setup"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("setup"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("parsedRoot"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))))))));
			}
			public void __add_method_5__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setup"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new ExternalStatement(new StringEntry("current"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("currentParsed"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("currentPosition"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),"")))))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("currentParsed"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getChildren"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(new StringEntry("positions"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("currentParsed"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getPositions"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("currentParsed"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getChildren"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("size"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("for",new ExternalStatement(";",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(new StringEntry("0"),"")),new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("size"),""))),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(new StringEntry("++"),""))),new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getChildren"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new ExternalStatement(new StringEntry("newToken"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("currentPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("current"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newToken"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("setup"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newToken"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("current"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Leaf"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("currentPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")))))))))))))))));
			}
			public void __add_method_6__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Passed:\\n\\t"),"")))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))))));
			}
			public void __INIT__(){
				super.__SETUP__(
					new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Pass"),
					"class ",
					new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),
					Arrays.asList(new Entry[]{}),
					null);
				setIsStatic(true);
				__add_variable_0__();
				__add_variable_1__();
				__add_method_0__();
				__add_method_1__();
				__add_method_2__();
				__add_method_3__();
				__add_method_4__();
				__add_method_5__();
				__add_method_6__();
			}
		}
		public static class Fail extends ExternalClassEntry{
			public static final Fail _ = new Fail();
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ruleName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getRuleName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("ruleName"),"")))));
			}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setRuleName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newRuleName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("ruleName = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newRuleName"),"")))));
			}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Failed:\\n\\tRule:"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("ruleName"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))));
			}
			public void __INIT__(){
				super.__SETUP__(
					new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Fail"),
					"class ",
					new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),
					Arrays.asList(new Entry[]{}),
					null);
				setIsStatic(true);
				__add_variable_0__();
				__add_method_0__();
				__add_method_1__();
				__add_method_2__();
			}
		}
		public static class Acceptor extends ExternalClassEntry{
			public static final Acceptor _ = new Acceptor();
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),"")))))),"",new ExternalStatement(new StringEntry("results"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),"")))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
			}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),"")))))),"",new Entry(){public void get(StringBuilder builder){builder.append("getResults");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("results"),"")))));
			}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setResults");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),"")))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newResults");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("results = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newResults"),"")))));
			}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("results"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))))))));
			}
			public void __add_method_3__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")),"",new ExternalStatement(new StringEntry("builder"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Conditional("for",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("results"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("resultString"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("if",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("resultString"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("resultString"),""))))))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))));
			}
			public void __INIT__(){
				super.__SETUP__(
					new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Acceptor"),
					"class ",
					new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),
					Arrays.asList(new Entry[]{}),
					null);
				setIsStatic(true);
				__add_variable_0__();
				__add_method_0__();
				__add_method_1__();
				__add_method_2__();
				__add_method_3__();
			}
		}
	}
}