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

public class BaseGenerator extends Generator {

	private File directory = (File)null;
	private String seedName = null;
	private IParser lazyNameParser = (IParser)Tokens.LISTNAME;


	public BaseGenerator(){
	}
	public void generate(ParseContext data){
		String fileName = data.getFileName();
		Integer indexOfDot = fileName.lastIndexOf(".");
		if((indexOfDot > -1)){
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
	public void assignListElementNames(ParseContext context,IToken root){
		ParseList list_rules = (ParseList)context.getList("list_rules");
		IToken new_list_rules = list_rules.getNewTokens();
		for(IToken.Key new_list_defKey:new_list_rules.keySet()){
			IToken new_list_def = new_list_rules.get(new_list_defKey);
			String listName = new_list_def.get("listname").getString();
			listName = listName.replaceAll("[ \\t]+","");
			String listSingle = listName;
			Integer indexOfDash = listName.indexOf("-");
			if((indexOfDash > -1)){
				ParseList oldList = (ParseList)context.getList(listName);
				if((oldList != null)){
					context.removeList(oldList);
				}
				listSingle = listName.substring(0,indexOfDash);
				listName = Generators.base.buildString(listSingle,listName.substring(indexOfDash + 1,listName.length()));
				if((oldList != null)){
					context.addList(oldList);
				}
			}
			context.addList(listName);
			ParseList listVar = (ParseList)context.getList(listName);
			NameParser listNameParser = (NameParser)listVar.getNamesParser();
			List<IToken> defListDef = new_list_def.getAll("list_def");
			if(defListDef != null){
				for(IToken def:defListDef){
					String name = def.get("parameters").get("name").getString();
					listNameParser.addName(name);
				}
			}
		}
		ParseList listRuless = (ParseList)context.getList("list_rules");
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