package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import lists.*;

public class BaseGenerator extends Generator {

	private File directory = (File)null;
	private String seedName = null;
	private IParser lazyNameParser = (IParser)Tokens.LISTNAME;

	public BaseGenerator(){
	}
	public void generate(ParseData data){
		String fileName = data.getFileName();
		Integer indexOfDot = fileName.lastIndexOf(".");
		if(new Boolean(indexOfDot > -1)){
			fileName = fileName.substring(0,indexOfDot);
		}
		seedName = fileName;
		directory = new File(Generators.base.buildString("../Generated",Generators.base.camelize(fileName),"/src/"));
		directory.mkdirs();
		Generators.list.generate(data);
		Generators.rule.generate(data);
		Generators.rule.outputAll();
		Generators.list.outputAll();
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

	public String getSeedName(){
		return seedName;
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