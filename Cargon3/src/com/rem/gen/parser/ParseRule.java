package com.rem.gen.parser;
import com.rem.gen.parser.ParseRule;
import java.nio.CharBuffer;
import java.util.ArrayList;
import com.rem.gen.parser.Token;
import java.util.function.Consumer;
import java.util.List;
import com.rem.gen.parser.ParseError;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.ErrorList;
import java.util.Map;
public interface ParseRule {
	public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData);
	public static ParseRule createOne(Token.Id id,ParseRule rule){
		return id==null?rule:new Tokenify.One(id,rule);
	}
	public static ParseRule createOptional(Token.Id id,ParseRule rule){
		return id==null?rule:new Tokenify.Optional(id,rule);
	}
	public static ParseRule createMany(Token.Id id,ParseRule rule){
		return id==null?new Loopify.Many(rule):new Tokenify.Many(id,rule);
	}
	public static ParseRule createMore(Token.Id id,ParseRule rule){
		return id==null?new Loopify.More(rule):new Tokenify.More(id,rule);
	}
	public static interface Tokenify extends ParseRule {
		public ParseRule setRule(ParseRule rule);
		public static class One implements Tokenify {
			protected Token.Id id;
			protected ParseRule rule;
			public Token.Id getId(){
				return id;
			}
			public ParseRule getRule(){
				return rule;
			}
			public One (Token.Id id){
				this.id=id;
			}
			public One (Token.Id id,ParseRule rule){
				this.id=id;
				this.rule=rule;
			}
			public ParseRule setRule(ParseRule rule){
				this.rule=rule;
				return this;
			}
			public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
				Token child = new Token(id);
				int result = rule.parse(child,errors,chars,braceData);
				if(result!=Parser.FAIL){
					parent.accept(child);
				}
				return result;
			}
		}
		public static class Optional implements Tokenify {
			protected Token.Id id;
			protected ParseRule rule;
			public Token.Id getId(){
				return id;
			}
			public ParseRule getRule(){
				return rule;
			}
			public Optional (Token.Id id){
				this.id=id;
			}
			public Optional (Token.Id id,ParseRule rule){
				this.id=id;
				this.rule=rule;
			}
			public ParseRule setRule(ParseRule rule){
				this.rule=rule;
				return this;
			}
			public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
				Token child = new Token(id);
				int result = rule.parse(child,errors,chars,braceData);
				if(result!=Parser.FAIL){
					parent.accept(child);
				}
				return Parser.PASS;
			}
		}
		public static class Many implements Tokenify {
			protected Token.Id id;
			protected ParseRule rule;
			public Token.Id getId(){
				return id;
			}
			public ParseRule getRule(){
				return rule;
			}
			public Many (Token.Id id){
				this.id=id;
			}
			public Many (Token.Id id,ParseRule rule){
				this.id=id;
				this.rule=rule;
			}
			public ParseRule setRule(ParseRule rule){
				this.rule=rule;
				return this;
			}
			public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
				int result = Parser.PASS;
				int ret = Parser.PASS;
				while(result!=Parser.FAIL){
					Token child = new Token(id);
					result=rule.parse(child,errors,chars,braceData);
					if(result!=Parser.FAIL){
						parent.accept(child);
						if(ret==Parser.PASS&&result==Parser.ERROR_ON_END){
							ret=result;
						}
					}
				}
				return result;
			}
		}
		public static class More implements Tokenify {
			protected Token.Id id;
			protected ParseRule rule;
			public Token.Id getId(){
				return id;
			}
			public ParseRule getRule(){
				return rule;
			}
			public More (Token.Id id){
				this.id=id;
			}
			public More (Token.Id id,ParseRule rule){
				this.id=id;
				this.rule=rule;
			}
			public ParseRule setRule(ParseRule rule){
				this.rule=rule;
				return this;
			}
			public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
				int result = Parser.PASS;
				int ret = Parser.FAIL;
				while(result!=Parser.FAIL){
					Token child = new Token(id);
					result=rule.parse(child,errors,chars,braceData);
					if(result!=Parser.FAIL){
						parent.accept(child);
						if(ret==Parser.FAIL||ret==Parser.PASS&&result==Parser.ERROR_ON_END){
							ret=result;
						}
					}
				}
				return ret;
			}
		}
	}
	public static interface Loopify extends ParseRule {
		public ParseRule setRule(ParseRule rule);
		public static class Optional implements Loopify {
			protected ParseRule rule;
			public ParseRule getRule(){
				return rule;
			}
			public Optional (ParseRule rule){
				this.rule=rule;
			}
			public ParseRule setRule(ParseRule rule){
				this.rule=rule;
				return this;
			}
			public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
				rule.parse(parent,errors,chars,braceData);
				return Parser.PASS;
			}
		}
		public static class Many implements Loopify {
			protected ParseRule rule;
			public ParseRule getRule(){
				return rule;
			}
			public Many (ParseRule rule){
				this.rule=rule;
			}
			public ParseRule setRule(ParseRule rule){
				this.rule=rule;
				return this;
			}
			public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
				int ret = Parser.PASS;
				while(true){
					int result = rule.parse(parent,errors,chars,braceData);
					if(result==Parser.FAIL){
						return ret;
					}
					else if(result==Parser.ERROR_ON_END){
						ret=result;
					}
				}
			}
		}
		public static class More implements Loopify {
			protected ParseRule rule;
			public ParseRule getRule(){
				return rule;
			}
			public More (ParseRule rule){
				this.rule=rule;
			}
			public ParseRule setRule(ParseRule rule){
				this.rule=rule;
				return this;
			}
			public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
				int ret = Parser.FAIL;
				while(true){
					int result = rule.parse(parent,errors,chars,braceData);
					if(result==Parser.FAIL){
						return ret;
					}
					else{
						ret=result;
					}
				}
			}
		}
	}
	public static class Branch implements ParseRule {
		protected ParseRule[] contenders;
		public ParseRule[] getContenders(){
			return contenders;
		}
		public Branch (ParseRule... contenders){
			this.contenders=contenders;
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			for(int i = 0;i<contenders.length;++i){
				ErrorList subErrors = new ErrorList();
				int result = contenders[i].parse(parent,subErrors,chars,braceData);
				if(result!=Parser.FAIL){
					return result;
				}
				else{
					errors.merge(subErrors);
				}
			}
			return Parser.FAIL;
		}
	}
	public static class Chain implements ParseRule {
		protected ParseRule[] contenders;
		public ParseRule[] getContenders(){
			return contenders;
		}
		public Chain (ParseRule... contenders){
			this.contenders=contenders;
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			List<Token> tokens = new ArrayList<Token>(contenders.length);
			int position = chars.position();
			int ret = Parser.PASS;
			for(int i = 0;i<contenders.length;++i){
				int result = contenders[i].parse(tokens::add,errors,chars,braceData);
				if(result==Parser.FAIL){
					chars.position(position);
					return result;
				}
				else if(result==Parser.ERROR_ON_END){
					ret=result;
				}
			}
			tokens.forEach(parent);
			return ret;
		}
	}
	public static class Terminal implements ParseRule {
		protected String value;
		public String getValue(){
			return value;
		}
		public Terminal (String value){
			this.value=value;
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			if(value.length()>chars.remaining()){
				errors.adjust(new ParseError.BufferEnd(null,chars.position()));
				return Parser.FAIL;
			}
			else{
				int position = chars.position();
				if(value.chars().allMatch( C->{						char c = chars.get();
						while(c=='\r'){
							c=chars.get();
						}
						return C==c;
 })){
					Parser.__WHITESPACE__.parse( T->{						;
 },new ErrorList.Dummy(),chars,braceData);
					parent.accept(new Token(Token.Id._SYNTAX,value));
					return Parser.PASS;
				}
				else{
					chars.position(position);
					CharBuffer buffer = chars.duplicate();
					buffer.limit(value.length());
					errors.adjust(new ParseError.WrongString(null,position,buffer,value));
					return Parser.FAIL;
				}
			}
		}
	}
	public static class Regex implements ParseRule {
		protected ParseRule[] contenders;
		protected String regex;
		public ParseRule[] getContenders(){
			return contenders;
		}
		public String getRegex(){
			return regex;
		}
		public Regex (String regex,ParseRule... contenders){
			this.regex=regex;
			this.contenders=contenders;
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			CharBuffer buffer = chars.duplicate();
			ErrorList subErrors = new ErrorList();
			for(int i = 0;i<contenders.length;++i){
				if(contenders[i].parse(null,subErrors,chars,braceData)==Parser.FAIL){
					errors.adjust(new ParseError.WrongRegex(null,buffer.position(),subErrors.getError().getPosition(),regex));
					chars.position(buffer.position());
					return Parser.FAIL;
				}
			}
			buffer.limit(chars.position());
			parent.accept(new Token(Token.Id._SYNTAX,buffer.toString()));
			Parser.__WHITESPACE__.parse( T->{						;
 },new ErrorList.Dummy(),chars,braceData);
			return Parser.PASS;
		}
	}
	public static class Not implements ParseRule {
		protected ParseRule[] contenders;
		protected String options;
		public ParseRule[] getContenders(){
			return contenders;
		}
		public String getOptions(){
			return options;
		}
		public Not (String options,ParseRule... contenders){
			this.contenders=contenders;
			this.options=options;
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			if(chars.remaining()==0 ){
				errors.adjust(new ParseError.BufferEnd(null,chars.position()));
				return Parser.FAIL;
			}
			CharBuffer buffer = chars.duplicate();
			for(int i = 0;i<contenders.length;++i){
				if(contenders[i].parse(parent,errors,buffer,braceData)==Parser.FAIL){
					errors.adjust(new ParseError.WrongString(null,chars.position(),chars.duplicate().get(),options));
					return Parser.FAIL;
				}
			}
			return Parser.PASS;
		}
	}
	public static class Range implements ParseRule {
		protected int lowerBound;
		protected int upperBound;
		protected String expected;
		public int getLowerBound(){
			return lowerBound;
		}
		public int getUpperBound(){
			return upperBound;
		}
		public String getExpected(){
			return expected;
		}
		public Range (int lowerBound,int upperBound){
			this.lowerBound=lowerBound;
			this.upperBound=upperBound;
			this.expected=((char)lowerBound)+"-"+((char)upperBound);
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			if(chars.remaining()==0 ){
				errors.adjust(new ParseError.BufferEnd(null,chars.position()));
				return Parser.FAIL;
			}
			else{
				char c = chars.get();
				if(c>=lowerBound&&c<=upperBound){
					return Parser.PASS;
				}
				else{
					chars.position(chars.position()-1);
					errors.adjust(new ParseError.WrongString(null,chars.position(),c,expected));
					return Parser.FAIL;
				}
			}
		}
	}
	public static class Any implements ParseRule {
		protected String value;
		public String getValue(){
			return value;
		}
		public Any (String value){
			this.value=value;
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			if(chars.remaining()==0 ){
				errors.adjust(new ParseError.BufferEnd(null,chars.position()));
				return Parser.FAIL;
			}
			else{
				char c = chars.get();
				if(value.chars().anyMatch( C->C==c)){
					return Parser.PASS;
				}
				else{
					chars.position(chars.position()-1);
					errors.adjust(new ParseError.WrongString(null,chars.position(),c,value));
					return Parser.FAIL;
				}
			}
		}
	}
	public static class Char implements ParseRule {
		protected char value;
		public char getValue(){
			return value;
		}
		public Char (char value){
			this.value=value;
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			if(chars.remaining()<1 ){
				errors.adjust(new ParseError.BufferEnd(null,chars.position()));
				return Parser.FAIL;
			}
			else{
				if(chars.get()==this.value){
					return Parser.PASS;
				}
				else{
					chars.position(chars.position()-1);
					errors.adjust(new ParseError.WrongString(null,chars.position(),chars.duplicate().get(),new Character(value).toString()));
					return Parser.FAIL;
				}
			}
		}
	}
}
