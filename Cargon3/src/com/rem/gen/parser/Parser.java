package com.rem.gen.parser;
import com.rem.gen.parser.ParseRule;
import com.rem.gen.parser.Token;
import java.util.function.Consumer;
import com.rem.gen.parser.Parser;
import java.util.Map;
import java.nio.CharBuffer;
import java.io.IOException;
import java.io.BufferedReader;
import com.rem.gen.parser.ErrorList;
import java.io.File;
import com.rem.gen.parser.Braces;
import java.io.FileReader;
public class Parser implements com.rem.output.helpers.OutputHelper.Parser<Parser.Result,Parser.Result.Pass> {
	public static final int ERROR_ON_END = 1;
	public static final int PASS = 0;
	public static final int FAIL = -1;
	public static ParseRule.Tokenify.One __ROOT__ = new ParseRule.Tokenify.One(Token.Id.ROOT);
	public static ParseRule.Loopify.Many __WHITESPACE__ = new ParseRule.Loopify.Many(new ParseRule.Any(" \t\n\r"));
	public static ParseRule.Tokenify.One _base = new ParseRule.Tokenify.One(Token.Id._base);
	public static ParseRule.Tokenify.One _comments = new ParseRule.Tokenify.One(Token.Id._comments);
	public static ParseRule.Tokenify.One _ignores = new ParseRule.Tokenify.One(Token.Id._ignores);
	public static ParseRule.Tokenify.One _imports = new ParseRule.Tokenify.One(Token.Id._imports);
	public static ParseRule.Tokenify.One _FILE_NAME = new ParseRule.Tokenify.One(Token.Id._FILE_NAME);
	public static ParseRule.Tokenify.One _NAME = new ParseRule.Tokenify.One(Token.Id._NAME);
	public static ParseRule.Tokenify.One _OPERATOR = new ParseRule.Tokenify.One(Token.Id._OPERATOR);
	public static ParseRule.Tokenify.One _NUMBER = new ParseRule.Tokenify.One(Token.Id._NUMBER);
	public static ParseRule.Tokenify.One _quote = new ParseRule.Tokenify.One(Token.Id._quote);
	public static ParseRule.Tokenify.One _braced_definition = new ParseRule.Tokenify.One(Token.Id._braced_definition);
	public static ParseRule.Tokenify.One _regex = new ParseRule.Tokenify.One(Token.Id._regex);
	public static ParseRule.Tokenify.One _regex_option = new ParseRule.Tokenify.One(Token.Id._regex_option);
	public static ParseRule.Tokenify.One _regex_group_definition = new ParseRule.Tokenify.One(Token.Id._regex_group_definition);
	public static ParseRule.Tokenify.One _single_ignores_character = new ParseRule.Tokenify.One(Token.Id._single_ignores_character);
	public static ParseRule.Tokenify.One _regex_option_definition = new ParseRule.Tokenify.One(Token.Id._regex_option_definition);
	public static ParseRule.Tokenify.One _rule_parameters = new ParseRule.Tokenify.One(Token.Id._rule_parameters);
	public static ParseRule.Tokenify.One _rule_params = new ParseRule.Tokenify.One(Token.Id._rule_params);
	public static ParseRule.Tokenify.One _base_element = new ParseRule.Tokenify.One(Token.Id._base_element);
	public static ParseRule.Tokenify.One _rule = new ParseRule.Tokenify.One(Token.Id._rule);
	public static ParseRule.Tokenify.One _list = new ParseRule.Tokenify.One(Token.Id._list);
	public static ParseRule.Tokenify.One _definition = new ParseRule.Tokenify.One(Token.Id._definition);
	public static ParseRule.Tokenify.One _element = new ParseRule.Tokenify.One(Token.Id._element);
	public static ParseRule.Tokenify.One _atom = new ParseRule.Tokenify.One(Token.Id._atom);
	public static ParseRule.Tokenify.One _regex_definition = new ParseRule.Tokenify.One(Token.Id._regex_definition);
	public static ParseRule.Tokenify.One _regex_element = new ParseRule.Tokenify.One(Token.Id._regex_element);
	public static ParseRule.Tokenify.One _regex_special = new ParseRule.Tokenify.One(Token.Id._regex_special);
	public void setup(){
		__ROOT__.setRule(_base);
		_base.setRule(ParseRule.createMany(null,_base_element));
		_comments.setRule(new Braces.Rule(0,ParseRule.createOne(Token.Id._comment,new ParseRule.Regex(".*",new ParseRule.Loopify.Many(new ParseRule.Range(32,128))))));
		_ignores.setRule(new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal("ignore")),ParseRule.createOne(null,new ParseRule.Terminal(":")),ParseRule.createMany(null,ParseRule.createOne(Token.Id._ignoreCharacter,_single_ignores_character)),ParseRule.createOne(null,new ParseRule.Terminal("\n"))));
		_imports.setRule(ParseRule.createOne(null,new ParseRule.Terminal("NOT IMPLEMENTED")));
		_FILE_NAME.setRule(ParseRule.createOne(null,new ParseRule.Regex("[^\\s]+",new ParseRule.Loopify.More(new ParseRule.Not("\\s",new ParseRule.Any(" \t\n\r"))))));
		_NAME.setRule(ParseRule.createOne(null,new ParseRule.Regex("[a-zA-Z'_'][a-zA-Z0-9'_']*",new ParseRule.Branch(new ParseRule.Range(97,122),new ParseRule.Range(65,90),new ParseRule.Char('_')),new ParseRule.Loopify.Many(new ParseRule.Branch(new ParseRule.Range(97,122),new ParseRule.Range(65,90),new ParseRule.Range(48,57),new ParseRule.Char('_'))))));
		_OPERATOR.setRule(ParseRule.createOne(null,new ParseRule.Regex("[^a-zA-Z0-9'_'\\s\n'('')''{''}''['']'';'\"\''`'',']+",new ParseRule.Loopify.More(new ParseRule.Not("a-zA-Z0-9'_'\\s\n'('')''{''}''['']'';'\"\''`'','",new ParseRule.Range(97,122),new ParseRule.Range(65,90),new ParseRule.Range(48,57),new ParseRule.Char('_'),new ParseRule.Any(" \t\n\r"),new ParseRule.Char('\n'),new ParseRule.Char('('),new ParseRule.Char(')'),new ParseRule.Char('{'),new ParseRule.Char('}'),new ParseRule.Char('['),new ParseRule.Char(']'),new ParseRule.Char(';'),new ParseRule.Char('\"'),new ParseRule.Char('\''),new ParseRule.Char('`'),new ParseRule.Char(','))))));
		_NUMBER.setRule(ParseRule.createOne(null,new ParseRule.Regex("['-']?(\\d*[.])?\\d+f?\\s*",new ParseRule.Loopify.Optional(new ParseRule.Branch(new ParseRule.Char('-'))),new ParseRule.Loopify.Optional(new ParseRule.Regex("\\d*[.]",new ParseRule.Loopify.Many(new ParseRule.Range(48,57)),new ParseRule.Branch(new ParseRule.Range(32,128)))),new ParseRule.Loopify.More(new ParseRule.Range(48,57)),new ParseRule.Loopify.Optional(new ParseRule.Any("f")),new ParseRule.Loopify.Many(new ParseRule.Any(" \t\n\r")))));
		_quote.setRule(new Braces.Rule(1,ParseRule.createOne(null,new ParseRule.Regex(".*",new ParseRule.Loopify.Many(new ParseRule.Range(32,128))))));
		_braced_definition.setRule(new Braces.Rule(2,ParseRule.createOne(null,_definition)));
		_regex.setRule(new Braces.Rule(3,ParseRule.createOne(null,_regex_definition)));
		_regex_option.setRule(new Braces.Rule(3,new ParseRule.Chain(ParseRule.createOptional(Token.Id._negate,new ParseRule.Terminal("^")),ParseRule.createMany(null,_regex_option_definition))));
		_regex_group_definition.setRule(new Braces.Rule(2,ParseRule.createOne(Token.Id._regex,_regex_definition)));
		_single_ignores_character.setRule(new Braces.Rule(3,ParseRule.createOne(null,new ParseRule.Regex("\\?.?",new ParseRule.Loopify.Optional(new ParseRule.Char('\\')),new ParseRule.Loopify.Optional(new ParseRule.Range(32,128))))));
		_regex_option_definition.setRule(new ParseRule.Branch(ParseRule.createOne(Token.Id._range,new ParseRule.Chain(ParseRule.createOne(Token.Id._left,new ParseRule.Regex(".",new ParseRule.Range(32,128))),ParseRule.createOne(null,new ParseRule.Terminal("-")),ParseRule.createOne(Token.Id._right,new ParseRule.Regex(".",new ParseRule.Range(32,128))))),ParseRule.createOne(null,_regex_special),ParseRule.createOne(Token.Id._standAlone,new ParseRule.Regex(".",new ParseRule.Range(32,128)))));
		_rule_parameters.setRule(new ParseRule.Chain(ParseRule.createOne(null,_rule_params),ParseRule.createMany(null,new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal(",")),ParseRule.createOne(null,_rule_params)))));
		_rule_params.setRule(new ParseRule.Branch(ParseRule.createOne(Token.Id._SILENT,new ParseRule.Terminal("silent")),new ParseRule.Chain(ParseRule.createOptional(null,new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal("@")),ParseRule.createOne(Token.Id._passConstraint,_NUMBER))),ParseRule.createOne(null,new ParseRule.Terminal("Braced")),ParseRule.createOne(Token.Id._braced_parameters,new ParseRule.Chain(ParseRule.createOne(Token.Id._left,new ParseRule.Regex("[^\\s]+",new ParseRule.Loopify.More(new ParseRule.Not("\\s",new ParseRule.Any(" \t\n\r"))))),ParseRule.createOne(Token.Id._right,new ParseRule.Regex("[^\\s]+",new ParseRule.Loopify.More(new ParseRule.Not("\\s",new ParseRule.Any(" \t\n\r")))))))),new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal("Imports")),ParseRule.createOne(Token.Id._import_parameter,ParseRule.createMany(null,new ParseRule.Branch(ParseRule.createOne(null,_quote),ParseRule.createOne(Token.Id._ruleName,_NAME)))),ParseRule.createOne(null,new ParseRule.Terminal("=")),ParseRule.createOne(Token.Id._import_definition,ParseRule.createMany(Token.Id._chain,_element))),new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal("Ignore")),ParseRule.createOne(null,new ParseRule.Branch(ParseRule.createOne(Token.Id._ignores_none,new ParseRule.Terminal("None")),ParseRule.createMany(Token.Id._ignores_character,ParseRule.createOne(null,_single_ignores_character)))))));
		_base_element.setRule(ParseRule.createMany(null,new ParseRule.Branch(ParseRule.createOne(null,new ParseRule.Terminal("\n")),ParseRule.createOne(null,_ignores),ParseRule.createOne(null,_comments),ParseRule.createOne(null,_imports),ParseRule.createOne(null,_list),ParseRule.createOne(null,_rule))));
		_rule.setRule(new ParseRule.Chain(ParseRule.createOne(Token.Id._ruleName,_NAME),ParseRule.createOptional(null,ParseRule.createOne(null,_rule_parameters)),ParseRule.createOne(null,new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Branch(ParseRule.createOne(null,new ParseRule.Terminal("=")),ParseRule.createOne(null,new ParseRule.Terminal("\n\t")))),ParseRule.createOne(null,_definition)))));
		_list.setRule(new ParseRule.Chain(ParseRule.createOne(Token.Id._listName,_NAME),ParseRule.createOptional(null,new ParseRule.Terminal("global")),ParseRule.createOne(null,new ParseRule.Terminal("list")),ParseRule.createOne(null,new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal("with")),ParseRule.createOne(Token.Id._listRuleName,_NAME))),ParseRule.createMany(null,new ParseRule.Branch(new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Branch(ParseRule.createOne(null,new ParseRule.Terminal(",")),ParseRule.createOne(null,new ParseRule.Terminal("\n\t")))),ParseRule.createOne(null,_quote)),ParseRule.createOne(null,new ParseRule.Terminal("\n"))))));
		_definition.setRule(new ParseRule.Chain(ParseRule.createMany(Token.Id._chain,_element),ParseRule.createOptional(Token.Id._choice,new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Branch(ParseRule.createOne(null,new ParseRule.Terminal("|")),ParseRule.createOne(null,new ParseRule.Terminal("\n\t")))),ParseRule.createOne(null,_definition)))));
		_element.setRule(new ParseRule.Chain(ParseRule.createOne(null,_atom),ParseRule.createOptional(null,new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal("as ")),ParseRule.createOne(Token.Id._newName,_NAME))),ParseRule.createOptional(null,new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal("in ")),ParseRule.createOne(Token.Id._listName,_NAME))),ParseRule.createOptional(Token.Id._multiple,new ParseRule.Branch(ParseRule.createOne(Token.Id._OPTIONAL,new ParseRule.Terminal("?")),ParseRule.createOne(Token.Id._MANY,new ParseRule.Terminal("*")),ParseRule.createOne(Token.Id._PLUS,new ParseRule.Terminal("+"))))));
		_atom.setRule(new ParseRule.Branch(ParseRule.createOne(null,_braced_definition),ParseRule.createOne(Token.Id._quoteToken,_quote),ParseRule.createOne(Token.Id._regexToken,_regex),ParseRule.createOne(Token.Id._ruleToken,_NAME)));
		_regex_definition.setRule(ParseRule.createMany(null,_regex_element));
		_regex_element.setRule(new ParseRule.Branch(new ParseRule.Chain(ParseRule.createOne(Token.Id._option,_regex_option),ParseRule.createOptional(Token.Id._multiple,new ParseRule.Branch(ParseRule.createOne(Token.Id._OPTIONAL,new ParseRule.Terminal("?")),ParseRule.createOne(Token.Id._MANY,new ParseRule.Terminal("*")),ParseRule.createOne(Token.Id._PLUS,new ParseRule.Terminal("+"))))),new ParseRule.Chain(ParseRule.createOne(Token.Id._group,_regex_group_definition),ParseRule.createOptional(Token.Id._multiple,new ParseRule.Branch(ParseRule.createOne(Token.Id._OPTIONAL,new ParseRule.Terminal("?")),ParseRule.createOne(Token.Id._MANY,new ParseRule.Terminal("*")),ParseRule.createOne(Token.Id._PLUS,new ParseRule.Terminal("+"))))),new ParseRule.Chain(ParseRule.createOne(null,_regex_special),ParseRule.createOptional(Token.Id._multiple,new ParseRule.Branch(ParseRule.createOne(Token.Id._OPTIONAL,new ParseRule.Terminal("?")),ParseRule.createOne(Token.Id._MANY,new ParseRule.Terminal("*")),ParseRule.createOne(Token.Id._PLUS,new ParseRule.Terminal("+"))))),new ParseRule.Chain(ParseRule.createOne(Token.Id._character,new ParseRule.Regex(".",new ParseRule.Range(32,128))),ParseRule.createOptional(Token.Id._multiple,new ParseRule.Branch(ParseRule.createOne(Token.Id._OPTIONAL,new ParseRule.Terminal("?")),ParseRule.createOne(Token.Id._MANY,new ParseRule.Terminal("*")),ParseRule.createOne(Token.Id._PLUS,new ParseRule.Terminal("+")))))));
		_regex_special.setRule(new ParseRule.Branch(ParseRule.createOne(Token.Id._number,new ParseRule.Terminal("\\d")),ParseRule.createOne(Token.Id._dot,new ParseRule.Terminal(".")),ParseRule.createOne(Token.Id._whitespace,new ParseRule.Terminal("\\s")),new ParseRule.Chain(ParseRule.createOne(null,new ParseRule.Terminal("\\\\")),ParseRule.createOne(Token.Id._escaped,new ParseRule.Regex("[a-z\"\']",new ParseRule.Branch(new ParseRule.Range(97,122),new ParseRule.Char('\"'),new ParseRule.Char('\''))))),ParseRule.createOne(Token.Id._slash,new ParseRule.Terminal("\\\\"))));
	}
	public Parser.Result.Pass asPass(Parser.Result result){
		return (result instanceof Parser.Result.Pass)?(Parser.Result.Pass)result:null;
	}
	public com.rem.gen.parser.Parser.Result parse(String fileName){
		long parseStartTime = System.currentTimeMillis();
		setup();
		try{
			File file = new File(fileName);
			if(!file.exists()){
				return null;
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			CharBuffer chars = CharBuffer.allocate((int)file.length()+1);
			reader.read(chars);
			reader.close();
			chars.position(0);
			com.rem.gen.parser.Parser.Result result = parse(chars);
			return result.setFileName(fileName).setParseTime(System.currentTimeMillis()-parseStartTime);
		}
		catch(IOException e0){
			e0.printStackTrace();
		}
		return null;
	}
	public com.rem.gen.parser.Parser.Result parse(CharBuffer chars){
		Map<Integer, Braces.Data> braceData = Braces.find(chars.duplicate());
		CharBuffer input = chars.duplicate();
		Token rootToken = new Token(Token.Id.ROOT,null);
		ErrorList errors = new ErrorList();
		int result = __ROOT__.parse(rootToken,errors,input,braceData);
		if(result==Parser.PASS&&(input.remaining()==0 ||input.get()==0)){
			return new Parser.Result.Pass(rootToken);
		}
		else{
			return new Parser.Result.Fail(chars.duplicate(),errors);
		}
	}
	public abstract static class Result {
		protected String fileName;
		protected long parseTime;
		public String getFileName(){
			return fileName;
		}
		public long getParseTime(){
			return parseTime;
		}
		public static String getStatus(State state){
			switch(state){
				case PASS:{
					return "Passed";
				}
				case FAIL:{
					return "Failed";
				}
			}
			return null;
		}
		public abstract State getState();
		public long accumulateTime(long sum){
			return sum+parseTime;
		}
		public void print(Consumer<String> consumer,boolean isSolo){
			consumer.accept(fileName);
			consumer.accept(" ");
			consumer.accept(getStatus(getState()));
			if(isSolo){
				consumer.accept(" in ");
				consumer.accept(com.rem.output.helpers.OutputHelper.getTimeAsString(parseTime));
			}
			consumer.accept("\n");
			consumer.accept(this.toString());
		}
		public Result setParseTime(long parseTime){
			this.parseTime=parseTime;
			return this;
		}
		public Result setFileName(String newFileName){
			fileName=newFileName;
			return this;
		}
		public static enum State {
			 PASS,
			 FAIL
		}
		public static class Pass extends Result {
			protected Token rootToken;
			public Token getRootToken(){
				return rootToken;
			}
			public Pass (Token rootToken){
				super();
				this.rootToken=rootToken;
			}
			public Result.State getState(){
				return Result.State.PASS;
			}
			public String toString(){
				return "";
			}
		}
		public static class Fail extends Result {
			protected ErrorList errors;
			protected CharBuffer chars;
			public ErrorList getErrors(){
				return errors;
			}
			public CharBuffer getChars(){
				return chars;
			}
			public Fail (CharBuffer chars,ErrorList errors){
				this.errors=errors;
				this.chars=chars;
			}
			public Result.State getState(){
				return Result.State.FAIL;
			}
			public String toString(){
				return errors.toString(chars);
			}
		}
	}
}
