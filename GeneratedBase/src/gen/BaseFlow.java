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

public class BaseFlow extends FlowController {
	public static void main(String[] args){
		if(args.length==1){
			new BaseFlow().parse(args[0]);
		}
		else {
			System.err.println("No filename provided!");
		}
	}


	private IParser lazyNameParser = (IParser)Tokens.LISTNAME;
	private List<IParser> rules = (List<IParser>)Rules.parser;
	private List<IParser> listnames = (List<IParser>)Listnames.parser;
	private IParser rootParser = (IParser)Rules.base;


	public IParser getLazyNameParser(){
		return lazyNameParser;
	}
	public List<IParser> getRules(){
		return rules;
	}
	public List<IParser> getListnames(){
		return listnames;
	}
	public IParser getRootParser(){
		return rootParser;
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
				listName = this.buildString(listSingle,listName.substring(indexOfDash + 1,listName.length()));
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
	public void setup(ParseContext context){
	}
	public Generator[] getGenerators(){
		return Generators._;
	}
}