package com.rem.gen.parser;
import com.rem.gen.parser.ParseRule;
import java.nio.CharBuffer;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Braces;
import java.util.function.Consumer;
import java.io.FileReader;
import java.io.IOException;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.ErrorList;
import java.io.BufferedReader;
import java.io.File;
import java.util.Map;
public class Parser implements com.rem.output.helpers.OutputHelper.Parser<Parser.Result,Parser.Result.Pass> {
	public static final int ERROR_ON_END = 1;
	public static final int PASS = 0;
	public static final int FAIL = -1;
	public static ParseRule.Tokenify.One __ROOT__ = new ParseRule.Tokenify.One(Token.Id.ROOT);
	public static String __WHITESPACE__ = (" \t\n\r");
	public void setup(){
		__ROOT__.setRule();
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
		Token root = new Token(Token.Id.ROOT,null);
		ErrorList errors = new ErrorList();
		int result = __ROOT__.parse(root,errors,input,braceData);
		if(result==Parser.PASS&&(input.remaining()==0 ||input.get()==0)){
			return new Parser.Result.Pass(root);
		}
		else{
			return new Parser.Result.Fail(chars.duplicate(),errors);
		}
	}
	public static void consumeWhitespace(CharBuffer chars,Map<Integer, Braces.Data> braceData){
		while(chars.remaining()>0 ){
			char c = chars.get();
			if(c!='\r'&& !__WHITESPACE__.chars().anyMatch( C->C==c)){
				chars.position(chars.position()-1);
				return ;
			}
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
		public abstract String description();
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
			consumer.accept(description());
		}
		public String toString(){
			StringBuilder builder = new StringBuilder();
			print(builder::append,true);
			return builder.toString();
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
			protected Token root;
			public Token getRoot(){
				return root;
			}
			public Pass (Token root){
				super();
				this.root=root;
			}
			public Result.State getState(){
				return Result.State.PASS;
			}
			public String description(){
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
			public String description(){
				return errors.toString(chars);
			}
		}
	}
}
