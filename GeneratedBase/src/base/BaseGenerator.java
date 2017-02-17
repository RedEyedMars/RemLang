package base;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rem.parser.Generator;
import com.rem.parser.IParser;
import com.rem.parser.IToken;
import com.rem.parser.ParseData;
import com.rem.parser.ParseList;
import com.rem.parser.RegexParser;

import base.lists.Listnames;
import base.lists.Tokens;

public class BaseGenerator extends Generator{

	private File baseDirectory;
	private ListGenerator listGen;
	private RuleGenerator ruleGen;;


	@Override
	protected void generate(ParseData data) {
		String fileName = data.getFileName();
		int indexOfDot = fileName.lastIndexOf('.');
		if(indexOfDot>-1)fileName = fileName.substring(0, indexOfDot);
		baseDirectory = new File("../Generated"+camelize(fileName)+"/src/base/");
		baseDirectory.mkdirs();
		listGen = new ListGenerator();
		ruleGen = new RuleGenerator();


		generateAll(listGen,((ParseList)data.getList("list_rules")).getNewTokens(),"list_rule");
		generateAll(ruleGen,((ParseList)data.getList("rules")).getNewTokens(),"rule");
		outputAll();

	}

	@Override
	public IParser getLazyNameParser(){
		return Tokens.LISTNAME;
	}

	@Override
	public void assignListElementNames(Map<String,ParseList> listMap, IToken root){
		
		List<IParser> listNameChoices = new ArrayList<IParser>();
		ParseList listnames = listMap.get("listnames");
		listnames.getNamesParser().clear();
		for(IToken.Key key:listMap.get("list_rules").getNewTokens().keySet()){
			IToken token = listMap.get("list_rules").getNewTokens().get(key);
			String listName = token.get("listname").getString().replaceAll("[ \\t]+","");
			String listSingle = listName;
			int indexOfBrace = listName.indexOf('{');
			if(indexOfBrace>-1){
				ParseList oldList = null;
				if(listMap.containsKey(listName)){
					oldList = listMap.remove(listName);
				}
				listSingle = listName.substring(0, indexOfBrace);
				listName = listSingle + listName.substring(indexOfBrace+1,listName.length()-1);
				if(oldList!=null){
					listMap.put(listName, oldList);
				}
			}
			if(!listMap.containsKey(listName)){
				listMap.put(listName, ParseList.createNew(listName));
			}

			listNameChoices.add(new RegexParser(listSingle,"listnames",listName));
			listnames.getNamesParser().addName(listSingle);
			
			ParseList list = listMap.get(listName);
			
			List<IToken> defs = token.getAll("list_def");
			if(defs!=null){
				for(IToken def:defs){
					String name = def.get("parameters").get("name").getString();
					list.getNamesParser().addName(name);					
				}
			}
		}		

		listNameChoices.add(new RegexParser("listname","listnames","listnames"));
		listnames.getNamesParser().addName("listname");
		listNameChoices.add(new RegexParser("rule","listnames","rules"));
		listnames.getNamesParser().addName("rule");
		Listnames.parser.replace(listNameChoices);
		listMap.get("list_rules").getNamesParser().clear();
		
	}


	private class RuleGenerator implements Interpreter {
		private Map<String/*RuleName*/,List<String>/*ParamNames*/> ruleParameterNames = new HashMap<String,List<String>>();
		private File ruleDirectory = new File(baseDirectory,"rules");
		private String[] ruleElement = new String[]{"\tpublic static final IRule "," = ",".parser;\n"};

		private String[] setupSilenceElement = new String[]{"\t\tisSilent(true);\n"};
		private String[] setupAddElement = new String[]{"\t\tset(",/*Rule*/");\n"};
		private String[] setupElement = new String[]{"",/*Entry*/""};

		private String[] asElement = new String[]{"new AddTokenParser(",/*Inner Rule*/",",/*Token Name*/")"};
		private String[] addToListElement = new String[]{"new AddTokenToListParser(",
				/*Inner Rule*/",",/*Token Name*/",",/*List Name*/")"};

		private String[] chainElement = new String[]{"new ChainParser(",/*Inner Rules*/")"};
		private String[] choiceElement = new String[]{"new ChoiceParser(",/*Inner Rules*/")"};
		private String[] multipleElement = new String[]{"new ",/*ParserName*/"Parser(",/*Inner Rule*/")"};

		private String[] listElement = new String[]{"",/*List Name*/".",/*Parser Name*/""};
		private String[] rule_parserElement = new String[]{"",/*Name*/".parser"};
		private String[] rule_name_parserElement = new String[]{"new ListNameParser(\"",/*Name*/"\")"};
		private String[] rule_any_list_nameElement = new String[]{"AnyListNameParser.parser"};		

		private String[] parameterMemberElement = new String[]{"\tprivate Parameter<",/*Type*/"> ",/*Parameter Name*/" = new Parameter<",/*TypeAgain*/">(",/*Default Value*/");\n"};
		private String[] parameterIndexElement = new String[]{"\t\tcase ",/*Index*/": return ",/*Parameter Name*/";\n"};
		private String[] parameterGetElement = new String[]{"\t\tswitch(i){\n",/*Params*/"\t\tdefault: return null;\n\t\t}\n"};
		private String[] parameterWithElement = new String[]{"new WithParser((IRule)",/*Parser*/",",/*Arguments*/")"};
		private String[] parameterOperatorElement = new String[]{"new Argument.",/*OperatorName*/"(",/*Left*/",",/*Right*/")"};
		private String[] parameterNewNumberElement = new String[]{"new Parameter<Integer>(",/*Value*/")"};
		private String[] parameterExistingElement = new String[]{"this.",""};

		private RuleGenerator(){
			ruleDirectory.mkdir();

			addElement("ruleElement",ruleElement);
			
			addElement("setupSilenceElement",setupSilenceElement);
			addElement("setupAddElement",setupAddElement);
			addElement("setupElement",setupElement);

			addElement("asElement",asElement);
			addElement("addToListElement",addToListElement);
			addElement("chainElement",chainElement);
			addElement("choiceElement",choiceElement);
			addElement("multipleElement",multipleElement);
			addElement("listElement",listElement);
			addElement("rule_parserElement",rule_parserElement);
			addElement("rule_name_parserElement",rule_name_parserElement);
			addElement("rule_any_list_nameElement",rule_any_list_nameElement);
			
			addElement("parameterMemberElement",parameterMemberElement);
			addElement("parameterIndexElement",parameterIndexElement);
			addElement("parameterGetElement",parameterGetElement);
			addElement("parameterWithElement",parameterWithElement);
			addElement("parameterOperatorElement",parameterOperatorElement);
			addElement("parameterNewNumberElement",parameterNewNumberElement);
			addElement("parameterExistingElement",parameterExistingElement);
		}

		@Override
		public void interpret(IToken token) {

			String ruleName = token.get("rulename").getString();
			String className = camelize(ruleName);
			String fileName = className + ".java";


			listGen.addClassList("rulenames", ruleName, ruleName, null);

			Entry entry = new ElementEntry("ruleElement",StringEntry.getEntry(ruleName,className));
			listGen.addList(new ListEntry(new ElementEntry("importRulesElement")),"rules", ruleName, entry);

			ListEntry param_list = new ListEntry();
			Entry[] parameters = new Entry[]{
					new StringEntry(className),
					new StringEntry(className),
					param_list,
					new StringEntry(className),
					new StringEntry(ruleName)};
			addFile(getName(),ruleDirectory,fileName,parameters);	

			IToken silence = token.get("silence");
			boolean isSilent = silence!=null&&!silence.isEmpty();

			System.out.println(">"+ruleName);
			
			ListEntry parameterIndexEntries = new ListEntry();
			int param_count = 0;
			for(IToken.Key key:token.keySet()){				
				if("definition".equals(key.getName())){
					ListEntry ruleEntry = new ListEntry();
					if(isSilent){
						ruleEntry.add(new ElementEntry("setupSilenceElement",new ListEntry()));
						ruleEntry.setDelimiter("");
					}
					ruleEntry.add(new ElementEntry("setupAddElement",
							new ListEntry(generateDefinition(ruleName,token.get(key),3))));
					addEntry(getName(),ruleDirectory,fileName,"rule",ruleEntry);
				}
				else if("rule_param".equals(key.getName())){
					String rule_param = token.get(key).getString();
					if(!ruleParameterNames.containsKey(ruleName)){
						ruleParameterNames.put(ruleName, new ArrayList<String>());
					}
					ruleParameterNames.get(ruleName).add(rule_param);
					param_list.add(new ElementEntry("parameterMemberElement","Integer",rule_param,"Integer","0"));
					parameterIndexEntries.add(
							new ElementEntry("parameterIndexElement",new ListEntry(new StringEntry(""+param_count),new StringEntry(rule_param))));
					++param_count;
				}
			}

			addEntry(getName(),ruleDirectory,fileName,"params",new ElementEntry("parameterGetElement",new ListEntry(parameterIndexEntries)));
		}


		public Entry generateAtom(String ruleName, IToken atom, int tabs){
			Entry entry = null;
			String enclosingName = null;
			String enclosingList = null;
			ElementEntry enclosingArgumentsStatement = null;
			for(IToken.Key key:atom.keySet()){
				if("parameters".equals(key.getName())){
					IToken name = atom.get(key).get("name");
					IToken list = atom.get(key).get("list");
					IToken parameter = atom.get(key).get("parameter");
					if(name!=null){
						enclosingName = "\""+name.getString()+"\"";
					}
					if(list!=null){
						enclosingList = "\""+list.getString()+"\"";
					}
					if(parameter!=null){
						enclosingArgumentsStatement = (ElementEntry) generateParameter(parameter);
					}
					
				}
				else if("terminal".equals(key.getName())){
					entry = generateToken(atom.get(key));
				}
				else if("braced".equals(key.getName())){
					entry = generateDefinition(ruleName,atom.get(key).get("definition"),tabs);
					//entries.add(new ElementEntry("choiceElement",generateRule(definition.get(key).get("definition"))));
				}
				else if("multiple".equals(key.getName())){
					String option = atom.get(key).get("option").getString();
					if("*".equals(option)){
						option = "Many";
					}
					else if("?".equals(option)){
						option = "Optional";
					}
					else if("+".equals(option)){
						option = "Multiple";
					}
					entry = new ElementEntry("multipleElement",
							new ListEntry(new StringEntry(option),generateDefinition(ruleName,atom.get(key).get("definition"),tabs)));
				}
			}
			
			Entry ret = entry;
			if(enclosingName!=null){
				if(enclosingList!=null){
					ret = new ElementEntry("addToListElement",
											new ListEntry(new TabEntry(tabs+1,entry),new StringEntry(enclosingName),new StringEntry(enclosingList)));
				}
				else {
					ret = new ElementEntry("asElement",
									new ListEntry(new TabEntry(tabs+1,entry),new StringEntry(enclosingName)));
				}
			}
			
			if(enclosingArgumentsStatement!=null){
				ret = new ElementEntry("parameterWithElement",new ListEntry(ret,enclosingArgumentsStatement));
			}
			return new TabEntry(tabs,ret);
		}
		
		public Entry generateParameter(IToken parameter){
			for(IToken.Key key:parameter.keySet()){
				if("definition".equals(key.getName())){
					System.err.println("Rules cannot have definitons as their parameter");
				}
				else if("arithmatic".equals(key.getName())){
					return generateArithmatic(parameter.get(key));
				}
			}
			return null;
		}
		
		public Entry generateArithmatic(IToken arithmatic){
			Entry left = null;
			String operand = null;
			Entry right = null;
			for(IToken.Key key:arithmatic.keySet()){
				if("arithmatic".equals(key.getName())){
					if(left==null){
						left = generateArithmatic(arithmatic.get(key)); 
					}
					else {
						right = generateArithmatic(arithmatic.get(key));
					}
				}
				else if("operand".equals(key.getName())){
					String op = arithmatic.get(key).getString();
					if("*".equals(op)) operand = "Multiply";
					else if("/".equals(op)) operand = "Divide";
					else if("+".equals(op)) operand = "Add";
					else if("-".equals(op)) operand = "Subtract";
					else {
						System.err.println("Operand:"+op+" not recognized.");
					}
				}
				else {
					if(left==null){
						left = generateArithmaticTerminal(key.getName(),arithmatic.get(key)); 
					}
					else {
						right = generateArithmaticTerminal(key.getName(),arithmatic.get(key));
					}
				}
			}
			if(right==null){
				return left;
			}
			else {
				return new ElementEntry("parameterOperatorElement",new ListEntry(new StringEntry(operand),left,right));
			}
		}
		
		public Entry generateArithmaticTerminal(String name, IToken terminal){
			if("NUMBER".equals(name)){
				return new ElementEntry("parameterNewNumberElement",terminal.getString());
			}
			else if("rule_parameters".equals(name)){
				return new ElementEntry("parameterExistingElement",terminal.getString());
			}
			else {
				System.err.println(name+" not a recognized Arithmatic Terminal");
				return null;
			}
		}

		public Entry generateDefinition(String ruleName,IToken definition, int tabs){
			return generateDefinition(ruleName,definition,new ListEntry(),tabs);
		}

		public Entry generateDefinition(String ruleName, IToken definition, ListEntry entries, int tabs){
			for(IToken.Key key:definition.keySet()){				
				if("choice".equals(key.getName())){
					for(IToken.Key choiceKey:definition.get(key).keySet()){
						if("definition".equals(choiceKey.getName())){
							ListEntry nextChoices = new ListEntry();
							generateDefinition(ruleName,definition.get(key).get(choiceKey),nextChoices,tabs);							
							entries.addAll(nextChoices);
						}
					}
				}
				else if("chain".equals(key.getName())){
					ListEntry chain = new ListEntry();
					for(IToken.Key chainKey:definition.get(key).keySet()){
						if("atom".equals(chainKey.getName())){
							chain.add(generateAtom(ruleName,definition.get(key).get(chainKey),tabs+2));
						}
					}
					if(chain.isSingluar()){
						entries.add(chain.getSingle());
					}
					else {
						entries.add(new TabEntry(tabs+1,new ElementEntry("chainElement",new ListEntry(chain))));
					}
				}

			}
			if(entries.isSingluar()){
				return entries.getSingle();
			}
			else {
				return new TabEntry(tabs,new ElementEntry("choiceElement",new ListEntry(entries)));
			}

		}

		public Entry generateToken(IToken terminal){
			for(IToken.Key key:terminal.keySet()){
				if("ruleToken".equals(key.getName())){
					return new ElementEntry("rule_parserElement",camelize(terminal.get(key).getString()));
				}
				else if("listsToken".equals(key.getName())){					
					return new ElementEntry("rule_parserElement",camelize(terminal.get(key).getString()));
				}
				else if("listToken".equals(key.getName())){
					String listName = "#NO_LISTNAME_FOUND";
					for(IToken.Key lkey:terminal.get(key).keySet()){
						listName = lkey.getName();
					}
					if(listName.equals("listnames")){
						return new ElementEntry("rule_name_parserElement",terminal.get(key).getString()+"s");
					}
					else return new ElementEntry("listElement",StringEntry.getEntry(camelize(listName),terminal.get(key).getString()));
				}
				else if("anyListNameToken".equals(key.getName())){					
					return new ElementEntry(
							"rule_any_list_nameElement",new ListEntry());
				}				
				else if("token".equals(key.getName())){					
					return new ElementEntry("listElement",StringEntry.getEntry("Tokens",terminal.get(key).getString()));
				}
				else if("braceToken".equals(key.getName())){
					return new ElementEntry("listElement",StringEntry.getEntry("Braces",terminal.get(key).getString()));
				}
			}
			return new StringEntry(terminal.getString());
		}

		@Override
		public String getName() {
			return "RULE";
		}

		@Override
		public String[] getOutline() {
			return new String[]{
					"package base.rules;\n\n"+			
							"import com.rem.parser.*;\n"+
							"import base.lists.*;\n\n"+
							"public class ",/*Class Name*/" extends AddTokenParser implements IRule {\n\n"+
							"\tpublic static final IRule parser = new ",/*Class Name*/"();\n",
							"\tpublic ",/*Class Name*/"(){\n"+
							"\t\tsuper(\"",/*Rule Name*/"\");\n"+
							"\t}\n"+
							"\t@Override\n"+
							"\tpublic void setup(){\n",/*RuleSetup*/"\n\t}\n"+
							"\t@Override @SuppressWarnings(\"unchecked\")\n"+
							"\tpublic Parameter<?> getParameter(int i) {\n",
							"\t}\n"+
							"\n}"
			};
		}

	}

	private class ListGenerator implements Interpreter{

		private Map<String/*ListName*/,String/*Association*/> listAssociatedClass = new HashMap<String,String>();
		private File listDirectory = new File(baseDirectory,"lists");
		private final String[] importRulesElement = new String[]{"import base.rules.*;\n"};
		private final String[] classElement = new String[]{
				"\tpublic static final ",/*Class*/"Parser ",/*Name*/" = new ",/*Class*/"Parser(",/*Parameters*/");\n"
		};
		private final String[] parserElement = new String[]{
				"\n\tpublic static final ChoiceParser parser = new ChoiceParser(\n\t\t\t\t",/*Rules*/");\n"
		};
		private final String[] name_parserElement = new String[]{
				"\n\tpublic static final NameParser name_parser = new NameParser(\n\t\t\t\t",/*Rules*/");\n"+
						"\t@Override\n"+
						"\tpublic NameParser getNamesParser(){\n"+
						"\t\treturn name_parser;\n"+
						"\t}\n"
		};
		private final String[] emptyListElement = new String[]{
				"\tpublic static final NameParser name_parser = new NameParser(",");\n"+
				"\t@Override\n"+
						"\tpublic NameParser getNamesParser(){\n"+
						"\t\treturn name_parser;\n"+
						"\t}\n"
		};
		public ListGenerator(){
			listDirectory.mkdir();
			addElement("importRulesElement",importRulesElement);
			addElement("classElement",classElement);
			addElement("parserElement",parserElement);
			addElement("name_parserElement",name_parserElement);
			addElement("emptyListElement",emptyListElement);
			
		}

		@Override
		public void interpret(IToken token) {
			String listName = token.get("listname").getString();
			int indexOfBrace = listName.indexOf('{');
			String singleListName = listName;
			if(indexOfBrace>-1){
				singleListName = listName.substring(0,indexOfBrace);
				listName = singleListName+listName.substring(indexOfBrace+1,listName.length()-1);
			}			

			Entry[] fileParameters = new Entry[]{
					new ListEntry(),
					new StringEntry("Listnames"),
					new StringEntry("listnames"),
					new StringEntry("listname")};
			addFile(getName(),listDirectory,"Listnames.java",fileParameters);
			addClassList("listnames", singleListName, listName, null);
			
			if(token.containsKey("listType")){
				listAssociatedClass.put(listName, camelize(token.get("listType").getString()));
			}
			String className = camelize(listName);
			String fileName = className + ".java";
/*
			fileParameters = new Entry[]{
					
					new StringEntry(className),
					new StringEntry(listName),
					new StringEntry(listName.substring(0,listName.length()-1))};
			addFile(getName(),listDirectory,fileName,fileParameters);*/
			
			boolean hasDefinition = false;

			for(IToken.Key key:token.keySet()){
				if("list_def".equals(key.getName())){
					IToken def = token.get(key);
					String name = def.get("parameters").get("name").getString();
					String regex = def.get("regex").getString();
					IToken parameter = def.get("parameters").get("parameter");
					Entry parameterEntry = null;
					if(parameter!=null){
						parameterEntry = ruleGen.generateDefinition(listName+"$HIDDEN",parameter.get("definition"),5);
					}

					addClassList(listName, name, regex, parameterEntry);
					hasDefinition = true;
				}
			}
			if(!hasDefinition){
				addEntry(getName(),listDirectory,fileName,"nameParser",new ElementEntry("emptyListElement",StringEntry.getEntry("\""+listName+"\"")));
			}
		}

		public void addClassList(String listName, String name, String regex, Entry parameter){
			if(!listAssociatedClass.containsKey(listName)){
				listAssociatedClass.put(listName, "Regex");
			}
			ListEntry params = new ListEntry();
			if(parameter!=null){
				params.add(parameter);
			}
			params.add(new StringEntry("\""+name+"\""));
			params.add(new StringEntry("\""+listName+"\""));
			if("listnames".equals(listName)){
				params.add(new StringEntry("\""+regex+"\\b\""));
			}
			else {
				params.add(new StringEntry("\""+regex+"\""));
			}
			Entry entry = new ElementEntry("classElement",new ListEntry(
					new StringEntry(listAssociatedClass.get(listName)),
					new StringEntry(name),
					new StringEntry(listAssociatedClass.get(listName)),
					params));
			addList(new ListEntry(),listName, name, entry);
		}

		public void addList(ListEntry imports,String listName, String name, Entry entry){
			String className = camelize(listName);
			String fileName = className + ".java";

			Entry[] parameters = new Entry[]{
					imports,
					new StringEntry(className),
					new StringEntry(listName),
					new StringEntry(listName.substring(0,listName.length()-1))
					};
			addFile(getName(),listDirectory,fileName,parameters);
			
			ListEntry main = new ListEntry();
			main.setDelimiter("");
			
			main = (ListEntry)getOrAddEntry(getName(),listDirectory,fileName,"main",main);
			if(main.isEmpty()){
				ListEntry subs = new ListEntry();
				subs.setDelimiter("");
				main.add(subs);
				
				main.add(new ElementEntry("parserElement",new ListEntry(new ListEntry())));
				main.add(new ElementEntry("name_parserElement",new ListEntry(new StringEntry("\""+listName + "\""))));
			}
			((ListEntry)main.get(0)).add(entry);
			((ListEntry)((ElementEntry)main.get(1)).get(0)).add(name);
			
			//((ListEntry)parser_entry[0]).add("\""+name+"\"");
		}

		@Override
		public String getName() {
			return "LIST";
		}
		@Override
		public String[] getOutline() {
			return new String[]{
					"package base.lists;\n\n"+			
							"import com.rem.parser.*;\n",/*Other Imports*/
							"\n"+
							"public class ",/*Class Name*/" extends ParseList {\n\n"+
									"\t@Override\n"+
									"\tpublic String getName() {\n"+
									"\t\treturn \"",/*Plural Name*/"\";\n"+
											"\t}\n"+
											"\t@Override\n"+
											"\tpublic String getSingular() {\n"+
											"\t\treturn \"",/*Singular Name*/"\";\n"+
													"\t}\n\n",/*Entries*/"}"};
		}


	}

}
