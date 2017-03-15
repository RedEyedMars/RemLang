package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import lists.*;

public class BaseGenerator extends Generator {

	private File directory = (File)null;
	private IParser lazyNameParser = (IParser)Tokens.LISTNAME;

	public BaseGenerator(){
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
	public void generate(ParseData data){
		String fileName = data.getFileName();
		Integer indexOfDot = fileName.lastIndexOf(".");
		if(new Boolean(indexOfDot > -1)){
			fileName = fileName.substring(0,indexOfDot);
		}
		directory = new File(Generators.base.buildString("../Generated",Generators.base.camelize(fileName),"/src/base/"));
		directory.mkdirs();
		Generators.list.generateAll(data.getList("list_rules").getNewTokens(),"list_rule");
		Generators.rule.generateAll(data);
		Generators.base.outputAll();
	}
	public void assignListElementNames(Map<String,ParseList> listMapObj,IToken root){
		IToken r = (IToken)root;
		Map<String,ParseList> listMap = (Map<String,ParseList>)listMapObj;
		ArrayList<IParser> listNameChoices = new ArrayList<IParser>();
		ParseList listnames = (ParseList)listMap.get("listnames");
		NameParser listNamesNameParser = (NameParser)listnames.getNamesParser();
		listNamesNameParser.clear();
		ParseList list_rules = (ParseList)listMap.get("list_rules");
		IToken new_list_rules = list_rules.getNewTokens();
		for(IToken.Key new_list_defKey:new_list_rules.keySet()){
			IToken new_list_def = new_list_rules.get(new_list_defKey);
			String listName = new_list_def.get("listname").getString();
			listName = listName.replaceAll("[ \\t]+","");
			String listSingle = listName;
			Integer indexOfDash = listName.indexOf("-");
			if(new Boolean(indexOfDash > -1)){
				ParseList oldList = (ParseList)null;
				Boolean listMapContainsKey = listMap.containsKey(listName);
				if(new Boolean(listMapContainsKey == true)){
					oldList = listMap.remove(listName);
				}
				listSingle = listName.substring(0,indexOfDash);
				listName = Generators.base.buildString(listSingle,listName.substring(indexOfDash + 1,listName.length()));
				if(new Boolean(oldList != null)){
					listMap.put(listName,oldList);
				}
			}
			Boolean listMapContainsKey = listMap.containsKey(listName);
			if(new Boolean(!listMapContainsKey)){
				listMap.put(listName,Generators.base.createNewParseList(listName));
			}
			listNameChoices.add(new RegexParser(listSingle,"listnames",listName));
			NameParser listnamesNameParser = (NameParser)listnames.getNamesParser();
			listnamesNameParser.addName(listSingle);
			ParseList listVar = (ParseList)listMap.get(listName);
			NameParser listNameParser = (NameParser)listVar.getNamesParser();
			List<IToken> defListDef = new_list_def.getAll("list_def");
			if(defListDef != null){
				for(IToken def:defListDef){
					String name = def.get("parameters").get("name").getString();
					listNameParser.addName(name);
				}
			}
		}
		listNameChoices.add(new RegexParser("listname","listnames","listnames"));
		NameParser lisnamesNameParser = (NameParser)listnames.getNamesParser();
		lisnamesNameParser.addName("listname");
		ChoiceParser listNamesParser = (ChoiceParser)Listnames.parser;
		listNamesParser.replace(listNameChoices);
		ParseList listRuless = (ParseList)listMap.get("list_rules");
		NameParser listRulessNamesParser = (NameParser)listRuless.getNamesParser();
		listRulessNamesParser.clear();
	}

	public File getDirectory(){
		return directory;
	}

	public IParser getLazyNameParser(){
		return lazyNameParser;
	}

	public String getName(){
		return "Base";
	}

	public void generateRoot(IToken root){
	}
}