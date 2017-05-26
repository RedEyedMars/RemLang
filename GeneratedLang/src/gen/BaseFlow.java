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

public class BaseFlow extends FlowController {
	public static void main(String[] args){
		if(args.length==1){
			new BaseFlow().parse(args[0]);
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
		System.out.println(Generators.classifier.completeTokenErrorMessage(root));
		context.addList("method_names");
		ParseList contextList = (ParseList)context.getList("method_names");
		NameParser namesParser = (NameParser)contextList.getNamesParser();
		List<IParser> methodNames = (List<IParser>)MethodNames.parser;
		for(IParser parser:methodNames){
			RegexParser regexParser = (RegexParser)parser;
			String methodName = regexParser.getPattern();
			namesParser.addName(methodName);
		}
		context.addList("variable_names");
		contextList = (ParseList)context.getList("variable_names");
		namesParser = (NameParser)contextList.getNamesParser();
		List<IParser> variableNames = (List<IParser>)VariableNames.parser;
		for(IParser parser:variableNames){
			RegexParser regexParser = (RegexParser)parser;
			String variableName = regexParser.getPattern();
			namesParser.addName(variableName);
		}
		namesParser.addName("this");
		context.addList("class_names");
		ParseList names = (ParseList)context.getList("class_names");
		ParseList classes = (ParseList)context.getList("class_declarations");
		IToken newClassTokens = classes.getNewTokens();
		NameParser namesNames = (NameParser)names.getNamesParser();
		for(IToken.Key classTokenKey:newClassTokens.keySet()){
			IToken classToken = newClassTokens.get(classTokenKey);
			namesNames.addName(classToken.get("className").getString());
		}
		ConcreteRule definedConstructor = (ConcreteRule)Rules.defined_method;
		definedConstructor.setup();
		Rules.defined_operator.setup();
	}
	public void setup(ParseContext context){
	}
	public Generator[] getGenerators(){
		return Generators._;
	}
}