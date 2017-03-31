package base;

import java.io.File;
import java.util.List;

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
