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

public class RuleGenerator extends Generator {

	private HashMap<String,List<String>> ruleParameterNames = new HashMap<String,List<String>>();
	private File directory = null;
	private String packageName = null;


	public static final Element outlineElement = new Element("outline",new String[]{"package ",/*Package Name*/";\n\n"+
			"import com.rem.parser.*;\n"+
			"import com.rem.parser.generation.*;\n"+
			"import com.rem.parser.token.*;\n"+
			"import com.rem.parser.parser.*;\n"+
			"import lists.*;\n\n"+
			"public class ",/*Class Name*/" extends ConcreteRule {\n\n"+
			"\tpublic static final IRule parser = new ",/*Class Name*/"();\n",/*Parameter Declarations*/"\n"+
			"\tpublic ",/*Class Name*/"(){\n"+
			"\t\tsuper(\"",/*Rule Name*/"\");\n"+
			"\t}\n"+
			"\t@Override\n"+
			"\tpublic void setup(){\n",/*RuleSetup*/"\n\t}\n\n}"});
	public static final Element importRulesElement = new Element("importRules",new String[]{"import ",/*Seed Name*/".rules.*;\n"});
	public static final Element ruleElementElement = new Element("ruleElement",new String[]{"\tpublic static final IRule ",/*Rule Name*/" = ",/*Class Name*/".parser;\n"});
	public static final Element setupSilenceElement = new Element("setupSilence",new String[]{"\t\tisSilent(true);\n"});
	public static final Element setupAddElement = new Element("setupAdd",new String[]{"\t\tset(",/*Rule Definition*/");\n"});
	public static final Element asElement = new Element("as",new String[]{"new AddTokenParser(",/*Inner Rule*/",",/*Token Name*/")"});
	public static final Element recontextualizationParserElement = new Element("recontextualizationParser",new String[]{"new ReContextParser(",/*Inner Rule*/",",/*Context Name*/",",/*List Name*/")"});
	public static final Element addToListElement = new Element("addToList",new String[]{"new AddTokenToListParser(",/*Inner Rule*/",",/*Token Name*/",",/*List Name*/")"});
	public static final Element addToListWithTokenElement = new Element("addToListWithToken",new String[]{"new AddTokenToListParser(",/*Inner Rule*/",",/*Token Name*/",",/*List Name*/",",/*With Name*/")"});
	public static final Element chainElement = new Element("chain",new String[]{"new ChainParser(",/*Inner Rules*/")"});
	public static final Element choiceElement = new Element("choice",new String[]{"new ChoiceParser(",/*Inner Rules*/")"});
	public static final Element multipleElement = new Element("multiple",new String[]{"new ",/*Parser Class Name*/"Parser(",/*Inner Rule*/")"});
	public static final Element listElementElement = new Element("listElement",new String[]{"",/*List Name*/".",/*Parser Name*/""});
	public static final Element rule_parserElement = new Element("rule_parser",new String[]{"",/*Name*/".parser"});
	public static final Element rule_name_parserElement = new Element("rule_name_parser",new String[]{"new ListNameElementParser(\"",/*Name*/"\")"});
	public static final Element rule_any_list_nameElement = new Element("rule_any_list_name",new String[]{"AnyListNameParser.parser"});
	public static final Element parameterMemberElement = new Element("parameterMember",new String[]{"\tprivate Parameter<",/*Type*/"> ",/*Parameter Name*/" = new Parameter<",/*Type*/">(",/*Default Value*/");\n"});
	public static final Element parameterIndexElement = new Element("parameterIndex",new String[]{"\t\tcase ",/*Index*/": return ",/*Parameter Name*/";\n"});
	public static final Element parameterGetElement = new Element("parameterGet",new String[]{"\t\tswitch(i){\n",/*Params*/"\t\tdefault: return null;\n\t\t}\n"});
	public static final Element parameterWithElement = new Element("parameterWith",new String[]{"new WithParser((IRule)",/*Parser*/",",/*Arguments*/")"});
	public static final Element parameterOperatorElement = new Element("parameterOperator",new String[]{"new Argument.",/*OperatorName*/"(",/*Left*/",",/*Right*/")"});
	public static final Element parameterNewNumberElement = new Element("parameterNewNumber",new String[]{"new Argument.Number(",/*Value*/")"});
	public static final Element parameterExistingElement = new Element("parameterExisting",new String[]{"this.",/*Parameter Name*/""});
	public RuleGenerator(){
		addElement("outline",outlineElement);
		addElement("importRules",importRulesElement);
		addElement("ruleElement",ruleElementElement);
		addElement("setupSilence",setupSilenceElement);
		addElement("setupAdd",setupAddElement);
		addElement("as",asElement);
		addElement("recontextualizationParser",recontextualizationParserElement);
		addElement("addToList",addToListElement);
		addElement("addToListWithToken",addToListWithTokenElement);
		addElement("chain",chainElement);
		addElement("choice",choiceElement);
		addElement("multiple",multipleElement);
		addElement("listElement",listElementElement);
		addElement("rule_parser",rule_parserElement);
		addElement("rule_name_parser",rule_name_parserElement);
		addElement("rule_any_list_name",rule_any_list_nameElement);
		addElement("parameterMember",parameterMemberElement);
		addElement("parameterIndex",parameterIndexElement);
		addElement("parameterGet",parameterGetElement);
		addElement("parameterWith",parameterWithElement);
		addElement("parameterOperator",parameterOperatorElement);
		addElement("parameterNewNumber",parameterNewNumberElement);
		addElement("parameterExisting",parameterExistingElement);
	}
	public void setup(ParseContext data){
		this.addPage();
		packageName = Generators.rule.buildString(Generators.base.getSeedName(),".rules");
		directory = new File(Generators.base.getDirectory(),packageName.replace(".","/"));
		directory.mkdirs();
	}
	public void generate(ParseContext data){
		ParseList rules = (ParseList)data.getList("rules");
		Generators.rule.generateAll(rules.getNewTokens(),"rule");
	}
	public void generateRoot(IToken root){
		String ruleName = root.get("rulename").getString();
		String className = Generators.rule.camelize(ruleName);
		String fileName = Generators.rule.buildString(className,".java");
		Generators.list.addClassList("rulenames",ruleName,ruleName,null);
		Entry rulenames = new ElementEntry(RuleGenerator.ruleElementElement,new ListEntry(new StringEntry(ruleName),new StringEntry(className)));
		String seedName = Generators.base.getSeedName();
		Generators.list.addList(new ListEntry(new ElementEntry(RuleGenerator.importRulesElement,new ListEntry(new ListEntry(new StringEntry(seedName))))),"rules",ruleName,rulenames);
		ListEntry param_declarations = new ListEntry();
		param_declarations.setDelimiter("");
		ListEntry parameters = new ListEntry(new ListEntry(new StringEntry(packageName)),new ListEntry(new StringEntry(className)),new ListEntry(new StringEntry(className)),param_declarations,new ListEntry(new StringEntry(className)),new ListEntry(new StringEntry(ruleName)));
		Generators.rule.addFile(Generators.rule.getDirectory(),fileName,parameters);
		IToken silence = root.get("silence");
		Boolean isSilent = (silence != null && !silence.isEmpty());
		Generators.rule.println(">",ruleName);
		ListEntry parameterIndexEntries = new ListEntry();
		parameterIndexEntries.setDelimiter("");
		Integer param_count = 0;
		for(IToken.Key branchKey:root.keySet()){
			if("definition".equals(branchKey.getName())){
				IToken branch = root.get(branchKey);
				ListEntry ruleEntry = new ListEntry();
				ruleEntry.setDelimiter("");
				if((isSilent == true)){
					ruleEntry.add(new ElementEntry(RuleGenerator.setupSilenceElement,new ListEntry()));
				}
				ruleEntry.add(new ElementEntry(RuleGenerator.setupAddElement,new ListEntry(generateDefinition(branch,ruleName,3))));
				Generators.rule.addEntry(Generators.rule.getDirectory(),fileName,"rule",ruleEntry);
			}
			else if("rule_param".equals(branchKey.getName())){
				IToken branch = root.get(branchKey);
				String rule_param = branch.getString();
				Boolean rules_contains = ruleParameterNames.containsKey(ruleName);
				if((!rules_contains)){
					ruleParameterNames.put(ruleName,new ArrayList<String>());
				}
				List<String> ruleParameterNamesList = (List<String>)ruleParameterNames.get(ruleName);
				ruleParameterNamesList.add(rule_param);
				param_declarations.add(new ElementEntry(RuleGenerator.parameterMemberElement,new ListEntry(new StringEntry("Integer"),new StringEntry(rule_param),new StringEntry("Integer"),new StringEntry("0"))));
				parameterIndexEntries.add(new ElementEntry(RuleGenerator.parameterIndexElement,new ListEntry(new StringEntry(param_count.toString()),new StringEntry(rule_param))));
				param_count = param_count + 1;
			}
		}
		Generators.rule.addEntry(Generators.rule.getDirectory(),fileName,"params",new ElementEntry(RuleGenerator.parameterGetElement,new ListEntry(parameterIndexEntries)));
	}
	public Entry generateAtom(IToken atom,String ruleName,Integer tabs){
		Entry returnEntry = null;
		QuoteEntry enclosingName = null;
		QuoteEntry enclosingList = null;
		QuoteEntry enclosingTokenName = null;
		QuoteEntry enclosingFromName = null;
		for(IToken.Key quarkKey:atom.keySet()){
			if("parameters".equals(quarkKey.getName())){
				IToken quark = atom.get(quarkKey);
				IToken name = quark.get("name");
				IToken listVar = quark.get("list");
				IToken withToken = quark.get("tokenName");
				IToken fromToken = quark.get("contextName");
				if((name != null)){
					enclosingName = new QuoteEntry(name.getString());
				}
				if((listVar != null)){
					enclosingList = new QuoteEntry(listVar.getString());
				}
				if((withToken != null)){
					enclosingTokenName = new QuoteEntry(withToken.getString());
				}
				if((fromToken != null)){
					enclosingFromName = new QuoteEntry(fromToken.getString());
				}
			}
			else if("terminal".equals(quarkKey.getName())){
				IToken quark = atom.get(quarkKey);
				returnEntry = generateTerminal(quark);
			}
			else if("braced".equals(quarkKey.getName())){
				IToken quark = atom.get(quarkKey);
				List<IToken> energyDefinition = quark.getAll("definition");
				if(energyDefinition != null){
					for(IToken energy:energyDefinition){
						returnEntry = generateDefinition(energy,ruleName,tabs);
					}
				}
			}
			else if("multiple".equals(quarkKey.getName())){
				IToken quark = atom.get(quarkKey);
				String option = quark.get("option").getString();
				if(option.equals("*")){
					option = "Many";
				}
				else if(option.equals("?")){
					option = "Optional";
				}
				else if(option.equals("+")){
					option = "Multiple";
				}
				List<IToken> energyDefinition = quark.getAll("definition");
				if(energyDefinition != null){
					for(IToken energy:energyDefinition){
						returnEntry = new ElementEntry(RuleGenerator.multipleElement,new ListEntry(new ListEntry(new StringEntry(option)),generateDefinition(energy,ruleName,tabs)));
					}
				}
			}
		}
		if((enclosingFromName != null)){
			returnEntry = new ElementEntry(RuleGenerator.recontextualizationParserElement,new ListEntry(new TabEntry(tabs + 1,new ListEntry(returnEntry)),enclosingFromName,enclosingList));
		}
		else {
			if((enclosingName != null)){
				if((enclosingList != null)){
					if((enclosingTokenName != null)){
						returnEntry = new ElementEntry(RuleGenerator.addToListWithTokenElement,new ListEntry(new TabEntry(tabs + 1,new ListEntry(returnEntry)),enclosingName,enclosingList,enclosingTokenName));
					}
					else {
						returnEntry = new ElementEntry(RuleGenerator.addToListElement,new ListEntry(new TabEntry(tabs + 1,new ListEntry(returnEntry)),enclosingName,enclosingList));
					}
				}
				else {
					returnEntry = new ElementEntry(RuleGenerator.asElement,new ListEntry(new TabEntry(tabs + 1,new ListEntry(returnEntry)),enclosingName));
				}
			}
			else {
				if((enclosingList != null)){
					if((enclosingTokenName != null)){
						returnEntry = new ElementEntry(RuleGenerator.addToListWithTokenElement,new ListEntry(new TabEntry(tabs + 1,new ListEntry(returnEntry)),new ListEntry(new StringEntry("null")),enclosingList,enclosingTokenName));
					}
					else {
						returnEntry = new ElementEntry(RuleGenerator.addToListElement,new ListEntry(new TabEntry(tabs + 1,new ListEntry(returnEntry)),new ListEntry(new StringEntry("null")),enclosingList));
					}
				}
			}
		}
		ListEntry enclosingArgumentsStatement = new ListEntry();
		List<IToken> energyParameter = atom.getAll("parameter");
		if(energyParameter != null){
			for(IToken energy:energyParameter){
				enclosingArgumentsStatement.add(generateParameter(energy));
			}
		}
		if((!enclosingArgumentsStatement.isEmpty())){
			returnEntry = new ElementEntry(RuleGenerator.parameterWithElement,new ListEntry(returnEntry,enclosingArgumentsStatement));
		}
		return new TabEntry(tabs,new ListEntry(returnEntry));
	}
	public Entry generateParameter(IToken parameter){
		for(IToken.Key pKey:parameter.keySet()){
			if("definition".equals(pKey.getName())){
				IToken p = parameter.get(pKey);
				System.err.println("Rules cannot have definitons as their parameter");
			}
			else if("arithmatic".equals(pKey.getName())){
				IToken p = parameter.get(pKey);
				return generateArithmatic(p);
			}
		}
		return null;
	}
	public Entry generateArithmatic(IToken arithmatic){
		Entry left = null;
		String operand = null;
		Entry right = null;
		for(IToken.Key elementKey:arithmatic.keySet()){
			if("arithmatic".equals(elementKey.getName())){
				IToken element = arithmatic.get(elementKey);
				if((left == null)){
					left = generateArithmatic(element);
				}
				else {
					right = generateArithmatic(element);
				}
			}
			else if("operand".equals(elementKey.getName())){
				IToken element = arithmatic.get(elementKey);
				operand = element.getString();
				if(operand.equals("*")){
					operand = "Multiply";
				}
				else if(operand.equals("/")){
					operand = "Divide";
				}
				else if(operand.equals("+")){
					operand = "Add";
				}
				else if(operand.equals("-")){
					operand = "Subtract";
				}
				else {
					System.err.println("Operand:"+operand+" not recognized.");
				}
			}
			else if("NUMBER".equals(elementKey.getName())){
				IToken element = arithmatic.get(elementKey);
				if((left == null)){
					left = new ElementEntry(RuleGenerator.parameterNewNumberElement,new ListEntry(new ListEntry(new StringEntry(element.getString()))));
				}
				else {
					right = new ElementEntry(RuleGenerator.parameterNewNumberElement,new ListEntry(new ListEntry(new StringEntry(element.getString()))));
				}
			}
			else if("rule_parameters".equals(elementKey.getName())){
				IToken element = arithmatic.get(elementKey);
				if((left == null)){
					left = new ElementEntry(RuleGenerator.parameterExistingElement,new ListEntry(new ListEntry(new StringEntry(element.getString()))));
				}
				else {
					right = new ElementEntry(RuleGenerator.parameterExistingElement,new ListEntry(new ListEntry(new StringEntry(element.getString()))));
				}
			}
		}
		if((right == null)){
			return left;
		}
		else {
			return new ElementEntry(RuleGenerator.parameterOperatorElement,new ListEntry(new ListEntry(new StringEntry(operand)),left,right));
		}
	}
	public Entry generateDefinition(IToken definition,String ruleName,Integer tabs){
		return generateDefinition2(definition,ruleName,tabs,new ListEntry());
	}
	public Entry generateDefinition2(IToken definition2,String ruleName,Integer tabs,ListEntry entries){
		for(IToken.Key elementKey:definition2.keySet()){
			if("choice".equals(elementKey.getName())){
				IToken element = definition2.get(elementKey);
				for(IToken.Key choiceKey:element.keySet()){
					if("definition".equals(choiceKey.getName())){
						IToken choice = element.get(choiceKey);
						ListEntry nextChoices = new ListEntry();
						generateDefinition2(choice,ruleName,tabs,nextChoices);
						entries.addAll(nextChoices);
					}
				}
			}
			else if("chain".equals(elementKey.getName())){
				IToken element = definition2.get(elementKey);
				ListEntry chain = new ListEntry();
				List<IToken> quarkAtom = element.getAll("atom");
				if(quarkAtom != null){
					for(IToken quark:quarkAtom){
						chain.add(generateAtom(quark,ruleName,tabs + 2));
					}
				}
				if((chain.isSingular())){
					entries.add(chain.getSingle());
				}
				else {
					entries.add(new TabEntry(tabs + 1,new ListEntry(new ElementEntry(RuleGenerator.chainElement,new ListEntry(chain)))));
				}
			}
		}
		if((entries.isSingular())){
			return entries.getSingle();
		}
		else {
			return new TabEntry(tabs,new ListEntry(new ElementEntry(RuleGenerator.choiceElement,new ListEntry(entries))));
		}
	}
	public Entry generateTerminal(IToken terminal){
		for(IToken.Key tokenKey:terminal.keySet()){
			if("ruleToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				String ruleName = Generators.rule.camelize(token.getString());
				return new ElementEntry(RuleGenerator.rule_parserElement,new ListEntry(new ListEntry(new StringEntry(ruleName))));
			}
			else if("listsToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				String listName = Generators.rule.camelize(token.getString());
				if((listName.equals("Listnames"))){
					return new ListEntry(new StringEntry("ListNameParser.parser"));
				}
				else {
					return new ElementEntry(RuleGenerator.rule_parserElement,new ListEntry(new ListEntry(new StringEntry(listName))));
				}
			}
			else if("listToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				String listName = Generators.rule.camelize(token.getString());
				for(IToken.Key atomKey:token.keySet()){
					IToken atom = token.get(atomKey);
					listName = atomKey.getName();
				}
				if((listName.equals("listnames"))){
					String name = Generators.rule.buildString(token.getString(),"s");
					return new ElementEntry(RuleGenerator.rule_name_parserElement,new ListEntry(new ListEntry(new StringEntry(name))));
				}
				else {
					String name = Generators.rule.camelize(listName);

					return new ElementEntry(RuleGenerator.listElementElement,new ListEntry(new StringEntry(name),new StringEntry(token.getString())));
				}
			}
			else if("anyListNameToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				return new ElementEntry(RuleGenerator.rule_any_list_nameElement,new ListEntry());
			}
			else if("token".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				return new ElementEntry(RuleGenerator.listElementElement,new ListEntry(new StringEntry("Tokens"),new StringEntry(token.getString())));
			}
			else if("braceToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				return new ElementEntry(RuleGenerator.listElementElement,new ListEntry(new StringEntry("Braces"),new StringEntry(token.getString())));
			}
		}
		return null;
	}

	public HashMap<String,List<String>> getRuleParameterNames(){
		return ruleParameterNames;
	}

	public File getDirectory(){
		return directory;
	}

	public String getPackageName(){
		return packageName;
	}

	public String getName(){
		return "Rule";
	}

	public IParser getLazyNameParser(){
		return null;
	}
}