package base;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rem.parser.Generator;
import com.rem.parser.IParser;
import com.rem.parser.IToken;
import com.rem.parser.NameParser;
import com.rem.parser.ParseData;
import com.rem.parser.ParseList;

import base.lists.Tokens;

public class BaseGenerator extends Generator{

	private File baseDirectory = new File("../Generation/src/base");
	private ListGenerator listGen = new ListGenerator();
	private RuleGenerator ruleGen = new RuleGenerator();


	@Override
	protected void generate(ParseData data) {
		baseDirectory.mkdir();


		generateAll(listGen,((ParseList)data.getList("list_rules")).getNewTokens(),"list_rule");
		generateAll(ruleGen,((ParseList)data.getList("rules")).getNewTokens(),"rule");
		outputAll();

	}

	@Override
	public IParser getLazyNameParser(){
		return Tokens.NAME;
	}

	@Override
	public void assignListElementNames(Map<String,ParseList> listMap, IToken root){
		for(IToken.Id key:listMap.get("list_rules").getNewTokens().keySet()){
			IToken token = listMap.get("list_rules").getNewTokens().get(key);
			final String listName = token.get("listname").getString();
			if(!listMap.containsKey(listName)){
				listMap.put(listName, new ParseList(){
					private NameParser name_parser = new NameParser(listName);
					@Override
					public String getName() {
						return listName;
					}

					@Override
					public String getSingular() {
						return listName.substring(0,listName.length()-1);
					}

					@Override
					public NameParser getNamesParser() {
						return name_parser;
					}

				});
			}
			ParseList list = listMap.get(listName);
			List<IToken> defs = token.getAll("list_def");
			if(defs!=null){
				for(IToken def:defs){
					list.getNamesParser().addName(def.get("parameters").get("name").getString());
				}
			}
		}
	}


	private class RuleGenerator implements Interpreter {
		private File ruleDirectory = new File(baseDirectory,"rules");
		private String[] ruleElement = new String[]{"\tpublic static final IParser "," = ",".parser;\n"};

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
		private String[] rule_name_parserElement = new String[]{"",/*Name*/".name_parser"};

		private RuleGenerator(){
			ruleDirectory.mkdir();
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
		}

		@Override
		public void interpret(IToken token) {

			String ruleName = token.get("rulename").getString();
			String className = camelize(ruleName);
			String fileName = className + ".java";


			listGen.addList("rulenames", ruleName, ruleName, null);

			Entry[] entry = StringEntry.getEntry(ruleName,className);
			listGen.addList("rules", ruleName, "ruleElement", ruleElement, entry);

			String[] parameters = new String[]{className,className,className,ruleName,""};
			addFile(getName(),ruleDirectory,fileName,parameters);	

			IToken silence = token.get("silence");
			boolean isSilent = silence!=null&&!silence.isEmpty();

			System.out.println(">"+ruleName);
			for(IToken.Id key:token.keySet()){				
				if("definition".equals(key.getName())){
					ListEntry ruleEntry = new ListEntry();
					if(isSilent){
						ruleEntry.add(new ElementEntry("setupSilenceElement",new Entry[]{}));
						ruleEntry.setDelimiter("");
					}
					ruleEntry.add(new ElementEntry("setupAddElement",generateDefinition(token.get(key),3)));
					Entry[] rules = new Entry[]{ruleEntry};
					Entries element = addElement(getName(),ruleDirectory,fileName,"setupElement",setupElement);
					element.add(rules);
				}
			}
		}


		public Entry generateAtom(IToken atom, int tabs){
			Entry entry = null;
			String enclosingName = null;
			String enclosingList = null;
			for(IToken.Id key:atom.keySet()){
				if("parameters".equals(key.getName())){
					IToken name = atom.get(key).get("name");
					IToken list = atom.get(key).get("list");
					if(name!=null){
						enclosingName = "\""+name.getString()+"\"";
					}
					if(list!=null){
						enclosingList = "\""+list.getString()+"\"";
					}
				}
				else if("terminal".equals(key.getName())){
					entry = generateToken(atom.get(key));
				}
				else if("braced".equals(key.getName())){
					entry = generateDefinition(atom.get(key).get("definition"),tabs);
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
							new Entry[]{new StringEntry(option),generateDefinition(atom.get(key).get("definition"),tabs)});
				}
			}
			if(enclosingName!=null){
				if(enclosingList!=null){
					return 
							new TabEntry(tabs,
									new ElementEntry("addToListElement",
											new Entry[]{new TabEntry(tabs+1,entry),new StringEntry(enclosingName),new StringEntry(enclosingList)}));
				}
				else {
					return new TabEntry(tabs,
							new ElementEntry("asElement",
									new Entry[]{new TabEntry(tabs+1,entry),new StringEntry(enclosingName)}));
				}
			}
			else return new TabEntry(tabs,entry);
		}

		public Entry generateDefinition(IToken definition, int tabs){
			return generateDefinition(definition,new ListEntry(),tabs);
		}

		public Entry generateDefinition(IToken definition, ListEntry entries, int tabs){
			for(IToken.Id key:definition.keySet()){				
				if("choice".equals(key.getName())){
					for(IToken.Id choiceKey:definition.get(key).keySet()){
						if("definition".equals(choiceKey.getName())){
							ListEntry nextChoices = new ListEntry();
							generateDefinition(definition.get(key).get(choiceKey),nextChoices,tabs);							
							entries.addAll(nextChoices);
						}
					}
				}
				else if("chain".equals(key.getName())){
					ListEntry chain = new ListEntry();
					for(IToken.Id chainKey:definition.get(key).keySet()){
						if("atom".equals(chainKey.getName())){
							chain.add(generateAtom(definition.get(key).get(chainKey),tabs+1));
						}
					}
					if(chain.isSingluar()){
						entries.add(chain.getSingle());
					}
					else {
						entries.add(new ElementEntry("chainElement",chain));
					}
				}

			}
			if(entries.isSingluar()){
				return entries.getSingle();
			}
			else {
				return new TabEntry(tabs,new ElementEntry("choiceElement",entries));
			}

		}

		public Entry generateToken(IToken terminal){
			for(IToken.Id key:terminal.keySet()){
				if("ruleToken".equals(key.getName())){
					return new ElementEntry("rule_parserElement",new StringEntry(camelize(terminal.get(key).getString())));
				}
				else if("listsToken".equals(key.getName())){					
					return new ElementEntry("rule_parserElement",new StringEntry(camelize(terminal.get(key).getString())));
				}
				else if("listToken".equals(key.getName())){					
					return new ElementEntry("rule_name_parserElement",new StringEntry(camelize(terminal.get(key).getString()+"s")));
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
									"\tpublic static final IParser parser = new ",/*Class Name*/"();\n"+
											"\tpublic ",/*Class Name*/"(){\n"+
													"\t\tsuper(\"",/*Rule Name*/"\");\n"+
															"\t}\n"+
															"\t@Override\n"+
															"\tpublic void setup(){\n",/*Entries*/"\n\t}\n\n}"
			};
		}

	}

	private class ListGenerator implements Interpreter{

		private Map<String/*ListName*/,String/*Association*/> listAssociatedClass = new HashMap<String,String>();
		private File listDirectory = new File(baseDirectory,"lists");
		private final String[] classElement = new String[]{
				"\tpublic static final IParser ",/*Name*/" = new ",/*Class*/"Parser(",/*Parameters*/");\n"
		};
		private final String[] parserElement = new String[]{
				"\n\tpublic static final IParser parser = new ChoiceParser(\n\t\t\t\t",/*Rules*/");\n"
		};
		private final String[] name_parserElement = new String[]{
				"\n\tpublic static final NameParser name_parser = new NameParser(\n\t\t\t\t",/*Rules*/");\n"+
						"\t@Override\n"+
						"\tpublic NameParser getNamesParser(){\n"+
						"\t\treturn name_parser;\n"+
						"\t}\n"
		};
		private final String[] emptyListElement = new String[]{
				"\t@Override\n"+
						"\tpublic NameParser getNamesParser(){\n"+
						"\t\treturn new NameParser();\n"+
						"\t}\n"
		};
		public ListGenerator(){
			listDirectory.mkdir();
		}

		@Override
		public void interpret(IToken token) {
			String listName = token.get("listname").getString();
			if(token.containsKey("listType")){
				listAssociatedClass.put(listName, camelize(token.get("listType").getString()));
			}
			String className = camelize(listName);
			String fileName = className + ".java";

			String[] fileParameters = new String[]{className,listName,listName.substring(0,listName.length()-1),""};
			addFile(getName(),listDirectory,fileName,fileParameters);

			for(IToken.Id key:token.keySet()){
				if("list_def".equals(key.getName())){
					IToken def = token.get(key);
					String name = def.get("parameters").get("name").getString();
					String regex = def.get("regex").getString();
					IToken parameter = def.get("parameters").get("parameter");
					Entry parameterEntry = null;
					if(parameter!=null){
						parameterEntry = ruleGen.generateDefinition(parameter.get("definition"),5);
					}

					addList(listName, name, regex, parameterEntry);
				}
			}
		}

		public void addList(String listName, String name, String regex, Entry parameter){
			if(!listAssociatedClass.containsKey(listName)){
				listAssociatedClass.put(listName, "Regex");
			}
			ListEntry params = new ListEntry();
			if(parameter!=null){
				params.add(parameter);
			}
			params.add(new StringEntry("\""+name+"\""));
			params.add(new StringEntry("\""+listName+"\""));
			params.add(new StringEntry("\""+regex+"\""));
			Entry[] entry = new Entry[]{
					new StringEntry(name),
					new StringEntry(listAssociatedClass.get(listName)),
					params};
			addList(listName, name, "classElement", classElement, entry);
		}

		public void addList(String listName, String name, String elementName, String[] elementOutline, Entry[] entry){
			String className = camelize(listName);
			String fileName = className + ".java";

			String[] parameters = new String[]{className,listName,listName.substring(0,listName.length()-1),""};
			addFile(getName(),listDirectory,fileName,parameters);

			Entries element = addElement(getName(),listDirectory,fileName,elementName, elementOutline);
			element.add(entry);

			element = addElement(getName(),listDirectory,fileName,"parserElement",parserElement);
			Entry[] parser_entry;
			if(element.isEmpty()){
				parser_entry = new Entry[]{new ListEntry()};
				element.add(parser_entry);
			}
			else {
				parser_entry = element.get(0);
			}
			((ListEntry)parser_entry[0]).add(name);


			element = addElement(getName(),listDirectory,fileName,"name_parserElement",name_parserElement);
			parser_entry = new Entry[1];
			if(element.isEmpty()){
				parser_entry = new Entry[]{new ListEntry()};
				((ListEntry)parser_entry[0]).add("\""+listName + "\"");
				element.add(parser_entry);
			}
			else {
				parser_entry = element.get(0);
			}
			((ListEntry)parser_entry[0]).add("\""+name+"\"");
		}

		@Override
		public String getName() {
			return "LIST";
		}
		@Override
		public String[] getOutline() {
			return new String[]{
					"package base.lists;\n\n"+			
							"import com.rem.parser.*;\n"+
							"import base.rules.*;\n\n"+
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
