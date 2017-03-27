package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class ListGenerator extends Generator {

	private HashMap<String,String> listAssociatedClass = new HashMap<String,String>();
	private File directory = null;


	public static final Element outlineElement = new Element("outline",new String[]{"package lists;\n\n"+
			"import com.rem.parser.*;\n"+
			"import com.rem.parser.token.*;\n"+
			"import com.rem.parser.parser.*;\n",/*Other Imports*/"\n"+
			"public class ",/*Class Name*/" extends ParseList {\n\n"+
			"\t@Override\n"+
			"\tpublic String getName() {\n"+
			"\t\treturn \"",/*Plural Name*/"\";\n"+
			"\t}\n"+
			"\t@Override\n"+
			"\tpublic String getSingular() {\n"+
			"\t\treturn \"",/*Singular Name*/"\";\n"+
			"\t}\n\n",/*Entries*/"}"});
	public static final Element importRulesElement = new Element("importRules",new String[]{"import base.rules.*;\n"});
	public static final Element classElement = new Element("class",new String[]{"\tpublic static final ",/*Class*/"Parser ",/*Name*/" = new ",/*Class*/"Parser(",/*Parameters*/");\n"});
	public static final Element parserElement = new Element("parser",new String[]{"\n\tpublic static final ChoiceParser parser = new ChoiceParser(\n\t\t\t\t",/*Rules*/");\n"});
	public ListGenerator(){
		addElement("outline",outlineElement);
		addElement("importRules",importRulesElement);
		addElement("class",classElement);
		addElement("parser",parserElement);
	}
	public void generate(ParseContext data){
		directory = new File(Generators.base.getDirectory(),"lists");
		directory.mkdirs();
		ParseList list_rules = (ParseList)data.getList("list_rules");
		Generators.list.generateAll(list_rules.getNewTokens(),"list_rule");
	}
	public void generateRoot(IToken root){
		String listName = root.get("listname").getString();
		Integer indexOfDash = listName.indexOf("-");
		String singleListName = listName;
		if((indexOfDash > -1)){
			singleListName = listName.substring(0,indexOfDash);
			listName = Generators.list.buildString(singleListName,listName.substring(indexOfDash + 1,listName.length()));
		}
		ListEntry fileParameters = new ListEntry(new ListEntry(),new ListEntry(new StringEntry("Listnames")),new ListEntry(new StringEntry("listnames")),new ListEntry(new StringEntry("listname")));
		Generators.list.addFile(directory,"Listnames.java",fileParameters);
		Generators.list.addClassList("listnames",singleListName,listName,null);
		Boolean token_contains_list = (root.containsKey("listType"));
		if((token_contains_list == true)){
			listAssociatedClass.put(listName,Generators.list.camelize(root.get("listType").getString()));
		}
		String className = Generators.list.camelize(listName);
		String fileName = Generators.list.buildString(className,".java");
		Boolean hasDefinition = false;
		for(IToken.Key elementKey:root.keySet()){
			if("list_def".equals(elementKey.getName())){
				IToken element = root.get(elementKey);
				String name = element.get("parameters").get("name").getString();
				IToken regexToken = element.get("regex");
				Boolean listAssociatedClassContainsKey = listAssociatedClass.containsKey(listName);
				if((regexToken == null)){
					regexToken = element.get("quote");
					if((!listAssociatedClassContainsKey)){
						listAssociatedClass.put(listName,"Exact");
					}
					else {
						String listAssociatedClassListName = listAssociatedClass.get(listName);

						if((listAssociatedClassListName.equals("Regex"))){
							listAssociatedClass.put(listName,"Exact");
						}
					}
				}
				else {
					if((!listAssociatedClassContainsKey)){
						listAssociatedClass.put(listName,"Regex");
					}
					else {
						String listAssociatedClassListName = listAssociatedClass.get(listName);

						if((listAssociatedClassListName.equals("Exact"))){
							listAssociatedClass.put(listName,"Regex");
						}
					}
				}
				String regex = regexToken.getString();
				IToken parameter = element.get("parameter");
				Entry parameterEntry = null;
				if((parameter != null)){
					parameterEntry = Generators.rule.generateDefinition(parameter.get("definition"),Generators.list.buildString(listName,"$HIDDEN"),5);
				}
				Generators.list.addClassList(listName,name,regex,parameterEntry);
				hasDefinition = true;
			}
		}
	}
	public void addClassList(String listName,String name,String regex,Entry parameter){
		Boolean containsAssociatedClass = listAssociatedClass.containsKey(listName);
		if((!containsAssociatedClass)){
			listAssociatedClass.put(listName,"Regex");
		}
		ListEntry params = new ListEntry();
		if((parameter != null)){
			params.add(parameter);
		}
		params.add(new QuoteEntry(name));
		params.add(new QuoteEntry(listName));
		if(("listnames".equals(listName))){
			regex = Generators.list.buildString(regex,"\\b");
		}
		params.add(new QuoteEntry(regex));
		String associatedClassName = listAssociatedClass.get(listName);
		Entry listEntry = new ElementEntry(ListGenerator.classElement,new ListEntry(new ListEntry(new StringEntry(associatedClassName)),new ListEntry(new StringEntry(name)),new ListEntry(new StringEntry(associatedClassName)),params));
		Generators.list.addList(new ListEntry(),listName,name,listEntry);
	}
	public void addList(Entry imports,String listName,String name,Entry listEntry){
		String className = Generators.list.camelize(listName);
		String fileName = Generators.list.buildString(className,".java");
		Integer listNameLength = listName.length();
		String singularListName = listName.substring(0,listNameLength - 1);
		ListEntry parameters = new ListEntry(imports,new ListEntry(new StringEntry(className)),new ListEntry(new StringEntry(listName)),new ListEntry(new StringEntry(singularListName)));
		Generators.list.addFile(directory,fileName,parameters);
		ListEntry main = (ListEntry)Generators.list.getOrAddEntry(directory,fileName,"main",new ListEntry());
		main.setDelimiter("");
		ListEntry subs = new ListEntry();
		subs.setDelimiter("");
		if((main.isEmpty())){
			main.add(subs);
			main.add(new ElementEntry(ListGenerator.parserElement,new ListEntry(new ListEntry())));
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

	public void assignListElementNames(ParseContext context, IToken rootToken){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}