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
	protected Set<String> globalListNames = new HashSet<String>();
	protected Map<String,String> listFirstPassRules = new HashMap<String,String>();
	protected Set<String> listPreparers = new HashSet<String>();
	protected Map<String,Set<String>> listNamesInRule = new HashMap<String,Set<String>>();
	protected Map<String,String> handleListAdditions = new HashMap<String,String>();
	protected Map<String,ExternalStatement.Body> handleListAdditionAftBodies = new HashMap<String,ExternalStatement.Body>();
	protected Set<String> handleRecursionProtection = new HashSet<String>();
	protected Integer tokenId = 1;
	protected String ROOT_NAME = null;
	protected Map<String,Set<String>> declaredPositions = new HashMap<String,Set<String>>();
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
	protected ExternalStatement.Parameters newContextParameters = new ExternalStatement.Parameters();
	protected Map<String,Set<String>> subRuleTokenDeclarations = new HashMap<String,Set<String>>();
	protected Integer currentPositionIndex = 0;
	protected Integer anonymousRuleIndex = 0;
	protected String plainTokenClassName = "_0";
	protected Integer plainTokenIndex = 0;
	protected Set<String> createdPlainTokens = new HashSet<String>();
	protected Map<String,String> createdSyntaxTokens = new HashMap<String,String>();
	protected Set<String> importRuleNameTokens = new HashSet<String>();
	protected Map<String,Set<String>> createdSyntaxNameTokens = new HashMap<String,Set<String>>();
	protected Set<String> createdNameTokens = new HashSet<String>();
	protected ExternalStatement.Body preparsedListRun = new ExternalStatement.Body();
	protected ExternalStatement.Body endBuildersBody = new ExternalStatement.Body();
	protected Integer listIndex = 0;
	protected Integer multipleIndex = 0;
	protected Integer recursionIndex = 0;
	protected Integer groupSuccessfulPositionIndex = 0;
	public static final Parser _ = new Parser();
	public void setReadInputBody(ExternalStatement.Body newReadInputBody) {
		readInputBody = newReadInputBody;
	}
	public ExternalStatement.Body getBraceVariableDeclaration() {
		return braceVariableDeclaration;
	}
	public void setBraceVariableDeclaration(ExternalStatement.Body newBraceVariableDeclaration) {
		braceVariableDeclaration = newBraceVariableDeclaration;
	}
	public String getNotQuoting() {
		return notQuoting;
	}
	public void setNotQuoting(String newNotQuoting) {
		notQuoting = newNotQuoting;
	}
	public String getNotEscaping() {
		return notEscaping;
	}
	public void setNotEscaping(String newNotEscaping) {
		notEscaping = newNotEscaping;
	}
	public Map<String,List<ExternalStatement.Body>> getRules() {
		return rules;
	}
	public void setRules(Map<String,List<ExternalStatement.Body>> newRules) {
		rules = newRules;
	}
	public Map<String,List<ExternalStatement.Body>> getCompleteRules() {
		return completeRules;
	}
	public void setCompleteRules(Map<String,List<ExternalStatement.Body>> newCompleteRules) {
		completeRules = newCompleteRules;
	}
	public Map<ExternalStatement.Body,String> getRuleHolders() {
		return ruleHolders;
	}
	public void setRuleHolders(Map<ExternalStatement.Body,String> newRuleHolders) {
		ruleHolders = newRuleHolders;
	}
	public Map<String,Map<Integer,List<ExternalStatement.Body>>> getUnsatisfiedRules() {
		return unsatisfiedRules;
	}
	public void setUnsatisfiedRules(Map<String,Map<Integer,List<ExternalStatement.Body>>> newUnsatisfiedRules) {
		unsatisfiedRules = newUnsatisfiedRules;
	}
	public Map<String,ExternalStatement.Body> getRuleForeBodies() {
		return ruleForeBodies;
	}
	public void setRuleForeBodies(Map<String,ExternalStatement.Body> newRuleForeBodies) {
		ruleForeBodies = newRuleForeBodies;
	}
	public Map<String,String> getParentRuleNames() {
		return parentRuleNames;
	}
	public void setParentRuleNames(Map<String,String> newParentRuleNames) {
		parentRuleNames = newParentRuleNames;
	}
	public Set<String> getSilentRules() {
		return silentRules;
	}
	public void setSilentRules(Set<String> newSilentRules) {
		silentRules = newSilentRules;
	}
	public Set<String> getGlobalListNames() {
		return globalListNames;
	}
	public void setGlobalListNames(Set<String> newGlobalListNames) {
		globalListNames = newGlobalListNames;
	}
	public Map<String,String> getListFirstPassRules() {
		return listFirstPassRules;
	}
	public void setListFirstPassRules(Map<String,String> newListFirstPassRules) {
		listFirstPassRules = newListFirstPassRules;
	}
	public Set<String> getListPreparers() {
		return listPreparers;
	}
	public void setListPreparers(Set<String> newListPreparers) {
		listPreparers = newListPreparers;
	}
	public Map<String,Set<String>> getListNamesInRule() {
		return listNamesInRule;
	}
	public void setListNamesInRule(Map<String,Set<String>> newListNamesInRule) {
		listNamesInRule = newListNamesInRule;
	}
	public Map<String,String> getHandleListAdditions() {
		return handleListAdditions;
	}
	public void setHandleListAdditions(Map<String,String> newHandleListAdditions) {
		handleListAdditions = newHandleListAdditions;
	}
	public Map<String,ExternalStatement.Body> getHandleListAdditionAftBodies() {
		return handleListAdditionAftBodies;
	}
	public void setHandleListAdditionAftBodies(Map<String,ExternalStatement.Body> newHandleListAdditionAftBodies) {
		handleListAdditionAftBodies = newHandleListAdditionAftBodies;
	}
	public Set<String> getHandleRecursionProtection() {
		return handleRecursionProtection;
	}
	public void setHandleRecursionProtection(Set<String> newHandleRecursionProtection) {
		handleRecursionProtection = newHandleRecursionProtection;
	}
	public Integer getTokenId() {
		return tokenId;
	}
	public void setTokenId(Integer newTokenId) {
		tokenId = newTokenId;
	}
	public String getROOTNAME() {
		return ROOT_NAME;
	}
	public void setROOTNAME(String newROOTNAME) {
		ROOT_NAME = newROOTNAME;
	}
	public Map<String,Set<String>> getDeclaredPositions() {
		return declaredPositions;
	}
	public void setDeclaredPositions(Map<String,Set<String>> newDeclaredPositions) {
		declaredPositions = newDeclaredPositions;
	}
	public ExternalStatement getBracedCondition() {
		return bracedCondition;
	}
	public void setBracedCondition(ExternalStatement newBracedCondition) {
		bracedCondition = newBracedCondition;
	}
	public Set<String> getDeclaredBraces() {
		return declaredBraces;
	}
	public void setDeclaredBraces(Set<String> newDeclaredBraces) {
		declaredBraces = newDeclaredBraces;
	}
	public Map<String,List<String>> getDeclaredBraceValues() {
		return declaredBraceValues;
	}
	public void setDeclaredBraceValues(Map<String,List<String>> newDeclaredBraceValues) {
		declaredBraceValues = newDeclaredBraceValues;
	}
	public Map<String,String> getDeclaredBraceRules() {
		return declaredBraceRules;
	}
	public void setDeclaredBraceRules(Map<String,String> newDeclaredBraceRules) {
		declaredBraceRules = newDeclaredBraceRules;
	}
	public Map<String,Integer> getBraceIds() {
		return braceIds;
	}
	public void setBraceIds(Map<String,Integer> newBraceIds) {
		braceIds = newBraceIds;
	}
	public Map<String,Integer> getDeclaredBraceOpenLengths() {
		return declaredBraceOpenLengths;
	}
	public void setDeclaredBraceOpenLengths(Map<String,Integer> newDeclaredBraceOpenLengths) {
		declaredBraceOpenLengths = newDeclaredBraceOpenLengths;
	}
	public Map<String,Integer> getDeclaredBraceCloseLengths() {
		return declaredBraceCloseLengths;
	}
	public void setDeclaredBraceCloseLengths(Map<String,Integer> newDeclaredBraceCloseLengths) {
		declaredBraceCloseLengths = newDeclaredBraceCloseLengths;
	}
	public Map<Integer,Map<String,List<Integer>>> getDeclaredBraceOpenValues() {
		return declaredBraceOpenValues;
	}
	public void setDeclaredBraceOpenValues(Map<Integer,Map<String,List<Integer>>> newDeclaredBraceOpenValues) {
		declaredBraceOpenValues = newDeclaredBraceOpenValues;
	}
	public Map<Integer,Map<String,List<Integer>>> getDeclaredBraceCloseValues() {
		return declaredBraceCloseValues;
	}
	public void setDeclaredBraceCloseValues(Map<Integer,Map<String,List<Integer>>> newDeclaredBraceCloseValues) {
		declaredBraceCloseValues = newDeclaredBraceCloseValues;
	}
	public Map<String,Integer> getDeclaredBracePasses() {
		return declaredBracePasses;
	}
	public void setDeclaredBracePasses(Map<String,Integer> newDeclaredBracePasses) {
		declaredBracePasses = newDeclaredBracePasses;
	}
	public ExternalStatement.Parameters getNewContextParameters() {
		return newContextParameters;
	}
	public void setNewContextParameters(ExternalStatement.Parameters newNewContextParameters) {
		newContextParameters = newNewContextParameters;
	}
	public Map<String,Set<String>> getSubRuleTokenDeclarations() {
		return subRuleTokenDeclarations;
	}
	public void setSubRuleTokenDeclarations(Map<String,Set<String>> newSubRuleTokenDeclarations) {
		subRuleTokenDeclarations = newSubRuleTokenDeclarations;
	}
	public Integer getCurrentPositionIndex() {
		return currentPositionIndex;
	}
	public void setCurrentPositionIndex(Integer newCurrentPositionIndex) {
		currentPositionIndex = newCurrentPositionIndex;
	}
	public Integer getAnonymousRuleIndex() {
		return anonymousRuleIndex;
	}
	public void setAnonymousRuleIndex(Integer newAnonymousRuleIndex) {
		anonymousRuleIndex = newAnonymousRuleIndex;
	}
	public String getPlainTokenClassName() {
		return plainTokenClassName;
	}
	public void setPlainTokenClassName(String newPlainTokenClassName) {
		plainTokenClassName = newPlainTokenClassName;
	}
	public Integer getPlainTokenIndex() {
		return plainTokenIndex;
	}
	public void setPlainTokenIndex(Integer newPlainTokenIndex) {
		plainTokenIndex = newPlainTokenIndex;
	}
	public Set<String> getCreatedPlainTokens() {
		return createdPlainTokens;
	}
	public void setCreatedPlainTokens(Set<String> newCreatedPlainTokens) {
		createdPlainTokens = newCreatedPlainTokens;
	}
	public Map<String,String> getCreatedSyntaxTokens() {
		return createdSyntaxTokens;
	}
	public void setCreatedSyntaxTokens(Map<String,String> newCreatedSyntaxTokens) {
		createdSyntaxTokens = newCreatedSyntaxTokens;
	}
	public Set<String> getImportRuleNameTokens() {
		return importRuleNameTokens;
	}
	public void setImportRuleNameTokens(Set<String> newImportRuleNameTokens) {
		importRuleNameTokens = newImportRuleNameTokens;
	}
	public Map<String,Set<String>> getCreatedSyntaxNameTokens() {
		return createdSyntaxNameTokens;
	}
	public void setCreatedSyntaxNameTokens(Map<String,Set<String>> newCreatedSyntaxNameTokens) {
		createdSyntaxNameTokens = newCreatedSyntaxNameTokens;
	}
	public Set<String> getCreatedNameTokens() {
		return createdNameTokens;
	}
	public void setCreatedNameTokens(Set<String> newCreatedNameTokens) {
		createdNameTokens = newCreatedNameTokens;
	}
	public ExternalStatement.Body getPreparsedListRun() {
		return preparsedListRun;
	}
	public void setPreparsedListRun(ExternalStatement.Body newPreparsedListRun) {
		preparsedListRun = newPreparsedListRun;
	}
	public ExternalStatement.Body getEndBuildersBody() {
		return endBuildersBody;
	}
	public void setEndBuildersBody(ExternalStatement.Body newEndBuildersBody) {
		endBuildersBody = newEndBuildersBody;
	}
	public Integer getListIndex() {
		return listIndex;
	}
	public void setListIndex(Integer newListIndex) {
		listIndex = newListIndex;
	}
	public Integer getMultipleIndex() {
		return multipleIndex;
	}
	public void setMultipleIndex(Integer newMultipleIndex) {
		multipleIndex = newMultipleIndex;
	}
	public Integer getRecursionIndex() {
		return recursionIndex;
	}
	public void setRecursionIndex(Integer newRecursionIndex) {
		recursionIndex = newRecursionIndex;
	}
	public Integer getGroupSuccessfulPositionIndex() {
		return groupSuccessfulPositionIndex;
	}
	public void setGroupSuccessfulPositionIndex(Integer newGroupSuccessfulPositionIndex) {
		groupSuccessfulPositionIndex = newGroupSuccessfulPositionIndex;
	}
	public void output() {
		if(ROOT_NAME==null) {
			System.err.println("No root rule found!");
		}
		else {
			for(final String ruleName:rules.keySet()) {
				final ExternalStatement.Body ruleBody = new ExternalStatement.Body();
				getRuleBody(ruleBody,ruleName,new HashMap<String,Set<Integer>>());
			}
			final ExternalStatement.Body extendsBody = new ExternalStatement.Body();
			final ExternalStatement.Body keepsBody = new ExternalStatement.Body();
			for(final String listName:MainFlow.self.listNames) {
				extendsBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("extend"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
				keepsBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("keep"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			}
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(endBuildersBody);
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputBuffer"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toCharArray"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement(new StringEntry("length"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("this._fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.globalIgnoresHeader),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))))));
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("SECOND_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(extendsBody),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("_parse_root"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("FIRST_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(keepsBody),"")))),new ExternalStatement.Conditional("try","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("for","(",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ImportThread"),"")),"",new ExternalStatement(new StringEntry("_import_thread"),""),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_threads"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_thread"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("join"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_thread"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getContext"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get_state"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))))))))),new ExternalStatement.Conditional("catch","(",new ExternalStatement(" ",new ExternalStatement(new StringEntry("Exception"),""),new ExternalStatement(new StringEntry("e0"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body())),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_threads"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("clear"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("_parse_root"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((ROOT_NAME).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("_succeedOnEnd"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))),"",new ExternalStatement(new StringEntry("pass"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_root"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("pass"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setup"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("pass"),""))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))))),new ExternalStatement.Conditional("else if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""),new ExternalStatement(new StringEntry("EOF"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))),new ExternalStatement.Conditional("else if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")))))))));
			for(final String listName:MainFlow.self.listNames) {
				final String listNameForMethod = MainFlow.camelize((listName).toString());
				ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("set"),""),new ExternalStatement(new VariableNameEntry((listNameForMethod).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")))))))));
				ExternalClassEntry.classMap.get("Parser").getSubClass("Result").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")))));
			ExternalClassEntry.classMap.get("Parser").getMethod("parseFile").appendToBody(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new ExternalStatement(new StringEntry("context"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("FinalContext"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(newContextParameters),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("parse"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),"")))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(new StringEntry("_root"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(new StringEntry("ROOT"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(new StringEntry("_root"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("contexts"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("parse"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),""))))))))))));
		}
		final String previousContextClassNameValue = MainFlow.self.previousContextClassName;
		ExternalClassEntry FinalContextClass = new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
	null,
	new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.packageName),"")),new ExternalStatement(new StringEntry("contexts"),"")),
	new Entry(){public void get(StringBuilder builder){}},
	new VariableNameEntry("FinalContext"),
	"class ",
	new ExternalStatement.TypeName(new ExternalStatement(new VariableNameEntry((previousContextClassNameValue).toString()),"")),
	Arrays.asList(new Entry[]{}),
	null);;}
			
			};
		FinalContextClass.__INIT__();
		MainFlow.outputClasses.add(FinalContextClass);
	}
	public void outputBraces() {
		Integer maxOpen = 0;
		for(final Integer key:declaredBraceOpenValues.keySet()) {
			if(key>maxOpen) {
				maxOpen=key;
			}
		}
		Integer maxClose = 0;
		for(final Integer key:declaredBraceCloseValues.keySet()) {
			if(key>maxClose) {
				maxClose=key;
			}
		}
		Integer max = 0;
		if(maxOpen>maxClose) {
			max=maxOpen;
		}
		else {
			max=maxClose;
		}
		final ExternalStatement.Body readBracesBody = new ExternalStatement.Body();
		readInputBody.add(preparsedListRun);
		readInputBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("13 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputBuffer"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("(char)_readInput"),"")))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new StringEntry("\\n"),""),new ExternalStatement(new StringEntry("'"),"")))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("escaping"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("escaping"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))))))),new ExternalStatement.Conditional("else if","(",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((notEscaping).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\\"),"")))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("escaping"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),""))))))),new ExternalStatement.Conditional("else if","(",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((notQuoting).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\""),"")))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("quoting"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("brace_open_0"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))),new ExternalStatement.Conditional("else if","(",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","&&","=="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("quoting"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\""),"")))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("quoting"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("brace_0"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("brace_open_0"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("pop"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))),new ExternalStatement.Conditional("else if","(",new ExternalStatement("&&",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((notQuoting).toString()),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((notEscaping).toString()),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(readBracesBody),"")))),new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("13 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))))));
		Integer i = maxOpen;
		while(i>0 ) {
			if(declaredBraceCloseValues.containsKey(i)) {
				final Map<String,List<Integer>> closeMap = declaredBraceCloseValues.get(i);
				for(final String key:closeMap.keySet()) {
					final ExternalStatement.Body closeBody = new ExternalStatement.Body();
					final ExternalStatement closeHeader = new ExternalStatement();
					Integer j = i;
					closeHeader.set("&&");
					final Integer jndexFirst = j;
					if(new Character(key.charAt(jndexFirst-1)).toString().equals("\'")) {
						closeHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndexFirst-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					else {
						closeHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndexFirst-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					j-=1;
					while(j>0 ) {
						final Integer jndex = j;
						if(new Character(key.charAt(j-1)).toString().equals("\'")) {
							closeHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndex-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						}
						else {
							closeHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndex-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						}
						j-=1;
					}
					readBracesBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(closeHeader),"")),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(closeBody),""))))));
					if(key.length()==1 ) {
						for(final Integer id:closeMap.get(key)) {
							closeBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("!brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("pop"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))))));
						}
					}
					else {
						final Integer keyOffset = key.length()-1;
						for(final Integer id:closeMap.get(key)) {
							closeBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("!brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("pop"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((keyOffset).toString()),"")))))))))))));
						}
					}
				}
			}
			if(declaredBraceOpenValues.containsKey(i)) {
				final Map<String,List<Integer>> openMap = declaredBraceOpenValues.get(i);
				for(final String key:openMap.keySet()) {
					final ExternalStatement.Body openBody = new ExternalStatement.Body();
					final ExternalStatement openHeader = new ExternalStatement();
					Integer j = i;
					openHeader.set("&&");
					final Integer jndexFirst = j;
					if(new Character(key.charAt(jndexFirst-1)).toString().equals("\'")) {
						openHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndexFirst-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					else {
						openHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndexFirst-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					j-=1;
					while(j>0 ) {
						final Integer jndex = j;
						if(new Character(key.charAt(j-1)).toString().equals("\'")) {
							openHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndex-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						}
						else {
							openHeader.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((new Character(key.charAt(jndex-1))).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
						}
						j-=1;
					}
					readBracesBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(openHeader),"")),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(openBody),""))))));
					if(key.length()==1 ) {
						for(final Integer id:openMap.get(key)) {
							openBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))));
						}
					}
					else {
						final Integer keyOffset = key.length()-1;
						for(final Integer id:openMap.get(key)) {
							openBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((id).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((keyOffset).toString()),""))))))))));
						}
					}
				}
			}
			i-=1;
		}
		i=max-1;
		while(i>1 ) {
			final Integer index = i;
			final Integer jndex = i-1;
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((index).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
			readInputBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((index).toString()),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_readInput_"),""),new ExternalStatement(new VariableNameEntry((jndex).toString()),"")))))));
			i-=1;
		}
		if(max>0 ) {
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput_1"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
			readInputBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput_1"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),""))))));
		}
		readInputBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputReader"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("read"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
	}
	public void findSilentRule(final com.rem.gen.parser.Token input) {
		if(input.get(com.rem.gen.parser.Token.Id._ruleName)!=null) {
			final String ruleName = (input.get(com.rem.gen.parser.Token.Id._ruleName)).toString();
			if(input.get(com.rem.gen.parser.Token.Id._SILENT)!=null) {
				silentRules.add(ruleName);
			}
			if(ROOT_NAME==null) {
				ROOT_NAME=ruleName;
			}
		}
	}
	public void setupCompile() {
		declaredBraces.add("\"\"");
		final Integer braceId = braceIds.size();
		braceIds.put("\"\"",braceId);
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceId).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").prependToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Stack"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((braceId).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Stack"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
	}
	public void findRuleHeirachy(final com.rem.gen.parser.Token ruleToken) {
		if(ruleToken.get(com.rem.gen.parser.Token.Id._ruleName)!=null) {
			final String ruleName = (ruleToken.get(com.rem.gen.parser.Token.Id._ruleName)).toString();
			MainFlow.self.ruleHeirachy.put(ruleName,new HashSet<String>());
			if(ROOT_NAME==null) {
				ROOT_NAME=ruleName;
			}
			for (final com.rem.gen.parser.Token definition: ruleToken.getAllSafely(com.rem.gen.parser.Token.Id._definition)) {
				findRuleHeirachy(ruleName,definition,ruleToken,MainFlow.self.ruleHeirachy.get(ruleName));
			}
		}
	}
	public void findRuleHeirachy(final String ruleName,final com.rem.gen.parser.Token definition,final com.rem.gen.parser.Token parentRule,final Set<String> subRuleSet) {
		Boolean hasFoundStopToken = false;
		for (final com.rem.gen.parser.Token chain: definition.getAllSafely(com.rem.gen.parser.Token.Id._chain)) {
			for (final com.rem.gen.parser.Token element: chain.getAllSafely(com.rem.gen.parser.Token.Id._element)) {
				if(hasFoundStopToken==false) {
					for (final com.rem.gen.parser.Token atom: element.getAll()) {
						switch (atom.getName()) {
							case _multiple: {
								for (final com.rem.gen.parser.Token quark: atom.getAllSafely(com.rem.gen.parser.Token.Id._definition)) {
									findRuleHeirachy(ruleName,quark,parentRule,subRuleSet);
								}
								break;
							}
							case _braced_definition: {
								for (final com.rem.gen.parser.Token quark: atom.getAllSafely(com.rem.gen.parser.Token.Id._definition)) {
									findRuleHeirachy(ruleName,quark,parentRule,subRuleSet);
								}
								break;
							}
							case _ruleToken: {
								subRuleSet.add((element).toString());
								MainFlow.self.subRulesProposed.put((element).toString(),parentRule);
								break;
							}
							case _regexToken: {
								hasFoundStopToken=true;
								break;
							}
							case _quoteToken: {
								hasFoundStopToken=true;
								break;
							}
						}
					}
				}
			}
		}
		for (final com.rem.gen.parser.Token choice: definition.getAllSafely(com.rem.gen.parser.Token.Id._choice)) {
			for (final com.rem.gen.parser.Token element: choice.getAllSafely(com.rem.gen.parser.Token.Id._definition)) {
				findRuleHeirachy(ruleName,element,parentRule,subRuleSet);
			}
		}
	}
	public void consolidateRuleHeirachy() {
		final Map<String,Set<String>> additions = new HashMap<String,Set<String>>();
		additions.put(ROOT_NAME,new HashSet<String>());
		consolidateRuleHeirachy(ROOT_NAME,additions,new HashSet<String>());
		for(final String ruleName:additions.keySet()) {
			if(MainFlow.self.ruleHeirachy.containsKey(ruleName)) {
				MainFlow.self.ruleHeirachy.get(ruleName).addAll(additions.get(ruleName));
			}
		}
	}
	public void consolidateRuleHeirachy(final String ruleName,final Map<String,Set<String>> additions,final Set<String> consulted) {
		if(consulted.add(ruleName)) {
			if(MainFlow.self.ruleHeirachy.containsKey(ruleName)) {
				for(final String subRuleName:MainFlow.self.ruleHeirachy.get(ruleName)) {
					if(additions.containsKey(subRuleName)) {
						additions.get(subRuleName).add(ruleName);
					}
					else {
						additions.put(subRuleName,new HashSet<String>());
						consolidateRuleHeirachy(subRuleName,additions,consulted);
					}
				}
			}
		}
	}
	public void list(final com.rem.gen.parser.Token input) {
		final String listName = (input.get(com.rem.gen.parser.Token.Id._listName)).toString();
		ExternalClassEntry.classMap.get("Parser").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_root"),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))))));
		newContextParameters.add(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((input.get(com.rem.gen.parser.Token.Id._listName)).toString()),""),new ExternalStatement(new StringEntry("_root"),""))));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((input.get(com.rem.gen.parser.Token.Id._listName)).toString()),""),new ExternalStatement(new StringEntry("_root"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((input.get(com.rem.gen.parser.Token.Id._listName)).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("*").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((input.get(com.rem.gen.parser.Token.Id._listName)).toString()),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((input.get(com.rem.gen.parser.Token.Id._listName)).toString()),""),new ExternalStatement(new StringEntry("_root"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
		MainFlow.self.listNames.add((input.get(com.rem.gen.parser.Token.Id._listName)).toString());
		for (final com.rem.gen.parser.Token element: input.getAllSafely(com.rem.gen.parser.Token.Id._SYNTAX)) {
			if(element.getValue().equals("global")) {
				globalListNames.add((input.get(com.rem.gen.parser.Token.Id._listName)).toString());
			}
		}
		if(input.get(com.rem.gen.parser.Token.Id._listRuleName)!=null) {
			listFirstPassRules.put((listName).toString(),(input.get(com.rem.gen.parser.Token.Id._listRuleName)).toString());
			if(listPreparers.add((input.get(com.rem.gen.parser.Token.Id._listRuleName)).toString())) {
				final String builderClassName = (input.get(com.rem.gen.parser.Token.Id._listRuleName)).toString();
				ExternalClassEntry.classMap.get("Parser").getSubClass("NameList").getSubClass("Builder").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
					null,
					new ExternalStatement("."),
					new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry(builderClassName),
					"class ",
					new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Builder"),""))),
					Arrays.asList(new Entry[]{}),
					null);
				setIsStatic(true);;__add_method_0__();}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("can"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("char"),"")),"",new ExternalStatement(new StringEntry("nextChar"),""),null)}),null,new ExternalStatement.Body()));}});
				ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_preparsed_"),""),new ExternalStatement(new VariableNameEntry((builderClassName).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
				ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").prependToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Builder"),""))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_builder_"),""),new ExternalStatement(new VariableNameEntry((builderClassName).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Builder"),""),new ExternalStatement(new VariableNameEntry((builderClassName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_preparsed_"),""),new ExternalStatement(new VariableNameEntry((builderClassName).toString()),""))))))))));
				endBuildersBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_builder_"),""),new ExternalStatement(new VariableNameEntry((builderClassName).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("end"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))));
				preparsedListRun.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_builder_"),""),new ExternalStatement(new VariableNameEntry((builderClassName).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("char"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),""))))))))));
			}
		}
		for (final com.rem.gen.parser.Token element: input.getAllSafely(com.rem.gen.parser.Token.Id._quote)) {
			ExternalClassEntry.classMap.get("Parser").getMethod("parseFile").appendToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((listName).toString()),""),new ExternalStatement(new StringEntry("_root"),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element).toString()),"")))))))))));
		}
		ExternalClassEntry.classMap.get("Parser").getMethod("parseFile").appendToBody(new ExternalStatement.Body());
	}
	public ExternalStatement.Body getListDeclarations(final String ruleName,final boolean hasSecondPass) {
		final ExternalStatement.Body listDeclarations = new ExternalStatement.Body();
		for(final String listName:MainFlow.self.listNames) {
			if(globalListNames.contains(listName)==false) {
				if(hasSecondPass) {
					listDeclarations.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("SECOND_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
				}
				else {
					listDeclarations.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("push"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),""))))))))));
				}
			}
		}
		return listDeclarations;
	}
	public ExternalStatement.Body getListReallocations(final String ruleName) {
		final ExternalStatement.Body listReallocations = new ExternalStatement.Body();
		for(final String listName:MainFlow.self.listNames) {
			if(globalListNames.contains(listName)==false) {
				listReallocations.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("pop"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
			}
		}
		return listReallocations;
	}
	public void getRuleBody(final ExternalStatement.Body completeBody,final String ruleName,final Map<String,Set<Integer>> excludeIndicesMap) {
		final String currentLengthValue = "_length_"+(ruleName).toString()+"_brace";
		final List<ExternalStatement.Body> rule = rules.get(ruleName);
		ExternalStatement.Body currentOption = completeBody;
		final ExternalStatement.Body withinBraces = new ExternalStatement.Body();
		final Integer ruleListIndex = listIndex;
		final String currentRulePositionValue = "_position_"+ruleName;
		final String currentRuleTokenValue = "_token_"+ruleName;
		currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentRulePositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))))));
		currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentRuleTokenValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))));
		if(declaredPositions.containsKey(ruleName)) {
			for(final String subRuleName:declaredPositions.get(ruleName)) {
				currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))))));
			}
		}
		if(subRuleTokenDeclarations.containsKey(ruleName)) {
			for(final String subRuleName:subRuleTokenDeclarations.get(ruleName)) {
				currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_token_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))));
			}
		}
		if(declaredBraceRules.containsKey(ruleName)) {
			listIndex+=1;
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentLengthValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))))));
			if(declaredBracePasses.containsKey(ruleName)) {
				if(declaredBracePasses.get(ruleName)==1 ) {
					withinBraces.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(withinBraces),""))))));
					withinBraces.add(getListDeclarations(ruleName,false));
				}
				else if(declaredBracePasses.get(ruleName)==2 ) {
					final ExternalStatement.Body completeBraceBody = new ExternalStatement.Body();
					completeBraceBody.add(getListDeclarations(ruleName,true));
					completeBraceBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(withinBraces),"")))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((declaredBraceCloseLengths.get(ruleName)).toString()),""))))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))))));
					currentOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("containsKey"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(completeBraceBody),""))))));
				}
			}
			else {
				currentOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("containsKey"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(withinBraces),""))))));
				withinBraces.add(getListDeclarations(ruleName,false));
			}
			currentOption=withinBraces;
			final String currentPositionValue = "_position_"+(ruleName).toString()+"_brace";
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))));
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((declaredBraceOpenLengths.get(ruleName)).toString()),""))))));
			currentOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))))));
		}
		for(final String listName:MainFlow.self.listNames) {
			currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("start"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))));
		}
		ExternalStatement.Body previousOption = null;
		for(Integer i = 0;i<rule.size();i++) {
			if(previousOption!=null) {
				previousOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(currentOption),""))))));
			}
			if(previousOption!=null) {
				for(final String listName:MainFlow.self.listNames) {
					currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("reject"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))))))))));
				}
				currentOption.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))))));
			}
			previousOption=currentOption;
			currentOption.add(rule.get(i));
			currentOption=new ExternalStatement.Body();
		}
		if(declaredBraceRules.containsKey(ruleName)) {
			final String currentPositionValue = "_position_"+(ruleName).toString()+"_brace";
			withinBraces.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry("_brace"),""))))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((declaredBraceCloseLengths.get(ruleName)).toString()),""))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result_acceptor"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""),new ExternalStatement(new StringEntry("EOB"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((ruleName).toString()),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceIds.get(declaredBraceRules.get(ruleName))).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry("_brace"),""))))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((declaredBraceCloseLengths.get(ruleName)).toString()),""))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_succeedOnEnd"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentLengthValue).toString()),"")))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.globalIgnoresHeader),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))))));
		}
		final ExternalStatement.Body removeAdditionsBody = new ExternalStatement.Body();
		final ExternalStatement.Body addAdditionBody = new ExternalStatement.Body();
		previousOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(removeAdditionsBody),""))))));
		previousOption.add(new ExternalStatement.Body(new ExternalStatement.Conditional("else if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(addAdditionBody),""))))));
		if(declaredBraceRules.containsKey(ruleName)) {
			previousOption.add(getListReallocations(ruleName));
		}
		for(final String listName:MainFlow.self.listNames) {
			removeAdditionsBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("reject"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))))))))));
			addAdditionBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("accept"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))))))))));
		}
		if(declaredBraceRules.containsKey(ruleName)) {
			completeBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))),new ExternalStatement.Conditional("if","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((parentRuleNames.get(ruleName)).toString()),""),new ExternalStatement(new StringEntry("("),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry(")"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))))));
		}
		if(((ruleName).toString().startsWith("_anonymous"))) {
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body())._abstract());
			ExternalClassEntry.classMap.get("AnonymousContext").addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
			ExternalClassEntry.classMap.get("AnonymousContext").getMethod("parse_"+(ruleName).toString()).appendToBody(completeBody);
		}
		else {
			final String contextClassName;
			final String contextPackageName;
			final int indexOfUnderscore = (ruleName).toString().indexOf("_");
			if(indexOfUnderscore>-1 &&(ruleName).toString().substring(0,indexOfUnderscore).equals("class")==false) {
				contextClassName=(ruleName).toString().substring(0,indexOfUnderscore).toLowerCase()+"_context";
				contextPackageName=(ruleName).toString().substring(0,indexOfUnderscore);
			}
			else {
				contextClassName=(ruleName).toString().toLowerCase()+"_context";
				contextPackageName=(ruleName).toString();
			}
			if(MainFlow.self.declaredContexts.add(contextClassName)) {
				final String previousContextClassNameValue = MainFlow.self.previousContextClassName;
				ExternalClassEntry contextClassNameClass = new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
	null,
	new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.packageName),"")),new ExternalStatement(new StringEntry("contexts"),"")),
	new Entry(){public void get(StringBuilder builder){}},
	new VariableNameEntry(contextClassName),
	"class ",
	new ExternalStatement.TypeName(new ExternalStatement(new VariableNameEntry((previousContextClassNameValue).toString()),"")),
	Arrays.asList(new Entry[]{}),
	null);
setIsAbstract(true);;__add_variable_0__();;__add_variable_1__();;__add_method_0__();;__add_method_1__();;__add_method_2__();;__add_method_3__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),"",new ExternalStatement(new StringEntry("__parser__"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_variable_1__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Tokens"),"")),"",new ExternalStatement(new StringEntry("__tokens__"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_Parser");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("__parser__"),"")))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_Parser");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_Parser");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("__parser__ = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_Parser"),"")))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Tokens"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_Tokens");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("__tokens__"),"")))));}
			public void __add_method_3__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_Tokens");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Tokens"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_Tokens");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("__tokens__ = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_Tokens"),"")))));}};
				contextClassNameClass.__INIT__();
				MainFlow.outputClasses.add(contextClassNameClass);
				MainFlow.self.previousContextClassName=contextClassName;
			}
			ExternalClassEntry.classMap.get((contextClassName).toString()).addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
			ExternalClassEntry.classMap.get((contextClassName).toString()).getMethod("parse_"+(ruleName).toString()).appendToBody(completeBody);
		}
		ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body())._abstract());
	}
	public String define(final com.rem.gen.parser.Token input,final String previousParentRuleName) {
		return define(input,previousParentRuleName,null,null);
	}
	public String define(final com.rem.gen.parser.Token input,final String previousParentRuleName,final String handleBracedTokenName,final Set<String> parentImportRuleNames) {
		final String ruleName;
		final String parentRuleName;
		if(input.get(com.rem.gen.parser.Token.Id._ruleName)!=null) {
			ruleName=(input.get(com.rem.gen.parser.Token.Id._ruleName)).toString();
			final String ruleClassName = MainFlow.camelize((ruleName).toString())+"Token";
			ExternalClassEntry.classMap.get("Token").getSubClass("Id").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))),null));
			ExternalClassEntry.classMap.get("Tokens").getSubClass("Rule").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(ruleClassName),
				"class ",
				new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("value"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")))))))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setValue");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newValue");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("value = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newValue"),"")))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),null,new ExternalStatement(new StringEntry("getName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))))))));}});
			parentRuleName=ruleName;
		}
		else {
			ruleName="_anonymous_"+anonymousRuleIndex;
			anonymousRuleIndex+=1;
			silentRules.add(ruleName);
			parentRuleName=previousParentRuleName;
		}
		parentRuleNames.put(ruleName,parentRuleName);
		if(ROOT_NAME==null) {
			ROOT_NAME=ruleName;
		}
		if(rules.containsKey(ruleName)==false) {
			completeRules.put(ruleName,new ArrayList<ExternalStatement.Body>());
			rules.put(ruleName,completeRules.get(ruleName));
			completeRules.get(ruleName).add(new ExternalStatement.Body());
		}
		final List<ExternalStatement.Body> rule = completeRules.get(ruleName);
		final Boolean isSilent = input.get(com.rem.gen.parser.Token.Id._SILENT)!=null;
		if(isSilent) {
			silentRules.add(ruleName);
		}
		final ExternalStatement.Body foreBody = new ExternalStatement.Body();
		final String currentPositionValue = "_position_"+ruleName;
		foreBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new VariableNameEntry((parentRuleNames.get(ruleName)).toString()),""),new ExternalStatement(new StringEntry("("),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry(")"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),""))))));
		ruleForeBodies.put(ruleName,foreBody);
		if(input.get(com.rem.gen.parser.Token.Id._ignores_character)!=null) {
			final ExternalStatement newIgnoresHeader = new ExternalStatement();
			final ExternalStatement newIgnoresVariableSection = new ExternalStatement();
			MainFlow.self.setupIgnoresHeader(newIgnoresHeader,newIgnoresVariableSection);
			for (final com.rem.gen.parser.Token character: input.getAllSafely(com.rem.gen.parser.Token.Id._ignores_character)) {
				MainFlow.self.addIgnoresCharacter((character).toString(),newIgnoresVariableSection);
			}
			MainFlow.self.ruleIgnoresHeaders.put(ruleName,newIgnoresHeader);
		}
		else if(input.get(com.rem.gen.parser.Token.Id._ignores_none)!=null) {
			final ExternalStatement newIgnoresHeader = new ExternalStatement();
			MainFlow.self.setupIgnoresHeader(newIgnoresHeader,new ExternalStatement());
			MainFlow.self.ruleIgnoresHeaders.put(ruleName,newIgnoresHeader);
		}
		else {
			MainFlow.self.ruleIgnoresHeaders.put(ruleName,MainFlow.self.globalIgnoresHeader);
		}
		if(input.get(com.rem.gen.parser.Token.Id._import_parameter)!=null) {
			final Set<String> importRuleNames = new HashSet<String>();
			final String ruleClassName = MainFlow.camelize((ruleName).toString());
			final String fileImportRuleName = ruleName+"__file_import";
			final String fileImportRuleClassName = MainFlow.camelize((fileImportRuleName).toString())+"Token";
			final String importContextClassName = MainFlow.camelize((fileImportRuleName).toString());
			silentRules.add(fileImportRuleName);
			ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(importContextClassName),
				"class ",
				new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("FinalContext"),"")),
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);;__add_method_0__();}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("_parse_root"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((fileImportRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));}});
			final ExternalStatement.Body fileNameStatement = new ExternalStatement.Body();
			fileNameStatement.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")),"",new ExternalStatement(new StringEntry("_fileNameBuilder"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			for (final com.rem.gen.parser.Token parameter: input.getAllSafely(com.rem.gen.parser.Token.Id._import_parameter)) {
				for (final com.rem.gen.parser.Token element: parameter.getAll()) {
					switch (element.getName()) {
						case _quote: {
							fileNameStatement.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileNameBuilder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element).toString()),"")))))))))));
							break;
						}
						case _ruleName: {
							final String importRuleName = (element).toString();
							importRuleNames.add(importRuleName);
							if(importRuleNameTokens.add((importRuleName).toString()+"_token")) {
								ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_import_"),""),new ExternalStatement(new VariableNameEntry((importRuleName).toString()),""),new ExternalStatement(new StringEntry("_value"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
							}
							fileNameStatement.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileNameBuilder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_import_"),""),new ExternalStatement(new VariableNameEntry((importRuleName).toString()),""),new ExternalStatement(new StringEntry("_value"),""))))))))));
							break;
						}
					}
				}
			}
			fileNameStatement.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_import_file_name"),""),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("_directoryName"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileNameBuilder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))),new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("File"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_file_name"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("exists"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("containsKey"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_file_name"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new ExternalStatement(new StringEntry("_import_context"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""),new ExternalStatement(new VariableNameEntry((importContextClassName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(newContextParameters),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_file_name"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_context"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_file_name"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("set_root"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(new StringEntry("ROOT"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_file_name"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("set_token"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_file_name"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get_root"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""),new ExternalStatement(new StringEntry("Import"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_file_name"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("addImportThread"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_file_name"),""))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("_directoryName"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileNameBuilder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(" cannot be found!"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("err"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Could not find file:"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_directoryName"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileNameBuilder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))))))));
			define(input.get(com.rem.gen.parser.Token.Id._import_definition),ruleName,parentRuleName,handleBracedTokenName,importRuleNames,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(fileNameStatement),""))))),rule);
			if(rules.containsKey(fileImportRuleName)==false) {
				completeRules.put(fileImportRuleName,new ArrayList<ExternalStatement.Body>());
				rules.put(fileImportRuleName,completeRules.get(fileImportRuleName));
				completeRules.get(fileImportRuleName).add(new ExternalStatement.Body());
			}
			ExternalClassEntry.classMap.get("Token").getSubClass("Id").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((fileImportRuleName).toString()),""))),null));
			ExternalClassEntry.classMap.get("Tokens").getSubClass("Rule").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(fileImportRuleClassName),
				"class ",
				new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("value"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")))))))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setValue");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newValue");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("value = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newValue"),"")))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),null,new ExternalStatement(new StringEntry("getName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((fileImportRuleName).toString()),""))))))));}});
			ruleForeBodies.put(fileImportRuleName,new ExternalStatement.Body());
			final List<ExternalStatement.Body> importRule = completeRules.get(fileImportRuleName);
			for (final com.rem.gen.parser.Token definition: input.getAllSafely(com.rem.gen.parser.Token.Id._definition)) {
				define(definition,fileImportRuleName,ruleName,null,parentImportRuleNames,null,importRule);
			}
			return ruleName;
		}
		if(input.get(com.rem.gen.parser.Token.Id._braced_parameters)!=null) {
			final String left = (input.get(com.rem.gen.parser.Token.Id._braced_parameters).get(com.rem.gen.parser.Token.Id._left)).toString();
			final String right = (input.get(com.rem.gen.parser.Token.Id._braced_parameters).get(com.rem.gen.parser.Token.Id._right)).toString();
			final String both = left+right;
			final Integer openLength = left.length();
			final Integer closeLength = right.length();
			if(declaredBraceValues.containsKey(both)==false) {
				declaredBraceValues.put(both,new ArrayList<String>());
			}
			if(input.get(com.rem.gen.parser.Token.Id._passConstraint)!=null) {
				declaredBracePasses.put(ruleName,Integer.parseInt((input.get(com.rem.gen.parser.Token.Id._passConstraint)).toString().trim()));
			}
			declaredBraceValues.get(both).add(ruleName);
			declaredBraceRules.put(ruleName,both);
			declaredBraceOpenLengths.put(ruleName,openLength);
			declaredBraceCloseLengths.put(ruleName,closeLength);
			if(declaredBraces.add(both)) {
				final Integer braceId = braceIds.size();
				braceIds.put(both,braceId);
				if(declaredBraceOpenValues.containsKey(left.length())==false) {
					declaredBraceOpenValues.put(left.length(),new HashMap<String,List<Integer>>());
				}
				if(declaredBraceOpenValues.get(left.length()).containsKey(left)==false) {
					declaredBraceOpenValues.get(left.length()).put(left,new ArrayList<Integer>());
				}
				declaredBraceOpenValues.get(left.length()).get(left).add(braceId);
				if(declaredBraceCloseValues.containsKey(right.length())==false) {
					declaredBraceCloseValues.put(right.length(),new HashMap<String,List<Integer>>());
				}
				if(declaredBraceCloseValues.get(right.length()).containsKey(right)==false) {
					declaredBraceCloseValues.get(right.length()).put(right,new ArrayList<Integer>());
				}
				declaredBraceCloseValues.get(right.length()).get(right).add(braceId);
				ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_"),""),new ExternalStatement(new VariableNameEntry((braceId).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
				ExternalClassEntry.classMap.get("Parser").getSubClass("Context").getMethod("parse").prependToBody(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Stack"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("brace_open_"),""),new ExternalStatement(new VariableNameEntry((braceId).toString()),""))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Stack"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			}
		}
		for (final com.rem.gen.parser.Token definition: input.getAllSafely(com.rem.gen.parser.Token.Id._definition)) {
			define(definition,ruleName,parentRuleName,handleBracedTokenName,rule);
		}
		return ruleName;
	}
	public void define(final com.rem.gen.parser.Token definition,final String ruleName,final String parentRuleName,final String handleBracedTokenName,final List<ExternalStatement.Body> inputRule) {
		define(definition,ruleName,parentRuleName,handleBracedTokenName,null,null,inputRule);
	}
	public void define(final com.rem.gen.parser.Token definition,final String ruleName,final String parentRuleName,final String handleBracedTokenName,final Set<String> importRuleNames,final ExternalStatement.Body importFileNameBody,final List<ExternalStatement.Body> inputRule) {
		Boolean isFirst = true;
		final String ruleAsClass = MainFlow.camelize((ruleName).toString())+"Token";
		final Integer choiceIndex = inputRule.size()-1;
		ExternalStatement.Body rule = inputRule.get(choiceIndex);
		ExternalStatement.Body nextBody = null;
		final String currentPositionValue = "_position_"+ruleName;
		final String currentTokenValue = "_token_"+ruleName;
		rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
		rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))));
		if(silentRules.contains(ruleName)==false) {
			rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Rule"),""),new ExternalStatement(new VariableNameEntry((ruleAsClass).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
		}
		else {
			if(handleBracedTokenName!=null) {
				rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(new VariableNameEntry((handleBracedTokenName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			}
			else if((ruleName.startsWith("_anonymous"))) {
				rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(new StringEntry("ANON"),"")))))))));
			}
			else {
				rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Rule"),""),new ExternalStatement(new VariableNameEntry((ruleAsClass).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
			}
		}
		final ExternalStatement.Body foreBody = ruleForeBodies.get(ruleName);
		for (final com.rem.gen.parser.Token chain: definition.getAllSafely(com.rem.gen.parser.Token.Id._chain)) {
			for (final com.rem.gen.parser.Token element: chain.getAllSafely(com.rem.gen.parser.Token.Id._element)) {
				final ExternalStatement.Body elementBody = encloseRuleByMultiple(element.get(com.rem.gen.parser.Token.Id._multiple),ruleName,rule);
				parseElement(element,ruleName,parentRuleName,elementBody,choiceIndex,importRuleNames,isFirst);
				isFirst=false;
				nextBody=new ExternalStatement.Body();
				final ExternalStatement.Body realNextBody = nextBody;
				rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(foreBody),"")))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(realNextBody),""))))));
				rule=nextBody;
			}
		}
		if(importFileNameBody!=null) {
			inputRule.get(inputRule.size()-1).add(importFileNameBody);
		}
		if(silentRules.contains(ruleName)==false||handleBracedTokenName!=null) {
			inputRule.get(inputRule.size()-1).add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))))))))));
			if(handleBracedTokenName!=null&&importRuleNames!=null&&importRuleNames.contains(handleBracedTokenName)) {
				inputRule.get(inputRule.size()-1).add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_import_"),""),new ExternalStatement(new VariableNameEntry((handleBracedTokenName).toString()),""),new ExternalStatement(new StringEntry("_value"),""))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
			}
			else if(importRuleNames!=null&&importRuleNames.contains(ruleName)) {
				inputRule.get(inputRule.size()-1).add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_import_"),""),new ExternalStatement(new VariableNameEntry((ruleName).toString()),""),new ExternalStatement(new StringEntry("_value"),""))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
			}
			inputRule.get(inputRule.size()-1).add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),""))))));
		}
		else {
			inputRule.get(inputRule.size()-1).add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("addAll"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentTokenValue).toString()),""))))));
		}
		if(definition.get(com.rem.gen.parser.Token.Id._choice)!=null) {
			inputRule.add(new ExternalStatement.Body());
			define(definition.get(com.rem.gen.parser.Token.Id._choice).get(com.rem.gen.parser.Token.Id._definition),ruleName,parentRuleName,handleBracedTokenName,inputRule);
		}
	}
	public ExternalStatement.Body encloseRuleByMultiple(final com.rem.gen.parser.Token multiple,final String ruleName,final ExternalStatement.Body rule) {
		if(multiple==null) {
			return rule;
		}
		if(handleListAdditionAftBodies.containsKey(ruleName)==false) {
			handleListAdditionAftBodies.put(ruleName,new ExternalStatement.Body());
		}
		final ExternalStatement.Body aftBody = new ExternalStatement.Body();
		final ExternalStatement.Body resultBody = new ExternalStatement.Body();
		final String stateName = "_state_"+multipleIndex;
		for (final com.rem.gen.parser.Token option: multiple.getAll()) {
			switch (option.getName()) {
				case _OPTIONAL: {
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),""))))));
					rule.add(resultBody);
					rule.add(aftBody);
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")))))))));
					break;
				}
				case _MANY: {
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),""))))));
					final ExternalStatement.Body whileRuleBody = new ExternalStatement.Body();
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("while","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(whileRuleBody),""))))));
					whileRuleBody.add(resultBody);
					final ExternalStatement.Body breakAftBody = new ExternalStatement.Body();
					breakAftBody.add(aftBody);
					breakAftBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))));
					whileRuleBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(breakAftBody),""))))));
					final ExternalStatement.Body afterMultipleBody = new ExternalStatement.Body();
					afterMultipleBody.add(aftBody);
					afterMultipleBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))))));
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(afterMultipleBody),""))))));
					break;
				}
				case _PLUS: {
					final String multipleValueName = "_iteration_achieved_"+multipleIndex;
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),""))))));
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))))));
					final ExternalStatement.Body breakAftBody = new ExternalStatement.Body();
					breakAftBody.add(aftBody);
					breakAftBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))));
					final ExternalStatement.Body whileRuleBody = new ExternalStatement.Body();
					whileRuleBody.add(resultBody);
					whileRuleBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(breakAftBody),"")))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))))))));
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("while","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(whileRuleBody),""))))));
					final ExternalStatement.Body noIterationFailBody = new ExternalStatement.Body();
					noIterationFailBody.add(aftBody);
					noIterationFailBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))));
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(noIterationFailBody),"")))),new ExternalStatement.Conditional("else if","(",new ExternalStatement(null,null,"==",Arrays.asList(new String[]{"","==","&&","=="}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")))))))));
					break;
				}
			}
		}
		multipleIndex+=1;
		return resultBody;
	}
	public void parseElement(final com.rem.gen.parser.Token element,final String ruleName,final String parentRuleName,final ExternalStatement.Body rule,final Integer choiceIndex,final Set<String> importRuleNames,final Boolean isFirst) {
		for (final com.rem.gen.parser.Token query: element.getAll()) {
			switch (query.getName()) {
				case _braced_definition: {
					final String handleBracedTokenName;
					if(element.get(com.rem.gen.parser.Token.Id._newName)!=null) {
						final String newTokenName = MainFlow.camelize((element.get(com.rem.gen.parser.Token.Id._newName)).toString())+"Token";
						final String simpleTokenName = (element.get(com.rem.gen.parser.Token.Id._newName)).toString();
						declareNamedToken(newTokenName,simpleTokenName);
						handleBracedTokenName=newTokenName;
					}
					else if(element.get(com.rem.gen.parser.Token.Id._listName)!=null) {
						handleBracedTokenName=MainFlow.camelize((element.get(com.rem.gen.parser.Token.Id._listName)).toString())+"Token";
					}
					else {
						handleBracedTokenName=null;
					}
					final String subRuleName = define(query,parentRuleName,handleBracedTokenName,importRuleNames);
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
					if(element.get(com.rem.gen.parser.Token.Id._listName)!=null) {
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_value"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLastValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element.get(com.rem.gen.parser.Token.Id._listName)).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")))))))))))))));
						if(listNamesInRule.containsKey(parentRuleName)==false) {
							listNamesInRule.put(parentRuleName,new HashSet<String>());
						}
						listNamesInRule.get(parentRuleName).add((element.get(com.rem.gen.parser.Token.Id._listName)).toString());
						handleListAdditions.put(ruleName,parentRuleName);
						if(handleListAdditionAftBodies.containsKey(ruleName)==false) {
							handleListAdditionAftBodies.put(ruleName,new ExternalStatement.Body());
						}
					}
					break;
				}
				case _quoteToken: {
					final String quote = (element.get(com.rem.gen.parser.Token.Id._quoteToken).get(com.rem.gen.parser.Token.Id._quote)).toString();
					Integer quoteLength = quote.length();
					final ExternalStatement.Body subrule = new ExternalStatement.Body();
					final StringBuilder quoteValue = new StringBuilder();
					Integer ip = 0;
					for(Integer i = 0;i<quote.length();i++) {
						final String ch;
						final String quoteChar = quote.charAt(i)+"";
						if(quoteChar.equals("\\")) {
							if(i+1 <quote.length()) {
								final String nextChar = quote.charAt(i+1)+"";
								if(nextChar.equals("t")) {
									ch="\'\\t'";
									quoteValue.append("\\t");
									quoteLength-=1;
									i+=1;
								}
								else if(nextChar.equals("n")) {
									ch="\'\\n'";
									quoteValue.append("\\n");
									quoteLength-=1;
									i+=1;
								}
								else if(nextChar.equals("r")) {
									ch="\'\\r'";
									quoteValue.append("\\r");
									quoteLength-=1;
									i+=1;
								}
								else if(nextChar.equals("\"")) {
									ch="\'\"'";
									quoteValue.append("\"");
									quoteLength-=1;
									i+=1;
								}
								else if(nextChar.equals("\'")) {
									ch="\'\\\'\'";
									quoteValue.append("\'");
									quoteLength-=1;
									i+=1;
								}
								else if(nextChar.equals("\\")) {
									ch="\'\\\\\'";
									quoteValue.append("\\\\");
									quoteLength-=1;
									i+=1;
								}
								else {
									ch="\'\\\\\'";
									quoteValue.append("\\\\");
								}
							}
							else {
								ch="\'\\\\\'";
								quoteValue.append("\\\\");
							}
						}
						else if(quoteChar.equals("'")) {
							ch="\'\\\'\'";
							quoteValue.append("\\'");
						}
						else {
							ch="\'"+quote.charAt(i)+"\'";
							quoteValue.append(quote.charAt(i));
						}
						final String ipValue = (ip).toString();
						if(ch.equals("\'\\n'")) {
							subrule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,"!=",Arrays.asList(new String[]{"","!=","&&","!="}),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement.ArrayParameters(new ExternalStatement.Parameters("][",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position+"),""),new ExternalStatement(new VariableNameEntry((ipValue).toString()),"")))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((ch).toString()),"")),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement.ArrayParameters(new ExternalStatement.Parameters("][",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position+"),""),new ExternalStatement(new VariableNameEntry((ipValue).toString()),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("\'\\r'"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
						}
						else {
							subrule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement.ArrayParameters(new ExternalStatement.Parameters("][",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position+"),""),new ExternalStatement(new VariableNameEntry((ipValue).toString()),"")))))),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((ch).toString()),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
						}
						ip+=1;
					}
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,">=",Arrays.asList(new String[]{"","+","-",">="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((quoteLength).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1 "),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(subrule),""))))));
					final Integer quoteLengthValue = quoteLength;
					if(createdSyntaxTokens.containsKey(quote)==false) {
						final String quoteName = "syntax_"+plainTokenIndex;
						plainTokenIndex+=1;
						createdSyntaxTokens.put(quote,quoteName);
						createdSyntaxNameTokens.put(quote,new HashSet<String>());
						ExternalClassEntry.classMap.get("Tokens").getSubClass("Syntax").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
							null,
							new ExternalStatement("."),
							new Entry(){public void get(StringBuilder builder){}},
							new VariableNameEntry(quoteName),
							"class ",
							new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
							Arrays.asList(new Entry[]{}),
							null);
						setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(new VariableNameEntry((quoteName).toString()),""))),"",new ExternalStatement(new StringEntry("SYNTAX"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(new VariableNameEntry((quoteName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(new StringEntry("_SYNTAX"),"")))))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setSYNTAX");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(new VariableNameEntry((quoteName).toString()),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newSYNTAX");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("SYNTAX = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newSYNTAX"),"")))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((quote.replace("\\","\\\\")).toString()),""))))))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setValue"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("newValue"),""),null)}),null,new ExternalStatement.Body()));}});
					}
					final String quoteName = createdSyntaxTokens.get(quote);
					final String newTokenName;
					if(element.get(com.rem.gen.parser.Token.Id._newName)!=null) {
						newTokenName=(element.get(com.rem.gen.parser.Token.Id._newName)).toString();
						if(createdSyntaxNameTokens.get(quote).add(newTokenName)) {
							ExternalClassEntry.classMap.get("Token").getSubClass("Id").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((newTokenName).toString()),""))),null));
							ExternalClassEntry.classMap.get("Tokens").getSubClass("Syntax").getSubClass(""+quoteName.toString()+"").addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((newTokenName).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(new VariableNameEntry((quoteName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((newTokenName).toString()),"")))))))));
						}
					}
					else {
						newTokenName="SYNTAX";
					}
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Syntax"),""),new ExternalStatement(new VariableNameEntry((quoteName).toString()),""),new ExternalStatement(new VariableNameEntry((newTokenName).toString()),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((quoteLengthValue).toString()),""))))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))))))))),new ExternalStatement.Conditional("else if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("unexpected plain "),""),new ExternalStatement(new VariableNameEntry((quoteValue).toString()),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))))));
					if(importRuleNames!=null&&importRuleNames.contains(newTokenName)) {
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_import_"),""),new ExternalStatement(new VariableNameEntry((newTokenName).toString()),""),new ExternalStatement(new StringEntry("_value"),""))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLastValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
					}
					break;
				}
				case _regexToken: {
					final com.rem.gen.parser.Token regex = element.get(com.rem.gen.parser.Token.Id._regexToken).get(com.rem.gen.parser.Token.Id._regex);
					final String currentPositionValue = "_position_regex_"+currentPositionIndex;
					final StringBuilder regexValue = new StringBuilder();
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
					for (final com.rem.gen.parser.Token regexElement: regex.getAllSafely(com.rem.gen.parser.Token.Id._regex_element)) {
						regexValue.append(addRegexElementToRule(regexElement,rule,currentPositionValue));
					}
					if(listPreparers.contains(ruleName)) {
						int currentStateOfPreparer = 0;
						final ExternalClassEntry preparerClass = ExternalClassEntry.classMap.get("Parser").getSubClass("NameList").getSubClass("Builder").getSubClass(""+ruleName.toString()+"");
						final ExternalStatement.Body switchBody = new ExternalStatement.Body();
						for (final com.rem.gen.parser.Token regexElement: regex.getAllSafely(com.rem.gen.parser.Token.Id._regex_element)) {
							switchBody.add(getRegexForPreparer(regexElement,ruleName,preparerClass,currentStateOfPreparer));
							currentStateOfPreparer+=1;
						}
						preparerClass.getMethod("can").appendToBody(new ExternalStatement.Body(new ExternalStatement.Conditional("switch","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(switchBody),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))));
					}
					currentPositionIndex+=1;
					if(element.get(com.rem.gen.parser.Token.Id._newName)!=null) {
						final String newTokenName = MainFlow.camelize((element.get(com.rem.gen.parser.Token.Id._newName)).toString());
						if(createdPlainTokens.add(newTokenName)) {
							ExternalClassEntry.classMap.get("Token").getSubClass("Id").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((element.get(com.rem.gen.parser.Token.Id._newName)).toString()),""))),null));
							ExternalClassEntry.classMap.get("Tokens").getSubClass("Plain").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
								null,
								new ExternalStatement("."),
								new Entry(){public void get(StringBuilder builder){}},
								new VariableNameEntry(newTokenName),
								"class ",
								new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
								Arrays.asList(new Entry[]{}),
								null);
							setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("value"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getValue");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("value"),"")))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setValue");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newValue");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("value = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newValue"),"")))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),null,new ExternalStatement(new StringEntry("getName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((element.get(com.rem.gen.parser.Token.Id._newName)).toString()),""))))))));}});
						}
						if(element.get(com.rem.gen.parser.Token.Id._listName)!=null) {
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Plain"),""),new ExternalStatement(new VariableNameEntry((newTokenName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element.get(com.rem.gen.parser.Token.Id._listName)).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))))));
							if(listNamesInRule.containsKey(parentRuleName)==false) {
								listNamesInRule.put(parentRuleName,new HashSet<String>());
							}
							listNamesInRule.get(parentRuleName).add((element.get(com.rem.gen.parser.Token.Id._listName)).toString());
							handleListAdditions.put(ruleName,parentRuleName);
							if(handleListAdditionAftBodies.containsKey(ruleName)==false) {
								handleListAdditionAftBodies.put(ruleName,new ExternalStatement.Body());
							}
						}
						else {
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Plain"),""),new ExternalStatement(new VariableNameEntry((newTokenName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))))))))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))))));
						}
						if(importRuleNames!=null&&importRuleNames.contains(newTokenName)) {
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_import_"),""),new ExternalStatement(new VariableNameEntry((newTokenName).toString()),""),new ExternalStatement(new StringEntry("_value"),""))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLastValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
						}
					}
					else {
						rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))))));
					}
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((regexValue.toString().replace("\\","\\\\").replace("\"","\\\"").replace("\'","\\\'")).toString()),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((currentPositionValue).toString()),"")))))))));
					break;
				}
				case _ruleToken: {
					if(MainFlow.self.listNames.contains((query).toString())) {
						final String listName = (query).toString();
						final String newTokenClassName;
						final String newTokenName;
						if(element.get(com.rem.gen.parser.Token.Id._newName)!=null) {
							newTokenClassName=MainFlow.camelize((element.get(com.rem.gen.parser.Token.Id._newName)).toString())+"Token";
							newTokenName=(query).toString();
						}
						else {
							newTokenClassName=MainFlow.camelize((query).toString())+"Token";
							newTokenName=(query).toString();
						}
						declareNamedToken(newTokenClassName,newTokenName);
						final ExternalStatement.Body listRuleBody;
						if(listFirstPassRules.containsKey((listName).toString())) {
							final String firstListPassTokenName = listFirstPassRules.get(listName);
							final String passRuleName = MainFlow.camelize((firstListPassTokenName).toString())+"Token";
							listRuleBody=new ExternalStatement.Body();
							declareNamedToken(passRuleName,listFirstPassRules.get(listName));
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_result"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_preparsed_"),""),new ExternalStatement(new VariableNameEntry((listFirstPassRules.get(listName)).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("_first_pass_token"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(new VariableNameEntry((passRuleName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_first_pass_token"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))))))),new ExternalStatement.Conditional("else if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(listRuleBody),""))))));
						}
						else {
							listRuleBody=rule;
						}
						final String isNameableCharacter = "_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )";
						listRuleBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_preparsed_"),""),new ExternalStatement(new VariableNameEntry((listFirstPassRules.get(listName)).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,"&&",Arrays.asList(new String[]{"","!=","&&"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("contains"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),""))))))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,"<",Arrays.asList(new String[]{"","+","<"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_next_char"),""),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray"),"")),new ExternalStatement.ArrayParameters(new ExternalStatement.Parameters("][",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((isNameableCharacter).toString()),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(new VariableNameEntry((newTokenClassName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_list_name_result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("while","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.ruleIgnoresHeaders.get(ruleName)),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Fail"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((listName).toString()),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_furthestPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))))))));
					}
					else {
						final String subRuleName = (query).toString();
						Boolean protectAgainstRecursion = false;
						final String exceptionMessage = " : rule not found!";
						if(MainFlow.self.ruleHeirachy.containsKey(subRuleName)==false) {
							throw new RuntimeException(""+subRuleName+exceptionMessage+"");
						}
						if(isFirst) {
							protectAgainstRecursion=MainFlow.self.ruleHeirachy.get(subRuleName).contains(parentRuleName);
						}
						if(element.get(com.rem.gen.parser.Token.Id._newName)!=null&&element.get(com.rem.gen.parser.Token.Id._listName)==null) {
							final String newTokenName = (element.get(com.rem.gen.parser.Token.Id._newName)).toString();
							final String newTokenClassName = MainFlow.camelize((newTokenName).toString())+"Token";
							declareNamedToken(newTokenClassName,newTokenName);
							declareSubRuleTokenInRule(ruleName,subRuleName,rule);
							rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(new VariableNameEntry((newTokenClassName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
							declarePosition(rule,ruleName,subRuleName);
						}
						else if(protectAgainstRecursion) {
							declarePosition(rule,ruleName,subRuleName);
						}
						if(protectAgainstRecursion) {
							final Integer recursionIndexValue = recursionIndex;
							recursionIndex+=1;
							final String recursionVariableName = "_recursion_protection_"+(subRuleName).toString()+"_"+(recursionIndexValue).toString();
							ExternalClassEntry.classMap.get("Parser").getSubClass("Context").addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((recursionVariableName).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashSet"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,"&&!",Arrays.asList(new String[]{"","==","&&!"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((recursionVariableName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("contains"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((recursionVariableName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((recursionVariableName).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("remove"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
						}
						else {
							rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("parse_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))));
						}
						if(element.get(com.rem.gen.parser.Token.Id._newName)!=null&&element.get(com.rem.gen.parser.Token.Id._listName)==null) {
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_token_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))))))))));
							if(importRuleNames!=null&&importRuleNames.contains((element.get(com.rem.gen.parser.Token.Id._newName)).toString())) {
								rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_import_"),""),new ExternalStatement(new VariableNameEntry((element.get(com.rem.gen.parser.Token.Id._newName)).toString()),""),new ExternalStatement(new StringEntry("_value"),""))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
							}
							rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_token_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),"")))))));
						}
						else if(importRuleNames!=null&&importRuleNames.contains(subRuleName)) {
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_import_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""),new ExternalStatement(new StringEntry("_value"),""))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLastValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
						}
						if(element.get(com.rem.gen.parser.Token.Id._listName)!=null) {
							final String listTokenName;
							if(element.get(com.rem.gen.parser.Token.Id._newName)!=null) {
								listTokenName=MainFlow.camelize((element.get(com.rem.gen.parser.Token.Id._newName)).toString())+"Token";
								final String simpleTokenName = (element.get(com.rem.gen.parser.Token.Id._newName)).toString();
								declareNamedToken(listTokenName,simpleTokenName);
							}
							else {
								listTokenName=MainFlow.camelize((element.get(com.rem.gen.parser.Token.Id._listName)).toString())+"Token";
							}
							rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_value"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLastValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((element.get(com.rem.gen.parser.Token.Id._listName)).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Tokens"),""),new ExternalStatement(new StringEntry("Name"),""),new ExternalStatement(new VariableNameEntry((listTokenName).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_value"),"")))))))))))))));
							if(listNamesInRule.containsKey(parentRuleName)==false) {
								listNamesInRule.put(parentRuleName,new HashSet<String>());
							}
							listNamesInRule.get(parentRuleName).add((element.get(com.rem.gen.parser.Token.Id._listName)).toString());
							handleListAdditions.put(ruleName,parentRuleName);
							if(handleListAdditionAftBodies.containsKey(ruleName)==false) {
								handleListAdditionAftBodies.put(ruleName,new ExternalStatement.Body());
							}
						}
					}
					break;
				}
			}
		}
	}
	public String addRegexElementToRule(final com.rem.gen.parser.Token element,final ExternalStatement.Body rule,final String positionName) {
		final String groupSuccessfulPositionName = "_position_of_last_success_"+groupSuccessfulPositionIndex;
		groupSuccessfulPositionIndex+=1;
		if(element.get(com.rem.gen.parser.Token.Id._option)!=null) {
			final StringBuilder regexValue = new StringBuilder();
			regexValue.append("[");
			final ExternalStatement option = new ExternalStatement();
			if(element.get(com.rem.gen.parser.Token.Id._option).get(com.rem.gen.parser.Token.Id._negate)==null) {
				option.set("||");
				final com.rem.gen.parser.Token optionToken = element.get(com.rem.gen.parser.Token.Id._option);
				for (final com.rem.gen.parser.Token atom: optionToken.getAll()) {
					switch (atom.getName()) {
						case _range: {
							final Character ch = (atom.get(com.rem.gen.parser.Token.Id._left)).toString().charAt(0);
							final Integer end = (atom.get(com.rem.gen.parser.Token.Id._right)).toString().charAt(0)-ch;
							for(Integer i = 0;i<=end;i++) {
								final String chValue = "'"+((char)(ch+i))+"'";
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
							}
							regexValue.append((atom.get(com.rem.gen.parser.Token.Id._left)).toString());
							regexValue.append("-");
							regexValue.append((atom.get(com.rem.gen.parser.Token.Id._right)).toString());
							break;
						}
						case _regex_special: {
							for (final com.rem.gen.parser.Token quark: atom.getAll()) {
								switch (quark.getName()) {
									case _number: {
										final Character ch = "0".charAt(0);
										final Integer end = "9".charAt(0)-ch;
										for(Integer i = 0;i<=end;i++) {
											final String chValue = "'"+(char)(ch+i)+"'";
											option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
										}
										regexValue.append("\\\\d");
										break;
									}
									case _whitespace: {
										option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),""))));
										option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\t'"),""))));
										option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),""))));
										option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),""))));
										regexValue.append("\\\\s");
										break;
									}
									case _quote: {
										option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'"),new StringEntry("'"),"",new ExternalStatement(new StringEntry("\\"),""),new ExternalStatement(new StringEntry("\""),"")))));
										regexValue.append("\\\"");
										break;
									}
									case _escaped: {
										option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((quark).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
										regexValue.append("\\"+(quark).toString());
										break;
									}
									case _dot: {
										option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'.'"),""))));
										regexValue.append("\\.");
										break;
									}
									case _slash: {
										option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\\\'"),""))));
										regexValue.append("\\\\");
										break;
									}
								}
							}
							break;
						}
						case _standAlone: {
							final String ch;
							final String quoteChar = (atom).toString();
							if(quoteChar.equals("\\")) {
								ch="\\\\";
							}
							else if(quoteChar.equals("\"")) {
								ch="\\\"";
							}
							else {
								ch=(atom).toString();
							}
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((ch).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
							regexValue.append((atom).toString());
							break;
						}
					}
				}
			}
			else {
				option.set("&&");
				final com.rem.gen.parser.Token optionToken = element.get(com.rem.gen.parser.Token.Id._option);
				for (final com.rem.gen.parser.Token atom: optionToken.getAll()) {
					switch (atom.getName()) {
						case _range: {
							final Character ch = (atom.get(com.rem.gen.parser.Token.Id._left)).toString().charAt(0);
							final Integer end = (atom.get(com.rem.gen.parser.Token.Id._right)).toString().charAt(0)-ch;
							for(Integer i = 0;i<=end;i++) {
								final String chValue = "'"+(char)(ch+i)+"'";
								option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
							}
							regexValue.append((atom.get(com.rem.gen.parser.Token.Id._left)).toString());
							regexValue.append("-");
							regexValue.append((atom.get(com.rem.gen.parser.Token.Id._right)).toString());
							break;
						}
						case _regex_special: {
							for (final com.rem.gen.parser.Token quark: atom.getAll()) {
								switch (quark.getName()) {
									case _number: {
										final Character ch = "0".charAt(0);
										final Integer end = "9".charAt(0)-ch;
										for(Integer i = 0;i<=end;i++) {
											final String chValue = "'"+(char)(ch+i)+"'";
											option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
										}
										regexValue.append("\\\\d");
										break;
									}
									case _whitespace: {
										option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),""))));
										option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\t'"),""))));
										option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),""))));
										option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),""))));
										regexValue.append("\\\\s");
										break;
									}
									case _escaped: {
										option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((quark).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
										regexValue.append("\\"+(quark).toString());
										break;
									}
									case _dot: {
										option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'.'"),""))));
										regexValue.append("\\.");
										break;
									}
									case _slash: {
										option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\\\'"),""))));
										regexValue.append("\\\\");
										break;
									}
								}
							}
							break;
						}
						case _standAlone: {
							final String ch;
							final String quoteChar = (atom).toString();
							if(quoteChar.equals("\\")) {
								ch="\\\\";
							}
							else if(quoteChar.equals("\"")) {
								ch="\\\"";
							}
							else if(quoteChar.equals("\'")) {
								ch="\\\'";
							}
							else {
								ch=(atom).toString();
							}
							option.add(new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((ch).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
							regexValue.append((atom).toString());
							break;
						}
					}
				}
			}
			if(element.get(com.rem.gen.parser.Token.Id._multiple)!=null) {
				final String multipleValueName = "_multiple_index_"+multipleIndex;
				multipleIndex+=1;
				if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._OPTIONAL)!=null) {
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))))))));
				}
				else if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._MANY)!=null) {
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("while","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))))))))));
				}
				else if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._PLUS)!=null) {
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement.Conditional("while","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),""))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),""))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),""))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
				}
			}
			else {
				rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
			}
			regexValue.append("]");
			if(element.get(com.rem.gen.parser.Token.Id._multiple)!=null) {
				if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._OPTIONAL)!=null) {
					regexValue.append("?");
				}
				else if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._MANY)!=null) {
					regexValue.append("*");
				}
				else if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._PLUS)!=null) {
					regexValue.append("+");
				}
			}
			return (regexValue).toString();
		}
		else {
			final StringBuilder regexValue = new StringBuilder();
			final ExternalStatement.Body regexBody;
			final String multipleValueName;
			final String stateName;
			if(element.get(com.rem.gen.parser.Token.Id._multiple)!=null) {
				multipleValueName="_multiple_index_"+multipleIndex;
				stateName="_state_"+multipleIndex;
				multipleIndex+=1;
				final String multipleValueLimit;
				if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._OPTIONAL)!=null) {
					multipleValueLimit="1";
				}
				else {
					multipleValueLimit="Integer.MAX_VALUE";
				}
				regexBody=new ExternalStatement.Body();
				if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._PLUS)!=null) {
					rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")))),new ExternalStatement.Conditional("while","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(regexBody),""))))));
				}
				else {
					if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._OPTIONAL)!=null) {
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")))),new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(regexBody),""))))));
					}
					else if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._MANY)!=null) {
						rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")))),new ExternalStatement.Conditional("while","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(regexBody),""))))));
					}
				}
			}
			else {
				regexBody=rule;
				multipleValueName=null;
				stateName=null;
			}
			if(element.get(com.rem.gen.parser.Token.Id._regex_special)!=null) {
				final ExternalStatement option = new ExternalStatement();
				option.set("||");
				for (final com.rem.gen.parser.Token quark: element.get(com.rem.gen.parser.Token.Id._regex_special).getAll()) {
					switch (quark.getName()) {
						case _number: {
							final Character ch = "0".charAt(0);
							final Integer end = "9".charAt(0)-ch;
							for(Integer i = 0;i<=end;i++) {
								final String chValue = "'"+(char)(ch+i)+"'";
								option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((chValue).toString()),""))));
							}
							regexValue.append("\\\\d");
							break;
						}
						case _whitespace: {
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),""))));
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\t'"),""))));
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),""))));
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),""))));
							regexValue.append("\\\\s");
							break;
						}
						case _escaped: {
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((quark).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
							regexValue.append("\\"+(quark).toString());
							break;
						}
						case _dot: {
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'.'"),""))));
							regexValue.append("\\.");
							break;
						}
						case _slash: {
							option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\\\'"),""))));
							regexValue.append("\\\\");
							break;
						}
					}
				}
				regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
			}
			if(element.get(com.rem.gen.parser.Token.Id._character)!=null) {
				if((element.get(com.rem.gen.parser.Token.Id._character)).toString().equals(".")) {
					regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
				}
				else {
					regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputLength"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputArray[_position]"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((element.get(com.rem.gen.parser.Token.Id._character)).toString()),""),new ExternalStatement(new StringEntry("'"),"")))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("++_position"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
				}
				regexValue.append((element.get(com.rem.gen.parser.Token.Id._character)).toString());
			}
			else if(element.get(com.rem.gen.parser.Token.Id._group)!=null) {
				final String op = "(";
				final String cp = ")";
				regexValue.append(op);
				final com.rem.gen.parser.Token regexToken = element.get(com.rem.gen.parser.Token.Id._group).get(com.rem.gen.parser.Token.Id._regex);
				regexBody.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
				for (final com.rem.gen.parser.Token regexElement: regexToken.getAllSafely(com.rem.gen.parser.Token.Id._regex_element)) {
					regexValue.append(addRegexElementToRule(regexElement,regexBody,positionName));
				}
			}
			if(element.get(com.rem.gen.parser.Token.Id._multiple)!=null) {
				if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._PLUS)!=null) {
					if(element.get(com.rem.gen.parser.Token.Id._group)!=null) {
						regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))));
					}
					else {
						regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")))))))));
					}
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,">",Arrays.asList(new String[]{"","==","&&",">"}),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")))))))));
					regexValue.append("+");
				}
				else {
					if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._OPTIONAL)!=null) {
						regexValue.append("?");
						if(element.get(com.rem.gen.parser.Token.Id._group)!=null) {
							regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")))))))));
						}
					}
					else if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._MANY)!=null) {
						if(element.get(com.rem.gen.parser.Token.Id._group)!=null) {
							regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupSuccessfulPositionName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),"")))))))));
						}
						else {
							regexBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("++"),""),new ExternalStatement(new VariableNameEntry((multipleValueName).toString()),"")))))))));
						}
						regexValue.append("*");
					}
					rule.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((stateName).toString()),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")))))))));
				}
			}
			return (regexValue).toString();
		}
	}
	public ExternalStatement.Body getRegexForPreparer(final com.rem.gen.parser.Token element,final String ruleName,final ExternalClassEntry preparerClass,final Integer preparerState) {
		final Integer nextPreparerState = preparerState+1;
		final ExternalStatement.Body resultBody = new ExternalStatement.Body();
		final ExternalStatement.Body regex = new ExternalStatement.Body();
		resultBody.add(new ExternalStatement.Body(new ExternalStatement.Conditional("case ","",new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((preparerState).toString()),"")),new ExternalStatement(new StringEntry(":"),"")),"",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(regex),""))))));
		final ExternalStatement option = new ExternalStatement();
		com.rem.gen.parser.Token optionToken = null;
		String lowerBoundOperator = ">=";
		String upperBoundOperator = "<=";
		String unionOperator = "&&";
		String equalityOperator = "==";
		option.set("||");
		if(element.get(com.rem.gen.parser.Token.Id._option)!=null) {
			if(element.get(com.rem.gen.parser.Token.Id._option).get(com.rem.gen.parser.Token.Id._negate)!=null) {
				lowerBoundOperator="<=";
				upperBoundOperator=">=";
				unionOperator="||";
				equalityOperator="!=";
				option.set("&&");
			}
			optionToken=element.get(com.rem.gen.parser.Token.Id._option);
		}
		else if(element.get(com.rem.gen.parser.Token.Id._regex_special)!=null) {
			optionToken=element.get(com.rem.gen.parser.Token.Id._regex_special);
		}
		else if(element.get(com.rem.gen.parser.Token.Id._character)!=null) {
			optionToken=element;
		}
		for (final com.rem.gen.parser.Token atom: optionToken.getAll()) {
			switch (atom.getName()) {
				case _range: {
					final Character begin = (atom.get(com.rem.gen.parser.Token.Id._left)).toString().charAt(0);
					final Character end = (atom.get(com.rem.gen.parser.Token.Id._right)).toString().charAt(0);
					final ExternalStatement rangeStatement = new ExternalStatement(unionOperator);
					final ExternalStatement leftSideStatement = new ExternalStatement(lowerBoundOperator);
					leftSideStatement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
					leftSideStatement.add(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("\'"),""),new ExternalStatement(new VariableNameEntry((begin).toString()),""),new ExternalStatement(new StringEntry("\'"),""))));
					final ExternalStatement rightSideStatement = new ExternalStatement(upperBoundOperator);
					rightSideStatement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
					rightSideStatement.add(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("\'"),""),new ExternalStatement(new VariableNameEntry((end).toString()),""),new ExternalStatement(new StringEntry("\'"),""))));
					rangeStatement.add(leftSideStatement);
					rangeStatement.add(rightSideStatement);
					option.add(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(rangeStatement),""))));
					break;
				}
				case _regex_special: {
					for (final com.rem.gen.parser.Token quark: atom.getAll()) {
						switch (quark.getName()) {
							case _number: {
								final Character begin = "0".charAt(0);
								final Character end = "9".charAt(0);
								final ExternalStatement rangeStatement = new ExternalStatement(unionOperator);
								final ExternalStatement leftSideStatement = new ExternalStatement(lowerBoundOperator);
								leftSideStatement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								leftSideStatement.add(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("\'"),""),new ExternalStatement(new VariableNameEntry((begin).toString()),""),new ExternalStatement(new StringEntry("\'"),""))));
								final ExternalStatement rightSideStatement = new ExternalStatement(upperBoundOperator);
								rightSideStatement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								rightSideStatement.add(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("\'"),""),new ExternalStatement(new VariableNameEntry((end).toString()),""),new ExternalStatement(new StringEntry("\'"),""))));
								rangeStatement.add(leftSideStatement);
								rangeStatement.add(rightSideStatement);
								option.add(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(rangeStatement),""))));
								break;
							}
							case _whitespace: {
								final ExternalStatement whitespaceStatement = new ExternalStatement(unionOperator);
								ExternalStatement statement = new ExternalStatement(equalityOperator);
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("' '"),"")));
								whitespaceStatement.add(statement);
								statement=new ExternalStatement(equalityOperator);
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\t'"),"")));
								whitespaceStatement.add(statement);
								statement=new ExternalStatement(equalityOperator);
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\r'"),"")));
								whitespaceStatement.add(statement);
								statement=new ExternalStatement(equalityOperator);
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),"")));
								whitespaceStatement.add(statement);
								option.add(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(whitespaceStatement),""))));
								break;
							}
							case _escaped: {
								final ExternalStatement statement = new ExternalStatement(equalityOperator);
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								statement.add(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'\\"),""),new ExternalStatement(new VariableNameEntry((quark).toString()),""),new ExternalStatement(new StringEntry("'"),""))));
								option.add(statement);
								break;
							}
							case _dot: {
								final ExternalStatement statement = new ExternalStatement(equalityOperator);
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("'.'"),"")));
								option.add(statement);
								break;
							}
							case _slash: {
								final ExternalStatement statement = new ExternalStatement(equalityOperator);
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
								statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\\\'"),"")));
								option.add(statement);
								break;
							}
						}
					}
					break;
				}
				case _standAlone: {
					final String ch;
					final String quoteChar = (atom).toString();
					if(quoteChar.equals("\\")) {
						ch="\\\\";
					}
					else if(quoteChar.equals("\"")) {
						ch="\\\"";
					}
					else {
						ch=(atom).toString();
					}
					final ExternalStatement statement = new ExternalStatement(equalityOperator);
					statement.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")));
					statement.add(new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((ch).toString()),""),new ExternalStatement(new StringEntry("'"),""))));
					option.add(statement);
					break;
				}
				case _character: {
					if((atom).toString().equals(".")) {
						option.add(new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")));
					}
					else {
						option.add(new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("'"),""),new ExternalStatement(new VariableNameEntry((atom).toString()),""),new ExternalStatement(new StringEntry("'"),"")))));
					}
					break;
				}
			}
		}
		if(element.get(com.rem.gen.parser.Token.Id._group)!=null) {
			final String groupClass = "Group"+preparerState;
			final String groupMember = "group_"+preparerState;
			preparerClass.addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(groupClass),
				"class ",
				new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Builder"),""))),
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);;__add_method_0__();}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("can"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("char"),"")),"",new ExternalStatement(new StringEntry("nextChar"),""),null)}),null,new ExternalStatement.Body()));}});
			preparerClass.addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(preparerClass.getFullName()),""),new ExternalStatement(new VariableNameEntry((groupClass).toString()),""))),"",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupMember).toString()),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(preparerClass.getFullName()),""),new ExternalStatement(new VariableNameEntry((groupClass).toString()),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
			option.add(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((groupMember).toString()),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")))))));
			int currentStateOfPreparer = 0;
			final ExternalClassEntry nextPreparerClass = preparerClass.getSubClass(""+groupClass.toString()+"");
			final ExternalStatement.Body switchBody = new ExternalStatement.Body();
			for (final com.rem.gen.parser.Token regexElement: element.get(com.rem.gen.parser.Token.Id._group).get(com.rem.gen.parser.Token.Id._regex).getAllSafely(com.rem.gen.parser.Token.Id._regex_element)) {
				switchBody.add(getRegexForPreparer(regexElement,ruleName,nextPreparerClass,currentStateOfPreparer));
				currentStateOfPreparer+=1;
			}
			nextPreparerClass.getMethod("can").appendToBody(new ExternalStatement.Body(new ExternalStatement.Conditional("switch","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(switchBody),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))));
		}
		if(element.get(com.rem.gen.parser.Token.Id._multiple)==null) {
			regex.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nextPreparerState).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))))))));
		}
		else {
			if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._OPTIONAL)!=null) {
				regex.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nextPreparerState).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nextPreparerState).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("can"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")))))))))));
			}
			else if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._MANY)!=null) {
				regex.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nextPreparerState).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("can"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),"")))))))))));
			}
			else if(element.get(com.rem.gen.parser.Token.Id._multiple).get(com.rem.gen.parser.Token.Id._PLUS)!=null) {
				regex.add(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(option),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("multiple_satisfied"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("multiple_satisfied"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new VariableNameEntry((nextPreparerState).toString()),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("multiple_satisfied"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("can"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("nextChar"),""))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("multiple_satisfied"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))))))))));
			}
		}
		return resultBody;
	}
	public void declarePosition(final ExternalStatement.Body rule,final String ruleName,final String subRuleName) {
		if(declaredPositions.containsKey(ruleName)==false) {
			declaredPositions.put(ruleName,new HashSet<String>());
		}
		declaredPositions.get(ruleName).add(subRuleName);
		rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_position_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))));
	}
	public void declareSubRuleTokenInRule(final String ruleName,final String subRuleName,final ExternalStatement.Body rule) {
		if(subRuleTokenDeclarations.containsKey(ruleName)==false) {
			subRuleTokenDeclarations.put(ruleName,new HashSet<String>());
		}
		subRuleTokenDeclarations.get(ruleName).add(subRuleName);
		rule.add(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_token_"),""),new ExternalStatement(new VariableNameEntry((subRuleName).toString()),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("_token"),""))))));
	}
	public void declareNamedToken(final String newTokenName,final String simpleTokenName) {
		if(createdNameTokens.add(newTokenName)) {
			ExternalClassEntry.classMap.get("Token").getSubClass("Id").addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),"",new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((simpleTokenName).toString()),""))),null));
			ExternalClassEntry.classMap.get("Tokens").getSubClass("Name").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(newTokenName),
				"class ",
				new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);;__add_variable_0__();;__add_method_0__();;__add_method_1__();;__add_method_2__();}
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("value"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setValue"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("newValue"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("newValue"),"")))))));}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),null,new ExternalStatement(new StringEntry("getName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(".",new ExternalStatement(new ExternalStatement(new StringEntry("_"),""),new ExternalStatement(new VariableNameEntry((simpleTokenName).toString()),""))))))));}});
		}
	}
	public void __add_variable_0__() {
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("SUCCESS"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
	}
	public void __add_variable_1__() {
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("FAILED"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))));
	}
	public void __add_variable_2__() {
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("FIRST_PASS"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
	}
	public void __add_variable_3__() {
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("SECOND_PASS"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))));
	}
	public void __add_variable_4__() {
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"),"",new ExternalStatement(new StringEntry("fileNames"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashSet"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
	}
	public void __add_variable_5__() {
		addVariable(new ExternalVariableEntry(false,true,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),"")))),">"),"",new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),"")))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
	}
	public void __add_method_0__() {
		addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Parser");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
	}
	public void __add_method_1__() {
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setSUCCESS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newSUCCESS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("SUCCESS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newSUCCESS"),"")))));
	}
	public void __add_method_2__() {
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFAILED");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newFAILED");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("FAILED = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFAILED"),"")))));
	}
	public void __add_method_3__() {
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFIRSTPASS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newFIRSTPASS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("FIRST_PASS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFIRSTPASS"),"")))));
	}
	public void __add_method_4__() {
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setSECONDPASS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newSECONDPASS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("SECOND_PASS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newSECONDPASS"),"")))));
	}
	public void __add_method_5__() {
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFileNames");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("newFileNames");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("fileNames = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFileNames"),"")))));
	}
	public void __add_method_6__() {
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setContexts");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),"")))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("newContexts");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("contexts = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newContexts"),"")))));
	}
	public void __add_method_7__() {
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),null,new ExternalStatement(new StringEntry("parse"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("fileName"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new ExternalStatement(new StringEntry("startParseTime"),""),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("currentTimeMillis"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(new StringEntry("firstResult"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseFile"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("firstResult"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getState"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("First-Pass Successful"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileNames"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("clear"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(new StringEntry("secondResult"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseFile"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),""))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("secondResult"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getState"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Second-Pass Successful"),"")))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Second-Pass Failed"),"")))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("secondResult"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setParseTime"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("currentTimeMillis"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("startParseTime"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("secondResult"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("First-Pass Failed"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("firstResult"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setParseTime"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("currentTimeMillis"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("startParseTime"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("firstResult"),"")))))))));
	}
	public void __add_method_8__() {
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),null,new ExternalStatement(new StringEntry("parseFile"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_pass"),""),null)}),null,new ExternalStatement.Body()));
	}
	public void __add_method_9__() {
		addMethod(new ExternalMethodEntry(0,true,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("readLine"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("input"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("position"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("indexOfLine"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("indexOf"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("'\\n'"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfLine"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfLine"),"")))))))))))));
	}
	public void __add_sub_class_0__() {
		addSubClass(Context._);
	}
	public void __add_sub_class_1__() {
		addSubClass(NameList._);
	}
	public void __add_sub_class_2__() {
		addSubClass(Result._);
	}
	public void __INIT__() {
		super.__SETUP__(
			null,
			new ExternalStatement(".",
					new ExternalStatement(".",
							new ExternalStatement(
									new VariableNameEntry(MainFlow.self.packageName),"")),
					new ExternalStatement(new StringEntry("parser"),"")),
			new Entry(){public void get(StringBuilder builder){}},
			new VariableNameEntry("Parser"),
			"class ",
			null,
			Arrays.asList(new Entry[]{
					new StringEntry("com.rem.output.helpers.OutputHelper.Parser<Parser.Result,Parser.Result.Pass>")
			}),
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
		__add_method_9__();
		__add_sub_class_0__();
		__add_sub_class_1__();
		__add_sub_class_2__();

		ExternalMethodEntry asPassMethod = new ExternalMethodEntry(
				0,false,
				new ExternalStatement.TypeName(new StringEntry("Parser.Result.Pass")),"",
				new Entry(){public void get(StringBuilder builder){
					builder.append("asPass");}},
				Arrays.asList(
						new ExternalVariableEntry[]{
								new ExternalVariableEntry(true,false,false,
										new ExternalStatement.TypeName(
												new ExternalStatement(new StringEntry("Parser.Result"),"")),"",
										new Entry(){public void get(StringBuilder builder){builder.append("result");}},
										null)}),"",new ExternalStatement.Body(
												new ExternalStatement(
														new TabEntry(
																new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("(result instanceof Parser.Result.Pass)?((Parser.Result.Pass)result):null"),""))));
		addMethod(asPassMethod);
	}
	public static class Context extends ExternalClassEntry{
		public static final Context _ = new Context();
		public Context() {
			super();
		}
		public void __add_variable_0__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("SUCCESS"),""))));
		}
		public void __add_variable_1__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("FAILED"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("FAILED"),""))));
		}
		public void __add_variable_2__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("FIRST_PASS"),""))));
		}
		public void __add_variable_3__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("SECOND_PASS"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("SECOND_PASS"),""))));
		}
		public void __add_variable_4__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("FIRST_PASS"),""))));
		}
		public void __add_variable_5__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_position"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
		}
		public void __add_variable_6__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_inputLength"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_variable_7__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_state"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("SUCCESS"),""))));
		}
		public void __add_variable_8__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_furthestPosition"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_variable_9__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_lineNumber"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))));
		}
		public void __add_variable_10__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_input"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_11__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_directoryName"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_12__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_fileName"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_13__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new VariableNameEntry((MainFlow.self.charArray).toString()),"")),"",new ExternalStatement(new StringEntry("_inputArray"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_14__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(new StringEntry("_result"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_15__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Acceptor"),""))),"",new ExternalStatement(new StringEntry("_result_acceptor"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Acceptor"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_16__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Boolean"),"")),"",new ExternalStatement(new StringEntry("_succeedOnEnd"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),""))));
		}
		public void __add_variable_17__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("_list_name_result"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_18__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(new StringEntry("_lineNumberRanges"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_19__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("_root"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Id"),""))),new ExternalStatement(new StringEntry("ROOT"),"")))))));
		}
		public void __add_variable_20__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("_token"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("_root"),""))));
		}
		public void __add_variable_21__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ImportThread"),""))),">"),"",new ExternalStatement(new StringEntry("_import_threads"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashSet"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ImportThread"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_method_0__() {
			addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Context");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
		}
		public void __add_method_1__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getSUCCESS");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("SUCCESS"),"")))));
		}
		public void __add_method_2__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setSUCCESS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newSUCCESS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("SUCCESS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newSUCCESS"),"")))));
		}
		public void __add_method_3__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getFAILED");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("FAILED"),"")))));
		}
		public void __add_method_4__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFAILED");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newFAILED");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("FAILED = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFAILED"),"")))));
		}
		public void __add_method_5__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getFIRSTPASS");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("FIRST_PASS"),"")))));
		}
		public void __add_method_6__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFIRSTPASS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newFIRSTPASS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("FIRST_PASS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFIRSTPASS"),"")))));
		}
		public void __add_method_7__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getSECONDPASS");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("SECOND_PASS"),"")))));
		}
		public void __add_method_8__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setSECONDPASS");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newSECONDPASS");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("SECOND_PASS = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newSECONDPASS"),"")))));
		}
		public void __add_method_9__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_pass");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_pass"),"")))));
		}
		public void __add_method_10__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_pass");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_pass");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_pass = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_pass"),"")))));
		}
		public void __add_method_11__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_position");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_position"),"")))));
		}
		public void __add_method_12__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_position");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_position");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_position = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_position"),"")))));
		}
		public void __add_method_13__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_inputLength");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_inputLength"),"")))));
		}
		public void __add_method_14__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_inputLength");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_inputLength");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_inputLength = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_inputLength"),"")))));
		}
		public void __add_method_15__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_state");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_state"),"")))));
		}
		public void __add_method_16__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_state");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_state");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_state = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_state"),"")))));
		}
		public void __add_method_17__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_furthestPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_furthestPosition"),"")))));
		}
		public void __add_method_18__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_furthestPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_furthestPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_furthestPosition = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_furthestPosition"),"")))));
		}
		public void __add_method_19__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_lineNumber");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_lineNumber"),"")))));
		}
		public void __add_method_20__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_lineNumber");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_lineNumber");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_lineNumber = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_lineNumber"),"")))));
		}
		public void __add_method_21__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_input");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_input"),"")))));
		}
		public void __add_method_22__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_input");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_input");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_input = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_input"),"")))));
		}
		public void __add_method_23__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_directoryName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_directoryName"),"")))));
		}
		public void __add_method_24__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_directoryName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_directoryName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_directoryName = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_directoryName"),"")))));
		}
		public void __add_method_25__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_fileName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_fileName"),"")))));
		}
		public void __add_method_26__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_fileName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_fileName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_fileName = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_fileName"),"")))));
		}
		public void __add_method_27__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new VariableNameEntry((MainFlow.self.charArray).toString()),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_inputArray");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_inputArray"),"")))));
		}
		public void __add_method_28__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_inputArray");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new VariableNameEntry((MainFlow.self.charArray).toString()),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_inputArray");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_inputArray = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_inputArray"),"")))));
		}
		public void __add_method_29__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_result");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_result"),"")))));
		}
		public void __add_method_30__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_result");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_result");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_result = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_result"),"")))));
		}
		public void __add_method_31__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Acceptor"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_resultAcceptor");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_result_acceptor"),"")))));
		}
		public void __add_method_32__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_resultAcceptor");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Acceptor"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_resultAcceptor");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_result_acceptor = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_resultAcceptor"),"")))));
		}
		public void __add_method_33__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Boolean"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_succeedOnEnd");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_succeedOnEnd"),"")))));
		}
		public void __add_method_34__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_succeedOnEnd");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Boolean"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_succeedOnEnd");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_succeedOnEnd = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_succeedOnEnd"),"")))));
		}
		public void __add_method_35__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("get_listNameResult");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_list_name_result"),"")))));
		}
		public void __add_method_36__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_listNameResult");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("new_listNameResult");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_list_name_result = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_listNameResult"),"")))));
		}
		public void __add_method_37__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("get_lineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")))));
		}
		public void __add_method_38__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_lineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("new_lineNumberRanges");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_lineNumberRanges = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_lineNumberRanges"),"")))));
		}
		public void __add_method_39__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_root");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_root"),"")))));
		}
		public void __add_method_40__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_root");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_root");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_root = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_root"),"")))));
		}
		public void __add_method_41__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("get_token");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_token"),"")))));
		}
		public void __add_method_42__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_token");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("new_token");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_token = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_token"),"")))));
		}
		public void __add_method_43__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ImportThread"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("get_importThreads");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_import_threads"),"")))));
		}
		public void __add_method_44__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_importThreads");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Set"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ImportThread"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("new_importThreads");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_import_threads = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_importThreads"),"")))));
		}
		public void __add_method_45__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),null,new ExternalStatement(new StringEntry("parse"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_pass_index"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileNames"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_pass_index"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_directoryName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("./"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("indexOfDirectorySlash"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("lastIndexOf"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("/"),"")))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfDirectorySlash"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfDirectorySlash"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("lastIndexOf"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\\\"),""))))))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement(">",new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfDirectorySlash"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_directoryName"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfDirectorySlash"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("indexOfDirectorySlash"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")),"",new ExternalStatement(new StringEntry("_inputBuffer"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Conditional("try","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("BufferedReader"),"")),"",new ExternalStatement(new StringEntry("_inputReader"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("BufferedReader"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("FileReader"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("_directoryName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("_fileName"),""))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("_readInput"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputReader"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("read"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),"",new ExternalStatement(new StringEntry("escaping"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),"",new ExternalStatement(new StringEntry("quoting"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))),new ExternalStatement.Conditional("while","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("_readInput"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(readInputBody),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("_position"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_inputReader"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("close"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))),new ExternalStatement.Conditional("catch","(",new ExternalStatement(" ",new ExternalStatement(new StringEntry("IOException"),""),new ExternalStatement(new StringEntry("e0"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body())))));
		}
		public void __add_method_46__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("addImportThread"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("importFileName"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_threads"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ImportThread"),"")),"",new ExternalStatement(new StringEntry("thread"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ImportThread"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("importFileName"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("importFileName"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_import_threads"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("thread"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("thread"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("start"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
		}
		public void __add_sub_class_0__() {
			addSubClass(ImportThread._);
		}
		public void __INIT__() {
			super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Context"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			setIsAbstract(true);
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
			__add_variable_16__();
			__add_variable_17__();
			__add_variable_18__();
			__add_variable_19__();
			__add_variable_20__();
			__add_variable_21__();
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
			__add_method_33__();
			__add_method_34__();
			__add_method_35__();
			__add_method_36__();
			__add_method_37__();
			__add_method_38__();
			__add_method_39__();
			__add_method_40__();
			__add_method_41__();
			__add_method_42__();
			__add_method_43__();
			__add_method_44__();
			__add_method_45__();
			__add_method_46__();
			__add_sub_class_0__();
		}
		public static class ImportThread extends ExternalClassEntry{
			public static final ImportThread _ = new ImportThread();
			public ImportThread() {
				super();
			}
			public void __add_variable_0__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new ExternalStatement(new StringEntry("parentContext"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_variable_1__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new ExternalStatement(new StringEntry("context"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_variable_2__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__() {
				addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("ImportThread");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
			}
			public void __add_method_1__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getParentContext");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parentContext"),"")))));
			}
			public void __add_method_2__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParentContext");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newParentContext");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parentContext = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParentContext"),"")))));
			}
			public void __add_method_3__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getContext");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("context"),"")))));
			}
			public void __add_method_4__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setContext");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newContext");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("context = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newContext"),"")))));
			}
			public void __add_method_5__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getFileName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("fileName"),"")))));
			}
			public void __add_method_6__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFileName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newFileName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("fileName = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFileName"),"")))));
			}
			public void __add_method_7__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("run"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(new StringEntry("result"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("parse"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("parentContext"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get_pass"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("parentContext"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get_resultAcceptor"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")))))))))))));
			}
			public void __INIT__() {
				super.__SETUP__(
					null,
					new ExternalStatement("."),
					new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("ImportThread"),
					"class ",
					new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Thread"),"")),
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
			}
		}
	}
	public static class NameList extends ExternalClassEntry{
		public static final NameList _ = new NameList();
		public NameList() {
			super();
		}
		public void __add_variable_0__() {
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),"",new ExternalStatement(new StringEntry("parent"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_1__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),""))),">"),"",new ExternalStatement(new StringEntry("children"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),""))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_2__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Addition"),"")))),">"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("additions"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Addition"),"")))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_3__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("addition_position"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_method_0__() {
			addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("NameList");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
		}
		public void __add_method_1__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getParent");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parent"),"")))));
		}
		public void __add_method_2__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParent");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newParent");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parent = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParent"),"")))));
		}
		public void __add_method_3__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("getChildren");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("children"),"")))));
		}
		public void __add_method_4__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setChildren");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("newChildren");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("children = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newChildren"),"")))));
		}
		public void __add_method_5__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Addition"),"")))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("getAdditions");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("additions"),"")))));
		}
		public void __add_method_6__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setAdditions");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Addition"),"")))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("newAdditions");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("additions = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newAdditions"),"")))));
		}
		public void __add_method_7__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getAdditionPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("addition_position"),"")))));
		}
		public void __add_method_8__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setAdditionPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newAdditionPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("addition_position = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newAdditionPosition"),"")))));
		}
		public void __add_method_9__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),null,new ExternalStatement(new StringEntry("push"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("pass"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),"",new ExternalStatement(new StringEntry("result"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("pass"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("SECOND_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),""))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("pass"),"")),new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("FIRST_PASS"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")))))))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("clear"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))),new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("for","(",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("newEntry"),""),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("safe_add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")))))))))))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))));
		}
		public void __add_method_10__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),null,new ExternalStatement(new StringEntry("push"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),"",new ExternalStatement(new StringEntry("result"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),""))))))),new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("for","(",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("newEntry"),""),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("safe_add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newEntry"),"")))))))))))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))));
		}
		public void __add_method_11__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("NameList"),"")),null,new ExternalStatement(new StringEntry("pop"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("parent"),""))))));
		}
		public void __add_method_12__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("addition"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("addition"),"")))))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("additions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Addition"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("addition_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("addition"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))))))))))));
		}
		public void __add_method_13__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("safe_add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("addition"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("addition"),""))))))))));
		}
		public void __add_method_14__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("start"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("addition_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))));
		}
		public void __add_method_15__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("accept"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("addition_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))));
		}
		public void __add_method_16__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("reject"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("addition_position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("i"),""),new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("additions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))),new ExternalStatement.Conditional("while","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(">=",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("additions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getPosition"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("addition_position"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("remove"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("additions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("remove"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getEntry"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("break"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("--i"),""))))))))))));
		}
		public void __add_method_17__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("extend"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("parent"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("for","(",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("entry"),""),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("parent"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("safe_add"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("entry"),"")))))))))))))));
		}
		public void __add_method_18__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("keep"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("synchronized","(",new ExternalStatement(".",new ExternalStatement(new StringEntry("parent"),"")),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("for","(",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("NameList"),""),new ExternalStatement(new StringEntry("Addition"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("addition"),"")),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("additions"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("parent"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("safe_add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("addition"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getEntry"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("additions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("clear"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))));
		}
		public void __add_sub_class_0__() {
			addSubClass(Addition._);
		}
		public void __add_sub_class_1__() {
			addSubClass(Builder._);
		}
		public void __INIT__() {
			super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("NameList"),
				"class ",
				new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashSet"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"),
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			__add_variable_0__();
			__add_variable_1__();
			__add_variable_2__();
			__add_variable_3__();
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
			__add_sub_class_0__();
			__add_sub_class_1__();
		}
		public static class Addition extends ExternalClassEntry{
			public static final Addition _ = new Addition();
			public Addition() {
				super();
			}
			public void __add_variable_0__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null));
			}
			public void __add_variable_1__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("entry"),""),null));
			}
			public void __add_method_0__() {
				addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Addition");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
			}
			public void __add_method_1__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("position"),"")))));
			}
			public void __add_method_2__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("position = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newPosition"),"")))));
			}
			public void __add_method_3__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getEntry");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("entry"),"")))));
			}
			public void __add_method_4__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setEntry");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newEntry");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("entry = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newEntry"),"")))));
			}
			public void __INIT__() {
				super.__SETUP__(
					null,
					new ExternalStatement("."),
					new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Addition"),
					"class ",
					null,
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
			}
		}
		public static class Builder extends ExternalClassEntry{
			public static final Builder _ = new Builder();
			public Builder() {
				super();
			}
			public void __add_variable_0__() {
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")),"",new ExternalStatement(new StringEntry("builder"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_variable_1__() {
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
			}
			public void __add_variable_2__() {
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("state"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))));
			}
			public void __add_variable_3__() {
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),"",new ExternalStatement(new StringEntry("multiple_satisfied"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))));
			}
			public void __add_variable_4__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"),"",new ExternalStatement(new StringEntry("_output"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__() {
				addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Builder");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
			}
			public void __add_method_1__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getBuilder");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("builder"),"")))));
			}
			public void __add_method_2__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setBuilder");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newBuilder");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("builder = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newBuilder"),"")))));
			}
			public void __add_method_3__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getLength");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("length"),"")))));
			}
			public void __add_method_4__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setLength");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newLength");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("length = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newLength"),"")))));
			}
			public void __add_method_5__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getState");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("state"),"")))));
			}
			public void __add_method_6__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setState");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newState");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("state = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newState"),"")))));
			}
			public void __add_method_7__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getMultipleSatisfied");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("multiple_satisfied"),"")))));
			}
			public void __add_method_8__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setMultipleSatisfied");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newMultipleSatisfied");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("multiple_satisfied = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newMultipleSatisfied"),"")))));
			}
			public void __add_method_9__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("get_output");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("_output"),"")))));
			}
			public void __add_method_10__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("set_output");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("new_output");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("_output = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("new_output"),"")))));
			}
			public void __add_method_11__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("can"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("char"),"")),"",new ExternalStatement(new StringEntry("newChar"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))))));
			}
			public void __add_method_12__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("boolean"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("char"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("newChar"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("can"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("newChar"),""))))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newChar"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("true"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,">=",Arrays.asList(new String[]{"","!=","&&",">="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("result"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_output"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),"")))))))));
			}
			public void __add_method_13__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("end"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement(null,null,">=",Arrays.asList(new String[]{"","!=","&&",">="}),new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("result"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("_output"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("length"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")))))))))))));
			}
			public void __INIT__() {
				super.__SETUP__(
					null,
					new ExternalStatement("."),
					new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Builder"),
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
			}
		}
	}
	public static class Result extends ExternalClassEntry{
		public static final Result _ = new Result();
		public Result() {
			super();
		}
		public void __add_variable_0__() {
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("state"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_variable_1__() {
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_variable_2__() {
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(new StringEntry("lineNumberRanges"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_3__() {
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_4__() {
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_5__() {
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new ExternalStatement(new StringEntry("parseTime"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
		}
		public void __add_method_0__() {
			addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Result");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
		}
		public void __add_method_1__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getState");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("state"),"")))));
		}
		public void __add_method_2__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setState");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newState");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("state = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newState"),"")))));
		}
		public void __add_method_3__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("position"),"")))));
		}
		public void __add_method_4__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("position = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newPosition"),"")))));
		}
		public void __add_method_5__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("getLineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("lineNumberRanges"),"")))));
		}
		public void __add_method_6__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setLineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("newLineNumberRanges");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("lineNumberRanges = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newLineNumberRanges"),"")))));
		}
		public void __add_method_7__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getInput");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("input"),"")))));
		}
		public void __add_method_8__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setInput");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newInput");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("input = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newInput"),"")))));
		}
		public void __add_method_9__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getFileName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("fileName"),"")))));
		}
		public void __add_method_10__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setFileName"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("newFileName"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("newFileName"),"")))))));
		}
		public void __add_method_11__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getParseTime");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parseTime"),"")))));
		}
		public void __add_method_12__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParseTime");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("long"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newParseTime");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parseTime = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParseTime"),"")))));
		}
		public void __add_method_13__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),null,new ExternalStatement(new StringEntry("getLineNumber"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("upperBound"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("lineNumber"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement.Conditional("while","(",new ExternalStatement(null,null,"<",Arrays.asList(new String[]{"","<","&&","<"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),""))))));
		}
		public void __add_method_14__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),null,new ExternalStatement(new StringEntry("getLineNumber"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("upperBound"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("lineNumber"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement.Conditional("while","(",new ExternalStatement(null,null,"<",Arrays.asList(new String[]{"","<","&&","<"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("+=",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),""))))));
		}
		public void __add_method_15__() {
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("state"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser.FAILED"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("lineNumber"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("getLineNumber"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("rangeIndex"),""),new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("upperBound"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("lowerBound"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))),new ExternalStatement.Conditional("if","(",new ExternalStatement(">",new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("lowerBound"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(new StringEntry("rangeIndex"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("errorAt"),""),null)),new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("length"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("lowerBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("$>"),"")))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("upperBound"),"")))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("lowerBound"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("<$"),"")))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("input"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("substring"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")))))))))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("<=",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tLine Number: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tError: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),""))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nFile: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(" Line : "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tError: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ms"),""))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("minutes"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("hundreds"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("100"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("tens"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("ones"),""),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),""))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("minutes"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("."),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("hundreds"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("tens"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("ones"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("s"),"")))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tError: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tFile: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(" Line : "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ms"),""))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("minutes"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("hundreds"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("100"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("tens"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("ones"),""),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),""))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tError: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("errorAt"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tFile: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(" Line: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumber"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n\\tParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("minutes"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("."),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("hundreds"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("tens"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("ones"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("s"),"")))))))))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<=",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000 "),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Parse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ms"),""))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ms"),"")))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("minutes"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1000"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("hundreds"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("100"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("tens"),""),new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement("/",new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),"")))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("ones"),""),new ExternalStatement("%",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement(new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),""))),new ExternalStatement(".",new ExternalStatement(new StringEntry("parseTime"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("10"),""))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Parse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("minutes"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("."),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("hundreds"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("tens"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("ones"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("s"),""))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("File: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\nParse Time: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("minutes"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("."),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("hundreds"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("tens"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("ones"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("s"),"")))))))))))))))))))));
		}
		public void __add_sub_class_0__() {
			addSubClass(Pass._);
		}
		public void __add_sub_class_1__() {
			addSubClass(Fail._);
		}
		public void __add_sub_class_2__() {
			addSubClass(Acceptor._);
		}
		public void __INIT__() {
			super.__SETUP__(
				null,
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
			__add_method_15__();
			__add_sub_class_0__();
			__add_sub_class_1__();
			__add_sub_class_2__();
		}
		public static class Pass extends ExternalClassEntry{
			public static final Pass _ = new Pass();
			public Pass() {
				super();
			}
			public void __add_variable_0__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("parsedRoot"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_variable_1__() {
				addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new ExternalStatement(new StringEntry("root"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__() {
				addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Pass");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
			}
			public void __add_method_1__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getParsedRoot");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parsedRoot"),"")))));
			}
			public void __add_method_2__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParsedRoot");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newParsedRoot");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parsedRoot = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParsedRoot"),"")))));
			}
			public void __add_method_3__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getRoot");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("root"),"")))));
			}
			public void __add_method_4__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setRoot");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newRoot");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("root = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newRoot"),"")))));
			}
			public void __add_method_5__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setup"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("setup"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("root"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("parsedRoot"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),"")))))))));
			}
			public void __add_method_6__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setup"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new ExternalStatement(new StringEntry("current"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("currentParsed"),""),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("currentPosition"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),"")))),">"),"",new ExternalStatement(new StringEntry("children"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("currentParsed"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getChildren"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(new StringEntry("positions"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("currentParsed"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getPositions"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("currentParsed"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getChildren"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("size"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("for","(",new ExternalStatement(";",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("i"),""),new ExternalStatement(new StringEntry("0"),"")),new ExternalStatement("<",new ExternalStatement(new StringEntry("i"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("size"),""))),new ExternalStatement(new ExternalStatement(new StringEntry("i"),""),new ExternalStatement(new StringEntry("++"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getChildren"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("setup"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("current"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))),"",new ExternalStatement(new StringEntry("newToken"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Branch"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("currentPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("current"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newToken"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("setup"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newToken"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))))))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("current"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Leaf"),""))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("currentPosition"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),"")))))))))))))))))));
			}
			public void __add_method_7__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("realFileName"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("result"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("realFileName"),"")))),new ExternalStatement.Conditional("if","(",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("equals"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(""),"")))))))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Passed: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")))))))))),new ExternalStatement.Conditional("else","(",null,")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")))))))));
			}
			public void __INIT__() {
				super.__SETUP__(
					null,
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
				__add_method_7__();
			}
		}
		public static class Fail extends ExternalClassEntry{
			public static final Fail _ = new Fail();
			public Fail() {
				super();
			}
			public void __add_variable_0__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("ruleName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__() {
				addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Fail");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
			}
			public void __add_method_1__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getRuleName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("ruleName"),"")))));
			}
			public void __add_method_2__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setRuleName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newRuleName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("ruleName = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newRuleName"),"")))));
			}
			public void __add_method_3__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("Failed: "),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("ruleName"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))));
			}
			public void __add_sub_class_0__() {
				addSubClass(EOF._);
			}
			public void __add_sub_class_1__() {
				addSubClass(EOB._);
			}
			public void __INIT__() {
				super.__SETUP__(
					null,
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
				__add_method_3__();
				__add_sub_class_0__();
				__add_sub_class_1__();
			}
			public static class EOF extends ExternalClassEntry{
				public static final EOF _ = new EOF();
				public EOF() {
					super();
				}
				public void __add_variable_0__() {
					addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("erroneousFile"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
				}
				public void __add_method_0__() {
					addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("EOF");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
				}
				public void __add_method_1__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getErroneousFile");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("erroneousFile"),"")))));
				}
				public void __add_method_2__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setErroneousFile");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newErroneousFile");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("erroneousFile = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newErroneousFile"),"")))));
				}
				public void __add_method_3__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("End of file not reached:"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("erroneousFile"),"")))))));
				}
				public void __INIT__() {
					super.__SETUP__(
						null,
						new ExternalStatement("."),
						new Entry(){public void get(StringBuilder builder){}},
						new VariableNameEntry("EOF"),
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
			public static class EOB extends ExternalClassEntry{
				public static final EOB _ = new EOB();
				public EOB() {
					super();
				}
				public void __add_variable_0__() {
					addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("myRuleName"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
				}
				public void __add_variable_1__() {
					addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("myPosition"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("-1"),""))));
				}
				public void __add_variable_2__() {
					addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new ExternalStatement(new StringEntry("myLineNumberRanges"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
				}
				public void __add_method_0__() {
					addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("EOB");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
				}
				public void __add_method_1__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getMyRuleName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("myRuleName"),"")))));
				}
				public void __add_method_2__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setMyRuleName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newMyRuleName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("myRuleName = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newMyRuleName"),"")))));
				}
				public void __add_method_3__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getMyPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("myPosition"),"")))));
				}
				public void __add_method_4__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setMyPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newMyPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("myPosition = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newMyPosition"),"")))));
				}
				public void __add_method_5__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("getMyLineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("myLineNumberRanges"),"")))));
				}
				public void __add_method_6__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setMyLineNumberRanges");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("newMyLineNumberRanges");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("myLineNumberRanges = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newMyLineNumberRanges"),"")))));
				}
				public void __add_method_7__() {
					addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("lineNumberRanges"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("myLineNumberRanges"),"")))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement("=",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("myPosition"),"")))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("End of brace not reached by ["),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("myRuleName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("]:"),"")))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("getLineNumber"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
				}
				public void __INIT__() {
					super.__SETUP__(
						null,
						new ExternalStatement("."),
						new Entry(){public void get(StringBuilder builder){}},
						new VariableNameEntry("EOB"),
						"class ",
						new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),
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
				}
			}
		}
		public static class Acceptor extends ExternalClassEntry{
			public static final Acceptor _ = new Acceptor();
			public Acceptor() {
				super();
			}
			public void __add_variable_0__() {
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),"")))),">"),"",new ExternalStatement(new StringEntry("results"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),"")))),">"))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
			}
			public void __add_method_0__() {
				addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Acceptor");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
			}
			public void __add_method_1__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),"")))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("getResults");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("results"),"")))));
			}
			public void __add_method_2__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setResults");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),"<",new ExternalStatement.Parameters(new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),"")))),">"),"",new Entry(){public void get(StringBuilder builder){builder.append("newResults");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("results = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newResults"),"")))));
			}
			public void __add_method_3__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(new StringEntry("result"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("setFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("results"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),""))))))))));
			}
			public void __add_method_4__() {
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")),"",new ExternalStatement(new StringEntry("builder"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("StringBuilder"),"")))),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Conditional("for","(",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("results"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("resultString"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("result"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))),new ExternalStatement.Conditional("if","(",new ExternalStatement("!=",new ExternalStatement(".",new ExternalStatement(new StringEntry("resultString"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))),")",new ExternalStatement.Body(new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("\\n"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("append"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("resultString"),"")))))))))))))))),new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("super"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("builder"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("toString"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))));
			}
			public void __INIT__() {
				super.__SETUP__(
					null,
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
				__add_method_4__();
			}
		}
	}
}