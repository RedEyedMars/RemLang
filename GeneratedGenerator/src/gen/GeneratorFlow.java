package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import gen.checks.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class GeneratorFlow extends FlowController {
	public static void main(String[] args){
		if(args.length==1){
			new GeneratorFlow().parse(args[0]);
		}
		else {
			System.err.println("No filename provided!");
		}
	}


	private RegexParser lazyNameParser = (RegexParser)Tokens.NAME;
	private List<IParser> rules = (List<IParser>)Rules.parser;
	private List<IParser> listnames = (List<IParser>)Listnames.parser;
	private IParser rootParser = (IParser)Rules.base;


	public RegexParser getLazyNameParser(){
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
		List<IParser> listNames = (List<IParser>)Listnames.parser;
		for(IParser parser:listNames){
			IParser iparser = (IParser)parser;
			RegexParser regexParser = (RegexParser)iparser;
			String pattern = regexParser.getPattern();
			Integer patternLength = pattern.length();
			String listName = pattern.substring(0,patternLength - 1);
			context.addList(listName);
		}
		context.addList("class_names");
		List<IParser> classNames = (List<IParser>)ClassNames.parser;
		for(IParser parser:classNames){
			IParser iparser = (IParser)parser;
			RegexParser regexParser = (RegexParser)iparser;
			String className = regexParser.getPattern();
			ParseList contextList = (ParseList)context.getList("class_names");
			NameParser namesParser = (NameParser)contextList.getNamesParser();
			namesParser.addName(className);
		}
		ParseList classDefsList = (ParseList)context.getList("class_definitions");
		if((classDefsList != null)){
			context.addList("generator_names");
			ParseList generatorNamesList = (ParseList)context.getList("generator_names");
			NameParser generatorNames = (NameParser)generatorNamesList.getNamesParser();
			IToken classDefsToken = classDefsList.getNewTokens();
			List<IToken> classDefClassDec = classDefsToken.getAll("class_dec");
			if(classDefClassDec != null){
				for(IToken classDef:classDefClassDec){
					generatorNames.addName(classDef.get("className").getString());
				}
			}
		}
		ParseList metaDeclarations = (ParseList)context.getList("meta_declarations");
		if((metaDeclarations != null)){
			context.addList("generator_names");
			ParseList generatorNamesList = (ParseList)context.getList("generator_names");
			NameParser generatorNames = (NameParser)generatorNamesList.getNamesParser();
			IToken metaDefs = metaDeclarations.getNewTokens();
			for(IToken.Key metaDefKey:metaDefs.keySet()){
				IToken metaDef = metaDefs.get(metaDefKey);
				generatorNames.addName(metaDef.get("metaName").getString());
			}
		}
		Map<String,List<String>> variableNames = (Map<String,List<String>>)new HashMap<String,List<String>>();
		Map<String,List<String>> entryNames = (Map<String,List<String>>)new HashMap<String,List<String>>();
		ParseList propertyDefinitions = (ParseList)context.getList("property_definitions");
		if((propertyDefinitions != null)){
			context.addList("property_names");
			ParseList propertyList = (ParseList)context.getList("property_names");
			NameParser propertyNames = (NameParser)propertyList.getNamesParser();
			IToken propertyDefs = propertyDefinitions.getNewTokens();
			if((propertyDefs != null)){
				for(IToken.Key propertyDefKey:propertyDefs.keySet()){
					IToken propertyDef = propertyDefs.get(propertyDefKey);
					String propName = propertyDef.get("propertyName").getString();
					propertyNames.addName(propName);
					List<String> varNames = (List<String>)new ArrayList<String>();
					List<String> entNames = (List<String>)new ArrayList<String>();
					variableNames.put(propName,varNames);
					entryNames.put(propName,entNames);
					List<IToken> elementEntryClassElement = propertyDef.getAll("entry_class_element");
					if(elementEntryClassElement != null){
						for(IToken element:elementEntryClassElement){
							IToken varDef = element.get("variable_declaration");
							if((varDef != null)){
								varNames.add(varDef.get("variableName").getString());
							}
							else {
								varDef = element.get("constant_declaration");
								if((varDef != null)){
									varNames.add(varDef.get("variableName").getString());
								}
								else {
									varDef = element.get("entry_declaration");
									if((varDef != null)){
										entNames.add(varDef.get("entryName").getString());
									}
								}
							}
						}
					}
				}
			}
		}
		ParseList entryClassDefinitions = (ParseList)context.getList("entry_class_definitions");
		if((entryClassDefinitions != null)){
			context.addList("entry_class_names");
			ParseList entryClassList = (ParseList)context.getList("entry_class_names");
			NameParser entryClassNames = (NameParser)entryClassList.getNamesParser();
			IToken classDefs = entryClassDefinitions.getNewTokens();
			for(IToken.Key entryClassTokKey:classDefs.keySet()){
				IToken entryClassTok = classDefs.get(entryClassTokKey);
				String entryClassName = entryClassTok.get("entryClassName").getString();
				entryClassNames.addName(entryClassName);
				IToken implement = entryClassTok.get("implements");
				if((implement != null)){
					ParseContext parentContext = (ParseContext)entryClassTok.getContext(context);
					ParseContext specificContext = (ParseContext)parentContext.getContextFromPosition(entryClassTok.getPosition(),false);
					for(IToken.Key elementKey:implement.keySet()){
						if("property_names".equals(elementKey.getName())){
							IToken element = implement.get(elementKey);
							String propName = element.getString();
							specificContext.addList("variable_names");
							specificContext.addList("entry_names");
							ParseList varNamesList = (ParseList)specificContext.getList("variable_names");
							NameParser varNames = (NameParser)varNamesList.getNamesParser();
							ParseList entNamesList = (ParseList)specificContext.getList("entry_names");
							NameParser entNames = (NameParser)entNamesList.getNamesParser();
							if((variableNames.containsKey(propName))){
								List<String> varList = (List<String>)variableNames.get(propName);
								for(String varName:varList){
									varNames.addName(varName);
								}
								List<String> entList = (List<String>)entryNames.get(propName);
								for(String entName:entList){
									entNames.addName(entName);
								}
							}
						}
					}
				}
			}
		}
	}
	public void setup(ParseContext context){
	}
	public Generator[] getGenerators(){
		return Generators._;
	}
}