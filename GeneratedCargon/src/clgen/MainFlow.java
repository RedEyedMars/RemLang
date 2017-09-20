package clgen;
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
	//Externals


	protected Parser ParserClass = new Parser();
	public class Parser extends ExternalClassEntry {
		protected final ExternalStatement.Body readInputBody = new ExternalStatement.Body();
		protected final ExternalStatement.Body braceVariableDeclaration = new ExternalStatement.Body();
		protected final String notQuoting = "!quoting";
		protected final String notEscaping = "!escaping";
		protected final Map<String,List<ExternalStatement.Body>> rules = new HashMap<String,List<ExternalStatement.Body>>();
		protected final Map<String,List<ExternalStatement.Body>> completeRules = new HashMap<String,List<ExternalStatement.Body>>();
		protected final Map<ExternalStatement.Body,String> ruleHolders = new HashMap<ExternalStatement.Body,String>();
		protected final Map<String,Map<Integer,List<ExternalStatement.Body>>> unsatisfiedRules = new HashMap<String,Map<Integer,List<ExternalStatement.Body>>>();
		protected final Map<String,ExternalStatement.Body> ruleForeBodies = new HashMap<String,ExternalStatement.Body>();
		protected final Set<String> silentRules = new HashSet<String>();
		protected final Set<String> listNames = new HashSet<String>();
		protected final Map<String,Set<String>> listNamesInRule = new HashMap<String,Set<String>>();
		protected final Map<String,String> handleListAdditions = new HashMap<String,String>();
		protected final Map<String,ExternalStatement.Body> handleListAdditionAftBodies = new HashMap<String,ExternalStatement.Body>();
		protected Integer tokenId = 1;
		protected String ROOT_NAME = null;
		protected final ExternalStatement bracedCondition = new ExternalStatement();
		protected final Set<String> declaredBraces = new HashSet<String>();
		protected final Map<String,List<String>> declaredBraceValues = new HashMap<String,List<String>>();
		protected final Map<String,String> declaredBraceRules = new HashMap<String,String>();
		protected final Map<String,Integer> declaredBraceOpenLengths = new HashMap<String,Integer>();
		protected final Map<String,Integer> declaredBraceCloseLengths = new HashMap<String,Integer>();
		protected Integer currentPositionIndex = 0;
		protected Integer anonymousRuleIndex = 0;
		protected String plainTokenClassName = "_0";
		protected Integer plainTokenIndex = 0;
		protected final Set<String> createdPlainTokens = new HashSet<String>();
		protected final Set<String> createdNameTokens = new HashSet<String>();
		protected Integer listIndex = 0;
		protected Integer multipleIndex = 0;

	
public void output() {
	if (ROOT_NAME == null) {
		System.err.println("No root rule found!");
	}
	else  {
		final ExternalStatement.Body rootBody = new ExternalStatement.Body();
		getRuleBody(rootBody,ROOT_NAME,new HashMap<String,Set<Integer>>());
		ParserClass.getMethod("parse").appendToBody(rootBody);
		ParserClass.getMethod("parse").appendToBody(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_result")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Pass"))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_lineNumber")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_fileName")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_tokens")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_children")))))))))))));
		ParserClass.getMethod("parse").appendToBody(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("long"))), /*Name*/new ExternalStatement(new StringEntry("parseTime")), /*Optr*/new ExternalStatement("-", /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("System"))), /*Enty*/new ExternalStatement(new StringEntry("currentTimeMillis"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("startParseTime"))))))));
		ParserClass.getMethod("parse").appendToBody(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_result"))), /*Enty*/new ExternalStatement(new StringEntry("setParseTime"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("parseTime")))))))))));
		ParserClass.getMethod("parse").appendToBody(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_result")))))));
	}
}
	
public void setupCompile() {
	declaredBraces.add("\"\"");
}
	
public void list(final IToken input) {
	final String listName = input.get("listName").toString();
ParserClass.addVariable(new ExternalVariableEntry(false, true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Root"))))), /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_root")))), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Root"))))),new ExternalStatement.Parameters()))));
	variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList"))))), /*Enty*/new ExternalStatement(new StringEntry(input.get("listName").toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(input.get("listName").toString())), /*Name*/new ExternalStatement(new StringEntry("_root"))))))))));
	variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList"))))), /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(input.get("listName").toString())), /*Name*/new ExternalStatement(new StringEntry("_additions")))), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null"))))))));
	listNames.add(input.get("listName").toString());
	for(final IToken element:input.getAllSafely("quote")) {
			ParserClass.getMethod("setup").appendToBody(/*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_root"))))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(element.toString().toString())))))))))));
	}
}
	
public ExternalStatement.Body getListDeclarations(final String ruleName,final String listRulename) {
	final ExternalStatement.Body listDeclarations = new ExternalStatement.Body();
	if (listNamesInRule.containsKey(ruleName)) {
		for (final String listName :  listNamesInRule.get(ruleName)) {
			final String subListName = listName.toString()+"_"+listRulename.toString();
			if (variableDeclarationNames.add(subListName)) {
				variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList"))))), /*Enty*/new ExternalStatement(new StringEntry(subListName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null"))))))));
			}
			listDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(subListName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(listName.toString()))))))));
			listDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Child"))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(listName.toString())))))))))));
		}
	}
	return listDeclarations;
}
	
public ExternalStatement.Body getListReallocations(final String ruleName,final String listRulename) {
	final ExternalStatement.Body listReallocations = new ExternalStatement.Body();
	if (listNamesInRule.containsKey(ruleName)) {
		for (final String listName :  listNamesInRule.get(ruleName)) {
			final String subListName = listName.toString()+"_"+listRulename.toString();
			listReallocations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(subListName.toString()))))))));
		}
	}
	return listReallocations;
}
	
public void getRuleBody(final ExternalStatement.Body completeBody,final String ruleName,final Map<String,Set<Integer>> excludeIndicesMap) {
	final List<ExternalStatement.Body> rule = rules.get(ruleName);
	if (excludeIndicesMap.containsKey(ruleName) == false) {
		excludeIndicesMap.put(ruleName,new HashSet<Integer>());
	}
	final Set<Integer> excludeIndices = excludeIndicesMap.get(ruleName);
	final Map<Integer,List<ExternalStatement.Body>> unsatisfiedMap;
	if (unsatisfiedRules.containsKey(ruleName)) {
		unsatisfiedMap = unsatisfiedRules.get(ruleName);
	}
	else  {
		unsatisfiedMap = null;
	}
	ExternalStatement.Body currentOption = completeBody;
	final ExternalStatement.Body withinBraces = new ExternalStatement.Body();
	final Integer ruleListIndex = listIndex;
	if (declaredBraceRules.containsKey(ruleName)) {
		listIndex += 1;
		currentOption.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceMap"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(declaredBraceRules.get(ruleName).toString().toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("containsKey"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))))))),
			/*InCl*/new ExternalStatement(withinBraces))));
		currentOption = withinBraces;
		final String currentPositionValue = "_position_" + ruleName.toString() + "_brace";
		final String currentLengthValue = "_length_" + ruleName.toString() + "_brace";
		currentOption.add(getListDeclarations(ruleName,ruleName));
		currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(currentLengthValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength"))))))));
		currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_inputLength")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceMap"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(declaredBraceRules.get(ruleName).toString().toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))))));
		currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))));
		currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("+=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(declaredBraceOpenLengths.get(ruleName).toString())))))));
		if (variableDeclarationNames.add(currentPositionValue)) {
			variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("-1"))))))));
		}
		if (variableDeclarationNames.add(currentLengthValue)) {
			variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(currentLengthValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("-1"))))))));
		}
	}
	if (handleListAdditions.containsKey(ruleName)) {
		final String forRuleName = handleListAdditions.get(ruleName);
		if (listNamesInRule.containsKey(forRuleName)) {
			for (final String listName :  listNamesInRule.get(forRuleName)) {
				final String subListName = listName.toString() + "_additions_" + ruleName.toString();
				if (variableDeclarationNames.add(subListName)) {
					variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList"))))), /*Enty*/new ExternalStatement(new StringEntry(subListName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null"))))))));
				}
				currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(subListName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions"))))))))));
				currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions")))), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Child"))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(listName.toString())))))))))));
				handleListAdditionAftBodies.get(ruleName).add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions")))), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(subListName.toString()))))))));
			}
		}
	}
	ExternalStatement.Body previousOption = null;
	for (Integer i = 0; i <  rule.size(); ++i) {
		if (excludeIndices.contains(i) == false) {
			if (previousOption != null) {
				previousOption.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*InCl*/new ExternalStatement(currentOption))));
				if (handleListAdditions.containsKey(ruleName)) {
					final String forRuleName = handleListAdditions.get(ruleName);
					if (listNamesInRule.containsKey(forRuleName)) {
						for (final String listName :  listNamesInRule.get(forRuleName)) {
							currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(listName.toString()))), /*Enty*/new ExternalStatement(new StringEntry("removeAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions")))))))))))));
							currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions"))))), /*Enty*/new ExternalStatement(new StringEntry("clear"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))));
						}
					}
				}
				currentOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS"))))))));
			}
			previousOption = currentOption;
			currentOption.add(rule.get(i));
			currentOption = new ExternalStatement.Body();
			if (unsatisfiedMap != null) {
				if (unsatisfiedMap.containsKey(i)) {
					excludeIndices.add(i);
					for (final ExternalStatement.Body unsatisfiedBody :  unsatisfiedMap.get(i)) {
						getRuleBody(unsatisfiedBody,ruleHolders.get(unsatisfiedBody),excludeIndicesMap);
					}
					excludeIndices.remove(i);
				}
			}
		}
	}
	if (declaredBraceRules.containsKey(ruleName)) {
		final String currentLengthValue = "_length_" + ruleName.toString() + "_brace";
		final String currentPositionValue = "_position_" + ruleName.toString() + "_brace";
		withinBraces.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))), /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceMap"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(declaredBraceRules.get(ruleName).toString().toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("_position_")), /*Enty*/new ExternalStatement(new StringEntry(ruleName.toString())))), /*Name*/new ExternalStatement(new StringEntry("_brace")))))))))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("+=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(declaredBraceCloseLengths.get(ruleName).toString()))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_position")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_furthestPosition")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_result")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Fail"))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_lineNumber")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_fileName")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(ruleName.toString().toString()))))))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_furthestPosition")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))))))))),
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_inputLength")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentLengthValue.toString()))))))));
	}
	if (handleListAdditions.containsKey(ruleName)) {
		final String forRuleName = handleListAdditions.get(ruleName);
		if (listNamesInRule.containsKey(forRuleName)) {
			final ExternalStatement.Body removeAdditionsBody = new ExternalStatement.Body();
			final ExternalStatement.Body addAdditionBody = new ExternalStatement.Body();
			previousOption.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*InCl*/new ExternalStatement(removeAdditionsBody))));
			previousOption.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"else if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*InCl*/new ExternalStatement(addAdditionBody))));
			if (declaredBraceRules.containsKey(ruleName)) {
				previousOption.add(getListReallocations(ruleName,forRuleName));
			}
			for (final String listName :  listNamesInRule.get(forRuleName)) {
				final String subListName = listName.toString() + "_additions_" + ruleName.toString();
				removeAdditionsBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions")))))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(listName.toString()))), /*Enty*/new ExternalStatement(new StringEntry("removeAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions"))))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions"))))), /*Enty*/new ExternalStatement(new StringEntry("clear"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))))));
				addAdditionBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions")))))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(listName.toString()))), /*Enty*/new ExternalStatement(new StringEntry("addAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions"))))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions"))))), /*Enty*/new ExternalStatement(new StringEntry("clear"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))))));
				previousOption.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(listName.toString())), /*Name*/new ExternalStatement(new StringEntry("_additions")))), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(subListName.toString()))))))));
			}
		}
	}
	else if (declaredBraceRules.containsKey(ruleName)) {
		withinBraces.add(getListReallocations(ruleName,ruleName));
	}
}
	
public String define(final IToken input,final String previousParentRuleName) {
	final String ruleName;
	final String parentRuleName;
	if (input.get("ruleName") != null) {
		ruleName = input.get("ruleName").toString();
		final String ruleClassName = Generator.camelize(ruleName.toString());
TokensClass.getSubClass("Rule").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		},new StringEntry(ruleClassName), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry(ruleClassName).get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("getValue")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null"))))))),new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("getName")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(ruleName.toString().toString())))))))}), Arrays.asList(new ExternalClassEntry[]{})) ;}});
		parentRuleName = ruleName;
	}
	else  {
		ruleName = "_anonymous_" + anonymousRuleIndex;
		anonymousRuleIndex += 1;
		silentRules.add(ruleName);
		parentRuleName = previousParentRuleName;
	}
	if (ROOT_NAME == null) {
		ROOT_NAME = ruleName;
	}
	if (rules.containsKey(ruleName) == false) {
		completeRules.put(ruleName,new ArrayList<ExternalStatement.Body>());
		rules.put(ruleName,completeRules.get(ruleName));
		completeRules.get(ruleName).add(new ExternalStatement.Body());
	}
	final List<ExternalStatement.Body> rule = completeRules.get(ruleName);
	final Boolean isSilent = input.get("SILENT") != null;
	if (isSilent) {
		silentRules.add(ruleName);
	}
	final ExternalStatement.Body foreBody = new ExternalStatement.Body();
	final String currentPositionValue = "_position_" + ruleName;
	foreBody.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_position")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))))),
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_furthestPosition")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_result")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Fail"))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_lineNumber")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_fileName")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(ruleName.toString().toString()))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_furthestPosition")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))));
	ruleForeBodies.put(ruleName,foreBody);
	if (input.get("braced_parameters") != null) {
		final String left = input.get("braced_parameters").get("left").toString();
		final String right = input.get("braced_parameters").get("right").toString();
		final String both = left + right;
		final Integer openLength = left.length();
		final Integer closeLength = right.length();
		if (declaredBraceValues.containsKey(both) == false) {
			declaredBraceValues.put(both,new ArrayList<String>());
		}
		declaredBraceValues.get(both).add(ruleName);
		declaredBraceRules.put(ruleName,both);
		declaredBraceOpenLengths.put(ruleName,openLength);
		declaredBraceCloseLengths.put(ruleName,closeLength);
		if (declaredBraces.add(both)) {
			braceVariableDeclaration.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceMap"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(both.toString().toString())))),/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashMap"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))),new ExternalStatement.Parameters())))))))));
			braceVariableDeclaration.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceOpens"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(both.toString().toString())))),/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Stack"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))),new ExternalStatement.Parameters())))))))));
			readInputBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_readInput")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("'")), /*Enty*/new ExternalStatement(new StringEntry(left.toString())))), /*Name*/new ExternalStatement(new StringEntry("'")))))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceOpens"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(both.toString().toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("push"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else if ", 
			/*Optr*/new ExternalStatement("&&!", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_readInput")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("'")), /*Enty*/new ExternalStatement(new StringEntry(right.toString())))), /*Name*/new ExternalStatement(new StringEntry("'")))))), /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceOpens"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(both.toString().toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("isEmpty"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceMap"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(both.toString().toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceOpens"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(both.toString().toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("pop"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))))))))))));
		}
	}
	for(final IToken definition:input.getAllSafely("definition")) {
			define(definition,ruleName,parentRuleName,rule);
	}
	return ruleName;
}
	
public void define(final IToken definition,final String ruleName,final String parentRuleName,final List<ExternalStatement.Body> inputRule) {
	Boolean isFirst = true;
	final Integer choiceIndex = inputRule.size() - 1;
	ExternalStatement.Body rule = inputRule.get(choiceIndex);
	ExternalStatement.Body nextBody = null;
	final String currentPositionValue = "_position_" + ruleName;
	final String currentLayerValue = "_layer_" + ruleName;
	if (variableDeclarationNames.add(currentPositionValue)) {
		variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("-1"))))))));
		variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("List"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))), /*Enty*/new ExternalStatement(new StringEntry(currentLayerValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null"))))))));
	}
	rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))));
	rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(currentLayerValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_layer"))))))));
	rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_layer")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("ArrayList"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))),new ExternalStatement.Parameters()))))));
	final ExternalStatement.Body foreBody = ruleForeBodies.get(ruleName);
	for(final IToken chain:definition.getAllSafely("chain")) {
			for(final IToken element:chain.getAllSafely("element")) {
				parseElement(element,ruleName,parentRuleName,rule,choiceIndex,isFirst);
				isFirst = false;
				nextBody = new ExternalStatement.Body();
				final ExternalStatement.Body realNextBody = nextBody;
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*InCl*/new ExternalStatement(foreBody)),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*InCl*/new ExternalStatement(realNextBody))));
				rule = nextBody;
			}
	}
	if (silentRules.contains(ruleName) == false) {
		final String ruleAsClass = Generator.camelize(ruleName.toString());
		inputRule.get(inputRule.size() - 1).add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_children"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_layer"))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_tokens"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))),/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Tokens")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Rule")))), /*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(ruleAsClass.toString())))))),new ExternalStatement.Parameters()))))))))),
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_layer")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentLayerValue.toString()))))))));
	}
	else  {
		inputRule.get(inputRule.size() - 1).add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentLayerValue.toString()))), /*Enty*/new ExternalStatement(new StringEntry("addAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_layer"))))))))))),
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_layer")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentLayerValue.toString()))))))));
	}
	if (definition.get("choice") != null) {
		inputRule.add(new ExternalStatement.Body());
		define(definition.get("choice").get("definition"),ruleName,parentRuleName,inputRule);
	}
}
	
public void parseElement(final IToken element,final String ruleName,final String parentRuleName,final ExternalStatement.Body rule,final Integer choiceIndex,final Boolean isFirst) {
	for(IToken.Key __query__KEY:element.keySet()) {
		if(__query__KEY.getName().equals("multiple")){ final IToken query = element.get(__query__KEY);
			final String subRuleName = define(query,parentRuleName);
			final ExternalStatement.Body subRuleBody = new ExternalStatement.Body();
			ruleHolders.put(subRuleBody,subRuleName);
			if (unsatisfiedRules.containsKey(ruleName) == false) {
			unsatisfiedRules.put(ruleName,new HashMap<Integer,List<ExternalStatement.Body>>());
			}
			if (unsatisfiedRules.get(ruleName).containsKey(choiceIndex) == false) {
			unsatisfiedRules.get(ruleName).put(choiceIndex,new ArrayList<ExternalStatement.Body>());
			}
			unsatisfiedRules.get(ruleName).get(choiceIndex).add(subRuleBody);
			final ExternalStatement.Body aftBody;
			if (declaredBraceRules.containsKey(parentRuleName)) {
			handleListAdditions.put(subRuleName,parentRuleName);
			aftBody = new ExternalStatement.Body();
			handleListAdditionAftBodies.put(subRuleName,aftBody);
			}
			else  {
			aftBody = null;
			}
			for(IToken.Key __option__KEY:query.get("option").keySet()) {
			if(__option__KEY.getName().equals("OPTIONAL")){ final IToken option = query.get("option").get(__option__KEY);
				final String stateName = "_state_" + multipleIndex;
				multipleIndex += 1;
				rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(stateName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state"))))))));
				rule.add(subRuleBody);
				if (aftBody != null) {
				rule.add(aftBody);
				}
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(stateName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS"))))))))));
			}
			if(__option__KEY.getName().equals("MANY")){ final IToken option = query.get("option").get(__option__KEY);
				final String stateName = "_state_" + multipleIndex;
				multipleIndex += 1;
				rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(stateName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state"))))))));
				final ExternalStatement.Body whileRuleBody = new ExternalStatement.Body();
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"while ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*InCl*/new ExternalStatement(whileRuleBody))));
				whileRuleBody.add(subRuleBody);
				if (aftBody != null) {
				final ExternalStatement.Body breakAftBody = new ExternalStatement.Body();
				breakAftBody.add(aftBody);
				breakAftBody.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("break")))))));
				whileRuleBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*InCl*/new ExternalStatement(breakAftBody))));
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(stateName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*InCl*/new ExternalStatement(aftBody))));
				}
				else  {
				whileRuleBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("break")))))))));
				}
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(stateName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS"))))))))));
			}
			if(__option__KEY.getName().equals("PLUS")){ final IToken option = query.get("option").get(__option__KEY);
				final String stateName = "_state_" + multipleIndex;
				final String multipleValueName = "_multiple_index_" + multipleIndex;
				multipleIndex += 1;
				rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(stateName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state"))))))));
				rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))))));
				final ExternalStatement.Body breakAftBody = new ExternalStatement.Body();
				if (aftBody != null) {
				breakAftBody.add(aftBody);
				}
				breakAftBody.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("break")))))));
				final ExternalStatement.Body whileRuleBody = new ExternalStatement.Body();
				whileRuleBody.add(subRuleBody);
				whileRuleBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*InCl*/new ExternalStatement(breakAftBody)),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("++")), /*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())))))))))));
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"while ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*InCl*/new ExternalStatement(whileRuleBody))));
				final ExternalStatement.Body noIterationFailBody = new ExternalStatement.Body();
				if (aftBody != null) {
				noIterationFailBody.add(aftBody);
				}
				noIterationFailBody.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED"))))))));
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0 ")))),
			/*InCl*/new ExternalStatement(noIterationFailBody)),
		/*Cond*/new ExternalStatement.Conditional(
			"else if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Optr*/new ExternalStatement(">", /*Optr*/new ExternalStatement("&&", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(stateName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0 ")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS"))))))))));
			}
			}
		}
		if(__query__KEY.getName().equals("braced")){ final IToken query = element.get(__query__KEY);
			final String subRuleName = define(query,parentRuleName);
			final ExternalStatement.Body subRuleBody = new ExternalStatement.Body();
			ruleHolders.put(subRuleBody,subRuleName);
			if (unsatisfiedRules.containsKey(ruleName) == false) {
			unsatisfiedRules.put(ruleName,new HashMap<Integer,List<ExternalStatement.Body>>());
			}
			if (unsatisfiedRules.get(ruleName).containsKey(choiceIndex) == false) {
			unsatisfiedRules.get(ruleName).put(choiceIndex,new ArrayList<ExternalStatement.Body>());
			}
			unsatisfiedRules.get(ruleName).get(choiceIndex).add(subRuleBody);
			rule.add(subRuleBody);
		}
		if(__query__KEY.getName().equals("quoteToken")){ final IToken query = element.get(__query__KEY);
			final String quote = element.get("quoteToken").get("quote").toString();
			final String quoteLength = "" + quote.length();
			final ExternalStatement.Body subrule = new ExternalStatement.Body();
			for (Integer i = 0; i <  quote.length(); ++i) {
			final String ch = "\'" + quote.charAt(i) + "\'";
			final String charArrayValue = "_inputArray[_position+" + i + "]";
			subrule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(charArrayValue.toString())))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(ch.toString())))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED"))))))))));
			}
			rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Optr*/new ExternalStatement("-", /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(quoteLength.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1 ")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*InCl*/new ExternalStatement(subrule))));
			if (element.get("newName") != null) {
			final String newTokenName = Generator.camelize(element.get("newName").toString());
			if (createdPlainTokens.contains(newTokenName) == false) {
TokensClass.getSubClass("Plain").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		},new StringEntry(newTokenName), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry(newTokenName).get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("value")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("getName")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(element.get("newName").toString().toString())))))))}), Arrays.asList(new ExternalClassEntry[]{})) ;}});
			}
TokensClass.getSubClass("Plain").getSubClass(newTokenName.toString()).addVariable(new ExternalVariableEntry(true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))), /*Enty*/new ExternalStatement(new StringEntry(quote.toString())), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Tokens")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Plain")))), /*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(newTokenName.toString())))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(quote.toString().toString())))))))));
			rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_tokens"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("Tokens"))), /*Name*/new ExternalStatement(new StringEntry("Plain"))), /*Enty*/new ExternalStatement(new StringEntry(newTokenName.toString()))), /*Enty*/new ExternalStatement(new StringEntry(element.get("quoteToken").get("quote").toString()))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_layer"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_position")), /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(quoteLength.toString()))))))))));
			}
			else  {
			rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_position")), /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(quoteLength.toString()))))))))));
			}
			rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"else if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_furthestPosition")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_result")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Fail"))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_lineNumber")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_fileName")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("unexpected plain \\\""+quote.toString()+"\\\"".toString()))))))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_furthestPosition")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))))));
		}
		if(__query__KEY.getName().equals("regexToken")){ final IToken query = element.get(__query__KEY);
			final IToken regex = element.get("regexToken").get("regex");
			final String currentPositionValue = "_position_regex_" + currentPositionIndex;
			if (variableDeclarationNames.add(currentPositionValue)) {
			variableDeclarations.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("-1"))))))));
			}
			final StringBuilder regexValue = new StringBuilder();
			rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))));
			for(final IToken regexElement:regex.getAllSafely("regex_element")) {
				regexValue.append(addRegexElementToRule(regexElement,rule,currentPositionValue));
			}
			currentPositionIndex += 1;
			if (element.get("newName") != null) {
			final String newTokenName = Generator.camelize(element.get("newName").toString());
			if (createdPlainTokens.contains(newTokenName) == false) {
TokensClass.getSubClass("Plain").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		},new StringEntry(newTokenName), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry(newTokenName).get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("value")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("getName")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(element.get("newName").toString().toString())))))))}), Arrays.asList(new ExternalClassEntry[]{})) ;}});
			}
			if (element.get("listName") != null) {
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_tokens"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))),/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Tokens")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Plain")))), /*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(newTokenName.toString())))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_input"))), /*Enty*/new ExternalStatement(new StringEntry("substring"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))))))))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_layer"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString()))))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.get("listName").toString()))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_input"))), /*Enty*/new ExternalStatement(new StringEntry("substring"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))))))))))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Enty*/new ExternalStatement(new StringEntry(element.get("listName").toString())), /*Name*/new ExternalStatement(new StringEntry("_additions"))))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_input"))), /*Enty*/new ExternalStatement(new StringEntry("substring"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))))))))))))))))));
				if (listNamesInRule.containsKey(parentRuleName) == false) {
					listNamesInRule.put(parentRuleName,new HashSet<String>());
				}
				listNamesInRule.get(parentRuleName).add(element.get("listName").toString());
				handleListAdditions.put(ruleName,parentRuleName);
				if (handleListAdditionAftBodies.containsKey(ruleName) == false) {
					handleListAdditionAftBodies.put(ruleName,new ExternalStatement.Body());
				}
			}
			else  {
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_tokens"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))),/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Tokens")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Plain")))), /*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(newTokenName.toString())))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_input"))), /*Enty*/new ExternalStatement(new StringEntry("substring"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))))))))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_layer"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))))))))))));
			}
			}
			rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_position")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(currentPositionValue.toString())))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_furthestPosition")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_result")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Fail"))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_lineNumber")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_fileName")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(regexValue.toString().toString()))))))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_furthestPosition")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))))));
		}
		if(__query__KEY.getName().equals("ruleToken")){ final IToken query = element.get(__query__KEY);
			if (listNames.contains(query.toString())) {
			final String listName = query.toString();
			final String newTokenClassName;
			final String newTokenName;
			if (element.get("newName") != null) {
				newTokenClassName = Generator.camelize(element.get("newName").toString());
				newTokenName = query.toString();
			}
			else  {
				newTokenClassName = Generator.camelize(query.toString());
				newTokenName = query.toString();
			}
			if (createdNameTokens.contains(newTokenClassName) == false) {
TokensClass.getSubClass("Name").addSubClass(new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		},new StringEntry(newTokenClassName), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry(newTokenClassName).get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("value")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("getName")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(newTokenName.toString().toString())))))))}), Arrays.asList(new ExternalClassEntry[]{})) ;}});
			}
			rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_list_name_result")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(listName.toString()))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray")))))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_list_name_result")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_tokens"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Tokens")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Name")))), /*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(newTokenClassName.toString())))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_list_name_result")))))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_layer"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("+=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_list_name_result"))), /*Enty*/new ExternalStatement(new StringEntry("length"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_furthestPosition")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_result")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Fail"))))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_lineNumber")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_fileName")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(listName.toString().toString()))))))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_furthestPosition")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))))));
			}
			else  {
			final String subRuleName = query.toString();
			final ExternalStatement.Body subRuleBody = new ExternalStatement.Body();
			ruleHolders.put(subRuleBody,subRuleName);
			if (unsatisfiedRules.containsKey(ruleName) == false) {
				unsatisfiedRules.put(ruleName,new HashMap<Integer,List<ExternalStatement.Body>>());
			}
			if (unsatisfiedRules.get(ruleName).containsKey(choiceIndex) == false) {
				unsatisfiedRules.get(ruleName).put(choiceIndex,new ArrayList<ExternalStatement.Body>());
			}
			unsatisfiedRules.get(ruleName).get(choiceIndex).add(subRuleBody);
			rule.add(subRuleBody);
			}
		}
	}
}
	
public String addRegexElementToRule(final IToken element,final ExternalStatement.Body rule,final String positionName) {
	if (element.get("option") != null) {
		final StringBuilder regexValue = new StringBuilder();
		regexValue.append("[");
		final ExternalStatement option = new ExternalStatement();
		if (element.get("option").get("negate") == null) {
			option.set("||");
			final IToken optionToken = element.get("option");
			for(IToken.Key __atom__KEY:optionToken.keySet()) {
				if(__atom__KEY.getName().equals("range")){ final IToken atom = optionToken.get(__atom__KEY);
					final Character ch = atom.get("left").toString().charAt(0);
					final Integer end = atom.get("right").toString().charAt(0) - ch;
					for (Integer i = 0; i <=  end; ++i) {
					final String chValue = "'"+asChar(ch+i) + "'";
					option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(chValue.toString())))));
					}
					regexValue.append(atom.get("left").toString());
					regexValue.append("-");
					regexValue.append(atom.get("right").toString());
				}
				if(__atom__KEY.getName().equals("regex_special")){ final IToken atom = optionToken.get(__atom__KEY);
					for(IToken.Key __quark__KEY:atom.keySet()) {
					if(__quark__KEY.getName().equals("REGEX_NUMBER")){ final IToken quark = atom.get(__quark__KEY);
						final Character ch = "0".charAt(0);
						final Integer end = "9".charAt(0) - ch;
						for (Integer i = 0; i <=  end; ++i) {
						final String chValue = "'"+asChar(ch+i) + "'";
						option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(chValue.toString())))));
						}
						regexValue.append("\\\\d");
					}
					if(__quark__KEY.getName().equals("REGEX_WHITESPACE")){ final IToken quark = atom.get(__quark__KEY);
						option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("' '")))));
						option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\t'")))));
						option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\n'")))));
						regexValue.append("\\\\s");
					}
					if(__quark__KEY.getName().equals("REGEX_QUOTE")){ final IToken quark = atom.get(__quark__KEY);
						option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\\"'")))));
						regexValue.append("\\\"");
					}
					if(__quark__KEY.getName().equals("REGEX_DOT")){ final IToken quark = atom.get(__quark__KEY);
						option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'.'")))));
						regexValue.append("\\.");
					}
					}
				}
				if(__atom__KEY.getName().equals("standAlone")){ final IToken atom = optionToken.get(__atom__KEY);
					option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("'")), /*Enty*/new ExternalStatement(new StringEntry(atom.toString())))), /*Name*/new ExternalStatement(new StringEntry("'")))))));
					regexValue.append(atom.toString());
				}
			}
		}
		else  {
			option.set("&&");
			final IToken optionToken = element.get("option");
			for(IToken.Key __atom__KEY:optionToken.keySet()) {
				if(__atom__KEY.getName().equals("range")){ final IToken atom = optionToken.get(__atom__KEY);
					final Character ch = atom.get("left").toString().charAt(0);
					final Integer end = atom.get("right").toString().charAt(0) - ch;
					for (Integer i = 0; i <=  end; ++i) {
					final String chValue = "'"+asChar(ch+i) + "'";
					option.add(/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(chValue.toString())))));
					}
					regexValue.append(atom.get("left").toString());
					regexValue.append("-");
					regexValue.append(atom.get("right").toString());
				}
				if(__atom__KEY.getName().equals("regex_special")){ final IToken atom = optionToken.get(__atom__KEY);
					for(IToken.Key __quark__KEY:atom.keySet()) {
					if(__quark__KEY.getName().equals("REGEX_NUMBER")){ final IToken quark = atom.get(__quark__KEY);
						final Character ch = "0".charAt(0);
						final Integer end = "9".charAt(0) - ch;
						for (Integer i = 0; i <=  end; ++i) {
						final String chValue = "'"+asChar(ch+i) + "'";
						option.add(/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(chValue.toString())))));
						}
						regexValue.append("\\\\d");
					}
					if(__quark__KEY.getName().equals("REGEX_WHITESPACE")){ final IToken quark = atom.get(__quark__KEY);
						option.add(/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("' '")))));
						option.add(/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\t'")))));
						option.add(/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\n'")))));
						regexValue.append("\\\\s");
					}
					if(__quark__KEY.getName().equals("REGEX_QUOTE")){ final IToken quark = atom.get(__quark__KEY);
						option.add(/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\\"'")))));
						regexValue.append("\\\"");
					}
					if(__quark__KEY.getName().equals("REGEX_DOT")){ final IToken quark = atom.get(__quark__KEY);
						option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'.'")))));
						regexValue.append("\\.");
					}
					}
				}
				if(__atom__KEY.getName().equals("standAlone")){ final IToken atom = optionToken.get(__atom__KEY);
					option.add(/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("'")), /*Enty*/new ExternalStatement(new StringEntry(atom.toString())))), /*Name*/new ExternalStatement(new StringEntry("'")))))));
					regexValue.append(atom.toString());
				}
			}
		}
		if (element.get("multiple") != null) {
			final String multipleValueName = "_multiple_index_" + multipleIndex;
			multipleIndex += 1;
			if (element.get("multiple").get("OPTIONAL") != null) {
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(option))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++_position")))))))))));
			}
			else if (element.get("multiple").get("MANY") != null) {
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"while ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(option))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++_position"))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("break")))))))))));
			}
			else if (element.get("multiple").get("PLUS") != null) {
				rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))))),
		/*Cond*/new ExternalStatement.Conditional(
			"while ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(option))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++_position"))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("++")), /*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString()))))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("break"))))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0 ")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED"))))))))));
			}
		}
		else  {
			rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(option))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++_position"))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED"))))))))));
		}
		regexValue.append("]");
		if (element.get("multiple") != null) {
			if (element.get("multiple").get("OPTIONAL") != null) {
				regexValue.append("?");
			}
			else if (element.get("multiple").get("MANY") != null) {
				regexValue.append("*");
			}
			else if (element.get("multiple").get("PLUS") != null) {
				regexValue.append("+");
			}
		}
		return regexValue.toString();
	}
	else  {
		final StringBuilder regexValue = new StringBuilder();
		final ExternalStatement.Body regexBody;
		final String multipleValueName;
		final String stateName;
		if (element.get("multiple") != null) {
			multipleValueName = "_multiple_index_" + multipleIndex;
			stateName = "_state_" + multipleIndex;
			multipleIndex += 1;
			final String multipleValueLimit;
			if (element.get("multiple").get("OPTIONAL") != null) {
				multipleValueLimit = "1";
			}
			else  {
				multipleValueLimit = "Integer.MAX_VALUE";
			}
			regexBody = new ExternalStatement.Body();
			rule.add(/*InCl*/new ExternalStatement());
			if (element.get("multiple").get("PLUS") != null) {
				rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))))),
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(stateName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))))),
		/*Cond*/new ExternalStatement.Conditional(
			"while ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*InCl*/new ExternalStatement(regexBody))));
			}
			else  {
				if (element.get("multiple").get("OPTIONAL") != null) {
					rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(stateName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))))),
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*InCl*/new ExternalStatement(regexBody))));
				}
				else if (element.get("multiple").get("MANY") != null) {
					rule.add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))))),
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Enty*/new ExternalStatement(new StringEntry(stateName.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))))),
		/*Cond*/new ExternalStatement.Conditional(
			"while ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*InCl*/new ExternalStatement(regexBody))));
				}
			}
		}
		else  {
			regexBody = rule;
			multipleValueName = null;
			stateName = null;
		}
		if (element.get("regex_special") != null) {
			final ExternalStatement option = new ExternalStatement();
			option.set("||");
			for(IToken.Key __quark__KEY:element.get("regex_special").keySet()) {
				if(__quark__KEY.getName().equals("REGEX_NUMBER")){ final IToken quark = element.get("regex_special").get(__quark__KEY);
					final Character ch = "0".charAt(0);
					final Integer end = "9".charAt(0) - ch;
					for (Integer i = 0; i <=  end; ++i) {
					final String chValue = "'"+asChar(ch+i) + "'";
					option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(chValue.toString())))));
					}
					regexValue.append("\\\\d");
				}
				if(__quark__KEY.getName().equals("REGEX_WHITESPACE")){ final IToken quark = element.get("regex_special").get(__quark__KEY);
					option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("' '")))));
					option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\t'")))));
					option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\n'")))));
					regexValue.append("\\\\s");
				}
				if(__quark__KEY.getName().equals("REGEX_QUOTE")){ final IToken quark = element.get("regex_special").get(__quark__KEY);
					option.add(/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("'\\\"'")))));
					regexValue.append("\\\"");
				}
			}
			regexBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(option))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++_position"))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED"))))))))));
		}
		if (element.get("character") != null) {
			if (element.get("character").toString().equals(".")) {
				regexBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++_position"))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED"))))))))));
			}
			else  {
				regexBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputLength")))),
			/*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray[_position]")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("'")), /*Enty*/new ExternalStatement(new StringEntry(element.get("character").toString())))), /*Name*/new ExternalStatement(new StringEntry("'")))))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++_position"))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED"))))))))));
			}
			regexValue.append(element.get("character").toString());
		}
		else if (element.get("group") != null) {
			final String op = "(";
			final String cp = ")";
			regexValue.append(op);
			final IToken regexToken = element.get("group").get("regex");
			for(final IToken regexElement:regexToken.getAllSafely("regex_element")) {
					regexValue.append(addRegexElementToRule(regexElement,regexBody,positionName));
			}
		}
		if (element.get("multiple") != null) {
			if (element.get("multiple").get("PLUS") != null) {
				regexBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("break"))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("++")), /*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())))))))))));
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">", /*Optr*/new ExternalStatement("&&", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(stateName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0 ")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED"))))))))));
				regexValue.append("+");
			}
			else  {
				if (element.get("multiple").get("OPTIONAL") != null) {
					regexValue.append("?");
				}
				else if (element.get("multiple").get("MANY") != null) {
					regexBody.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_state")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("FAILED")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("break"))))))),
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("++")), /*Enty*/new ExternalStatement(new StringEntry(multipleValueName.toString())))))))))));
					regexValue.append("*");
				}
				rule.add(/*InCl*/new ExternalStatement(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(stateName.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS"))))))))));
			}
		}
		return regexValue.toString();
	}
}


	protected NameList NameListClass = new NameList();
	public class NameList extends ExternalClassEntry {



	protected Node NodeClass = new Node();
	public class Node extends ExternalClassEntry {
		protected final String nodeArray = "Node[]";
		protected final String nodeArrayWith128 = "new Node[128]";
		protected final String nodeArrayAtPosition = "children[newEntry.charAt(position)]";
		protected final String nodeArrayAtInputPosition = "children[input[position]]";
		protected final String nodeArrayAtFirstEntry = "children[entry.charAt(0)]";
		protected final String nodeArrayAtRemoveIndex = "children[toRemove.charAt(index)]";
		protected final String inputAccessPosition = "input[position]";



	protected Root RootClass = new Root();
	public class Root extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Root"), "class ", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Node")))), new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Root").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Set"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))), /*Name*/new ExternalStatement(new StringEntry("allEntries")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashSet"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))),new ExternalStatement.Parameters())))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(3, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("boolean"))), /*Exac*/new ExternalStatement(new StringEntry("add")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("newEntry")))}), /*Body*/new ExternalStatement.Body(
				/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("allEntries"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("newEntry")))))))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("super"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("newEntry")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))))))))),
/*BODY*/				
				/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("false"))))))))),new ExternalMethodEntry(3, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Set"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))), /*Exac*/new ExternalStatement(new StringEntry("list")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("allEntries"))))))),new ExternalMethodEntry(3, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))), /*Exac*/new ExternalStatement(new StringEntry("clear")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
				/*Cond*/new ExternalStatement.Conditional(
			"for ", 
			/*Optr*/new ExternalStatement(": ", new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("entry"))), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("allEntries"))))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtFirstEntry.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))))))),new ExternalMethodEntry(3, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))), /*Exac*/new ExternalStatement(new StringEntry("removeAll")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Node")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Root"))))), /*Name*/new ExternalStatement(new StringEntry("fromNode")))}), /*Body*/new ExternalStatement.Body(
				/*Cond*/new ExternalStatement.Conditional(
			"for ", 
			/*Optr*/new ExternalStatement(": ", new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("entry"))), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fromNode"))), /*Name*/new ExternalStatement(new StringEntry("allEntries"))))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("remove")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("entry")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))))))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("allEntries"))), /*Enty*/new ExternalStatement(new StringEntry("removeAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fromNode"))), /*Name*/new ExternalStatement(new StringEntry("allEntries"))))))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("numberOfEntries")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("allEntries"))), /*Enty*/new ExternalStatement(new StringEntry("size"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))))),new ExternalMethodEntry(3, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))), /*Exac*/new ExternalStatement(new StringEntry("addAll")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Node")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Root"))))), /*Name*/new ExternalStatement(new StringEntry("fromNode")))}), /*Body*/new ExternalStatement.Body(
				/*Cond*/new ExternalStatement.Conditional(
			"for ", 
			/*Optr*/new ExternalStatement(": ", new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("entry"))), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fromNode"))), /*Name*/new ExternalStatement(new StringEntry("allEntries"))))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("add")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("entry")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))))))))))}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}

	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Node"), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Node").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(nodeArray.toString())))), /*Name*/new ExternalStatement(new StringEntry("children")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(nodeArrayWith128.toString()))))),new ExternalVariableEntry(false, true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("numberOfEntries")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))),new ExternalVariableEntry(false, true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("value")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(2, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("boolean"))), /*Exac*/new ExternalStatement(new StringEntry("add")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("newEntry"))),
/*PARAMS*/				new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("position")))}), /*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("position")))), /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("newEntry"))), /*Enty*/new ExternalStatement(new StringEntry("length"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))),
			/*Body*/new ExternalStatement.Body(
				/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtPosition.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtPosition.toString())), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Node"))))),new ExternalStatement.Parameters())))),
/*BODY*/				
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("++numberOfEntries"))))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtPosition.toString()))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("newEntry")))),/*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1"))))))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("||", /*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("value")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))), /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("!value"))), /*Enty*/new ExternalStatement(new StringEntry("equals"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("newEntry")))))))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("value")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("newEntry")))))),
/*BODY*/				
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("true"))))))),
/*BODY*/				
				/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("false"))))))))))),new ExternalMethodEntry(2, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("get")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("position"))),
/*PARAMS*/				new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("length"))),
/*PARAMS*/				new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(charArray.toString())))), /*Name*/new ExternalStatement(new StringEntry("input")))}), /*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("length")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("value"))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtInputPosition.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("result")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtInputPosition.toString()))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("length")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("input")))))))))),
/*BODY*/				
				/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("result")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("result"))))))),
/*BODY*/				
				/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("value"))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("value"))))))),new ExternalMethodEntry(2, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Exac*/new ExternalStatement(new StringEntry("remove")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("toRemove"))),
/*PARAMS*/				new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("index")))}), /*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("index")))), /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("toRemove"))), /*Enty*/new ExternalStatement(new StringEntry("length"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))),
			/*Body*/new ExternalStatement.Body(
				/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("toRemove"))), /*Enty*/new ExternalStatement(new StringEntry("equals"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("value")))))))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("value")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))),
/*BODY*/				
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))))),
/*BODY*/				
				/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("2"))))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtRemoveIndex.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("result")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtRemoveIndex.toString()))), /*Enty*/new ExternalStatement(new StringEntry("remove"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("toRemove")))),/*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("index")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1")))))))))),
/*BODY*/				
					/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("<", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("result")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("2")))),
			/*Body*/new ExternalStatement.Body(
						/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("numberOfEntries")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1 ")))),
			/*Body*/new ExternalStatement.Body(
							/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Enty*/new ExternalStatement(new StringEntry(nodeArrayAtRemoveIndex.toString())), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))),
/*BODY*/				
							/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("--numberOfEntries"))))))),
/*BODY*/				
						/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("numberOfEntries"))))))),
/*BODY*/				
					/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
						/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("result"))))))))),
/*BODY*/				
				/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
					/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("2")))))))))))}), Arrays.asList(new ExternalClassEntry[]{ParserClass.NameListClass.NodeClass.RootClass}));
	}
}
	protected Root RootClass = new Root();
	public class Root extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Root"), "class ", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Root").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(2, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("boolean"))), /*Exac*/new ExternalStatement(new StringEntry("add")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("query")))}), /*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"synchronized ", 
			/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("this")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("super"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("query")))))))))))))}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}
	protected Child ChildClass = new Child();
	public class Child extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Child"), "class ", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Child").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList"))), /*Name*/new ExternalStatement(new StringEntry("parentList")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(2, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("get")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("position"))),
/*PARAMS*/				new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("length"))),
/*PARAMS*/				new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(charArray.toString())))), /*Name*/new ExternalStatement(new StringEntry("input")))}), /*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("result")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("super"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("length")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("input")))))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("result")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("result"))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("parentList"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("length")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("input"))))))))))))),new ExternalMethodEntry(2, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Set"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))), /*Exac*/new ExternalStatement(new StringEntry("list")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Set"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))), /*Name*/new ExternalStatement(new StringEntry("set")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashSet"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))),new ExternalStatement.Parameters())))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("set"))), /*Enty*/new ExternalStatement(new StringEntry("addAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("super"))), /*Enty*/new ExternalStatement(new StringEntry("list"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("set"))), /*Enty*/new ExternalStatement(new StringEntry("addAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("parentList"))), /*Enty*/new ExternalStatement(new StringEntry("list"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("set")))))))}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}

	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("NameList"), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("NameList").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Node")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Root"))))), /*Name*/new ExternalStatement(new StringEntry("root")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Node")))), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Root"))))),new ExternalStatement.Parameters())))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("boolean"))), /*Exac*/new ExternalStatement(new StringEntry("add")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("newEntry")))}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("root"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("newEntry"))))))))))),new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("get")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("position"))),
/*PARAMS*/				new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("length"))),
/*PARAMS*/				new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(charArray.toString())))), /*Name*/new ExternalStatement(new StringEntry("input")))}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("root"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("position")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("length")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("input"))))))))))),new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Set"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))), /*Exac*/new ExternalStatement(new StringEntry("list")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("root"))), /*Enty*/new ExternalStatement(new StringEntry("list"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))),new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))), /*Exac*/new ExternalStatement(new StringEntry("clear")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("root"))), /*Enty*/new ExternalStatement(new StringEntry("clear"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))),new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))), /*Exac*/new ExternalStatement(new StringEntry("removeAll")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList"))))), /*Name*/new ExternalStatement(new StringEntry("fromList")))}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("root"))), /*Enty*/new ExternalStatement(new StringEntry("removeAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fromList"))), /*Enty*/new ExternalStatement(new StringEntry("getRoot"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))))))),new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))), /*Exac*/new ExternalStatement(new StringEntry("addAll")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("NameList"))))), /*Name*/new ExternalStatement(new StringEntry("fromList")))}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("root"))), /*Enty*/new ExternalStatement(new StringEntry("addAll"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fromList"))), /*Enty*/new ExternalStatement(new StringEntry("getRoot"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))))))))}), Arrays.asList(new ExternalClassEntry[]{ParserClass.NameListClass.NodeClass,ParserClass.NameListClass.RootClass,ParserClass.NameListClass.ChildClass}));
	}
}
	protected Result ResultClass = new Result();
	public class Result extends ExternalClassEntry {



	protected Pass PassClass = new Pass();
	public class Pass extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Pass"), "class ", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result")), new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Pass").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Map"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))))), /*Name*/new ExternalStatement(new StringEntry("tokens")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null"))))),new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Map"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("List"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))))), /*Name*/new ExternalStatement(new StringEntry("children")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(2, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("toString")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("parseTime")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0 ")))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("Passed:".toString()))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("Passed:\\n\\t".toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("parseTime")))), /*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("ms".toString())))))))))}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}
	protected Fail FailClass = new Fail();
	public class Fail extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Fail"), "class ", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result")), new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Fail").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("ruleName")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(2, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("toString")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("Failed:\\n\\tRule:".toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("ruleName")))), /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("super"))), /*Enty*/new ExternalStatement(new StringEntry("toString"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))))}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}

	public void __INIT__(){
		super.__SETUP__(
		new Entry(){
			public void get(StringBuilder __BUILDER__){/*Enty*/new ExternalStatement(new StringEntry(packageName.toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
			}
		}, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Result"), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Result").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))), /*Name*/new ExternalStatement(new StringEntry("state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("-1"))))),new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))), /*Name*/new ExternalStatement(new StringEntry("position")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))),new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))), /*Name*/new ExternalStatement(new StringEntry("lineNumber")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))),new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("fileName")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null"))))),new ExternalVariableEntry(false, true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("long"))), /*Name*/new ExternalStatement(new StringEntry("parseTime")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("-1")))))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("toString")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("return ")), new StringEntry(";"), /*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("\\n\\tPosition: ".toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("position")))), /*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("\\n\\tFile Name: ".toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fileName")))), /*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("\\n\\tParse Time: ".toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("parseTime")))), /*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("ms".toString())))))))}), Arrays.asList(new ExternalClassEntry[]{ParserClass.ResultClass.PassClass,ParserClass.ResultClass.FailClass}));
	}
}

	public void __INIT__(){
		super.__SETUP__(
		new Entry(){
			public void get(StringBuilder __BUILDER__){/*Enty*/new ExternalStatement(new StringEntry(packageName.toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
			}
		}, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Parser"), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append(" class ");
			new StringEntry("Parser").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))), /*Name*/new ExternalStatement(new StringEntry("SUCCESS")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))),new ExternalVariableEntry(true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))), /*Name*/new ExternalStatement(new StringEntry("FAILED")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1"))))),new ExternalVariableEntry(true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("List"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))), /*Name*/new ExternalStatement(new StringEntry("fileNames")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("ArrayList"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))))),new ExternalStatement.Parameters())))}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(0, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))), /*Exac*/new ExternalStatement(new StringEntry("setup")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body()),new ExternalMethodEntry(0, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result"))))), /*Exac*/new ExternalStatement(new StringEntry("parse")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("_fileName")))}), /*Body*/new ExternalStatement.Body(
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("long"))), /*Name*/new ExternalStatement(new StringEntry("startParseTime")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("System"))), /*Enty*/new ExternalStatement(new StringEntry("currentTimeMillis"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("StringBuilder"))), /*Name*/new ExternalStatement(new StringEntry("_inputBuffer")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("StringBuilder"))),new ExternalStatement.Parameters())))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("_position")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Map"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Map"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))))), /*Name*/new ExternalStatement(new StringEntry("braceMap")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashMap"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Map"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))))),new ExternalStatement.Parameters())))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Map"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Stack"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))))), /*Name*/new ExternalStatement(new StringEntry("braceOpens")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashMap"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("String")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Stack"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))))),new ExternalStatement.Parameters())))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceMap"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("\"\"")))),/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashMap"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))),new ExternalStatement.Parameters()))))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceOpens"))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("\"\"")))),/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Stack"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))),new ExternalStatement.Parameters()))))))),
/*BODY*/				
	/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("!=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceMap")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))),
			/*InCl*/new ExternalStatement(braceVariableDeclaration)),
/*BODY*/				
	/*Cond*/new ExternalStatement.Conditional(
			"try ", null,
			/*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("BufferedReader"))), /*Name*/new ExternalStatement(new StringEntry("_inputReader")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("BufferedReader"))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("FileReader"))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_fileName")))))))))))),
/*BODY*/				
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("_readInput")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputReader"))), /*Enty*/new ExternalStatement(new StringEntry("read"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))),
/*BODY*/				
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("boolean"))), /*Name*/new ExternalStatement(new StringEntry("escaping")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("false")))))),
/*BODY*/				
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("boolean"))), /*Name*/new ExternalStatement(new StringEntry("quoting")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("false")))))),
/*BODY*/				
		/*Cond*/new ExternalStatement.Conditional(
			"while ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_readInput")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0 ")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputBuffer"))), /*Enty*/new ExternalStatement(new StringEntry("append"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("(char)_readInput"))))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("escaping")))),
			/*Body*/new ExternalStatement.Body()),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(notEscaping.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_readInput")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("'")), /*Name*/new ExternalStatement(new StringEntry("\\\\")))), /*Name*/new ExternalStatement(new StringEntry("'")))))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("escaping")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("true")))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(notQuoting.toString())))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_readInput")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("'")), /*Name*/new ExternalStatement(new StringEntry("\\\"")))), /*Name*/new ExternalStatement(new StringEntry("'")))))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("quoting")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("true")))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceOpens"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("\\\"\\\"".toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("push"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else if ", 
			/*Optr*/new ExternalStatement("==", /*Optr*/new ExternalStatement("&&", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("quoting")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_readInput")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("'")), /*Name*/new ExternalStatement(new StringEntry("\\\"")))), /*Name*/new ExternalStatement(new StringEntry("'")))))),
			/*Body*/new ExternalStatement.Body(
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("quoting")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("false")))))),
/*BODY*/				
				/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceMap"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("\\\"\\\"".toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("put"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("braceOpens"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("\\\"\\\"".toString()))))))), /*Enty*/new ExternalStatement(new StringEntry("pop"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position"))))))))))),
/*BODY*/				
			/*Cond*/new ExternalStatement.Conditional(
			"else if ", 
			/*Optr*/new ExternalStatement("&&", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(notQuoting.toString())))), /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(notEscaping.toString())))),
			/*InCl*/new ExternalStatement(readInputBody)),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_position")), /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_position")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1")))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_readInput")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputReader"))), /*Enty*/new ExternalStatement(new StringEntry("read"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))))),
/*BODY*/				
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputReader"))), /*Enty*/new ExternalStatement(new StringEntry("close"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))),
/*BODY*/				
	/*Cond*/new ExternalStatement.Conditional(
			"catch ", 
			/*Optr*/new ExternalStatement(" ", /*Exac*/new ExternalStatement(new StringEntry("IOException")), /*Exac*/new ExternalStatement(new StringEntry("e0"))),
			/*Body*/new ExternalStatement.Body(
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Exac*/new ExternalStatement(new StringEntry("e0.printStackTrace()"))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("_input")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputBuffer"))), /*Enty*/new ExternalStatement(new StringEntry("toString"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(charArray.toString())))), /*Name*/new ExternalStatement(new StringEntry("_inputArray")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_input"))), /*Enty*/new ExternalStatement(new StringEntry("toCharArray"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("_inputLength")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_inputArray"))), /*Enty*/new ExternalStatement(new StringEntry("length")))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("_fileId")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fileNames"))), /*Enty*/new ExternalStatement(new StringEntry("size"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fileNames"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("_fileName"))))))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("_position")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("_furthestPosition")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("-1")))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("_lineNumber")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1")))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))), /*Name*/new ExternalStatement(new StringEntry("_state")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("SUCCESS")))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new ExternalStatement(".", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser")), /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Result"))))), /*Name*/new ExternalStatement(new StringEntry("_result")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Name*/new ExternalStatement(new StringEntry("_list_name_result")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("null")))))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("List"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))), /*Name*/new ExternalStatement(new StringEntry("_lineNumberSizes")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("ArrayList"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))),new ExternalStatement.Parameters())))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Map"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))))), /*Name*/new ExternalStatement(new StringEntry("_tokens")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashMap"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Token"))))),new ExternalStatement.Parameters())))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Map"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("List"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))))), /*Name*/new ExternalStatement(new StringEntry("_children")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashMap"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer")),/*TypeName*/new ExternalStatement.TypeName(new StringEntry("List"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))))),new ExternalStatement.Parameters())))),
/*BODY*/				
	/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("List"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))), /*Name*/new ExternalStatement(new StringEntry("_layer")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("ArrayList"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("Integer"))))),new ExternalStatement.Parameters()))))))}), Arrays.asList(new ExternalClassEntry[]{ParserClass.NameListClass,ParserClass.ResultClass}));
	}
}
	protected Tokens TokensClass = new Tokens();
	public class Tokens extends ExternalClassEntry {



	protected Plain PlainClass = new Plain();
	public class Plain extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Plain"), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Plain").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{}), Arrays.asList(new ExternalMethodEntry[]{}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}
	protected Name NameClass = new Name();
	public class Name extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Name"), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Name").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{}), Arrays.asList(new ExternalMethodEntry[]{}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}
	protected Rule RuleClass = new Rule();
	public class Rule extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		null, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Rule"), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append("static class ");
			new StringEntry("Rule").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{}), Arrays.asList(new ExternalMethodEntry[]{}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}

	public void __INIT__(){
		super.__SETUP__(
		new Entry(){
			public void get(StringBuilder __BUILDER__){/*Enty*/new ExternalStatement(new StringEntry(packageName.toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
			}
		}, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Tokens"), "class ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append(" class ");
			new StringEntry("Tokens").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{}), Arrays.asList(new ExternalMethodEntry[]{}), Arrays.asList(new ExternalClassEntry[]{TokensClass.PlainClass,TokensClass.NameClass,TokensClass.RuleClass}));
	}
}
	protected Token TokenClass = new Token();
	public class Token extends ExternalClassEntry {




	public void __INIT__(){
		super.__SETUP__(
		new Entry(){
			public void get(StringBuilder __BUILDER__){/*Enty*/new ExternalStatement(new StringEntry(packageName.toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
			}
		}, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		}, new StringEntry("Token"), "interface ", null, new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append(" interface ");
			new StringEntry("Token").get(builder);
			new StringEntry("").get(builder);
		}
	}, Arrays.asList(new ExternalVariableEntry[]{}), Arrays.asList(new ExternalMethodEntry[]{new ExternalMethodEntry(0, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("getValue")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body()),new ExternalMethodEntry(0, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))), /*Exac*/new ExternalStatement(new StringEntry("getName")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), /*Body*/new ExternalStatement.Body())}), Arrays.asList(new ExternalClassEntry[]{}));
	}
}
	//Internals
	protected final IParser rootParser = Rules.base;
	protected final List listnames = Listnames.parser;
	protected final List rules = Rules.parser;
	protected final RegexParser lazyNameParser = CargonTokens.NAME;
	protected final ExternalStatement.Body variableDeclarations = new ExternalStatement.Body();
	protected final Set<String> variableDeclarationNames = new HashSet<String>();
	protected final String packageName = "com.rem.crg";
	protected final String charArray = "char[]";

	public static void main(final String[] args) {
		if(args.length==1) {
	new MainFlow().parse(args[0]);
		}
		else {
	System.err.println("No filename provided!");
		}
	}
	public void assignListElementNames(final ParseContext context,final IToken root) {
	}
	public void setup(final ParseContext data) {
	}
	public void generate(final ParseContext data) {
		String projectName = data.getFileName();
		final Integer dotIndex = projectName.indexOf(".");
		if (dotIndex > -1 ) {
			projectName = projectName.substring(0,dotIndex);
		}
		setup("../" + Generator.camelize(projectName.toString()) + "/src");
		ParserClass.setupCompile();
		final IToken root = data.getRoot();
		final ExternalStatement.Body vb = new ExternalStatement.Body();
		vb.add(variableDeclarations);
		ParserClass.getMethod("parse").appendToBody(vb);
		for(final IToken list:root.getAllSafely("list")) {
				ParserClass.list(list);
		}
		for(final IToken rule:root.getAllSafely("rule")) {
				ParserClass.define(rule,null);
		}
		ParserClass.output();
		output(data);
	}
	public IParser getRootParser() {
		return rootParser;
	}
	public List getListnames() {
		return listnames;
	}
	public List getRules() {
		return rules;
	}
	public RegexParser getLazyNameParser() {
		return lazyNameParser;
	}
	public ExternalStatement.Body getVariableDeclarations() {
		return variableDeclarations;
	}
	public Set<String> getVariableDeclarationNames() {
		return variableDeclarationNames;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getCharArray() {
		return charArray;
	}
	public Generator[] getGenerators() {
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
	/*Enty*/new ExternalStatement(new StringEntry(packageName.toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
	__DIRECTORY__ = new File(__ROOT_DIRECTORY__, __BUILDER__.toString().replace(".","/"));
	__DIRECTORY__.mkdirs();
	addFile(__DIRECTORY__,Generator.camelize("Parser")+".java", ParserClass);
				__BUILDER__ = new StringBuilder();
	/*Enty*/new ExternalStatement(new StringEntry(packageName.toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
	__DIRECTORY__ = new File(__ROOT_DIRECTORY__, __BUILDER__.toString().replace(".","/"));
	__DIRECTORY__.mkdirs();
	addFile(__DIRECTORY__,Generator.camelize("Tokens")+".java", TokensClass);
				__BUILDER__ = new StringBuilder();
	/*Enty*/new ExternalStatement(new StringEntry(packageName.toString())).get(__BUILDER__);
				__BUILDER__.append(".");
				/*Name*/new ExternalStatement(new StringEntry("parser")).get(__BUILDER__);
	__DIRECTORY__ = new File(__ROOT_DIRECTORY__, __BUILDER__.toString().replace(".","/"));
	__DIRECTORY__.mkdirs();
	addFile(__DIRECTORY__,Generator.camelize("Token")+".java", TokenClass);
				outputAll();
			}
			public void generateRoot(IToken rootToken){
			}
		}};
	}
	private String __ROOT_DIRECTORY__ = ".";
	public void setup(String rootDirectory){
		__ROOT_DIRECTORY__ = rootDirectory;ExternalClassEntry.suppliment("Stack", "java.util");
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
		ParserClass.__INIT__();
		TokensClass.__INIT__();
		TokenClass.__INIT__();
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