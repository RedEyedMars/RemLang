package com.rem.gen;
import java.util.Set;
import com.rem.gen.Token;
import com.rem.gen.Braces;
import java.util.List;
import com.rem.gen.Cargon2;
import java.util.HashSet;
import java.util.stream.IntStream;
public class Cargon2 {
	public static com.rem.output.helpers.OutputArguments rootSetCall = new com.rem.output.helpers.OutputArguments();
	public static Set<String> rulenames = new HashSet<String>();
	public static com.rem.output.helpers.OutputArguments ignoreSpace = new com.rem.output.helpers.OutputArguments();
	public static void main(String[] args){
		com.rem.output.helpers.OutputHelper.parse(args,new com.rem.gen.parser.Parser(),Cargon2::init,Cargon2::setup,Cargon2::generate);
	}
	public static void init(com.rem.gen.parser.Parser.Result.Pass result){
		com.rem.output.helpers.OutputHelper.suppliment("java.nio","CharBuffer");
com.rem.output.helpers.OutputHelper.suppliment("java.io","BufferedReader");
com.rem.output.helpers.OutputHelper.suppliment("java.io","FileReader");
com.rem.output.helpers.OutputHelper.suppliment("java.io","IOException");
com.rem.output.helpers.OutputHelper.suppliment("java.lang","Comparable");
com.rem.output.helpers.OutputHelper.suppliment("java.util","Arrays");
com.rem.output.helpers.OutputHelper.suppliment("java.util","EnumMap");
com.rem.output.helpers.OutputHelper.suppliment("java.util","Comparator");
com.rem.output.helpers.OutputHelper.suppliment("java.util","Collections");
com.rem.output.helpers.OutputHelper.suppliment("java.util.stream","Collectors");
com.rem.output.helpers.OutputHelper.suppliment("java.util.stream","IntStream");
com.rem.output.helpers.OutputHelper.suppliment("java.util.PrimitiveIterator","OfInt");
com.rem.output.helpers.OutputHelper.suppliment("java.util.function","Consumer");
com.rem.output.helpers.OutputHelper.addOutputClass(Parser.OUTPUT);
com.rem.output.helpers.OutputHelper.addOutputClass(ParseRule.OUTPUT);
com.rem.output.helpers.OutputHelper.addOutputClass(Braces.OUTPUT);
com.rem.output.helpers.OutputHelper.addOutputClass(Token.OUTPUT);
com.rem.output.helpers.OutputHelper.addOutputClass(ErrorList.OUTPUT);
com.rem.output.helpers.OutputHelper.addOutputClass(ParseError.OUTPUT);
;
	}
	public static com.rem.output.helpers.Output regex(com.rem.gen.parser.Token regex){
		return regex(regex,null);
	}
	public static com.rem.output.helpers.Output regex(com.rem.gen.parser.Token regex,StringBuilder parentStringBuilder){
		com.rem.output.helpers.OutputArguments args = new com.rem.output.helpers.OutputArguments();
		com.rem.output.helpers.OutputQuote asString = new com.rem.output.helpers.OutputQuote();
		args.add(asString);
		StringBuilder asStringBuilder = new StringBuilder();
		for(com.rem.gen.parser.Token element:regex.getAllSafely(com.rem.gen.parser.Token.Id._regex_element)){
			com.rem.output.helpers.Output arg = null;
			for(com.rem.gen.parser.Token atom:element.getAll()){
				switch(atom.getName()){
					case _group :{
						asStringBuilder.append("(");
						arg=regex(atom.get(com.rem.gen.parser.Token.Id._regex),asStringBuilder);
						asStringBuilder.append(")");
						break;
					}
					case _option :{
						arg=regex_option(atom,asStringBuilder);
						break;
					}
					case _character :{
						String result = (atom).toString();
						if(result.equals("\"")){
							result="\\\"";
						}
						else if(result.equals("\\")){
							result="\\\\";
						}
						arg=new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Any")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(result)));
						asStringBuilder.append(result);
						break;
					}
					case _regex_special :{
						arg=regex_special(atom,asStringBuilder);
						break;
					}
				}
			}
			if(element.get(com.rem.gen.parser.Token.Id._multiple)==null){
				args.add(arg);
			}
			else{
				for(com.rem.gen.parser.Token multiple:element.get(com.rem.gen.parser.Token.Id._multiple).getAll()){
					switch(multiple.getName()){
						case _OPTIONAL :{
							args.add(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Loopify")).add(new com.rem.output.helpers.OutputExact().set("Optional")),new com.rem.output.helpers.OutputArguments().add(arg)));
							asStringBuilder.append("?");
							break;
						}
						case _MANY :{
							args.add(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Loopify")).add(new com.rem.output.helpers.OutputExact().set("Many")),new com.rem.output.helpers.OutputArguments().add(arg)));
							asStringBuilder.append("*");
							break;
						}
						case _PLUS :{
							args.add(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Loopify")).add(new com.rem.output.helpers.OutputExact().set("More")),new com.rem.output.helpers.OutputArguments().add(arg)));
							asStringBuilder.append("+");
							break;
						}
					}
				}
			}
		}
		asString.set(asStringBuilder.toString());
		if(parentStringBuilder!=null){
			parentStringBuilder.append(asStringBuilder.toString());
		}
		return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType("ParseRule").add("Regex"),args);
	}
	public static com.rem.output.helpers.Output regex_option(com.rem.gen.parser.Token option,StringBuilder asStringBuilder){
		StringBuilder personalStringBuilder = new StringBuilder();
		asStringBuilder.append("[");
		com.rem.output.helpers.OutputArguments args = new com.rem.output.helpers.OutputArguments();
		com.rem.output.helpers.OutputQuote personalString = new com.rem.output.helpers.OutputQuote();
		if(option.get(com.rem.gen.parser.Token.Id._negate)!=null){
			asStringBuilder.append("^");
			args.add(personalString);
		}
		for(com.rem.gen.parser.Token type:option.getAll()){
			switch(type.getName()){
				case _range :{
					args.add(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Range")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact((int)(type.get(com.rem.gen.parser.Token.Id._left)).toString().charAt(0))).add(new com.rem.output.helpers.OutputExact((int)(type.get(com.rem.gen.parser.Token.Id._right)).toString().charAt(0)))));
					personalStringBuilder.append((type.get(com.rem.gen.parser.Token.Id._left)).toString());
					personalStringBuilder.append("-");
					personalStringBuilder.append((type.get(com.rem.gen.parser.Token.Id._right)).toString());
					break;
				}
				case _regex_special :{
					args.add(regex_special(type,personalStringBuilder));
					break;
				}
				case _standAlone :{
					String result = "\'"+(type).toString()+"\'";
					if(result.equals("\'")){
						result="\'\\\'\'";
					}
					else if(result.equals("\\")){
						result="\'\\\\\'";
					}
					args.add(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Char")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact((result).toString()))));
					personalStringBuilder.append(result);
					break;
				}
			}
		}
		asStringBuilder.append(personalStringBuilder.toString());
		asStringBuilder.append("]");
		if(option.get(com.rem.gen.parser.Token.Id._negate)!=null){
			personalString.set(personalStringBuilder.toString());
			return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Not")),new com.rem.output.helpers.OutputArguments().add(args));
		}
		else{
			return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Branch")),new com.rem.output.helpers.OutputArguments().add(args));
		}
	}
	public static com.rem.output.helpers.Output regex_special(com.rem.gen.parser.Token special,StringBuilder asStringBuilder){
		for(com.rem.gen.parser.Token atom:special.getAll()){
			switch(atom.getName()){
				case _number :{
					asStringBuilder.append("\\\\d");
					return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Range")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact().set("48")).add(new com.rem.output.helpers.OutputExact().set("57")));
				}
				case _dot :{
					asStringBuilder.append(".");
					return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Range")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact().set("32")).add(new com.rem.output.helpers.OutputExact().set("128")));
				}
				case _whitespace :{
					asStringBuilder.append("\\\\s");
					return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Any")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(" \\t\\n\\r")));
				}
				case _escaped :{
					String result = "\'\\"+(atom).toString()+"\'";
					asStringBuilder.append("\\"+(atom).toString());
					return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Char")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact((result).toString())));
				}
				case _slash :{
					String result = "\'\\\\\'";
					asStringBuilder.append("\\\\");
					return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Char")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact((result).toString())));
				}
			}
		}
		return null;
	}
	public static void setup(com.rem.gen.parser.Parser.Result.Pass result){
		com.rem.gen.parser.Token root = result.getRoot();
		for(com.rem.gen.parser.Token rule:root.getAllSafely(com.rem.gen.parser.Token.Id._rule)){
			if(rulenames.add((rule.get(com.rem.gen.parser.Token.Id._ruleName)).toString())){
				String idName = "_"+(rule.get(com.rem.gen.parser.Token.Id._ruleName)).toString();
				com.rem.output.helpers.OutputHelper.getClass("Parser").variable(new com.rem.output.helpers.OutputVariable().isPublic().isStatic().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Tokenify")).add(new com.rem.output.helpers.OutputExact().set("One")),new com.rem.output.helpers.OutputExact((idName).toString())).assign(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Tokenify")).add(new com.rem.output.helpers.OutputExact().set("One")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Token")).add(new com.rem.output.helpers.OutputExact().set("Id")).add(idName),new com.rem.output.helpers.OutputCall())))));
				if(Cargon2.rulenames.size()==1){
					Cargon2.rootSetCall.add(new com.rem.output.helpers.OutputExact((idName).toString()));
				}
			}
		}
		if(ignoreSpace.isEmpty()){
			Cargon2.ignoreSpace.add(new com.rem.output.helpers.OutputQuote().set(" \\t\\n\\r"));
		}
	}
	public static void generate(com.rem.gen.parser.Parser.Result.Pass result){
		com.rem.gen.parser.Token root = result.getRoot();
		List<com.rem.gen.parser.Token> rules = root.getAllSafely(com.rem.gen.parser.Token.Id._rule);
		for(com.rem.gen.parser.Token rule:root.getAllSafely(com.rem.gen.parser.Token.Id._rule)){
			String variableName = "_"+(rule.get(com.rem.gen.parser.Token.Id._ruleName)).toString();
			com.rem.output.helpers.OutputHelper.getClass("Parser").getMethod("setup()").add(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact((variableName).toString()),null).add(new com.rem.output.helpers.OutputExact().set("setRule"),new com.rem.output.helpers.OutputArguments().add(rule(rule))))));
			Token.addId(variableName);
		}
	}
	public static com.rem.output.helpers.Output rule(com.rem.gen.parser.Token rule){
		com.rem.output.helpers.Output subRule;
		com.rem.output.helpers.OutputArguments definitions = new com.rem.output.helpers.OutputArguments();
		define(rule.get(com.rem.gen.parser.Token.Id._definition),definitions);
		if(definitions.isSingle()){
			subRule=definitions.get(0);
		}
		else{
			subRule=new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Branch")),new com.rem.output.helpers.OutputArguments().add(definitions));
		}
		if(rule.get(com.rem.gen.parser.Token.Id._braced_parameters)!=null){
			return new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType("Braces").add("Rule"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact(Braces.addBrace((rule.get(com.rem.gen.parser.Token.Id._braced_parameters).get(com.rem.gen.parser.Token.Id._left)).toString().equals((rule.get(com.rem.gen.parser.Token.Id._braced_parameters).get(com.rem.gen.parser.Token.Id._right)).toString()),(rule.get(com.rem.gen.parser.Token.Id._braced_parameters).get(com.rem.gen.parser.Token.Id._left)).toString(),(rule.get(com.rem.gen.parser.Token.Id._braced_parameters).get(com.rem.gen.parser.Token.Id._right)).toString(),"\\"))).add(subRule));
		}
		else{
			return subRule;
		}
	}
	public static void define(com.rem.gen.parser.Token definition,com.rem.output.helpers.OutputArguments definitions){
		com.rem.output.helpers.OutputArguments args = new com.rem.output.helpers.OutputArguments();
		for(com.rem.gen.parser.Token chain:definition.getAllSafely(com.rem.gen.parser.Token.Id._chain)){
			args.add(element(chain.get(com.rem.gen.parser.Token.Id._element)));
		}
		if(args.isSingle()){
			definitions.add(args.get(0));
		}
		else{
			definitions.add(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Chain")),new com.rem.output.helpers.OutputArguments().add(args)));
		}
		if(definition.get(com.rem.gen.parser.Token.Id._choice)!=null){
			define(definition.get(com.rem.gen.parser.Token.Id._choice).get(com.rem.gen.parser.Token.Id._definition),definitions);
		}
	}
	public static com.rem.output.helpers.OutputStaticCall element(com.rem.gen.parser.Token element){
		com.rem.output.helpers.OutputArguments args = new com.rem.output.helpers.OutputArguments();
		if(element.get(com.rem.gen.parser.Token.Id._newName)!=null){
			String newName = "_"+(element.get(com.rem.gen.parser.Token.Id._newName)).toString();
			args.add(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Token")).add(new com.rem.output.helpers.OutputExact().set("Id")).add(newName),new com.rem.output.helpers.OutputCall()));
			Token.addId(newName);
		}
		else{
			args.add(new com.rem.output.helpers.OutputExact().set("null"));
		}
		for(com.rem.gen.parser.Token quark:element.getAll()){
			switch(quark.getName()){
				case _braced_definition :{
					args.add(rule(quark));
					break;
				}
				case _quoteToken :{
					StringBuilder quote = new StringBuilder();
					String string = quark.toString();
					IntStream.range(0,string.length()-1).forEach( I->{						if(new Character(string.charAt(I)).toString().equals("\\")&&string.charAt(I+1)!='n'&&string.charAt(I+1)!='t'&&string.charAt(I+1)!='r'){
							quote.append(string.charAt(I));
							quote.append(string.charAt(I));
						}
						else{
							quote.append(string.charAt(I));
						}
 });
					if(new Character(string.charAt(string.length()-1)).toString().equals("\\")){
						quote.append(string.charAt(string.length()-1));
						quote.append(string.charAt(string.length()-1));
					}
					else{
						quote.append(string.charAt(string.length()-1));
					}
					args.add(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")).add(new com.rem.output.helpers.OutputExact().set("Terminal")),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputQuote().set(quote.toString()))));
					break;
				}
				case _regexToken :{
					args.add(regex(quark.get(com.rem.gen.parser.Token.Id._regex)));
					break;
				}
				case _ruleToken :{
					String subRuleName = "_"+(quark).toString();
					args.add(new com.rem.output.helpers.OutputExact((subRuleName).toString()));
					break;
				}
			}
		}
		if(element.get(com.rem.gen.parser.Token.Id._multiple)==null){
			return new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("createOne"),new com.rem.output.helpers.OutputArguments().add(args)));
		}
		else{
			for(com.rem.gen.parser.Token multiple:element.get(com.rem.gen.parser.Token.Id._multiple).getAll()){
				switch(multiple.getName()){
					case _OPTIONAL :{
						return new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("createOptional"),new com.rem.output.helpers.OutputArguments().add(args)));
					}
					case _MANY :{
						return new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("createMany"),new com.rem.output.helpers.OutputArguments().add(args)));
					}
					case _PLUS :{
						return new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseRule")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("createMore"),new com.rem.output.helpers.OutputArguments().add(args)));
					}
				}
			}
			return null;
		}
	}
}
