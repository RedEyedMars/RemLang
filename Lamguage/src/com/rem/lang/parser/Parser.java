package com.rem.lang.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.rem.lang.parser.ParsedToken.ID;

interface Beable {
	public Boolean be(char c); 
}
public class Parser {

	static long startTime = System.currentTimeMillis();
	private static Consumer<Object> finalMethod =				S->{};
	//System.out::println;
	private static Tokenizer tokenize(Stream<ParsedToken> chars){
		return chars.collect(
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
			ContextedToken.Branch.ROOT.process(UncontextedToken.map(tokenizer.get()))
			.ifPresentOrElse(C->{
				System.out.println("Present");
				C
			.build()
			.get()
			.value
			.forEach(L->L.getString.ifPresent(S->System.out.println(S.get())));}
			,()->{System.out.println("Error");});
			/*tokenizer.get()
			.forEach(finalMethod);*/

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
	public Stream<ParsedToken> get(){
		return IntStream.range(0,length)
				.mapToObj(I->ParsedToken.create(position+I,c[I]));
	}
}
class ParsedToken {
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
		plus(TokenState::mergePlus,
				c->c=='+',
				semicolon),
		equal(TokenState::mergeSingle,
				c->c=='+',
				plus),
		plus_equals(TokenState::mergeSingle,
				c->c=='+',
				equal),

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
		public Optional<ParsedToken.ID> get(char c){
			return Optional.of(this).filter(S->can.be(c)).or(()->next.apply(c));
		}
	}

	private int startPosition;
	private Function<StringBuilder,StringBuilder> builder;
	private TokenState state;

	public static ParsedToken create(int p,char i){
		ParsedToken result = new ParsedToken();
		result.state = new TokenState();
		result.startPosition = p;
		result.builder = S->S.append(i);
		ParsedToken.ID.root.get(i)
		.ifPresentOrElse(result.state::set,
				()->{throw new RuntimeException("Unrecognized character:'"+i+"'");});
		return result;
	}
	private ParsedToken extendId(ParsedToken otherToken,ParsedToken.ID newId) {
		if(newId==ParsedToken.ID.error) {
			throw new RuntimeException(
					"Error at "+startPosition+":"+builder.apply(new StringBuilder())+": cannot merge these characters("+state.id+">>"+otherToken.state.id+")");
		}
		ParsedToken.this.state.set(newId);
		Function<StringBuilder,StringBuilder> b = builder;
		builder = S->otherToken.builder.apply(b.apply(S));
		return ParsedToken.this;
	}
	public ParsedToken extend(ParsedToken otherToken){
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
		return "("+startPosition+")"+getString()+":"+state.id;
	}
	public String getString() {
		return builder.apply(new StringBuilder()).toString();
	}
	public ID getID() {
		return state.id;
	}
	public int getPosition() {
		return this.startPosition;
	}
}
class UncontextedToken<T> {
	enum Keyword {
		PUBLIC("public"),
		PROTECTED("protected"),
		PRIVATE("private"),

		CLASS("class"),
		INTERFACE("interface"),
		ENUM("enum"),
		;
		private final String string;
		private Keyword(String string) {
			this.string = string;
		}
		public static Optional<Keyword> find(ParsedToken.ID id, String string) {
			if(id!=ParsedToken.ID.alpha)return Optional.empty();
			return Arrays.stream(Keyword.values()).filter(K->K.string.equals(string)).findAny();
		}
		public UncontextedToken<Keyword> create(ParsedToken token){
			return new UncontextedToken<Keyword>(token,this,string);
		}
		public static Predicate<UncontextedToken<?>> is(Keyword... keywords) {
			return T->{
				if(!T.isKeyword())return false;
				return Arrays.stream(keywords).anyMatch(K->K==T.getID());
			};
		}
	}
	enum Operator {
		PLUS(ParsedToken.ID.plus,"+"),
		EQUAL(ParsedToken.ID.equal,"="),
		PLUS_EQUALS(ParsedToken.ID.plus_equals,"+="),
		SEMICOLON(ParsedToken.ID.semicolon,";"),
		;
		private final ParsedToken.ID id;
		private final String string;
		private Operator(ParsedToken.ID id, String string) {
			this.id = id;
			this.string = string;
		}
		public static Optional<Operator> find(ParsedToken.ID id) {
			return Arrays.stream(Operator.values()).filter(O->O.id==id).findAny();
		}
		public UncontextedToken<Operator> create(ParsedToken token) {
			return new UncontextedToken<Operator>(token,this,string);
		}
		public static Predicate<UncontextedToken<?>> is(Operator... operators) {
			return T->{
				if(!T.isOperator())return false;
				return Arrays.stream(operators).anyMatch(K->K==T.getID());
			};
		}
	}
	enum Value {
		NAME,
		NUMBER

		;
		public static Optional<Value> find(ParsedToken.ID id) {
			switch(id) {
			case alpha:return Optional.of(NAME);
			case alphanum: return Optional.of(NAME);
			case num: return Optional.of(NUMBER);
			default:return Optional.empty(); 
			}
		}
		public UncontextedToken<Value> create(ParsedToken token, String string) {
			return new UncontextedToken<Value>(token,this,string);
		}
		public static Predicate<UncontextedToken<?>> is(Value... values) {
			return T->{
				if(!T.isValue())return false;
				return Arrays.stream(values).anyMatch(K->K==T.getID());
			};
		}
	}
	private int position;
	private T id;
	private String string;
	private UncontextedToken(ParsedToken token, T t, String actualString) {
		this.position = token.getPosition();
		this.id = t;
		this.string = actualString;
	}
	public String toString() {
		return string+":"+id+"("+position+")";
	}
	public T getID() {
		return id;
	}
	public boolean isKeyword() {
		return id.getClass()==Keyword.class;
	}
	public boolean isOperator() {
		return id.getClass()==Operator.class;
	}
	public boolean isValue() {
		return id.getClass()==Value.class;
	}
	public String getString() {
		return string;
	}
	public String getReadableString() {
		return isOperator()?string:string+" ";
	}
	public int getPosition() {
		return position;
	}
	public static Optional<Supplier<UncontextedToken<?>>> get(ParsedToken token) {
		if(token.getID()==ParsedToken.ID.whitespace) {
			return Optional.empty();
		}
		else {
			return Operator
					.find(token.getID())
					.map(I->(Supplier<UncontextedToken<?>>)()->I.create(token))
					.or(()->{
						String actualString = token.getString();
						return Keyword.find(token.getID(), actualString)
								.map(I->(Supplier<UncontextedToken<?>>)()->I.create(token))
								.or(()->Value.find(token.getID())
										.map(I->()->I.create(token,actualString)))
								;})
					;
		}
	}
	private static Stream<UncontextedToken<?>> stream(Stream<ParsedToken> tokens){
		return tokens.parallel()
				.map(UncontextedToken::get)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Supplier::get);
	}
	public static List<UncontextedToken<?>> map(Stream<ParsedToken> tokens){
		return stream(tokens).collect(Collectors.toList());
	}
}
class ContextedToken<IdType,ValueType> {

	enum Leaf {
		METHOD_ACCESS(Judgement.OPTIONAL_RECORD,
				UncontextedToken.Keyword.is(
						UncontextedToken.Keyword.PRIVATE,
						UncontextedToken.Keyword.PROTECTED,
						UncontextedToken.Keyword.PUBLIC))
		,METHOD_TYPE(Judgement.SINGLE_RECORD,UncontextedToken.Value.is(UncontextedToken.Value.NAME))
		,METHOD_NAME(Judgement.SINGLE_RECORD,UncontextedToken.Value.is(UncontextedToken.Value.NAME))
		,METHOD_SEMICOLON(Judgement.SINGLE_RECORD,UncontextedToken.Operator.is(UncontextedToken.Operator.SEMICOLON))
		
		;
		private final Predicate<UncontextedToken<?>> onJudge;
		private final Judgement judgement;
		private Supplier<Intermediate<?>> create(UncontextedToken<?> token){
			return ()->Intermediate.leaf(Leaf.this, token);
		}
		@SafeVarargs
		private Leaf(Judgement judgement,
				Predicate<UncontextedToken<?>>... predicates) {
			this.judgement = judgement;
			this.onJudge = predicates.length==0?
				 T->true:
				 T->Arrays.stream(predicates).allMatch(P->P.test(T));
		}
		public IntermediateResult process(List<UncontextedToken<?>> tokens, IntWrapper tokenIndex) {
			UncontextedToken<?> token = tokens.get(tokenIndex.get());
			return new IntermediateResult(
					create(token),
					judgement,
					onJudge.test(token)
					);
		}
		
	}
	enum Progression {
		RECORD_SINGLE		   (Intermediate.Branch::addToken,Movements::CONTINUE,Movements::CONTINUE),
		RECORD_MANY            (Intermediate::addToken,Movements::CONTINUE,Movements::SAME),
		RETRY              	   (null,Movements::SAME,Movements::CONTINUE),
		RECORD_BRANCH 		   (Intermediate::addToken,Movements::SAME,Movements::SAME),
		
		SKIP_SINGLE            (null,Movements::CONTINUE,Movements::CONTINUE),
		SKIP_MANY              (null,Movements::CONTINUE,Movements::SAME),
		;
		private final Optional<BiConsumer<Intermediate.Branch,Intermediate<?>>> record;
		private final Consumer<IntWrapper> moveToken;
		private final Consumer<IntWrapper> moveState;
		private Progression(BiConsumer<Intermediate.Branch,Intermediate<?>> record,Consumer<IntWrapper> moveToken, Consumer<IntWrapper> moveState) {
			this.record = Optional.ofNullable(record);
			this.moveState = moveState;
			this.moveToken = moveToken;
		}
	}
	interface Movements{
		public static void CONTINUE(IntWrapper i) {
			i.inc();
		}
		public static void SAME(IntWrapper i) {
		}
	}
	enum Judgement {
		SINGLE_RECORD            (Progression.RECORD_SINGLE,null),
		MANY_RECORD              (Progression.RECORD_MANY,Progression.RETRY),
		OPTIONAL_RECORD          (Progression.RECORD_SINGLE,Progression.RETRY),
		
		SINGLE_SKIP            (Progression.SKIP_SINGLE,null),
		MANY_SKIP              (Progression.SKIP_MANY,Progression.RETRY),
		
		BRANCH_RECORD            (Progression.RECORD_BRANCH,Progression.RETRY),
		;
		private final Progression onSuccess;
		private final Optional<Progression> onFail;
		private Judgement(Progression onSuccess, Progression onFail) {
			this.onSuccess = onSuccess;
			this.onFail = Optional.ofNullable(onFail);
		}
	}
	private static class IntWrapper {
		private int i=0;
		public int get() {return i;}
		public void inc() {++i;}
		public void set(int j) {
			i=j;
		}
	}
	enum Branch {
		METHOD(
			Judgement.BRANCH_RECORD,
			Leaf.METHOD_ACCESS::process,
			Leaf.METHOD_TYPE::process,
			Leaf.METHOD_NAME::process,
			Leaf.METHOD_SEMICOLON::process
		)
,
		ROOT(Judgement.BRANCH_RECORD,
				Branch.METHOD::process)
		;
		private final BiFunction<List<UncontextedToken<?>>, IntWrapper,IntermediateResult>[] states;
		private final Judgement judgement;
		@SafeVarargs
		private Branch(Judgement judgement,
				BiFunction<List<UncontextedToken<?>>, IntWrapper,IntermediateResult>... states) {
			this.states = states;
			this.judgement = judgement;
		}
		
		public Optional<Intermediate.Branch> process(List<UncontextedToken<?>> tokens) {
			IntWrapper tokenIndex = new IntWrapper();
			return Optional.of(process(tokens,tokenIndex)).filter(I->I.success).map(I->(Intermediate.Branch)I.token());
		}

		public IntermediateResult process(List<UncontextedToken<?>> tokens, IntWrapper tokenIndex) {
			Stream.Builder<Consumer<Intermediate.Branch>> children = Stream.builder();
			int startIndex = tokenIndex.get();
			IntWrapper stateIndex = new IntWrapper();
			while(tokenIndex.get()<tokens.size()&&stateIndex.get()<this.states.length) {
				states[stateIndex.get()].apply(tokens,tokenIndex)
				.progress(
						T->
						S->{
							S.record.ifPresent(R->children.accept(B->R.accept(B, T.get())));
							S.moveToken.accept(tokenIndex);
							S.moveState.accept(stateIndex);
						},
						()->{
							tokenIndex.set(startIndex);
							stateIndex.set(states.length+1);});
				/*
				
				(S,I)->{
					//System.out.println(this+"record:"+tokens.get(tokenIndex.get())+":"+S.id+":"+S.success);
					children.accept(S.record);
				},);
				*/
			}
			return new IntermediateResult(()->
			{
				Intermediate.Branch branch = Intermediate.branch(this);
				children.build().forEach(C->C.accept(branch));
				return branch;
			},judgement,stateIndex.get()!=states.length+1);
			//System.out.println(stateIndex.get()+":"+((ContextedToken<?,?>)current.build().get()).value);
		}
	}
	static class IntermediateResult {
		public final Supplier<Intermediate<?>> token;
		public final Judgement judgement;
		private final boolean success;
		public IntermediateResult(Supplier<Intermediate<?>> token,
				Judgement judgement,
				boolean success
				) {
			this.token = token;
			this.judgement = judgement;
			this.success = success;
		}
		public void progress(
				Function<Supplier<Intermediate<?>>,Consumer<Progression>> onProgress,
							Runnable onFail) {
			Optional.of(judgement.onSuccess)
					.filter(S->success)
					.or(()->judgement.onFail)
					.ifPresentOrElse(
							onProgress.apply(token)
							,
							onFail
					);
		}
		public void progress(BiConsumer<Progression,Supplier<Intermediate<?>>> progressor, Runnable onFail) {
			if(success) {
				progressor.accept(judgement.onSuccess,token);
			}
			else {
				judgement.onFail.ifPresentOrElse(S->progressor.accept(S, token),onFail);
			}
		}
		public Intermediate<?> token(){
			if(!success)throw new IllegalStateException();
			return token.get();
		}
	}
	static abstract class Intermediate<Type> {
		static class Branch extends Intermediate<ContextedToken.Branch>{

			private final Stream.Builder<Intermediate<?>> children;
			private boolean cleared = false;
			private Branch(ContextedToken.Branch id){
				super(id);
				this.children = Stream.builder();
			}
			public void addToken(Intermediate<?> supplier) {
				this.children.add(supplier);
			}
			public Optional<ContextedToken<ContextedToken.Branch,List<ContextedToken<?,?>>>> build(){
					
					List<ContextedToken<?,?>> children = 
							this.children.build()
										 .map(I->I.build())
										 .filter(I->I.isPresent())
										 .map(I->(ContextedToken<?,?>)I.get())
										 .collect(Collectors.toList());
					if(children.isEmpty()) {
						throw new RuntimeException("Unculled Branch:"+id);
					}
					else if(cleared) {
						return Optional.empty();
					}
					else {
						return Optional.of(new ContextedToken<ContextedToken.Branch,List<ContextedToken<?,?>>>(
								id,
								children,
								Optional.of(children::get),
								Optional.of(children::stream),
								children.get(0).position,
								Optional.of(()->children.stream().collect(
										StringBuilder::new,
										(S,C)->C.getString.ifPresent(s->S.append(s.get())),
										StringBuilder::append).toString()),
								children.get(0).getToken
								));
					}
				}
		}
		static class Leaf extends Intermediate<ContextedToken.Leaf> {
			private final UncontextedToken<?> token;
			private Leaf(ContextedToken.Leaf id, UncontextedToken<?> token) {
				super(id);
				this.token = token;
			}
			public Optional<ContextedToken<ContextedToken.Leaf,UncontextedToken<?>>> build(){
				return Optional.of(new ContextedToken<ContextedToken.Leaf,UncontextedToken<?>>(
						id,
						token,
						Optional.empty(),
						Optional.empty(),
						Optional.of(token::getPosition),
						Optional.of(token::getReadableString),
						Optional.of(()->token)
						));
			}
			@Override
			public void addToken(Intermediate<?> supplier) {
			}
		}
		protected final Type id;
		protected Intermediate(Type id) {
			this.id = id;
		}
		
		public static Intermediate.Branch branch(ContextedToken.Branch id) {
			return new Intermediate.Branch(id);
		}
		public static Intermediate<?> leaf(ContextedToken.Leaf id, UncontextedToken<?> token) {
			return new Intermediate.Leaf(id,token);
		}
		
		public abstract void addToken(Intermediate<?> supplier);
		public abstract Optional<?> build();
	}
	public final IdType id;
	public final ValueType value;
	public final Optional<Function<Integer,ContextedToken<?,?>>> get;
	public final Optional<Supplier<Stream<ContextedToken<?,?>>>> getAll;
	public final Optional<Supplier<Integer>> position;
	public final Optional<Supplier<String>> getString;
	public final Optional<Supplier<UncontextedToken<?>>> getToken;
	public ContextedToken(
			IdType id,
			ValueType value,
			Optional<Function<Integer,ContextedToken<?,?>>> get,
			Optional<Supplier<Stream<ContextedToken<?,?>>>> getAll,
			Optional<Supplier<Integer>> position,
			Optional<Supplier<String>> getString,
			Optional<Supplier<UncontextedToken<?>>> getToken
			) {
		this.id = id;
		this.value = value;
		this.get = get;
		this.getAll = getAll;
		this.position = position;
		this.getString = getString;
		this.getToken = getToken;
	}
	
}
class Tokenizer {
	private Stream.Builder<ParsedToken> stream = Stream.builder();
	private Optional<ParsedToken> token = Optional.empty();


	public Stream<ParsedToken> get(){
		return token.map(T->stream.add(T).build())
				.orElse(Stream.empty());

	}

	public void evaluate(ParsedToken next){
		token = token.map(T->{
			ParsedToken newToken = T.extend(next);
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
	public ParsedToken.ID id;
	public Optional<ParsedToken.ID> merge(TokenState state) {
		return id.merge.apply(this, state.id);
	}
	public Optional<ParsedToken.ID> mergeAlpha(ParsedToken.ID id) {
		switch(id) {
		case alpha: return Optional.of(ParsedToken.ID.alpha);
		case alphanum: return Optional.of(ParsedToken.ID.alphanum);
		case num: return Optional.of(ParsedToken.ID.alphanum);
		default: return Optional.empty();
		}
	}

	public Optional<ParsedToken.ID> mergeNum(ParsedToken.ID id) {
		return mergeMany(id).or(()->{
			switch(id) {
			case num: return Optional.of(ParsedToken.ID.num);
			case alpha: return Optional.of(ParsedToken.ID.error);
			default: return Optional.empty();
			}
		});
	}
	public Optional<ParsedToken.ID> mergePlus(ParsedToken.ID id){ 
		return id==ParsedToken.ID.equal?Optional.of(ParsedToken.ID.plus_equals):Optional.empty();
	}
	public Optional<ParsedToken.ID> mergeSingle(ParsedToken.ID id) {
		return Optional.empty();
	}
	public Optional<ParsedToken.ID> mergeMany(ParsedToken.ID id) {
		return this.id==id?Optional.of(id):Optional.empty();
	}

	public void set(ParsedToken.ID id){
		this.id = id;
	}
}
