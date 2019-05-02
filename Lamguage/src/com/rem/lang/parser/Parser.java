package com.rem.lang.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

interface Beable {
	public Boolean be(char c); 
}
public class Parser {

	static long startTime = System.currentTimeMillis();
	private static Consumer<Object> finalMethod =	S->{}
			//System.out::println
	;
	private static Tokenizer tokenize(Stream<Token> chars){
		return chars
		.collect(
				
				Tokenizer::new,
				Tokenizer::evaluate,
				Tokenizer::merge)
		
		;
	}

	public static void main(String[] args){
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("test.test"));) {
			Tokenizer tokenizer = tokenize(
					Stream.iterate(
							CharPoints.set(new CharPoints(),reader),
							Objects::nonNull
							,C->CharPoints.set(C,reader))
					.parallel()
					.flatMap(CharPoints::get)
					);
			tokenizer.get()
					 .forEach(finalMethod);

		}
		catch(IOException e){e.printStackTrace();}
		System.out.println(System.currentTimeMillis()-Parser.startTime);
	}
}
class CharPoints { 
	private int position;
	private int length;
	protected char[] c;
	public CharPoints(){this.position=0;}
	public CharPoints(int position,int length, char[] c){
		this.position=position;
		this.length = length;
		this.c = c;
	}
	public static CharPoints set(CharPoints previous, BufferedReader reader){
		char[] c = new char[256];
		try {
			int length = reader.read(c);
			if(length==-1){
				return null;
			}
			return new CharPoints(previous.position+previous.length,length,c);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	public Stream<Token> get(){
		return IntStream.range(0,length)
				.mapToObj(I->Token.create(position+I,c[I]));
	}
}
class Token {
	public enum ID {
		
		alpha(TokenState::mergeAlpha,
				c->c>='a'&&c<='z'||c>='A'&&c<='Z'||c=='_',null),
		
		num(TokenState::mergeNum,
				c->c>='0'&&c<='9',
				alpha),
		whitespace(TokenState::mergeMany,
				c->c==' '||c=='\t'||c=='\n'||c=='\r',
				num),
		semicolon(TokenState::mergeSingle,
				c->c==';',
				whitespace),
		
		
		
		alphanum(TokenState::mergeMany),
		error(null,null,null),
		
		root(semicolon)
		;

		
		//,root

		public final BiFunction<TokenState,ID,Optional<ID>> merge;
		public final Beable can;
		private final Function<Character,Optional<ID>> next;
		private ID(ID next) {
			this.merge = null;
			this.can = c->false;
			this.next = c->next.get(c);
		}
		private ID(BiFunction<TokenState,ID,Optional<ID>> merge) {
			this.merge = merge;
			this.can = null;
			this.next = c->Optional.empty();
		}
		private ID(BiFunction<TokenState,ID,Optional<ID>> merge,
				Beable can,
				ID next) {
			this.merge = merge;
			this.can = can;
			this.next = next!=null?
					c->next.get(c):
					c->Optional.empty();
		}
		public Optional<Token.ID> get(char c){
			return Optional.of(this).filter(S->can.be(c)).or(()->next.apply(c));
		}
	}
	
	private int startPosition;
	private Function<StringBuilder,StringBuilder> builder;
	private TokenState state;
	
	public static Token create(int p,char i){
		Token result = new Token();
		result.state = new TokenState();
		result.startPosition = p;
		result.builder = S->S.append(i);
		Token.ID.root.get(i)
				.ifPresentOrElse(result.state::set,
				()->{throw new RuntimeException("Unrecognized character:'"+i+"'");});
		return result;
	}
	private Token extendId(Token otherToken,Token.ID newId) {
		if(newId==Token.ID.error) {
			throw new RuntimeException(
					"Error at "+startPosition+":"+builder.apply(new StringBuilder())+": cannot merge these characters("+state.id+">>"+otherToken.state.id+")");
		}
		Token.this.state.set(newId);
		Function<StringBuilder,StringBuilder> b = builder;
		builder = S->otherToken.builder.apply(b.apply(S));
		return Token.this;
	}
	public Token extend(Token otherToken){
		return state.merge(otherToken.state)
				.map(newId->extendId(otherToken,newId))
				.orElse(otherToken);
		/*
			case none:{
				return otherToken;
			}
			case root:{
				state = otherToken.state;
				startPosition = otherToken.startPosition;
				endPosition = otherToken.endPosition;
				return this;
			}*/

	}
	public String toString(){
		return "("+startPosition+")"+builder.apply(new StringBuilder())+":"+state.id;
	}
}

class Tokenizer {
	private Stream.Builder<Token> stream = Stream.builder();
	private Optional<Token> token = Optional.empty();


	public Stream<Token> get(){
		return token.map(T->stream.add(T).build())
				.orElse(Stream.empty());

	}

	public void evaluate(Token next){
		token = token.map(T->{
			Token newToken = T.extend(next);
			if(newToken!=T){
				stream.add(T);
				return newToken;
			}
			else {
				return T;
			}
		}).or(()->Optional.of(next));
	}
	public void merge(Tokenizer tokenizer){
		tokenizer.get().forEach(this::evaluate);
	}


}
class TokenState {
	public Token.ID id;
	public Optional<Token.ID> merge(TokenState state) {
		return id.merge.apply(this, state.id);
	}
	public Optional<Token.ID> mergeAlpha(Token.ID id) {
		switch(id) {
		case alpha: return Optional.of(Token.ID.alpha);
		case alphanum: return Optional.of(Token.ID.alphanum);
		case num: return Optional.of(Token.ID.alphanum);
		default: return Optional.empty();
		}
	}

	public Optional<Token.ID> mergeNum(Token.ID id) {
		return mergeMany(id).or(()->{
			switch(id) {
			case num: return Optional.of(Token.ID.num);
			case alpha: return Optional.of(Token.ID.error);
			default: return Optional.empty();
			}
		});
	}
	public Optional<Token.ID> mergeSingle(Token.ID id) {
		return Optional.empty();
	}
	public Optional<Token.ID> mergeMany(Token.ID id) {
		return this.id==id?Optional.of(id):Optional.empty();
	}

	public void set(Token.ID id){
		this.id = id;
	}
}
