package base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseUtil;
import com.rem.parser.generation.ElementEntry;
import com.rem.parser.generation.Entry;
import com.rem.parser.generation.FlowController;
import com.rem.parser.generation.Generator;
import com.rem.parser.generation.ListEntry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.TabEntry;
import com.rem.parser.parser.IParser;
import com.rem.parser.parser.NameParser;
import com.rem.parser.parser.RegexParser;
import com.rem.parser.token.IToken;

import base.GeneratorGenerator.MethodEntry;
import base.GeneratorGenerator.TypeEntry;
import base.GeneratorGenerator.VariableEntry;
import generator.rules.Base;
import lists.ClassNames;
import lists.Listnames;
import lists.Rules;
import lists.Tokens;

public class GeneratorFlow extends FlowController {

	public static void main(String[] args){

		if(args.length == 1){
			new GeneratorFlow().parse(args[0]);
		}
		else {
			new GeneratorFlow().parse("test.generator");
		}
	}

	@Override
	public void assignListElementNames(ParseContext context, IToken root){

		//System.out.println(Generators.generator.completeTokenErrorMessage(root));
		for(IParser parser:Listnames.parser){
			String pattern = ((RegexParser)parser).getPattern();
			String listName = pattern.substring(0, pattern.length()-1);
			context.addList(listName);
		}
		context.addList("class_names");
		for(IParser parser:ClassNames.parser){
			String className = ((RegexParser)parser).getPattern();
			context.getList("class_names").getNamesParser().addName(className);
		}
		if(context.getList("class_definitions")!=null){
			context.addList("generator_names");
			NameParser generatorNames = context.getList("generator_names").getNamesParser();
			List<IToken> classDefs = context.getList("class_definitions").getNewTokens().getAll("class_dec");
			if(classDefs!=null){
				for(IToken token:classDefs){
					generatorNames.addName(token.get("className").getString());
				}
			}
			else {
				System.out.println("classDefs null");
			}
		}

		if(context.getList("meta_declarations")!=null){
			context.addList("generator_names");
			NameParser generatorNames = context.getList("generator_names").getNamesParser();
			IToken classDefs = context.getList("meta_declarations").getNewTokens();
			if(classDefs!=null){
				for(IToken.Key token:classDefs.keySet()){
					generatorNames.addName(classDefs.get(token).get("metaName").getString());
				}
			}
		}

		Map<String,List<String>> variableNames = new HashMap<String,List<String>>();
		Map<String,List<String>> entryNames = new HashMap<String,List<String>>();
		if(context.getList("property_definitions")!=null){
			context.addList("property_names");
			NameParser propertyNames = context.getList("property_names").getNamesParser();
			IToken classDefs = context.getList("property_definitions").getNewTokens();

			if(classDefs!=null){
				for(IToken.Key key:classDefs.keySet()){
					String propName = classDefs.get(key).get("propertyName").getString();
					propertyNames.addName(propName);
					List<String> varNames = new ArrayList<String>();
					List<String> entNames = new ArrayList<String>();
					variableNames.put(propName, varNames);
					entryNames.put(propName, entNames);
					List<IToken> elements = classDefs.get(key).getAll("entry_class_element");
					if(elements!=null){
						for(IToken element:elements){
							IToken varDef = element.get("variable_declaration");
							if(varDef!=null){
								varNames.add(varDef.get("variableName").getString());
								continue;
							}
							varDef = element.get("constant_declaration");
							if(varDef!=null){
								varNames.add(varDef.get("variableName").getString());
								continue;
							}
							varDef = element.get("entry_declaration");
							if(varDef!=null){
								entNames.add(varDef.get("entryName").getString());
							}
						}
					}
				}
			}
		}

		if(context.getList("entry_class_definitions")!=null){
			context.addList("class_names");
			NameParser classNames = context.getList("entry_class_names").getNamesParser();
			IToken classDefs = context.getList("entry_class_definitions").getNewTokens();
			if(classDefs!=null){
				for(IToken.Key token:classDefs.keySet()){
					IToken entryClass = classDefs.get(token);
					String entryClassName = entryClass.get("entryClassName").getString();
					classNames.addName(entryClassName);
					IToken implement = entryClass.get("implements");
					if(implement!=null){
						ParseContext specificContext = entryClass.getContext(context).getContextFromPosition(entryClass.getPosition(),false);

						for(IToken.Key ikey:implement.keySet()){
							System.out.println(entryClassName+":"+ikey.getName());
							if("property_names".equals(ikey.getName())){
								IToken propertyName = implement.get(ikey);
								String propName = propertyName.getString();

								specificContext.addList("variable_names");
								specificContext.addList("entry_names");
								NameParser varNames = specificContext.getList("variable_names").getNamesParser();
								NameParser entNames = specificContext.getList("entry_names").getNamesParser();
								if(variableNames.containsKey(propName)){
								for(String varName:variableNames.get(propName)){
									System.out.println(varName+" added to "+entryClassName);
									varNames.addName(varName);
								}
								for(String entName:entryNames.get(propName)){
									entNames.addName(entName);
								}
								}

							}
						}
					}
				}
			}
		}
	}

	@Override
	public IParser getRootParser() {
		return Rules.base;
	}

	@Override
	public List<IParser> getRules() {
		return Rules.parser;
	}

	@Override
	public List<IParser> getListnames() {
		return Listnames.parser;
	}

	@Override
	public IParser getLazyNameParser() {
		return Tokens.NAME;
	}

	@Override
	public Generator[] getGenerators() {
		return Generators._;
	}

	@Override
	public void setup(ParseContext data) {		
	}

}
