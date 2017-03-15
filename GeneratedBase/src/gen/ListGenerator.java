package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import lists.*;

public class ListGenerator extends Generator {

	private HashMap<String,String> listAssociatedClass = new HashMap<String,String>();
	private File directory = new File(Generators.base.getDirectory(),"lists");

	public ListGenerator(){
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
	public void generateRoot(IToken root){
		directory.mkdir();
		String listName = root.get("listname").getString();
		Integer indexOfDash = listName.indexOf("-");
		String singleListName = listName;
		if(new Boolean(indexOfDash > -1)){
			singleListName = listName.substring(0,indexOfDash);
			listName = Generators.list.buildString(singleListName,listName.substring(indexOfDash + 1,listName.length()));
		}
		ListEntry fileParameters = new ListEntry(new ListEntry(),new ListEntry(new StringEntry("Listnames")),new ListEntry(new StringEntry("listnames")),new ListEntry(new StringEntry("listname")));
		Generators.list.addFile(directory,"Listnames.java",fileParameters);
		Generators.list.addClassList("listnames",singleListName,listName,null);
		Boolean token_contains_list = new Boolean(root.containsKey("listType"));
		if(new Boolean(token_contains_list == true)){
			listAssociatedClass.put(listName,Generators.list.camelize(root.get("listType").getString()));
		}
		String className = Generators.list.camelize(listName);
		String fileName = Generators.list.buildString(className,".java");
		Boolean hasDefinition = false;
		for(IToken.Key elementKey:root.keySet()){
			if("list_def".equals(elementKey.getName())){
				IToken element = root.get(elementKey);
				System.out.println(tokenErrorMessage(element));
				IToken parameters = element.get("parameters");
				String name = parameters.get("name").getString();
				String regex = element.get("regex").getString();
				IToken parameterToken = element.get("parameter");
				Entry parameterEntry = null;
				if(new Boolean(parameterToken != null)){
					parameterEntry = Generators.rule.generateDefinition(parameterToken.get("definition"),Generators.list.buildString(listName,"$HIDDEN"),5);
					Generators.list.addClassList(listName,name,regex,parameterEntry);
					hasDefinition = true;
				}
			}
		}
		String listNameInQuotes = Generators.list.buildString("\"",listName,"\"");
		if(new Boolean(hasDefinition == true)){
			Generators.list.addEntry(directory,fileName,"nameParser",new ElementEntry("emptyList",new ListEntry(new StringEntry(listNameInQuotes))));
		}
	}
	public void addClassList(String listName,String name,String regex,Entry parameter){
		Entry p = (Entry)parameter;
		Boolean containsAssociatedClass = listAssociatedClass.containsKey("listname");
		if(new Boolean(!containsAssociatedClass)){
			listAssociatedClass.put(listName,"Regex");
		}
		ListEntry params = new ListEntry();
		if(new Boolean(parameter != null)){
			params.add(parameter);
		}
		String nameInQuotes = Generators.list.buildString("\"",name,"\"");
		String listNameInQuotes = Generators.list.buildString("\"",listName,"\"");
		params.add(new ListEntry(new StringEntry(nameInQuotes)));
		params.add(new ListEntry(new StringEntry(listNameInQuotes)));
		String regexInQuotes = Generators.list.buildString("\"",regex);
		if(new Boolean("listnames".equals(listName))){
			regexInQuotes = Generators.list.buildString(regexInQuotes,"\\b\"");
		}
		else {
			regexInQuotes = Generators.list.buildString(regexInQuotes,"\"");
		}
		params.add(new ListEntry(new StringEntry(regexInQuotes)));
		String associatedClassName = listAssociatedClass.get(listName);
		Entry listEntry = new ElementEntry("class",new ListEntry(new ListEntry(new StringEntry(associatedClassName)),new ListEntry(new StringEntry(name)),new ListEntry(new StringEntry(associatedClassName)),params));
		Generators.list.addList(new ListEntry(),listName,name,listEntry);
	}
	public void addList(Entry imports,String listName,String name,Entry listEntry){
		Entry l = (Entry)listEntry;
		String className = Generators.list.camelize(listName);
		String fileName = Generators.list.buildString(className,".java");
		String listNameInQuotes = Generators.list.buildString("\"",listName,"\"");
		Integer listNameLength = listName.length();
		String singularListName = listName.substring(0,listNameLength - 1);
		ListEntry parameters = new ListEntry(imports,new ListEntry(new StringEntry(className)),new ListEntry(new StringEntry(listName)),new ListEntry(new StringEntry(singularListName)));
		Generators.list.addFile(directory,fileName,parameters);
		ListEntry main = (ListEntry)Generators.list.getOrAddEntry(directory,fileName,"main",new ListEntry());
		main.setDelimiter("");
		ListEntry subs = new ListEntry();
		subs.setDelimiter("");
		if(new Boolean(main.isEmpty())){
			main.add(subs);
			main.add(new ElementEntry("parser",new ListEntry(new ListEntry())));
			main.add(new ElementEntry("name_parser",new ListEntry(new ListEntry(new StringEntry(listNameInQuotes)))));
		}
		ListEntry main0 = (ListEntry)main.get(0);
		main0.add(listEntry);
		ElementEntry main1 = (ElementEntry)main.get(1);
		ListEntry main10 = (ListEntry)main1.get(0);
		main10.add(name);
	}

	public HashMap<String,String> getListAssociatedClass(){
		return listAssociatedClass;
	}

	public File getDirectory(){
		return directory;
	}

	public String getName(){
		return "List";
	}

	public void generate(ParseData data){
	}

	public void assignListElementNames(Map<String, ParseList> listMap, IToken rootToken){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}