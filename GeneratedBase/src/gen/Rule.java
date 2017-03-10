package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;

public class Rule extends Generator {

	private HashMap ruleParameterNames = new HashMap<String,List<String>>();
	private File directory = new File(new Base().getDirectory(),"rules");

	public Rule(){
		addElement("defaultLazyParser",new String[]{"Tokens.LISTNAME"});

		addElement("outline",new String[]{"package base.rules;\n\n"+
			"import com.rem.parser.*;\n"+
			"import base.lists.*;\n\n"+
			"public class ",/*Class Name*/" extends ConcreteRule {\n\n"+
			"\tpublic static final IRule parser = new ",/*Class Name*/"();\n",/*Parameter Declarations*/"\tprivate Parameter<?>[] parameters = new Parameter<?>[]{",/*Parameter List*/"};\n"+
			"\tpublic ",/*Class Name*/"(){\n"+
			"\t\tsuper(\"",/*Rule Name*/"\");\n"+
			"\t}\n"+
			"\t@Override\n"+
			"\tpublic void setup(){\n",/*RuleSetup*/"\n\t}\n"+
			"\t@Override @SuppressWarnings(\"unchecked\")\n"+
			"\tpublic Parameter<?>[] getParameters(){\n"+
			"\t\treturn parameters;\n\t}\n\n}"});

		addElement("ruleElement",new String[]{"\tpublic static final IRule ",/*Rule Name*/" = ",/*Class Name*/".parser;\n"});

		addElement("setupSilence",new String[]{"\t\tisSilent(true);\n"});

		addElement("setupAdd",new String[]{"\t\tset(",/*Rule Definition*/");\n"});

		addElement("as",new String[]{"new AddTokenParser(",/*Inner Rule*/",",/*Token Name*/")"});

		addElement("addToList",new String[]{"new AddTokenToListParser(",/*Inner Rule*/",",/*Token Name*/",",/*List Name*/")"});

		addElement("chain",new String[]{"new ChainParser(",/*Inner Rules*/")"});

		addElement("choice",new String[]{"new ChoiceParser(",/*Inner Rules*/")"});

		addElement("multiple",new String[]{"new ",/*Parser Class Name*/"Parser(",/*Inner Rule*/")"});

		addElement("listElement",new String[]{"",/*List Name*/".",/*Parser Name*/""});

		addElement("rule_parser",new String[]{"",/*Name*/".parser"});

		addElement("rule_name_parser",new String[]{"new ListNameParser(\"",/*Name*/"\")"});

		addElement("rule_any_list_name",new String[]{"AnyListNameParser.parser"});

		addElement("parameterMember",new String[]{"\tprivate Parameter<",/*Type*/"> ",/*Parameter Name*/" = new Parameter<",/*Type*/">(",/*Default Value*/");\n"});

		addElement("parameterIndex",new String[]{"\t\tcase ",/*Index*/": return ",/*Parameter Name*/";\n"});

		addElement("parameterGet",new String[]{"\t\tswitch(i){\n",/*Params*/"\t\tdefault: return null;\n\t\t}\n"});

		addElement("parameterWith",new String[]{"new WithParser((IRule)",/*Parser*/",",/*Arguments*/")"});

		addElement("parameterOperator",new String[]{"new Argument.",/*OperatorName*/"(",/*Left*/",",/*Right*/")"});

		addElement("parameterNewNumber",new String[]{"new Parameter<Integer>(",/*Value*/")"});

		addElement("parameterExisting",new String[]{"this.",/*Parameter Name*/""});

		addElement("outline",new String[]{"package base.lists;\n\n"+
			"import com.rem.parser.*;\n",/*Other Imports*/"\n"+
			"public class ",/*Class Name*/" extends ParseList {\n\n"+
			"\t@Override\n"+
			"\tpublic String getName() {\n"+
			"\t\treturn \"",/*Plural Name*/"\";\n"+
			"\t}\n"+
			"\t@Override\n"+
			"\tpublic String getSingular() {\n"+
			"\t\treturn \"",/*Singular Name*/"\";\n"+
			"\t}\n\n",/*Entries*/"}"});

		addElement("importRules",new String[]{"import base.rules.*;\n"});

		addElement("class",new String[]{"\tpublic static final ",/*Class*/"Parser ",/*Name*/" = new ",/*Class*/"Parser(",/*Parameters*/");\n"});

		addElement("parser",new String[]{"\n\tpublic static final ChoiceParser parser = new ChoiceParser(\n\t\t\t\t",/*Rules*/");\n"});

		addElement("name_parser",new String[]{"\n\tpublic static final NameParser name_parser = new NameParser(\n\t\t\t\t",/*Rules*/");\n"+
			"\t@Override\n"+
			"\tpublic NameParser getNamesParser(){\n"+
			"\t\treturn name_parser;\n"+
			"\t}\n"});

		addElement("emptyList",new String[]{"\tpublic static final NameParser name_parser = new NameParser(",/*List Name*/");\n"+
			"\t@Override\n"+
			"\tpublic NameParser getNamesParser(){\n"+
			"\t\treturn name_parser;\n"+
			"\t}\n"});
	}
	public Entry generateRoot(IToken root){
		directory.mkdirs();
		String ruleName = root.get(rulename).toString();
		String className = new Base().camelize(ruleName);
		String fileName = new Base().buildString(className,".java");
		new List().addClassList("rulenames",ruleName,ruleName,null);
		Entry rulenames = new ElementEntry("ruleElement",new ListEntry(new StringEntry(ruleName),new StringEntry(className)));
		new List().addList(new ListEntry(new ElementEntry("importRules",new ListEntry())),"rules",ruleName,rulenames);
		ListEntry param_list = new ListEntry();
		param_list.setDelimiter("");
		Entry parameters = new ListEntry(new ListEntry(new StringEntry(className)),new ListEntry(new StringEntry(className)),param_list,new ListEntry(new StringEntry(className)),new ListEntry(new StringEntry(ruleName)));
		new Rule().addFile(new Rule().getName(),new Rule().getDirectory(),fileName,parameters);
		String silence = root.get(silence);
		Boolean isSilent = new Boolean(!silence.equals(null) && !silence.isEmpty());
		new Base().println(new Base().buildString(">",ruleName));
		ListEntry parameterIndexEntries = new ListEntry();
		parameterIndexEntries.setDelimiter("");
		Integer param_count = 0;
		for(IToken.Key branchKey:root.keySet()){
			if("definition".equals(branchKey.getName())){
				IToken branch = root.get(branchKey);
				ListEntry ruleEntry = new ListEntry();
				ruleEntry.setDelimiter("");
				if(new Boolean(isSilent.equals(true))){
					ruleEntry.add(new ElementEntry("setupSilence",new ListEntry()));
				}
				ruleEntry.add(new ElementEntry("setupAdd",new ListEntry(generateDefinition(IToken branch = root.get(branchKey);,ruleName,3))));
				new Rule().addEntry(new Rule().getDirectory(),fileName,"rule",ruleEntry);
			}
			else if("rule_param".equals(branchKey.getName())){
				IToken branch = root.get(branchKey);
				String rule_param = branch.getString();
				String rules_contains = ruleParameterNames.containsKey(ruleName);
				if(new Boolean(rules_contains.equals(true))){
					ruleParameterNames.put(ruleName,new ArrayList<String>());
				}
				String ruleParameterNamesList = ruleParameterNames.get(ruleName);
				ruleParameterNamesList.add(rule_param);
				param_list.add(new ElementEntry("parameterMember",new ListEntry(new StringEntry("Integer"),new StringEntry(rule_param),new StringEntry("Integer"),new StringEntry("0"))));
				parameterIndexEntries.add(new ElementEntry("parameterIndex",new ListEntry(new StringEntry(param_count.toString()),new StringEntry(rule_param))));
				new Base().increment(param_count);
			}
		}
		new Rule().addEntry(new Rule().getDirectory(),fileName,"params",new ElementEntry("parameterGet",new ListEntry(parameterIndexEntries)));
	}
	public Entry generateAtom(IToken atom,String ruleName,String tabs){
		Entry returnEntry = null;
		String enclosingName = null;
		String enclosingList = null;
		 enclosingArgumentsStatement = null;
		for(IToken.Key quarkKey:atom.keySet()){
			if("parameters".equals(quarkKey.getName())){
				IToken quark = atom.get(quarkKey);
				String name = quark.get(name).toString();
				String listVar = quark.get(list).toString();
				if(new Boolean(!name.equals(null))){
					enclosingName = new Rule().buildString("\"",name,"\"");
				}
				if(new Boolean(!listVar.equals(null))){
					enclosingList = new Rule().buildString("\"",listVar,"\"");
				}
				Entry params = new ListEntry();
				List<IToken> energyParameter = quark.getAll("Parameter");
				if(energyParameter != null){
					for(IToken energy:energyParameter){
						params.add(generateParameter(IToken energy));
					}
				}
				if(new Boolean(!params.isEmpty())){
					enclosingArgumentsStatement = params;
				}
			}
			else if("terminal".equals(quarkKey.getName())){
				IToken quark = atom.get(quarkKey);
				returnEntry = generateToken(IToken quark = atom.get(quarkKey););
			}
			else if("braced".equals(quarkKey.getName())){
				IToken quark = atom.get(quarkKey);
				List<IToken> energyDefinition = quark.getAll("Definition");
				if(energyDefinition != null){
					for(IToken energy:energyDefinition){
						returnEntry = generateDefinition(IToken energy,ruleName,tabs);
					}
				}
			}
			else if("multiple".equals(quarkKey.getName())){
				IToken quark = atom.get(quarkKey);
				String option = quark.get(option).toString();
				if(option.equals("*")){
					option = "Many";
				}
				else if(option.equals("?")){
					option = "Optional";
				}
				else if(option.equals("+")){
					option = "Multiple";
				}
				List<IToken> energyDefinition = quark.getAll("Definition");
				if(energyDefinition != null){
					for(IToken energy:energyDefinition){
						returnEntry = new ElementEntry("multiple",new ListEntry(new ListEntry(new StringEntry(option)),generateDefinition(IToken energy,ruleName,tabs)));
					}
				}
			}
		}
		if(new Boolean(!enclosingName.equals(null))){
			if(new Boolean(!enclosingList.equals(null))){
				returnEntry = new ElementEntry("addToList",new ListEntry(new TabEntry(tabs + 1,new ListEntry(returnEntry)),new ListEntry(new StringEntry(enclosingName)),new ListEntry(new StringEntry(enclosingList))));
			}
			else {
				returnEntry = new ElementEntry("as",new ListEntry(new TabEntry(tabs + 1,new ListEntry(returnEntry)),new ListEntry(new StringEntry(enclosingName))));
			}
		}
		if(new Boolean(!enclosingArgumentsStatement.equals(null))){
			returnEntry = new ElementEntry("parameterWith",new ListEntry(returnEntry,enclosingArgumentsStatement));
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
				return generateArithmatic(IToken p = parameter.get(pKey););
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
				if(new Boolean(left.equals(null))){
					left = generateArithmatic(IToken element = arithmatic.get(elementKey););
				}
				else {
					right = generateArithmatic(IToken element = arithmatic.get(elementKey););
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
			else {
				IToken element = arithmatic.get(elementKey);
				String elementName = element.name();
				if(new Boolean(left.equals(null))){
					left = generateArithmaticTerminal(IToken element = arithmatic.get(elementKey);,elementName);
				}
				else {
					right = generateArithmaticTerminal(IToken element = arithmatic.get(elementKey);,elementName);
				}
			}
		}
		if(new Boolean(right.equals(null))){
			return left;
		}
		else {
			return new ElementEntry("parameterOperator",new ListEntry(new ListEntry(new StringEntry(operand)),left,right));
		}
	}
	public Entry generateArithmaticTerminal(IToken arithmaticTerminal,String name){
		if(new Boolean("NUMBER".equals(name))){
			return new ElementEntry("parameterNewNumber",new ListEntry(new ListEntry(new StringEntry(arithmaticTerminal.getString()))));
		}
		if(new Boolean("rule_parameters".equals(name))){
			return new ElementEntry("parameterExisting",new ListEntry(new ListEntry(new StringEntry(arithmaticTerminal.getString()))));
		}
		System.err.println(name+" not a recognized Arithmatic Terminal");
		return null;
	}
	public Entry generateDefinition(IToken definition,String ruleName,String tabs){
		return generateDefinition(IToken definition,ruleName,tabs,new ListEntry());
	}
	public Entry generateDefinition(IToken definition,String ruleName,String tabs,String entries){
		for(IToken.Key elementKey:definition.keySet()){
			if("choice".equals(elementKey.getName())){
				IToken element = definition.get(elementKey);
				for(IToken.Key choiceKey:element.keySet()){
					if("definition".equals(choiceKey.getName())){
						IToken choice = element.get(choiceKey);
						Entry nextChoices = new ListEntry();
						generateDefinition(IToken choice = element.get(choiceKey);,ruleName,tabs,nextChoices);
						entries.addAll(nextChoices);
					}
				}
			}
			else if("chain".equals(elementKey.getName())){
				IToken element = definition.get(elementKey);
				Entry chain = new ListEntry();
				List<IToken> atomAtom = element.getAll("Atom");
				if(atomAtom != null){
					for(IToken atom:atomAtom){
						chain.add(generateAtom(IToken atom,ruleName,tabs + 2));
					}
				}
				if(new Boolean(chain.isSingular())){
					entries.add(chain.single());
				}
				else {
					entries.add(new TabEntry(tabs + 1,new ListEntry(new ElementEntry("chain",new ListEntry(chain)))));
				}
			}
		}
		if(new Boolean(entries.isSingular())){
			return entries.single();
		}
		else {
			return new TabEntry(tabs,new ListEntry(new ElementEntry("choice",new ListEntry(entries))));
		}
	}
	public Entry generateTerminal(IToken terminal){
		for(IToken.Key tokenKey:terminal.keySet()){
			if("ruleToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				String ruleName = new Rule().camelize(token.getString());
				return new ElementEntry("rule_parser",new ListEntry(new ListEntry(new StringEntry(ruleName))));
			}
			else if("listsToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				String listName = new Rule().camelize(token.getString());
				return new ElementEntry("rule_parser",new ListEntry(new ListEntry(new StringEntry(listName))));
			}
			else if("listToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				String listName = new Rule().camelize(token.getString());
				List<IToken> atom* = token.getAll("*");
				if(atom* != null){
					for(IToken atom:atom*){
						listName = atom.name();
					}
				}
				if(new Boolean(listName.equals("listnames"))){
					String name = new Rule().buildString(token.getString(),"s");

					return new ElementEntry("rule_name_parser",new ListEntry(new ListEntry(new StringEntry(name))));
				}
				else {
					String name = new Rule().buildString(token.getString(),"s");

					return new ElementEntry("listElement",new ListEntry(new StringEntry(name),new StringEntry(token.getString())));
				}
			}
			else if("anyListNameToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				return new ElementEntry("rule_any_list_name",new ListEntry());
			}
			else if("token".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				return new ElementEntry("listElement",new ListEntry(new StringEntry("Tokens"),new StringEntry(token.getString())));
			}
			else if("braceToken".equals(tokenKey.getName())){
				IToken token = terminal.get(tokenKey);
				return new ElementEntry("listElement",new ListEntry(new StringEntry("Braces"),new StringEntry(token.getString())));
			}
		}
		return null;
	}
}