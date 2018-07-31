package com.rem.gen.parser;
import java.nio.CharBuffer;
import com.rem.gen.parser.Token;
import java.util.function.Consumer;
import com.rem.gen.parser.ParseError;
import java.util.List;
import java.util.stream.IntStream;
public abstract class ParseError {
	protected Token.Id id;
	protected Integer position;
	public Token.Id getId(){
		return id;
	}
	public Integer getPosition(){
		return position;
	}
	public ParseError (Token.Id id,Integer position){
		this.id=id;
		this.position=position;
	}
	public int getLine(List<Integer> lines){
		return IntStream.range(0,lines.size()).parallel().filter( I->position<=lines.get(I)).findFirst().orElse(lines.size());
	}
	public void toString(Consumer<String> builder,List<Integer> lines,CharBuffer chars){
		chars=chars.duplicate();
		int lineIndex = getLine(lines);
		int position;
		if(lineIndex>0 ){
			position=this.position-lines.get(lineIndex-1);
		}
		else{
			position=this.position;
		}
		builder.accept("Error on line ");
		builder.accept(new Integer(lineIndex).toString());
		builder.accept(":");
		description(builder,position);
		builder.accept("\n");
		if(lines.size()>1 ){
			if(lineIndex>1 ){
				chars.position(lines.get(lineIndex-1));
			}
			if(lineIndex<lines.size()){
				chars.limit(lines.get(lineIndex));
			}
		}
		chars.chars().forEach( I->builder.accept(new Character((char)I).toString()));
		builder.accept("\n");
		IntStream.range(0,position).forEach( I->builder.accept(" "));
		builder.accept("^\n");
	}
	public abstract void description(Consumer<String> builder,int carrotPosition);
	public static class BufferEnd extends ParseError {
		public BufferEnd (Token.Id id,Integer position){
			super(id,position);
		}
		public void description(Consumer<String> builder,int carrotPosition){
			builder.accept("Unexpected End of Input:");
		}
	}
	public static class WrongString extends ParseError {
		protected String found;
		protected CharBuffer foundBuffer;
		protected String options;
		public String getFound(){
			return found;
		}
		public CharBuffer getFoundBuffer(){
			return foundBuffer;
		}
		public String getOptions(){
			return options;
		}
		public WrongString (Token.Id id,Integer position,char found,String options){
			super(id,position);
			this.found=new Character(found).toString();
			this.options=options;
		}
		public WrongString (Token.Id id,Integer position,String found,String options){
			super(id,position);
			this.found=found;
			this.options=options;
		}
		public WrongString (Token.Id id,Integer position,CharBuffer found,String options){
			super(id,position);
			this.foundBuffer=found;
			this.options=options;
		}
		public void description(Consumer<String> builder,int carrotPosition){
			if(found==null){
				found=foundBuffer.chars().boxed().reduce(new StringBuilder(),(B,I)->{						int i = I;
						B.append((char)i);
						return B;
 },(N,P)->N).toString();
			}
			this.options=options.replace("\n","\\n").replace("\t","\\t").replace("\r","\\r");
			if(this.found.length()<=1 ){
				builder.accept("Char");
			}
			else{
				builder.accept("String");
			}
			builder.accept(" Expected: ");
			builder.accept(this.options);
			builder.accept("\t but found: ");
			builder.accept(this.found);
		}
	}
	public static class WrongRegex extends ParseError {
		protected Integer subPosition;
		protected String options;
		public Integer getSubPosition(){
			return subPosition;
		}
		public String getOptions(){
			return options;
		}
		public WrongRegex (Token.Id id,Integer position,Integer subPosition,String options){
			super(id,position);
			this.subPosition=subPosition;
			this.options=options;
		}
		public void description(Consumer<String> builder,int carrotPosition){
			builder.accept("Error:\n");
			builder.accept("\tRegex Failed: ");
			builder.accept(this.options);
			builder.accept("\t At:\n");
			IntStream.range(0,carrotPosition+subPosition).forEach( I->builder.accept(" "));
			builder.accept("v");
		}
	}
}
